<?php
//error_reporting(E_ALL);
require_once("./classes/Admin.inc");
require_once("./inc_files/php_fxns.inc");
$admin = new Admin();
/*
* IF A STORE HAS NOT BEEN CHOSEN TO DISPLAY, LOAD THE STORE LIST
* TO DISPLAY FOR THE USER TO CHOOSE WHAT STORE TO MODIFY.
*/
if(!isset($_GET["sn"]))
{
    $title = "Please select a store to display.";
}
else
{
    $title = "Store Number " . $_GET["sn"];
}    
?>
<!--
DISPLAY THE HEADER. THIS WILL BE DISPLAYED NO MATTER THE CONDITION
-->
<html>
<head>
    <title><?php echo($title); ?></title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <script language = "javascript" type="text/javascript" src="./inc_files/form_check.js"></script>
    <link rel="stylesheet" type="text/css" href="./inc_files/template.css"/>
</head>
<body>
<!--
END DISPLAY THE HEADER.
-->
<?php
/*
* IF THE STORE NUMBER IS NOT SELECTED, DISPLAY A LIST OF STORES
*    FOR THE USER TO SELECT WHICH STORE TO DISPLAY.
*/
if(!isset($_GET["sn"]))
{
    $title = "Click on any site to display it.";
    if(isset($_GET["ob"]))
        $admin->get_store_menu($title, $_SERVER['PHP_SELF'], $_GET["ob"]);
    else
        echo $admin->get_store_menu($title, $_SERVER['PHP_SELF']);
}
/*
* A STORE HAS BEEN SELECTED. DISPLAY THIS STORE FOR MODIFYING.
*/
else
{
    // LOAD ALL INFO ABOUT THIS STORE.
    $store = $admin->db_load_store($_GET["sn"]);
    ?>
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
    <table class="grey_box" align="center">
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr>
            <td colspan=2>
                <div class="header">
                    <?php echo($store->site_num . " " . $store->address->description); ?>
                </div>
            </td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr><td colspan=2><u><h4>General Info</h4></u></td></tr>
        <tr>
            <td class="label">Store Number :</td>
            <td class="value"><?php echo($store->site_num . "\n");?></td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr>
            <td class="label">Region.District:</td>
            <td class="value"><?php echo($store->region . "." . $store->district); ?></td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr>
            <td class="label">Date Opened (dd/mm/yyyy):</td>
            <td class="value"><?php echo($store->date_active . "\n");?></td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr>
            <td class="label">Active :</td>
            <td class="value">
                <?php
                if($store->active)
                echo("YES\n");
                else
                echo("NO\n");
                ?>
            </td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr><td colspan=2><u><h4>Location</h4></u></td></tr>
        <tr>
            <td class="label">Site Name :</td>
            <td class="value"><?php echo($store->site_name . "\n");?></td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr>
            <td class="label">Location Name :</td>
            <td class="value"><?php echo($store->address->description . "\n");?></td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr>
            <td class="label">Street :</td>
            <td class="value"><?php echo($store->address->street . "\n");?></td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr>
            <td class="label">City :</td>
            <td class="value"><?php echo($store->address->city . "\n");?></td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr>
            <td class="label">State :</td>
            <td class="value"><?php echo($store->address->state . "\n");?></td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr>
            <td class="label">Zip:</td>
            <td class="value"><?php echo($store->address->zip . "\n");?></td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr>
            <td class="label">Sysco Acct # :</td>
            <td class="value"><?php echo($store->sysco_num . "\n");?></td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr><td colspan=2><u><h4>Telephone</h4></u></td></tr>
        <tr>
            <td class="label">Phone Co:</td>
            <td class="value"><?php echo($store->get_phone_company_name()); ?></td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <?php
        for($i=0; $i < $store->get_num_phone_lines(); $i++)
        {            
            echo("\t\t\t<tr><td class=\"label\">\n");
            echo("\t\t\t\t\t" . $store->get_line_type_name($i) . " : \n");
            echo("\t\t\t</td>\n");
            echo("\t\t\t<td class=\"value\">\n");
            echo("\t\t\t\t\t" . $store->get_line_number($i) . "\n");                        
            echo("\t\t\t</td></tr>\n");
            echo("\t\t\t<tr><td colspan=2><div class=\"spacer\"></div></td></tr>\n");
        }
        ?>
        <tr><td colspan=2><u><h4>Contacts</h4></u></td></tr>
        <tr>
            <td class="label">Manager :</td>
            <td class="value"><?php echo($store->manager->first_name . " " . $store->manager->last_name); ?></td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <?php
        foreach($store->asst_mgr_list AS $key=>$am)
        {
            ?>
            <tr>
                <td class="label">Asst Mgr :</td>
                <td class="value"><?php echo($am->first_name . " " . $am->last_name); ?></td>
            </tr>
            <tr><td colspan=2><div class="spacer"></div></td></tr>
            <?php
        }
        ?>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr><td colspan=2><u><h4>Amenities</h4></u></td></tr>
        <?php
        for($i=0; $i<$store->amenity_list_size(); $i++)
        {
            $amen = $store->get_amenity_from_list($i);
            ?>
            <tr>
                <td class="label"><?php echo($amen->name); ?> :</td>
            <td class="value">
            <?php
            if($store->get_amenity_allowed($amen->name) == 1)
            echo("YES\n");
            else
            echo("NO\n");
            ?>
            </td>
            </tr>
            <tr><td colspan=2><div class="spacer"></div></td></tr>
            <?php
        }
        ?>
    </table>
    <?php
} // END ELSE if(!isset($_GET["sn"]))
?>
<div class="spacer"></div>
<div class="spacer"></div>
<div class="spacer"></div>
<div class="spacer"></div>
<div class="center">
<div class="grey_box_50">
<div class="spacer"></div>
<a class="button" href="index.php">Main Menu</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a class="button" href=<?php echo($_SERVER['PHP_SELF']); ?>>Store List</a>
<div class="spacer"></div>
</div>
</div>
</body>
</html>