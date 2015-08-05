
<?php
// RETURN AN ARRAY OF POSITIONS
function load_job_positions()
{
    //include("./vars.inc");
    //$db = $databases[3];
    //$connection = connect_to_mssql($db[0], $db[1], $db[2], $db[3]);
    $positions = array();
    $query = "SELECT JOBCODE, DESCR FROM PS_JOBCODE_TBL";
    $temp = mssql_query($query);
    while($row = mssql_fetch_array($temp))
    {
        $positions[$row[0]] = trim($row[1]);
    }
    return $positions;
}
function load_emp_name($empNum)
{
    //include("./vars.inc");
    $query = "SELECT * FROM PS_PERSONAL_DATA WHERE EMPLID = '" . $empNum . "'";
    //$query = "SELECT * FROM " . $names[$i];
    $temp = mssql_query($query);
    if(mssql_num_rows($temp) == 0)
    {
        //echo("alert(\"That employee number was not found.\nPlease try again.\")");
        Header("Location: ./emp_num_entry.php?not_found=$empNum"); 
        exit();
    }
    $row = mssql_fetch_row($temp);
    return $row[1];
}
function load_violations()
{
    $violations = array();
    $query = "SELECT DISCIPLINARY_TYPE, DESCR FROM PS_DISCIP_TYPE_TBL WHERE EFF_STATUS = 'A'";
    $temp = mssql_query($query);
    while($row = mssql_fetch_array($temp))
    {
        $violations[$row[0]] = trim($row[1]);
    }
    return $violations;
}
function load_actions()
{
    $actions = array();
    $query = "SELECT DISCIPLINARY_STEP, DESCR FROM PS_DISCIP_STEP_TBL WHERE EFF_STATUS = 'A'";
    $temp = mssql_query($query);
    while($row = mssql_fetch_array($temp))
    {
        $actions[$row[0]] = trim($row[1]);
    }
    return $actions;
}
function load_store_nums(){
    $stores = array();
    $query = "SELECT DEPTID, DESCR FROM PS_DEPT_TBL WHERE EFF_STATUS = 'A' AND COMPANY = 'SMC'";
    $query = "SELECT DEPTID, DESCR FROM PS_DEPT_TBL";
    $temp = mssql_query($query);
    while($row = mssql_fetch_array($temp))
    {
        $stores[$row[0]] = trim($row[1]);
    }
    return $stores;
}
?>