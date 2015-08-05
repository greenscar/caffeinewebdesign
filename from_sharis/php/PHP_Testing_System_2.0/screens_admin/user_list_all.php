<?php
/*
* This screen will be used by the admin to modify existing users in the testing system
* Uses will include but won't be limited to:
* 	1) Modify a person's securiy level
* 	2) Modify a person's name
* 	3) Modify a person's testing scheme
*/
$user_type = "admin";
require("../inc_files/check_session.inc");
echo("<H3 align=center>Your employee list</H3>");
$str = "SELECT * FROM emp_def WHERE active = 1 AND sec_lvl_seq <= " . $the_user->get_sec_lvl_seq() . " ORDER BY emp_seq";
$temp = mssql_query($str);
$fields = mssql_num_fields ($temp) - 1;
echo("<TABLE border=1 align=center>");
for ( $f = 1 ; $f < $fields ; $f++ ){
	$name = mssql_fetch_field($temp, $f);
	echo "<th>".$name->name."</th>";
}
$sec_lvls = array();
$sec = "SELECT sec_lvl FROM sec_lvl_def";
$sec = mssql_query($sec);
for($i = 1; $sec_row = mssql_fetch_row($sec); $i++){
	$sec_lvls[$i] = $sec_row[0];
}
while($row = mssql_fetch_row($temp)){
	echo("<tr>");
	$num = sizeof($row) - 2;
	for($x=1; $x < $num; $x++){
		echo("<TD>" . $row[$x] . "</TD>\n\t");
	}
	echo("<TD>" . $sec_lvls[$row[$x++]] . "</TD>\n\t");
	echo("</tr>");
}
echo("</TABLE>");
?>