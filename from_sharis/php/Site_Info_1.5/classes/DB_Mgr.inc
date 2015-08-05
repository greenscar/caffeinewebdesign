<?php
require_once("Settings.inc");
class DB_Mgr
{
    var $settings;
    var $rs;
    var $conn;
    
    function DB_Mgr($db_name)
    {
        $this->settings = new Settings();
        $this->connect($db_name);
    }
    
    function connect($db_name)
    {
        //echo("mssql_connect($this->server, $this->id, $this->pwd);<br>");
        mssql_connect($this->settings->server[$db_name], $this->settings->id, $this->settings->pwd);
        //returnecho($($this->conn);
        $conn = mssql_select_db($this->settings->db[$db_name]);
        //return $this->settings->db["ps"];
    }
    function disconnect()
    {
        mssql_close($this->conn);
    }
    function query($string)
    {
        //echo($string . "<BR>");
        return($this->rs = mssql_query($string));
        
    }
    
    function execute($string)
    {
        //return(true);    
        return mssql_query($string);
    }
    function get_a_row()
    {
        return mssql_fetch_row($this->rs);
    }
    
    function get_num_rows()
    {
        if (!isset($this->rs))
            return 0;
        else
            return mssql_num_rows($this->rs);
    }
}
?>