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
<body onload="alert('Please press the back arrow when you are done.');">

<?php
require_once("./classes/Admin.inc");
$num_stores_per_page = 9;
$admin = new Admin();
$admin->db_load_detailed_store_list();
$row_num = 0;
$table_started = false;
for($i=0; $i < $admin->site_list_size(); $i++)
{
   $row_num ++;
   $site = $admin->get_a_site_from_list($i);
   if($row_num == 1)
   {
      if((($admin->site_list_size()) - $i) > $num_stores_per_page)
         echo("<table class=\"not_last\">\n");
      else
         echo("<table class=\"last\">\n");
      echo("\t<tr>\n");
      echo("\t\t<th width=\"15%\">STORE #</th>\n");
      echo("\t\t<th width=\"39%\">STORE NAME</th>\n");
      echo("\t\t<th width=\"16%\">NUMBER</th>\n");
      echo("\t\t<th width=\"15%\">TYPE OF LINE</th>\n");
      echo("\t</tr>\n");
      $table_started = true;
   }
?>
   <tr>
      <td class="print_medium">
         <?php
            echo($site->site_num);
            echo("<br>");
            echo($site->get_phone_company_name());
         ?>
      </td>
      <td class="print_medium">
         <?php
            $address = $site->site_name . "<br>";
            $descr = $site->get_address_description();
            if(!empty($descr))
            {
               $address .= $descr . "<br>";
            }
            $address .= $site->get_street() . "<br>"
                     . $site->get_city() . ", "
                     . $site->get_state() . " "
                     . $site->get_zip();
            echo($address);
         ?>
      </td>
      <td class="print_small">
         <?php
            $num_lines = $site->get_num_phone_lines();
            $phones = "";
            for($q=0; $q < $num_lines; $q++)
            {
               //$phones .= "$q<BR>";
               $phones .= $site->get_line_number($q) . "<br>";
            }
            echo($phones);
         ?>
      </td>
      <td class="print_small">
         <?php
            $num_lines = $site->get_num_phone_lines();
            $phones = "";
            for($q=0; $q < $num_lines; $q++)
            {
               //$phones .= "$q<BR>";
               $phones .= $site->get_line_type_name($q) . "<br>";
            }
            echo($phones);
         ?>
      </td>
   </tr>
<?
if($row_num == $num_stores_per_page)
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