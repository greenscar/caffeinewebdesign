<?php
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
    function db_insert_phone_type($si_cnx)
    {
        $stmt = "INSERT INTO SI_PHONE_TYPE VALUES ('"
              . $this->id . "', '"
              . $this->name . "', \""
              . $this->description . "\");";
        odbc_exec($si_cnx, $stmt);
    }
    
    // Query the DB for the number of occurances an ID starts with this object's ID.
    function db_num_times_id_exists($si_cnx)
    {
        $toReturn = false;
        $stmt = "SELECT count(*) FROM SI_PHONE_TYPE WHERE type_id LIKE '" . $this->id . "%'";
        $rs = odbc_exec($si_cnx, $stmt);
        odbc_fetch_row($rs);
        $num = odbc_result($rs,1);
        odbc_free_result($rs);
        return($num);
    }
    
    // Check the DB to see if this company name exists in the table.
    function db_type_name_exists($si_cnx)
    {
        $toReturn = false;
        $stmt = "SELECT count(*) FROM SI_PHONE_TYPE WHERE name = '" . $this->name . "'";
        $rs = odbc_exec($si_cnx, $stmt);
        odbc_fetch_row($rs);
        $num = odbc_result($rs,1);
        odbc_free_result($rs);
        return($num);
    }
    
    function db_load_id_via_name($si_cnx)
    {
        $stmt = "SELECT type_id FROM SI_PHONE_TYPE WHERE name = '" . $this->name . "'";
        $rs = odbc_exec($si_cnx, $stmt);
        odbc_fetch_row($rs);
        $this->id = trim(odbc_result($rs,1));
        odbc_free_result($rs);
    }
    function db_load_name($si_cnx)
    {
        $stmt = "SELECT name FROM SI_PHONE_TYPE WHERE type_id = '" . $this->id . "'";
        $rs = odbc_exec($si_cnx, $stmt);
        odbc_fetch_row($rs);
        $this->name = trim(odbc_result($rs,1));
        odbc_free_result($rs);
    }
    /*******************************************************************
    * END DB Functions
    ********************************************************************/
}   
?>