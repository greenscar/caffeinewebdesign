<?php

    require_once("./classes/Admin.inc");
    require_once("./inc_files/php_fxns.inc");
    session_start();
    header("Cache-control: private");  // IE 6 Fix.
    $admin = new Admin();
    echo($admin->process_store_mod($_SESSION['store'], $_POST));
    header("Location: ./store_modify.php");
?>