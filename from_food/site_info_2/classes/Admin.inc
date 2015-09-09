<?php
require_once("Employee.inc");
require_once("Store.inc");
require_once("Scribe.inc");
include("../../php_objects/Settings.inc");
class Admin
{
    // Array of Regions
    var $regions;
    // Array of Districts
    var $districts;
    // DB Mgr
    var $dbmgr;
    // Array of Sites
    var $sites;
    // Phone System Object
    var $phone_system;
    // Employee List
    var $emps;
    
    // The Logger
    var $scribe;
    
    var $settings;
    
    function Admin()
    {
        $this->phone_system = new Phone_System();
        $this->settings = new Settings();
        $this->scribe = new Scribe();
    }
    
    /********************************************************************
     * Store List Functions
     ********************************************************************/
    function db_load_site_list($order_by="dept_id")
    {
        //$this->scribe->log("Admin->db_load_site_list($order_by)");
        $this->sites = array();
        //$settings = new Settings();
        $cnx = odbc_connect($this->settings->db["web"], $this->settings->id["web"], $this->settings->pwd["web"]);
        $select = "SELECT s.dept_id, s.site_name, s.loc_name,"
              . "a.street, a.city, a.state, a.zip"
              . " FROM SI_SITE s, SI_ADDRESS a"
              . " WHERE a.address_id = s.address_id"
              . " AND s.active = 1";
        //$this->scribe->log($select);
        $rs = odbc_exec($cnx, $select);
        while(odbc_fetch_row($rs))
        {
            $s = new Store(odbc_result($rs,1), stripslashes(odbc_result($rs,2)));
            $s->address->description = stripslashes(odbc_result($rs,3));
            $s->address->street = odbc_result($rs,4);
            $s->address->city = stripslashes(odbc_result($rs,5));
            $s->address->state = odbc_result($rs,6);
            $s->address->zip = odbc_result($rs,7);            
            array_push($this->sites, $s);
        }
        odbc_free_result($rs);
        odbc_close_all();
        //$this->scribe->log("returning " . sizeof($this->sites) . " sites");
        //$this->scribe->log_end_of_fxn();
        return sizeof($this->sites);
    }
    function get_a_site_from_list($array_loc)
    {
        if($array_loc < sizeof($this->sites))
            return($this->sites[$array_loc]);
        else
            return null;
    }
    function site_list_size()
    {
        return sizeof($this->sites);
    }
    
    function db_load_detailed_site_list($order_by="dept_id")
    {
        $this->scribe->log("db_load_detailed_site_list($order_by)");
        $this->sites = array();
        
        $si_cnx = odbc_connect($this->settings->db["web"], $this->settings->id["web"], $this->settings->pwd["web"]);
        $ps_cnx = odbc_connect($this->settings->db["pstest"], $this->settings->id["pstest"], $this->settings->pwd["pstest"]);
                
        $select = "SELECT dept_id, site_name FROM SI_SITE WHERE active = 1 ORDER BY $order_by";
        $this->scribe->log($select);
        $rs = odbc_exec($si_cnx, $select);
        $toReturn = "";
        $i = 1;
        while(odbc_fetch_row($rs))
        {
            $s = new Store(odbc_result($rs,1), stripslashes(odbc_result($rs,2)));
            $s->db_load_all_info($si_cnx, $ps_cnx);
            array_push($this->sites, $s);
        }
        odbc_close_all();
        //$this->scribe->log_end_of_fxn();
    }
    
    function db_load_detailed_store_list($order_by="dept_id")
    {
        $this->scribe->log("db_load_detailed_site_list($order_by)");
        $this->sites = array();
        
        $si_cnx = odbc_connect($this->settings->db["web"], $this->settings->id["web"], $this->settings->pwd["web"]);
        $ps_cnx = odbc_connect($this->settings->db["pstest"], $this->settings->id["pstest"], $this->settings->pwd["pstest"]);
                
        $select = "SELECT dept_id, site_name FROM SI_SITE WHERE active = 1 AND dept_id > 119 ORDER BY $order_by";
        $this->scribe->log($select);
        $rs = odbc_exec($si_cnx, $select);
        $toReturn = "";
        $i = 1;
        while(odbc_fetch_row($rs))
        {
            $s = new Store(odbc_result($rs,1), stripslashes(odbc_result($rs,2)));
            $s->db_load_all_info($si_cnx, $ps_cnx);
            array_push($this->sites, $s);
        }
        odbc_close_all();
        //$this->scribe->log_end_of_fxn();
    }
    /********************************************************************
     * END Store List Functions
     ********************************************************************/
    
