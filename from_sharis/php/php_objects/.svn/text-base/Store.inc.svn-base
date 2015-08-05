<?php
require_once("Site.inc");
require_once("Amenity.inc");
class Store extends Site
{
    var $sysco_num;
    var $amenity_list;
    var $region;
    var $district;
    var $stmt;
    function Store($num = "", $name = "")
    {
        Site::Site();
        $this->amenity_list = array();
        $this->site_num = $num;
        $this->site_name = $name;
    }
    
    function display_info()
    {
        $go = "NUMBER: " . $this->site_num . "<br>"
            . "NAME: " . $this->site_name . "<br>"
            . "MGR EMPLID: " . $this->manager->emplid . "<br>"
            . "MGR NAME: " . $this->manager->first_name . " " . $this->manager->last_name . "<br>"
            . "DATE ACTIVE: " . $this->date_active . "<br>"
            . "ADDRESS:<br> " . $this->address->description . "<br>"
            . "         " . $this->address->street . "<br>"
            . "         " . $this->address->city . ", " . $this->address->state . " " . $this->address->zip . "<br>"
            . "ACTIVE = " . $this->active . "<br>"
            . "SYSCO = " . $this->sysco_num . "<BR>";
        
        $go .= "<h3>PHONE SYSTEM</h3>";        
        
        if(strcmp($this->phone_system->company->id, "0") == 0)
            $go .= "<h1>PHONE CO ID= XXXXXXXXXXXX</h1>";
        else
            $go .= "PHONE CO ID = " .$this->phone_system->company->id . "<br>";
        for($i=0; $i<sizeof($this->phone_system->line_list); $i++)
        {
            $go .= $this->phone_system->line_list[$i]->type->id . " => " . $this->phone_system->line_list[$i]->number . "<BR>";
        }
        for($i=0; $i<sizeof($this->amenity_list); $i++)
        {
            $am = $this->amenity_list[$i];
            $go .= $am->name . " allowed = " . $am->allowed . "<br>";
        }
        return($go);
    }
    function get_sysco_num()
    {
        return $this->sysco_num;
    }
    function set_sysco_num($n)
    {
        $this->sysco_num = $n;
    }
    
    
    
    /*********************************************************************
     * Amenity List Functions
     ********************************************************************/
    
    function get_amenity_allowed($name)
    {
        $toReturn = "0";
        foreach($this->amenity_list AS $key=>$val)
        {
            if(strcmp($val->name, $name) == 0)
                $toReturn = $val->allowed;
        }
        return $toReturn;
    }
    function get_amenity_from_list($array_loc)
    {
        return($this->amenity_list[$array_loc]);
    }
    function amenity_list_size()
    {
        return sizeof($this->amenity_list);
    }
    function remove_all_amenities()
    {
        $this->amenity_list = array();
    }
    
    function add_amenity($id, $name, $allowed = 0)
    {
        $am = new Amenity($id, $name, $allowed);
        return(array_push($this->amenity_list, $am));
    }
    /*********************************************************************
     * END Amenity List Functions
     ********************************************************************/
    
    
    /*******************************************************************
    * Store DB Functions
    ********************************************************************/
    function db_load_all_info($si_cnx, $ps_cnx)
    {
        $stmt = "SELECT * FROM SI_SITE WHERE loc_num = " . $this->site_num;
        $rs = odbc_exec($si_cnx, $stmt);
        if(odbc_fetch_row($rs))
        {
            //$this->site_name = odbc_num_fields($rs);
            $this->site_name = trim(stripslashes(odbc_result($rs,2)));
            $this->address->description = trim(odbc_result($rs,3));
            $this->address->street = trim(odbc_result($rs,4));
            $this->address->city = trim(stripslashes(odbc_result($rs,5)));
            $this->address->state = trim(odbc_result($rs,6));
            $this->address->zip = trim(odbc_result($rs,7));
            $this->sysco_num = trim(odbc_result($rs,8));
            $this->phone_system->db_load_co_via_id($si_cnx, trim(odbc_result($rs,9)));
            $this->convert_mssql_to_date_active(odbc_result($rs,10));
            $this->active = odbc_result($rs,11);
        }
        else
        {
            return("<div class=\"error\">ERROR. NO LOCATION NUMBER " . $this->site_num . " EXISTS!!!!<div class=\"error\">");
        }
        $this->phone_system->db_load_lines($si_cnx, $this->site_num);
        $this->db_load_amenities($si_cnx);
        $this->db_load_contacts($ps_cnx);
        $this->db_load_territory($ps_cnx);
    }
    /*******************************************************************
    * END Store DB Functions
    ********************************************************************/
        
