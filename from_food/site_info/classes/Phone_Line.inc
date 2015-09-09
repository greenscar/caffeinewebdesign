<?php
require_once("Phone_Line_Type.inc");
require_once("../../../php_objects/DB_Mgr.inc");
class Phone_Line
{
    // The Phone_Line_Type object
    var $type;
    // the actual phone number of this line.
    var $number;
    
    function Phone_Line($t, $n)
    {
        $this->type = $t;
        $this->number = $n;
    }
    
    
    // Provided the location num and the type name, provide the phone number.
    function db_load_phone_num($dbmgr_web)
    {
        $stmt = "SELECT phone_num FROM SI_PHONE_LINE WHERE dept_id = $ln AND type_num = $tn";
        $dbmgr_web->query($stmt);        
        if($dbmgr_web->get_num_rows() > 0)
        {
            $rs = $dbmgr_web->fetch_row();
            return $rs;
        }
        else
        {
            return(-1);
        }
    }
    
    // REQUIRE: this dept_id and type_name combo isn't in the DB already.
    function db_insert_phone_line($dbmgr_web, $dept_id)
    {
        $stmt = "INSERT INTO SI_PHONE_LINE VALUES('SMC', "
              . $dept_id . ", '"
              . $this->type->id . "', '"
              . $this->number . "');";
        return $dbmgr_web->exec($stmt);
    }
}
?>