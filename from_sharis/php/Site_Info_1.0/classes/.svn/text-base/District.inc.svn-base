<?php
require_once("DB_Mgr.inc");
require_once("Region.inc");
require_once("Division.inc");
class District extends Division
{
    // The region object this district belongs to.
    var $region;
    
    // Constructor requires a region.
    // A District cannot exist outside a region.
    function District($nu = "", $na = "", $mg = "", $rn = "")
    {
        Division::Division($nu, $na, $mg);
        $this->region = new Region($rn);
    }
    
    /*******************************************************************
    * DB Functions
    ********************************************************************/
    function db_district_num_exists()
    {
        $dbmgr = new DB_Mgr();
        $stmt = "SELECT COUNT(*) FROM SI_DISTRICT WHERE "
              . "district_num = " . $this->number
              . "AND region_num = " . $this->region->number;
        $dbmgr->query($stmt);
        $row = $dbmgr->get_a_row();
        $num = $row[0];
        return($num);
    }    
    function db_insert_district()
    {
        $dbmgr = new DB_Mgr();
        $stmt = "INSERT INTO SI_DISTRICT VALUES("
              . $this->number . ", "
              . $this->region->number . ", '"
              . $this->name . "', "
              . $this->mgr_emp_id . ")";
        return $dbmgr->query($stmt);
    }
    
    
    
    function insert_district($num, $dm_emp_id, $name = "")
    {
        $dbmgr = new DB_Mgr();
        $stmt = "INSERT INTO SI_DISTRICT VALUES($num, $name, $dm_emp_id)";
        return $dbmgr->query($stmt);
    }
    function update_district($num, $dm_emp_id, $name = "")
    {
        $dbmgr = new DB_Mgr();
        $stmt = "UPDATE SI_DISTRICT SET "
              . "name = $name, dm_emp_id = $dm_emp_id "
              . "WHERE district_num = $num";
        return $dbmgr->query($stmt);
    }
    function load_district_info($num)
    {
        $dbmgr = new DB_Mgr();
        $stmt = "SELECT name, dm_emp_id FROM SI_DISTRICT "
              . "WHERE district_num = $num";
        $dbmgr->query($stmt);
        if($dbmgr->get_num_rows() > 0)
        {
            $row = $dbmgr->get_a_row();
            $this->num = $row["number"];
            $this->name = $row["name"];
            $this->dm_emp_id = $row["dm_emp_id"];
        }
    }
    
    /*******************************************************************
    * END DB Functions
    ********************************************************************/
}
?>