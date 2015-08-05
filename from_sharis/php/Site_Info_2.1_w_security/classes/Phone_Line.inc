<?php
require_once("Phone_Line_Type.inc");
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
    function db_load_phone_num($si_cnx)
    {
        $stmt = "SELECT phone_num FROM SI_PHONE_LINE WHERE loc_num = $ln AND type_num = $tn";
        $rs = odbc_exec($si_cnx, $stmt);        
        if(odbc_num_rows($rs) > 0)
        {
            odbc_fetch_row($rs);
            return odbc_result($rs,0);
        }
        else
        {
            return(-1);
        }
    }
    
    // REQUIRE: this loc_num and type_name combo isn't in the DB already.
    function db_insert_phone_line($si_cnx, $loc_num)
    {
        $stmt = "INSERT INTO SI_PHONE_LINE VALUES("
              . $loc_num . ", '"
              . $this->type->id . "', '"
              . $this->number . "');";
        return odbc_exec($si_cnx, $stmt);
    }
}
?>