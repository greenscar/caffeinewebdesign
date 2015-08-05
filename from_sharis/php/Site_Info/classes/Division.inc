<?php
class Division
{
    // This division's name
    var $name;
    // This division's number
    var $number;
    // This division's manager's emp id
    var $mgr_emp_id;

    function Division($nu = "", $na = "", $mg = "")
    {
        $this->number = $nu;
        $this->name = $na;
        $this->mgr_emp_id = $mg;
    }
    
    function set_name($n)
    {
        $this->name = $n;
    }
    function set_number($n)
    {
        $this->number = $n;
    }
    function set_mgr($emp_num)
    {
        $this->mgr_emp_id = $emp_num;
    }
    
    function get_name()
    {
        return $this->name;
    }
    function get_number()
    {
        return $this->number;
    }
    function get_mgr()
    {
        return $this->mgr_emp_num;
    }
}
?>