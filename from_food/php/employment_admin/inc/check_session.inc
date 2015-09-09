<?php
	require_once("A_Person.inc");
	$mailing_list_admin = 0;
	if(!(session_is_registered("web_employment_admin")))
	{
		Header("Location: ./login.php");
	}
?>