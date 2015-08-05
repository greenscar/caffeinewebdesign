<?php
class A_Person{
	var $emp_seq;
	var $emp_num;
	var $ssn;
	var $first_name;
	var $last_name;
	var $sec_lvl;
	var $email;
	var $sec_lvl_seq;
	function A_Person($emp_num="", $first_name="", $last_name="", $emailAddress="", $sec_lvl=""){
	//require_once("http://127.0.0.1/Testing_System_2.0/inc_files/display_fxns.inc");
		$query = "SELECT * FROM emp_def WHERE emp_num = $emp_num";
		$result = mssql_query($query) or die("Error in $query");
		$line = mssql_fetch_array($result);
		//view_array($line);
		$this->emp_seq = $line["emp_seq"];
   		$this->emp_num = $line["emp_num"];
		$this->ssn = $line["ssn"];
		$this->first_name = $line["first_name"];
		$this->last_name = $line["last_name"];
		$this->email = $line["email"];    	
		$this->sec_lvl_seq = $line["sec_lvl_seq"];
		$sec_lvl_query = "SELECT sec_lvl FROM sec_lvl_def WHERE sec_lvl_seq = " . $this->sec_lvl_seq;
		$sec_query = mssql_query($sec_lvl_query) or die($sec_lvl_query);
		$row = mssql_fetch_array($sec_query);
		$this->sec_lvl = $row["sec_lvl"];
		$this->name = $this->first_name . " " . $this->last_name;
	}
	function get_sec_lvl(){
		return $this->sec_lvl;
	}
	function get_sec_lvl_seq(){
		return $this->sec_lvl_seq;
	}
	
	function get_emp_seq(){
		return $this->emp_seq;
	}
	function displayInfo(){
		echo("emp_seq = $this->emp_seq<br>");
		echo("emp_num = $this->emp_num<br>");
		echo("ssn = $this->ssn<br>");
		echo("name = $this->first_name $this->last_name<br>");
		echo("email = $this->email<br>");
		echo("sec_lvl = $this->sec_lvl<br>");
		echo("sec_lvl_seq= $this->sec_lvl_seq<br>");
	}
}


?>