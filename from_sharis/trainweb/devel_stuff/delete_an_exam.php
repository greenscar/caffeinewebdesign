<?php
	/*
  	* This header file connects us to the database which will
  	*  be used.
  	*/
$host_name = "PSNT";
$user_name = "sa";
$password = "sa";
$database = "TRAINDEV";
$row_color = "#999999";
$dbcnx = MSSQL_CONNECT($host_name,$user_name,$password) or DIE("<h1>DATABASE FAILED TO RESPOND.</h1>"); 
if(!mssql_select_db("$database")){
   	echo( "<P>Unable to connect to the database server at this time.</P>");
  	exit();
}
$examToDelete = $_GET["num"];
//for($examToDelete=26; $examToDelete < 52; $examToDelete++){
	if(!empty($examToDelete)){
		$testTables = array("EXAM", "EXAM_ENTRY", "SECTION_HEADER", "TRUE_FALSE", "ESSAY", "MULT_CHOICE", "MATCHING", "MATCHING_QUESTION", "MATCHING_CHOICE", "WORD_BANK", "WORD_BANK_QUESTION", "WORD_BANK_CHOICE", "FILL_IN_BLANK", "FILL_IN_BLANK_SOLUTION");
		for($i=0; $i< sizeof($testTables); $i++){
			$delete = "DELETE FROM " . $testTables[$i] . " WHERE exam_num = " . $examToDelete;
			mssql_query($delete);
		}
	}
//}


$title = "Your tables";
echo("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 //EN\">\n");
echo("<HTML>\n<HEAD>\n");
echo("<TITLE>$title</TITLE>\n");
echo("<meta http-equiv=\"Content-Type\" CONTENT=\"text/html; charset=iso-8859-1\">\n");
echo("</HEAD>\n");
echo("<BODY background = \"../Logo.jpg\" TEXT=#000000>\n");


echo("<table border=4 align=\"center\">");
echo("<tr valign=\"middle\"><td><h1>TEST TABLES</h1></td></tr>");
echo("</table>");
for($i = 0; $i < sizeof($testTables); $i++){
	$rowNum = 0;
	echo("<h3 align=center>" . $testTables[$i] . "</H3>");
	$query = "SELECT * FROM " . $testTables[$i];
	if($i > 3) 
		$query = $query . " ORDER BY exam_num, exam_loc";
	$temp = mssql_query($query);
	$fields = mssql_num_fields ($temp);
	echo("<TABLE border=1 align=center>");
	for ( $f = 0 ; $f < $fields ; $f++ ){
		$name = mssql_fetch_field($temp, $f);
		echo "<th>".$name->name."</th>";
	}
	
	while($row = mssql_fetch_row($temp)){
		echo("<tr ");
		if($rowNum++%2 == 0){
			echo("bgcolor=\"$row_color\" ");
		}
		echo("valign=\"top\">");
		for($x=0; $x < sizeof($row); $x++){
			echo("<TD>" . stripslashes($row[$x]) . "</TD>");
		}
		echo("</tr>");
	}
	echo("</TABLE>");
}
echo("<br><br><br>");
echo("<hr width=\"100%\" size=\"3\">");
$userTables = array("USER_INFO", "USER_ROLE", "LOGIN_INFO");
echo("<table border=4 align=\"center\">");
echo("<tr valign=\"middle\"><td><h1>USER TABLES</h1></td></tr>");
echo("</table>");
for($i = 0; $i < sizeof($userTables); $i++){
	$rowNum = 0;
	echo("<h3 align=center>" . $userTables[$i] . "</H3>");
	$temp = mssql_query("SELECT * FROM " . $userTables[$i]);
	$fields = mssql_num_fields ($temp);
	echo("<TABLE border=1 align=center>");
	for ( $f = 0 ; $f < $fields ; $f++ ){
		$name = mssql_fetch_field($temp, $f);
		echo "<th>".$name->name."</th>";
	}
	while($row = mssql_fetch_row($temp)){
		echo("<tr ");
		if($rowNum++%2 == 0){
			echo("bgcolor=\"$row_color\" ");
		}
		echo("valign=\"top\">");
		for($x=0; $x < sizeof($row); $x++){
			echo("<TD>" . stripslashes($row[$x]) . "</TD>");
		}
		echo("</tr>");
	}
	echo("</TABLE>");
}

echo("<br><br><br>");
echo("<hr width=\"100%\" size=\"3\">");
$takeTables = array("EXAM_TAKE", "EMP_ANSWER");
echo("<table border=4 align=\"center\">");
echo("<tr valign=\"middle\"><td><h1>TAKE TABLES</h1></td></tr>");
echo("</table>");
for($i = 0; $i < sizeof($takeTables); $i++){
	$rowNum = 0;
	echo("<h3 align=center>" . $takeTables[$i] . "</H3>");
	$temp = mssql_query("SELECT * FROM " . $takeTables[$i]);
	$fields = mssql_num_fields ($temp);
	echo("<TABLE border=1 align=center>");
	for ( $f = 0 ; $f < $fields ; $f++ ){
		$name = mssql_fetch_field($temp, $f);
		echo "<th>".$name->name."</th>";
	}
	while($row = mssql_fetch_row($temp)){
		echo("<tr ");
		if($rowNum++%2 == 0){
			echo("bgcolor=\"$row_color\" ");
		}
		echo("valign=\"top\">");
		for($x=0; $x < sizeof($row); $x++){
			echo("<TD>" . stripslashes($row[$x]) . "</TD>");
		}
		echo("</tr>");
	}
	echo("</TABLE>");
}
//echo("<P ALIGN=CENTER><INPUT TYPE=\"BUTTON\" VALUE=\"create_tables\" onClick=\"window.location='" . $_SERVER['PHP_SELF'] . "?do=create_tables'\"><p>\n");
echo("<P ALIGN=CENTER><INPUT TYPE=\"BUTTON\" VALUE=\"create_tables\" onClick=\"window.location='create_tables.php?do=create_tables'\"><p>\n");
?>
</BODY>
</HTML>