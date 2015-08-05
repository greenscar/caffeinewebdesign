<?php
	$user_type = "admin";
	require_once("../inc_files/check_session.inc");
	$test_seq = $_GET["tesseq"];
	$question_num = $_GET["qn"];
	$question_seq = $_GET["qs"];
	$i = 0;
	$query = "SELECT * FROM question_def" .
				" WHERE test_seq = $test_seq" . 
				" AND question_num = $question_num";
	$question = mssql_query($query);
	while($temp = mssql_fetch_array($question)){
		$a_query = "SELECT * FROM question_solution_dtl WHERE question_seq = " . $temp["question_seq"];
		$answer = mssql_query($a_query);
		$answer = mssql_fetch_array($answer);
		if(!empty($answer))	
			$sol_seq[$i++] = $answer["solution_seq"];
	}	
	$delete = "DELETE FROM question_def " .
				" WHERE test_seq = " . $_GET["tesseq"] . 
				" AND question_num = " . $_GET["qn"];
	mssql_query($delete);
	for($i=0; $i<sizeof($sol_seq); $i++){
		$delete = "DELETE FROM question_solution_dtl WHERE solution_seq = " . $sol_seq[$i];
		mssql_query($delete);
	}
	//RENUMBER ALL QUESTIONS OF THIS TEST ABOVE THIS NUMBER
	$update = "UPDATE question_def SET question_num = question_num - 1" .
				" WHERE question_num > " . $_GET["qn"] .
				" AND test_seq = $test_seq";
	mssql_query($update);
	//include("./test_modify.php?ts=$test_seq&do=mod");
	Header("Location: ./menu_admin.php?cat=test&scr=modtest&ts=$test_seq&do=mod");
	
?>