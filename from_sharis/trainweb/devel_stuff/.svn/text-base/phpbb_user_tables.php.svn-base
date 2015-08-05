<?php
	/*
  	* This header file connects us to the database which will
  	*  be used.
  	*/
$host_name = "PSNT";
$user_name = "helpdesk";
$password = "helpdesk";
$database = "TRAIN";
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
$title = "Your tables";
echo("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 //EN\">\n");
echo("<HTML>\n<HEAD>\n");
echo("<TITLE>$title</TITLE>\n");
echo("<meta http-equiv=\"Content-Type\" CONTENT=\"text/html; charset=iso-8859-1\">\n");
echo("</HEAD>\n");
echo("<BODY background = \"../Logo.jpg\" TEXT=#000000>\n");
/*
"phpbb_auth_access"
"phpbb_banlist"
"phpbb_categories"
"phpbb_config"
"phpbb_disallow"
"phpbb_forum_prune"
"phpbb_forums"
"phpbb_groups"
"phpbb_posts"
"phpbb_posts_text"
"phpbb_privmsgs"
"phpbb_privmsgs_text"
"phpbb_ranks"
"phpbb_search_results"
"phpbb_search_wordlist"
"phpbb_search_wordmatch"
"phpbb_sessions"
"phpbb_smilies"
"phpbb_themes"
"phpbb_themes_name"
"phpbb_topics"
"phpbb_topics_watch"
"phpbb_user_group"
"phpbb_users"
"phpbb_vote_desc"
"phpbb_vote_results"
"phpbb_vote_voters"
"phpbb_words"
*/
//$testTables = array("EXAM", "EXAM_ENTRY", "SECTION_HEADER", "TRUE_FALSE", "ESSAY", "MULT_CHOICE", "MATCHING", "MATCHING_QUESTION", "MATCHING_CHOICE", "WORD_BANK", "WORD_BANK_QUESTION", "WORD_BANK_CHOICE", "FILL_IN_BLANK", "FILL_IN_BLANK_SOLUTION");
//$selectTables = "select name from sysobjects where type=\"U\" and name like 'phpbb%'";
//$tableQuery = mssql_query($selectTables);
$tables = array("phpbb_posts", "phpbb_posts_text", "phpbb_privmsgs", "phpbb_privmsgs_text", "phpbb_users", "phpbb_vote_results", "phpbb_vote_voters");
echo("<table border=4 align=\"center\">");
echo("<tr valign=\"middle\"><td><h1>$title</h1></td></tr>");
echo("</table>");
//while($row = mssql_fetch_row($tableQuery)){
for($i = 0; $i < sizeof($tables); $i++){
	$rowNum = 0;
	echo("<h3 align=center>" . $tables[$i] . "</H3>");
	$query = "SELECT * FROM " . $tables[$i];
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
				echo("<TD>" . date("Y\-m\-d H\:i\:s", stripslashes($row[$x])) . "</TD>");
			}
			else
				echo("<TD>" . stripslashes($row[$x]) . "</TD>");
		}
		echo("</tr>");
	}
	echo("</TABLE>");
}
?>
</BODY>
</HTML>