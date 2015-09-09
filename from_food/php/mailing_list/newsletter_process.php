<?php
include("./inc/sql_connect.inc");
session_start();
require_once("./inc/check_session.inc");
/*
 * Assign variables.
 */
$from = "webmaster@sharis.com";
$senderName = "Shari's Restaurant";
$topic = $_POST["mailTopic"];
$htmlBody = $_POST["mailBodyHtml"];
$textBody = $_POST["mailBodyText"];
/*
 * END Assign variables.
 */
/*
 * Mail the HTML format group.
 */
$htmlSelect = "SELECT * FROM ml_recipients WHERE HTML_FORMAT = 1";
$html = mssql_query($htmlSelect);
$htmlToArray = array();
//addArray($htmlToArray, "James Sandlin", "jsandlin@sharis.com");
//addArray($htmlToArray, "Eric Leisegang", "eleisegang@sharis.com");
//addArray($htmlToArray, "Ken Vandegrift", "kvandegrift@sharis.com");
//addArray($htmlToArray, "Toni Versoza", "tversoza@sharis.com");
while($row = mssql_fetch_row($html))
{
    addArray($htmlToArray, $row[0], $row[1]);
//    echo("$row[0], $row[1]<BR>");
}
//socketmail($htmlToArray, $from, $senderName, "text/html", $topic, $htmlBody);
/*
 * END Mail the HTML format group.
 */

/*
 * Mail the TEXT format group.
 */
$textSelect = "SELECT * FROM ml_recipients WHERE HTML_FORMAT = 0";
$text = mssql_query($textSelect);
$textToArray = array();
//addArray($textToArray, "James Sandlin", "jsandlin@sharis.com");
//addArray($textToArray, "Eric Leisegang", "eleisegang@sharis.com");
//addArray($textToArray, "Ken Vandegrift", "kvandegrift@sharis.com");
//addArray($textToArray, "Toni Versoza", "tversoza@sharis.com");
while($row = mssql_fetch_row($text))
{
    addArray($textToArray, $row[0], $row[1]);
//    echo("$row[0], $row[1]<BR>");
}
//socketmail($textToArray, $from, $senderName, "text/plain", $topic, $textBody);
view_array($htmlToArray);
view_array($textToArray);
/*
 * END Mail the TEXT format group.
 */
?>
<script language=javascript type=text/javascript>
window.close();
</script>
<?php

/*
* These 2 functions are used to display the contents of any array.
* This can be single or multidimensional.
*/
function view_array($arr){
	echo "\t<TABLE CELLPADDING=\"0\" CELLSPACING=\"0\" BORDER=\"1\">\n";
	foreach ($arr as $key1 => $elem1) 
	{
		echo("\t\t<TR>\n");
		echo("\t\t\t<TD>$key1&nbsp;</TD>\n");
		if (is_array($elem1))  
			extArray($elem1);
		else  
			echo("\t\t\t<TD>". stripslashes($elem1)."&nbsp;</TD>\n"); 
		echo("\t\t</TR>\n");
	}
	echo("\t</TABLE>\n");
}
	
//This function is called from view_array to take care of multi-dimensional arrays
function extArray($arr){
	echo("\t\t\t<TD>\n");
	echo "\t<TABLE CELLPADDING=\"0\" CELLSPACING=\"0\" BORDER=\"1\">\n";
	foreach ($arr as $key => $elem) 
	{
		echo("\t\t<TR>\n");
		echo("\t\t\t<TD>$key&nbsp;</TD>\n");
		if (is_array($elem)) 
			extArray($elem);
		else 
			echo("\t\t\t<TD>". stripslashes($elem)."&nbsp;</TD>\n");
		echo("\t\t</TR>\n");
	}
	echo("\t</TABLE>\n");
	echo("</TD>\n");
}


function addArray(&$array, $key, $val)
{
   $tempArray = array($key => $val);
   $array = array_merge ($array, $tempArray);
} 

/*
 * $mailFormat should be text/plain or text/html
 */
function socketmail($toArray, $from, $senderName, $mailFormat, $subject, $message)
{
    // $toArray format --> array("Name1" => "address1", "Name2" => "address2", ...)
    ini_set(sendmail_from, "$from");
    $connect = fsockopen ("DOGBERT", 25, $errno, $errstr, 30) or die("Could not talk to the sendmail server!");
    $rcv = fgets($connect, 1024);
    fputs($connect, "HELO {$_SERVER['SERVER_NAME']}\r\n");
    $rcv = fgets($connect, 1024);
    while (list($toKey, $toValue) = each($toArray)) {
	fputs($connect, "MAIL FROM:$from\r\n");
	$rcv = fgets($connect, 1024);
	fputs($connect, "RCPT TO:$toValue\r\n");
	$rcv = fgets($connect, 1024);
	fputs($connect, "DATA\r\n");
	$rcv = fgets($connect, 1024);
	fputs($connect, "Subject: " . stripslashes($subject) . "\r\n");
	fputs($connect, "From: $senderName <$from>\r\n");
	fputs($connect, "To: $toKey  <$toValue>\r\n");
	fputs($connect, "X-Sender: <$from>\r\n");
	fputs($connect, "Return-Path: <$from>\r\n");
	fputs($connect, "Errors-To: <$from>\r\n");
	fputs($connect, "X-Mailer: PHP\r\n");
	fputs($connect, "X-Priority: 3\r\n");
	fputs($connect, "Content-Type: $mailFormat; charset=iso-8859-1\r\n");
	fputs($connect, "\r\n");
	fputs($connect, stripslashes($message)." \r\n");
	fputs($connect, ".\r\n");
	$rcv = fgets($connect, 1024);
	fputs($connect, "RSET\r\n");
	$rcv = fgets($connect, 1024);
    }
    fputs ($connect, "QUIT\r\n");
    $rcv = fgets ($connect, 1024);
    fclose($connect);
    ini_restore(sendmail_from);
}  
?>