    /********************************************************************
     * Phone System Functions
     ********************************************************************/
    
    function db_load_phone_type_list()
    {
        $si_cnx = odbc_connect($this->settings->db["web"], $this->settings->id["web"], $this->settings->pwd["web"]);
        $toReturn = $this->phone_system->db_load_phone_type_list($si_cnx);
        odbc_close_all();
        return $toReturn;
    }
    function get_a_phone_type_from_list($array_loc){
        return($this->phone_system->get_a_phone_type_from_list($array_loc));
    }
    function phone_type_list_size()
    {
        return $this->phone_system->phone_type_list_size();
    }
    
    function db_load_phone_co_list()
    {
        $si_cnx = odbc_connect($this->settings->db["web"], $this->settings->id["web"], $this->settings->pwd["web"]);
        $toReturn = $this->phone_system->db_load_phone_co_list($si_cnx);
        odbc_close_all();
        return $toReturn;
    }
    function get_a_phone_co_from_list($array_loc){
        return($this->phone_system->get_a_phone_co_from_list($array_loc));
    }
    function phone_co_list_size()
    {
        return $this->phone_system->phone_co_list_size();
    }
    
        
    /********************************************************************
     * END Phone System Functions
     ********************************************************************/
    
    function db_load_store($num)
    {
        $si_cnx = odbc_connect($this->settings->db["web"], $this->settings->id["web"], $this->settings->pwd["web"]);
        $ps_cnx = odbc_connect($this->settings->db["pstest"], $this->settings->id["pstest"], $this->settings->pwd["pstest"]);
        $store = new Store($num, "");
        $store->db_load_all_info($si_cnx, $ps_cnx);
        odbc_close_all();
        return $store;
    }
    /********************************************************************
     * Emp ID Lookup Functions
     ********************************************************************/
    /*
    function get_an_emp_from_list($array_loc){
        return($this->emps[$array_loc]);    
    }
    function emp_list_size()
    {
        return sizeof($this->emps);
    }
    */
    function process_store_mod($store, $_POST)
    {
        $si_cnx = odbc_connect($this->settings->db["web"], $this->settings->id["web"], $this->settings->pwd["web"]);
        $ps_cnx = odbc_connect($this->settings->db["pstest"], $this->settings->id["pstest"], $this->settings->pwd["pstest"]) or die("Error connecting to ps");
        // CREATE NEW STORE OBJECT WITH NUMBER AND NAME
        $store->site_num = $_POST["store_number"];
        $store->site_name = stripslashes($_POST["site_name"]);
        
        // SET DATE OPENED
        $store->date_active = $_POST["date"];
        
        // SET STORE ACTIVE
        $store->active = $_POST["active"];
        
        // SET ADDRESS VALUES
        $store->address = new Address(stripslashes($_POST["street"]), stripslashes($_POST["city"]), $_POST["state"], $_POST["zip"], stripslashes($_POST["address_description"]));
        
        // SET SYSCO ACCT NUM
        $store->sysco_num = $_POST["sysco_num"];
        
        // SET PHONE COMPANY
        $store->set_phone_company_id($_POST["phone_co"]);
        
        
        // SET PHONE LINES
        $store->remove_all_lines();
        foreach ($_POST as $key => $val) 
        {
            if(strcmp(substr($key, 0, 4), "phn_") == 0)
            {
                if($val != "")
                {
                    $type_id = substr($key, 4, (strlen($key) - 1));
                    $store->phone_system->add_line($type_id, $val);  
                }
            }
        }
        
        // SET AMENITIES
        $store->remove_all_amenities();
        foreach ($_POST as $key => $val)
        {
            $val = trim($val);
            if(strcmp(substr($key, 0, 5), "amen_") == 0)
            {
                $id = substr($key, 5, (strlen($key) - 1));
                $store->add_amenity($id, "", $val);
            }
        }
        
        // SET MGR & ASST MGR NICKNAMES
        // Set mgr nick
        //$mgr = $store->manager;
        //$mgr->first_name = $_POST["name_mgr"];
        //return $mgr->first_name;
        // Set asst mgr nicks
        $store->set_mgr_first_name($_POST["name_mgr"]);
        //$toReturn = "name_mgr = " . $_POST["name_mgr"] . "<BR>";
        //$toReturn .= "mgr = " . $store->manager->first_name . "<BR>";
        for($i=0; $i<sizeof($store->asst_mgr_list); $i++)
        {
            $post_name = "am_" . $i;
            $store->set_asst_mgr_first_name($i, $_POST[$post_name]);
            //$am = $store->asst_mgr_list[$i];
            //$am->first_name = $_POST[$post_name];
            //$toReturn .= $post_name . " = " . $_POST[$post_name] . "<BR>";
        }
        //return $toReturn;
        /********************************************************
         * END Create a Store with the POST data
         *******************************************************/
        
        /********************************************************
         * Insert this store into the DB.
         *******************************************************/
        //echo($store->display_info());
        //echo("UPDATE = " .$store->db_update_store());
        odbc_close_all();
        return($store->db_update_store($si_cnx, $ps_cnx));
    }
    
