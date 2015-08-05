<?php

$user_type = "admin";
require_once("../inc_files/check_session.inc");
require_once("../inc_files/display_fxns.inc");
require_once("../inc_files/some_useful_functions.inc");
if(isset($_GET["cs"])){
	if($_GET["ver"] == "true"){
		$drop = "DROP FROM course_def WHERE course_seq = " . $_GET["cs"];
		echo($drop);
	}
	else{
		echo("<H2>Are you sure you want to delete this course?</H2>");
		
	}
	
}
else{
	display_list_of_courses("Please select a course to delete.", "delcou");
}
?>