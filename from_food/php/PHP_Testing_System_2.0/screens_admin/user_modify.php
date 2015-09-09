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
if(@$_POST["edit"] == "Submit Changes"){
	$emp_seq = $_POST["emp_seq"];
	$emp_num = $_POST["emp_num"];
	$ssn = $_POST["ssn"];
	$first_name = $_POST["first_name"];
	$last_name = $_POST["last_name"];
	$email = $_POST["email"];
	$sec_lvl = $_POST["sec_lvl"];
	$update = "UPDATE emp_def SET " .
			"emp_num = '$emp_num', " .
			"ssn = '$ssn', " .
			"first_name = '$first_name', " .
			"last_name = '$last_name', " .
			"email = '$email', " .
			"sec_lvl_seq = '$sec_lvl' " .
			"WHERE emp_seq = '$emp_seq'";
	//echo("$update<BR>");
	$update = mssql_query($update) or die("Error in $update<BR>");
	//Header("Location: ./menu_admin.php");
	$_POST["edit"] = "";
	include("user_modify.php");
}	
else if(isset($_POST["emp_seq"]) && (@$_POST["edit"] == "EDIT")){
	$temp = mssql_query("SELECT * FROM emp_def WHERE emp_seq = " . $_POST["emp_seq"]);
	//echo($_POST["emp_seq"] . "<BR>");
	$user = mssql_fetch_array($temp);
	$emp_seq = $user[0];
	$emp_num = $user[1];
	$ssn = $user[2];
	$first_name = $user[3];
	$last_name = $user[4];
	$email = $user[5];
	$sec_lvl_seq = $user[6];
	$sec_lvl_ary = array();
	//$title = "Enter the new info for " . $first_name . " " . $last_name;
	//include("../inc_files/html_header.inc");
		
	$sec_lvl = "SELECT * FROM sec_lvl_def";
	$temp = mssql_query($sec_lvl) or die("Error in $sec_lvl<BR>");
	for($x = 1;$sec_lvl = mssql_fetch_array($temp); $x++){		
		$sec_lvl_ary[$x] = $sec_lvl[1];		
	}
		
	echo("<FORM NAME=\"FORM\" METHOD=\"post\" ACTION=\"" . $_SERVER['PHP_SELF'] . "?cat=user&scr=modusr\" ONSUBMIT=\"return validateData(this)\">\n\t\t");
	echo("<INPUT TYPE=\"hidden\" NAME=\"emp_seq\" VALUE=\"$emp_seq\">\n\t");
	echo("<TABLE WIDTH=600 border=1 align=center valign=center>\n\t");
	echo("<TR>\n\t");
	echo("<TD>Payroll ID:</TD>\n\t");
	echo("<TD><INPUT TYPE=\"text\" NAME=\"emp_num\" SIZE=\"8\" VALUE=\"" . $emp_num . "\"></TD>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD>SSN:</TD>\n\t");
	echo("<TD><INPUT TYPE=\"text\" NAME=\"ssn\" SIZE=\"10\" VALUE=\"" . $ssn . "\"></TD>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD>Name:</TD>\n\t");
	echo("<TD>\n\t\t<INPUT TYPE=\"text\" NAME=\"first_name\" SIZE=\"20\" VALUE=\"" . $first_name . "\">\n\t\t&nbsp\n\t\t");
	echo("<INPUT TYPE=\"text\" NAME=\"last_name\" SIZE=\"20\" VALUE=\"" . $last_name . "\">\n\t</TD>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD>Email:</TD>\n\t");
	echo("<TD><INPUT TYPE=\"text\" NAME=\"email\" SIZE=\"30\" VALUE=\"" . $email. "\"></TD>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD>Security Level:</TD>\n\t");
	echo("<TD>\n\t\t<SELECT NAME=\"sec_lvl\">"); // onChange=\"submit()\"
	for($x = 1; $x <= sizeof($sec_lvl_ary); $x++){
		echo("\n\t\t\t<OPTION ");
		if($sec_lvl_seq == $x){
			echo("SELECTED ");
		}
		echo("VALUE=\"" .$x. "\">" . $sec_lvl_ary[$x]);
	}
	echo("\n\t\t</SELECT>\n\t</TD>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD align=\"center\" colspan=\"2\"><INPUT TYPE=\"submit\" NAME=\"edit\" VALUE=\"Submit Changes\">&nbsp&nbsp<INPUT TYPE=\"submit\" NAME=\"edit\" VALUE=\"Cancel\"></TD>\n\t\t");
	echo("</TR>\n\t");
	echo("</TABLE>");
}
else{
	//$title = "Select the user you want to modify";
	//require("../inc_files/html_header.inc");
	echo("<h3 align=center>Please select the user you wish to modify</H3>\n");
	$temp = mssql_query("SELECT * FROM emp_def WHERE active = 1 ORDER BY emp_seq");
	echo("<TABLE border=1 align=center>\n\t");
	echo("<TH>Edit</TH>\n\t");
	echo("<TH>Emp Num</TH>\n\t");
	echo("<TH>SSN</TH>\n\t");
	echo("<TH>Name</TH>\n\t");
	echo("<TH>Email Address</TH>\n\t");
	echo("<TH>Security Level</TH>\n\t");
	while($row = mssql_fetch_array($temp)){
		echo("<TR>\n\t");
		echo("<FORM NAME=\"FORM\" METHOD=\"post\" ACTION=\"" . $_SERVER['PHP_SELF'] . "?cat=student&scr=modusr\">\n\t\t");
		echo("<INPUT TYPE=\"HIDDEN\" NAME=\"emp_seq\" VALUE=\"" . $row[0] . "\">\n\t\t");
		echo("<TD align=\"right\"><INPUT TYPE=\"submit\" NAME=\"edit\" VALUE=\"EDIT\"></TD>\n\t\t");
		echo("<TD align=\"right\">" . $row[1] . "</TD>\n\t\t");
		echo("<TD align=\"right\">" . $row[2] . "</TD>\n\t\t");
		echo("<TD align=\"center\">" . $row[3] . " " . $row[4] . "</TD>\n\t\t");
		echo("<TD align=\"right\">" . $row[5] . "</TD>\n\t\t");
		$sec_lvl = "SELECT sec_lvl FROM sec_lvl_def WHERE sec_lvl_seq = " . $row[6];
		$sec_lvl = mssql_query($sec_lvl);
		$sec_lvl = mssql_fetch_array($sec_lvl);
		$sec_lvl = $sec_lvl[0];
		echo("<TD align=\"right\">" . $sec_lvl . "</TD>\n\t");
		echo("</FORM>\n\t");
		echo("</TR>\n\t");
	}
	echo("</TABLE>");
}
?>