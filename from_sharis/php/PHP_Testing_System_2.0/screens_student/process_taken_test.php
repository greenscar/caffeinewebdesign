<?php
	$user_type = "student";
	/** 
	* CHECK TO ENSURE A SESSION HAS BEEN STARTED. 
	* IF NO SESSION IS STARTED, THE USER WILL BE FORWARDED TO THE LOGIN SCREEN
	*/
	require_once("../inc_files/check_session.inc");
	//CONNECT TO THE DATABASE
	require_once("../inc_files/sql_connect.inc");
	require_once("email_test_results.php");
	include("../inc_files/display_fxns.inc");
	//view_array($_POST);
	/*
	* When display_test_for_taking.php gives a test and the person clicks the submit 
	* button, it is sent here. process_test_taken.php will take the form values and 
	* place them in the test_taken_dtl and student_answer_dtl.
	*/
	
	//Assign the html form variables into an array.
	$answer_array = assign_post_vars_to_array($_POST);
	
	//Insert the header information into the db (date, test taker, etc)
	insert_into_test_taken_dtl($_POST["test_seq"], $the_user->get_emp_seq());
	
	
	//Update the time the test was completed.
	update_duration($_POST["emp_seq"], $_POST["test_seq"]);
	
	//Insert the student's answers into the database
	insert_into_student_answer_dtl($answer_array, $_POST["emp_seq"], $_POST["test_seq"]);
	
	//Email the student's test to training.
	if(email_results($the_user->get_emp_seq())){
		$take_seq = "SELECT MAX(take_seq) AS take_seq FROM test_taken_dtl " .
					"WHERE emp_seq = " . $_POST["emp_seq"];
		$ts = mssql_query($take_seq);
		$ts = mssql_fetch_array($ts);
		$ts = $ts[0];
		$display = "SELECT display_after FROM test_header_def WHERE test_seq = " .$_POST["test_seq"];
		$display = mssql_query($display);
		$display = mssql_fetch_row($display);
		if($display[0]){
			$title = "Congradulations. You have completed the exam.";
			include("../inc_files/html_header_no_fxns.inc");
			display_taken_test($ts);
			include("../inc_files/html_footer.inc");
		}
		else{
			Header("Location: ../index.php");
		} 
		
	}
	else{
		echo("Error in sending the email. Please contact the Help Desk<BR>");
	}
	/*
	* Insert the test number, employee number, and date taken
	* 	into test_taken_dtl
	*/	
	function insert_into_test_taken_dtl($test_seq, $emp_seq){
		$right_now = date("Y\-m\-d H\:i\:s", time());
		$start_time = $_POST["start_time"];
		$duration = strtotime($right_now)-strtotime($start_time);
		$insert = "INSERT INTO test_taken_dtl " .
				  "(test_seq, emp_seq, date_taken, duration_secs) " .
				  "VALUES " .
				  "(\"$test_seq\", \"$emp_seq\", \"$start_time\", \"$duration\");";
		if(!(@mssql_query($insert))){
			echo("Error in <BR>$insert<BR>");
		}
	}
	function update_duration($emp_seq, $test_seq){
		$take_num = "SELECT MAX(take_seq) AS take_seq FROM test_taken_dtl WHERE emp_seq = $emp_seq AND test_seq = $test_seq";
		$the_num = mssql_query($take_num);
		$row = mssql_fetch_array($the_num);
		$take_seq = $row["take_seq"];		

		$start_time = "SELECT date_taken FROM test_taken_dtl WHERE take_seq = $take_seq";
		$start_time = mssql_query($start_time) or die("No tests have been taken.");
		$st = mssql_fetch_array($start_time);
		$start_time = $st["date_taken"];
		
		$right_now = date("Y\-m\-d H\:i\:s", time());
		$duration = strtotime($right_now)-strtotime($start_time);

		$update = "UPDATE test_taken_dtl SET duration_secs = \"$duration\" WHERE take_seq = $take_seq";
		if(!(@mssql_query($update))){
			echo("Error in <BR>$update<BR>");
		}
	}
	/*
	*  Insert the student's answers into the database.
	*/
	function insert_into_student_answer_dtl($answer_array, $emp_seq, $test_seq){
		//view_array($answer_array);
		//GET take_seq FROM THE test_taken_dtl
		$take_num = "SELECT MAX(take_seq) AS take_seq FROM test_taken_dtl WHERE emp_seq = $emp_seq AND test_seq = $test_seq";
		$the_num = mssql_query($take_num);
		$row = mssql_fetch_array($the_num);
		$take_seq = $row["take_seq"];		
		foreach ($answer_array as $question_seq => $answer){
			if(is_numeric($question_seq)){
				$array_location = 0;
				while(isset($answer[$array_location])){
					$solution = "SELECT solution FROM question_solution_dtl WHERE question_seq = $question_seq";
					$solution = mssql_query($solution);
					$solution = mssql_fetch_array($solution);
					$solution = $solution["solution"];
					$their_answer = htmlspecialchars(ltrim($answer[$array_location]));
					if(strcasecmp($their_answer, $solution) == 0){
						$correct = 1;
					}
					else{
						$correct = 0;
					}
					$insert = "INSERT INTO student_answer_dtl " .
							  "(take_seq, question_seq, answer, correct) " .
							  "VALUES " .
							  "(\"$take_seq\", \"$question_seq\", \"$their_answer\", \"$correct\");";
					$array_location ++;
					if(!(@mssql_query($insert))){
						die("LINE 113 Error in <br>$insert<BR>");
					}
				}
			}
		}//end foreach ($answer_array as $question_seq => $answer)
	}//end function insert_into_student_answer_dtl($max_array_key, $answer_array)
	
	/*
	* assign_post_vars_to_array will go through the _POST array and assign each 
	* 	value to an array based on the question_seq $answers[$question_seq].	
	* In the _POST array, the keys are : answer_<question_seq><_letter of line>
	* 	EX: answer_2, answer_45_A, answer_45_B, etc.
	* 
	*/
	function assign_post_vars_to_array($_POST){
		include("../inc_files/variables.inc");
		$previous_letter = "0";
		$previous_question_seq = 0;
		/*
		* Go through the entire array of POST variables and do the following:
		* 1) If key is a number value, insert value into the answer array
		* 2) If key is not a number, the value is the first answer of a multiple
		* 		answer question. Therefore, it will go into a 2D array.
		* 		As long as the ending characters are not "A" or a number, the same
		* 		question is still being answered
		*/
		foreach ($_POST as $key => $value){
			$length = strlen($key);
			if(!($underscore_location = strpos($key, "_"))){
				$underscore_location = $length;
			} 
			$question_seq = substr($key, 0, $underscore_location);
			//GET THE question_letter FROM THE KEY
			$question_letter = substr($key, $underscore_location+1, $length);
			//LOOK IN THE alphabet ARRAY AND FIND OUT THE INT VALUE OF THE LOCATION FOR THE LETTER
			$alphabet_location = array_search($question_letter, $alphabet);					
			if(!isset($alphabet_location)){
				$alphabet_location = 0;
			}
			//PLACE THE ANSWER INTO THE ARRAY IN THE PROPER LOCATION BASED ON QUESTION NUM AND LETTER
			$answers[$question_seq][$alphabet_location] = $value;
		}
		return $answers;
	}
	/*
	* Because the answers will be placed in the array based on the question number,
	* 	not the next array value, there will be some array locations with null values.
	* 	Therefore, sizeof(ARRAY) won't work. We must find out the largest array key 
	* 	that holds a value.
	*/
	function get_max_array_key($answer_array){
		return max(array_keys($answer_array));
	}

?>
