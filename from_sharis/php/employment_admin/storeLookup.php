<?php
session_start();
require_once("./inc/check_session.inc");
require_once("./inc/some_useful_fxns.inc");
require_once("./inc/sql_connect.inc");
if(isset($_GET["store_num"]))
{
    /*
     * User has provided a store number. Look up information about this store.
     */
    //echo("$_GET[store_num] = " . $_GET["store_num"]);
    $query = "SELECT * FROM emp_locations WHERE store_num = '" . $_GET["store_num"] . "'";
    //echo($query . "<BR>");
    $rs = mssql_query($query);
    $num = mssql_num_rows($rs);
    if($num == 0)
    {
        Header("Location: ./jobCreate.php?bad=" . $_GET["store_num"]);
        exit();
    }
    $row = mssql_fetch_array($rs);
    $num = $_GET["store_num"];
    $name = trim($row["name"]);
    $ad1 = trim($row["address1"]);
    $ad2 = trim($row["address2"]);
    $city = trim($row["city"]);
    $state = trim($row["state"]);
    $zip = trim($row["zip"]);
    $fax = trim($row["fax_number"]);
    //echo("num = $num<br>name=$name<br>ad1=$ad1<br>ad2=$ad2<br>city=$city<br>state=$state<br>zip=$zip<br>");
    $goTo = "./jobCreate.php?num=$num&name=$name&ad1=$ad1&ad2=$ad2&city=$city&state=$state&zip=$zip&fax=$fax";
    //$goTo = htmlentities($goTo);
    $goTo = str_replace(" ", "%20", trim($goTo));
    Header("Location: ./$goTo");
}
else
{
    /*
     * User has not provided a store number. Give user a list of stores to choose from.
     */
    $query = "SELECT TAX_LOCATION_CD, DESCR, ADDRESS1, ADDRESS2, CITY, STATE, POSTAL" .
            " FROM PS_TAX_LOCATION1";
    $rs = mssql_query($query);
    ?>
    <table class="storeList">
            <tr>
                    <td>
                            <a href="storeLookup.php?sort=store_num" target="_self">
                                    <img src='down.bmp' border=0>
                            </a>
                            <a href='storelookup.asp?UserSort=store_num desc' target='_self'>
                                    <img src='up.bmp' border=0>
                            </a>
                    </td>
                    <td>
                            <a href="storeLookup.php?sort=store_name" target="_self">
                                    <img src='down.bmp' border=0>
                            </a>
                            <a href='storelookup.asp?UserSort=store_name desc' target='_self'>
                                    <img src='up.bmp' border=0>
                            </a>
                    </td>
                    <td>
                            <a href="storeLookup.php?sort=city" target="_self">
                                    <img src='down.bmp' border=0>
                            </a>
                            <a href='storelookup.asp?UserSort=city desc' target='_self'>
                                    <img src='up.bmp' border=0>
                            </a>
                    </td>
                    <td>
                            <a href="storeLookup.php?sort=state" target="_self">
                                    <img src='down.bmp' border=0>
                            </a>
                            <a href='storelookup.asp?UserSort=state desc' target='_self'>
                                    <img src='up.bmp' border=0>
                            </a>
                    </td>
                    <td>
                            <a href="storeLookup.php?sort=zip" target="_self">
                                    <img src='down.bmp' border=0>
                            </a>
                            <a href='storelookup.asp?UserSort=zip desc' target='_self'>
                                    <img src='up.bmp' border=0>
                            </a>
                    </td>
            </tr>
            <tr>
                    <th>Number</th>
                    <th>Name</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Zip</th>
            </tr>
                    
    <?
    while($row = mssql_fetch_row($rs))
    {
    }
}
?>