<?php
require_once("Employee.inc");
require_once("Store.inc");
require_once("Secretary.inc");
require_once("../../../php_objects/DB_Mgr.inc");
//require_once("Settings.inc");
class Admin
{
    // Array of Regions
    var $regions;
    // Array of Districts
    var $districts;
    // DB Mgr
    var $dbmgr_web;
    var $dbmgr_ps;
    // Array of Sites
    var $sites;
    // Phone System Object
    var $phone_system;
    // Employee List
    var $emps;
    
    // The Logger
    var $log;
    
    var $settings;
    
    function Admin()
    {
        $this->log = new Secretary();
        $this->phone_system = new Phone_System();
        $this->settings = new Settings();
        $this->dbmgr_web = new DB_Mgr("web");
        $this->log->write("web dbmgr => " . $this->dbmgr_web->get_conn());
        $this->dbmgr_ps = new DB_Mgr("pstest");
        $this->log->write("ps dbmgr => " . $this->dbmgr_ps->get_conn());
    }
    
    /********************************************************************
     * Store List Functions
     ********************************************************************/
    function db_load_site_list($order_by="dept_id")
    {
        $this->log->write("Admin->db_load_site_list($order_by) => START");
        $this->sites = array();
        $settings = new Settings();
        //$cnx = odbc_connect($this->settings->db["WEB"], $this->settings->id["WEB"], $this->settings->pwd["WEB"]);
        $select = "SELECT s.dept_id, s.site_name, s.loc_name,"
              . "a.street, a.city, a.state, a.zip"
              . " FROM SI_SITE s, SI_ADDRESS a"
              . " WHERE a.address_id = s.address_id"
              . " AND s.active = 1";
        $this->log->write($select);
        $rs = $this->dbmgr_web->query($select);
        while(odbc_fetch_into($rs, $row))
        {
            //$temp .= "hello<br>";
            $s = new Store($row[0], stripslashes($row[1]));
            $s->address->description = stripslashes($row[2]);
            $s->address->street = $row[3];
            $s->address->city = stripslashes($row[4]);
            $s->address->state = $row[5];
            $s->address->zip = $row[6];            
            array_push($this->sites, $s);
            $this->log->write($s->display_info());
        }
        $this->dbmgr_web->free_result();
        $this->log->write("returning " . sizeof($this->sites) . " sites");
        //$this->log->log_end_of_fxn();
        //return $temp;
        $this->log->write("Admin->db_load_site_list($order_by) => END");
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
        $this->log->write("db_load_detailed_site_list($order_by)");
        $this->sites = array();
        $select = "SELECT dept_id, site_name FROM SI_SITE WHERE active = 1 ORDER BY $order_by";
        //$this->log->write($select);
        $rs = $this->dbmgr_web->query($select);
        $toReturn = "";
        $i = 1;
        while(odbc_fetch_into($rs, $row))
        {
            $s = new Store($row[1], stripslashes($row[2]));
            $s->db_load_all_info();
            array_push($this->sites, $s);
        }
        $this->dbmgr_web->free_result();
        $this->log->log_end_of_fxn();
    }
    
    function db_load_detailed_store_list($order_by="dept_id")
    {
        $this->log->write("db_load_detailed_site_list($order_by)");
        $this->sites = array();
        $select = "SELECT dept_id, site_name FROM SI_SITE WHERE active = 1 AND dept_id > 119 ORDER BY $order_by";
        $toReturn = "";
        $i = 1;
        /*
        * REDO OR DELETE
        */ 
        $rs = $this->dbmgr_web->query($select);
        while(odbc_fetch_into($rs, $row))
        {
            $s = new Store($row[1], stripslashes($row[2]));
            $s->db_load_all_info();
            array_push($this->sites, $s);
        }
        $this->dbmgr_web->free_result();
        $this->log->log_end_of_fxn();
    }
    /********************************************************************
     * END Store List Functions
     ********************************************************************/
    
    /********************************************************************
     * Phone System Functions
     ********************************************************************/
    
