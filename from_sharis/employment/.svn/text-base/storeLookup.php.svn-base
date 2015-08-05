<?php
/*
header("Location: http://www.google.com/");
exit();
*/
//include("../../includes/php_files/ensure_admin.inc");
   include("http://www.e-sharis.net/includes/php_files/DB_Mgr.inc");
   $dbmgr = new DB_Mgr("web");
if(isset($_GET["store_num"]))
{
    $num = $_GET["store_num"];
    //echo("$_GET[store_num] = " . $_GET["store_num"));

    $query = "SELECT * FROM emp_locations WHERE store_num = '$num'";
    //$query = "select * from SI_SITE s, SI_PHONE_LINE pl, SI_PHONE_TYPE pt where s.loc_num = $num AND s.loc_num = pl.loc_num and pl.type_id = pt.type_id and pt.type_id = 'MN'";
    $dbmgr->query($query);
    //echo("num = " . $num);
    if($dbmgr->get_num_rows() == 0)
    {
        header("Location: ./jobCreate.php?bad=" . $num);
        exit();
    }
//echo("store_num = " . $_GET["store_num"]);
    $dbmgr->fetch_row();
    $name = trim($dbmgr->get_field("name"));
    $ad1 = trim($dbmgr->get_field("address1"));
    $ad2 = trim($dbmgr->get_field("address2"));
    $city = trim($dbmgr->get_field("city"));
    $state = trim($dbmgr->get_field("state"));
    $zip = trim($dbmgr->get_field("zip"));
    $fax = trim($dbmgr->get_field("fax_number"));
    //echo("num = $num<br>name=$name<br>ad1=$ad1<br>ad2=$ad2<br>city=$city<br>state=$state<br>zip=$zip<br>");
    $goTo = "/jobCreate.php?num=$num&name=$name&ad1=$ad1&ad2=$ad2&city=$city&state=$state&zip=$zip&fax=$fax";
    //$goTo = htmlentities($goTo);
    $goTo = str_replace(" ", "%20", trim($goTo));
    $goTo = "./" . $goTo;
    //echo($goTo);
    header("Location: $goTo");
}
else{
    header("Location: ./jobCreate.php");
    exit();
}
/*
else
{
    $query = "SELECT TAX_LOCATION_CD, DESCR, ADDRESS1, ADDRESS2, CITY, STATE, POSTAL" .
            " FROM PS_TAX_LOCATION1";
    $dbmgr->query($query);
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
*/
?>