<?php
$databases = array();
/*
global $server;
global $id;
global $pwd;
global $db;
*/
// Training Development DB
$server = "PSNT";
$id = "sa";
$pwd = "sa";
$db = "TRAINDEV";
array_push($databases, array($server, $id, $pwd, $db));

// Training Production DB
$server = "PSNT";
$id = "helpdesk";
$pwd = "helpdesk";
$db = "TRAIN";
array_push($databases, array($server, $id, $pwd, $db));

// PS Production DB
$server = "PSPROD";
$id  = "sa";
$pwd = "sa";
$db = "PROD";
array_push($databases, array($server, $id, $pwd, $db));

// PS Development DB
$server = "PSTEST,2433";
$id = "sa";
$pwd = "sa";
$db = "HCM8DEMO";
$db3 = array($server, $id, $pwd, $db);
array_push($databases, array($server, $id, $pwd, $db));


?>