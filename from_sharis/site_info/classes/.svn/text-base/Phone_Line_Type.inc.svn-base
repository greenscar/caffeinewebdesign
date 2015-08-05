<?php
require_once("../../../php_objects/DB_Mgr.inc");
class Phone_Line_Type
{
    // The DB unique ID
    var $id;
    // The name of this type.
    var $name;
    // The description of this type.
    var $description;
    
    function Phone_Line_Type($id = "")
    {
        $this->id = $id;
    }
    function generate_id()
    {
        $this->id = get_first_n_cons($this->name, 4);
        $num_times = $this->db_num_times_id_exists();
        if(@$num_times == 0)
        {
            return($this->id);
        }
        else
        {
            if($this->db_type_name_exists())
            {
                return(false);
            }
            else
            {
                $this->id = $this->id . $num_times;
                return($this->id);
            }        
        }
    }
    /*******************************************************************
    * DB Functions
    ********************************************************************/
    function db_insert_phone_type($dbmgr_web)
    {
        $stmt = "INSERT INTO SI_PHONE_TYPE VALUES ('"
              . $this->id . "', '"
              . $this->name . "', \""
              . $this->description . "\");";
        $dbmgr_web->exec($stmt);
    }
    
    // Query the DB for the number of occurances an ID starts with this object's ID.
    function db_num_times_id_exists($dbmgr_web)
    {
        $toReturn = false;
        $stmt = "SELECT count(*) FROM SI_PHONE_TYPE WHERE type_id LIKE '" . $this->id . "%'";
        $dbmgr_web->query($stmt);
        $rs = $dbmgr_web->fetch_row();
        return($rs[0]);
    }
    
    // Check the DB to see if this company name exists in the table.
    function db_type_name_exists($dbmgr_web)
    {
        $toReturn = false;
        $stmt = "SELECT count(*) FROM SI_PHONE_TYPE WHERE name = '" . $this->name . "'";
        $dbmgr_web->query($stmt);
        $rs = $dbmgr_web->fetch_row();
        return($rs[0]);
    }
    
    function db_load_id_via_name($dbmgr_web)
    {
        $stmt = "SELECT type_id FROM SI_PHONE_TYPE WHERE name = '" . $this->name . "'";
        $dbmgr_web->query($stmt);
        $rs = $dbmgr_web->fetch_row();
        $this->id = trim($rs[0]);
    }
    function db_load_name($dbmgr_web)
    {
        $stmt = "SELECT name FROM SI_PHONE_TYPE WHERE type_id = '" . $this->id . "'";
        $dbmgr_web->query($stmt);
        $rs = $dbmgr_web->fetch_row();
        $this->name = trim($rs[0]);
    }
    /*******************************************************************
    * END DB Functions
    ********************************************************************/
}   
?>