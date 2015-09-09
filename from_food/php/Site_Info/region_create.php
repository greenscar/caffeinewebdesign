<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Please Enter The Region</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link rel="stylesheet" type="text/css" href="./inc_files/template.css"/>
</head>

<body>
<form name="ldp" method="post" action="region_create_process.php">

<div class="center">
	<div class="grey_box">
		<div class="spacer"></div>
<?php
if(isset($_GET["disp"]))
{
?>
		<div class="row_center">
			You have entered a region that is already in the list below.
		</div>
		<div class="spacer"></div>
<?php
}
?>
		<div class="row">
			<span class="left">
				Region Number:
			</span>
			<span class="right_field">
				<input type="text" class="name" name="number">
			</span>
		</div>
		<div class="spacer">&nbsp;</div>		
		<div class="row">
			<span class="left">
				Region Name:
			</span>
			<span class="right_field">
				<input type="text" class="name" name="name">
			</span>
		</div>
		<div class="spacer">&nbsp;</div>		
		<div class="row">
			<span class="left">
				Region Mgr Emp ID:
			</span>
			<span class="right_field">
				<input type="text" class="name" name="mgr_emp_id">
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
	<div class="grey_box">
		<div class="spacer"></div>

	<div class="row_center">
		<table align="center" border="0" width=90%>
		<tr>
			<td colspan="3">
				<div class="row_center">
					<span class="left">
						<h3>Existing Regions</h3>
					</span>
				</div>
			</td>
		</tr>
		<tr>
			<td align="center" width="25%">
				<span class="white_center">
					<u>Number</u>
				</span>
			</td>
			<td align="center" width="50%">
				<span class="white_center">
					<u>Name</u>
				</span>
			</td>
			<td align="center" width="25%">
				<span class="white_center">
					<u>Mgr Emp ID</u>
				</span>
			</td>
		</tr>				
<?php
require_once("./classes/Admin.inc");
$admin = new Admin();
$admin->db_load_region_list();
for($i=0; $i < $admin->region_list_size(); $i++)
{
	$region = $admin->get_a_region_from_list($i);
?>
		<tr>
			<td align="center">
			<span class="white_center">
				<?php echo($region->number); ?>
			</span>
			</td>
			<td align="center">
			<span class="white_center">
				<?php echo($region->name); ?>
			</span>
			</td>
			<td align="center">
			<span class="white_center">
				<?php echo($region->mgr_emp_id); ?>
			</span>
			</td>
		</tr>
<?php
}
?>
		</table>
	</div>
</div>
</form>
</body>
</html>