<?
//$db = 'C:\Inetpub\wwwroot\shipping\Supply.mdb';
//$conn = new COM('ADODB.Connection');
//$conn->Open("DRIVER={MS Access Driver(*.mdb)}; DBQ=$db");

$conn = new COM("ADODB.Connection"); 
$dsn = "DRIVER={Microsoft Access Driver (*.mdb)}; DBQ=" . realpath("Supply.mdb"); 
$conn->Open($dsn);
// Driver do Microsoft Access (*.mdb)
// must be the name in your odbc drivers, the one you get
// from the Data Sources (ODBC).
// In this case, I'm in Mexico but the driver name is in portuguese, thanks Microsoft.

$sql = 'SELECT username FROM tblUsuarios';
$res = $conn->Execute($sql);
while (!$res->EOF)
{
   print $res->Fields['username']->Value . "<br>";
   $res->MoveNext();
}
?>