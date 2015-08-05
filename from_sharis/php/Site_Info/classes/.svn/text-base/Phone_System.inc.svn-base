<?php
require_once("Phone_Line.inc");
require_once("Phone_Company.inc");
class Phone_System
{
    // the Phone_Company the store uses.
    var $company;
    // an array of Phone_Line objects with the store's phone lines and numbers.
    var $line_list = array();
    
    // Array of Phone_Type
    var $phone_types;
    
    // Array of Phone_Company
    var $phone_companies;
    
    /********************************************************************
     * Line List Functions
     ********************************************************************/
    // get_line is used to return a Phone_Line.
    // REQUIRES: The name of the phone line type.
    function get_line_number($name)
    {
        foreach($this->line_list AS $key=>$val)
        {
            if(strcmp($val->type, $type) == 0)
                return $val->get_number();
        }
        return "N/A";
    }
    
    function get_line($num)
    {
        //return("get_line($num)");
        return $this->line_list[$num];
    }
        
    // Add this line object to the line array.
    // REQUIRE: The site does not already have a line with that name.
    function add_line($type_id, $number)
    {
        $plt = new Phone_Line_Type($type_id);
        $line = new Phone_Line($plt, $number);
        array_push($this->line_list, $line);
    }
    
    // Remove this phone line from the array.
    function remove_line($type)
    {
        foreach($this->line_list AS $key=>$val)
        {
            if(strcmp($val->type, $type) != 0)
                $tmp[$key] = $val;
        }
        $this->line_list = $tmp;
    }
    
    // Does this line type exist in the array?
    function line_exists($type)
    {
        $return = false;
        foreach($this->line_list AS $key=>$val)
        {
            if(strcmp($val->type, $type) == 0)
                $return = true;
        }
        return $return;
    }
    function line_list_size()
    {
        return sizeof($this->line_list);
    }
    
    /********************************************************************
     * END Line List Functions
     ********************************************************************/
   
   
    /********************************************************************
     * Phone Company List Functions
     ********************************************************************/
    function db_load_phone_co_list()
    {
        $this->phone_companies = array();
        $dbmgr = new DB_Mgr();
        $select = "SELECT * FROM SI_PHONE_CO ORDER BY name";
        $dbmgr->query($select);
        while($row = $dbmgr->get_a_row())
        {
            $tmp = new Phone_Company();
            $tmp->id = trim($row[0]);
            $tmp->name = trim($row[1]);
            array_push($this->phone_companies, $tmp);
        }
    }
    function get_a_phone_co_from_list($array_loc)
    {
        return($this->phone_companies[$array_loc]);
    }
    function phone_co_list_size()
    {
        return sizeof($this->phone_companies);
    }
    /********************************************************************
     * END Phone Company List Functions
     ********************************************************************/
    
    /********************************************************************
     * Phone Type List Functions
     ********************************************************************/
    function db_load_phone_type_list()
    {
        $this->phone_types = array();
        $dbmgr = new DB_Mgr();
        $select = "SELECT * FROM SI_PHONE_TYPE";
        $dbmgr->query($select);
        while($row = $dbmgr->get_a_row())
        {
            $tmp = new Phone_Line_Type();
            $tmp->id = trim($row[0]);
            $tmp->name = trim($row[1]);
            $tmp->description = trim($row[2]);
            array_push($this->phone_types, $tmp);
        }
    }
    
    function get_a_phone_type_from_list($array_loc)
    {
        return($this->phone_types[$array_loc]);
    }
    function phone_type_list_size()
    {
        return sizeof($this->phone_types);
    }
    /********************************************************************
     * END Phone Type List Functions
     ********************************************************************/
}

?>