    function get_store_menu($title, $target, $order_by="site_name")
    {
        $this->db_load_site_list($order_by);
        
        $str = "<!--\n\tDISPLAY THE LIST OF STORES\n-->\n"
        . "<div class=\"center\">\n"
        . "\t<div class=\"grey_box_90\">\n"
        . "\t<div class=\"spacer\"></div>\n"
        . "\t<span class=\"white_center\">\n"
        . "\t\t<h2>$title</h2>\n"
        . "\t</span>\n"
        . "\t<table width=\"95%\" border=\"0\">\n"
        . "\t\t<tr>\n"
        . "\t\t\t<td width=\"10%\" class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n"
        . "\t\t\t\t<span class=\"white_center\">\n"
        . "\t\t\t\t<a href=\"$target?ob=dept_id\" class=\"sl_header\">Number</a>\n"
        . "\t\t\t\t</span>\n"
        . "\t\t\t</td>\n"
        . "\t\t\t<td width=\"20%\" class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n"
        . "\t\t\t\t<span class=\"white_center\">\n"
        . "\t\t\t\t<a href=\"$target?ob=site_name\" class=\"sl_header\">Name</a>\n"
        . "\t\t\t\t</span>\n"
        . "\t\t\t</td>\n"
        . "\t\t\t<td width=\"20%\" class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n"
        . "\t\t\t\t<span class=\"white_center\">\n"
        . "\t\t\t\t<a href=\"$target?ob=address\" class=\"sl_header\">Street</a>\n"
        . "\t\t\t\t</span>\n"
        . "\t\t\t</td>\n"
        . "\t\t\t<td width=\"20%\" class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n"
        . "\t\t\t\t<span class=\"white_center\">\n"
        . "\t\t\t\t<a href=\"$target?ob=city\" class=\"sl_header\">City</a>\n"
        . "\t\t\t\t</span>\n"
        . "\t\t\t</td>\n"
        . "\t\t\t<td width=\"5%\" class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n"
        . "\t\t\t\t<span class=\"white_center\">\n"
        . "\t\t\t\t<a href=\"$target?ob=state\" class=\"sl_header\">State</a>\n"
        . "\t\t\t\t</span>\n"
        . "\t\t\t</td>\n"
        . "\t\t\t<td width=\"5%\" class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n"
        . "\t\t\t\t<span class=\"white_center\">\n"
        . "\t\t\t\t<a href=\"$target?ob=zip\" class=\"sl_header\">Zip</a>\n"
        . "\t\t\t\t</span>\n"
        . "\t\t\t</td>\n"
        
        . "\t\t</tr>\n";
        for($i=0; $i < $this->site_list_size(); $i++)
        {
            $site = $this->get_a_site_from_list($i);
            $str .= "\t\t<tr>\n";
            if($i % 2 == 1)
                $str .= "\t\t\t<td class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n";
            else
                $str .= "\t\t\t<td class=\"black_on_white\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='black_on_white'\">\n";
            
            $str .= "\t\t\t\t<span class=\"white_center\">\n";
            $str .= "\t\t\t\t\t";
            $str .= "\t<a class=\"white_center\" href=\"$target?sn=" . $site->site_num . "\">\n";
            $str .= $site->site_num;
            $str .= "\n";
            $str .= "\t</a>\n";
            $str .= "\t\t\t\t</span>\n";
            $str .= "\t\t\t</td>\n";
            
            if($i % 2 == 1)
                $str .= "\t\t\t<td class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n";
            else
                $str .= "\t\t\t<td class=\"black_on_white\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='black_on_white'\">\n";
            
            $str .= "\t\t\t\t<span class=\"white_center\">\n";
            $str .= "\t\t\t\t\t";
            $str .= "\t<a class=\"white_center\" href=\"$target?sn=" . $site->site_num . "\">\n";
            $str .= $site->site_name;
            $str .= "\n";
            $str .= "\t</a>\n";
            $str .= "\t\t\t\t</span>\n";
            $str .= "\t\t\t</td>\n";
            
            if($i % 2 == 1)
                $str .= "\t\t\t<td class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n";
            else
                $str .= "\t\t\t<td class=\"black_on_white\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='black_on_white'\">\n";
            
            $str .= "\t\t\t\t<span class=\"white_center\">\n";
            $str .= "\t\t\t\t\t";
            $str .= "\t<a class=\"white_center\" href=\"$target?sn=" . $site->site_num . "\">\n";
            $str .= $site->address->street;
            $str .= "\n";
            $str .= "\t</a>\n";
            $str .= "\t\t\t\t</span>\n";
            $str .= "\t\t\t</td>\n";
       
            if($i % 2 == 1)
                $str .= "\t\t\t<td class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n";
            else
                $str .= "\t\t\t<td class=\"black_on_white\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='black_on_white'\">\n";
            
            $str .= "\t\t\t\t<span class=\"white_center\">\n";
            $str .= "\t\t\t\t\t";
            $str .= "\t<a class=\"white_center\" href=\"$target?sn=" . $site->site_num . "\">\n";
            $str .= $site->address->city;
            $str .= "\n";
            $str .= "\t</a>\n";
            $str .= "\t\t\t\t</span>\n";
            $str .= "\t\t\t</td>\n";
       
            if($i % 2 == 1)
                $str .= "\t\t\t<td class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n";
            else
                $str .= "\t\t\t<td class=\"black_on_white\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='black_on_white'\">\n";
            
            $str .= "\t\t\t\t<span class=\"white_center\">\n";
            $str .= "\t\t\t\t\t";
            $str .= "\t<a class=\"white_center\" href=\"$target?sn=" . $site->site_num . "\">\n";
            $str .= $site->address->state;
            $str .= "\n";
            $str .= "\t</a>\n";
            $str .= "\t\t\t\t</span>\n";
            $str .= "\t\t\t</td>\n";
       
            if($i % 2 == 1)
                $str .= "\t\t\t<td class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n";
            else
                $str .= "\t\t\t<td class=\"black_on_white\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='black_on_white'\">\n";
            
            $str .= "\t\t\t\t<span class=\"white_center\">\n";
            $str .= "\t\t\t\t\t";
            $str .= "\t<a class=\"white_center\" href=\"$target?sn=" . $site->site_num . "\">\n";
            $str .= $site->address->zip;
            $str .= "\n";
            $str .= "\t</a>\n";
            $str .= "\t\t\t\t</span>\n";
            $str .= "\t\t\t</td>\n";
            $str .= "\t\t</tr>\n";
        }
        $str .= "</table>\n";
        $str .= "</div>\n</div>\n";
        $str .= "</div>\n";
        return $str;
    }
    
}
?>