<?php
function display_all_post_vars()
{
    $keys = array_keys($_POST);
    for($i=0; $i < sizeof($keys); $i++)
    {
        echo("POST[$keys[$i]] = " . $_POST[$keys[$i]] . "<BR>");
    }
}


function connect_to_mssql($host_name, $user_name, $password, $database)
{
    $dbcnx = MSSQL_CONNECT($host_name,$user_name,$password) or DIE("<h1>DATABASE FAILED TO RESPOND.</h1>"); 
    if(!mssql_select_db("$database")){
            echo( "<P>Unable to connect to the database server at this time.</P>");
            exit();
    }
    return $dbcnx;
}
?>