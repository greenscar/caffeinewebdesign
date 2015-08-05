<?php
   error_reporting(E_ALL);
   //include("../../includes/php_files/ensure_admin.inc");
   include("../includes/php_files/DB_Mgr.inc");
   include("html_header.inc");
   $dbmgr = new DB_Mgr("web");
if(empty($_GET["num"]))
{
?>
<h1 align="center">Activate a job posting</h1>
<h3 align="center">or go down to create a new posting</h3>
<table style='width:750; mso-cellspacing:0in;border:outset .75pt;mso-padding-alt:2.25pt 2.25pt 2.25pt 2.25pt'>
        <tr>
          <th class="activate">
            <p class=MsoNormal align=center style='text-align:center'>
               <span class='columnName'>
                 ACTIVATE
               </span>
            </p>
          </th>
          <th class="storeNum">
            <p class=MsoNormal align=center style='text-align:center'>
               <span class='columnName'>
                 STORE #
               </span>
            </p>
          </th>
          <th class="jobTitle">
            <p class=MsoNormal align=center style='text-align:center'>
               <span class='columnName'>
                 JOB TITLE
               </span>
            </p>
          </th>
          <th class="address">
            <p class=MsoNormal align=center style='text-align:center'>
               <span class='columnName'>
                 LOCATIONS
               </span>
            </p>
          </th>
          <th class="contact">
            <p class=MsoNormal align=center style='text-align:center'>
               <span class='columnName'>
                 CONTACT
               </span>
            </p>
          </th>
          <th class="fax">
            <p class=MsoNormal align=center style='text-align:center'>
               <span class='columnName'>
                 FAX#
               </span>
            </p>
          </th>
          <th class="other">
            <p class=MsoNormal align=center style='text-align:center'>
               <span class='columnName'>
                 OTHER INSTRUCTIONS
               </span>
            </p>
          </th>
        </tr>
<?php
   $today = time();
   $secs_per_day = 60 * 60 * 24;
   $days_before_exp = 45;
   $days_b4_delete = 60;
   $secs_b4_exp = $days_before_exp * $secs_per_day;
   $delete_time = $days_b4_delete * $secs_per_day;
   $delete = "DELETE FROM emp_job_postings WHERE time_posted < $delete_time";
   $dbmgr->query($delete);
   $expired_time = $today - $secs_b4_exp;
   $jobs = "SELECT jp.time_posted, jp.store_num, jp.contact_name as contact_person, jp.apply_online, jp.apply_in_person, jp.apply_email, "
       . "jp.apply_fax, jp.apply_call, jp.apply_other, c.contact_name, t.title_name, l.name, l.address1, "
       . "l.address2, l.city, l.state, l.zip, l.fax_number "
       . "FROM emp_job_postings jp, emp_contacts c, emp_job_titles t, emp_locations l "
       . "WHERE jp.store_num = l.store_num "
       . "AND jp.contact_code = c.contact_code "
       . "AND jp.title_code = t.title_code "
       . "AND (time_posted < " . $expired_time
       . " OR active = 0) order by l.city";
   $dbmgr->query($jobs);
   while($dbmgr->fetch_row())
   {
      echo("<tr>\n\t");
      echo("<td class=\"jobTitle\">");
      echo("<a class=\"link\" href=\"./jobCreateProcess.php?jobNum=" .
            $dbmgr->get_field("time_posted")
            . "\">ACTIVATE</a>");
      echo("</td>");
      echo("<td class=\"jobTitle\"><span class=\"normal\">");
      echo($dbmgr->get_field("store_num"));
      echo("</span></td>");
      echo("<td class=\"jobTitle\">\n\t\t");
      echo("<span class=\"normal\">"
            . $dbmgr->get_field("title_name")
            . "</span>\n\t");
      echo("</td>");
      $address = $dbmgr->get_field("address1") . "<br>";
      $ad2 = trim($dbmgr->get_field("address2"));
      if(!empty($ad2)) $address .= $ad2 . "<br>";
      $address .= $dbmgr->get_field("city") . ", " . $dbmgr->get_field("state") . " " . $dbmgr->get_field("zip");
      echo("<td class=\"address\">\n\t\t");
      echo("<span class=\"normal\">$address</span>\n\t");
      echo("</td>");
      echo("<td class=\"contact\">\n\t\t");
      echo("<span class=\"normal\">");
      $contact = trim($dbmgr->get_field("contact_person"));
      if(!empty($contact))
      {
          echo($contact . "<br><i>" . $dbmgr->get_field("contact_name") . "</i>");
      }
      else
      {
          echo($dbmgr->get_field("contact_name"));
      }
      echo("</span>\n\t");
      echo("</td>");
      echo("<td class=\"fax\">\n\t\t");
      echo("<span class=\"normal\">".$dbmgr->get_field("fax_number")."</span>\n\t");
      echo("</td>");
      echo("<td class=\"other\">\n\t\t");
      echo("<span class=\"normal\">");
      $printed = false;
      if($dbmgr->get_field("apply_online") == 1)
      {
          echo("<a href=\"jobApp.html\">Apply online</a>"
              . " or&nbsp;<br>in person at the restaurant.");
              $printed = true;
      }
      else if($dbmgr->get_field("apply_in_person") == 1)
      {
          if($printed)
          {
              echo("<br>or<br>");
          }
          echo("Apply in person at the restaurant.");
          $printed = true;
      }
      if($dbmgr->get_field("apply_email") != "false")
      {
          if($printed)
          {
              echo("<br>or<br>");
          }
          echo("Send resume with cover letter to: <a href=\"mailto:" . $dbmgr->get_field("apply_email") . "\">"
                  . $dbmgr->get_field("apply_email") . "</a>");
          $printed = true;
      }
      if($dbmgr->get_field("apply_fax") == 1)
      {
          if($printed)
          {
              echo("<br>or<br>");
          }
          echo("Fax resume.");
          $printed = true;
      }
      $call = $dbmgr->get_field("apply_call");
      if(!empty($call))
      {
          if($printed)
          {
              echo("<br>or<br>");
          }
          echo("Call " . $dbmgr->get_field("apply_call"));
          $printed = true;
      }
      $temp = trim($dbmgr->get_field("apply_other"));
      if(!empty($temp))
      {
          if($printed)
          {
              echo("<br>or<br>");
          }
          echo($dbmgr->get_field("apply_other"));
      }
      echo("</span>\n\t");
      echo("</td>");
   }
?>
    
      <td class="job">
      <span class="normal"></span>
      </td>
     </tr>
  </table>
  
<hr size="10" width="100%">
<?
}
?>
<form name="jobForm" method="post" action="jobCreateProcess.php" onsubmit="return checkForm(this)">
<table width="750" border="0" cellspacing="3" cellpadding="3" align="center">
<?php

   if(!empty($_GET["bad"]))
   {
      echo("<tr><td colspan=\"2\" align=\"center\">\n");
      echo("<H3 class=\"error\">" . $_GET["bad"] . " is not one of our stores. Please reenter.</h3>\n");
      echo("</td></tr>\n");
   }
