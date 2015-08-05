<?php
/*
 * DB_Mgr is responsible for all database connections on this linux box.
 * NOTE: You must use the free_result() call to do your own garbage collection.
 * For some reason, freeTDS & unixODBC do not do correct garbage collection, so
 * you will get meaningless errors if you don't clean up after you are done with
 * a result set.
 */
require_once("Settings.inc");
class DB_Mgr
{
	var $settings;
	var $rs;
	var $conn;
	var $row;
	function DB_Mgr($db)
	{
		$this->settings = new Settings();
		$this->connect($db);
	}
	
	function connect($db)
	{
		$this->conn = odbc_connect($this->settings->db[$db],
											$this->settings->id[$db],
											$this->settings->pwd[$db]);
	}
	
	function disconnect()
	{
		odbc_close($this->conn);
	}
	
	function free_result()
	{
		if($this->rs != null)
			odbc_free_result($this->rs);
	}
	function get_conn()
	{
		return $this->conn;
	}
	
	function query($query)
	{
		$this->rs = odbc_exec($this->conn, $query);
		return $this->rs;
	}
	function exec($query)
	{
		if(!(odbc_exec($this->conn, $query)))
			return(odbc_error($this->conn));
		else return(true);
	}
	function fetch_row()
	{
		if(odbc_fetch_into($this->rs, $this->row))
			return $this->row;
		else
			return 0;
		//return(odbc_fetch_row($this->rs));
	}
	
	function fetch_into(&$result)
	{
		return odbc_fetch_into($this->rs, $result);
	}
	
	function fetch_array()
	{
		return(odbc_fetch_array($this->rs));
	}
	
	function get_field($num)
	{
		return odbc_result($this->rs, $num);
	}
	function get_error()
	{
		return odbc_error($this->conn);
	}
	function get_num_rows()
	{
		/*
		 * For some reason, odbc_num_rows($this->rs) returns 0,
       *    unless we do this while loop before calling num_rows.
       * Because we are going to have to do this loop anyway,
       * 	we have no reason to call odbc_num_rows().
		 */
		$to_ret = 0;
      while(odbc_fetch_row($this->rs)) 
         $to_ret++; 
      return $to_ret;
		//return odbc_num_rows($this->rs);
	}
	function get_num_fields()
	{
		//$this->get_num_rows();
		return odbc_num_fields($this->rs);
	}
	function get_field_name($field_num)
	{
		return odbc_field_name($this->rs, $field_num);
	}
	function get_data_type($field_num)
	{
		return odbc_field_type($this->rs, $field_num);
	}
}
?>