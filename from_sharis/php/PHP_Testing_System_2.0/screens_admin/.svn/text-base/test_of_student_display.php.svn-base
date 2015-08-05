<?php
/*
* REQUIREMENT: emp_seq must be defined.
* This file will be called by display_test_taken.php
*/

$user_type = "admin";
require_once("../inc_files/sql_connect.inc");
require_once("../inc_files/check_session.inc");
require_once("../inc_files/some_useful_functions.inc");
require_once("../inc_files/display_fxns.inc");
session_start();

if(isset($_POST["take_seq"])){
	display_taken_test($_POST["take_seq"]);
}

else{
	//emp_seq is defined.
	//Display all exams this student has taken allowing the viewer to check it.
	$select = "SELECT * FROM test_taken_dtl, test_header_def, test_category_def " .
			  "WHERE test_taken_dtl.emp_seq = " . $_POST["emp_seq"] . " AND " .
			  "test_taken_dtl.test_seq = test_header_def.test_seq AND " .
			  "test_header_def.category_seq = test_category_def.category_seq " .
			  "ORDER BY test_taken_dtl.date_taken";
	//	echo($select . "<BR>");
	$select = mssql_query($select);
	//$title = "Click the button to the left of the test you wish to view.";
	//include("../inc_files/html_header_no_fxns.inc");
	echo("<H2 ALIGN=\"CENTER\">" . $_POST["emp_name"]. "'s tests</H2>\n");
	echo("<TABLE BORDER=\"1\" ALIGN=\"CENTER\">\n\t");
	echo("<TR>\n\t");
	echo("<TH><B>Test Name</B></TH>\n\t");
	echo("<TH><B>Test Category</B></TH>\n\t");
	echo("<TH><B>Date Taken</B></TH>\n\t");
	echo("</TR>\n\t");
	for($i = 0; $a_test = mssql_fetch_array($select); $i++){
		echo("<FORM NAME=\"STUDENTFORM\" method=\"POST\" action=\"" . $_SERVER['PHP_SELF'] . "?cat=teacher&scr=disptake\">\n\t");
		echo("<INPUT TYPE=\"HIDDEN\" NAME=\"take_seq\" VALUE=\"" . $a_test["take_seq"] . "\">");
		echo("<TR>\n\t\t");
		echo("<TD ALIGN=\"CENTER\">\n\t\t\t");
		echo("<INPUT TYPE=\"submit\" NAME=\"Submit\" VALUE=\"" . stripslashes($a_test["test_name"]) . "\">\n\t\t");
		echo("</TD>\n\t");
		echo("<TD>\n\t\t\t");
		echo($a_test["category_name"] . "\n\t\t");
		echo("</TD>\n\t");
		echo("<TD>\n\t\t\t");
		echo($a_test["date_taken"] . "\n\t\t");
		echo("</TD>\n\t");
		echo("</TR>\n\t");
		echo("</FORM>\n");	
	}
	echo("</TABLE>\n");	
	echo("</BODY>\n");
}

?>