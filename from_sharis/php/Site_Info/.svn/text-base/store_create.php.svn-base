<?php
    require_once("./classes/Admin.inc");
    require_once("./inc_files/php_fxns.inc");
    $admin = new Admin();
    $pc = new Phone_System();
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Please Enter The Location Information</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <script language = "javascript" type="text/javascript" src="./inc_files/form_check.js"></script>
    <link rel="stylesheet" type="text/css" href="./inc_files/template.css"/>
</head>

<body>
<form name="store_create" method="post" action="store_create_process.php" onsubmit="return validate_create(this)";>
    <div class="center">
            <div class="grey_box_50">
                    <div class="spacer"></div>
                    <div class="spacer"></div>   
                    <div class="row_center">
                        <u><h4>General Info</h4></u>
                    </div>
                    <div class="spacer"></div>
                    <div class="row_center">
                        <span class="left">
                            Store Number:
                        </span>
                        <span class="right">
                            <input type="text" class="store_num" name="store_number">
                        </span>
                    </div>
                    <div class="spacer"></div>              
                    <div class="row_center">
                        <span class="left">
                            Region.District
                        </span>
                        <span class="right">
                            <select class="region_district" name="dist_reg">
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
                            Date Opened (dd/mm/yyyy):
                        </span>
                        <span class="right">
                            <input type="text" class="name" name="date">
                        </span>
                    </div>
                    <div class="spacer"></div>   
                    <div class="row_center">
                        <u><h4>Location</h4></u>
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
                            <input type="text" class="name" name="address_description">
                        </span>
                    </div>
                    <div class="spacer"></div>                
                    <div class="row_center">
                        <span class="left">
                            Street:
                        </span>
                        <span class="right">
                            <input type="text" class="name" name="street">
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
                            Zip Code:
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
                        <u><h4>Telephone</h4></u>
                    </div>
                    <div class="spacer"></div>               
                    <div class="row_center">
                        <span class="left">
                            Phone Company:
                        </span>
                        <span class="right">
                            <select class="black_wide" name="phone_co">
    <?php
        $pc->db_load_phone_co_list();
        for($i=0; $i < $pc->phone_co_list_size(); $i++)
        {
            $phone_co = $pc->get_a_phone_co_from_list($i);
            echo("\t\t\t<option value=\"" . $phone_co->id . "\">" . $phone_co->name . "</option>\n");
        }
    ?>
                            </select>
                        </span>
                    </div>
                    <div class="spacer"></div>
    <?php
        $pc->db_load_phone_type_list();
        for($i=0; $i < $pc->phone_type_list_size(); $i++)
        {
            $phone_type = $pc->get_a_phone_type_from_list($i);
            ?>
                    <div class="row_center">
                        <span class="left">
                            <?php echo($phone_type->name); ?>:
                        </span>
                        <span class="right">
                            <input type="text" class="name" name="phn_<?php echo($phone_type->id); ?>">
                        </span>
                    </div>
                    <div class="spacer"></div>
            <?php
        }
        ?>
                    <div class="spacer"></div>
                    <div class="row_center">
                        <u><h4>Contacts</h4></u>
                    </div>
                    <div class="spacer"></div>               
                    <div class="row_center">
                        <span class="left">
                            Manager Emp ID:
                        </span>
                        <span class="right">
                            <input type="text" class="name" name="mgr">
                        </span>
                    </div>
                    <div class="spacer"></div>              
                    <div class="row_center">
                        <span class="left">
                            Asst Manager Emp ID:
                        </span>
                        <span class="right">
                            <input type="text" class="name" name="asst_mgr_1">
                        </span>
                    </div>
                    <div class="spacer"></div>               
                    <div class="row_center">
                        <span class="left">
                            Asst Manager Emp ID:
                        </span>
                        <span class="right">
                            <input type="text" class="name" name="asst_mgr_2">
                        </span>
                    </div>
                    <div class="spacer"></div>                 
                    <div class="row_center">
                        <span class="left">
                            Asst Manager Emp ID:
                        </span>
                        <span class="right">
                            <input type="text" class="name" name="asst_mgr_3">
                        </span>
                    </div>
                    <div class="spacer"></div>  
                    <div class="spacer"></div>
                    <div class="row_center">
                        <u><h4>Amenities</h4></u>
                    </div>         
                    <div class="spacer"></div>        
                    <div class="row_center">
                        <span class="left">
                            Smoking:
                        </span>
                        <span class="right">
                            <input type="radio" name="smoking" value="true">&nbsp; Allowed
                            <input type="radio" name="smoking" value="false">&nbsp; Not Allowed
                        </span>
                    </div>
                    <div class="spacer"></div>     
                    <div class="row_center">
                        <span class="left">
                            Alcohol:
                        </span>
                        <span class="right">
                            <input type="radio" name="alcohol" value="true">&nbsp; Allowed
                            <input type="radio" name="alcohol" value="false">&nbsp; Not Allowed
                        </span>
                    </div>
                    <div class="spacer"></div>    
                    <div class="row_center">
                        <span class="left">
                            Gaming:
                        </span>
                        <span class="right">
                            <input type="radio" name="gaming" value="true">&nbsp; Allowed
                            <input type="radio" name="gaming" value="false">&nbsp; Not Allowed
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
                    <div class="row_center">
                            <input class="grey" type="submit" name="submit" value="Submit">
                    </div>
                    <div class="spacer"></div>
            </div>
    </div>
</form>

</body>
</html>