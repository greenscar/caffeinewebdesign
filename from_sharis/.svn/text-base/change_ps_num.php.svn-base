<html>
<head><title>test</title></head>
<body>
<?php
require_once("../../php_objects/DB_Mgr.inc");
$dbm = new DB_Mgr("WEB");
echo("<h1>First, it's WEB</h1>");
$query = "select DISTINCT ps_num from ses_EXAM where ps_num LIKE 'SH%'";
$dbm->query($query);
while($row = $dbm->fetch_row())
{
   $ps_num = $row[0];
   //echo("substr => " . substr($ps_num, 0, 3) . "<BR>");
   //echo("strcmp => " . strcmp("SHA", substr($ps_num, 0, 3)) . "<BR>");
   if(strcmp("SHA", substr($ps_num, 0, 3)) != 0)
   {
      $new_ps_num = trim('SHA' . substr($ps_num, 2, 3));
      $q = "update ses_EXAM set ps_num = '$new_ps_num' where ps_num = '$ps_num'";
   //   $dbm->exec($q);
      echo($q . "<br>");
   }
}
$ps_num = 0;
$new_ps_num = 0;
$dbm->disconnect();
echo("<hr>");
echo("<h1>Now for PRODSUP</h1>");
$dbm2 = new DB_Mgr("PRODSUP");
$query = "SELECT DISTINCT ACCOMPLISHMENT FROM PS_ACCOMP_TBL WHERE ACCOMPLISHMENT LIKE 'SH%'";
echo($query . "<BR>");
$dbm2->query($query);
while($row = $dbm2->fetch_row())
{
   $ps_num = $row[0];
   if(strcmp("SHA", substr($ps_num, 0, 3)) != 0)
   {
      $new_ps_num = trim('SHA' . substr($ps_num, 2, 3));
      $q = "update PS_ACCOMP_TBL set ACCOMPLISHMENT = '$new_ps_num' where ACCOMPLISHMENT = '$ps_num'";
      //$dbm2->exec($q);
      echo($q . "<br>");
      $q = "update PS_ACCOMPLISHMENTS set ACCOMPLISHMENT = '$new_ps_num' where ACCOMPLISHMENT = '$ps_num'";
      //$dbm2->exec($q);
      echo($q . "<br>");
   }
   
}
?>
</body>
</html>