<?php
	require_once("./inc_files/check_session.inc");
	$title = "Welcome, " . $the_user->first_name;
	//include("./inc_files/html_header.inc");
	if($the_user->get_sec_lvl() == "teacher"){
		$title = "Welcome, " . $the_user->first_name;
		include("./inc_files/html_header.inc");
		display_menu();
	}
	else{
		Header("Location: ./login.php");
	}
	function display_menu(){
		
	}
?>