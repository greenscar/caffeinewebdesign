<?php
function remove_duplicate_prereqs(&$_POST, &$prereq_count){
	//echo("prereq_count = $prereq_count<BR>");

	$this_course_seq = $_POST["course_seq"];
	for($i=1, $numPres = 0;$i<=sizeof($_POST);$i++){
		$temp = "prereq_" . $i;
		if(isset($_POST[$temp])){
			$prereqseq = $_POST[$temp];
			$prereq[$numPres] = $_POST[$temp];
			$SEL = "SELECT prereq_seq_1, prereq_seq_2, prereq_seq_3, prereq_seq_4, prereq_seq_5 " .
					"FROM course_def WHERE course_seq = " . $prereqseq;
			$query = mssql_query($SEL);
			$ROW[$numPres] = mssql_fetch_row($query);
			$numPres++;
		}
	}
	$all_prereq = array();
	switch($prereq_count){
		case 0:
			break;
		case 1:
			$all_prereq = array_diff($prereq, $ROW[0]);
			break;
		case 2:
			$all_prereq = array_diff($prereq, $ROW[0], $ROW[1]);
			break;
		case 3:
			$all_prereq = array_diff($prereq, $ROW[0], $ROW[1], $ROW[2]);
			break;
		case 4:
			$all_prereq = array_diff($prereq, $ROW[0], $ROW[1], $ROW[2], $ROW[3]);
			break;
		case 5:
			$all_prereq = array_diff($prereq, $ROW[0], $ROW[1], $ROW[2], $ROW[3], $ROW[4]);
			break;
	}
	$all_prereq = array_values($all_prereq);
	$prereq_num = 1;
	for($i = 0;$i < sizeof($all_prereq); $i++){
		$all_prereqs["prereq_" . ($i+1)] = $all_prereq[$i];
	}
	/*
	echo("PREREQS AFTER REMOVING PREPRES<BR>");
	view_array($all_prereqs);
	echo("_POST<BR>");
	view_array($_POST);
	*/
	//REMOVE ALL PREREQUISITS FROM $_POST AND THEN APPEND $all_prereq TO $_POST
	$all_keys = array_keys($_POST);
	for($x=0;$x<sizeof($all_keys); $x++){
		if(strncmp($all_keys[$x], "prereq_", 7) == 0) $_POST[$all_keys[$x]] = null;
	}	
	$_POST = (array_filter($_POST, "notnull"));
	$_POST = array_merge($_POST, $all_prereqs);
	
	//echo("_POST FILTERED<BR>");
	//view_array($_POST);
	
	$_POST = (array_filter($_POST, "notnull"));
}

function notnull($temp){
	return !empty($temp);
}
?>