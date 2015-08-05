<?php
include("./vars.inc");
include("multiuse_fxns/php_fxns.php");
$db = $databases[3];
$connection = connect_to_mssql($db[0], $db[1], $db[2], $db[3]);
$names = array("PS_DISCIP_TYPE_TBL", "PS_DISCIPLIN_ACTN", "PS_DISCIP_STEP_TBL", "PS_PERSONAL_DATA", "PS_JOBCODE_TBL");
//echo(display_mssql_db($db[0], $db[1], $db[2], $db[3]));
echo(display_tables($names, $db[0], $db[3]));
?>