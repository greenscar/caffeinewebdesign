<?php
require_once("../../../php_objects/DB_Mgr.inc");
require_once("Secretary.inc");
class Phone_Company
{
    var $name;
    var $id;
    var $log;
    var $dbmgr;
    
    function Phone_Company($id = "", $n = "")
    {
        $this->id = $id;
        $this->name = $n;
        $this->log = new Secretary();
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
    function db_insert_phone_company($dbmgr_web)
    {
        $stmt = "INSERT INTO SI_PHONE_CO VALUES ('"
              . $this->id . "', '"
              . $this->name . "');";
        return $dbmgr->exec($stmt);
        //return odbc_exec($dbmgr_web, $stmt);
    }
    
    // Query the DB for the number of occurances an ID starts with this object's ID.
    function db_num_times_id_exists($dbmgr_web)
    {
        $toReturn = false;
        $stmt = "SELECT count(*) FROM SI_PHONE_CO WHERE phone_co_id LIKE '" . $this->id . "%'";
        $dbmgr_web->query($stmt);
        $rs = $dbmgr_web->fetch_row();
        return $rs[0];
        //$rs = odbc_exec($dbmgr_web, $stmt);
        //odbc_fetch_row($rs);
        //return(odbc_result($rs,1));
    }
    
    // Check the DB to see if this company name exists in the table.
    function db_company_name_exists($dbmgr_web)
    {
        $toReturn = false;
        $stmt = "SELECT count(*) FROM SI_PHONE_CO WHERE name = '" . $this->name . "'";
        $dbmgr_web->query($stmt);
        $rs = $dbmgr_web->fetch_row();
        return $rs[0];
        /*
        $rs = odbc_exec($dbmgr_web, $stmt);
        odbc_fetch_row($rs);
        return(odbc_result($rs,1));
        */
       }
    function db_load_co_via_name($dbmgr_web)
    {
        $stmt = "SELECT * FROM SI_PHONE_CO WHERE name = '" . $this->name . "'";
        $dbmgr_web->query($stmt);
        $rs = $dbmgr_web->fetch_row();
        $dbmgr_web->free_result();
        return $rs[0];
        /*
        $rs = odbc_exec($dbmgr_web, $stmt);
        odbc_fetch_row($rs);
        $this->id = odbc_result($rs,1);
        */
    }
    function db_load_co_via_id()
    {
        $this->log->write("Phone_Company->db_load_co_via_id($dbconn) START");
        $conn = odbc_connect("WEB", "sa", "ferrarif1");
        $this->log->write("conn = $conn");
        $stmt = "SELECT name FROM SI_PHONE_CO WHERE phone_co_id = '$this->id'";
        $this->log->write($stmt);
        $rs = odbc_exec($conn, $stmt);
        $this->log->write("rs = " . $odbc_error($conn));
        echo("rs = " . $odbc_error($conn));
        $name = $rs[0];
        $dbmgr->disconnect();
        $this->log->write("Phone_Company->db_load_co_via_id($dbconn) => " . $name);
        return $name;
        /*
        $rs = odbc_exec($dbmgr_web, $stmt);
        odbc_fetch_row($rs);
        $this->name = odbc_result($rs,1);
        */
    }
    /*******************************************************************
    * END Phone Co DB Functions
    *******************************************************************/
    
}

?>