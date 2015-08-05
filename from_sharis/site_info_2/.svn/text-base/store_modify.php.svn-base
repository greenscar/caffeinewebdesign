<?php
    include("../includes/php_files/ensure_admin.inc");
    require_once("./classes/Admin.inc");
    require_once("./inc_files/php_fxns.inc");
    $admin = new Admin();
    /*
     * IF A STORE HAS NOT BEEN CHOSEN TO DISPLAY, LOAD THE STORE LIST
     * TO DISPLAY FOR THE USER TO CHOOSE WHAT STORE TO MODIFY.
     */
    if(!isset($_GET["sn"]))
    {
      session_unregister("store");
      $title = "Please select a store to modify.";
    }
    else
    {
        $title = "Modify your info then press submit.";
        // LOAD ALL INFO ABOUT THIS STORE.
        $store = $admin->db_load_store($_GET["sn"]);
        //$_SESSION["store"] = $store;
        session_register("store");
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
     *    FOR THE USER TO SELECT WHICH STORE TO MODIFY.
     */
    if(!isset($_GET["sn"]))
    {
	$title = "Click on any site to modify it.";
	if(isset($_GET["ob"]))
	    echo($admin->get_store_menu($admin, $title, $_SERVER['PHP_SELF'], $_GET["ob"]));
	else
	    echo($admin->get_store_menu($admin, $title, $_SERVER['PHP_SELF']));
    }
    /*
     * A STORE HAS BEEN SELECTED. DISPLAY THIS STORE FOR MODIFYING.
     */
    else
    {
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<form name="store_create" method="post" action="store_modify_process.php" onsubmit="return validate_create(this)";>
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
            <td class="value"><?php echo($store->site_num . "\n");?><input type="text" class="name" name="store_number" value=<?php echo("\"" . $store->site_num . "\"");?>></td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr>
            <td class="label">Region.District:</td>
            <td class="value"><?php echo($store->region . "." . $store->district); ?></td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr>
            <td class="label">Date Opened (dd/mm/yyyy):</td>
            <td class="value"><input type="text" class="name" name="date" value=<?php echo("\"" . $store->date_active . "\"");?>></td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr>
            <td class="label">Active :</td>
            <td class="value">
              <?php
                  echo("<input type=\"radio\" name=\"active\" value=\"1\"");
                  if($store->active)
                      echo(" checked");
                  echo(">&nbsp; Open\n");
                  echo("<input type=\"radio\" name=\"active\" value=\"0\"");
                  if(!($store->active))
                      echo(" checked");
                  echo(">&nbsp; Closed\n");
              ?>
            </td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr><td colspan=2><u><h4>Location</h4></u></td></tr>
        <tr>
            <td class="label">Site Name :</td>
            <td class="value"><input type="text" class="name" name="site_name" value=<?php echo("\"" . $store->site_name . "\"");?>></td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr>
            <td class="label">Location Name :</td>
            <td class="value"><input type="text" class="name" name="address_description" value=<?php echo("\"" . $store->address->description . "\"");?>></td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr>
            <td class="label">Street :</td>
            <td class="value"><input type="text" class="name" name="street" value=<?php echo("\"" . $store->address->street . "\"");?>></td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr>
            <td class="label">City :</td>
            <td class="value"><input type="text" class="name" name="city" value=<?php echo("\"" . $store->address->city . "\"");?>></td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr>
            <td class="label">State :</td>
            <td class="value">
              <select class="black_wide" name="state">
                <?php
                    foreach ($state_array as $abr => $state) 
                    {
                        echo("\t\t\t\t<option value=\"$abr\" ");
                        if(strcmp($store->address->state, $abr) == 0)
                            echo("SELECTED");
                        echo(">$state</option>\n");
                    }
                ?>
              </select>
            </td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr>
            <td class="label">Zip:</td>
            <td class="value"><input type="text" class="name" name="zip" value=<?php echo("\"" . $store->address->zip . "\"");?>></td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr>
            <td class="label">Sysco Acct # :</td>
            <td class="value"><input type="text" class="name" name="sysco_num" value=<?php echo("\"" . str_pad($store->sysco_num, 6, "0", STR_PAD_LEFT)  . "\"");?>></td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <tr><td colspan=2><u><h4>Telephone</h4></u></td></tr>
        <tr>
            <td class="label">Phone Co:</td>
            <td class="value">
              <?php
                $admin->db_load_phone_co_list();
                $pc_id = $store->get_phone_company_id();
                echo("\t\t\t<select class=\"black_wide\" name=\"phone_co\">");
                for($i=0; $i < $admin->phone_co_list_size(); $i++)
                {
                    $phone_co = $admin->get_a_phone_co_from_list($i);
                    echo("\t\t\t<option value=\"" . $phone_co->id . "\"");
                    if(strcmp($phone_co->id, $pc_id) == 0)
                        echo(" SELECTED");
                    echo (">" . $phone_co->name . "</option>\n");
                }
              ?>
            </td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        
        <?php
          $admin->db_load_phone_type_list();
          //$ar = $store->phone_system->line_list;
          for($i=0; $i < $admin->phone_type_list_size(); $i++)
          {
            //echo($store->phone_system->get_number_via_code("CRDT"));
            $phone_type = $admin->get_a_phone_type_from_list($i);
            echo("\t\t\t<tr>\n");
            echo("\t\t\t\t<td class=\"label\">\n");
            //echo("\t\t\t\t<span class=\"label\">\n");
            echo("\t\t\t\t\t" . $phone_type->name . "\n");
            //echo("\t\t\t\t</span>\n");
            echo("\t\t\t\t</td>\n");
            echo("\t\t\t\t<td class=\"value\">\n");
            //echo("\t\t\t\t<span class=\"right\">\n");
            echo("\t\t\t\t\t<input type=\"text\" class=\"name\" ");
            echo("name = \"phn_" . $phone_type->id . "\"");
            $num = $store->get_number_via_name($phone_type->name);
            if(isset($num))
                echo(" VALUE=\"$num\"");            
            echo(">\n");
            //echo("\t\t\t\t</span>\n");
            echo("\t\t\t\t</td>\n");
            echo("\t\t\t</tr>\n");
          }
        ?>
        <tr><td colspan=2><u><h4>Contacts</h4></u></td></tr>
        <tr>
            <td class="label">Manager :</td>
            <td class="value">
              <?php
                echo("<input name=\"name_mgr\" class=\"first_name\" value=\"" . $store->manager->first_name . "\">&nbsp;");
                echo($store->manager->last_name);
              ?> 
            </td>
        </tr>
        <tr><td colspan=2><div class="spacer"></div></td></tr>
        <?php
        foreach($store->asst_mgr_list AS $key=>$am)
        {
            ?>
            <tr>
                <td class="label">Asst Mgr :</td>
                <td class="value">
                  <?php
                    echo("<input name=\"am_$key\" class=\"first_name\" value=\"" . $am->first_name . "\">&nbsp;");
                    echo($am->last_name);
                  ?>
                </td>
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
                  echo("<input type=\"radio\" name=\"amen_" . $amen->id . "\" value=\"1\"");
                  if($store->get_amenity_allowed($amen->name) == 1)
                      echo(" checked");
                  echo(">&nbsp; YES\n");
                  echo("<input type=\"radio\" name=\"amen_" . $amen->id . "\" value=\"0\"");
                  if(($store->get_amenity_allowed($amen->name)) == 0)
                      echo(" checked");
                  echo(">&nbsp; NO\n");
              ?>
            </td>
            </tr>
            <tr><td colspan=2><div class="spacer"></div></td></tr>
            <?php
        }
        ?>
    <tr><td colspan="2" align="center">
        <input class="grey" type="submit" name="submit" value="Submit">
    </td></tr>
    </table>
</form>
<?php
    }
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