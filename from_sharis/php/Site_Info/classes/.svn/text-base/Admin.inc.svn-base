<?php
require_once("DB_Mgr.inc");
require_once("Phone_System.inc");
require_once("Region.inc");
require_once("District.inc");
require_once("Employee.inc");
class Admin
{
    // Array of Regions
    var $regions;
    // Array of Districts
    var $districts;
    // DB Mgr
    var $dbmgr;
    
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