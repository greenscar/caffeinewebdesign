<?php
$title = "Store Tables";
include("../../../php_objects/DB_Mgr.inc");
include("../../../php_objects/php_fxns.inc");
error_reporting(E_ALL);
$dbmgr = new DB_Mgr("train");
//$dbmgr->connect();
/*
$q = "select * 
from PS_JOB psj (nolock)
WHERE
psj.EFFDT =
(SELECT MAX(EFFDT) FROM PS_JOB J1 WHERE J1.EMPLID = psj.EMPLID)
AND psj.EFFSEQ =
(SELECT MAX(EFFSEQ) FROM PS_JOB J2 WHERE J2.EMPLID = psj.EMPLID
AND J2.EFFDT = psj.EFFDT)
and psj.JOBCODE < 100
AND psj.EMPL_STATUS = 'A'";
$dbmgr->query($q);
if($row = $dbmgr->fetch_array())
{
   foreach($row as $id=>$val)
   {
      echo("$id = $val<br>");
   }
}
*/
$dbmgr->disconnect();

/*************************************
 * TRANSFER REGIONS
 * INSERT INTO PRODSUP..PS_TRN_EQUIP_TBL (EQUIP_CD, EQUIP_TYPE, DESCR, DESCRSHORT, AUTHOR, YR_PUBLISHED, ISBN) SELECT EQUIP_CD, EQUIP_TYPE, DESCR, DESCRSHORT, AUTHOR, YR_PUBLISHED, ISBN FROM TRAIN..PS_TRN_EQUIP_TBL;
 ************************************/
/*************************************
 * END TRANSFER REGIONS
 ************************************/
/*************************************
 * TRANSFER DISTRICTS
 ************************************/
/*************************************
 * END TRANSFER DISTRICTS
 ************************************/
/*************************************
 * TRANSFER STORES
 ************************************/
/*************************************
 * END TRANSFER STORES
 ************************************/
?>