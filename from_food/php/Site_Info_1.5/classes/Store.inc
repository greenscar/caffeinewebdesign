<?php
require_once("Site.inc");
require_once("Amenity.inc");
class Store extends Site
{
    var $sysco_num;
    var $amenity_list;
    var $region;
    var $district;
    
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
     * PHONE LINE FUNCTIONS
     ********************************************************************/
    /*
    function get_phone_num($type_id)
    {
        return $this->phone_system->get_number_via_code($type_id);
    }
    function set_phone_company($co_id)
    {
        $this->phone_system->company = new Phone_Company($co_id);
    }
    */
    /*********************************************************************
     * END PHONE LINE FUNCTIONS
     ********************************************************************/
        
    /*********************************************************************
     * Amenity List Functions
     ********************************************************************/
     /*
    // Add the amenity to the amenity array.
    // REQUIRE: This amenity name does not already exist
    function add_amenity($name, $allowed)
    {
        $allowed = (strcmp($allowed, "true") == 0) ? 1 : 0;
        $amen = new Amenity($name, $allowed);
        array_push($this->amenity_list, $amen);
    }
    
     */
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
    * Amenity DB Functions
    ********************************************************************/
    /*
    // Insert the amenities into the db for this store.
    function db_insert_amenities()
    {
        $this->dbmgr = new DB_Mgr("sites");
        //$stmt = "INSERT INTO " . $table["amenity_list"]
        
        $stmt = "INSERT INTO SI_AMENITY VALUES ("
              . $this->site_num . ", "
              . $this->get_amenity("smoking") . ", "
              . $this->get_amenity("alcohol") . ", "
              . $this->get_amenity("gaming") . ");";
        return $this->dbmgr->query($stmt);
    }
    
    // Update the amenities in the db for this store.
    function db_update_amenities()
    {
        $this->dbmgr = new DB_Mgr("sites");
        $stmt = "UPDATE SI_AMENITY SET "
              . "smoking = " . $this->get_amenity("smoking") . ", "
              . "alcohol = " . $this->get_amenity("alcohol") . ", "
              . "gaming = " . $this->get_amenity("gaming") . " "
              . "WHERE loc_num = $loc_num";
        return $this->dbmgr->query($stmt);
    }
    
    */
    function db_update_amenities()
    {
        if(empty($this->dbmgr))
            $this->dbmgr = new DB_Mgr("sites");
        $stmt = "DELETE FROM SI_AMENITY_ALLOWED WHERE loc_num = " . $this->site_num;
        $this->dbmgr->execute($stmt);
        for($i=0; $i<sizeof($this->amenity_list); $i++)
        {
            $am = $this->amenity_list[$i];
            $stmt = "INSERT INTO SI_AMENITY_ALLOWED VALUES("
                . $this->site_num . ", '"
                . $am->id . "', "
                . $am->allowed . ");";
            if(!($this->dbmgr->execute($stmt)))
            {
                return (false);
            }
        }
        return(true);
    }
    function db_load_amenities()
    {
        $this->amenity_list = array();
        $this->dbmgr = new DB_Mgr("sites");
        $stmt = "SELECT t.type_id, t.name, a.allowed "
                . "FROM SI_AMENITY_ALLOWED a, SI_AMENITY_TYPE t "
                . "WHERE a.type_id = t.type_id "
                . "AND a.loc_num = " . $this->site_num;
        $this->dbmgr->query($stmt);
        
        if($this->dbmgr->get_num_rows() == 0)
        {
            $stmt = "SELECT type_id, name FROM SI_AMENITY_TYPE";
            $this->dbmgr->query($stmt);
            while($row = $this->dbmgr->get_a_row())
            {
                $this->add_amenity(trim($row[0]), trim($row[1]), 0);
            }
            
        }
        else
        {
            while($row = $this->dbmgr->get_a_row())
            {
                $this->add_amenity(trim($row[0]), trim($row[1]), trim($row[2]));
            }
        }
    }
    function db_load_amenity_type_list()
    {
        $this->amenity_list = array();
        if(empty($this->dbmgr))
            $this->dbmgr = new DB_Mgr("sites");
        $select = "SELECT * FROM SI_AMENITY_TYPE";
        $this->dbmgr->query($select);
        while($row = $this->dbmgr->get_a_row())
        {
            $this->add_amenity(trim($row[0]), trim($row[1]));
        }
    }
    /*******************************************************************
    * END Amenity DB Functions
    ********************************************************************/
    
