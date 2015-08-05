<?php
require_once("DB_Mgr.inc");
class Phone_Company
{
    var $name;
    var $id;
    
    function Phone_Company($id = "", $n = "")
    {
        $this->id = $id;
        $this->name = $n;
    }
    /*******************************************************************
    * Phone Co DB Functions
    *******************************************************************/
    function db_insert_phone_company()
    {
        $dbmgr = new DB_Mgr();
        $stmt = "INSERT INTO SI_PHONE_CO VALUES ('"
              . $this->id . "', '"
              . $this->name . "');";
        //return($stmt);
        return $dbmgr->query($stmt);
    }
    
    // Query the DB for the number of occurances an ID starts with this object's ID.
    function db_num_times_id_exists()
    {
        $toReturn = false;
        $dbmgr = new DB_Mgr();
        $stmt = "SELECT count(*) FROM SI_PHONE_CO WHERE phone_co_id LIKE '" . $this->id . "%'";
        $dbmgr->query($stmt);
        $row = $dbmgr->get_a_row();
        $num = $row[0];
        return($num);
    }
    
    // Check the DB to see if this company name exists in the table.
    function db_company_name_exists()
    {
        $toReturn = false;
        $dbmgr = new DB_Mgr();
        $stmt = "SELECT count(*) FROM SI_PHONE_CO WHERE name = '" . $this->name . "'";
        $dbmgr->query($stmt);
        $row = $dbmgr->get_a_row();
        $num = $row[0];
        return($num);
    }
    
    /*
    function db_load_phone_co_name($co_id)
    {
        $dbmgr = new DB_Mgr();
        $stmt = "SELECT name FROM SI_PHONE_CO WHERE phone_co_id = $co_id";
        $dbmgr->query($stmt);
        if($dbmgr->get_num_rows() > 0)
        {
            $array = $dbmgr->get_a_row();
            $this->id = $array[0];
            $this->name = $array[1];
            return($this->id);
        }
        else
        {
            return(0);
        }
    }
    function db_get_num_keys_like_my_key()
    {
        $dbmgr = new DB_Mgr();
        $stmt = "SELECT count(name) FROM SI_PHONE_CO WHERE phone_co_id LIKE '" . $this->id . "%'";
        $dbmgr->query($stmt);
        $array = $dbmgr->get_a_row();
        $count = $array[0];
        return $count;
    }
    // Check the DB to see if this company name exists in the table.
    function db_phone_co_name_exists()
    {
        $toReturn = false;
        $dbmgr = new DB_Mgr();
        $stmt = "SELECT phone_co_id FROM SI_PHONE_CO WHERE name = '" . $this->name . "'";
        $dbmgr->query($stmt);
        if($dbmgr->get_num_rows() > 0)
        {
            $toReturn = true;
        }
        return($toReturn);
    }
    
    // Check the DB to see if this company nick exists in the table.
    function db_phone_co_id_exists()
    {
        $toReturn = false;
        $dbmgr = new DB_Mgr();
        $stmt = "SELECT name FROM SI_PHONE_CO WHERE phone_co_id = '" . $this->id . "'";
        $dbmgr->query($stmt);
        if($dbmgr->get_num_rows() > 0)
        {
            $toReturn = true;
        }
        return($toReturn);
    }
    */
    /*******************************************************************
    * END Phone Co DB Functions
    *******************************************************************/
    
}

?>