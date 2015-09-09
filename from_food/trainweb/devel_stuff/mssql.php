<?php

$ser="psnt"; #the name of the SQL Server
$db="TRAINDEV"; #the name of the database
$user="sa"; #a valid username
$pass="sa"; #a password for the username

//putenv("LD_LIBRARY_PATH=/wwwroot/unixODBC/lib");
//putenv("ODBCINSTINI=/etc");
//putenv("ODBCINI=/wwwroot/unixODBC");
//echo(getenv("LD_LIBRARY_PATH") . "<br>");


// connect to DSN MSSQL with a user and password
$connect = odbc_connect("psnt", "sa", "sa") or die("couldn't connect");
echo("connect = " . $connect);
#one line
//$conn=odbc_connect("SERVER=".$ser.";UID=".$user.";PWD=".$pass.";DATABASE=".$db.";Address=".$ser.",1433","","");
//$conn=odbc_connect("SERVER=".$ser.";UID=".$user.";PWD=".$pass);
echo("ser = " . $ser . "<BR>");
echo("db = " . $db . "<BR>");
echo("user = " . $user . "<BR>");
echo("pass = " . $pass . "<BR>");

$conn=odbc_connect($db, $user, $pass);
#one line 
echo("conn = " . $conn . "<BR>");
?>
