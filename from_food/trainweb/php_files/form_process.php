<html>
<header>

<?php
require_once("../php_files/functions.inc");
$from = "aStudent@sharis.com";
if(empty($_POST["name"]))
	$senderName = "Anonymous";
else
	$senderName = $_POST["name"];
$formName = $_POST["formName"] . " Form";
if(strcmp($_POST["submitto"], "all") == 0)
{
   $tos = array("Vickie Irish" => "virish@sharis.com",
                "Debbie Wolfe" => "dwolfe@sharis.com",
                "Esther Klein" => "eklein@sharis.com",
                "Johanna Hensley" => "jhensley@sharis.com");
   $recipient = "All Teachers";
}
else
{
	if(strpos($_POST["submitto"], "@sharis.com"))
	{
		$recipient = $_POST["submitto"];
	}
	else
	{
		$recipient = $_POST["submitto"] . "@sharis.com";
	}
   //$recipient = $_POST["submitto"] . "@sharis.com";
   //$recipient = "kvandegrift@sharis.com";
   $tos = array("Training Dept" => "$recipient");
   $tos["Tom Matson"] = "tmatson@sharis.com";
   //$tos["James Sandlin"] = "jsandlin@sharis.com";
}
//$tos = array("Training Dept" => "jsandlin@sharis.com");
socketmail($tos, $from, $senderName, $formName, display_vals($_POST));
echo("<script language = \"javascript\" type=\"text/javascript\">");
echo("alert(\"Thank you. Your " . $_POST["formName"] . " has been sent to $recipient\\nYou will now be asked if you would like this window closed. SELECT YES\");");
?>
window.close();
</script>
</header>
<body></body>
</html>
