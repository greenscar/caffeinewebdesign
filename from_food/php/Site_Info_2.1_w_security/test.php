<?php
include("./inc_files/php_fxns.php");
   $ps_cnx = odbc_connect("PRODSUP", "sa", "sa");
   $select = "SELECT REGION, DISTRICT FROM PS_SH_TERRITORY;";
   $rs = odbc_exec($ps_cnx, $select);
   echo("rs = " . $rs . "<br>");
   echo("num_fields = " . odbc_num_fields($rs));
   while(odbc_fetch_row($rs))
   {
      echo("<br>region = " . odbc_result($rs, 1));
      echo("&nbspdistrict = " . odbc_result($rs, 2));
   }
?>
