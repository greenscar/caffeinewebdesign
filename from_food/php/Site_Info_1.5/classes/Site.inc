<?php
require_once("DB_Mgr.inc");
require_once("Address.inc");
require_once("Phone_System.inc");
require_once("Employee.inc");
class Site
{
    // The site number
    var $site_num;
    // The site name (i.e. MIS)
    var $site_name;
    // The general manager
    var $manager;
    // array of Employee Objects.
    var $asst_mgr_list = array();
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
        $this->settings = new Settings();
        $this->phone_system = new Phone_System();
    }
    
    function get_date_active_for_mssql()
    {
        $dt = $this->date_active . " 08:00:00 GMT";
        //Convert the date to a unix timestamp
        $ts = strtotime($dt);
        // Create the date in mssql datetime notation.
        return(date("n/j/Y g:i:s A", $ts));
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
    
    function get_num_phone_lines()
    {
        return $this->phone_system->line_list_size();
    }
    
    function get_number_via_name($name)
    {
        return $this->phone_system->get_number_via_name($name);
    }
    
    
    function get_line_number($array_loc)
    {
        $line = $this->phone_system->get_line($array_loc);
        return $line->number;
    }
    function get_line_type_name($array_loc)
    {
        $line = $this->phone_system->get_line($array_loc);
        return $line->type->name;
    }
    // Add the given phone type with the given number.
    function add_line($type_id, $number)
    {
        if($this->phone_system->line_exists($type_id))
        {
            return("Error. That line already exists.");
        }
        else
        {
            return $this->phone_system->add_line($type_id, $number);
        }
    }
    
    //Remove the phone line with the provided name.
    function remove_line($name)
    {
        $this->phone_system->remove_line($name);
    }
    // Delete all phone lines from this site.
    function remove_all_lines()
    {
        $this->phone_system->remove_all_lines();
    }
    
    
    function set_phone_company_name($name)
    {
        $this->phone_system->set_company($name);
    }
    function set_phone_company_id($id)
    {
        $this->phone_company->id = $id;
    }
    function get_phone_company_id()
    {
        return $this->phone_system->company->id;
    }
    function get_phone_company_name()
    {
        return $this->phone_system->company->name;
    }
    
    
    /*********************************************************************
     * END Phone Functions
     ********************************************************************/
    
    /*********************************************************************
     * Address Functions
     ********************************************************************/
    function get_address_description()
    {
        return $this->address->description;
    }
    function get_street()
    {
        return $this->address->street;
    }
    
    function get_city()
    {
        return $this->address->city;
    }
    
    function get_state()
    {
        return $this->address->state;
    }
    
    function get_zip()
    {
        return $this->address->zip;
    }
    
    
    /*********************************************************************
     * END Address Functions
     ********************************************************************/
    
    /*********************************************************************
     * MANAGER / ASST MANAGER FUNCTIONS
     ********************************************************************/
    function get_mgr_full_name()
    {
        return $this->manager->first_name . " " . $this->manager->last_name;
    }
    function get_asst_mgr_full_name($array_loc)
    {
        $temp = $this->asst_mgr_list[$array_loc];
        return $temp->first_name . " " . $temp->last_name;
    }
    // If the provided asst mgr is not already in the list, add it.
    function add_asst_mgr($emp)
    {
        if((!$this->asst_mgr_exists($emp)) && (sizeof($this->asst_mgr_list) < 4))
        {
            array_push($this->asst_mgr_list, $emp);
        }
    }
    
    // Does this emp_id exist in the asst_mgr_list?
    function asst_mgr_exists($emp)
    {
        $return = false;
        foreach($this->asst_mgr_list AS $key=>$val)
        {
            if($val == $emp->emplid)
                $return = true;
        }
        return $return;
    }
    
    function get_num_asst_mgrs()
    {
        return sizeof($this->asst_mgr_list);
    }
    
    /*********************************************************************
     * END MANAGER / ASST MANAGER FUNCTIONS
     ********************************************************************/
       
    
    /*******************************************************************
    * Store Contact DB Functions
    ********************************************************************/
    
    function db_load_contacts($ps_link)
    {
        $stmt = "select psj.EMPLID, pse.FIRST_NAME, pse.LAST_NAME, psj.JOBCODE "
              . "from PS_EMPLOYEES pse, PS_JOB psj "
              . "where psj.DEPTID = " . $this->site_num . " AND "
              . "psj.EFFDT = (SELECT MAX(EFFDT) FROM PS_JOB J1 WHERE J1.EMPLID = pse.EMPLID)"
              . "and "
              . "psj.EFFSEQ = (SELECT MAX(EFFSEQ) FROM PS_JOB J2 WHERE J2.EMPLID = pse.EMPLID AND J2.EFFDT = psj.EFFDT)"
              . "and "
              . "pse.EMPLID = psj.EMPLID "
              . "and "
              . "psj.JOBCODE < 100";
        $rs = mssql_query($stmt, $ps_link);
        while($row = mssql_fetch_row($rs))
        {
            $emp = new Employee(trim($row[0]), stripslashes(trim($row[1])), stripslashes(trim($row[2])), trim($row[3]));
            $jobcode = trim($row[3]);
            if(strcmp($jobcode, "010") == 0)
            {
                $this->manager = $emp;
            }
            else if(strcmp($jobcode, "020") == 0)
            {
                // Person is Asst Mgr.
                $this->add_asst_mgr($emp);
            }   
        }
    }
    /*******************************************************************
    * END Store Contact DB Functions
    ********************************************************************/
    
    /*******************************************************************
    * Site DB Functions
    ********************************************************************/
    function db_insert_site()
    {
        $dbmgr = new DB_Mgr();
        $stmt = "INSERT INTO SI_SITE VALUES("
              . $this->site_num . ", "
              . addslashes($this->site_name) . ", "
              . $this->address->description . ", "
              . $this->address->street . ", "
              . addslashes($this->address->city) . ", "
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