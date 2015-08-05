<?php
require_once("./classes/Admin.inc");
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Please Enter The District</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link rel="stylesheet" type="text/css" href="./inc_files/template.css"/>
</head>

<body>
<form name="ldp" method="post" action="district_create_process.php">

<div class="center">
	<div class="grey_box">
		<div class="spacer"></div>
<?php
if(isset($_GET["disp"]))
{
?>
		<div class="row_center">
			You have entered a district that is already in the list below.
		</div>
		<div class="spacer"></div>
<?php
}
?>
		<div class="row">
			<span class="left">
				District Number:
			</span>
			<span class="right_field">
				<input type="text" class="name" name="number">
			</span>
		</div>
		<div class="spacer">&nbsp;</div>		
		<div class="row">
			<span class="left">
				District Name:
			</span>
			<span class="right_field">
				<input type="text" class="name" name="name">
			</span>
		</div>
		<div class="spacer">&nbsp;</div>		
		<div class="row">
			<span class="left">
				District Mgr Emp ID:
			</span>
			<span class="right_field">
				<input type="text" class="name" name="mgr_emp_id">
			</span>
		</div>
		<div class="spacer">&nbsp;</div>
		<div class="row">
			<span class="left">
				Region Num:
			</span>
			<span class="right_field">
				<select class="black_wide" name="region_num">
<?php
    $admin = new Admin();
    $admin->db_load_region_list();
    for($i=0; $i < $admin->region_list_size(); $i++)
    {
	$region = $admin->get_a_region_from_list($i);
        echo("<option value=\"" . $region->number . "\">" . $region->number . "</option>\n");
    }
?>
                                </select>
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
			<td colspan="4">
				<div class="row_center">
				    <h3>Existing Districts</h3>
				</div>
			</td>
		</tr>
		<tr>
			<td align="center" width="10%">
				<span class="white_center">
					<u>Region</u>
				</span>
			</td>
			<td align="center" width="20%">
				<span class="white_center">
					<u>Number</u>
				</span>
			</td>
			<td align="center" width="50%">
				<span class="white_center">
					<u>Name</u>
				</span>
			</td>
			<td align="center" width="20%">
				<span class="white_center">
					<u>Mgr Emp ID</u>
				</span>
			</td>
		</tr>				
<?php
$admin->db_load_district_list();
for($i=0; $i < $admin->district_list_size(); $i++)
{
	$district = $admin->get_a_district_from_list($i);
?>
		<tr>
			<td align="center">
			<span class="white_center">
				<?php echo($district->region->number); ?>
			</span>
			</td>
			<td align="center">
			<span class="white_center">
				<?php echo($district->number); ?>
			</span>
			</td>
			<td align="center">
			<span class="white_center">
				<?php echo($district->name); ?>
			</span>
			</td>
			<td align="center">
			<span class="white_center">
				<?php echo($district->mgr_emp_id); ?>
			</span>
			</td>
		</tr>
<?php
}
?>
		</table>
	</div> <!--row_center-->
</div>
</form>
</body>
</html>