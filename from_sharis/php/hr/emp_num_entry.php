<html>
<head>
<style type="text/css">
@import url("styles.css");
</style>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<body>
<div align="center">
<?php
if(isset($_GET["not_found"]))
{
    echo("<h1 align=\"center\">$_GET[not_found] is not a valid ID. Please reenter.</h1>\n");
}
else
{
    echo("<h1 align=\"center\">Please Enter the Payroll ID</h1>\n");
}
?>
<form name="payrollID" method="post" action="disciplinary_notice.php">
<table class='outer_frame'>
  <tr width="253">
    <td width="231"><table width="100%" border="0" cellspacing="3" cellpadding="3">
        <tr>
          <td width="35%">Payroll ID:</td>
          <td width="65%"><input type="text" class="emp_num" name="emp_num" value="K0G001"></td>
        </tr>
        <tr>
          <td colspan="2"><div align="center">
            <input type="submit" name="Submit" value="Continue">
          </div></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</form>
</div>
</body>
</html>
