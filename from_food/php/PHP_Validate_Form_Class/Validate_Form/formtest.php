<html>
<head>
<title>Form Validation Test</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<?php
 
#Just a simple form to show you how to use the form Validation class
#Any comments suggestions or bug feel free to e-mail me george@experimentalmonkey.co.uk

  if($_POST['submit']) {
  	
  	include_once("ValidateForm.class.php");
  	
  		$fv = new Validateform();
  		
    $fv->ValidateEmpty("name","This is a required field");
	
	$fv->ValidateEmail("e-mail","This is not a valid e-mail address");
	
	$fv->ValidateUrl("url","This is not a valid URL");
	
	$fv->ValidateLen("username",5,"Your username must be longer than 5 characters");
	
	$fv->ValidateIsNum("number","You can only enter a number in this field");
	
	$fv->ValidateRange("number_range",10,20,"You must enter a value between 10 and 20");
	
	if($fv->ErrorList()) {
		
		$fv->DisplayError();
		
	} else {
		
		//process your data here
		
	print("Thank you for filling in my form");
		
	}
	
}
	
?>
<form name="form1" method="post" action="<?=$PHP_SELF?>">
  <table width="551" border="0" cellspacing="0" cellpadding="5">
    <tr>
      <td width="150">Name:</td>
      <td width="381"> 
        <input type="text" name="name" /></td>
    </tr>
    <tr>
      <td>E-mail</td>
      <td><input type="text" name="e-mail" /></td>
    </tr>
    <tr>
      <td>URL</td>
      <td><input type="text" name="url" /></td>
    </tr>
    <tr>
      <td>Username *Min Length 5</td>
      <td><input type="text" name="username" /></td>
    </tr>
    <tr>
      <td>Number Only</td>
      <td><input type="text" name="number" /></td>
    </tr>
    <tr>
      <td>Number Between 10-20</td>
      <td><input type="text" name="number_range" /></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><input type="submit" name="submit" value="submit"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </table>
</form>
</body>
</html>
  	