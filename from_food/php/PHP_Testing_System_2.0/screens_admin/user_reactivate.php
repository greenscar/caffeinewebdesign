<?php
$user_type = "admin";
require("../inc_files/check_session.inc");
require("../inc_files/sql_connect.inc");
if(@$_POST["reactivate"] == "Yes"){
	$emp_seq = $_POST["emp_seq"];
	$reactive = "UPDATE emp_def SET active = 1 WHERE emp_seq = $emp_seq";
	$reactive = mssql_query($reactive) or die("Error in $reactive<BR>");
	include("./user_list_all.php");
}	
else if(isset($_POST["emp_seq"]) && (@$_POST["reactivate"] == "Reactivate")){
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
	$title = "Are you sure you want to reactivate " . $first_name . " " . $last_name . "?";
	include("../inc_files/html_header.inc");
	echo("<H3 align=center>Are you sure you want to reactivate:</H3>\n");	
	$sec_lvl = "SELECT * FROM sec_lvl_def";
	$temp = mssql_query($sec_lvl) or die("Error in $sec_lvl<BR>");
	for($x = 1;$sec_lvl = mssql_fetch_array($temp); $x++){		
		$sec_lvl_ary[$x] = $sec_lvl[1];		
	}
		
	echo("<FORM NAME=\"FORM\" METHOD=\"post\" ACTION=\"" . $_SERVER['PHP_SELF'] . "?cat=user&scr=react\" ONSUBMIT=\"return validateData(this)\">\n\t\t");
	echo("<INPUT TYPE=\"hidden\" NAME=\"emp_seq\" VALUE=\"$emp_seq\">\n\t");
	echo("<TABLE border=1 align=center>\n\t");
	echo("<TR>\n\t");
	echo("<TD>Name:</TD>\n\t");
	echo("<TD>\n\t\t$first_name $last_name\n\t</TD>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD>Payroll ID:</TD>\n\t");
	echo("<TD>$emp_num</TD>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD>SSN:</TD>\n\t");
	echo("<TD>$ssn</TD>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD>Email:</TD>\n\t");
	echo("<TD>$email</TD>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD>Security Level:</TD>\n\t");
	echo("<TD>\n\t\t");
	for($x = 1; $x <= sizeof($sec_lvl_ary); $x++){		
		if($sec_lvl_seq == $x) echo($sec_lvl_ary[$x]);
	}
	echo("\n\t</TD>\n\t");
	echo("</TR>\n\t");
	echo("<TR>\n\t");
	echo("<TD align=\"center\" colspan=\"2\"><INPUT TYPE=\"submit\" NAME=\"reactivate\" VALUE=\"Yes\">&nbsp&nbsp<INPUT TYPE=\"submit\" NAME=\"reactivate\" VALUE=\"No\"></TD>\n\t\t");
	echo("</TR>\n\t");
}
else{
	//$title = "Select the user you want to reactivate";
	//require("../inc_files/html_header.inc");
	echo("<H3 align=center>Please select the user you wish to reactivate</H3>\n");
	$temp = mssql_query("SELECT * FROM emp_def WHERE active = 0");
	echo("<TABLE border=1 align=center>\n\t");
	echo("<TH>Reactivate</TH>\n\t");
	echo("<TH>Employee Number</TH>\n\t");
	echo("<TH>Social Security Number</TH>\n\t");
	echo("<TH>Name</TH>\n\t");
	echo("<TH>Email Address</TH>\n\t");
	echo("<TH>Security Level</TH>\n\t");
	while($row = mssql_fetch_array($temp)){
		echo("<TR>\n\t");
		echo("<FORM NAME=\"FORM\" METHOD=\"post\" ACTION=\"" . $_SERVER['PHP_SELF'] . "?cat=user&scr=react\">\n\t\t");
		echo("<INPUT TYPE=\"HIDDEN\" NAME=\"emp_seq\" VALUE=\"" . $row[0] . "\">\n\t\t");
		echo("<TD align=\"right\"><INPUT TYPE=\"submit\" NAME=\"reactivate\" VALUE=\"Reactivate\"></TD>\n\t\t");
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
	//require("../inc_files/html_footer.inc");
}

?>