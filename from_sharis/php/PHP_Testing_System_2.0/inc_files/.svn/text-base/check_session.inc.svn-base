<?php
	/*
	* This file will check the current user for an existing session.
	* If a session does not exist, the user will be sent to the login page.
	* If a session does exist, a Person instance will be created and returned
	* to the page as $the_user
	*/
	require_once("A_Person.php");
	$the_user = 0;
	session_start();
	if(session_is_registered("the_user")){
		$the_user = $_SESSION['the_user'];			
		if($the_user->get_sec_lvl() == $user_type){		
			$title = "Welcome, " . $the_user->first_name;
			require_once("sql_connect.inc");
		}
	}
	else{
		Header("Location: ../index.php");
	}
?>