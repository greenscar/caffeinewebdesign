<?php
   include("../includes/php_files/ensure_admin.inc");
   $dbmgr = new DB_Mgr("web");
if(isset($_GET["jobNum"]))
{
    $update = "UPDATE emp_job_postings set time_posted = " . time()
            . ", active = 1 WHERE time_posted = " . $_GET["jobNum"];
    $dbmgr->query($update);
}
else
{
    $insert = "INSERT INTO emp_job_postings values("
                . time() . ", "
                . $_POST["store_number"] . ", \""
                . $_POST["job_title"] . "\", \""
                . $_POST["contact"] . "\", \""
                . $_POST["contact_name"] . "\"";
                
    if(isset($_POST["online"]))
        $insert .= ", 1";
    else
        $insert .= ", 0";
    if(isset($_POST["in_person"]))
        $insert .= ", 1";
    else
        $insert .= ", 0";
    if(isset($_POST["email"]))
    {
        $address = $_POST["email_address"];
        $insert .= ", \"$address\"";
    }
    else
        $insert .= ", \"false\"";
    if(isset($_POST["fax"]))
        $insert .= ", 1";
    else
        $insert .= ", 0";
    if(isset($_POST["call"]))
    {
        $number = $_POST["call_number"];
        $insert .= ", \"$number\"";
    }
    else
        $insert .= ", \"0\"";
    if(isset($_POST["other"]))
    {
        $instructions = $_POST["other_instructions"];
        $insert .= ", \"$instructions\"";
    }
    else
        $insert .= ", \"\"";
    $insert .= ", 1);";
    $dbmgr->query($insert);
}
$dbmgr->disconnect();
header("Location: ./index.php");
?>