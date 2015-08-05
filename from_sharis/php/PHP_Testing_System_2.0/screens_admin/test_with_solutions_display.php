<?php
$user_type = "admin";
require_once("../inc_files/check_session.inc");
require_once("../inc_files/display_fxns.inc");
//require_once("../inc_files/some_useful_functions.inc");
if(isset($_POST["test_seq"])){
	//SET THE TITLE AND DISPLAY THE HTML HEADER
    //$title = "Here is your exam with the solutions, " . $the_user->first_name;
    //require_once("../inc_files/html_header_no_fxns.inc");
   	display_w_solutions($_POST["test_seq"], false);
	//include("../inc_files/html_footer.inc");
}
else{
	//DISPLAY ALL EXAMS FOR THE USER TO CHOOSE FROM.
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
		echo("\t<FORM NAME=\"form1\" method=\"POST\" action=\"" . $_SERVER['PHP_SELF'] . "?cat=test&scr=disptest\">\n");
		if(!empty($temp["test_name"])) $test_name = $temp["test_name"];
		else $test_name = "Untitled";
		echo("\t\t<INPUT TYPE=\"SUBMIT\" VALUE=\"" . stripslashes($test_name) . "\">\n");
		echo("\t\t</INPUT>\n\t</TD></TR>\n");
		echo("\t\t<INPUT TYPE=\"HIDDEN\" NAME=\"test_seq\" VALUE=\"" . $temp["test_seq"] . "\">");
		echo("\t</FORM>");
	}
	
	echo("\t\t\t</TD></TR>\n");
	echo("\t\t</TABLE>\n");
}
?>
