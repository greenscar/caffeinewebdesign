<?php
//error_reporting(E_ALL);
// Load Session
session_start();
header("Cache-control: private"); 
session_unregister("user");


$_COOKIE["USERID"] = "565";
setcookie('USERID', '565');
$_SERVER["PATH_INFO"] = "/site_info";

// If the cookie isn't set, the user came here directly.
// Forward the user to the login.
if(empty($_COOKIE['USERID']))
   header("location:http://www.e-sharis.net");

include("../../php_objects/Security.inc");

//error_reporting(E_ALL);

// Create security guard.
$security = new Security();

// Load this user from the DB and create the session.
$user = $security->load_user($_COOKIE["USERID"], &$_SESSION);

//echo($security->load_user($_COOKIE["USERID"], &$_SESSION));
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <title><?php echo($user->first_name);?> please select an option</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link rel="stylesheet" type="text/css" href="./inc_files/template.css"/>
    <script language="javascript">
      function be_patient()
      {
         alert("This can take up to a minute to load.\nPlease be patient.");
      }
    </script>
</head>
<body>
<div class="center">
   <div class="grey_box_25">
<?php
   if($security->user_is_admin($user, $_SERVER["PATH_INFO"]))
   {
?>
      <div class="spacer"></div>
      <div class="row">
         <span class="white_center">
             <a class="main_menu" href="store_modify.php">Modify a Store</a>
             <div class="spacer"></div>
         </span>
      </div>
<?php
   }
?>
      <div class="spacer"></div>
      <div class="row">
         <span class="white_center">
             <a class="main_menu" href="store_display.php">Display a Store</a>
             <div class="spacer"></div>
         </span>
      </div>
      <div class="spacer"></div>
      <div class="row">
         <span class="white_center">
             <a class="main_menu" href="print_phone_list.php" onclick="be_patient();">Display Phone List for Printing</a>
             <div class="spacer"></div>
         </span>
      </div>
      <div class="spacer"></div>
      <div class="row">
         <span class="white_center">
             <a class="main_menu" href="print_amenity_list.php" onclick="be_patient();">Display Amenity List for Printing</a>
             <div class="spacer"></div>
         </span>
      </div>
      <div class="spacer"></div>
                <!--
      <div class="spacer"></div>
      <div class="row">
         <span class="white_center">
             <a class="main_menu" href="store_create.php">Create a Store</a>
             <div class="spacer"></div>
         </span>
      </div>
      <div class="row">
         <span class="white_center">
             <a class="main_menu" href="amenity_create.php">Create an Amenity</a>
             <div class="spacer"></div>
         </span>
      </div>
      <div class="spacer"></div>
   
      <div class="spacer"></div>
      <div class="row">
         <span class="white_center">
             <a class="main_menu" href="phone_type_create.php">Create a Phone Line Type</a>
             <div class="spacer"></div>
         </span>
      </div>
      <div class="spacer"></div>
      <div class="row">
         <span class="white_center">
             <a class="main_menu" href="phone_company_create.php">Create a Phone Company</a>
             <div class="spacer"></div>
         </span>
      </div>
                -->
   
   </div>
</div>
</body>
</html>