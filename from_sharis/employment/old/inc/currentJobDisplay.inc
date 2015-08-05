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
    $rs = mssql_query($jobs) or die("Error");
    while($row = mssql_fetch_array($rs))
    {
        echo("<tr>\n\t");
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
  <br><br>