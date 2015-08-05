<?php
    require_once("./classes/Admin.inc");
    require_once("./inc_files/php_fxns.inc");
    $admin = new Admin();
    $ps = new Phone_System();
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
            <div class="grey_box_70">
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
                            <input type="text" class="name" name="store_number">
                        </span>
                    </div>
                    <div class="spacer"></div>               
                    <div class="row_center">
                        <span class="label">
                            Date Opened (dd/mm/yyyy):
                        </span>
                        <span class="right">
                            <input type="text" class="name" name="date">
                        </span>
                    </div>
                    <div class="spacer"></div>
                    <div class="row_center">
                        <span class="label">
                            Active : 
                        </span>
                        <span class="right">
                            <input type="radio" name="active" value="1">&nbsp; Open
                            <input type="radio" name="active" value="0">&nbsp; Closed
                        </span>
                    </div>
                    <div class="spacer"></div>   
                    <div class="row_center">
                        <u><h4>Location</h4></u>
                    </div>
                    <div class="spacer"></div> 
                    <div class="row_center">
                        <span class="label">
                            Site Name:
                        </span>
                        <span class="right">
                            <input type="text" class="name" name="site_name">
                        </span>
                    </div>
                    <div class="spacer"></div>                
                    <div class="row_center">
                        <span class="label">
                            Location Name:
                        </span>
                        <span class="right">
                            <input type="text" class="name" name="address_description">
                        </span>
                    </div>
                    <div class="spacer"></div>                
                    <div class="row_center">
                        <span class="label">
                            Street:
                        </span>
                        <span class="right">
                            <input type="text" class="name" name="street">
                        </span>
                    </div>
                    <div class="spacer"></div>                
                    <div class="row_center">
                        <span class="label">
                            City:
                        </span>
                        <span class="right">
                            <input type="text" class="name" name="city">
                        </span>
                    </div>
                    <div class="spacer"></div>                
                    <div class="row_center">
                        <span class="label">
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
                        <span class="label">
                            Zip Code:
                        </span>
                        <span class="right">
                            <input type="text" class="name" name="zip">
                        </span>
                    </div>
                    <div class="spacer"></div>                
                    <div class="row_center">
                        <span class="label">
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
                        <span class="label">
                            Phone Company:
                        </span>
                        <span class="right">
                            <select class="black_wide" name="phone_co">
    <?php
        $ps->db_load_phone_co_list();
        for($i=0; $i < $ps->phone_co_list_size(); $i++)
        {
            $phone_co = $ps->get_a_phone_co_from_list($i);
            echo("\t\t\t<option value=\"" . $phone_co->id . "\">" . $phone_co->name . "</option>\n");
        }
    ?>
                            </select>
                        </span>
                    </div>
                    <div class="spacer"></div>
    <?php
        $ps->db_load_phone_type_list();
        for($i=0; $i < $ps->phone_type_list_size(); $i++)
        {
            $phone_type = $ps->get_a_phone_type_from_list($i);
            ?>
                    <div class="row_center">
                        <span class="label">
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
                        <u><h4>Amenities</h4></u>
                    </div>         
                    <div class="spacer"></div>        
                    <div class="row_center">
                        <span class="label">
                            Smoking:
                        </span>
                        <span class="right">
                            <input type="radio" name="smoking" value="true">&nbsp; Allowed
                            <input type="radio" name="smoking" value="false">&nbsp; Not Allowed
                        </span>
                    </div>
                    <div class="spacer"></div>     
                    <div class="row_center">
                        <span class="label">
                            Alcohol:
                        </span>
                        <span class="right">
                            <input type="radio" name="alcohol" value="true">&nbsp; Allowed
                            <input type="radio" name="alcohol" value="false">&nbsp; Not Allowed
                        </span>
                    </div>
                    <div class="spacer"></div>    
                    <div class="row_center">
                        <span class="label">
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