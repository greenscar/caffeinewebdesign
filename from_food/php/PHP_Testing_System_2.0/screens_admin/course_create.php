<?php
/*
* This screen will be used by the admin to modify existing users in the testing system
* Uses will include but won't be limited to:
* 	1) Modify a person's securiy level
* 	2) Modify a person's name
* 	3) Modify a person's testing scheme
*/
$user_type = "admin";
require("../inc_files/check_session.inc");
require("../inc_files/sql_connect.inc");
//include("../inc_files/display_fxns.inc");
include("../inc_files/course_fxns.inc");
if(@$_POST["edit"] == "Submit"){
	$course_name = nl2br(htmlspecialchars($_POST["course_name"]));
	$course_num = $_POST["course_num"];
	$dept_seq = $_POST["dept_seq"];
	$description = nl2br(htmlspecialchars($_POST["description"]));
	$insert = "INSERT INTO course_def " .
			  "(course_name, course_number, category_seq";
	$prereq_count = 0;
	//GET NUMBER OF PREREQS
	for($i = 1; $i < sizeof($_POST); $i++){
		$temp = "prereq_" . $i;
		if(isset($_POST[$temp])){
			$prereq_count++;
		}
	}
	remove_duplicate_prereqs($_POST, $prereq_count);
	
	for($i = 1; $i <= $prereq_count; $i++){
		$temp = "prereq_" . $i;
		if(isset($_POST[$temp]))
			$insert .= ", prereq_seq_$i";
	}
			  
	$insert .= ", description) " .
			  "VALUES " .
			  "('$course_name', '$course_num', '$dept_seq'";
	
	for($i = 1; $i <= $prereq_count; $i++){
		$temp = "prereq_" . $i;
		if(isset($_POST[$temp]))
			$insert .= ", '" . $_POST[$temp] . "'";
	}
	$insert .= ", \"$description\")";
	mssql_query($insert) or die("Error in $insert<BR>");
	echo("<H2 ALIGN=\"CENTER\">Course $course_name created</H2>");
	echo("<H2 ALIGN=\"CENTER\">Add display all courses here (course_create.php line 42)</H2>");
}	
else{
	$cat_def_ary = array();
	echo("<H3 align=\"center\">Enter your course infomation</H3>")	;
	$cat_def = "SELECT * FROM test_category_def";
	$temp = mssql_query($cat_def) or die("Error in $cat_def<BR>");
	for($x = 1;$cat_def = mssql_fetch_array($temp); $x++){		
		$cat_def_ary[$x] = $cat_def[1];		
	}
	$tests = "SELECT * FROM course_def, test_category_def " .
			"WHERE test_category_def.category_seq = course_def.category_seq ORDER BY course_def.course_number";
	$test_list = mssql_query($tests) or die("Error in $tests<BR>create_course line 39<BR>");
	
	echo("<FORM NAME=\"FORM\" METHOD=\"post\" ACTION=\"" . $_SERVER['PHP_SELF'] . "?cat=course&scr=createcou\" ONSUBMIT=\"return validate_numbers(this)\">\n\t\t");
	echo("<TABLE border = 0 align=center width=500>\n\t");
	echo("<TR><TD>");
	echo("<TABLE border=1 align=center>\n\t");
	echo("<TR>\n\t");
	echo("<TD>Name:</TD>\n\t");
	echo("<TD><INPUT TYPE=\"text\" NAME=\"course_name\" SIZE=\"50\"></TD>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD>Course #:</TD>\n\t");
	echo("<TD><INPUT TYPE=\"text\" NAME=\"course_num\" SIZE=\"10\"></TD>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD>Department:</TD>\n\t");
	echo("<TD>\n\t\t<SELECT NAME=\"dept_seq\">");
	for($x = 1; $x <= sizeof($cat_def_ary); $x++){
		echo("\n\t\t\t<OPTION VALUE=\"" .$x. "\">" . $cat_def_ary[$x]);
	}
	echo("\n\t\t</SELECT>\n\t</TD>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD>Description:</TD>\n\t");
	echo("<TD>\n\t\t");
	echo("<TEXTAREA NAME=\"description\" ROWS=\"3\" COLS=\"" . ($num_cols - ($num_cols/5)) ."\"></TEXTAREA>");
	echo("</TD>\n\t\t");
	echo("</TR>\n\t");
	echo("</TABLE>\n");
	echo("</TD></TR>\n");
	echo("<TR>\n\t");
	echo("<TD>\n\t");
	echo("<TABLE border=1 align=center>\n\t");
	echo("<TR>\n\t\t");
	echo("<TH>Prereq?</TH><TH>Number</TH><TH>Name</TH><TH>Category</TH><TH>Description</TH><TH>Prereq's</TH>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t\t");
	$prereq_num = 1;
	for($x = 1; $test_info = mssql_fetch_array($test_list); $x++){
		$course_num = $test_info["course_number"];
		echo("<TR>\n\t");
		echo("\t<TD ALIGN=\"CENTER\">\n\t\t");
		echo("<INPUT TYPE=\"CHECKBOX\" NAME=prereq_$x VALUE=\"" . $test_info["course_seq"] . "\">\n\t");
		echo("</TD>\n\t");
		echo("<TD ALIGN=\"CENTER\">\n\t\t");
		echo($test_info["course_number"]);
		echo("</TD>\n\t");
		echo("<TD ALIGN=\"CENTER\">\n\t\t");
		echo(stripslashes($test_info["course_name"]));
		echo("</TD>\n\t");
		echo("<TD ALIGN=\"CENTER\">\n\t\t");
		echo($test_info["category_name"]);
		echo("</TD>\n\t");
		echo("<TD>\n\t\t");
		if(!empty($test_info["description"])) echo(stripslashes($test_info["description"]));
		else echo("&nbsp");
		echo("</TD>\n\t");
		echo("<TD>\n\t\t");
		//Assign all prerequisits to a string
		$count = 0;
		for($i = 1; $i < 6; $i++){
			$temp = "prereq_seq_$i";
			if(!empty($test_info[$temp])){
				$select = "SELECT course_number FROM course_def WHERE course_seq = " . $test_info[$temp];
				$prereqseq = mssql_query($select);
				$prereqseq = mssql_fetch_array($prereqseq);
				$prereqseq = $prereqseq[0];
				if($count == 0){
					$prereq = $prereqseq;
					$count++;
				} 
				else $prereq .=  ", " . $prereqseq;
			}
		}
		//Display the prerequisit string
		if(!empty($prereq))echo("\t\t" .$prereq);
		else echo("&nbsp");
		$prereq = null;
		echo("</TD>\n\t");
		echo("</TR>\n");
		//view_array($test_info);
	}
	echo("</TD></TR>\n");
	echo("</TABLE>\n");
	echo("</TD></TR>\n");
	echo("<TR>\n\t");
	echo("<TD align=\"center\" colspan=\"2\"><INPUT TYPE=\"submit\" NAME=\"edit\" VALUE=\"Submit\">&nbsp&nbsp");
	echo("<INPUT TYPE=\"BUTTON\" VALUE=\"Cancel\" onClick=\"window.location='./" . "menu_". @$the_user->get_sec_lvl() . ".php'\">\n");
	echo("</TR>\n\t");
	echo("</TABLE>\n");
	echo("</FORM>\n");
	
}

?>