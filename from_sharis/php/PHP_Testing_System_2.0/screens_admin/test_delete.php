<?php
$user_type = "admin";
require_once("../inc_files/check_session.inc");
//phpinfo();
if(isset($_GET["ts"])){
	$test_seq = $_GET["ts"];
	//include("../inc_files/html_header_no_fxns.inc");
	echo("<H3 class=\"red\">Are you sure you want to delete this test?</H3>\n");
	require_once("../inc_files/display_fxns.inc");
	display_w_solutions($test_seq, false);
	echo("<FORM NAME=\"form1\" method=\"POST\" action=\"" . $_SERVER['PHP_SELF'] . "?cat=test&scr=deltest\">\n");
	echo("<INPUT TYPE=\"HIDDEN\" NAME = \"test_seq\" VALUE=\"" . $test_seq . "\">");
	echo("<P ALIGN=CENTER><INPUT TYPE=\"SUBMIT\" NAME=\"button\" VALUE=\"Yes, Delete It.\">\n");
	echo("&nbsp&nbsp&nbsp<INPUT TYPE=\"BUTTON\" VALUE=\"No, Return to the Test List\" onClick=\"location.href='./menu_admin.php?cat=test&scr=deltest';\">\n");
	echo("</FORM>\n");
	//echo("</BODY>\n</HTML>\n");
	exit();
}
else if (isset($_POST["test_seq"])){
	$test_seq = $_POST["test_seq"];
	$taken = "SELECT COUNT(take_seq) FROM test_taken_dtl WHERE test_seq = $test_seq";
	$taken = mssql_query($taken);
	$taken = mssql_fetch_array($taken);
	if($taken[0] > 0){
		$update = "UPDATE test_header_def SET active = 0 WHERE test_seq = $test_seq";
	}
	else{
		$update = "DELETE FROM test_header_def WHERE test_seq = $test_seq";
		$update2 = "DELETE FROM question_def WHERE test_seq = $test_seq";
	}
	if(isset($update2)){
		if(!mssql_query($update)) die("Error in $update<BR> in display_test_to_modify.php line 33<BR>");
		if(!mssql_query($update2)) die("Error in $update2<BR> in display_test_to_modify.php line 34<BR>");
	}
	else{
		if(!mssql_query($update)) die("Error in $update<BR> in display_test_to_modify.php line 37<BR>");
	}
	//include("../inc_files/html_header_no_fxns.inc");
	echo("<H3 class=\"red\">The test has been deleted.</H3>\n");
	require_once("../inc_files/display_fxns.inc");
	//display_w_solutions($test_seq, false);
	echo("</BODY>\n</HTML>\n");
	exit();
}

else{
	$tests = "SELECT * FROM test_header_def WHERE active = 1 ORDER BY creation_date";
	$search_results = mssql_query($tests) or die("Error in Query: $tests<BR>");  //select the username 
	$title = "Select a test to delete.";
	$counter = 1;
	//require_once("../inc_files/html_header_no_fxns.inc");
	echo("\n\t<H3 ALIGN=\"CENTER\">Click on the exam you wish to delete.</H3>\n");	
	echo("<TABLE BORDER=\"0\" ALIGN=\"CENTER\">\n");
	while($temp = mssql_fetch_array($search_results)){
		echo("\t<TR><TD SIZE=\"4\">$counter) </TD>\n");
		$counter++;
		echo("\t<TD ALIGN=\"CENTER\">\n");
		echo("\t\t<A HREF=\"" . $_SERVER['PHP_SELF'] . "?cat=test&scr=deltest&ts=" . $temp["test_seq"] . "\">\n");
		if(!empty($temp["test_name"])) $test_name = $temp["test_name"];
		else $test_name = "Untitled";
		echo("\t\t\t" . stripslashes($test_name) . "\n");
		echo("\t\t</A>\n\t</TD></TR>\n");
   	}
	echo("\t<TR><TD ALIGN=\"CENTER\" COLSPAN=\"2\">\n");
	echo("<BR><BR><BR><BR>");
	//echo("<INPUT TYPE=\"BUTTON\" VALUE=\"Cancel and return to the Main Menu\" onClick=\"location.href='./menu_admin.php';\">\n");
	echo("\t</TD></TR>\n");
	echo("</TABLE>");
?>
	</BODY>
	</HTML>
<?php

}

	
?>