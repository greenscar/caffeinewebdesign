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
        $stmt = "SELECT phone_num FROM SI_PHONE_LINE WHERE dept_id = $ln AND type_num = $tn";
        $rs = odbc_exec($si_cnx, $stmt);        
        if(odbc_num_rows($rs) > 0)
        {
            odbc_fetch_row($rs);
            $num = odbc_result($rs,0);
            odbc_free_result($rs);
            return($num);
        }
        else
        {
            return(-1);
        }
    }
    
    // REQUIRE: this dept_id and type_name combo isn't in the DB already.
    function db_insert_phone_line($si_cnx, $dept_id)
    {
        $stmt = "INSERT INTO SI_PHONE_LINE VALUES('SMC', "
              . $dept_id . ", '"
              . $this->type->id . "', '"
              . $this->number . "');";
        return odbc_exec($si_cnx, $stmt);
    }
}
?>