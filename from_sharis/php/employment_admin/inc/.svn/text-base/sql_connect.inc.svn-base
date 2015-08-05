<?php

require_once("vars.inc");
/*
 * Connect to the Database.
 */
$dbcnx = MSSQL_CONNECT($server,$id,$pwd) or DIE("<h1>DATABASE FAILED TO RESPOND.</h1>"); 
if(!mssql_select_db("$db")){
	echo("ERROR - Couldn't connect to database. Please contact the Help Desk.");
	exit();
}
/*
 * END Connect to the Database.
 */

?>