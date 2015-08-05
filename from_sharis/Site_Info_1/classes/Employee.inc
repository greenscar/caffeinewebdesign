<?php
require_once("Scribe.inc");
class Employee
{
    var $emplid;
    var $first_name;
    var $last_name;
    var $job_code;
    var $scribe;
    function Employee($pay_id, $fn, $ln, $jc)
    {
        $this->emplid = $pay_id;
        $this->last_name = $ln;
        $this->first_name = $fn;
        $this->job_code = $jc;
        $this->scribe = new Scribe();
    }
    
    function log_all_values()
    {
        $this->scribe->log($this->emplid . " " . $this->first_name . " " . $this->last_name . " " . $this->job_code);
    }
    function db_update_nick($ps_cnx)
    {
        $this->scribe = new Scribe();
        $this->scribe->log("db_update_nick($ps_cnx)");
        $stmt = "UPDATE PS_PERSONAL_DATA set PREF_FIRST_NAME = '"
                . $this->first_name
              . "' WHERE EMPLID = " . $this->emplid;
        $this->scribe->log($stmt);
        //return($stmt);
       $this->scribe->log(odbc_exec($ps_cnx, $stmt));
       $this->scribe->log_end_of_fxn();
    }
}
?>