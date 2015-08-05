
<?php
include("config.php");
$row_color = "#999999";
$current_time = time();
$num_days = 42;
$todays_date = time();
$delete_date = time() - ($num_days * 86400);
$dbcnx = MSSQL_CONNECT($dbhost, $dbuser, $dbpasswd) or DIE("<h1>DATABASE FAILED TO RESPOND.</h1>");
if (!mssql_select_db("$dbname")) {
    echo("<P>Unable to connect to the database server at this time.</P>");
    exit();
} 
if ($_GET["do"] == "delete") {
    $user_id = $_GET["user_id"];

    $allPrivMsgs = "SELECT privmsgs_id FROM phpbb_privmsgs WHERE "
     . "privmsgs_from_userid = $user_id OR "
     . "privmsgs_to_userid = $user_id";
	//echo $allPrivMsgs;
    $allPrivs = mssql_query($allPrivMsgs);
    while ($aPriv = mssql_fetch_array($allPrivs)) {
        $delete = "DELETE FROM phpbb_privmsgs_text WHERE privmsgs_text_id = " . $aPriv[0];
        //echo($delete . "<BR>");
		mssql_query($delete);
    } 
    $delete = "DELETE FROM phpbb_privmsgs WHERE privmsgs_to_userid = $user_id OR privmsgs_from_userid = $user_id";
    //echo($delete . "<BR>");
	mssql_query($delete);

    $allPosts = "SELECT post_id FROM phpbb_posts WHERE poster_id = $user_id";
    $allPosts = mssql_query($allPosts);
    while ($aPost = mssql_fetch_array($allPosts)) {
        $delete = "DELETE FROM phpbb_posts_text WHERE post_id = " . $aPost[0];
		//echo($delete . "<BR>");
        mssql_query($delete);
    } 
	$delete = "DELETE FROM phpbb_posts WHERE poster_id = $user_id";
	mssql_query($delete);

    $allVotes = "SELECT vote_id FROM phpbb_vote_voters WHERE vote_user_id = $user_id";
    //echo("voteID = " .$voteID . "<BR>");
	$allVotes = mssql_query($allVotes);
	while ($aVoteID = mssql_fetch_array($allVotes)) {
        $delete = "DELETE FROM phpbb_vote_results WHERE vote_id = " . $aVoteID[0];
        //echo($delete . "<BR>");
		mssql_query($delete);
    } 
	$delete = "DELETE FROM phpbb_vote_voters WHERE vote_user_id = $user_id";
	mssql_query($delete);
	
    $delete = "DELETE FROM phpbb_users WHERE user_id = $user_id";
    //echo($delete . "<BR>");
	mssql_query($delete);
    $delete = "DELETE FROM phpbb_posts WHERE poster_id = $user_id";
    //echo($delete . "<BR>");
	mssql_query($delete);

    ?>
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 //EN">
	<HTML>
	<HEAD>
		<TITLE><?php echo($_GET["nick"])?></TITLE>
		<meta http-equiv="Content-Type" CONTENT="text/html; charset=iso-8859-1">
	</HEAD>
	<BODY background = "../Logo.jpg" TEXT=#000000>
	<H1 align=center><?php echo($_GET["nick"])?> has been deleted.</H1>
	<p align=center>
	<INPUT TYPE="BUTTON" VALUE="RETURN TO DELETE USER MENU" onClick="window.location='delete_a_user.php'">
	<INPUT TYPE="BUTTON" VALUE="EXIT" onClick="javascript:window.close()">
	</p>
	</BODY>
	</HTML>
	<?
}
else if($_GET["do"] == "verify"){
	?>
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 //EN">
	<HTML>
	<HEAD>
		<TITLE>Please Verify Your Selection</TITLE>
		<meta http-equiv="Content-Type" CONTENT="text/html; charset=iso-8859-1">
	</HEAD>
	<BODY background = "../Logo.jpg" TEXT=#000000>
	<table border=0 align="center">
		<tr valign="middle">
			<td align="center">
				<h1>Are you sure you want to delete <?php echo($_GET["nick"])?>?</h1>
			</td>
		</tr>
	<?php
    $postedTo = "SELECT forums.forum_name FROM phpbb_forums forums , phpbb_posts posts, phpbb_users users "
     . "WHERE forums.forum_id = posts.forum_id AND posts.poster_id = users.user_id "
     . "AND users.user_id = " . $_GET["user_id"]
     . " GROUP BY forums.forum_name " ;
    $temp = mssql_query($postedTo);
    $count = 0;
    $numPosts = mssql_num_rows($temp);
    if ($numPosts > 0) {

        ?>
		<tr valign="middle">
			<td align="center">
				<h3><?php echo($_GET["nick"])?> has posted in the following tables:</h3>
			</td>
		</tr>
		<?php
    } else {

        ?>
		<tr valign="middle">
			<td align="center">
				<h3><?php echo($_GET["nick"])?> has made no posts.</h3>
			</td>
		</tr>
		<?php
    }
	while ($row = mssql_fetch_row($temp)) {
        echo("<tr><td align=\"center\">" . $row[0] . "</td></tr>");
    } 
    echo("</table>");
    echo("<p align=\"center\">");
    echo("<INPUT TYPE=\"BUTTON\" VALUE=\"YES Delete EVERYTHING\" onClick=\"window.location='" . $_SERVER['PHP_SELF'] . "?do=delete&user_id=" . $_GET["user_id"] . "&nick=" . $_GET["nick"] . "'\">\n");
    echo("<INPUT TYPE=\"BUTTON\" VALUE=\"Oops, wrong user.\" onClick=\"window.location='" . $_SERVER['PHP_SELF'] . "'\">\n");
    echo("<p>");
	echo("</BODY></HTML>");
} 
else {

    ?>
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 //EN">
	<HTML>
	<HEAD>
		<TITLE>Select a User to Delete</TITLE>
		<meta http-equiv="Content-Type" CONTENT="text/html; charset=iso-8859-1">
	</HEAD>
	<BODY background = "../Logo.jpg" TEXT=#000000>
	<table border=4 align="center">
		<tr valign="middle">
			<td>
				<h1>Select a User to Delete</h1>
				<h3>The following users are over <?php echo($num_days)?> days old</h3>
			</td>
		</tr>
	</table>
	<h3 align=center>Users</H3>
	<TABLE border=1 align=center>
		<th>Delete</th>
		<th>User Name</th>
		<th>User Email</th>
	<?php
    $selectUsers = "SELECT user_id, username, user_email FROM phpbb_users "
     . "WHERE username NOT LIKE 'Anonymous' "
     . "AND user_regdate < $delete_date";
    $temp = mssql_query($selectUsers);
    $rowNum = 0;
    while ($row = mssql_fetch_row($temp)) {
        echo("<tr ");
        if ($rowNum++ % 2 == 0) {
            echo("bgcolor=\"$row_color\" ");
        } 
        echo(">");
        echo("<TD>");
        echo("<INPUT TYPE=\"BUTTON\" VALUE=\"Delete User\" onClick=\"window.location='" . $_SERVER['PHP_SELF'] . "?do=verify&user_id=" . $row[0] . "&nick=" . $row[1] . "'\">\n");
        echo("</TD>");
        for($x = 1; $x < sizeof($row); $x++) {
            echo("<TD>");
            echo($row[$x]);
            echo("</TD>");
        } 
        echo("</tr>");
    } 

    ?>
	</TABLE>
	</BODY>
	</HTML>
<?php } 
?>