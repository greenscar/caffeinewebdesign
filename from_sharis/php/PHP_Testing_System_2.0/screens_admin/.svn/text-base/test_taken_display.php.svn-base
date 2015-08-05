<?php
$user_type = "admin";
require_once("../inc_files/sql_connect.inc");
require_once("../inc_files/check_session.inc");
require_once("../inc_files/some_useful_functions.inc");
session_start();
	
/*
* This screen will be the menu for a user to view a single taken test with the student's 
* 	answers on it.
* Allow them to view a list of Students and the list 
* 
*/
//$title = "Click the button to the left of the person whose tests you want to view";
//require("../inc_files/html_header_no_fxns.inc");
display_all_students_menu();
//require("../inc_files/html_footer.inc");
function display_all_students_menu(){
	//Fetch all students from the table and assign them to an array
	$students = "SELECT * FROM emp_def, sec_lvl_def " .
	            "WHERE emp_def.sec_lvl_seq = sec_lvl_def.sec_lvl_seq " .
				"AND sec_lvl_def.sec_lvl = \"student\" " .
				"ORDER BY last_name;";
	$students = mssql_query($students);
	for($i = 0; $a_student = mssql_fetch_array($students); $i++){
		$student_array[$i] = $a_student;
	}
	//Fetch all students who have taken a test and assign them to an array
	$took_test = "SELECT * FROM emp_def, sec_lvl_def, test_taken_dtl " .
	            "WHERE emp_def.sec_lvl_seq = sec_lvl_def.sec_lvl_seq " .
				"AND test_taken_dtl.emp_seq = emp_def.emp_seq " .
				"AND sec_lvl_def.sec_lvl = \"student\" " . 
				"ORDER BY last_name;";
	$took_test = mssql_query($took_test);
	for($i = 0; $a_student = mssql_fetch_array($took_test); $i++){
		$students_who_took_test[$i] = $a_student;
	}
	//view_array($students_who_took_test);
	/*
	* Display all students making only those who have taken a test a link.
	*/
	echo("<H1 ALIGN=\"CENTER\">Select a student</H1>\n");
	echo("<TABLE BORDER=\"1\" ALIGN=\"CENTER\" WIDTH=500>\n\t");
	//echo("sizeof(student_array) = " . sizeof($student_array) . "<BR>");
	for($i = 0; $i < sizeof($student_array); $i++){
		$has_taken_a_test = false;
		$student_name = $student_array[$i]["first_name"] . " " . $student_array[$i]["last_name"];
		
		echo("<FORM NAME=\"STUDENTFORM\" method=\"POST\" action=\"" . $_SERVER['PHP_SELF'] . "?cat=teacher&scr=disptake\">\n\t");
		echo("<TR>\n\t\t<TD WIDTH=300>\n\t\t\t");
		//echo("sizeof(students_who_took_test) = " . sizeof($students_who_took_test) . "<BR>");
		for($j=0; $j < sizeof(@$students_who_took_test); $j++){
			//echo($student_array[$i]["emp_seq"] . "==" . @$students_who_took_test[$j]["emp_seq"] . "<BR>");
			if($student_array[$i]["emp_seq"] == @$students_who_took_test[$j]["emp_seq"]){
				$has_taken_a_test = true;
			}
		}
		if($has_taken_a_test){
			echo("<INPUT TYPE=\"submit\" NAME=\"Submit\" VALUE=\"View a list of exams taken by\">\n\t\t");
			echo("</TD>\n\t\t");
			echo("<TD WIDTH=200>\n\t\t\t");
			echo($student_name . "\n\t\t");
			echo("<INPUT TYPE=\"HIDDEN\" NAME=\"emp_seq\" VALUE=\"" . $student_array[$i]["emp_seq"] . "\">\n\t\t");
			echo("<INPUT TYPE=\"HIDDEN\" NAME=\"emp_name\" VALUE=\"$student_name\">");
		}
		else{
			echo($student_name . "\n\t\t");
			echo("</TD>\n\t\t");
			echo("<TD>\n\t\t\t");
			echo("<B>NO TESTS</B>\n\t\t");
		}
		echo("</TD>\n\t</TR>\n\t");
		echo("</FORM>\n");		
	}
	echo("</TABLE>\n");	
	
}
?>