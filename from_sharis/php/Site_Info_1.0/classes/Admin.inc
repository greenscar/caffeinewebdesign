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
    
    function Admin()
    {
    }
    /********************************************************************
     * Region List Functions
     ********************************************************************/
    function db_load_region_list()
    {
        $this->regions = array();
        $dbmgr = new DB_Mgr("sites");
        $select = "SELECT * FROM SI_REGION";
        $dbmgr->query($select);
        while($row = $dbmgr->get_a_row())
        {
            $tmp = new Region();
            $tmp->number = $row[0];
            $tmp->name = $row[1];
            $tmp->mgr_emp_id = $row[2];
            array_push($this->regions, $tmp);
        }
    }
    function get_a_region_from_list($array_loc){
        return($this->regions[$array_loc]);    
    }
    function region_list_size()
    {
        return sizeof($this->regions);
    }
    /********************************************************************
     * END Region List Functions
     ********************************************************************/
    
    /********************************************************************
     * District List Functions
     ********************************************************************/
    function db_load_district_list()
    {
        $this->districts = array();
        $dbmgr = new DB_Mgr("sites");
        $select = "SELECT * FROM SI_DISTRICT ORDER BY region_num, district_num";
        $dbmgr->query($select);
        while($row = $dbmgr->get_a_row())
        {
            $tmp = new District($row[0], $row[2], $row[3], $row[1]);
            array_push($this->districts, $tmp);
        }
    }
    function get_a_district_from_list($array_loc){
        return($this->districts[$array_loc]);    
    }
    function district_list_size()
    {
        return sizeof($this->districts);
    }
    /********************************************************************
     * END District List Functions
     ********************************************************************/
    
    /********************************************************************
     * Store List Functions
     ********************************************************************/
    function db_load_site_list($order_by)
    {
        $this->sites = array();
        $dbmgr = new DB_Mgr("sites");
        $select = "SELECT * FROM SI_SITE WHERE active = 1 ORDER BY $order_by";
        $dbmgr->query($select);
        while($row = $dbmgr->get_a_row())
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
        return($this->sites[$array_loc]);    
    }
    function site_list_size()
    {
        return sizeof($this->sites);
    }
    
    /********************************************************************
     * END Store List Functions
     ********************************************************************/
    
    
    /********************************************************************
     * Emp ID Lookup Functions
     ********************************************************************/
    
    function db_search_via_first_and_last_name($ln, $fn)
    {
        $this->emps = array();
        $dbmgr = new DB_Mgr("ps");
        $select = "SELECT LAST_NAME, FIRST_NAME, EMPLID FROM PS_EMPLOYEES "
                . "WHERE FIRST_NAME = '" . $fn
                . "' AND LAST_NAME = '" . $ln . "';";
        $dbmgr->query($select);
        while($row = $dbmgr->get_a_row())
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