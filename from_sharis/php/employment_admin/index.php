<?php
session_start();
require_once("./inc/check_session.inc");
include("./inc/html_header.inc");
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
include("./inc/currentJobDisplay.inc");
?>
<?php
include("./inc/html_footer.inc");
?>