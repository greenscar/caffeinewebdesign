<?php

    require_once("./classes/Store.inc");
    require_once("./inc_files/php_fxns.inc");
    session_start();
    header("Cache-control: private");  // IE 6 Fix.
    $store = $_SESSION['store'];
    //phpinfo();    
/********************************************************
 * Create a Store with the POST data
 *******************************************************/
// CREATE NEW STORE OBJECT WITH NUMBER AND NAME
$store->site_num = $_POST["store_number"];
$store->site_name = $_POST["site_name"];

// SET DATE OPENED
$store->date_active = $_POST["date"];

// SET STORE ACTIVE
$store->active = $_POST["active"];

// SET ADDRESS VALUES
$store->address = new Address($_POST["street"], $_POST["city"], $_POST["state"], $_POST["zip"], $_POST["address_description"]);

// SET SYSCO ACCT NUM
$store->sysco_num = $_POST["sysco_num"];

// SET PHONE COMPANY
$store->set_phone_company($_POST["phone_co"]);


// SET PHONE LINES
$store->remove_all_lines();
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

// SET AMENITIES
$store->remove_all_amenities();
$store->add_amenity("smoking", $_POST["smoking"]);
$store->add_amenity("alcohol", $_POST["alcohol"]);
$store->add_amenity("gaming", $_POST["gaming"]);

/********************************************************
 * END Create a Store with the POST data
 *******************************************************/

/********************************************************
 * Insert this store into the DB.
 *******************************************************/
//echo($store->display_info());
//echo("UPDATE = " .$store->db_update_store());
$store->db_update_store();
header("Location: ./store_modify.php");
?>