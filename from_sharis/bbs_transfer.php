<?php
$host = "trainweb";
$db = "BBS";
$user = "jsandlin";
$pwd = "Zoolu4";
$link = mysql_connect($host, $user, $pwd);
echo("link => $link<BR>");
mysql_select_db($db);
?>