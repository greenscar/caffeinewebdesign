<?php
require_once("Site.inc");
require_once("Amenity.inc");
require_once("District.inc");
class Store extends Site
{
    var $sysco_num;
    var $asst_mgr_list = array();
    var $amenity_list;
    // The district object this store is in
    var $district;
    
    function Store($num, $name)
    {
        Site::Site();
        $this->amenity_list = array();
        $this->site_num = $num;
        $this->site_name = $name;
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
     * District/Region Functions
     ********************************************************************/
    function get_region_num()
    {
        return $this->district->region->get_number();
    }
    function get_region_name()
    {
        return $this->district->region->get_name();
    }
    function get_rm()
    {
        return $this->district->region->get_mgr();
    }
    function get_district_num()
    {
        return $this->district->get_number();
    }
    function get_district_name()
    {
        return $this->district->get_name();
    }
    function get_dm()
    {
        return $this->district->get_mgr();
    }
    function set_district($d)
    {
        $this->district = $d;
    }
    /*********************************************************************
     * END District/Region Functions
     ********************************************************************/
    
    
    
    /*********************************************************************
     * Asst Mgr List Functions
     ********************************************************************/
    // Get the array of asst mgr emp ids
    function get_asst_mgr_list()
    {
        return $this->asst_mgr_list;
    }
    
    // If the provided asst mgr is not already in the list, add it.
    function add_asst_mgr($emp_id)
    {
        if((!$this->asst_mgr_exists($emp_id)) && (sizeof($this->asst_mgr_list) < 4))
        {
            array_push($this->asst_mgr_list, $emp_id);
        }
    }
    
    // Remove the given asst mgr id from the list.
    function remove_asst_mgr($emp_id)
    {
        foreach($this->asst_mgr_list AS $key=>$val)
        {
            if($val != $emp_id)
                $tmp[$key] = $val;
        }
        $this->asst_mgr_list = $tmp;
    }
    
    // Does this emp_id exist in the asst_mgr_list?
    function asst_mgr_exists($emp_id)
    {
        $return = false;
        foreach($this->asst_mgr_list AS $key=>$val)
        {
            if($val == $emp_id)
                $return = true;
        }
        return $return;
    }
    /*********************************************************************
     * END Asst Mgr List Functions
     ********************************************************************/
    
    /*********************************************************************
     * Amenity List Functions
     ********************************************************************/
    // Add the amenity to the amenity array.
    // REQUIRE: This amenity name does not already exist
    function add_amenity($name, $allowed)
    {
        $allowed = (strcmp($allowed, "true") == 0) ? 1 : 0;
        $amen = new Amenity($name, $allowed);
        array_push($this->amenity_list, $amen);
    }
    function get_amenity($name)
    {
        $toReturn = "n/a";
        foreach($this->amenity_list AS $key=>$val)
        {
            if(strcmp($val->name, $name) == 0)
                $toReturn = $val->allowed;
        }
        return $toReturn;
    }
    /*
    // Remove the amenity from the amenity array.
    function remove_amenity($name)
    {
        foreach($this->amenity_list AS $key=>$val)
        {
            if(strcmp($val->name, $name) != 0)
                $tmp[$key] = $val;
        }
        $this->amenity_list = $tmp;
    }
    // The amenity is already in the amenity array.
    // Change the value.
    function update_amenity_status($name, $allowed)
    {
        $this->amenity_list[$name]->set_allowed($allowed);
    }
    // Is this amenity in the list defined for this store?
    function amenity_exists($name)
    {
        $return = false;
        foreach($this->amenity_list AS $key=>$val)
        {
            if(strcmp($val->name, $name) == 0)
                $return = true;
        }
        return $return;
    }
    */
    /*********************************************************************
     * END Amenity List Functions
     ********************************************************************/
    
    
    /*******************************************************************
    * Amenity DB Functions
    ********************************************************************/
    // Insert the amenities into the db for this store.
    function db_insert_amenities()
    {
        $dbmgr = new DB_Mgr();
        $stmt = "INSERT INTO SI_AMENITY VALUES ("
              . $this->site_num . ", "
              . $this->get_amenity("smoking") . ", "
              . $this->get_amenity("alcohol") . ", "
              . $this->get_amenity("gaming") . ");";
        return $dbmgr->query($stmt);
    }
    
    // Update the amenities in the db for this store.
    function db_update_amenity()
    {
        $dbmgr = new DB_Mgr();
        $stmt = "UPDATE SI_AMENITY SET "
              . "smoking = " . $this->get_amenity("smoking") . ", "
              . "alcohol = " . $this->get_amenity("alcohol") . ", "
              . "gaming = " . $this->get_amenity("gaming") . " "
              . "WHERE loc_num = $loc_num";
        return $dbmgr->query($stmt);
    }
    
    // Load the amenities from the db for this store.
    function db_load_amenity()
    {
        $dbmgr = new DB_Mgr();
        $stmt = "SELECT smoking, alcohol, gaming FROM SI_AMENITY WHERE "
              . " loc_num = " . $this->site_num;
        $dbmgr->query($stmt);
        $rs = $dbmgr->get_a_row();
        $smok = new Amenity("smoking", $rs[0]);
        $alco = new Amenity("alcohol", $rs[1]);
        $gami = new Amenity("gaming" , $rs[2]);
        $this->amenity_list = array($smok, $alco, $gami);
        return $toReturn;
    }
    /*******************************************************************
    * END Amenity DB Functions
    ********************************************************************/
    /*******************************************************************
    * Store Contact DB Functions
    ********************************************************************/
    function db_insert_contacts()
    {
        
        $dbmgr = new DB_Mgr();
        $stmt = "INSERT INTO SI_SITE_CONTACT VALUES ("
              . $this->site_num . ", "
              . $this->mgr_emp_id . ", "
              . $this->asst_mgr_list[0] . ", "
              . $this->asst_mgr_list[1] . ", "
              . $this->asst_mgr_list[2] . ", "
              . $this->asst_mgr_list[3] . ")";
        return $dbmgr->query($stmt);
    }
    function db_load_contacts()
    {
        $dbmgr = new DB_Mgr();
        $stmt = "SELECT * FROM SI_SITE_CONTACT WHERE loc_num = " . $this->site_num;
        $dbmgr->query($stmt);
        $row = $dbmgr->get_a_row();
        $this->mgr_emp_id = $row[0];
        for($i=0;$i<4;$i++)
        {
            $this->asst_mgr_list[$i] = $row[$i+1];
        }
    }
    function db_update_contacts()
    {
        $dbmgr = new DB_Mgr();
        $stmt = "UPDATE SI_SITE_CONTACT SET "
              . "mgr_emp_id = " . $this->mgr_emp_id . ", "
              . "asst_mgr_1 = " . $this->asst_mgr_list[0] . ", "
              . "asst_mgr_2 = " . $this->asst_mgr_list[1] . ", "
              . "asst_mgr_3 = " . $this->asst_mgr_list[2] . ", "
              . "asst_mgr_4 = " . $this->asst_mgr_list[3] . " "
              . "WHERE loc_num = " . $this->site_num;
        return $dbmgr->query($stmt);
    }
    /*******************************************************************
    * END Store Contact DB Functions
    ********************************************************************/
    
    /*******************************************************************
    * Territory DB Functions
    ********************************************************************/
    function db_insert_territory()
    {
        $dbmgr = new DB_Mgr();
        $stmt = "INSERT INTO SI_TERRITORY VALUES("
              . $this->district->region->number . ", "
              . $this->district->number . ", "
              . $this->site_num . ");";
        return $dbmgr->query($stmt);
    }
    function db_update_territory()
    {
        $dbmgr = new DB_Mgr();
        $stmt = "UPDATE SI_TERRITORY SET "
              . "region_num = " . $this->district->region->number . ", "
              . "district_num = " . $this->district->number . " "
              . "WHERE loc_num = " . $this->site_num . ";";
        return $dbmgr->query($stmt);
    }
    function db_load_territory()
    {
        $dbmgr = new DB_Mgr();
        $stmt = "LOAD region_num, district_num FROM SI_TERRITORY "
              . "WHERE loc_num = " . $this->site_num;
        $dbmgr->query($stmt);
        $row = $dbmgr->get_a_row();
        $this->$district->region->set_number($row[0]);
        $this->$district->set_number($row[1]);
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
        $dbmgr = new DB_Mgr();
        
        // INSERT INTO SI_SITE
        $stmt = "INSERT INTO SI_SITE VALUES("
              . $this->site_num . ", '"
              . $this->site_name . "', '"
              . $this->address->description . "', '"
              . $this->address->street . "', '"
              . $this->address->city . "', '"
              . $this->address->state . "', "
              . $this->address->zip . ", "
              . $this->sysco_num . ", '" 
              . $this->phone_system->company->id . "', '"
              . $this->date_active . "', "
              . $this->active . ");";
        //return($stmt);
        if(!($dbmgr->execute($stmt)))
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
        if(!($dbmgr->execute($stmt)))
        {
            // INSERT FAILED.
            return ("Error in SI_SITE_CONTACT insert");
        }
        
        // INSERT INTO SI_AMENITY
        $stmt = "INSERT INTO SI_AMENITY VALUES("
                . $this->site_num . ", "
                . $this->get_amenity("smoking") . ", "
                . $this->get_amenity("alcohol") . ", "
                . $this->get_amenity("gaming") . ");";
        if(!($dbmgr->execute($stmt)))
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
        if(!($dbmgr->execute($stmt)))
        {
            // INSERT FAILED.
            return ("Error in SI_TERRITORY insert");
        }
        return(true);
    }
    /*
    function db_update_site()
    {
        $dbmgr = new DB_Mgr();
        $stmt = "UPDATE SI_SITE SET "
              . "loc_name_1 = " . $this->site_name . ", "
              . "loc_name_2 = " . $this->address->description . ", "
              . "address = " . $this->address->street . ", "
              . "city = " . $this->address->city . ", "
              . "state = " . $this->address->state . ", "
              . "zip = " . $this->address->zip . ", "
              . "sysco_# = " . $this->sysco_num . ", " 
              . "phone_co_id = " . $this->phone_system->company_id . ", "
              . "date_active = " . $this->date_active . ", "
              . "active = " . $this->active . " "
              . "where loc_num = " . $this->site_num . ";";
        return $dbmgr->query($stmt);
    }
    */
    /*******************************************************************
    * END Site DB Functions
    ********************************************************************/
}
?>