<?php
require_once("DB_Mgr.inc");
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
    function db_insert_phone_type()
    {
        $dbmgr = new DB_Mgr("sites");
        $stmt = "INSERT INTO SI_PHONE_TYPE VALUES ('"
              . $this->id . "', '"
              . $this->name . "', \""
              . $this->description . "\");";
        return $dbmgr->query($stmt);
    }
    
    // Query the DB for the number of occurances an ID starts with this object's ID.
    function db_num_times_id_exists()
    {
        $toReturn = false;
        $dbmgr = new DB_Mgr("sites");
        $stmt = "SELECT count(*) FROM SI_PHONE_TYPE WHERE type_id LIKE '" . $this->id . "%'";
        $dbmgr->query($stmt);
        $row = $dbmgr->get_a_row();
        $num = $row[0];
        return($num);
    }
    
    // Check the DB to see if this company name exists in the table.
    function db_type_name_exists()
    {
        $toReturn = false;
        $dbmgr = new DB_Mgr("sites");
        $stmt = "SELECT count(*) FROM SI_PHONE_TYPE WHERE name = '" . $this->name . "'";
        $dbmgr->query($stmt);
        $row = $dbmgr->get_a_row();
        $num = $row[0];
        return($num);
    }
    
    function db_load_id_via_name()
    {
        $dbmgr = new DB_Mgr("sites");
        $stmt = "SELECT type_id FROM SI_PHONE_TYPE WHERE name = '" . $this->name . "'";
        $dbmgr->query($stmt);
        $row = $dbmgr->get_a_row();
        $this->id = trim($row[0]);
    }
    function db_load_name()
    {
        $dbmgr = new DB_Mgr("sites");
        $stmt = "SELECT name FROM SI_PHONE_TYPE WHERE type_id = '" . $this->id . "'";
        $dbmgr->query($stmt);
        $row = $dbmgr->get_a_row();
        $this->name = trim($row[0]);
    }
    /*******************************************************************
    * END DB Functions
    ********************************************************************/
}   
?>