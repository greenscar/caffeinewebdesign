<?php
 	/*
  	* This header file connects us to the database which will
  	*  be used.
  	*/
	require("variables.inc");
    $dbcnx = MSSQL_CONNECT($host_name,$user_name,$password) or DIE("<h1>DATABASE FAILED TO RESPOND.</h1>"); 
    if(!mssql_select_db("$database")){
       	echo( "<P>Unable to connect to the database server at this time.</P>");
       	exit();
    }
?>