<?php
/*
 * 1) Generate a key based on the first 4 consonents of the name.
 * 2) Check the DB to see how many keys start with these 4 consonents.
 * 3) If = 0, insert
 * 4) If > 0, check if name exists in DB
 * 5) If name doesn't exist, insert
 * 6) If name does exist, it's already there. Do nothing.
 * 7) Forward user to phone_company_create.php
 */

require_once("classes/Phone_Company.inc");
require_once("inc_files/php_fxns.inc");
$pc = new Phone_Company();
$pc->name = strtoupper($_POST["name"]);

// 1) GET THE FIRST 4 CONSONENTS TO THE ID
$pc->id = get_first_n_cons($pc->name, 4);
// 2) GET NUM IDS START WITH THIS ONE. 
$num_times = $pc->db_num_times_id_exists();
if(@$num_times == 0)
{
    // 3) IF 0, INSERT
    $pc->db_insert_phone_company();
}
else
{
    // 4) CHECK IF NAME EXISTS IN DB.
    if($pc->db_company_name_exists())
    {
        // 6) THE NAME IS ALREADY IN THE DB. EXIT.
        header("Location: ./phone_company_create.php?disp=ae");
        exit();
    }
    else
    {
        // 5) THE NAME DOESN'T EXIST. GENERATE KEY AND INSERT.
        $pc->id = $pc->id . $num_times;
        $pc->db_insert_phone_company();
    }        
}
header("Location: ./phone_company_create.php");
?>