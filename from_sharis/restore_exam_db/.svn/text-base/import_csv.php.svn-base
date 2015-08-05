<?php
require_once("../../../php_objects/DB_Mgr.inc");
$dbmgr = new DB_Mgr("web");

import_EMP_ANSWER($dbmgr);
import_EXAM_TAKE($dbmgr);
function import_EMP_ANSWER($dbmgr)
{
	$handle = fopen("ses_EMP_ANSWER.CSV", "r");
	echo("handle = $handle <BR>");
	while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) 
	{
		//echo "<p> $num fields in line $row: <br />\n";
		$ins = "INSERT INTO ses_EMP_ANSWER (insert_time, take_num, quest_loc, answer_entered, correct, points_earned, comment) "
				. "VALUES (" . $data[0] . ", " . $data[1] . ", "
				. $data[2] . ", '" . addslashes($data[3]) . "', " . $data[4] . ", "
				. $data[5] . ", '" . $data[6] . "')";
		echo($ins . "<BR>");
		
		/*
		$insert = "INSERT INTO shp_item (item_id, description, cost, gl_acct, active) "
				. "VALUES ($row, '" . htmlspecialchars($data[1], ENT_QUOTES) . "', " 
				. substr($data[2], 1) . ", "
				. $data[3] . ", 1);";
				
		
		if(!(odbc_exec($conn, $insert)))
		{
			echo("ERROR IN:<br>");
			echo($insert);
		}
		$row++;
		echo($insert . "<br>");
		*/
	}
	//echo("Items Added.");
	fclose($handle);
}
function import_EXAM_TAKE($dbmgr)
{
	$handle = fopen("ses_EXAM_TAKE.CSV", "r");
	while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) 
	{
		//echo "<p> $num fields in line $row: <br />\n";
		$ins = "INSERT INTO ses_EXAM_TAKE (take_num, exam_num, date_taken, emp_num, final_grade, graders_comment) "
				. "VALUES (" . $data[0] . ", " . $data[1] . ", "
				. $data[2] . ", " . $data[3] . ", " . $data[4] . ", "
				. addslashes($data[5]) . ")";
		echo($ins . "<br>");
		
		/*
		$insert = "INSERT INTO shp_item (item_id, description, cost, gl_acct, active) "
				. "VALUES ($row, '" . htmlspecialchars($data[1], ENT_QUOTES) . "', " 
				. substr($data[2], 1) . ", "
				. $data[3] . ", 1);";
				
		
		if(!(odbc_exec($conn, $insert)))
		{
			echo("ERROR IN:<br>");
			echo($insert);
		}
		$row++;
		echo($insert . "<br>");
		*/
	}
	//echo("Items Added.");
	fclose($handle);
}
?>