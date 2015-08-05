
<?php
function socketmail($toArray, $from, $senderName, $subject, $message)
{
    // $toArray format --> array("Name1" => "address1", "Name2" => "address2", ...)
    ini_set(@sendmail_from, "$from");
    $connect = fsockopen ("corpmail", 25, $errno, $errstr, 30) or die("Could not talk to the sendmail server!");
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
	fputs($connect, "Subject: $subject\r\n");
	fputs($connect, "From: $senderName <$from>\r\n");
	fputs($connect, "To: $toKey  <$toValue>\r\n");
	fputs($connect, "X-Sender: <$from>\r\n");
	fputs($connect, "Return-Path: <$from>\r\n");
	fputs($connect, "Errors-To: <$from>\r\n");
	fputs($connect, "X-Mailer: PHP\r\n");
	fputs($connect, "X-Priority: 3\r\n");
	fputs($connect, "Content-Type: text/html; charset=iso-8859-1\r\n");
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
    ini_restore(@sendmail_from);
}

function display_vals($_POST)
{
        $return = "<html><header></header><body>";
        $return .= "<h1 align=\"center\">RESULTS</h1>";
	$return .= "<TABLE ALIGN=CENTER WIDTH=\"100%\" BORDER=\"1\">\n";
	$return .= "<TR><TH width=20%>FIELD</TH><TH>VALUE</TH></TR>";
	$return .= "<tr><td><b>Store IP:</b></TD>\n<TD>$_SERVER[REMOTE_ADDR]</TD></TR>\n";
	foreach ( $_POST as $key => $value ) {
	    if((strcmp($key, "Submit") != 0) && (strcmp($key, "submitto") != 0))
	    {
		$return .= "<TR><TD><b>$key:</b></TD>\n<TD>$value</TD></TR>\n";
	    }
	}
	$return .= "</TABLE>";
        $return .= "</body></html>";
        return $return;
}
?>
