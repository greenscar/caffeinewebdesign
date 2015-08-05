<?php
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
        // LOAD ALL INFO ABOUT THIS STORE.
        $store = new Store($_GET["sn"], "");
        $store->db_load_all_info();
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
	    echo(get_store_menu($admin, $title, $_SERVER['PHP_SELF'], $_GET["ob"]));
	else
	    echo(get_store_menu($admin, $title, $_SERVER['PHP_SELF']));
    }
    /*
     * A STORE HAS BEEN SELECTED. DISPLAY THIS STORE FOR MODIFYING.
     */
    else
    {
        //echo($store->display_info());
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
    <div class="center">
            <div class="grey_box_70">
                    <div class="spacer"></div>
                    <div class="header">
                        <?php echo($store->site_num . " " . $store->site_name); ?>
                    </div>
                    <div class="spacer"></div>
                    <div class="spacer"></div>   
                    <div class="row_center">
                        <u><h4>General Info</h4></u>
                    </div>
                    <div class="spacer"></div>
                    <div class="row_center">
                        <span class="label">
                            Store Number : 
                        </span>
                        <span class="right">
                            <?php echo($store->site_num . "\n");?>
                        </span>
                    </div>
                    <div class="spacer"></div>              
                    <div class="row_center">
                        <span class="label">
                            Region.District : 
                        </span>
                        <span class="right">
                            <?php echo($store->region . "." . $store->district); ?>
                        </span>
                    </div>
                    <div class="spacer"></div>               
                    <div class="row_center">
                        <span class="label">
                            Date Opened (dd/mm/yyyy) : 
                        </span>
                        <span class="right">
                            <?php echo($store->date_active . "\n");?>
                        </span>
                    </div>
                    <div class="spacer"></div>
                    <div class="row_center">
                        <span class="label">
                            Active : 
                        </span>
                        <span class="right">
                        <?php
                            if($store->active)
                                echo("YES\n");
                            else
                                echo("NO\n");
                        ?>
                        </span>
                    </div>
                    <div class="spacer"></div>
                    <div class="row_center">
                        <u><h4>Location</h4></u>
                    </div>
                    <div class="spacer"></div> 
                    <div class="row_center">
                        <span class="label">
                            Site Name : 
                        </span>
                        <span class="right">
                            <?php echo($store->site_name . "\n");?>
                        </span>
                    </div>
                    <div class="spacer"></div>                
                    <div class="row_center">
                        <span class="label">
                            Location Name : 
                        </span>
                        <span class="right">
                            <?php echo($store->address->description . "\n");?>
                        </span>
                    </div>
                    <div class="spacer"></div>                
                    <div class="row_center">
                        <span class="label">
                            Street : 
                        </span>
                        <span class="right">
                            <?php echo($store->address->street . "\n");?>
                        </span>
                    </div>
                    <div class="spacer"></div>                
                    <div class="row_center">
                        <span class="label">
                            City : 
                        </span>
                        <span class="right">
                            <?php echo($store->address->city . "\n");?>
                        </span>
                    </div>
                    <div class="spacer"></div>                
                    <div class="row_center">
                        <span class="label">
                            State : 
                        </span>
                        <span class="right">
                            
                            <span class="right_field">
                                <?php echo($store->address->state . "\n"); ?>
                            </span>
                        </span>
                    </div>
                    <div class="spacer"></div>                
                    <div class="row_center">
                        <span class="label">
                            Zip Code : 
                        </span>
                        <span class="right">
                            <?php echo($store->address->zip . "\n");?>
                        </span>
                    </div>
                    <div class="spacer"></div>                
                    <div class="row_center">
                        <span class="label">
                            Sysco Acct # : 
                        </span>
                        <span class="right">
                            <?php echo($store->sysco_num . "\n");?>
                        </span>
                    </div>
                    <div class="spacer"></div>   
                    <div class="row_center">
                        <u><h4>Telephone</h4></u>
                    </div>
                    <div class="spacer"></div>               
                    <div class="row_center">
                        <span class="label">
                            Phone Company : 
                        </span>
                        <span class="right">
                            <?php echo($store->get_phone_company_name()); ?>
                        </span>
                    </div>
                    <div class="spacer"></div>
    <?php
    $ps = new Phone_System();
    $ar = $store->phone_system->line_list;
    $ps->db_load_phone_type_list();
        for($i=0; $i < $ps->phone_type_list_size(); $i++)
        {
            //echo($store->phone_system->get_number_via_code("CRDT"));
            $phone_type = $ps->get_a_phone_type_from_list($i);
            $num = $store->get_phone_num($phone_type->id);
            if(isset($num))
            {
                echo("\t\t\t<div class=\"row_center\">\n");
                echo("\t\t\t\t<span class=\"label\">\n");
                echo("\t\t\t\t\t" . $phone_type->name . " : \n");
                echo("\t\t\t\t</span>\n");
                echo("\t\t\t\t<span class=\"right\">\n");
                echo("\t\t\t\t\t" . $num . "\n");                        
                echo("\t\t\t\t</span>\n");
                echo("\t\t\t</div>\n");
                echo("\t\t\t<div class=\"spacer\"></div>\n");
            }
        }
        ?>
                    <div class="spacer"></div>
                    <div class="row_center">
                        <u><h4>Contacts</h4></u>
                    </div>
                    <div class="spacer"></div>
                    <div class="spacer"></div>
                    
                    <div class="row_center">
                        <span class="label">
                            Manager : 
                        </span>
                        <span class="right">
                            <?php echo($store->manager->first_name . " " . $store->manager->last_name); ?>
                        </span>
                    </div>
    <?php
    foreach($store->asst_mgr_list AS $key=>$am)
    {
            ?>
                    <div class="spacer"></div>              
                    <div class="row_center">
                        <span class="label">
                            Asst Mgr : 
                        </span>
                        <span class="right">
                            <?php echo($am->first_name . " " . $am->last_name); ?>
                        </span>
                    </div>
            <?php
    }
         ?>
                    <div class="spacer"></div>  
                    <div class="spacer"></div>
                    <div class="row_center">
                        <u><h4>Amenities</h4></u>
                    </div>         
                    <div class="spacer"></div>        
                    <div class="row_center">
                        <span class="label">
                            Smoking : 
                        </span>
                        <span class="right">
                        <?php
                            if($store->get_amenity_allowed("smoking"))
                                echo("YES");
                            else
                                echo("NO");
                        ?>
                        </span>
                    </div>
                    <div class="spacer"></div>     
                    <div class="row_center">
                        <span class="label">
                            Alcohol : 
                        </span>
                        <span class="right">
                        <?php
                            if($store->get_amenity_allowed("alcohol"))
                                echo("YES");
                            else
                                echo("NO");
                        ?>
                        </span>
                    </div>
                    <div class="spacer"></div>    
                    <div class="row_center">
                        <span class="label">
                            Gaming : 
                        </span>
                        <span class="right">
                        <?php
                            if($store->get_amenity_allowed("gaming"))
                                echo("YES");
                            else
                                echo("NO");
                        ?>
                        </span>
                    </div>
                    <div class="spacer"></div>  
                    <div class="spacer"></div>
                    <div class="spacer"></div>
                    <div class="spacer"></div>
                    <div class="spacer"></div>
                    <div class="spacer"></div>  
                    <div class="spacer"></div>
                    <div class="spacer"></div>
                    <div class="spacer"></div>
                    <div class="spacer"></div>
                    <div class="spacer"></div>
            </div>

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
	<a class="button" href="index.htm">Home</a>
	<div class="spacer"></div>
	</div>
</div>
</body>
</html>