<?php
session_start();
require_once("./inc/check_session.inc");
include("./inc/html_header.inc");
if(@$_GET["num"] == null)
{
?>
<h1 align="center">Activate a job posting</h1>
<h3 align="center">or go down to create a new posting</h3>
<table width=700 border=0 align="center" cellpadding=0 cellspacing=0 style='width:408.75pt; mso-cellspacing:0in;border:outset .75pt;mso-padding-alt:2.25pt 2.25pt 2.25pt 2.25pt'>
        <tr>
          <th class="other">
            <p class=MsoNormal align=center style='text-align:center'>
              <b>
                <span class='columnName'>
                  ACTIVATE
                </span>
              </b>
              <span style='font-size:9.0pt;font-family:Verdana; color:black'>
                <o:p></o:p>
              </span>
            </p>
          </th>
          <th class="jobTitle">
            <p class=MsoNormal align=center style='text-align:center'>
              <b>
                <span class='columnName'>
                  JOB TITLE
                </span>
              </b>
              <span style='font-size:9.0pt;font-family:Verdana;color:black'>
                <o:p></o:p>
              </span>
            </p>
          </th>
          <th class="address">
            <p class=MsoNormal align=center style='text-align:center'>
              <b>
                <span class='columnName'>
                  LOCATIONS
                </span>
              </b>
              <span style='font-size:9.0pt;font-family:Verdana;color:black'>
                <o:p></o:p>
              </span>
            </p>
          </th>
          <th class="contact">
            <p class=MsoNormal align=center style='text-align:center'>
              <b>
                <span class='columnName'>
                  CONTACT
                </span>
              </b>
              <span style='font-size:9.0pt;font-family:Verdana;color:black'>
                <o:p></o:p>
              </span>
            </p>
          </th>
          <th class="fax">
            <p class=MsoNormal align=center style='text-align:center'>
              <b>
                <span class='columnName'>
                  FAX#
                </span>
              </b>
              <span style='font-size:9.0pt;font-family:Verdana;color:black'>
                <o:p></o:p>
              </span>
            </p>
          </th>
          <th class="other">
            <p class=MsoNormal align=center style='text-align:center'>
              <b>
                <span class='columnName'>
                  OTHER INSTRUCTIONS
                </span>
              </b>
              <span style='font-size:9.0pt;font-family:Verdana; color:black'>
                <o:p></o:p>
              </span>
            </p>
          </th>
        </tr>
<?php
    $today = time();
    $secs_per_day = 60 * 60 * 24;
    $days_before_exp = 45;
    $secs_b4_exp = $days_before_exp * $secs_per_day;
    $expired_time = $today - $secs_b4_exp;
    $jobs = "SELECT jp.time_posted, jp.store_num, jp.contact_name as contact_person, jp.apply_online, jp.apply_in_person, jp.apply_email, "
        . "jp.apply_fax, jp.apply_call, jp.apply_other, c.contact_name, t.title_name, l.name, l.address1, "
        . "l.address2, l.city, l.state, l.zip, l.fax_number "
        . "FROM emp_job_postings jp, emp_contacts c, emp_job_titles t, emp_locations l "
        . "WHERE jp.store_num = l.store_num "
        . "AND jp.contact_code = c.contact_code "
        . "AND jp.title_code = t.title_code "
        . "AND time_posted > " . $expired_time
        . " AND active = 0";
        //echo($jobs);
    $rs = mssql_query($jobs) or die("Error");
    while($row = mssql_fetch_array($rs))
    {
        echo("<tr>\n\t");
        echo("<td class=\"jobTitle\">");
        echo("<a class=\"link\" href=\"./jobCreateProcess.php?jobNum=" . $row["time_posted"] . "\">ACTIVATE</a>");
        echo("</td>");
        echo("<td class=\"jobTitle\">\n\t\t");
        echo("<span class=\"normal\">$row[title_name]</span>\n\t");
        echo("</td>");
        $address = $row["address1"] . "<br>";
        $ad2 = trim($row["address2"]);
        if(!empty($ad2)) $address .= $ad2 . "<br>";
        $address .= $row["city"] . ", " . $row["state"] . " " . $row["zip"];
        echo("<td class=\"address\">\n\t\t");
        echo("<span class=\"normal\">$address</span>\n\t");
        echo("</td>");
        echo("<td class=\"contact\">\n\t\t");
        echo("<span class=\"normal\">");
        $contact = trim($row["contact_person"]);
        if(!empty($contact))
        {
            echo($contact . "<br><i>" . $row["contact_name"] . "</i>");
        }
        else
        {
            echo($row["contact_name"]);
        }
        echo("</span>\n\t");
        echo("</td>");
        echo("<td class=\"fax\">\n\t\t");
        echo("<span class=\"normal\">".$row["fax_number"]."</span>\n\t");
        echo("</td>");
        echo("<td class=\"other\">\n\t\t");
        echo("<span class=\"normal\">");
        $printed = false;
        if($row["apply_online"] == 1)
        {
            echo("<a href=\"jobApp.html\">Apply online</a>"
                . " or&nbsp;<br>in person at the restaurant.");
                $printed = true;
        }
        else if($row["apply_in_person"] == 1)
        {
            if($printed)
            {
                echo("<br>or<br>");
            }
            echo("Apply in person at the restaurant.");
            $printed = true;
        }
        
        if($row["apply_email"] != "false")
        {
            if($printed)
            {
                echo("<br>or<br>");
            }
            echo("Send resume with cover letter to: <a href=\"mailto:" . $row["apply_email"] . "\">"
                    . $row["apply_email"] . "</a>");
            $printed = true;
        }
        if($row["apply_fax"] == 1)
        {
            if($printed)
            {
                echo("<br>or<br>");
            }
            echo("Fax resume.");
            $printed = true;
        }
        if(!empty($row["apply_call"]))
        {
            if($printed)
            {
                echo("<br>or<br>");
            }
            echo("Call " . $row["apply_call"]);
            $printed = true;
        }
        $temp = trim($row["apply_other"]);
        if(!empty($temp))
        {
            if($printed)
            {
                echo("<br>or<br>");
            }
            echo($row["apply_other"]);
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
            //echo("<input name=\"ad1\" type=\"hidden\" value=\"$_GET[ad1]\">\n");
            echo("$_GET[ad1]\n<br>\n");
            if(!empty($_GET["ad2"]))
            {
                //echo("<input name=\"ad2\" type=\"hidden\" value=\"$_GET[ad2]\">\n");
                echo("$_GET[ad2]\n<br>\n");
            }
            //echo("<input name=\"city\" type=\"hidden\" value=\"$_GET[city]\">\n");
            //echo("<input name=\"state\" type=\"hidden\" value=\"$_GET[state]\">\n");
            //echo("<input name=\"zip\" type=\"hidden\" value=\"$_GET[zip]\">\n");
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
        $rs = mssql_query($query);
        while($row = mssql_fetch_array($rs))
        {
            echo("<option value=\"" . $row["title_code"] . "\">" . $row["title_name"] . "\n\t\t");
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
        $rs = mssql_query($query);
        while($row = mssql_fetch_array($rs))
        {
            echo("<option value=\"" . $row["contact_code"] . "\">" . $row["contact_name"] . "\n\t\t");
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
include("./inc/html_footer.inc");
?>