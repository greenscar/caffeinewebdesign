<?php
function display_title_to_mod($test_seq){
	$ot_query = "SELECT test_name FROM test_header_def WHERE test_seq = $test_seq";
	$ot = mssql_query($ot_query) or die("ERROR IN LINE 21: $ot_query<br>");
	$old_title = mssql_fetch_array($ot);
	$old_title = stripslashes($old_title["test_name"]);
	echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"type_name\" VALUE=\"title\">\n");
	echo("\t<TABLE WIDTH=\"630\">\n");
    echo("\t\t<TR>\n");
	echo("\t\t\t<TD WIDTH=\"126\">\n\t\t\tNEW TEST NAME:\n\t\t\t</TD>\n");
    echo("\t\t\t<TD WIDTH=\"504\"> \n\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp\n");
	echo("\t\t\t\t<INPUT TYPE=\"text\" NAME=\"title\" VALUE=\"$old_title\" SIZE=\"30\">\n");
    echo("\t\t\t</TD>\n");
    echo("\t\t</TR>\n");
    echo("\t\t<TR>\n");
	echo("\t\t\t<TD COLSPAN=\"2\">&nbsp;</TD>\n");
	echo("\t\t</TR>\n");
	echo("\t</TABLE>\n");		
}
function process_title_mod($_POST){
	$ts = $_POST["test_seq"];
	$new_title = htmlspecialchars($_POST["title"]);
	$update = "UPDATE test_header_def SET test_name = \"$new_title\" " . 
				"WHERE test_seq = $ts";
	if(!mssql_query($update)) echo("ERROR IN LINE 25: $update<BR>");
	else Header("Location: ./menu_admin.php?cat=test&scr=modtest&ts=$ts&do=mod");
}
?>