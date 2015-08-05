<?php
    session_start();
    //require_once("./inc_files/check_session.inc");
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Please Enter The Amenity Name</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <script language = "javascript" type="text/javascript" src="./inc_files/form_check.js"></script>
    <link rel="stylesheet" type="text/css" href="./inc_files/template.css"/>
</head>

<body>
<form name="ldp" method="post" action="amenity_create_process.php" onsubmit="return field_is_blank(this, 'name', 'Amenity Name')">

<div class="center">
	<div class="grey_box_70">
		<div class="spacer"></div>
<?php
if(isset($_GET["disp"]))
{
?>
		<div class="row_center">
			You have entered an amenity that is already in the list below.
		</div>
		<div class="spacer"></div>
<?php
}
?>
		<div class="row_center">
			<span class="label">
				&nbsp;&nbsp;Amenity Name:
			</span>
			<span class="right">
				<input type="text" class="name" name="name">&nbsp;&nbsp;
			</span>
		</div>
		<div class="spacer"></div>
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
		<div class="header">
			Existing Amenities
		</div>
		<div class="spacer"></div>
		<div class="grey_box_25">
<?php
require_once("./classes/Store.inc");
$al = new Store();
$al->db_load_amenity_type_list();

for($i=0; $i < $al->amenity_list_size(); $i++)
{
	$amenity = $al->get_amenity_from_list($i);
?>
		<div class="row_center">
			<?php echo($amenity->name); ?>
		</div>
		<div class="spacer"></div>
<?php
}
?>
		</div>
	</div>
</div>

<div class="spacer"></div>
<div class="spacer"></div>
<div class="spacer"></div>
<div class="spacer"></div>
<div class="center">
	<div class="grey_box_50">
	<div class="spacer"></div>
	<a class="button" href="index.htm">Home</a>
	<div class="spacer"></div>
	</div>
</div>
</body>
</html>