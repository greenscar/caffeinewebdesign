<?php
require_once("../../../php_objects/DB_Mgr.inc");
require_once("Secretary.inc");
class Employee
{
    var $emplid;
    var $first_name;
    var $last_name;
    var $job_code;
    var $log;
    function Employee($pay_id, $fn, $ln, $jc)
    {
        $this->emplid = $pay_id;
        $this->last_name = $ln;
        $this->first_name = $fn;
        $this->job_code = $jc;
        $this->log = new Secretary();
    }
    
    function log_all_values()
    {
        $this->log->log($this->emplid . " " . $this->first_name . " " . $this->last_name . " " . $this->job_code);
    }
    function db_update_nick($ps_cnx)
    {
        $this->log = new Secretary();
        $this->log->log("db_update_nick($ps_cnx)");
        $stmt = "UPDATE PS_PERSONAL_DATA set PREF_FIRST_NAME = '"
                . $this->first_name
              . "' WHERE EMPLID = " . $this->emplid;
        $this->log->log($stmt);
        //return($stmt);
       $this->log->log(odbc_exec($ps_cnx, $stmt));
       $this->log->log_end_of_fxn();
    }
}
?>