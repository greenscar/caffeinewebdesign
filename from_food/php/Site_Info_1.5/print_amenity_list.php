<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
   <title>Shari's Store List</title>
   <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
   <link rel="stylesheet" type="text/css" href="./inc_files/print_template.css"/>
</head>
<body>

<?php
$num_rows_per_page = 6;
require_once("./classes/Admin.inc");
$admin = new Admin();
$admin->db_load_detailed_site_list("loc_name_1");
$row_num = 0;
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
   echo("\t<tr class=\"tiny\">\n");
   
   
   /*
   * DO THIS 3 TIMES
   */
   for($col_num = 0; $col_num < 3; $col_num ++)
   {
      $site = $admin->get_a_site_from_list($i);
      echo("\t\t<td class=\"store\">\n");
      echo("\t\t\t<table class=\"inner\">\n");
      if(!empty($site))
      {
         echo("\t\t\t\t<tr class=\"tiny\">\n");
         echo("\t\t\t\t\t<td colspan=\"3\">\n");
         echo("\t\t\t\t\t\t<u><b>" . $site->site_name . " " . $site->site_num ."</b></u>\n");
         echo("\t\t\t\t\t</td>\n");
         echo("\t\t\t\t\t<td class=\"tiny\">\n");
         echo("\t\t\t\t\t\t" . $site->date_active . "<br>\n");
         echo("\t\t\t\t\t</td>\n");
         echo("\t\t\t\t</tr>\n");
         echo("\t\t\t\t<tr class=\"tiny\">\n");
         echo("\t\t\t\t\t<td colspan=\"4\">\n");
         $address = "";
         $descr = $site->get_address_description();
         if(!empty($descr))
         {
            $address .= $descr . "<br>";
         }
         $address .= $site->get_street() . "<br>"
                  . $site->get_city() . ", "
                  . $site->get_state() . " "
                  . $site->get_zip();
         echo("\t\t\t\t\t\t" . $address . "\n");
         echo("\t\t\t\t\t</td>\n");
         echo("\t\t\t\t<tr class=\"tiny\">\n");
         echo("\t\t\t\t\t<td colspan=\"2\">\n");
         // Phone Numbers
         echo("\t\t\t\t\t\t" . $site->get_number_via_name("Main") . "\n");
         echo("\t\t\t\t\t</td>\n");
         echo("\t\t\t\t\t<td colspan=\"2\">\n");
         // Phone Numbers
         echo("\t\t\t\t\t\tFax: " . $site->get_number_via_name("Fax") . "\n");
         echo("\t\t\t\t\t</td>\n");
         echo("\t\t\t\t</tr>\n");
         echo("\t\t\t\t<tr class=\"tiny\">\n");
         echo("\t\t\t\t\t<td colspan=\"1\" width=\"15%\">\n");
         echo("\t\t\t\t\t\tMgr:<BR>\n");
         echo("\t\t\t\t\t\tAsst:\n");
         echo("\t\t\t\t\t</td>\n");
         echo("\t\t\t\t\t<td colspan=\"3\">\n");
         // PRINT MANAGER NAME
         echo("\t\t\t\t\t\t" . $site->get_mgr_full_name() . "<br>\n");
         for($i=0; $i < $site->get_num_asst_mgrs(); $i++)
         {
            echo("\t\t\t\t\t\t" . $site->get_asst_mgr_full_name($i) . "\n");
         }
         // PRINT ALL ASST MGR NAMES.
         echo("\t\t\t\t\t</td>\n");
         
         echo("\t\t\t\t</tr>\n");
         
         echo("\t\t\t\t<tr class=\"tiny\">\n");
         echo("\t\t\t\t\t<td colspan=\"4\" class=\"tiny\">\n");
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
         
      } // END if(!empty($site))
      echo("\t\t\t</table>");
      echo("\t\t</td>\n");
      $i++;
   } // END for($col_num = 0; $col_num < 3; $col_num ++)
   /*
   * END DO THIS 3 TIMES
   */
   
   
   
   
   
   
   
   echo("\t</tr>\n");
if($row_num == $num_rows_per_page)
   {
      echo("</table>\n");
      $table_started = false;
      $row_num = 0;
   }
}
if($table_started)
{
   echo("</table>\n");
}
?>
</body>