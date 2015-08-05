<?php
/*
* These 2 functions are used to display the contents of any array.
*/
function view_array($arr)
{
	echo "\t<TABLE CELLPADDING=\"0\" CELLSPACING=\"0\" BORDER=\"1\">\n";
	foreach ($arr as $key1 => $elem1) 
	{
		echo("\t\t<TR>\n");
		echo("\t\t\t<TD>$key1&nbsp;</TD>\n");
		if (is_array($elem1))  
			extArray($elem1);
		else  
			echo("\t\t\t<TD>". stripslashes($elem1)."&nbsp;</TD>\n"); 
		echo("\t\t</TR>\n");
	}
	echo("\t</TABLE>\n");
}
	
function is_lowercase_alpha($input){
	return (("a" <= $input && $input <= "z"))?true:false;
}

//This function is called from viewArray to take care of multi-dimensional arrays
function extArray($arr)
{
	echo("\t\t\t<TD>\n");
	echo "\t<TABLE CELLPADDING=\"0\" CELLSPACING=\"0\" BORDER=\"1\">\n";
	foreach ($arr as $key => $elem) 
	{
		echo("\t\t<TR>\n");
		echo("\t\t\t<TD>$key&nbsp;</TD>\n");
		if (is_array($elem)) 
			extArray($elem);
		else 
			echo("\t\t\t<TD>". stripslashes($elem)."&nbsp;</TD>\n");
		echo("\t\t</TR>\n");
	}
	echo("\t</TABLE>\n");
	echo("</TD>\n");
}


/*
* This will be used only if we want to see all variables
* 	posted via form
*/		
function display_all_post_vars_and_values($_POST){
	echo("<TABLE ALIGN=CENTER WIDTH=\"50%\" BORDER=\"1\">\n");
	echo("<TR><TH>KEY</TH><TH>VALUE</TH></TR>");
	foreach ( $_POST as $key => $value ) { 
		echo ("<TR><TD>$key</TD>\n<TD>$value</TD></TR>\n");
	}
	echo("</TABLE>");
}

function display_question_type_menu(){
	echo("\t<A NAME=\"qtm\"><H3> Please select the type of the next question.</H3></A>\n");
	$question_type_string = "SELECT * FROM question_type_def";
	$search_results = mssql_query($question_type_string) or die("Error in Query: $question_type_string. mssql said " . mssql_error() . '.');  //select the username 
	echo("\t<TABLE ALIGN=\"CENTER\">\n");
	echo("\t\t<SELECT NAME = \"next_question_type_seq\" onChange=\"check_num_blanks()\" SIZE = \"1\">\n");
	echo("\t\t<OPTION SELECTED VALUE = \"\">Question Type</OPTION>\n");
	while($_POST = mssql_fetch_array($search_results)){
		if($_POST["type_name"] != "choice"){
			$type_name = str_replace("_", " ", $_POST["type_name"]);
			echo("\t\t\t<OPTION VALUE = \"". $_POST["type_seq"] ."\">$type_name</OPTION>\n");
		}
   }
	echo("\t\t\t<OPTION VALUE = \"End\">End of Test</OPTION>\n");
	echo("\t\t</SELECT>\n");
	echo("\t</TABLE>\n");
	echo("\t\t<INPUT TYPE=\"hidden\" NAME=\"next_num_blanks\" VALUE=\"1\">\n");
	echo("\t\t<INPUT TYPE=\"hidden\" NAME=\"next_num_choices\" VALUE=\"1\">\n");	
	
}
function display_question_form($question_type, $question_num, $num_choices=1, $num_blanks=1){
	$select_statement = "SELECT type_seq, type_name FROM helpdesk.question_type_def where type_seq = $question_type";
	$search_results = mssql_query($select_statement) or die("Error in Query: $select_statement.");  //select the username 
	$i = 1;
	$_POST = mssql_fetch_array($search_results);
	$function = "display_" . $_POST["type_name"] . "_form";
	call_user_func($function, $question_num, $num_choices, $num_blanks);
}
function display_test_menu($purpose, $the_user){
	require_once("sql_connect.inc");
	$title = "Welcome, " . $the_user->first_name;
	include("html_header_no_fxns.inc");
	echo("\t\t<TABLE ALIGN=\"CENTER\" border=\"0\">\n");
	echo("\t\t\t<TR><TD ALIGN=\"CENTER\" COLSPAN=\"2\">\n");
	echo("\t<H3 ALIGN = \"CENTER\"> Please select an exam.</H3>\n");
	echo("\t\t\t</TD></TR>\n");
	echo("\t\t\t<TR><TD ALIGN=\"CENTER\">\n");
	$tests = "SELECT * FROM test_header_def WHERE active = 1 ORDER BY creation_date";
	$search_results = mssql_query($tests) or die("Error in Query: $tests<BR>");  //select the username 
	$counter = 1;
	while($temp = mssql_fetch_array($search_results)){
		echo("\t<TR><TD SIZE=\"4\">$counter) </TD>\n");
		$counter++;
		echo("\t<TD ALIGN=\"CENTER\">\n");
		echo("\t<FORM NAME=\"form1\" method=\"POST\" action=\"display_test_" . $purpose . ".php\">\n");
		if(!empty($temp["test_name"])) $test_name = $temp["test_name"];
		else $test_name = "Untitled";
		echo("\t\t<INPUT TYPE=\"SUBMIT\" VALUE=\"" . stripslashes($test_name) . "\">\n");
		echo("\t\t</INPUT>\n\t</TD></TR>\n");
		echo("\t\t<INPUT TYPE=\"HIDDEN\" NAME=\"test_seq\" VALUE=\"" . $temp["test_seq"] . "\">");
		echo("\t</FORM>");
	}
	
	echo("\t\t\t</TD></TR>\n");
	echo("\t\t</TABLE>\n");
	echo("\t</BODY>\n</HTML>\n");
}
function display_header_info($test){
	echo("\t<TABLE align = \"CENTER\">\n");
	echo("\t\t<TR>\n\t\t\t<TD COLSPAN=\"2\"><h2 align = \"CENTER\">Test Header</h2></TD>\n\t\t</TR>\n");
	echo("\t\t<TR>\n\t\t\t<TD><B>Test Name:</B></TD>\n\t\t\t<TD>" . stripslashes($test['test_name']) . "</TD>\n\t\t</TR>\n");
	echo("\t\t<TR>\n\t\t\t<TD><B>Creator:</B></TD>\n\t\t\t<TD>" . stripslashes($test['created_by']) . "</TD>\n\t\t</TR>\n");
	echo("\t\t<TR>\n\t\t\t<TD><B>Test Category:</B></TD>\n\t\t\t<TD>" . stripslashes($test['category_name']) . "</TD>\n\t\t</TR>\n");
	echo("\t\t<TR>\n\t\t\t<TD><B>Display After Taking:</B></TD>\n\t\t\t<TD>");
	echo ($test['display_after']) ? "TRUE" : "FALSE"; 
	echo("</TD>\n\t\t</TR>\n");
	echo("\t</TABLE>\n");
	echo("\t<br><br>\n");
}

