<?php
require_once("DB_Mgr.inc");
class Employee
{
    var $emplid;
    var $first_name;
    var $last_name;
    var $job_code;
    
    function Employee($pay_id, $fn, $ln, $jc)
    {
        $this->emplid = $pay_id;
        $this->last_name = $ln;
        $this->first_name = $fn;
        $this->job_code = $jc;
    }
    
}
?>