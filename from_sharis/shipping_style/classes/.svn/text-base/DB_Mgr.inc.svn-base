<?php
require_once("Settings.inc");
class DB_Mgr
{
	var $settings;
	var $rs;
	var $conn;
	
	function DB_Mgr()
	{
		$this->settings = new Settings();
		$this->connect();
	}
	
	function connect()
	{
		$cfg_dsn = "DRIVER=Microsoft Access Driver (*.mdb);
DBQ=C:/Inetpub/wwwroot/shipping/Supply.mdb;
UserCommitSync=Yes;
Threads=3;
SafeTransactions=0;
PageTimeout=5;
MaxScanRows=8;
MaxBufferSize=2048;
DriverId=281;
DefaultDir=C:/ProgramFiles/CommonFiles/ODBC/DataSources";

$cfg_dsn_login = "jsandlin";
$cfg_dsn_mdp = "7733";

//$this->conn = odbc_connect($cfg_dsn,$cfg_dsn_login,$cfg_dsn_mdp);
	$this->conn = odbc_connect("Shipping", "jsandlin", "");
	//$this->conn = odbc_connect($this->settings->db, $this->settings->id, $this->settings->pwd);
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