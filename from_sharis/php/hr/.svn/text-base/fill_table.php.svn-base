<html>
<head>
</head>
<body>
<?php
require_once("./multiuse_fxns/php_fxns.php");
fillDiscTable();
function fillDiscTable()
{
    $types = array("EAB", "VOP", "FFD", "INS", "TAR", "VSR", "LWA", "PBS", "ICP", "OTH");
    $descriptions = array(
                        "Excessive Absenteeism",
                        "Violation of Policy",
                        "Failure to Follow Directions",
                        "Insubordination",
                        "Tardiness",
                        "Violation of Safety Rules",
                        "Leaving Work Without Authorization",
                        "Performance Below Standards",
                        "Improper Call-In Procedures",
                        "Other"
                    );
    $descShort = array(
                        "Absent",
                        "Policy Viol",
                        "Directions",
                        "Insubord",
                        "Late",
                        "Safety Viol",
                        "Leave Work",
                        "Low Perform",
                        "Bad Call-In",
                        "Other"
                    );
    include("./vars.inc");
    connect_to_mssql($server, $id, $pwd, $db);
    for($i=0; $i<sizeof($types);$i++)
    {
        //mssql_query("DELETE FROM PS_DISCIP_TYPE_TBL");
        $insert = "INSERT INTO PS_DISCIP_TYPE_TBL VALUES('"
                . $types[$i] . "', '"
                . strftime("%b %d %Y %H:%M:%S",time()) . "', "
                . "'A' , '"
                . $descriptions[$i] . "', '"
                . $descShort[$i] . "');";
        mssql_query($insert) or die("Error in " . $insert);
        echo($insert . "<BR>\n");
    }
    echo(display_table("PS_DISCIP_TYPE_TBL"));
    
}
?>
</body>
</html>