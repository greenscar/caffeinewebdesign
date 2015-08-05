<?php
include("inc_files/php_fxns.inc");
include("classes/Store.inc");

/********************************************************
 * Create a Store with the POST data
 *******************************************************/

// CREATE NEW STORE OBJECT WITH NUMBER AND NAME
$store = new Store($_POST["store_number"], $_POST["site_name"]);

// SET DATE OPENED
$store->set_date_active($_POST["date"]);

// SET ADDRESS VALUES
$store->address = new Address($_POST["street"], $_POST["city"], $_POST["state"], $_POST["zip"], $_POST["address_description"]);

//SET DISTRICT / REGION
$rd = $_POST["dist_reg"];
$dec_loc = strpos($rd, ".");
$region = substr($rd, 0, $dec_loc);
$district = substr($rd, ($dec_loc + 1), (strlen($rd) - 1));
$store->set_district(new District($district, "", "", $region));
$store->active = true;

// SET SYSCO ACCT NUM
$store->sysco_num = $_POST["sysco_num"];

// SET PHONE COMPANY
$store->phone_system->company = new Phone_Company($_POST["phone_co"]);

// SET PHONE LINES
foreach ($_POST as $key => $val) 
{
    if(strcmp(substr($key, 0, 4), "phn_") == 0)
    {
        if($val != "")
        {
            $type_id = substr($key, 4, (strlen($key) - 1));
            $store->phone_system->add_line($type_id, $val);  
        }
    }
}
// SET MGR
$store->mgr_emp_id = $_POST["mgr"];

// SET ASST MGR LIST
$store->add_asst_mgr($_POST["asst_mgr_1"]);
if(!empty($_POST["asst_mgr_2"]))
    $store->add_asst_mgr($_POST["asst_mgr_2"]);
if(!empty($_POST["asst_mgr_3"]))
    $store->add_asst_mgr($_POST["asst_mgr_3"]);

// SET AMENITIES
$store->add_amenity("smoking", $_POST["smoking"]);
$store->add_amenity("alcohol", $_POST["alcohol"]);
$store->add_amenity("gaming", $_POST["gaming"]);

/********************************************************
 * END Create a Store with the POST data
 *******************************************************/

/********************************************************
 * Insert this store into the DB.
 *******************************************************/

echo("INSERT = " .$store->db_insert_new_store());


?>