function display_w_solutions($test_seq, $for_mod){
	require_once("../inc_files/question_fxns.inc");
	//GET test_name AND test_num FROM THE DATABASE BASED ON test_seq
	$test_info = "SELECT * FROM test_header_def WHERE test_seq = $test_seq";
	$test_info = mssql_query($test_info);
	$test_info = mssql_fetch_array($test_info);
	$test_name = $test_info["test_name"];
	
	//START THE HTML CODE DISPLAYING THE TEST NAME & FORM
	echo("<TABLE ALIGN=\"CENTER\" BORDER=\"0\">\n");
   	echo("   <TR>\n");
	echo("\t<TD><H2><U>" . stripslashes($test_name) . "</U></H2></TD>\n");
	echo("   </TR>\n");
	echo("   <TR>\n");
	echo("\t<TD><HR SIZE=\"1\" WIDTH=\"400\"></TD>\n");
	echo("   </TR>\n");
	echo("\t<INPUT TYPE=\"hidden\" NAME=\"test_seq\" VALUE = \"$test_seq\">\n");
	//GET THE TEST FROM THE DATABASE
	$query = "SELECT * FROM question_def, question_type_def " .
			 "WHERE question_def.test_seq = $test_seq AND " . 
			 "question_type_def.type_name != \"choice\" AND " .
			 "question_def.type_seq = question_type_def.type_seq " .
			 //"ORDER BY question_def.question_num" .
			 "ORDER BY question_def.question_num, question_def.question_seq;";
	$all_questions = mssql_query($query);
	//$q_num = 1;
	//DISPLAY ALL QUESTIONS. THE DISPLAY FORMAT WILL BE BASED ON THE type_name
	while(@$question = mssql_fetch_array($all_questions)){
		//$question_seq = $question["question_seq"];
		$question_num = $question["question_num"];
		$type_name = $question["type_name"];
		$function = "display_" . $type_name . "_with_solution";
		echo("   <TR><TD>\n");	
		if($type_name == "subject"){
			echo("\t<h4>" . stripslashes($question["question"]) . "</h4>\n");
		}
		else{
			call_user_func($function, $question, $all_questions, $for_mod, $question_num);
			//$q_num++;
		}
		echo("   </TD></TR>\n");
	}	
	echo("</TABLE>\n");
	//echo("\n</BODY>\n</HTML>");
}
function display_taken_test($take_seq){
	$emp_info = "SELECT * FROM test_taken_dtl, emp_def WHERE " .
				"test_taken_dtl.take_seq = $take_seq AND ".
				"test_taken_dtl.emp_seq = emp_def.emp_seq;";
	$emp_info = mssql_query($emp_info);
	$emp_info = mssql_fetch_array($emp_info);
	$first_name = $emp_info["first_name"];
	$last_name = $emp_info["last_name"];
	$ssn = $emp_info["ssn"];
	$emp_num = $emp_info["emp_num"];
	$test_seq = $emp_info["test_seq"];
	$duration = $emp_info["duration_secs"];

	$test_header = "SELECT * FROM test_header_def, test_category_def " .
				   "WHERE test_header_def.test_seq = $test_seq " .
				   "AND test_category_def.category_seq = test_header_def.category_seq";
	$test_header = mssql_query($test_header);
	$test_header = mssql_fetch_array($test_header);
	$test_name = $test_header["test_name"];
	$test_category = $test_header["category_name"];  
	
	/*
	* All header info has been retreived from the database. Display the header info before 
	* 	fetching the actual questions from the database.
	*/
	
	
	//$title = "$first_name $last_name -> $test_name";
	//include("../inc_files/html_header_no_fxns.inc");
	require_once("../inc_files/variables.inc");
	echo("\t<TABLE WIDTH=\"80%\" BORDER=\"0\" ALIGN=\"CENTER\">\n\t\t");
	echo("<TR>\n\t\t\t");
	echo("<TD width=\"50%\">\n\t\t\t\t");  
	echo("<TABLE BORDER=\"0\" ALIGN=\"RIGHT\">\n\t\t\t\t\t");
	echo("<TR>\n\t\t\t\t\t\t");
	echo("<TD>NAME:</TD>\n\t\t\t\t\t\t");
	echo("<TD><B>$first_name $last_name</B></TD>\n\t\t\t\t\t");
	echo("</TR>\n\t\t\t\t\t");
	echo("<TR>\n\t\t\t\t\t\t");
	echo("<TD>SSN:</TD>\n\t\t\t\t\t\t");
	echo("<TD><B>$ssn</B></TD>\n\t\t\t\t\t");
	echo("</TR>\n\t\t\t\t\t");
	echo("<TR>\n\t\t\t\t\t\t");
	echo("<TD>PAYROLL ID:</TD>\n\t\t\t\t\t\t");
	echo("<TD><B>$emp_num</B></TD>\n\t\t\t\t\t");
	echo("</TR>\n\t\t\t\t\t");
	echo("<TR>\n\t\t\t\t\t\t");
	echo("<TD>DURATION OF TEST:</TD>\n\t\t\t\t\t\t");
	
	$h = intval($duration / 3600);
	$duration -= $h*3600;
	$m = intval($duration / 60);
	$duration -= $m*60;
	$s = intval($duration);
	
	echo("<TD><B>$h hrs : $m min : $s sec</B></TD>\n\t\t\t\t\t");
	echo("</TR>\n\t\t\t\t");
	echo("</TABLE>\n\t\t\t</TD>\n\t\t");
	echo("</TR>\n\t\t");
	echo("<TR>\n\t\t\t");
	echo("<TD COLSPAN=\"3\">&nbsp;</TD>\n\t\t");
	echo("</TR>\n\t\t"); 
	echo("<TR>\n\t\t\t");
	echo("<TD COLSPAN=\"3\"><H1>" . stripslashes($test_name));
	echo("<HR SIZE=\"2\" WIDTH=\"70%\">");
	echo("</H1></TD>\n\t\t");
	echo("</TR>\n\t\t");
	echo("<TR>\n\t\t\t");
	echo("<TD COLSPAN=\"3\"></TD>\n\t\t");
	echo("</TR>\n\t");
	echo("</TABLE>\n");
	include("question_fxns.inc");
	//GET THE TEST FROM THE DATABASE
	$query = "SELECT * FROM question_def, question_type_def " .
			 "WHERE question_def.test_seq = $test_seq AND " . 
			 "question_type_def.type_name != \"choice\" AND " .
			 "question_def.type_seq = question_type_def.type_seq " .
			 "ORDER BY question_def.question_num;";
	$all_questions = mssql_query($query);
	//DISPLAY ALL QUESTIONS. THE DISPLAY FORMAT WILL BE BASED ON THE type_name
	$q_num = 1;
	echo("<TABLE BORDER=\"0\" ALIGN=\"CENTER\" WIDTH=\"95%\">\n");
	while(@$question = mssql_fetch_array($all_questions)){	
		$question_seq = $question["question_seq"];
		$question_num = $question["question_num"];
		$type_name = $question["type_name"];
		$function = "display_" . $type_name . "_graded";
		echo("\t<TR><TD>\n");
		if($type_name == "subject"){
			echo("\t<H4 ALIGN=\"CENTER\">" . stripslashes($question["question"]) . "</H4>\n");
		}
		else{
			//$question["take_seq"] = 
			call_user_func($function, $question, $q_num, $take_seq, $all_questions);
			$q_num ++;
		}	
		echo("\t</TD></TR>\n");
	}	
	echo("</TABLE>\n");	
}

