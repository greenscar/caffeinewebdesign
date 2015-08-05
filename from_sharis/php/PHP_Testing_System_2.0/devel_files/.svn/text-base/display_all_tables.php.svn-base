<?php
require_once("../inc_files/sql_connect.inc");
//require("./inc_files/Person.php");
//require_once("./inc_files/check_session.inc");
$title = "Your tables";
require_once("../inc_files/html_header.inc");
echo("<h1 ALIGN=CENTER>$database</h1>");
$tables = array("course_def", "class_dtl", "enrollment_dtl", "sec_lvl_def", "emp_def", "test_header_def", "test_category_def", "question_type_def", "question_def", "question_solution_dtl", "test_taken_dtl", "student_answer_dtl");
//$tables = array("question_type_def", "question_def","question_solution_dtl");
for($i = 0; $i < sizeof($tables); $i++){
	echo("<h1 align=center>" . $tables[$i] . "</H1>");
	$temp = mssql_query("SELECT * FROM " . $tables[$i]);
	$fields = mssql_num_fields ($temp);
	echo("<TABLE border=1 align=center>");
	for ( $f = 0 ; $f < $fields ; $f++ ){
		$name = mssql_fetch_field($temp, $f);
		echo "<th>".$name->name."</th>";
	}
	
	while($row = mssql_fetch_row($temp)){
		echo("<tr valign=\"top\">");
		for($x=0; $x < sizeof($row); $x++){
			echo("<TD>" . stripslashes($row[$x]) . "</TD>");
		}
		echo("</tr>");
	}
	echo("</TABLE>");
}
echo("<P ALIGN=CENTER><INPUT TYPE=\"BUTTON\" VALUE=\"create_tables\" onClick=\"window.location='./create_tables.php'\"><p>\n");
?>
</BODY>
</HTML>