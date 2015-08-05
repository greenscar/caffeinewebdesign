<?php
	/*
  	* This header file connects us to the database which will
  	*  be used.
  	*/
$host_name = "psnt";
$user_name = "sa";
$password = "sa";
$database = "TRAINDEV";
$row_color = "#999999";
$dbcnx = MSSQL_CONNECT($host_name,$user_name,$password) or DIE("<h1>DATABASE FAILED TO RESPOND.</h1>"); 
if(!mssql_select_db("$database")){
   	echo( "<P>Unable to connect to the database server at this time.</P>");
  	exit();
}
/*
if($_GET["do"] == "create_tables"){
	include("./create_tables.php");
}
*/
$title = "TrainDev Tables";
echo("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 //EN\">\n");
echo("<HTML>\n<HEAD>\n");
echo("<TITLE>$title</TITLE>\n");
echo("<meta http-equiv=\"Content-Type\" CONTENT=\"text/html; charset=iso-8859-1\">\n");
echo("</HEAD>\n");
echo("<BODY background = \"../Logo.jpg\" TEXT=#000000>\n");

$testTables = array("ses_EXAM", "ses_EXAM_ENTRY", "ses_SECTION_HEADER", "ses_TRUE_FALSE", "ses_ESSAY", "ses_MULT_CHOICE", "ses_MATCHING", "ses_MATCHING_QUESTION", "ses_MATCHING_CHOICE", "ses_WORD_BANK", "ses_WORD_BANK_QUESTION", "ses_WORD_BANK_CHOICE", "ses_FILL_IN_BLANK", "ses_FILL_IN_BLANK_SOLUTION");

echo("<table border=4 align=\"center\">");
echo("<tr valign=\"middle\"><td><h1>$title</h1></td></tr>");
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
			if(is_float($row[$x])){
				if($row[$x] == 0){
					echo("<TD>FALSE</TD>");
				}
				else if($row[$x] == 1){
					echo("<TD>TRUE</TD>");
				}
				else{
					echo("<TD>" . date("Y\-m\-d H\:i\:s", round($row[$x]/1000)) . "</TD>");
				}
			}
			else{
				echo("<TD>" . stripslashes($row[$x]) . "</TD>");
			}
		}
		echo("</tr>");
	}
	echo("</TABLE>");
}
echo("<br><br><br>");
echo("<hr width=\"100%\" size=\"3\">");
$userTables = array("ses_USER_INFO", "ses_USER_ROLE", "ses_LOGIN_INFO");
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
			if(is_float($row[$x])){
				if($row[$x] == 0){
					echo("<TD>FALSE</TD>");
				}
				else if($row[$x] == 1){
					echo("<TD>TRUE</TD>");
				}
				else{
					echo("<TD>" . date("Y\-m\-d H\:i\:s", round($row[$x]/1000)) . "</TD>");
				}
			}
			else{
				echo("<TD>" . stripslashes($row[$x]) . "</TD>");
			}
		}
		echo("</tr>");
	}
	echo("</TABLE>");
}

echo("<br><br><br>");
echo("<hr width=\"100%\" size=\"3\">");
$takeTables = array("ses_EXAM_TAKE", "ses_EMP_ANSWER");
echo("<table border=4 align=\"center\">");
echo("<tr valign=\"middle\"><td><h1>TAKE TABLES</h1></td></tr>");
echo("</table>");
for($i = 0; $i < sizeof($takeTables); $i++){
	$rowNum = 0;
	echo("<h3 align=center>" . $takeTables[$i] . "</H3>");
	$temp = mssql_query("SELECT * FROM " . $takeTables[$i]);
	$fields = mssql_num_fields ($temp);
	echo("<TABLE border=1 align=center>");
        $nameTemp = "";
	for ( $f = 0 ; $f < $fields ; $f++ ){
		$name = mssql_fetch_field($temp, $f);
                $nameTemp = $name->name;
		echo "<th>".$name->name."</th>";
	}
	while($row = mssql_fetch_row($temp)){
		echo("<tr ");
		if($rowNum++%2 == 0){
			echo("bgcolor=\"$row_color\" ");
		}
		echo("valign=\"top\">");
		for($x=0; $x < sizeof($row); $x++){
			if(is_float($row[$x])){
				if($row[$x] == 0){
					echo("<TD>FALSE</TD>");
				}
				else if($row[$x] == 1){
					echo("<TD>TRUE</TD>");
				}
                                else if($row[$x] == -1){
                                        echo("<TD>FALSE</TD>");
                                } 
				else{
					echo("<TD>" . date("Y\-m\-d H\:i\:s", round($row[$x]/1000)) . "</TD>");
				}
			}
			else{
				echo("<TD>" . stripslashes($row[$x]) . "</TD>");
			}
		}
		echo("</tr>");
	}
	echo("</TABLE>");
}
echo("<br><br><br>");
echo("<hr width=\"100%\" size=\"3\">");
$takeTables = array("ses_ENTRY_TYPE", "ses_EXAM_CATEGORY");
echo("<table border=4 align=\"center\">");
echo("<tr valign=\"middle\"><td><h1>CATEGORY TABLES</h1></td></tr>");
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
			if(is_float($row[$x])){
				if($row[$x] == 0){
					echo("<TD>FALSE</TD>");
				}
				else if($row[$x] == 1){
					echo("<TD>TRUE</TD>");
				}
				else{
					echo("<TD>" . date("Y\-m\-d H\:i\:s", round($row[$x]/1000)) . "</TD>");
				}
			}
			else if(is_bool($row[$x])){
				echo("<TD>BOOL</TD>");
			}
			else{
				echo("<TD>" . stripslashes($row[$x]) . "</TD>");
			}
		}
		echo("</tr>");
	}
	echo("</TABLE>");
}
//echo("<P ALIGN=CENTER><INPUT TYPE=\"BUTTON\" VALUE=\"create_tables\" onClick=\"window.location='" . $_SERVER['PHP_SELF'] . "?do=create_tables'\"><p>\n");
//echo("<P ALIGN=CENTER><INPUT TYPE=\"BUTTON\" VALUE=\"create_tables\" onClick=\"window.location='create_tables.php?do=create_tables'\"><p>\n");
?>
</BODY>
</HTML>
