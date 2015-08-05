<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Please Enter The Phone Type</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link rel="stylesheet" type="text/css" href="./inc_files/template.css"/>
</head>

<body>
<form name="ldp" method="post" action="phone_type_create_process.php">

<div class="center">
	<div class="grey_box_70">
		<div class="spacer"></div>
<?php
if(isset($_GET["disp"]))
{
?>
		<div class="row_center">
			You have entered a line type that is already in the list below.
		</div>
		<div class="spacer"></div>
<?php
}
?>
		<div class="row">
			<span class="left">
				Phone Type Name:
			</span>
			<span class="right_field">
				<input type="text" class="name" name="name">&nbsp;&nbsp;
			</span>
		</div>
		<div class="spacer">&nbsp;</div>
		
		<div class="row">
			<span class="left">
				Phone Type Description:
			</span>
			<span class="right_area">
				<textarea class="description" name="description"></textarea>
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
		<div class="center">
		<table align="center" border="0" width=90%>
		<tr>
			<td colspan="2">
				<div class="row_center">
					<h3>Existing Phone Types</h3>
				</div>
			</td>
		</tr>
		<tr>
			<td align="center" width="50%">
				<span class="white_center">
					<u>Type Name</u>
				</span>
			</td>
			<td align="center" width="50%">
				<span class="white_center">
					<u>Type Description</u>
				</span>
			</td>
		</tr>				
<?php
require_once("./classes/Phone_System.inc");
$ps = new Phone_System();
$ps->db_load_phone_type_list();
for($i=0; $i < $ps->phone_type_list_size(); $i++)
{
	$phone_line = $ps->get_a_phone_type_from_list($i);
?>
		<tr>
			<td align="center">
			<span class="white_center">
				<?php echo($phone_line->name); ?>
			</span>
			</td>
			<td align="center">
			<span class="white_center">
				<?php echo($phone_line->description); ?>
			</span>
			</td>
		</tr>
<?php
}
?>
		</table>
	</div>
</div>
</body>
</html>