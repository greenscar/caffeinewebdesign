<?php
require_once("./classes/District.inc");
$reg = new District($_POST["number"], $_POST["name"], $_POST["mgr_emp_id"], $_POST["region_num"]);
if($reg->db_district_num_exists())
{
    // The district number is already in the DB. Return the user to the create page.
    header("Location: ./district_create.php?disp=ae");
    exit();
}
else
{
    // The district number isn't in the DB. Insert it.
    echo($reg->db_insert_district());
    header("Location: ./district_create.php");
}

?>