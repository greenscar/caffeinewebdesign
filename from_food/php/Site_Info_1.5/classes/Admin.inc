<?php
require_once("DB_Mgr.inc");
require_once("Employee.inc");
/*
require_once("Phone_System.inc");
require_once("Region.inc");
require_once("District.inc");
require_once("Employee.inc");
*/
require_once("Store.inc");
class Admin
{
    // Array of Regions
    var $regions;
    // Array of Districts
    var $districts;
    // DB Mgr
    var $dbmgr;
    // Array of Sites
    var $sites;
    // Phone System Object
    var $phone_system;
    
    // Employee List
    var $emps;
    
    var $settings;
    function Admin()
    {
        $this->settings = new Settings();
    }
    
    /********************************************************************
     * Store List Functions
     ********************************************************************/
    function db_load_site_list($order_by="loc_num")
    {
        $this->sites = array();
        $settings = new Settings();
        $string = "mssql_connect(" . $this->settings->server["sites"] . ", "
                . $this->settings->id . ", " . $this->settings->pwd . ")";
                //return $string;
        $dbcnx = mssql_connect("DW, 2433", "sa", "sa") or die("Error in connect");
        if(!(mssql_connect($this->settings->server["sites"], $this->settings->id, $this->settings->pwd)))
        {
            return("Error in connecting to the DB");
        }
        return(mssql_select_db($this->settings->db["sites"]));
        $select = "SELECT * FROM SI_SITE WHERE active = 1 ORDER BY $order_by";
        $rs = mssql_query($select);
        return($rs);
        //return mssql_num_rows($rs);
        while($row = mssql_fetch_row($rs))
        {
            $s = new Store($row[0], stripslashes($row[1]));
            $s->address->description = stripslashes($row[2]);
            $s->address->street = $row[3];
            $s->address->city = stripslashes($row[4]);
            $s->address->state = $row[5];
            $s->address->zip = $row[6];
            array_push($this->sites, $s);
        }
        return sizeof($this->sites);
    }
    function get_a_site_from_list($array_loc){
        if($array_loc < sizeof($this->sites))
            return($this->sites[$array_loc]);
        else
            return null;
    }
    function site_list_size()
    {
        return sizeof($this->sites);
    }
    
    function db_load_detailed_site_list($order_by="loc_num")
    {
        $this->sites = array();
        
        $d_dub_link = mssql_connect($this->settings->server["sites"], $this->settings->id, $this->settings->pwd);
        mssql_select_db($this->settings->db["sites"], $d_dub_link);
                
        $ps_link = mssql_connect($this->settings->server["ps"], $this->settings->id, $this->settings->pwd);
        mssql_select_db($this->settings->db["ps"],$ps_link);
                
        $select = "SELECT loc_num, loc_name_1 FROM SI_SITE WHERE active = 1 ORDER BY $order_by";
        $rs = mssql_query($select, $d_dub_link);
        
        $toReturn = "";
        $i = 1;
        while($row = mssql_fetch_row($rs))
        //while($row = $this->dbmgr->get_a_row())
        {
            //$toReturn .= $i++ . "<br>";
            $s = new Store($row[0], stripslashes($row[1]));
            $s->db_load_all_info($d_dub_link, $ps_link);
            array_push($this->sites, $s);
        }
        //return $toReturn;
        //return sizeof($this->sites);
    }
    /********************************************************************
     * END Store List Functions
     ********************************************************************/
    
    
    function db_load_store($num)
    {
        $d_dub_link = mssql_connect($this->settings->server["sites"], $this->settings->id, $this->settings->pwd);
        mssql_select_db($this->settings->db["sites"], $d_dub_link);
                
        $ps_link = mssql_connect($this->settings->server["ps"], $this->settings->id, $this->settings->pwd);
        mssql_select_db($this->settings->db["ps"],$ps_link);
                
        $store = new Store($num, "");
        $store->db_load_all_info($d_dub_link, $ps_link);
        return $store;
    }
    /********************************************************************
     * Emp ID Lookup Functions
     ********************************************************************/
    
    function db_search_via_first_and_last_name($ln, $fn)
    {
        $this->emps = array();
        $this->dbmgr =  new DB_Mgr("ps");
        $select = "SELECT LAST_NAME, FIRST_NAME, EMPLID FROM PS_EMPLOYEES "
                . "WHERE FIRST_NAME = '" . $fn
                . "' AND LAST_NAME = '" . $ln . "';";
        $this->dbmgr->query($select);
        while($row = $this->dbmgr->get_a_row())
        {
            $emp = new Employee($row[2], $row[1], $row[0]);
            array_push($this->emps, $emp);
        }
    }
    
    function get_an_emp_from_list($array_loc){
        return($this->emps[$array_loc]);    
    }
    function emp_list_size()
    {
        return sizeof($this->emps);
    }
}
?>