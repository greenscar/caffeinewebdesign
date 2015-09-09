<?php
   include("../../includes/php_files/ensure_admin.inc");
   include("html_header.inc");
   error_reporting(E_ALL);
?>
<table class="mainMenu">
    <tr align="center">
        <td>
            <a class="mainMenu" href="./jobCreate.php">Create a Job</a>
        </td>
    </tr>
    <tr align="center">
        <td>
            <a class="mainMenu" href="./jobDelete.php">Remove a Job</a>
        </td>
    </tr>
</table>
<br><br>
<?php
require("currentJobDisplay.inc");
?>
<?php
include("html_footer.inc");
?>