<?php

function mail_to($sender, $recipient, $subject, $message){
    mail("$recipient", "$subject", "$message",
         "From: $sender\r\n"
        ."Reply-To: $sender\r\n"
        ."Content-Type: text/html; charset=iso-8859-1\r\n"
        ."X-Mailer: PHP/" . phpversion());
}
function socketmail($toArray, $from, $senderName, $subject, $message)
{
    // $toArray format --> array("Name1" => "address1", "Name2" => "address2", ...)
    ini_set(sendmail_from, "$from");
    $connect = fsockopen ("dogbert", 25, $errno, $errstr, 30) or die("Could not talk to the sendmail server!");
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
    ini_restore(sendmail_from);
}
    // $toArray format --> array("Name1" => "address1", "Name2" => "address2", ...)
    ini_set(sendmail_from, "webadmin@sharis.com");
    $connect = fsockopen ("dogbert", 25, $errno, $errstr, 30) or die("Could not talk to the sendmail server!");
    $rcv = fgets($connect, 1024);
    fputs($connect, "HELO {$_SERVER['SERVER_NAME']}\r\n");
    $rcv = fgets($connect, 1024);
    while (list($toKey, $toValue) = each($toArray)) {
	fputs($connect, "MAIL FROM:jsandlin@sharis.com\r\n");
	$rcv = fgets($connect, 1024);
	fputs($connect, "RCPT TO:$toValue\r\n");
	$rcv = fgets($connect, 1024);
	fputs($connect, "DATA\r\n");
	$rcv = fgets($connect, 1024);
	fputs($connect, "Subject: $subject\r\n");
	fputs($connect, "From: James Sandlin <jsandlin@sharis.com>\r\n");
	fputs($connect, "To: $toKey  <$toValue>\r\n");
	fputs($connect, "X-Sender: <jsandlin@sharis.com>\r\n");
	fputs($connect, "Return-Path: <jsandlin@sharis.com>\r\n");
	fputs($connect, "Errors-To: <jsandlin@sharis.com>\r\n");
	fputs($connect, "X-Mailer: PHP\r\n");
	fputs($connect, "X-Priority: 3\r\n");
	fputs($connect, "Content-Type: text/plain; charset=iso-8859-1\r\n");
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
function display_all_post_n_get_vars(){
    $toReturn = "";
    $toReturn = "<TABLE ALIGN=CENTER WIDTH=\"50%\" BORDER=\"1\">\n"
             . "<TR><TH>KEY</TH><TH>VALUE</TH></TR>";
    foreach ( $_POST as $key => $value )
    { 
	$toReturn .= "<TR><TD>$key</TD>\n<TD>$value</TD></TR>\n";
    }
    $toReturn .= "</TABLE>"
             .  "<hr size=\"10\" width=\"100%\">"
             .  "<h1 align=\"center\">GET</h1>"    
             .  "<TABLE ALIGN=CENTER WIDTH=\"50%\" BORDER=\"1\">\n"
             .  "<TR><TH>KEY</TH><TH>VALUE</TH></TR>";
    foreach ( $_GET as $key => $value )
    { 
	$toReturn .= "<TR><TD>$key</TD>\n<TD>$value</TD></TR>\n";
    }
    $toReturn .= "</TABLE>";
    return $toReturn;
}

function display_all_post_vars_and_values($_POST){
	echo("<TABLE ALIGN=CENTER WIDTH=\"50%\" BORDER=\"1\">\n");
	echo("<TR><TH>KEY</TH><TH>VALUE</TH></TR>");
	foreach ( $_POST as $key => $value ) { 
		echo ("<TR><TD>$key</TD>\n<TD>$value</TD></TR>\n");
	}
	echo("</TABLE>");
}

function connect_to_mssql($host_name, $user_name, $password, $database){
    $dbcnx = MSSQL_CONNECT($host_name,$user_name,$password) or DIE("<h1>DATABASE FAILED TO RESPOND.</h1>"); 
    if(!mssql_select_db("$database")){
            return false;
            exit();
    }
    return $dbcnx;
}
function display_mssql_db($host_name, $user_name, $password, $database){
    if(!(connect_to_mssql($host_name, $user_name, $password, $database)))
    {
        return("<HTML><head>ERROR</head><body><h1>DATABASE $database FAILED TO RESPOND.</h1></body></HTML>");
    }
    $toReturn = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 //EN\">\n"
              . "<HTML>\n<HEAD>\n"
              . "<TITLE>Tables</TITLE>\n"
              . "<meta http-equiv=\"Content-Type\" CONTENT=\"text/html; charset=iso-8859-1\">\n"
              . "</HEAD>\n"
              . "<BODY background = \"../Logo.jpg\" TEXT=#000000>\n";
    $selectTables = "select name from sysobjects where type=\"U\"";// and name like 'emp%'";
    $tableQuery = mssql_query($selectTables);
    $names = array();
    while($row = mssql_fetch_row($tableQuery))
    {
	array_push($names, $row[0]);
	//display_table($row[0]);
    }
    $toReturn .= display_tables($names, $host_name, $database);
    $toReturn .= "</BODY>\n</HTML>";
    return $toReturn;
}

function get_mssql_datetime()
{
    return strftime("%b %d %Y %H:%M:%S",time());
}
/*
 * display_table takes a table name as an argument and displays that table in HTML format.
 * REQUIREMENT: already connected to DB
 * ARGUMENT: table name
 */
function display_tables($names, $host="Your", $db=" Tables")
{
    if(strcmp($host, "Your") != 0)
	$title = $host . ":" . $db;
    else
	$title = $host . $db;
	
    $tableNames = "<table border=4 align=center>\n"
              . "\t<tr valign=\"middle\">\n\t\t<td colspan=3>"
	      . "<a name=\"title\"><h2 align=center>$title</h2></a>\n\t\t</td>\n\t</tr>\n";
    for($i=0, $toReturn = ""; $i < sizeof($names);$i++)
    {
	if($i % 3 == 0)
	{
	    $tableNames .= "\t<tr>\n";
	}
	$tableNames .= "\t\t<td align=center>"
		    .  "<a href=\"#$names[$i]\">$names[$i]</a><br>"
		    .  "</td>\n";
	if($i % 3 == 2)
	{
	    $tableNames .= "\t</tr>\n";
	}
	$rowNum = 0;
	$row_color = "#999999";
	// Display the table name
	$toReturn .= "<a name=\"$names[$i]\">"
		  .  "<h3 align=center>$names[$i]</H3>"
		  .  "</a>";
	
        // Get the table data
	$query = "SELECT * FROM " . $names[$i];
	$temp = mssql_query($query);
	
	/* Display the Field Names. */
	$fields = mssql_num_fields ($temp);
	$toReturn .="<TABLE border=1 align=center>";
	for ( $f = 0 ; $f < $fields ; $f++ ){
		$name = mssql_fetch_field($temp, $f);
		$toReturn .= "<th bgcolor=\"#EE00DD\">".$name->name."</th>\n";
	}
	/* End Display the Field Names */
	
	/* Display the Field Data. */
	$numCols = 0;
	while($row = mssql_fetch_row($temp)){
		$toReturn .="<tr ";
		if($rowNum++%2 == 0){
			$toReturn .="bgcolor=\"$row_color\" ";
		}
		$toReturn .= "valign=\"top\">";
		for($x=0; $x < sizeof($row); $x++){
			if(is_float($row[$x])){
				$toReturn .="<TD>" . date("Y\-m\-d H\:i\:s", stripslashes($row[$x])) . "</TD>";
			}
			else
				$toReturn .="<TD>" . stripslashes($row[$x]) . "</TD>";
		    $numCols++;
		}
		$toReturn .="</tr>\n";
	}
	$toReturn .= "<tr><td colspan=$numCols align=\"center\">"
		  .  "<a href=\"#title\"><h3>Back to the top</h3></a></td></tr>\n";
	/* End Display the Field Data. */
	$toReturn .="</TABLE>";
    }
    $tableNames .= "</table>";
    return($tableNames . $toReturn);
}

/*
* This function will convert a multidimensional array into an XML TRee
* sTRing arr2xml (array array, [sTRing TRee_name], [[int level]) 
* use the second argument if you want to specify a name for the top of the TRee - 
* 	otherwise it defaults to level0. the first call to the function is kind of a 
* 	dummy top level that wraps the array inside another array and calls the function for real. 
* dont use the third argument. thats used to keep TRack of the levels in all the recursive calls. 
*/
function arr2xml ($arr){
	if (func_num_args() < 3)
	{
		$wrapper = (func_num_args() < 2) ? array ($arr) : array (func_get_arg(1)=>$arr);
		$xml = arr2xml ($wrapper, '', 0);
	}
	else
	{
		$level = func_get_arg (2);
		while (list ($key, $val) = each ($arr))
		{
			if ($key === (int)$key) $key = 'level'.$level;
				$xml .= '<'.$key.'>';
			if (gettype ($val) == 'array')
			{
				$xml .= arr2xml ($val, '', $level+1);
			}
			else
			{
				$xml .= $val;
			}
			$xml .= '</'.$key.'>';
			}
	}
	return $xml;
}

// Determine if the alpha argument is a lower case letter.
function is_lowercase_alpha($input){
	return (("a" <= $input && $input <= "z"))?true:false;
}


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

function print_line($line)
{
    echo($line . "<BR>");
}

function timeStame()
{
    return(strftime("%b %d %Y %H:%M:%S",time()));
}

function selectDate (
                        $sel_d = 0       // selected day
                      , $sel_m = 0       // selected month
                      , $sel_y = 0       // selected year
                      , $var_d = "d"     // name for day variable
                      , $var_m = "m"     // name for month variable
                      , $var_y = "y"     // name for year variable
                      , $min_y = 0       // minimum year
                      , $max_y = 0       // maximum year
                      , $enabled = true  // enable drop-downs?
                    ) {
  // --------------------------------------------------------------------------
  // First of all, set up some sensible defaults
  // Default day is today
  if ($sel_d == 0) 
    $sel_d = date("j");
  // Default month is this month
  if ($sel_m == 0) 
    $sel_m = date("n");
  // Default year is this year
  if ($sel_y == 0)
    $sel_y = date("Y");
  // Default minimum year is 1978(the day 
  if ($min_y == 0) 
    $min_y = 1978;
    //$min_y = date("Y");
  // Default maximum year is this year
  if ($max_y == 0) 
    $max_y = $sel_y ;
  // --------------------------------------------------------------------------
  // Start off with the drop-down for Days
  // Start opening the select element
  echo "<select name=\"". $var_d. "\"";
  // Add disabled attribute if necessary
  if (!$enabled) 
    echo " disabled=\"disabled\"";
  // Finish opening the select element
  echo ">\n";
  // Loop round and create an option element for each day (1 - 31)
  for ($i = 1; $i <= 31; $i++) {
    // Start the option element
    echo "\t<option value=\"". $i. "\"";
    // If this is the selected day, add the selected attribute
    if ($i == $sel_d) 
      echo " selected=\"selected\"";
    // Display the value and close the option element
    echo ">". $i. "</option>\n";
  }
  // Close the select element
  echo "</select>\n";
  // --------------------------------------------------------------------------
  // Now do the drop-down for Months
  // Start opening the select element
  echo "<select name=\"". $var_m. "\"";
  // Add disabled attribute if necessary
  if (!$enabled) 
    echo " disabled=\"disabled\"";
  // Finish opening the select element
  echo ">\n";
  // Loop round and create an option element for each month (Jan - Dec)
  for ($i = 1; $i <= 12; $i++) {
    // Start the option element
    echo "\t<option value=\"". $i. "\"";
    // If this is the selected month, add the selected attribute
    if ($i == $sel_m) 
      echo " selected=\"selected\"";
    // Display the value and close the option element
    echo ">". date("F", mktime(3, 0, 0, $i)). "</option>\n";

  }
  // Close the select element
  echo "</select>\n";
  // --------------------------------------------------------------------------
  // Finally, the drop-down for Years
  // Start opening the select element
  echo "<select name=\"". $var_y. "\"";
  // Add disabled attribute if necessary
  if (!$enabled) 
    echo " disabled=\"disabled\"";
  // Finish opening the select element
  echo ">\n";
  // Loop round and create an option element for each year ($min_y - $max_y)
  for ($i = $min_y; $i <= $max_y; $i++) {
    // Start the option element
    echo "\t<option value=\"". $i. "\"";
    // If this is the selected year, add the selected attribute
    if ($i == $sel_y) 
      echo " selected=\"selected\"";
    // Display the value and close the option element
    echo ">". $i. "</option>\n";
  }
  // Close the select element
  echo "</select>\n";
}

function getNumDaysInMonths($year)
{
    //The year which is divisible by 4 and not divisible by 100 or divisible by 400
    //is a leap year
    if((($year % 4 == 0) && ($year%100 != 0)) || ($year % 400==0))
    {
	return array(31,29,31,30,31,30,31,31,30,31,30,31);
    }
    else
    {
	return array(31,28,31,30,31,30,31,31,30,31,30,31);
    }
}
?>