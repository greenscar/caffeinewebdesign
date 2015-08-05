<?php
function display_subject_form($question_num){
	if($question_num == 0) $question_num = 1;
	echo("\t<TABLE WIDTH=\"630\">\n");
    echo("\t\t<TR>\n");
	echo("\t\t\t<TD WIDTH=\"126\">\n\t\t\tSection Header:\n\t\t\t</TD>\n");
    echo("\t\t\t<TD WIDTH=\"504\"> \n\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp\n");
	echo("\t\t\t\t<TEXTAREA ROWS=\"3\" COLS=\"70\"NAME=\"question_A\"></TEXTAREA>\n");
    echo("\t\t\t</TD>\n");
    echo("\t\t</TR>\n");
    echo("\t\t<TR>\n");
	echo("\t\t\t<TD COLSPAN=\"2\">&nbsp;</TD>\n");
	echo("\t\t</TR>\n");
	echo("\t</TABLE>\n");	
}
function display_subject_with_solution($question){
	echo("\t<h4 align=\"center\" WIDTH=\"630\">" . stripslashes($question["question"]) . "</h4>\n");
}
function add_subject_to_email($question){
	$temp = "<TABLE><TH>";
	$temp .= "\t<h4 align=\"center\">" . stripslashes($question["question"]) . "</h4>\n";
	$temp .= "</TH></TABLE>";
}
function display_subject_to_mod($ts, $qs){
	$os_query = "SELECT question, question_num FROM question_def WHERE question_seq = $qs";
	$os = mssql_query($os_query) or die("ERROR IN LINE 21: $os_query<br>");
	$old_subject = mssql_fetch_array($os);
	$question_num = $old_subject["question_num"];
	$old_subject = stripslashes(ereg_replace ("(<br />|<br/>)","", $old_subject["question"])) ;
	//echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"question_num\" VALUE=\"$question_num\">\n");
	echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"type_name\" VALUE=\"subject\">\n");
	echo("\t<TABLE WIDTH=\"504\">\n");
    echo("\t\t<TR>\n");
	echo("\t\t\t<TD WIDTH=\"126\">\n\t\t\tNEW SUBJECT:\n\t\t\t</TD>\n");
    echo("\t\t\t<TD WIDTH=\"504\"> \n\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp\n");
	echo("\t\t\t\t<TEXTAREA ROWS=\"3\" COLS=\"70\"NAME=\"subject\">$old_subject</TEXTAREA>\n");
    echo("\t\t\t</TD>\n");
    echo("\t\t</TR>\n");
    echo("\t\t<TR>\n");
	echo("\t\t\t<TD COLSPAN=\"2\">&nbsp;</TD>\n");
	echo("\t\t</TR>\n");
	echo("\t</TABLE>\n");		
}
function process_subject_mod($_POST){
	$qs = $_POST["question_seq"];
	$ts = $_POST["test_seq"];
	$new_subject = nl2br($_POST["subject"]);
	$update = "UPDATE question_def SET question = \"$new_subject\" " . 
				"WHERE question_seq = $qs";
	if(!mssql_query($update)) echo("ERROR IN LINE 53: $update<BR>");
	else Header("Location: ./menu_admin.php?cat=test&scr=modtest&ts=$ts&do=mod");
}

?>