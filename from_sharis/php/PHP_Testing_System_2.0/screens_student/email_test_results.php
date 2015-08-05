<?php
function email_results($emp_seq){
	include("../inc_files/variables.inc");
	include("../inc_files/question_fxns.inc");
	$take_num = "SELECT MAX(take_seq) AS take_seq FROM test_taken_dtl " .
				"WHERE emp_seq = $emp_seq";
	$the_num = mssql_query($take_num);
	$row = mssql_fetch_array($the_num);
	$take_seq = $row["take_seq"];
		
	//GET THE INFO ABOUT THE TEST TAKEN
	$tr = "SELECT * FROM test_taken_dtl WHERE take_seq = $take_seq";
	$tr = mssql_query($tr);
	$row = mssql_fetch_array($tr);
	$test_seq = $row["test_seq"];
	$emp_seq  = $row["emp_seq"];
	$date_taken = $row["date_taken"];
	$duration = $row["duration_secs"];
	
	//GET THE TEST INFORMATION
	$tr = "SELECT * FROM test_header_def WHERE test_seq = $test_seq";
	$tr = mssql_query($tr);
	$row = mssql_fetch_array($tr);
	$test_name = stripslashes($row["test_name"]);
	$creation_date = $row["creation_date"];
	$category_seq = $row["category_seq"];	
	
	//GET THE CATEGORY NAMES
	$tr = "SELECT category_name FROM test_category_def WHERE category_seq = $category_seq";
	$tr = mssql_query($tr);
	$row = mssql_fetch_array($tr);
	$category_name = stripslashes($row["category_name"]);
	
	//GET THE TEST TAKER'S INFORMATION
	$tr = "SELECT * FROM emp_def WHERE emp_seq = $emp_seq";
	$tr = mssql_query($tr);
	$row = mssql_fetch_array($tr);
	$first_name = $row["first_name"];
	$last_name = $row["last_name"];
	$ssn = $row["ssn"];
	$emp_num = $row["emp_num"];
	$email_body = "";
	/***************************************************************************
	* GENERATE EMAIL HEADER
	***************************************************************************/
	$title = "$first_name $last_name - $test_name";
	$email_body = "<HTML><HEAD><TITLE>$title</TITLE></HEAD><BODY>";
	//$email_body = include("../inc_files/html_header_no_fxns.inc");
	
	$email_body .= "<TABLE align = \"CENTER\">";
	$email_body .= "<tr>\n\t<td colspan=\"2\"><h2 align = \"CENTER\">EMPLOYEE</h2></td>\n</tr>\n";
	$email_body .= "<tr>\n\t<td>Name:</td>\n\t<td>$first_name $last_name</td>\n</tr>\n";
	$email_body .= "<tr>\n\t<td>SSN:</td>\n\t<td>$ssn</td>\n</tr>\n";
	$email_body .= "<tr>\n\t<td>Employee Number:</td>\n\t<td>$emp_num</td>\n</tr>\n";
	$email_body .= "<tr>\n\t<td>Date Taken:</td>\n\t<td>$date_taken</td>\n</tr>\n";
	$email_body .= "<TR><TD><P>DURATION OF TEST:</P></TD>\n\t\t\t\t\t\t";
	
	$h = intval($duration / 3600);
	$duration -= $h*3600;
	$m = intval($duration / 60);
	$duration -= $m*60;
	$s = intval($duration);
	
	$email_body .= "<TD>$h hrs : $m min : $s sec</TD></TR>\n\t\t\t\t\t";
	$email_body .= "<tr>\n\t<td>Time to Complete:</td>\n\t<td>$date_taken</td>\n</tr>\n";
	$email_body .= "<tr>\n\t<td>Test Category:</td>\n\t<td>$category_name</td>\n</tr>\n";
	$email_body .= "<tr>\n\t<td>Test Name:</td>\n\t<td>$test_name</td>\n</tr>\n";
	$email_body .= "</TABLE>";
	$email_body .= "<br><br>";
	$email_body .= "<TABLE align = \"CENTER\">";
	$email_body .= "<tr>\n\t<td width=\"1000\" colspan=\"2\"><h2 align = \"CENTER\">$test_name</h2></td>\n</tr>\n";
	$email_body .= "</TABLE>";
	/***************************************************************************
	* END GENERATE EMAIL HEADER
	***************************************************************************/
	$email_body .="<TABLE WIDTH=95%>";
	/*
	* GET QUESTION INFO
	* At this point, we have these variables:
	* $take_seq, $test_seq, $emp_seq
	* $date_taken, $test_name, $creation_date, 
	* $category_seq, $category_name, $ssn
	* $first_name, $last_name, $emp_num
	*/
 	$query = "SELECT * FROM question_def, question_type_def " .
			 "WHERE question_def.test_seq = $test_seq AND " . 
			 "question_type_def.type_name != \"choice\" AND " .
			 "question_def.type_seq = question_type_def.type_seq " .
			 "ORDER BY question_def.question_num, question_def.question_seq;";
	$all_questions = mssql_query($query);
	//DISPLAY ALL QUESTIONS. THE DISPLAY FORMAT WILL BE BASED ON THE type_name
	
	while($question = mssql_fetch_array($all_questions)){  
		$question_seq = $question["question_seq"];
		$question_num = $question["question_num"];
		$type_name = $question["type_name"];
		$function = "add_" . $type_name . "_to_email";
		$email_body .= "\t<TR><TD>\n";
		$email_body .= call_user_func($function, $question, $take_seq, $all_questions);
		$email_body .= "\t</TD></TR>\n";
	}   
	$email_body .= "</TABLE>\n";	
	$graders_email = "jsandlin@sharis.com";//"marnie@treat-me.biz";
	$senders_email = $first_name . "_" . $last_name . "@sharis.com";
	$graders_name = "\"Sharis Training\"";
	$headers  = "MIME-Version: 1.0\r\n";
	$headers .= "Content-type: text/html; charset=iso-8859-1\r\n";
	$headers .= "From: $senders_email\r\nReply-To: $senders_email\r\nX-Mailer: PHP/" . phpversion() . "\r\n";
	
	$mail_recipient = $graders_name." <". $graders_email .">";
	$subject = "$first_name $last_name, $category_name exam # $test_seq";
	//echo($email_body);
	return mail("$graders_email", "$subject", $email_body, $headers);
}
?>