    /*******************************************************************
    * Territory DB Functions
    ********************************************************************/
    function db_load_territory($ps_link)
    {
        $stmt = "SELECT REGION, DISTRICT FROM PS_SH_TERRITORY WHERE DEPTID = '" . $this->site_num . "';";
        $query = mssql_query($stmt, $ps_link);
        $row = mssql_fetch_row($query);
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
        /*
        * THIS WILL INSERT DATA INTO:
        * SI_SITE, SI_CONTACT, SI_AMENITY, SI_PHONE_LINE, SI_TERRITORY
        */
        // CREATE THE DB MGR
        $this->dbmgr = new DB_Mgr("sites");
        
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
        if(!($this->dbmgr->execute($stmt)))
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
    
    function db_load_all_info($d_dub_link, $ps_link)
    {
        $stmt = "SELECT * FROM SI_SITE WHERE loc_num = " . $this->site_num;
        if(!($rs = mssql_query($stmt, $d_dub_link)))
        {
            // INSERT FAILED.
            return ("<div class=\"error\">Error in db_load_store stmt:<br> $stmt</div>");
        }
        //if($row = $this->dbmgr->get_a_row())
        if($row = mssql_fetch_row($rs))
        {
            $this->site_name = trim(stripslashes($row[1]));
            $this->address->description = trim(stripslashes($row[2]));
            $this->address->street = trim(stripslashes($row[3]));
            $this->address->city = trim(stripslashes($row[4]));
            $this->address->state = trim(stripslashes($row[5]));
            $this->address->zip = trim(stripslashes($row[6]));
            $this->sysco_num = trim(stripslashes($row[7]));
            $this->phone_system->db_load_co_via_id(trim(stripslashes($row[8])));
            //$this->date_active = $row[9];
            $ts = strtotime($row[9]);
            $this->date_active = date("m/d/Y", $ts);
            $this->active = $row[10];
        }
        else
        {
            return("<div class=\"error\">ERROR. NO LOCATION NUMBER " . $this->site_num . " EXISTS!!!!<div class=\"error\">");
        }
        $this->phone_system->db_load_lines($this->site_num);
        $this->db_load_amenities();
        $this->db_load_contacts($ps_link);
        $this->db_load_territory($ps_link);
    }
    
    function db_update_store()
    {
        /*
        * THIS WILL UPDATE DATA IN:
        * SI_SITE, SI_AMENITY, SI_PHONE_LINE
        */
        // CREATE THE DB MGR
        $this->dbmgr = new DB_Mgr("sites");
        
        // UPDATE SI_SITE
        $stmt = "UPDATE SI_SITE SET "
              . "loc_name_1 = '" . addslashes($this->site_name) . "', "
              . "loc_name_2 = '" . addslashes($this->address->description) . "', "
              . "address = '" . addslashes($this->address->street) . "', "
              . "city = '" . addslashes($this->address->city) . "', "
              . "state = '" . $this->address->state . "', "
              . "zip = " . $this->address->zip . ", "
              . "sysco_# = " . $this->sysco_num . ", "
              . "phone_co_id = '" . $this->phone_system->company->id . "', "
              . "date_open = '" . $this->get_date_active_for_mssql() . "', "
              . "active = " . $this->active . " "
              . "WHERE loc_num = " . $this->site_num;
        //return($stmt);
        if(!($this->dbmgr->execute($stmt)))
        {
            // UPDATE FAILED.
            return ("<div class=\"error\">Error in SI_SITE update<BR>$stmt</div>");
        }
        
        
        if(!($this->db_update_amenities()))
        {
            return ("<div class=\"error\">Error in SI_AMENITY_ALLOWED update<BR>$stmt</div>");
        }
        
        //UPDATE SI_PHONE_LINE
        
        $stmt = "DELETE FROM SI_PHONE_LINE WHERE loc_num = " . $this->site_num;
        $this->dbmgr->execute($stmt);
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
    }
    /*******************************************************************
    * END Site DB Functions
    ********************************************************************/
}
?>