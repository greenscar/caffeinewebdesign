<?php
 	$firstName = $_POST["FirstName"];
 	$lastName = $_POST["LastName"];
 	$address = $_POST["Address"];
 	$city = $_POST["City"];
 	$state = $_POST["State"];
 	$zip = $_POST["Zip"];
 	$phone = $_POST["Phone"];
 	$eMail = $_POST["e_email"];
 	$title = $_POST["Title"];
 	$position = $_POST["Position"];
 	$locations = $_POST["Locations"];
 	$availability = $_POST["Availability"];
	$schoolCollege = $_POST["SchoolCollege"];
	$schoolCollegeAddress = $_POST["SchoolCollegeAddress"];


	$body = "<table border=\"1\" width=\"50%\" align=\"left\" cellpadding=\"1\" cellspacing=\"1\">\n";
	$body .= "<tr><th colspan = 2><h2>$firstName $lastName On-Line Application</h2></th></tr>\n";
	$body .= "<tr><th colspan=2>PERSONAL INFORMATION</th></tr>\n";
	$body .= "<tr><td><b>First Name:</b></td><td>$firstName</td></tr>\n";
	$body .= "<tr><td><b>Last Name:</b></td><td>$lastName</td></tr>\n";
	$body .= "<tr><td><b>Address:</b></td><td>$address</td></tr>\n";
	$body .= "<tr><td><b>Address:</b></td><td>$city</td></tr>\n";
	$body .= "<tr><td><b>State:</b></td><td>$state</td></tr>\n";
	$body .= "<tr><td><b>Zip:</b></td><td>$zip</td></tr>\n";
	$body .= "<tr><td><b>Phone:</b></td><td>$phone</td></tr>\n";
	$body .= "<tr><td><b>E-Mail:</b></td><td>$eMail</td></tr>\n";
	$body .= "<tr><td colspan=2></td></tr>\n";
	$body .= "<tr><th colspan=2>JOB INTEREST</th></tr>\n";
	$body .= "<tr><td><b>Title:</b></td><td>$title</td></tr>\n";
	$body .= "<tr><td><b>Position:</b></td><td>$position</td></tr>\n";
	$body .= "<tr><td><b>Locations:</b></td><td>$locations</td></tr>\n";
	$body .= "<tr><td><b>Your Availability:</b></td><td>$availability</td></tr>\n";
	$body .= "<tr><td colspan=2></td></tr>\n";
	$body .= "<tr><th colspan=2>EDUCATION</th></tr>\n";
	$body .= "<tr><td><b>School:</b></td><td>$schoolCollege</td></tr>\n";
	$body .= "<tr><td><b>School Address:</b></td><td>$schoolCollegeAddress</td></tr>\n";
	
	if(isset($_POST["MajorDegree"])){
		$major = $_POST["MajorDegree"];
		$body .= "<tr><td><b>Major:</b></td><td>$major</td></tr>\n";
	}
	if(isset($_POST["CompanyName"])){
		$companyName = $_POST["CompanyName"];
		$body .= "<tr><th colspan = 2>EMPLOYMENT HISTORY 1</th></tr>\n";	
		$body .= "<tr><td><b>Company:</b></td><td>$companyName</td></tr>\n";
	}
	if(isset($_POST["DatesofEmployement"])){
		$datesofEmployement = $_POST["DatesofEmployement"];
		$body .= "<tr><td><b>Dates of Employment:</b></td><td>$datesofEmployement</td></tr>\n";
	}
	if(isset($_POST["YourTitle"])){
		$yourTitle = $_POST["YourTitle"];
		$body .= "<tr><td><b>Title:</b></td><td>$yourTitle</td></tr>\n";
	}
	if(isset($_POST["Responsibilities"])){
		$responsibilities = $_POST["Responsibilities"];
		$body .= "<tr><td><b>Responsibilities:</b></td><td>$responsibilities</td></tr>\n";
	}
	if(isset($_POST["CompanyName2"])){
		$companyName2 = $_POST["CompanyName2"];
		$body .= "<tr><th colspan = 2>EMPLOYMENT HISTORY 2</th></tr>\n";	
		$body .= "<tr><td><b>Company:</b></td><td>$companyName2</td></tr>\n";
	}
	if(isset($_POST["DatesofEmployment2"])){
		$datesOfEmployment2 = $_POST["DatesofEmployement2"];
		$body .= "<tr><td><b>Dates of Employment:</b></td><td>$datesOfEmployment2</td></tr>\n";
	}
	if(isset($_POST["YourTitle2"])){
		$yourTitle2 = $_POST["YourTitle2"];
		$body .= "<tr><td><b>Title:</b></td><td>$yourTitle2</td></tr>\n";
	}
	if(isset($_POST["Responsibilities2"])){
		$responsibilities2 = $_POST["Responsibilities2"];
		$body .= "<tr><td><b>Responsibilities:</b></td><td>$responsibilities:</td></tr>\n";
	}
	$body .= "</table>\n";
	
	$headers  = "MIME-Version: 1.0\r\n";
	$headers .= "Content-type: text/html; charset=iso-8859-1\r\n";
	$headers .= "From: $eMail\r\nReply-To: $eMail\r\nX-Mailer: PHP/" . phpversion() . "\r\n";
	$receiver = "Employment@sharis.com";
	$mail_recipient = $receiver ." <". $receiver .">";
	$subject = "Job Application: $firstName $lastName";
	
	mail("$receiver", "$subject", $body, $headers);
	
	global $outTitle;
	$outTitle = "Thank you $firstName $lastName";
	global $outMessage;
	$outMessage = "Your transmission was successful.";
	
function onlyDigits($value){
	return ereg('[0-9]+', $value);
}
function onlyWords($value){
	return ereg('[0-9A-Za-z]*', $value);
}
function goodEmail($value){
	return ereg('[0-9A-Za-z]*@[0-9A-Za-z]*.[a-z]{3}', $value);
}
function goodPhone($value){
	$x = ereg('[0-9]{10}', $value);
	if(!$x){
		$x = ereg('[0-9]{3}-[0-9]{3}-[0-9]{4}', $value);
	}
	if(!$x){
		$x = ereg('[0-9]{1}-[0-9]{3}-[0-9]{3}-[0-9]{4}', $value);
	}
	return $x;
}
 ?>
 <html>
 <head>
 <title> <? echo($GLOBALS["outTitle"]); ?></title>
 </head>

<body bgcolor="#FFFFFF" text="#000000" link="#0000FF" vlink="#800080" alink="#FF0000">
<div align="center"><center>

<table border="2" cellpadding="0" cellspacing="0" bgcolor="#FFD26B" bordercolor="#699A69"
width="500">
  <tr>
    <td><h2 align="center"><?  echo($GLOBALS["outTitle"]); ?></h2>
    <p align="center"><?  echo($GLOBALS["outMessage"]); ?></p></td>
  </tr>
  <tr><td align="center"><a href="http://www.sharis.com">HOME</a></td></tr>
</table>
<p><br><p>
<table border="2" cellpadding="0" cellspacing="0" bgcolor="#FFD26B" bordercolor="#699A69"
width="500">
</table>
</center></div>
</body>