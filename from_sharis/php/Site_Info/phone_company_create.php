<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Please Enter The Phone Company's Name</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link rel="stylesheet" type="text/css" href="./inc_files/template.css"/>
</head>

<body>
<form name="ldp" method="post" action="phone_company_create_process.php">

<div class="center">
	<div class="grey_box_70">
		<div class="spacer"></div>
<?php
if(isset($_GET["disp"]))
{
?>
		<div class="row_center">
			You have entered a company that is already in the list below.
		</div>
		<div class="spacer"></div>
<?php
}
?>
		<div class="row">
			<span class="left">
				&nbsp;&nbsp;Phone Company Name:
			</span>
			<span class="right_field">
				<input type="text" class="name" name="name">&nbsp;&nbsp;
			</span>
		</div>
		<div class="spacer">&nbsp;</div>
		<div class="row_center">
			<input class="grey" type="submit" name="submit" value="Submit">
		</div>
		<div class="spacer">&nbsp;</div>
	</div>
</div>
</form>
<div class="center">
	<div class="grey_box_70">
		<div class="spacer"></div>
		<div class="row_center">
			<u>Existing Phone Companies</u>
		</div>
		<div class="spacer"></div>
		<div class="grey_box_25">
<?php
require_once("./classes/Phone_System.inc");
$ps = new Phone_System();
$ps->db_load_phone_co_list();

for($i=0; $i < $ps->phone_co_list_size(); $i++)
{
	$phone_co = $ps->get_a_phone_co_from_list($i);
?>
		<div class="row_center">
			<?php echo($phone_co->name); ?>
		</div>
<?php
}
?>
		</div>
	</div>
</div>
</body>
</html>