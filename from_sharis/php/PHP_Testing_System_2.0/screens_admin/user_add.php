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
require("../inc_files/sql_connect.inc");
if (!empty($_POST["emp_num"])){
	$good_emp_num = emp_num_not_used(@$_POST["emp_num"]);
	$good_ssn = ssn_not_used(@$_POST["ssn"]);
}
if(@$_POST["edit"] == "Submit" && $good_emp_num && $good_ssn){
		insert_student($_POST);
}	
else{
	if(!$good_emp_num) echo("<H3 ALIGN=\"CENTER\">That payroll ID is already used.</H3>");
	if(!$good_ssn) echo("<H3 ALIGN=\"CENTER\">That social security number is already used.</H3>");
	$sec_lvl_ary = array();
	echo("<H3 align=\"center\">Enter your new person</H3>")	;
	$sec_lvl = "SELECT * FROM sec_lvl_def";
	$temp = mssql_query($sec_lvl) or die("Error in $sec_lvl<BR>");
	for($x = 1;$sec_lvl = mssql_fetch_array($temp); $x++){		
		$sec_lvl_ary[$x] = $sec_lvl[1];		
	}
		
	echo("<FORM NAME=\"FORM\" METHOD=\"post\" ACTION=\"" . $_SERVER['PHP_SELF'] . "?cat=user&scr=addusr\" ONSUBMIT=\"return validateData(this)\">\n\t\t");
	echo("<INPUT TYPE=\"hidden\" NAME=\"emp_seq\">\n\t");
	echo("<TABLE border=1 align=center>\n\t");
	echo("<TR>\n\t");
	echo("<TD>Payroll ID:</TD>\n\t");
	echo("<TD><INPUT TYPE=\"text\" NAME=\"emp_num\" SIZE=\"15\"");
	if($good_emp_num)
		echo(" VALUE=\"" . $_POST["emp_num"] . "\"");
	echo("></TD>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD>SSN:</TD>\n\t");
	echo("<TD><INPUT TYPE=\"text\" NAME=\"ssn\" SIZE=\"15\"");
	if($good_ssn)
		echo(" VALUE=\"" . $_POST["ssn"] . "\"");
	echo("></TD>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD>Name:</TD>\n\t");
	echo("<TD>\n\t\t<INPUT TYPE=\"text\" NAME=\"first_name\" SIZE=\"15\"");
	if(!empty($_POST["first_name"]))
		echo(" VALUE=\"" . $_POST["first_name"] . "\"");
	echo(">\n\t\t&nbsp\n\t\t");
	echo("<INPUT TYPE=\"text\" NAME=\"last_name\" SIZE=\"15\"");
	if(!empty($_POST["last_name"]))
		echo(" VALUE=\"" . $_POST["last_name"] . "\"");
	echo(">\n\t</TD>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD>Email:</TD>\n\t");
	echo("<TD><INPUT TYPE=\"text\" NAME=\"email\" SIZE=\"20\"");
	if(!empty($_POST["email"]))
		echo(" VALUE=\"" . $_POST["email"] . "\"");
	echo(">\n\t</TD>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD>Security Level:</TD>\n\t");
	echo("<TD>\n\t\t<SELECT NAME=\"sec_lvl\">"); // onChange=\"submit()\"
	for($x = 1; $x <= sizeof($sec_lvl_ary); $x++){
		echo("\n\t\t\t<OPTION VALUE=\"" .$x. "\"");
		if($x == $_POST["sec_lvl"]) echo(" SELECTED");
		echo(">" . $sec_lvl_ary[$x]);
	}
	echo("\n\t\t</SELECT>\n\t</TD>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD align=\"center\" colspan=\"2\"><INPUT TYPE=\"submit\" NAME=\"edit\" VALUE=\"Submit\">&nbsp&nbsp");
	echo("<INPUT TYPE=\"BUTTON\" VALUE=\"Cancel\" onClick=\"window.location='./" . "menu_". @$the_user->get_sec_lvl() . ".php'\">\n");
	//<INPUT TYPE=\"submit\" NAME=\"edit\" VALUE=\"Cancel\"></TD>\n\t\t");
	echo("</TR>\n\t");
}

function insert_student($_POST){
	$emp_seq = $_POST["emp_seq"];
	$emp_num = $_POST["emp_num"];
	$ssn = $_POST["ssn"];
	$first_name = $_POST["first_name"];
	$last_name = $_POST["last_name"];
	$email = $_POST["email"];
	$sec_lvl = $_POST["sec_lvl"];
	$insert = "INSERT INTO emp_def VALUES (" .
			"'$emp_num', '$ssn', '$first_name', " .
			"'$last_name', '$email', '$sec_lvl', '1')";
	mssql_query($insert) or die("Error in $insert<BR>");
	include("user_list_all.php");
}
function emp_num_not_used($emp_num){
	$temp = "SELECT * FROM emp_def WHERE emp_num = $emp_num";
	$results = mssql_query($temp);
	if(mssql_num_rows($results) == 0) return true;
	else return false;
}
function ssn_not_used($ssn){
	$temp = "SELECT * FROM emp_def WHERE ssn = $ssn";
	$results = mssql_query($temp);
	if(mssql_num_rows($results) == 0) return true;
	else return false;
}
?>