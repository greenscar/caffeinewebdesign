<?php
  //include("../includes/php_files/ensure_root.inc");
  include("./classes/DB_Mgr.inc");
  error_reporting(E_ALL);
	/*
  	* This header file connects us to the database which will
  	*  be used.
  	*/
$row_color = "#999999";

?>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 //EN">
<HTML>
<HEAD>
<TITLE>$title</TITLE>
<meta http-equiv="Content-Type" CONTENT="text/html; charset=iso-8859-1">
<style type="text/css">
      body {
            background: #000000;
      }
      td.header {
            text-align: center;
            background: #00FF00;
            /*border: 5px solid red;*/
            font-weight: 900;
            font-size: 25pt;
      }
      td.data {
            text-align: center;
            font-family: Verdana, Serif; 
            font-weight: bold; 
            font-size: 15px;
            font-color: #FFFFFF;
      }
      tr.odd{
            background: #BBBBBB;
      }
      tr.even{
            background: #888888;
      }
      h3 {
            text-align: center;
            background: #00FF00;
            border: 5px solid red;
            font-weight: 900;
            font-size: 25pt;
      }
</style>
</head>
<BODY>

<?php
$tbl_dbmgr = new DB_Mgr();
  
$selectTables = "select name from sysobjects where type='U' and name like 'SI_%'";
$tbl_dbmgr->query($selectTables);


echo("<table border=4 align=\"center\">");
echo("<tr valign=\"middle\"><td><h1> <font color=#FFFFFF>The Store Tables</font></h1></td></tr>");
echo("</table>");
while($tbl_dbmgr->fetch_row())
{
	$rowNum = 0;
   $table = trim($tbl_dbmgr->get_field(1));
	echo("<h3>" . $table . "</H3>");
	
   $data_db_mgr = new DB_Mgr();
   $query = "SELECT * FROM " . $table;
   $temp = $data_db_mgr->query($query);
   
   echo("<TABLE border=1 align=center>");
	for ( $f = 1 ; $f <= $data_db_mgr->get_num_fields(); $f++ ){
      $name = $data_db_mgr->get_field_name($f);
		echo "<td class=\"header\">".$name."</td>";
	}
	
   while($data_db_mgr->fetch_row())
   {
	//while($row = mssql_fetch_row($temp)){
		echo("<tr class=\"");
		if($rowNum++%2 == 0){
			echo("even\">");
		}
      else
         echo("odd\">");
      for($x=1; $x <= $data_db_mgr->get_num_fields(); $x++)
      {
			echo("<TD class=\"data\">" . $data_db_mgr->get_field($x) . "</TD>");
		}
		echo("</tr>");
	}
	echo("</TABLE>");
}
?>
</BODY>
</HTML>