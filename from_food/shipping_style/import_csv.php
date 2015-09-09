<?php
$conn = connect();
delete_items($conn);
get_items($conn);
function connect()
{
	$ser="PSNT"; #the name of the SQL Server
	$db="WEB"; #the name of the database
	$user="sa"; #a valid username
	$pass="sa"; #a password for the username

	#one line
	$conn=odbc_connect("DRIVER=SQL Server;SERVER=".$ser.";UID=".$user.";PWD=".$pass.";DATABASE=".$db.";Address=".$ser.",2433","","");
	#one line
	return $conn;
}
function delete_items($conn)
{
	$del = "delete from shp_item";
	if(odbc_exec($conn, $del))
	{
		echo("Items Deleted<br>");
	}
	
}
function get_items($conn)
{
	$handle = fopen("./Supply_List.csv", "r");
	$row = 1;
	while (($data = fgetcsv($handle, 1000, "`")) !== FALSE) 
	{
   		$num = count($data);
		if($num != 5)
		{
		echo("<h1 align=center>$num</h1>");
		}
		//echo "<p> $num fields in line $row: <br />\n";
		$insert = "INSERT INTO shp_item (item_id, description, cost, gl_acct, active) "
				. "VALUES ($row, '" . htmlspecialchars($data[1], ENT_QUOTES) . "', " 
				. substr($data[2], 1) . ", "
				. $data[3] . ", 1);";
		if(!(odbc_exec($conn, $insert)))
		{
			echo("ERROR IN:<br>");
			echo($insert);
		}
   		$row++;
		//echo($insert . "<br>");
	}
	echo("Items Added.");
	fclose($handle);
}
?>