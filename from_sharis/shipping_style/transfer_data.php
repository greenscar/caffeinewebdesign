<?php
$title = "Store Tables";
//include("../../../php_objects/php_fxns.inc");
error_reporting(E_ALL);

/*************************************
 * TRANSFER REGIONS
 * INSERT INTO PRODSUP..PS_TRN_EQUIP_TBL (EQUIP_CD, EQUIP_TYPE, DESCR, DESCRSHORT, AUTHOR, YR_PUBLISHED, ISBN) SELECT EQUIP_CD, EQUIP_TYPE, DESCR, DESCRSHORT, AUTHOR, YR_PUBLISHED, ISBN FROM TRAIN..PS_TRN_EQUIP_TBL;
 ************************************/
//INSERT INTO CO_REGION (CO_ID, REG_ID, MGR_EMPLID) VALUES ('SMC', 1, 36005);
//INSERT INTO CO_REGION (CO_ID, REG_ID, MGR_EMPLID) VALUES ('SMC', 2, 1500);
/*************************************
 * END TRANSFER REGIONS
 ************************************/
/*************************************
 * TRANSFER DISTRICTS
 ************************************/
//INSERT INTO CO_DISTRICT (CO_ID, REG_ID, DIST_ID, MGR_EMPLID) SELECT company_id, region_id, district_id, mgr_emp_num FROM SI_DISTRICT
/*************************************
 * END TRANSFER DISTRICTS
 ************************************/
/*************************************
 * TRANSFER STORES
 ************************************/
$sel = "SELECT DISTINCT t.district_id, t.region_id, "
     . "si.dept_id, si.site_name, si.loc_name, si.sysco_#, si.date_open, si.active, "
     . "a.street, a.city, a.state, a.zip "
     . "FROM SI_SITE si, SI_TERRITORY t, SI_ADDRESS a "
     . "WHERE si.dept_id = t.dept_id "
     . "AND a.address_id = si.address_id";
echo($sel);
$connWeb = mssql_connect("PSTEST", "sa", "sa");
mssql_select_db("WEB_DEV", $connWeb);
$rs = mssql_query($sel, $connWeb);
$arrays = array();
while($row = mssql_fetch_array($rs))
{
   array_push($arrays, $row);
}

foreach($arrays as $row)
{
   $dist = trim($row["district_id"]);
   $reg = trim($row["region_id"]);
   $dept =  trim($row["dept_id"]);
   $name = trim($row["site_name"]);
   $loc1 = trim($row["loc_name"]);
   $sysco = trim($row["sysco_#"]);
   $date_open = $row["date_open"];
   $active = $row["active"];
   
   $street = $row["street"];
   $city = $row["city"];
   $state = $row["state"];
   $zip = $row["zip"];
   
   $insert = "INSERT INTO CO_CONTACT_INFO "
      . "(STREET1, CITY, STATE, ZIP, EMAIL) "
      . "VALUES ('"
      . str_replace("'", "''", trim($street)) . "', '"
      . str_replace("'", "''", trim($city)) . "', '"
      . str_replace("'", "''", trim($state)) . "', '"
      . str_replace("'", "''", trim($zip)) . "', '"
      . "Store-" . $dept . "@sharis.com');";
      
   if(!mssql_query($insert)) die("ERROR IN $insert");
   echo("$insert<br>");
   $sel = "SELECT MAX(CONTACT_ID) FROM CO_CONTACT_INFO";
   $rs = mssql_query($sel);
   $row = mssql_fetch_row($rs);
   $max = $row[0];
   $insert = "INSERT INTO CO_STORE "
           . "(CO_ID, REG_ID, DIST_ID, STORE_ID, DESCR, DESCR2, SYSCO_ACCT_ID, ACTIVE, DATE_ACTIVE, CONTACT_ID) "
           . "VALUES "
           . "('SMC', '"
           . str_replace("'", "''", trim($reg)) . "', '"
           . str_replace("'", "''", trim($dist)) . "', '"
           . str_replace("'", "''", trim($dept)) . "', '"
           . str_replace("'", "''", trim($name)) . "', '"
           . str_replace("'", "''", trim($loc1)) . "', '"
           . str_replace("'", "''", trim($sysco)) . "', '"
           . str_replace("'", "''", trim($active)) . "', '" 
           . str_replace("'", "''", trim($date_open)) . "', '"
           . str_replace("'", "''", trim($max)) . "')";
   echo("$insert<br>");
   if(!mssql_query($insert)) die("ERROR IN $insert");
}
/*************************************
 * END TRANSFER STORES
 ************************************/
/*************************************
 * TRANSFER DISTRICT & REGION MGRS
 ************************************/
/*
$connPS = mssql_connect("PSTEST", "sa", "sa");
mssql_select_db("TRAIN", $connPS);

$emplids = array();
$firsts = array();
$lasts = array();


$q = "select psj.EMPLID, pse.FIRST_NAME, pse.PREF_FIRST_NAME, pse.LAST_NAME, pse.LOCATION, 
pse.ADDRESS1, pse.ADDRESS2, pse.CITY, pse.STATE, pse.POSTAL, 
psj.JOBCODE, psj.DEPTID, pse.DEPTNAME, pse.DEPTNAME_ABBRV
from PS_EMPLOYEES pse, PS_JOB psj 
where psj.DEPTID <= 3 AND psj.EFFDT = (SELECT MAX(EFFDT) FROM PS_JOB J1 WHERE J1.EMPLID = pse.EMPLID)
and psj.EFFSEQ = (SELECT MAX(EFFSEQ) FROM PS_JOB J2 WHERE J2.EMPLID = pse.EMPLID AND J2.EFFDT = psj.EFFDT)
and pse.EMPLID = psj.EMPLID and psj.JOBCODE = 1 order by DEPTNAME";

$rs = mssql_query($q, $connPS);
while($row = mssql_fetch_array($rs))
{
   array_push($emplids, trim($row["EMPLID"]));
   array_push($firsts, trim($row["FIRST_NAME"]));
   array_push($lasts, trim($row["LAST_NAME"]));
}
mssql_close($connPS);

$connWeb = mssql_connect("PSTEST", "sa", "sa");
mssql_select_db("WEB_DEV", $connWeb);
for($i=0; $i < sizeof($emplids); $i++)
{
   $ins = "INSERT INTO CO_EMPLOYEE (EMPLID, NAME_FIRST, NAME_LAST) VALUES (" . $emplids[$i] . ", '" . $firsts[$i] . "', '" . $lasts[$i] . "');";
   echo($ins . "<BR>");
   //mssql_query($ins, $connWeb) or die("ERROR");
}
*/
/*************************************
 * END TRANSFER DISTRICT & REGION MGRS
 ************************************/
?>