    /*******************************************************************
    * Amenity DB Functions
    ********************************************************************/
    function db_load_amenities($si_cnx)
    {
        $this->amenity_list = array();
        $stmt = "SELECT t.type_id, t.name, a.allowed "
                . "FROM SI_AMENITY_ALLOWED a, SI_AMENITY_TYPE t "
                . "WHERE a.type_id = t.type_id "
                . "AND a.loc_num = " . $this->site_num
                . " ORDER BY t.print_order";
        $this->stmt = $stmt;
        $rs = odbc_exec($si_cnx, $stmt);
        while(odbc_fetch_row($rs))
        {
            $this->add_amenity(trim(odbc_result($rs,1)), trim(odbc_result($rs,2)), odbc_result($rs,3));
        }
        if(sizeof($this->amenity_list) == 0)
            $this->db_load_amenity_type_list($si_cnx);
    }
    function db_load_amenity_type_list($si_cnx)
    {
        $this->amenity_list = array();
        $stmt = "SELECT * FROM SI_AMENITY_TYPE ORDER BY print_order";
        $rs = odbc_exec($si_cnx, $stmt);
        while(odbc_fetch_row($rs))
        {
            $this->add_amenity(trim(odbc_result($rs,1)), trim(odbc_result($rs,2)));
        }
    }
    function db_update_amenities($si_cnx)
    {   
        // DELETE ALL FROM THIS STORE
        $stmt = "DELETE FROM SI_AMENITY_ALLOWED WHERE loc_num = " . $this->site_num;
        odbc_exec($si_cnx, $stmt);
        // ADD THIS STORE'S
        for($i=0; $i < sizeof($this->amenity_list); $i++)
        {
            $amenity = $this->amenity_list[$i];
            $stmt = "INSERT INTO SI_AMENITY_ALLOWED VALUES("
                  . $this->site_num . ", '"
                  . $amenity->id . "', "
                  . $amenity->allowed . ");";
            odbc_exec($si_cnx, $stmt);
        }
    }
    /*******************************************************************
    * END Amenity DB Functions
    ********************************************************************/
    
    /*******************************************************************
    * Territory DB Functions
    ********************************************************************/
    function db_load_territory($ps_cnx)
    {
        $stmt = "SELECT REGION, DISTRICT FROM PS_SH_TERRITORY WHERE DEPTID = '" . $this->site_num . "';";
        $rs = odbc_exec($ps_cnx, $stmt);  
        odbc_fetch_row($rs);
        $this->region = trim(odbc_result($rs,1));
        $this->district = trim(odbc_result($rs,2));
    }
    /*******************************************************************
    * END Territory DB Functions
    ********************************************************************/
    
