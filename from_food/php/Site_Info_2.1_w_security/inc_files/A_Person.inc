<?php
class A_Person{
	var $login;
	var $pwd;
	function A_Person($login="", $pwd=""){
	//require_once("http://127.0.0.1/Testing_System_2.0/inc_files/display_fxns.inc");
		$query = "SELECT * FROM ml_admins WHERE login = $login";
		$result = mssql_query($query) or die("Error in $query");
		$line = mssql_fetch_array($result);
		//view_array($line);
		$this->login = $line["login"];
   		$this->pwd = $line["pwd"];
	}
}
?>