?>
   <tr>
      <td colspan="2"><h2 align="center">Enter the Job Information</h2></td>
   </tr>
   <tr>
      <td>Store Number:</td>
      <td>
   <?php
      if(empty($_GET["num"]))
        {
            echo("<input name=\"store_number\" type=\"text\" size=\"10\" maxlength=\"3\">&nbsp;&nbsp;");
            ?>
            <input type="button" name="buttonLookUp" value="Look Up Store" onclick="return lookUpStore(document.jobForm)"></td>
            <?php
        }
        else
        {
            echo("<input name=\"store_number\" type=\"hidden\" value = \"$_GET[num]\">\n");
            echo("$_GET[num]<br>\n");
        }
    ?>
  </tr>
  <tr>
   <td>Store Location:</td>
   <td rowspan="2">
   <?php
        if(!empty($_GET["ad1"]))
        {
            //echo("<input name=\"ad1\" type=\"hidden\" value=\"$_GET[ad1)\">\n");
            echo("$_GET[name]\n<br>\n");
            echo("$_GET[ad1]\n<br>\n");
            if(!empty($_GET["ad2"]))
            {
                //echo("<input name=\"ad2\" type=\"hidden\" value=\"$_GET[ad2)\">\n");
                echo("$_GET[ad2]\n<br>\n");
            }
            //echo("<input name=\"city\" type=\"hidden\" value=\"$_GET[city)\">\n");
            //echo("<input name=\"state\" type=\"hidden\" value=\"$_GET[state)\">\n");
            //echo("<input name=\"zip\" type=\"hidden\" value=\"$_GET[zip)\">\n");
            echo("$_GET[city],&nbsp;$_GET[state]&nbsp;$_GET[zip]<br>\n");
        }
        else
        {
            echo("<textarea name=\"store_location\" wrap=\"OFF\" class=\"store_location\">");
            echo("</textarea>\n");
        }
    ?>
	</td>
  </tr>
  <tr>
    <td height="26">&nbsp;</td>
  </tr>
  <tr>
    <td width="17%">Job Title:</td>
    <td width="83%">
    <select name="job_title">
    <?php
        /*
        * Get Job titles from DB
        */
      $query = "SELECT * FROM emp_job_titles";
      $dbmgr->query($query);
      while($dbmgr->fetch_row())
      {
         echo("<option value=\"" . $dbmgr->get_field("title_code") . "\">" . $dbmgr->get_field("title_name") . "\n\t\t");
      }
    ?>
    </select>
    </td>
  </tr>
  <tr>
    <td>Contact: </td>
    <td>
      <select name="contact">
      <?php
        /*
        * Get Contacts from emp_contacts
        */
        $query = "SELECT * FROM emp_contacts";
        $dbmgr->query($query);
        while($dbmgr->fetch_row())
        {
            echo("<option value=\"" . $dbmgr->get_field("contact_code") . "\">" . $dbmgr->get_field("contact_name") . "\n\t\t");
        }
    ?>
      </select>
      </td>
  </tr>
  <tr>
    <td>Contact Name:</td>
    <td><input name="contact_name" type="text" size="30" maxlength="50"></td>
  </tr>
  <tr>
    <td>Fax #: </td>
    <td>
        <?php
            if(!empty($_GET["fax"]))
            {
                echo($_GET["fax"]);
            }
            else
            {
                echo("<input name=\"fax_number\" type=\"text\" size=\"30\" maxlength=\"50\">");
            }
    ?>
        </td>
  </tr>
  <tr>
    <td>Other Instructions:</td>
    <td><input type="checkbox" name="online" value="true">      
      Apply Online</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input type="checkbox" name="in_person" value="true">
Apply in Person at the restaurant.</td>
  </tr>
  <tr>
    <td height="17">&nbsp;</td>
    <td><input type="checkbox" name="email" value="true">
Email resume with cover letter to:&nbsp;
 <input name="email_address" type="text" size="30" maxlength="50">
</td>
  </tr>
  <tr>
    <td height="29">&nbsp;</td>
    <td><input type="checkbox" name="fax" value="true"> 
      Fax resume</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input type="checkbox" name="call" value="true">
      Call:&nbsp;
      <input name="call_number" type="text" size="30" maxlength="15"></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="top">      <input type="checkbox" name="other" value="true">
      Other:<br> 
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea class="other_instructions" name="other_instructions"></textarea></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <input type="submit" name="button_insert" value="Insert Job">
    </div></td>
    </tr>
</table>
</form>
<?php
$dbmgr->disconnect();
include("html_footer.inc");
?>