<?php
    require_once("./classes/Admin.inc");
    require_once("./inc_files/php_fxns.inc");
    $admin = new Admin();
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Please Enter The Location Information</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link rel="stylesheet" type="text/css" href="./inc_files/template.css"/>
</head>

<body>
<form name="site_create" method="post" action="site_create_process.php">

<div class="center">
	<div class="grey_box_50">
		<div class="spacer"></div>
		<div class="spacer"></div>
		<div class="row_center">
                    <span class="left">
                        Site Number:
                    </span>
                    <span class="right">
                        <input type="text" class="store_num" name="number">
                    </span>
                </div>
		<div class="spacer"></div>
                <div class="row_center">
                    <span class="left">
                        Site Name:
                    </span>
                    <span class="right">
                        <input type="text" class="name" name="site_name">
                    </span>
                </div>
		<div class="spacer"></div>                
                <div class="row_center">
                    <span class="left">
                        Location Name:
                    </span>
                    <span class="right">
                        <input type="text" class="name" name="name">
                    </span>
                </div>
		<div class="spacer"></div>                
                <div class="row_center">
                    <span class="left">
                        Street:
                    </span>
                    <span class="right">
                        <input type="text" class="name" name="address">
                    </span>
                </div>
		<div class="spacer"></div>                
                <div class="row_center">
                    <span class="left">
                        City:
                    </span>
                    <span class="right">
                        <input type="text" class="name" name="city">
                    </span>
                </div>
		<div class="spacer"></div>                
                <div class="row_center">
                    <span class="left">
                        State:
                    </span>
                    <span class="right">
                        
			<span class="right_field">
				<select class="black_wide" name="state">
                                    <option value="OR">Oregon</option>
                                    <option value="WA">Washington</option>
                                    <option value="ID">Idaho</option>
                                    <option value="WY">Wyoming</option>
                                    <option value="CA">California</option>
                                    <option value="CO">Colorado</option>
                                </select>
			</span>
                    </span>
                </div>
		<div class="spacer"></div>                
                <div class="row_center">
                    <span class="left">
                        Zip:
                    </span>
                    <span class="right">
                        <input type="text" class="name" name="zip">
                    </span>
                </div>
		<div class="spacer"></div>                
                <div class="row_center">
                    <span class="left">
                        Sysco Acct #:
                    </span>
                    <span class="right">
                        <input type="text" class="name" name="sysco_num">
                    </span>
                </div>
		<div class="spacer"></div>                
                <div class="row_center">
                    <span class="left">
                        Date Opened (dd/mm/yyyy):
                    </span>
                    <span class="right">
                        <input class="2_digits" name="day">&nbsp;/
                        <input class="2_digits" name="month">&nbsp;/
                        <input class="4_digits" name="year">
                    </span>
                </div>
                <div class="spacer"></div>                
                <div class="row_center">
                    <span class="left">
                        Region.District
                    </span>
                    <span class="right">
                        <select class="region_district" name="district">
<?php
    $admin->db_load_district_list();
    for($i=0; $i < $admin->district_list_size(); $i++)
    {
        $district = $admin->get_a_district_from_list($i);
        echo("\t\t\t<option value=\"" . $district->region->number . "." . $district->number . "\">" . $district->region->number . "." . $district->number . "</option>\n");
    }
?>
                        </select>
                    </span>
                </div>
		<div class="spacer"></div>                
                <div class="row_center">
                    <span class="left">
                        Phone Company:
                    </span>
                    <span class="right">
			<select class="black_wide" name="phone_co">
<?php
    $admin->db_load_phone_co_list();
    for($i=0; $i < $admin->phone_co_list_size(); $i++)
    {
	$phone_co = $admin->get_a_phone_co_from_list($i);
        echo("\t\t\t<option value=\"" . $phone_co->id . "\">" . $phone_co->name . "</option>\n");
    }
?>
                        </select>
                    </span>
                </div>
                <div class="spacer"></div>
                <div class="row_center">
                    <u><h4>Phone Lines</h4></u>
                </div>
                <div class="spacer"></div>
<?php
    $admin->db_load_phone_type_list();
    for($i=0; $i < $admin->phone_type_list_size(); $i++)
    {
        $phone_type = $admin->get_a_phone_type_from_list($i);
        ?>
                <div class="row_center">
                    <span class="left">
                        <?php echo($phone_type->name); ?>:
                    </span>
                    <span class="right">
                        <input type="text" class="name" name="<?php echo($phone_type->id); ?>">
                    </span>
                </div>
                <div class="spacer"></div>
        <?php
    }
    ?>
		<div class="spacer"></div>
		<div class="spacer"></div>
		<div class="row_center">
			<input class="grey" type="submit" name="submit" value="Submit">
		</div>
		<div class="spacer">&nbsp;</div>
	</div>
</div>
</form>

</body>
</html>