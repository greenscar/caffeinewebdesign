<?php
	/*
  	* This header file connects us to the database which will
  	*  be used.
  	*/
$host_name = "localhost";
$user_name = "tomcat";
$password = "village";
$database = "BBS";
$row_color = "#999999";
//echo("database = $database<BR>");
$dbcnx = MYSQL_CONNECT($host_name,$user_name,$password) or DIE("<h1>DATABASE FAILED TO RESPOND.</h1>"); 
if(!mysql_select_db("$database")){
   	echo( "<P>Unable to connect to the database server at this time.</P>");
  	exit();
}
/*
if($_GET["do"] == "create_tables"){
	include("./create_tables.php");
}
*/
$title = "Your tables";
echo("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 //EN\">\n");
echo("<HTML>\n<HEAD>\n");
echo("<TITLE>$title</TITLE>\n");
echo("<meta http-equiv=\"Content-Type\" CONTENT=\"text/html; charset=iso-8859-1\">\n");
echo("</HEAD>\n");
echo("<BODY background = \"../Logo.jpg\" TEXT=#000000>\n");
$tableQuery = mysql_list_tables($database);

echo("<table border=4 align=\"center\">");
echo("<tr valign=\"middle\"><td><h1>$title</h1></td></tr>");
echo("</table>");
while($row = mysql_fetch_row($tableQuery)){
//for($i = 0; $i < sizeof($testTables); $i++){
	$rowNum = 0;
	echo("<h3 align=center>" . $row[0] . "</H3>");
	$query = "SELECT * FROM " . $row[0];
	//if($i > 3) 
	//	$query = $query . " ORDER BY exam_num, exam_loc";
	$temp = mysql_query($query);
	$fields = mysql_num_fields ($temp);
	echo("<TABLE border=1 align=center>");
	for ( $f = 0 ; $f < $fields ; $f++ ){
		$name = mysql_fetch_field($temp, $f);
		echo "<th>".$name->name."</th>";
	}
	
	while($row = mysql_fetch_row($temp)){
		echo("<tr ");
		if($rowNum++%2 == 0){
			echo("bgcolor=\"$row_color\" ");
		}
		echo("valign=\"top\">");
		for($x=0; $x < sizeof($row); $x++){
			if(is_float($row[$x])){
				echo("<TD>" . date("Y\-m\-d H\:i\:s", stripslashes($row[$x])) . "</TD>");
			}
			else
				echo("<TD>" . stripslashes($row[$x]) . "</TD>");
		}
		echo("</tr>");
	}
	echo("</TABLE>");
}
?>
</BODY>
</HTML>
