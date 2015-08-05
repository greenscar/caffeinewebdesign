<?
    require_once("../../includes/php_files/DB_Mgr.inc");
    $dbmgr = new DB_Mgr("web");
?>
<h1 align="center">Current Job Postings</h1>
<table width=700 border=0 align="center" cellpadding=0 cellspacing=0 style='width:408.75pt; mso-cellspacing:0in;border:outset .75pt;mso-padding-alt:2.25pt 2.25pt 2.25pt 2.25pt'>
        <tr>
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
        . " AND active = 1";
        //echo($jobs);
    $dbmgr->query($jobs);
    //$rs = mssql_query($jobs) or die("Error");
    //while($row = mssql_fetch_array($rs))
    while($dbmgr->fetch_array())
    {
        echo("<tr>\n\t");
        echo("<td class=\"jobTitle\">\n\t\t");
        echo("<span class=\"normal\">" . $dbmgr->get_field("title_name") . "</span>\n\t");
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
        $ac = $dbmgr->get_field("apply_call");
        if(!empty($ac))
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
  <br><br>