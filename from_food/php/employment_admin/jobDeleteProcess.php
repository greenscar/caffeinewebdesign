<?php
    session_start();
    require_once("./inc/check_session.inc");
    require_once("./inc/some_useful_fxns.inc");
    require_once("./inc/sql_connect.inc");
    $delete = "UPDATE emp_job_postings SET active = 0 WHERE time_posted = " . $_GET["jobNum"];    
    if(!mssql_query($delete))
    {
        echo("<h1>ERROR ON DELETE</h1>");
        echo("<h2>$delete</h2>");
    }
    else
    {
        header("Location: ./index.php");
    }
?>