<?php
require_once("Phone_Line_Type.inc");
require_once("DB_Mgr.inc");
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
    function db_load_phone_num()
    {
        $dbmgr = new DB_Mgr("sites");
        $stmt = "SELECT phone_num FROM SI_PHONE_LINE WHERE loc_num = $ln AND type_num = $tn";
        $dbmgr->query($stmt);
        if($dbmgr->get_num_rows() > 0)
        {
            $array = $dbmgr->get_a_row();
            return $array[0];
        }
        else
        {
            return(-1);
        }
    }
    
    // REQUIRE: this loc_num and type_name combo isn't in the DB already.
    function db_insert_phone_line($loc_num)
    {
        $dbmgr = new DB_Mgr("sites");
        $stmt = "INSERT INTO SI_PHONE_LINE VALUES("
              . $loc_num . ", '"
              . $this->type->id . "', '"
              . $this->number . "');";
        return $dbmgr->execute($stmt);
    }
}
?>