<html>
<body>
<table>
<tr>
   <th>Take Num</th>
   <th>Exam Num</th>
   <th>Date Taken</th>
   <th>Emp Num</th>
   <th>Final Grade</th>
   <th>Comment</th>
</tr>
<?php
include("../../php_objects/DB_Mgr.inc");
include("../../php_objects/php_fxns.inc");
$dbmgr = new DB_Mgr("web");
$delB4 = (mktime(0,0,0,3,5,2005) * 1000);
//echo("delB4 = " . date("d-M-Y", $delB4));
$dates = array();
$dbmgr->query("SELECT take_num FROM ses_EXAM_TAKE where date_taken < " . $delB4);
while($row = $dbmgr->fetch_array())
{
   array_push($dates, $row["take_num"]);
}
view_array($dates);



//$dbmgr->query("DELETE FROM ses_EMP_ANSWER where insert_time < " . $delB4);

//$dbmgr->query("DELETE FROM ses_EXAM_TAKE where date_taken < " . $delB4);
/*
while($rs = $dbmgr->fetch_array())
{
   echo("<tr>");
   echo("<td>" . $rs["take_num"] . "</td>");
   echo("<td>" . $rs["exam_num"] . "</td>");
   echo("<td>" . date("M-d-Y", $rs["date_taken"] / 1000) . "</td>");
   echo("<td>" . $rs["emp_num"] . "</td>");
   echo("<td>" . $rs["final_grade"] . "</td>");
   echo("</tr>");
   //echo("diff => " . getNumWeeksDiff($rs[2]) . "<br>");
   //echo(view_array($rs));
}
*/
function getNumWeeksDiff($time)
{
   // calculate elapsed time (in seconds!)
   $diff = time()-$time;
   $yearsDiff = floor($diff/60/60/24/365);
   $diff -= $yearsDiff*60*60*24*365;
   $monthsDiff = floor($diff/60/60/24/30);
   $diff -= $monthsDiff*60*60*24*30;
   $weeksDiff = floor($diff/60/60/24/7);
   return $weeksDiff;
}
?>
</body>
</html>