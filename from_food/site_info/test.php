<?php

include("../../../php_objects/DB_Mgr.inc");
require_once("../../../php_objects/Settings.inc");
$settings = new Settings();
$db = "pstest";
echo("<h1>DB $db</h1>");
$conn = odbc_connect($settings->db[$db], $settings->id[$db], $settings->pwd[$db]);
echo("conn = $conn<BR>");
$dbmgr = new DB_Mgr($db);
echo("dbmgr->get_conn() = " . $dbmgr->get_conn() . "<BR>");
/*
$select = "SELECT s.dept_id, s.site_name, s.loc_name,"
         . "a.street, a.city, a.state, a.zip"
         . " FROM SI_SITE s, SI_ADDRESS a"
         . " WHERE a.address_id = s.address_id"
         . " AND s.active = 1";
echo($select . "<BR>");
$dbmgr->query($select);
while($row = $dbmgr->fetch_row())
{
    $temp .= "$row[1]<br>";
}
echo($temp);
*/
//require_once("./classes/Admin.inc");
//$admin = new Admin();
//$a_store = $admin->db_load_store(170);
//echo("sysco_num = " . $a_store->sysco_num);
?>
