<?php
$user_type = "admin";
require_once("../inc_files/check_session.inc");
require_once("../inc_files/display_fxns.inc");
include("../inc_files/course_fxns.inc");
require_once("../inc_files/some_useful_functions.inc");
if(isset($_GET["cs"])){
	//THE USER HAS SELECTED A COURSE TO MODIFY.
	//DISPLAY THE COURSE INFORMATION IN A FORM.
	echo("<H3>Do your modifications then press submit.</H3>");
	display_course_mod_form($_GET["cs"]);
}
else if(isset($_POST["cs"])){
	//PROCESS TEH COURSE MODIFICATION
	process_course_mod($_POST);
}
else{
	//THE USER JUST CAME TO THE PAGE. LIST COURSES TO SELECT FROM
	display_list_of_courses("Please select a course to modify.", "modcou");
}

function process_course_mod($_POST){
	$course_name = nl2br(htmlspecialchars($_POST["name"]));
	$course_num = $_POST["number"];
	$dept_seq = $_POST["dept_seq"];
	$description = nl2br(htmlspecialchars($_POST["description"]));
	$modification = "INSERT INTO course_def " .
			  "(course_name, course_number, category_seq";
	$prereq_count = 0;
	//GET NUMBER OF PREREQS
	for($i = 0; $i < sizeof($_POST); $i++){
		$temp = "prereq_" . $i;
		if(isset($_POST[$temp])){
			$prereq[$temp] = $_POST[$temp];
			$prereq_count++;
		}
	}
	
	
	//view_array($prereq);
	remove_duplicate_prereqs($_POST, $prereq_count);
	//view_array($prereq);
	
	for($i = 1; $i <= $prereq_count; $i++){
		$temp = "prereq_" . $i;
		if(isset($_POST[$temp]))
			$modification .= ", prereq_seq_$i";
	}
			  
	$modification .= ", description) " .
			  "VALUES " .
			  "('$course_name', '$course_num', '$dept_seq'";
	
	for($i = 1; $i <= $prereq_count; $i++){
		$temp = "prereq_" . $i;
		if(isset($_POST[$temp]))
			$modification .= ", '" . $_POST[$temp] . "'";
	}
	$modification .= ", \"$description\")";
	
	//mssql_query($modification) or die("Error in $modification<BR>");
	echo($modification . "<BR>");
	echo("<H2 ALIGN=\"CENTER\">Course $course_name modified</H2>");
	echo("<H2 ALIGN=\"CENTER\">Add display all courses here (course_create.php line 42)</H2>");
	//PROCESS COURSE MODIFICATION.
	
}
function display_course_mod_form($course_seq){
	include("../inc_files/variables.inc");
	$course = "SELECT * FROM course_def, test_category_def WHERE course_seq = $course_seq";
	$course = mssql_query($course);
	$course = mssql_fetch_row($course);
	
	$name = stripslashes($course[1]);
	$number = $course[2];
	$category_seq = $course[3];
	$description = stripslashes($course[24]);
	$prereqs = "";
	//view_array($course);
	for($i=1;$i<6;$i++){
		$prereqseq[$i] = $course[$i+3];
	}
	echo("<FORM NAME=\"FORM\" METHOD=\"post\" ACTION=\"" . $_SERVER['PHP_SELF'] . "?cat=cou&scr=modcou\" ONSUBMIT=\"return validateData(this)\">\n\t\t");
	echo("<INPUT TYPE=\"hidden\" NAME=\"cs\" VALUE=\"$course_seq\">\n\t");
	echo("<TABLE WIDTH=600 border=1 align=center valign=center>\n\t");
	echo("<TR>\n\t");
	echo("<TD>Name:</TD>\n\t");
	echo("<TD COLSPAN=5><INPUT TYPE=\"text\" NAME=\"name\" SIZE=\"50\" VALUE=\"" . $name . "\"></TD>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD WIDTH=20>Number:</TD>\n\t");
	echo("<TD COLSPAN=5><INPUT TYPE=\"text\" NAME=\"number\" SIZE=\"8\" VALUE=\"" . $number . "\"></TD>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD>Category:</TD>\n\t");
	echo("<TD COLSPAN=5>\n\t\t<SELECT NAME=\"dept_seq\">");
	$cats = "SELECT * FROM test_category_def";
	$cats = mssql_query($cats);
	while($cat = mssql_fetch_row($cats)){	
		$category[$cat[0]] = $cat[1];
		echo("\n\t\t\t<OPTION ");
		if($cat[0] == $category_seq) echo("SELECTED ");
		echo("VALUE=\"" .$cat[0]. "\">" . $cat[1]);	
	}
	echo("\n\t\t</SELECT>\n\t</TD>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD WIDTH=20>Description:</TD>\n\t");	
	echo("<TD COLSPAN=5>\n\t\t");
	echo("<TEXTAREA NAME=\"description\" ROWS=\"3\" COLS=\"" . ($num_cols - ($num_cols/5)) ."\">");
	echo("$description");
	echo("</TEXTAREA>\n\t");
	echo("</TD>\n\t");		
	echo("</TR>\n\t");
	$tests = "SELECT * FROM course_def, test_category_def " .
			"WHERE test_category_def.category_seq = course_def.category_seq ORDER BY course_def.course_number";
	$test_list = mssql_query($tests) or die("Error in $tests<BR>create_course line 39<BR>");
	echo("<TR>\n\t\t");
	echo("<TH>Prereq?</TH><TH>Number</TH><TH>Name</TH><TH>Category</TH><TH>Description</TH><TH>Prereq's</TH>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t\t");
	$prereq_num = 1;
	for($x = 1; $test_info = mssql_fetch_array($test_list); $x++){
		$course_num = $test_info["course_number"];
		echo("<TR>\n\t");
		echo("\t<TD ALIGN=\"CENTER\">\n\t\t");
		echo("<INPUT TYPE=\"CHECKBOX\" NAME=prereq_$x VALUE=\"" . $test_info["course_seq"] . "\"");
		
		if(@in_array($test_info["course_seq"], $prereqseq))
			echo(" CHECKED");		
		echo(">\n\t");
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
	}
	echo("<TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD align=\"center\" colspan=\"6\"><INPUT TYPE=\"submit\" NAME=\"edit\" VALUE=\"Submit Changes\">&nbsp&nbsp<INPUT TYPE=\"submit\" NAME=\"edit\" VALUE=\"Cancel\"></TD>\n\t\t");
	echo("</TR>\n\t");
	echo("</TABLE>");
}
?>