function display_list_of_courses($header, $scr){
	$tests = "SELECT * FROM course_def, test_category_def " .
			"WHERE test_category_def.category_seq = course_def.category_seq ORDER BY course_def.course_number";
	$test_list = mssql_query($tests) or die("Error in $tests<BR>create_course line 39<BR>");
	
	echo("\t<TABLE ALIGN=\"CENTER\" border=\"0\">\n");
	echo("\t\t<TR><TD ALIGN=\"CENTER\">\n");
	echo("\t\t\t<H2><U>$header</U></H2>\n");
	echo("\t\t</TD></TR>\n");
	echo("\t\t<TR><TD ALIGN=\"CENTER\">\n");
	echo("\t\t\t<TABLE ALIGN=\"CENTER\" BORDER=\"5\" CELLPADDING=\"4\">\n");
	echo("\t\t\t\t<TR>\n");
	echo("\t\t\t\t\t<TH>#</TH>\n");
	echo("\t\t\t\t\t<TH>NAME</TH>\n");
	echo("\t\t\t\t\t<TH>CATEGORY</TH>\n");
	echo("\t\t\t\t\t<TH>DESCRIPTION</TH>\n");
	echo("\t\t\t\t\t<TH>PREREQS</TH>\n");
	echo("\t\t\t\t</TR>\n");
	while($a_course = mssql_fetch_array($test_list)){
		$cs = $a_course["course_seq"];
		echo("\t\t\t\t<TR>\n");
		echo("\t\t\t\t\t<TD>\n");
		echo("\t\t\t\t\t\t<A CLASS=\"test_list\" HREF=\"" . $_SERVER['PHP_SELF'] . "?cat=course&scr=$scr&cs=$cs&do=ver\">\n");
		echo("\t\t\t\t\t\t\t" . stripslashes($a_course["course_number"]) . "\n");
		echo("\t\t\t\t\t\t</A>\n");
		echo("\t\t\t\t\t</TD>\n");
		echo("\t\t\t\t\t<TD>\n");
		echo("\t\t\t\t\t\t<A CLASS=\"test_list\" HREF=\"" . $_SERVER['PHP_SELF'] . "?cat=course&scr=$scr&cs=$cs&do=ver\">\n");
		echo("\t\t\t\t\t\t\t" . stripslashes($a_course["course_name"]) . "\n");
		echo("\t\t\t\t\t\t</A>\n");
		echo("\t\t\t\t\t</TD>\n");
		echo("\t\t\t\t\t<TD>\n");
		echo("\t\t\t\t\t\t<A CLASS=\"test_list\" HREF=\"" . $_SERVER['PHP_SELF'] . "?cat=course&scr=$scr&cs=$cs&do=ver\">\n");
		echo("\t\t\t\t\t\t\t" . stripslashes($a_course["category_name"]) . "\n");
		echo("\t\t\t\t\t\t</A>\n");
		echo("\t\t\t\t\t</TD>\n");
		echo("\t\t\t\t\t<TD>\n");
		echo("\t\t\t\t\t\t<A CLASS=\"test_list\" HREF=\"" . $_SERVER['PHP_SELF'] . "?cat=course&scr=$scr&cs=$cs&do=ver\">\n");
		echo("\t\t\t\t\t\t\t" . stripslashes($a_course["description"]) . "\n");
		echo("\t\t\t\t\t\t</A>\n");
		echo("\t\t\t\t\t</TD>\n");
		echo("\t\t\t\t\t<TD>\n");
		//Assign all prerequisits to a string
		$count = 0;
		$prereq = "";
		for($i = 1; $i < 6; $i++){
			$temp = "prereq_seq_$i";
			if(!empty($a_course[$temp])){
				$select = "SELECT course_number FROM course_def WHERE course_seq = " . $a_course[$temp];
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
		if(empty($prereq)) $prereq = "&nbsp";
		echo("\t\t\t\t\t\t<A CLASS=\"test_list\" HREF=\"" . $_SERVER['PHP_SELF'] . "?cat=course&scr=$scr&cs=$cs&do=ver\">\n");
		echo("\t\t\t\t\t\t\t$prereq\n");
		echo("\t\t\t\t\t\t</A>\n");
		echo("\t\t\t\t\t</TD>\n");
		echo("\t\t\t\t</TR>\n");
	}
	echo("\t\t\t</TABLE>\n");
	echo("\t\t</TD></TR>\n");
	echo("\t</TABLE>\n");
}
?>