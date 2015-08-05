<?php


require_once("./classes/Admin.inc");

$admin = new Admin();
$a_store = $admin->db_load_store(170);
echo("sysco_num = " . $a_store->sysco_num);
?>
