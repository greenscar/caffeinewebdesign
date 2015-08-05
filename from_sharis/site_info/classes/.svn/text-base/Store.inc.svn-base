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
    
    function set_mgr_first_name($name)
    {
        $this->manager->first_name = $name;
    }
    function set_asst_mgr_first_name($am_array_loc, $name)
    {
        $this->asst_mgr_list[$am_array_loc]->first_name = $name;
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
    function db_load_all_info()
    {
        $this->log->write("db_load_all_info() START");
        $stmt = "SELECT s.site_name, s.loc_name, s.sysco_#, s.phone_co_id, s.date_open, s.active, "
              . "a.street, a.city, a.state, a.zip"
              . " FROM SI_SITE s, SI_ADDRESS a"
              . " WHERE a.address_id = s.address_id"
              . " AND s.dept_id = " . $this->site_num;
        //$stmt = "SELECT  FROM SI_SITE WHERE dept_id = " . $this->site_num;
        //return($dbmgr_web);
        $dbmgr_web = new DB_Mgr("web");
        $this->log->write($stmt);
        $dbmgr_web->free_result();
        $dbmgr_web->query($stmt);
        //$this->log->write($dbmgr_web->query($stmt));
        //$this->log->write("num_rows => " . $dbmgr_web->get_num_rows());
        if($row = $dbmgr_web->fetch_row())
        {
            //$this->log->write("name = " . $row[0]);
            //$this->site_name = odbc_num_fields($rs);
            $this->site_name = trim(stripslashes($row[0]));
            $this->address->description = trim($row[1]);
            $this->sysco_num = trim($row[2]);
            $this->phone_system->db_load_co_via_id(trim($row[3]));
            $this->convert_mssql_to_date_active($row[4]);
            $this->active = $row[5];
            $this->address->street = trim($row[6]);
            $this->address->city = trim(stripslashes($row[7]));
            $this->address->state = trim($row[8]);
            $this->address->zip = trim($row[9]);
        }
        else
        {
            return("<div class=\"error\">ERROR. NO LOCATION NUMBER " . $this->site_num . " EXISTS!!!!<div class=\"error\">");
        }
        //$dbmgr_web->free_result();
        $this->log->write($this->display_info());
        //$this->phone_system->db_load_lines($dbmgr_web, $this->site_num);
        //$this->db_load_amenities($dbmgr_web);
        //$this->db_load_contacts($dbmgr_ps);
        //$this->db_load_territory($dbmgr_ps);
        $this->log->write("db_load_all_info($dbmgr_web, $dbmgr_ps) END");
    }
    /*******************************************************************
    * END Store DB Functions
    ********************************************************************/
        
    /*******************************************************************
    * Amenity DB Functions
    ********************************************************************/
    function db_load_amenities($dbmgr_web)
    {
        //$this->log->write("db_load_amenities($dbmgr_web)");
        $this->amenity_list = array();
        $stmt = "SELECT t.type_id, t.name, a.allowed "
                . "FROM SI_AMENITY_ALLOWED a, SI_AMENITY_TYPE t "
                . "WHERE a.type_id = t.type_id "
                . "AND a.dept_id = " . $this->site_num
                . " ORDER BY t.print_order";
        $this->log->write($stmt);
        //$this->stmt = $stmt;
        $dbmgr_web->query($stmt);
        while($row = $dbmgr_web->fetch_row())
        {
            $this->add_amenity(trim($row[0]), trim($row[1]), $row[2]);
        }
        if(sizeof($this->amenity_list) == 0)
            $this->db_load_amenity_type_list($dbmgr_web);
        //$this->log->write_end_of_fxn();
    }
    function db_load_amenity_type_list($dbmgr_web)
    {
        //$this->log->write("db_load_amenity_type_list($dbmgr_web)");
        $this->amenity_list = array();
        $stmt = "SELECT * FROM SI_AMENITY_TYPE ORDER BY print_order";
        //$this->log->write($stmt);
        $dbmgr_web->query($stmt);
        while($row = $dbmgr_web->fetch_row())
        {
            $this->add_amenity(trim($row[0]), trim($row[1]));
        }
        //$this->log->write_end_of_fxn();
    }
    function db_update_amenities($dbmgr_web)
    {
        //$this->log->write("db_update_amenities($dbmgr_web)");
        // DELETE ALL FROM THIS STORE
        $stmt = "DELETE FROM SI_AMENITY_ALLOWED WHERE dept_id = " . $this->site_num;
        //$this->log->write($stmt);
        $dbmgr_web->query($stmt);
        //odbc_exec($dbmgr_web, $stmt);
        // ADD THIS STORE'S
        for($i=0; $i < sizeof($this->amenity_list); $i++)
        {
            $amenity = $this->amenity_list[$i];
            $stmt = "INSERT INTO SI_AMENITY_ALLOWED VALUES('SMC', "
                  . $this->site_num . ", '"
                  . $amenity->id . "', "
                  . $amenity->allowed . ");";
            //$this->log->write($stmt);
            $dbmgr_web->query($stmt);
            //odbc_exec($dbmgr_web, $stmt);
        }
        //$this->log->write_end_of_fxn();
    }
    /*******************************************************************
    * END Amenity DB Functions
    ********************************************************************/
    
    /*******************************************************************
    * Territory DB Functions
    ********************************************************************/
    function db_load_territory($dbmgr_ps)
    {
        $stmt = "SELECT REGION, DISTRICT FROM PS_SH_TERRITORY WHERE DEPTID = '" . $this->site_num . "';";
        $dbmgr_ps->query($stmt);
        $row = $dbmgr_ps->fetch_row();
        $this->region = trim($row[0]);
        $this->district = trim($row[1]);
    }
    /*******************************************************************
    * END Territory DB Functions
    ********************************************************************/
    
    /*******************************************************************
    * Site DB Functions
    ********************************************************************/
    function db_insert_new_store()
    {
        //$this->log->write("db_insert_new_store()");
        /*
        * THIS WILL INSERT DATA INTO:
        * SI_SITE, SI_CONTACT, SI_AMENITY, SI_PHONE_LINE, SI_TERRITORY
        */
        // INSERT INTO SI_SITE
        $stmt = "INSERT INTO SI_SITE VALUES('SMC', "
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
        //$this->log->write($stmt);
        if(!(odbc_exec($dbmgr_web, $stmt)))
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
        //$this->log->write($stmt);
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
        //$this->log->write($stmt);
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
        //$this->log->write($stmt);
        if(!($this->dbmgr->execute($stmt)))
        {
            // INSERT FAILED.
            return ("Error in SI_TERRITORY insert");
        }
        //$this->log->write_end_of_fxn();
        return(true);
    }
    
    function db_update_store($dbmgr_web, $dbmgr_ps)
    {
        //$this->log->write("db_update_store($dbmgr_web, $dbmgr_ps)");
        /*
        * THIS WILL UPDATE DATA IN:
        * SI_SITE, SI_AMENITY, SI_PHONE_LINE
        */
        // UPDATE SI_SITE
        $stmt = "UPDATE SI_SITE SET "
              . "site_name = '" . str_replace("'", "''", $this->site_name) . "', "
              . "loc_name = '" . str_replace("'", "''", $this->address->description) . "', "
              . "sysco_# = " . $this->sysco_num . ", "
              . "phone_co_id = '" . $this->phone_system->company->id . "', "
              . "date_open = '" . $this->get_date_active_for_mssql() . "', "
              . "active = " . $this->active . " "
              . "WHERE dept_id = " . $this->site_num
              . "AND company = 'SMC'";
        //return($stmt);
        //$this->log->write($stmt);
        $rs = odbc_exec($dbmgr_web, $stmt);
        
        $stmt = "SELECT address_id FROM SI_SITE where company = 'SMC' AND dept_id = " . $this->site_num;
        $rs = odbc_exec($dbmgr_web, $stmt);  
        odbc_fetch_row($rs);
        $addId = trim(odbc_result($rs,1));
        
        $stmt = "UPDATE SI_ADDRESS SET "
              . "address = '" . str_replace("'", "''",$this->address->street) . "', "
              . "city = '" . str_replace("'", "''", $this->address->city) . "', "
              . "state = '" . $this->address->state . "', "
              . "zip = " . $this->address->zip . " "
              . "WHERE address_id = " . $addId;
        
        $this->db_update_amenities($dbmgr_web);
        //UPDATE SI_PHONE_LINE
        $stmt = "DELETE FROM SI_PHONE_LINE WHERE dept_id = " . $this->site_num;
        //$this->log->write($stmt);
        $rs = odbc_exec($dbmgr_web, $stmt);
        // INSERT INTO SI_PHONE_LINE
        for($x=0; $x < $this->phone_system->line_list_size(); $x++)
        {
            $phone_line = $this->phone_system->get_line($x);
            //return ($phone_line->db_insert_phone_line($this->site_num));
            if(!($phone_line->db_insert_phone_line($dbmgr_web, $this->site_num)))
            {
                // INSERT FAILED.
                return ("Error in SI_PHONE_LINE insert");
            }
        }
        
        //UPDATE MGT NICKS
        $this->manager->db_update_nick($dbmgr_ps);
        for($i=0; $i<sizeof($this->asst_mgr_list); $i++)
        {
            $am = $this->asst_mgr_list[$i];
            $am->db_update_nick($dbmgr_ps);
        }
        //return $toReturn;
        //$this->log->write_end_of_fxn();
    }
    /*******************************************************************
    * END Site DB Functions
    ********************************************************************/
}
?>