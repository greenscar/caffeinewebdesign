<?php
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
    function db_insert_phone_company($si_cnx)
    {
        $stmt = "INSERT INTO SI_PHONE_CO VALUES ('"
              . $this->id . "', '"
              . $this->name . "');";
        return odbc_exec($si_cnx, $stmt);
    }
    
    // Query the DB for the number of occurances an ID starts with this object's ID.
    function db_num_times_id_exists($si_cnx)
    {
        $toReturn = false;
        $stmt = "SELECT count(*) FROM SI_PHONE_CO WHERE phone_co_id LIKE '" . $this->id . "%'";
        $rs = odbc_exec($si_cnx, $stmt);
        odbc_fetch_row($rs);
        $num = odbc_result($rs,1);
        odbc_free_result($rs);
        return($num);
    }
    
    // Check the DB to see if this company name exists in the table.
    function db_company_name_exists($si_cnx)
    {
        $toReturn = false;
        $stmt = "SELECT count(*) FROM SI_PHONE_CO WHERE name = '" . $this->name . "'";
        $rs = odbc_exec($si_cnx, $stmt);
        odbc_fetch_row($rs);
        $num = odbc_result($rs,1);
        odbc_free_result($rs);
        return($num);
    }
    function db_load_co_via_name($si_cnx)
    {
        $stmt = "SELECT * FROM SI_PHONE_CO WHERE name = '" . $this->name . "'";
        $rs = odbc_exec($si_cnx, $stmt);
        odbc_fetch_row($rs);
        $this->id = odbc_result($rs,1);
        odbc_free_result($rs);
    }
    function db_load_co_via_id($si_cnx)
    {
        $stmt = "SELECT name FROM SI_PHONE_CO WHERE phone_co_id = '$this->id'";
        $rs = odbc_exec($si_cnx, $stmt);
        odbc_fetch_row($rs);
        $this->name = odbc_result($rs,1);
        odbc_free_result($rs);
    }
    /*******************************************************************
    * END Phone Co DB Functions
    *******************************************************************/
    
}

?>