    /*******************************************************************
    * Site DB Functions
    ********************************************************************/
    function db_insert_new_store()
    {
        /*
        * THIS WILL INSERT DATA INTO:
        * SI_SITE, SI_CONTACT, SI_AMENITY, SI_PHONE_LINE, SI_TERRITORY
        */
        // INSERT INTO SI_SITE
        $stmt = "INSERT INTO SI_SITE VALUES("
              . $this->site_num . ", '"
              . addslashes($this->site_name) . "', '"
              . $this->address->description . "', '"
              . $this->address->street . "', '"
              . addslashes($this->address->city) . "', '"
              . $this->address->state . "', "
              . $this->address->zip . ", "
              . $this->sysco_num . ", '" 
              . $this->phone_system->company->id . "', '"
              . $this->date_active . "', "
              . $this->active . ");";
        //return($stmt);
        if(!(odbc_exec($si_cnx, $stmt)))
        {
            // INSERT FAILED.
            return ("Error in SI_SITE insert<BR>$stmt<BR>");
        }
        
        // INSERT INTO SI_CONTACT
        $stmt = "INSERT INTO SI_SITE_CONTACT VALUES("
              . $this->site_num . ", "
              . $this->mgr_emp_id;
        for($i=0; $i < 4; $i++)
        {
            if($i < sizeof($this->asst_mgr_list))
                $stmt .=  ", " . $this->asst_mgr_list[$i];
            else
                $stmt .=  ", 0";
        }   
        $stmt .= ");";
        if(!($this->dbmgr->execute($stmt)))
        {
            // INSERT FAILED.
            return ("Error in SI_SITE_CONTACT insert");
        }
        
        // INSERT INTO SI_AMENITY
        $stmt = "INSERT INTO SI_AMENITY VALUES("
                . $this->site_num . ", "
                . $this->get_amenity_allowed("smoking") . ", "
                . $this->get_amenity_allowed("alcohol") . ", "
                . $this->get_amenity_allowed("gaming") . ");";
        if(!($this->dbmgr->execute($stmt)))
        {
            // INSERT FAILED.
            return ("Error in SI_AMENITY insert");
        }
        
        // INSERT INTO SI_PHONE_LINE
        for($x=0; $x < $this->phone_system->line_list_size(); $x++)
        {
            $phone_line = $this->phone_system->get_line($x);
            //return ($phone_line->db_insert_phone_line($this->site_num));
            if(!($phone_line->db_insert_phone_line($this->site_num)))
            {
                // INSERT FAILED.
                return ("Error in SI_PHONE_LINE insert");
            }
        }
              
        //INSERT INTO SI_TERRITORY
        $stmt = "INSERT INTO SI_TERRITORY VALUES("
              . $this->district->number . ", "
              . $this->district->region->number . ", "
              . $this->site_num . ", '"
              . $this->date_active . "');";
        if(!($this->dbmgr->execute($stmt)))
        {
            // INSERT FAILED.
            return ("Error in SI_TERRITORY insert");
        }
        return(true);
    }
    
    function db_update_store($si_cnx)
    {
        /*
        * THIS WILL UPDATE DATA IN:
        * SI_SITE, SI_AMENITY, SI_PHONE_LINE
        */
        // UPDATE SI_SITE
        $stmt = "UPDATE SI_SITE SET "
              . "loc_name_1 = '" . str_replace("'", "''", $this->site_name) . "', "
              . "loc_name_2 = '" . str_replace("'", "''", $this->address->description) . "', "
              . "address = '" . str_replace("'", "''",$this->address->street) . "', "
              . "city = '" . str_replace("'", "''", $this->address->city) . "', "
              . "state = '" . $this->address->state . "', "
              . "zip = " . $this->address->zip . ", "
              . "sysco_# = " . $this->sysco_num . ", "
              . "phone_co_id = '" . $this->phone_system->company->id . "', "
              . "date_open = '" . $this->get_date_active_for_mssql() . "', "
              . "active = " . $this->active . " "
              . "WHERE loc_num = " . $this->site_num;
        //return($stmt);
        $rs = odbc_exec($si_cnx, $stmt);
        
        $this->db_update_amenities($si_cnx);
        //UPDATE SI_PHONE_LINE
        $stmt = "DELETE FROM SI_PHONE_LINE WHERE loc_num = " . $this->site_num;
        $rs = odbc_exec($si_cnx, $stmt);
        // INSERT INTO SI_PHONE_LINE
        for($x=0; $x < $this->phone_system->line_list_size(); $x++)
        {
            $phone_line = $this->phone_system->get_line($x);
            //return ($phone_line->db_insert_phone_line($this->site_num));
            if(!($phone_line->db_insert_phone_line($si_cnx, $this->site_num)))
            {
                // INSERT FAILED.
                return ("Error in SI_PHONE_LINE insert");
            }
        }
    }
    /*******************************************************************
    * END Site DB Functions
    ********************************************************************/
}
?>