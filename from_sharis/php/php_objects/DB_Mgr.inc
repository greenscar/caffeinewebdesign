<?php
require_once("http://www.e-sharis.net/includes/php_files/Rics_Settings.inc");
class DB_Mgr
{
	var $settings;
	var $rs;
	var $conn;
	
	function DB_Mgr()
	{
		$this->settings = new Rics_Settings();
		$this->connect();
	}
	
	function connect()
	{
		$this->conn = odbc_connect($this->settings->db, $this->settings->id, $this->settings->pwd);
	}
	
	function disconnect()
	{
		odbc_close($this->conn);
	}
	
	function get_conn()
	{
		return $this->conn;
	}
	
	function query($query)
	{
		$this->rs = odbc_exec($this->conn, $query);
	}
	
	function fetch_row()
	{
		return(odbc_fetch_row($this->rs));
	}
	
	function get_field($num)
	{
		return odbc_result($this->rs, $num);
	}
	
	function get_num_rows()
	{
		return odbc_num_rows($this->rs);
	}
	
	function get_num_fields()
	{
		return odbc_num_fields($this->rs);
	}
	function get_field_name($field_num)
	{
		return odbc_field_name($this->rs, $field_num);
	}
}
?>