<?php
require_once("./classes/Region.inc");
$reg = new Region($_POST["number"], $_POST["name"], $_POST["mgr_emp_id"]);
if($reg->db_region_num_exists())
{
    // The region number is already in the DB. Return the user to the create page.
    header("Location: ./region_create.php?disp=ae");
    exit();
}
else
{
    // The region number isn't in the DB. Insert it.
    $reg->db_insert_region();
    header("Location: ./region_create.php");
}

?>