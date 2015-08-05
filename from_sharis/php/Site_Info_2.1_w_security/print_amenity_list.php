<?php
// If the cookie isn't set, the user came here directly.
// Forward the user to the login.
if(empty($_COOKIE["USERID"]))
   header("location:http://www.e-sharis.net");
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
   <title>Shari's Store List</title>
   <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
   <link rel="stylesheet" type="text/css" href="./inc_files/print_template.css"/>
</head>
<body>

<?php
$num_rows_per_page = 5;
require_once("./classes/Admin.inc");
$admin = new Admin();
$admin->db_load_detailed_site_list("loc_name_1");
$row_num = 0;
//echo("site_list_size = " . $admin->site_list_size());
//exit();
for($i=0; $i < $admin->site_list_size(); $i++)
{
   $row_num ++;
   if($row_num == 1)
   {
      if((($admin->site_list_size()) - $i) > 21)
         echo("<table class=\"not_last\">\n");
      else
         echo("<table class=\"last\">\n");
      $table_started = true;
   }
   echo("\t<tr class=\"amenity_list_small\">\n");
   
   // Display 3 stores in a row.   
   for($col_num = 0; $col_num < 3; $col_num ++)
   {
      $num_blank_lines = 0;
      $site = $admin->get_a_site_from_list($i);
      $i++;
      echo("\t\t<td class=\"store\">\n");
      echo("\t\t\t<table class=\"inner\">\n");
      if(!empty($site))
      {
         /**********************************************************
          * PRINT STORE LOCATION AND DATE OPENED.
          *********************************************************/
         echo("\t\t\t\t<tr>\n");
         echo("\t\t\t\t\t<td class=\"amenity_list_small\" colspan=\"3\">\n");
         echo("\t\t\t\t\t\t<u><b>" . $site->site_name . " " . $site->site_num ."</b></u>\n");
         echo("\t\t\t\t\t</td>\n");
         echo("\t\t\t\t\t<td class=\"amenity_list_small\">\n");
         echo("\t\t\t\t\t\t" . $site->date_active . "<br>\n");
         echo("\t\t\t\t\t</td>\n");
         echo("\t\t\t\t</tr>\n");
         echo("\t\t\t\t<tr>\n");
         echo("\t\t\t\t\t<td class=\"amenity_list_small\" colspan=\"4\">\n");
         $address = "";
         $descr = $site->get_address_description();
         if(!empty($descr))
         {
            $address .= $descr . "<br>";
         }
         else
         {
            $num_blank_lines++;
         }
         $address .= $site->get_street() . "<br>"
                  . $site->get_city() . ", "
                  . $site->get_state() . " "
                  . $site->get_zip();
         echo("\t\t\t\t\t\t" . $address . "\n");
         echo("\t\t\t\t\t</td>\n");
         echo("\t\t\t\t</tr>\n");
         /**********************************************************
          * END PRINT STORE LOCATION AND DATE OPENED.
          *********************************************************/
         
         /**********************************************************
          * PRINT PHONE NUMBERS
          *********************************************************/
         echo("\t\t\t\t<tr>\n");
         echo("\t\t\t\t\t<td class=\"amenity_list_small\" colspan=\"2\" width=\"40%\">\n");
         // Phone Numbers
         echo("\t\t\t\t\t\t" . $site->get_number_via_name("Main") . "\n");
         echo("\t\t\t\t\t</td>\n");
         echo("\t\t\t\t\t<td class=\"amenity_list_small\" colspan=\"2\" width=\"60%\">\n");
         // Phone Numbers
         echo("<div align=\"right\">");
         echo("\t\t\t\t\t\tFax: " . $site->get_number_via_name("Fax") . "\n");
         echo("</div>");
         echo("\t\t\t\t\t</td>\n");
         echo("\t\t\t\t</tr>\n");
         /**********************************************************
          * END PRINT PHONE NUMBERS
          *********************************************************/
         
         /**********************************************************
          * PRINT MANAGEMENT
          *********************************************************/
         echo("\t\t\t\t<tr>\n");
         echo("\t\t\t\t\t<td class=\"amenity_list_small\" colspan=\"1\" width=\"25%\">\n");
         echo("\t\t\t\t\t\tMgr:<BR>\n");
         echo("\t\t\t\t\t\tAsst:\n");
         echo("\t\t\t\t\t</td>\n");
         echo("\t\t\t\t\t<td colspan=\"3\" class=\"amenity_list_small\">\n");
         // PRINT MANAGER NAME
         echo("\t\t\t\t\t\t" . $site->get_mgr_full_name() . "<br>\n");
         for($amn=0; $amn < 3; $amn++)
         {
            if($amn < $site->get_num_asst_mgrs())
               echo("\t\t\t\t\t\t" . $site->get_asst_mgr_full_name($amn) . "<br>\n");
            else
               $num_blank_lines++;
            
         }while($num_blank_lines > 0)
         {
            echo("<br>");
            $num_blank_lines --;
         }
         // PRINT ALL ASST MGR NAMES.
         echo("\t\t\t\t\t</td>\n");
         
         echo("\t\t\t\t</tr>\n");
         /**********************************************************
          * END PRINT MANAGEMENT
          *********************************************************/
         
         /**********************************************************
          * PRINT AMENITIES
          *********************************************************/
         echo("\t\t\t\t<tr class=\"amenity_list_small\">\n");
         echo("\t\t\t\t\t<td colspan=\"4\" class=\"amenity_list_small\">\n");
         // Amenities
         for($an=0; $an < $site->amenity_list_size(); $an++)
         {
            $am = $site->get_amenity_from_list($an);
            if($am->allowed)
            {
               echo("\t\t\t\t\t\t<img src=\"./images/yes_" . $am->id . ".jpg\">\n");
            }
            else
            {
               echo("\t\t\t\t\t\t<img src=\"./images/no_" . $am->id . ".jpg\">\n");
            }
         }
         echo("\t\t\t\t\t</td>\n");
         echo("\t\t\t\t</tr>\n");
         /**********************************************************
          * END PRINT AMENITIES
          *********************************************************/
         
      } // END if(!empty($site))
      echo("\t\t\t</table>");
      echo("\t\t</td>\n");
   } // END for($col_num = 0; $col_num < 3; $col_num ++)
  // END DO THIS 3 TIMES
   echo("\t</tr>\n");
if($row_num == $num_rows_per_page)
   {
      echo("</table>\n");
      $table_started = false;
      $row_num = 0;
   }
}// END for($i=0; $i < $admin->site_list_size(); $i++)
  
if($table_started)
{
   echo("</table>\n");
}
?>
</body>