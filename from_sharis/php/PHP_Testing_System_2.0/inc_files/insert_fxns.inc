<?php
function insert_question_into_db($_POST, $test){
	require_once("../inc_files/some_useful_functions.inc");
	$type_seq = $_POST["q_type_seq"];
	//INSERT INFORMATION INTO VARIABLES.
	$description = @$_POST["description"];
	$num_blanks = $_POST["num_blanks"];
	$num_choices = $_POST["num_choices"];
	$question_num = $test["question_num"] - 1;	
	$test_seq = $test["test_seq"];
	$t_seq = "SELECT type_seq FROM question_type_def WHERE type_name = \"matching\" OR type_name = \"word_list\"";
	$t_seq = mssql_query($t_seq);
	$matching_seq = mssql_fetch_array($t_seq);
	$word_list_seq = mssql_fetch_array($t_seq);
	$matching_seq = $matching_seq["type_seq"];
	$word_list_seq = $word_list_seq["type_seq"];
	if(($_POST["q_type_seq"] == $matching_seq) || ($_POST["q_type_seq"] == $word_list_seq)){
		$matching_subject = @$_POST["subject"];
	}
	$t_seq = "SELECT type_seq FROM question_type_def WHERE type_name = \"subject\"";
	$t_seq = mssql_query($t_seq);
	$t_seq = mssql_fetch_array($t_seq);
	$subject_seq = $t_seq["type_seq"];
	//if($type_seq == $subject_seq)
	//	$question_num = 0;	
	$q = 0;
	$a = 0;
	$c = 0;
	foreach ($_POST as $key => $value){ 
		switch($key){
			case(!strncmp($key, "question_", 9)):
				$question[$q++] = ltrim($value);	
				break;
			case(!strncmp($key, "answer", 6)):
				$value = ltrim($value);
				if(strlen($value) == 1) $value = strtoupper($value);
				$answer[$a++] = $value;
				break;
			case(!strncmp($key, "choice", 6)):
				$choice[$c++] = ltrim($value);
				break;
		}
	}
	/*
	* Check the question_def table's last entry and if this question is the last one in the 
	* 	table don't enter anythign into the database.
	* This is done to ensure the user doesn't hit the refresh button and reenter the same question
	* 	twice.
	*/
	$last_question = "SELECT question FROM question_def WHERE question_seq = (SELECT MAX(question_seq) FROM question_def)";
	$last_question = mssql_query($last_question);
	$last_question = mssql_fetch_array($last_question);
	$last_question = ltrim(@$last_question["question"]);
	if(sizeof(@$last_question) > 0) @$last_question = @$last_question["question"];
	else $last_question = 0;
	$s = $q-1;
	if(strcmp($question[$s], @$last_question) == 0) return 0;
	echo("fix this part, insert_fxns.inc line 54<BR>");
	//INSERT INFORMATION INTO DATABASE
	/*
	* All question types have 1 question and n answers except matching.
	* Matching will have n questions and n answers.
	* If num_questions == 1, all answers will have the same question_seq.
	* If num_questions > 1, each answer will have a different question_seq.
	*/
	if(isset($matching_subject)){
		$matching_subject = nl2br(htmlspecialchars($matching_subject));
		//Insert the question into the question table
		$insert = "INSERT INTO question_def " .
				"(test_seq, type_seq, question_num, question) " .
				"VALUES " . 
				"('$test_seq', '$type_seq', '$question_num', \"$matching_subject\")";
		
		if(!(@mssql_query($insert)))
			echo("error in: <br>$insert<br>");
	}
	if(sizeof($question) == 1 ){
		//Insert the question into the question table
		$insert = "INSERT INTO question_def " .
				"(test_seq, type_seq, question_num, question) " .
				"VALUES " . 
				"('$test_seq', '$type_seq', '$question_num', \"" . nl2br(htmlspecialchars($question[0])) . "\")";
		
		if(!(@mssql_query($insert)))
			echo("error in: <br>$insert<br>");
		//Get the question_seq of the question just entered
		$question_seq = "SELECT MAX(question_seq) AS question_seq FROM question_def";
		$the_num = mssql_query($question_seq);
		$row = mssql_fetch_array($the_num);
		$question_seq = $row["question_seq"];
		
		if(sizeof(@$choice) > 0){
			$choice_seq = "SELECT type_seq FROM question_type_def WHERE type_name = \"choice\"";
			$temp = mssql_query($choice_seq);
			$temp = mssql_fetch_array($temp);
			$type_seq = $temp["type_seq"];
			foreach($choice as $key=>$value){
				$insert = "INSERT INTO question_def " .
						"(test_seq, type_seq, question_num, question) " .
						"VALUES " . 
						"('$test_seq', '$type_seq', '$question_num', \"" . htmlspecialchars($value) . "\")";
				//echo("<BR>" . $insert . "<BR>");
				if(!(@mssql_query($insert)))
					echo("error in: <br>$insert<br>");	
			}
		}
		$type_name = "SELECT type_name FROM question_type_def WHERE type_seq = $type_seq";
		$type_name = mssql_query($type_name);
		$type_name = mssql_fetch_array($type_name);
		$type_name = $type_name["type_name"];
		//Insert the answer for the question just entered.
		if(sizeof(@$answer) > 0){
			foreach($answer as $key =>$value){
				if(($type_name == "true_false") || ($type_name == "multiple_choice") || ($type_name == "matching")){
					$value = strtoupper($value);
				}
				//echo("<BR>strlen($value) = " . strlen($value) . "<BR>");
				if((empty($value)) || strlen($value) == 0) $value="No answer entered";
				$insert = "INSERT INTO question_solution_dtl " .
						"(question_seq, test_seq, solution) " .
						"VALUES " .
						"('$question_seq', '$test_seq', \"" . htmlspecialchars($value) . "\")";
				if(!(@mssql_query($insert)))
					echo("error in: <br>$insert<br>");	
			}
		}
	}
	// Else it is a matching question with multiple questions
	else{
		if(sizeof(@$question) > 0)
		foreach(@$question as $key => $value){
			//Insert the question into the question table
			$insert = "INSERT INTO question_def " .
					"(test_seq, type_seq, question_num, question) " .
					"VALUES " . 
					"('$test_seq', '$type_seq', '$question_num', \"" . htmlspecialchars($value) . "\")";
			if(!(@mssql_query($insert)))
				echo("error in: <br>$insert<br>");
			//Get the question_seq of the question just entered
			$question_seq = "SELECT MAX(question_seq) AS question_seq FROM question_def";
			$the_num = mssql_query($question_seq);
			$row = mssql_fetch_array($the_num);
			$question_seq = $row["question_seq"];
			
			$value = $answer[$key];
			$insert = "INSERT INTO question_solution_dtl " .
					"(question_seq, test_seq, solution) " .
					"VALUES " .
					"('$question_seq', '$test_seq', \"" . htmlspecialchars($value) . "\")";
			if(!(@mssql_query($insert)))
				echo("error in: <br>$insert<br>");	
		}
		$choice_seq = "SELECT type_seq FROM question_type_def WHERE type_name = \"choice\"";
		$temp = mssql_query($choice_seq);
		$temp = mssql_fetch_array($temp);
		$type_seq = $temp["type_seq"];
		if(sizeof(@$choice) > 0)
		foreach(@$choice as $key=>$value){
			$insert = "INSERT INTO question_def " .
					"(test_seq, type_seq, question_num, question) " .
					"VALUES " . 
					"('$test_seq', '$type_seq', '$question_num', \"" . htmlspecialchars($value) . "\")";
			if(!(@mssql_query($insert)))
				echo("error in: <br>$insert<br>");	
		}
	}
}
?>