    function db_load_phone_type_list()
    {
        //$dbmgr_web = odbc_connect($this->settings->db["WEB"], $this->settings->id, $this->settings->pwd);
        return($this->phone_system->db_load_phone_type_list());
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
        //$dbmgr_web = odbc_connect($this->settings->db["WEB"], $this->settings->id, $this->settings->pwd);
        return($this->phone_system->db_load_phone_co_list());
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
        /*
        $dbmgr_web = odbc_connect($this->settings->db["WEB"], $this->settings->id, $this->settings->pwd);
        $ps_cnx = odbc_connect($this->settings->db["PSTEST"], $this->settings->id, $this->settings->pwd);
        */
        $this->log->write("Admin->db_load_store($num) START");
        $store = new Store($num, "");
        $store->db_load_all_info();
        $this->log->write("Admin->db_load_store($num) END");
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
        /*
        $dbmgr_web = odbc_connect($this->settings->db["WEB"], $this->settings->id, $this->settings->pwd);
        $ps_cnx = odbc_connect($this->settings->db["PSTEST"], $this->settings->id, $this->settings->pwd) or die("Error connecting to ps");
        */
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
        return($store->db_update_store($this->dbmgr_web, $this->dbmgr_ps));
    }
    
    function get_store_menu($title, $target, $order_by="site_name")
    {
        $this->log->write("Admin->get_store_menu($title, $target, $order_name) START");
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
            /*
            if($i % 2 == 1)
                $str .= "\t\t\t<td class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n";
            else
                $str .= "\t\t\t<td class=\"black_on_white\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='black_on_white'\">\n";
            */
            $str .= "<td>";
            $str .= "\t\t\t\t<span class=\"white_center\">\n";
            $str .= "\t\t\t\t\t";
            $str .= "\t<a class=\"white_center\" href=\"$target?sn=" . $site->site_num . "\">\n";
            $str .= $site->site_num;
            $str .= "\n";
            $str .= "\t</a>\n";
            $str .= "\t\t\t\t</span>\n";
            $str .= "\t\t\t</td>\n";
            /*
            if($i % 2 == 1)
                $str .= "\t\t\t<td class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n";
            else
                $str .= "\t\t\t<td class=\"black_on_white\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='black_on_white'\">\n";
            */
            $str .= "<td>";
            $str .= "\t\t\t\t<span class=\"white_center\">\n";
            $str .= "\t\t\t\t\t";
            $str .= "\t<a class=\"white_center\" href=\"$target?sn=" . $site->site_num . "\">\n";
            $str .= $site->site_name;
            $str .= "\n";
            $str .= "\t</a>\n";
            $str .= "\t\t\t\t</span>\n";
            $str .= "\t\t\t</td>\n";
            /*
            if($i % 2 == 1)
                $str .= "\t\t\t<td class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n";
            else
                $str .= "\t\t\t<td class=\"black_on_white\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='black_on_white'\">\n";
            */
            $str .= "<td>";
            $str .= "\t\t\t\t<span class=\"white_center\">\n";
            $str .= "\t\t\t\t\t";
            $str .= "\t<a class=\"white_center\" href=\"$target?sn=" . $site->site_num . "\">\n";
            $str .= $site->address->street;
            $str .= "\n";
            $str .= "\t</a>\n";
            $str .= "\t\t\t\t</span>\n";
            $str .= "\t\t\t</td>\n";
            /*
            if($i % 2 == 1)
                $str .= "\t\t\t<td class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n";
            else
                $str .= "\t\t\t<td class=\"black_on_white\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='black_on_white'\">\n";
            */
            $str .= "<td>";
            $str .= "\t\t\t\t<span class=\"white_center\">\n";
            $str .= "\t\t\t\t\t";
            $str .= "\t<a class=\"white_center\" href=\"$target?sn=" . $site->site_num . "\">\n";
            $str .= $site->address->city;
            $str .= "\n";
            $str .= "\t</a>\n";
            $str .= "\t\t\t\t</span>\n";
            $str .= "\t\t\t</td>\n";
            /*
            if($i % 2 == 1)
                $str .= "\t\t\t<td class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n";
            else
                $str .= "\t\t\t<td class=\"black_on_white\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='black_on_white'\">\n";
            */
            $str .= "<td>";
            $str .= "\t\t\t\t<span class=\"white_center\">\n";
            $str .= "\t\t\t\t\t";
            $str .= "\t<a class=\"white_center\" href=\"$target?sn=" . $site->site_num . "\">\n";
            $str .= $site->address->state;
            $str .= "\n";
            $str .= "\t</a>\n";
            $str .= "\t\t\t\t</span>\n";
            $str .= "\t\t\t</td>\n";
            /*
            if($i % 2 == 1)
                $str .= "\t\t\t<td class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n";
            else
                $str .= "\t\t\t<td class=\"black_on_white\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='black_on_white'\">\n";
            */
            $str .= "<td>";
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
        $this->log->write("Admin->get_store_menu($title, $target, $order_name) END");
        return $str;
    }
    
}
?>