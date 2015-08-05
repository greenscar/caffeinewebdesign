<?php
/*
 * 1) Generate a key based on the first 4 consonents of the name.
 * 2) Check the DB to see how many keys start with these 4 consonents.
 * 3) If = 0, insert
 * 4) If > 0, check if name exists in DB
 * 5) If name doesn't exist, insert
 * 6) If name does exist, it's already there. Do nothing.
 * 7) Forward user to phone_type_create.php
 */

require_once("classes/Amenity.inc");
require_once("inc_files/php_fxns.inc");
$am = new Amenity();
$am->name = trim($_POST["name"]);


// 1) GET THE FIRST 4 CONSONENTS TO THE ID
$am->id = get_first_n_cons($am->name, 4);
// 2) GET NUM IDS START WITH THIS ONE. 
$num_times = $am->db_num_times_id_exists();
if(@$num_times == 0)
{
    // 3) IF 0, INSERT
    $am->db_insert_amenity();
}
else
{
    // 4) CHECK IF NAME EXISTS IN DB.
    if($am->db_amenity_name_exists())
    {
        // 6) THE NAME IS ALREADY IN THE DB. EXIT.
        header("Location: ./amenity_create.php?disp=ae");
        exit();
    }
    else
    {
        // 5) THE NAME DOESN'T EXIST. GENERATE KEY AND INSERT.
        $am->id = $am->id . $num_times;
        $am->db_insert_amenity();
    }        
}
header("Location: ./amenity_create.php");
?>