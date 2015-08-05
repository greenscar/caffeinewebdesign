<?php
require_once("Division.inc");
require_once("DB_Mgr.inc");
class Region extends Division
{
    
    // Constructor requires a region.
    // A District cannot exist outside a region.
    function Region($nu = "", $na = "", $mg = "")
    {
        Division::Division($nu, $na, $mg);
    }
    
    /*******************************************************************
    * DB Functions
    ********************************************************************/
    function db_region_num_exists()
    {
        $dbmgr = new DB_Mgr();
        $stmt = "SELECT COUNT(*) FROM SI_REGION WHERE region_num = " . $this->number;
        $dbmgr->query($stmt);
        $row = $dbmgr->get_a_row();
        $num = $row[0];
        return($num);
    }    
    function db_insert_region()
    {
        $dbmgr = new DB_Mgr();
        $stmt = "INSERT INTO SI_REGION VALUES("
              . $this->number . ", '"
              . $this->name . "', "
              . $this->mgr_emp_id . ")";
        return $dbmgr->query($stmt);
    }
    function db_load_region_info()
    {
        $dbmgr = new DB_Mgr();
        $stmt = "SELECT name, rm_emp_id FROM SI_REGION "
              . "WHERE region_num = " . $this->number;
        $dbmgr->query($stmt);
        if($dbmgr->get_num_rows() > 0)
        {
            $row = $dbmgr->get_a_row();
            $this->name = $row["name"];
            $this->rm_emp_id = $row["rm_emp_id"];
        }
    }
    
    
    
    function update_region($num, $rm_emp_id, $name = "")
    {
        $dbmgr = new DB_Mgr();
        $stmt = "UPDATE SI_REGION SET "
              . "name = $name, rm_emp_id = $rm_emp_id "
              . "WHERE region_num = $num";
        return $dbmgr->query($stmt);
    }
    /*******************************************************************
    * END DB Functions
    ********************************************************************/
}
?>