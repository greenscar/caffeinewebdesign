<?php
	//GLOBAL $user_type;
	$user_type = "student";
	require_once("../inc_files/check_session.inc");
	require_once("../inc_files/display_fxns.inc");
	$title = "Welcome, " . $the_user->first_name;
	include("../inc_files/html_header_no_fxns.inc");
	echo("\t\t<TABLE ALIGN=\"CENTER\" border=\"0\">\n");
	echo("\t\t\t<TR><TD ALIGN=\"CENTER\" COLSPAN=\"2\">\n");
	echo("\t<H3 ALIGN = \"CENTER\"> Please select an exam.</H3>\n");
	echo("\t\t\t</TD></TR>\n");
	echo("\t\t\t<TR><TD ALIGN=\"CENTER\">\n");
	$tests = "SELECT * FROM test_header_def WHERE active = 1 ORDER BY creation_date";
	$search_results = mssql_query($tests) or die("Error in Query: $tests<BR>");  //select the username 
	$counter = 1;
	while($temp = mssql_fetch_array($search_results)){
		echo("\t<TR><TD SIZE=\"4\">$counter) </TD>\n");
		$counter++;
		echo("\t<TD ALIGN=\"CENTER\">\n");
		echo("\t<FORM NAME=\"form1\" method=\"POST\" action=\"display_test_for_taking.php\">\n");
		if(!empty($temp["test_name"])) $test_name = $temp["test_name"];
		else $test_name = "Untitled";
		echo("\t\t<INPUT TYPE=\"SUBMIT\" VALUE=\"" . stripslashes($test_name) . "\">\n");
		echo("\t\t</INPUT>\n\t</TD></TR>\n");
		echo("\t\t<INPUT TYPE=\"HIDDEN\" NAME=\"test_seq\" VALUE=\"" . $temp["test_seq"] . "\">");
		echo("\t</FORM>");
	}
	
	echo("\t\t\t</TD></TR>\n");
	echo("\t\t</TABLE>\n");
	echo("\t</BODY>\n</HTML>\n");
	
	echo("<P ALIGN=CENTER><A HREF=\"../logout.php\">Logout</A></P>");
	//echo("<A HREF=\"./take_test_screen.php\">Take a test</A><BR>");
?>