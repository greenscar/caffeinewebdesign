<?php
require_once("DB_Mgr.inc");
require_once("Address.inc");
require_once("Phone_System.inc");
class Site
{
    // The site number
    var $site_num;
    // The site name (i.e. MIS)
    var $site_name;
    // The general manager's emp id
    var $mgr_emp_id;
    // The date the store went active.
    var $date_active;
    // The address object
    var $address;
    // Is this site active?
    var $active;
    // The phone system object.
    var $phone_system;
    
    function Site()
    {
        $this->phone_system = new Phone_System();
    }
    
    function set_date_active($date)
    {
        // $date is in format XX/XX/XXXX
        
        //$dt = $year . "-" . $month . "-" . $day . " 00:00:00 GMT";
        $dt = $date . " 00:00:00 GMT";
        //Convert the date to a unix timestamp
        $ts = strtotime($dt);
        //return($ts);
        // Create the date in mssql datetime notation.
        $this->date_active = date("n/j/Y g:i:s A", $ts);
    }
    /*********************************************************************
     * Phone Functions
     ********************************************************************/
    
    // has_line_type returns true if this site has the type of line provided
    //    and false if it does not.
    function has_line_type($name)
    {
        return $this->phone_system->line_exists($name);
    }
    
    // Get the phone number of the provided name.
    function get_line_number($name)
    {
        return $this->phone_system->get_line_number();
    }
    
    // Add the given phone type with the given number.
    function add_line($name, $number)
    {
        if($this->phone_system->line_exists($name))
        {
            return("Error. That line already exists.");
        }
        else
        {
            return $this->phone_system->add_line($name, $number);
        }
    }
    
    //Remove the phone line with the provided name.
    function remove_line($name)
    {
        $this->phone_system->remove_line($name);
    }
    /*********************************************************************
     * END Phone Functions
     ********************************************************************/
    
    /*******************************************************************
    * Site Contact DB Functions
    ********************************************************************/
    function db_insert_contacts()
    {
        
        $dbmgr = new DB_Mgr();
        $stmt = "INSERT INTO SI_SITE_CONTACT VALUES ("
              . $this->site_num . ", "
              . $this->mgr_emp_id . ", 0, 0, 0, 0";
        return $dbmgr->query($stmt);
    }
    function db_load_contacts()
    {
        $dbmgr = new DB_Mgr();
        $stmt = "SELECT mgr_emp_id FROM SI_SITE_CONTACT WHERE loc_num = " . $this->site_num;
        $dbmgr->query($stmt);
        $row = $dbmgr->get_a_row();
        $this->mgr_emp_id = $row[0];
    }
    function db_update_contacts()
    {
        $dbmgr = new DB_Mgr();
        $stmt = "UPDATE SI_SITE_CONTACT SET "
              . "mgr_emp_id = " . $this->mgr_emp_id . " "
              . "WHERE loc_num = " . $this->site_num;
        return $dbmgr->query($stmt);
    }
    /*******************************************************************
    * END Site Contact DB Functions
    ********************************************************************/
    
    /*******************************************************************
    * Site DB Functions
    ********************************************************************/
    function db_insert_site()
    {
        $dbmgr = new DB_Mgr();
        $stmt = "INSERT INTO SI_SITE VALUES("
              . $this->site_num . ", "
              . $this->site_name . ", "
              . $this->address->description . ", "
              . $this->address->street . ", "
              . $this->address->city . ", "
              . $this->address->state . ", "
              . $this->address->zip . ", "
              . "0, " //E-Sysco Acct Num
              . $this->phone_system->company . ", "
              . $this->date_active . ", "
              . $this->active . ");";
        return $dbmgr->query($stmt);
    }
    
    /*******************************************************************
    * END Site DB Functions
    ********************************************************************/
}
?>