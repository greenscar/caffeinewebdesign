<?php
require("../inc_files/sql_connect.inc");
create_course_def();
create_class_dtl();
create_enrollment_dtl();
function create_course_def(){
	$dropIt = "DROP TABLE course_def";
	@mssql_query($dropIt);	
	
 	$createCourse = "CREATE TABLE course_def ( " .
				"course_seq INTEGER IDENTITY (1,1), " .
 				"course_name VARCHAR (50), " .
				"course_number INTEGER, " .
				"category_seq INTEGER, ";
	for($i = 1; $i < 6; $i ++){
		$createCourse .= "prereq_seq_" . $i . " INTEGER NULL, ";
	}	
	for($i=1; $i<16;$i++){
		$createCourse .= "test_seq_" . $i . " INTEGER NULL, ";
	}
 	$createCourse .= "description VARCHAR (100))";
	//echo($createCourse . "<BR>");
 	if(!mssql_query($createCourse)) echo("<P>Error creating course_def</P>");
	else echo("course_def created<br>");
}

function create_class_dtl(){
	$dropIt = "DROP TABLE class_dtl";
	@mssql_query($dropIt);
 	$createClass = "CREATE TABLE class_dtl ( " .
				"class_seq INTEGER IDENTITY (1,1), " .
 				"course_seq INTEGER, " .
 				"teacher_seq INTEGER, " .
				"start_date DATETIME)";
	//echo($createClass . "<BR>");
 	if(!mssql_query($createClass)) echo("<P>Error creating class_dtl</P>");
	else echo("class_dtl created<br>");
}
function create_enrollment_dtl(){
	$dropIt = "DROP TABLE enrollment_dtl";
	@mssql_query($dropIt);
	$createEnroll = "CREATE TABLE enrollment_dtl ( " .
				"enrollment_seq INTEGER IDENTITY (1,1), " .
				"class_seq INTEGER, " .
				"student_seq INTEGER, " .
				"active bit DEFAULT(1))";
	//echo($createEnroll . "<BR>");
 	if(!mssql_query($createEnroll)) echo("<P>Error creating enrollment_dtl</P>");
	else echo("enrollment_dtl created<br>");
}


?>