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
            if($this->db_company_name_exists())
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
    * Phone Co DB Functions
    *******************************************************************/
    function db_insert_phone_company()
    {
        $dbmgr = new DB_Mgr("sites");
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
        $dbmgr = new DB_Mgr("sites");
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
        $dbmgr = new DB_Mgr("sites");
        $stmt = "SELECT count(*) FROM SI_PHONE_CO WHERE name = '" . $this->name . "'";
        $dbmgr->query($stmt);
        $row = $dbmgr->get_a_row();
        $num = $row[0];
        return($num);
    }
    function db_load_co_via_name()
    {
        $dbmgr = new DB_Mgr("sites");
        $stmt = "SELECT * FROM SI_PHONE_CO WHERE name = '" . $this->name . "'";
        $dbmgr->query($stmt);
        if($dbmgr->get_num_rows() > 0)
        {
            $array = $dbmgr->get_a_row();
            $this->id = $array[0];
        }
        else
        {
            $this->id = "0";
        }
    }
    function db_load_co_via_id()
    {
        $dbmgr = new DB_Mgr("sites");
        $stmt = "SELECT name FROM SI_PHONE_CO WHERE phone_co_id = '$this->id'";
        $dbmgr->query($stmt);
        if($dbmgr->get_num_rows() > 0)
        {
            $array = $dbmgr->get_a_row();
            $this->name = $array[0];
        }
        else
        {
            $this->name = "ERROR";
        }
    }
    /*
    function db_get_num_keys_like_my_key()
    {
        $dbmgr = new DB_Mgr("sites");
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
        $dbmgr = new DB_Mgr("sites");
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
        $dbmgr = new DB_Mgr("sites");
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