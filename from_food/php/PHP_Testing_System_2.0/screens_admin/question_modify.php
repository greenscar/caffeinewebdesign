<?php
$user_type = "admin";
require_once("../inc_files/check_session.inc");
require_once("../inc_files/question_fxns.inc");
require_once("../inc_files/display_fxns.inc");

//THE USER HAS CHOSEN TO INSERT A NEW QUESTION OR HEADER.
if(isset($_GET["ts"]) && isset($_GET["qn"]) && ($_GET["do"] != "del")){
	/*
	* THE USER HAS CHOSEN TO INSERT A NEW QUESTION OR HEADER BEFORE question_num = $qn.
	* DISPLAY THE QUESTION TYPE MENU 
	* CHANGE THE question_num OF EVERY ENTRY WHOSE question_num > $qn TO question_num + 1.
	*/
	$ts = $_GET["ts"];
	$qn = $_GET["qn"];
	echo("<FORM NAME=\"form1\" method=\"POST\" action=\"" . $_SERVER['PHP_SELF'] . "?scr=modquest&cat=test\">\n");
	echo("<INPUT TYPE=\"HIDDEN\" NAME=\"question_num\" VALUE=\"$qn\">\n");
	echo("<INPUT TYPE=\"HIDDEN\" NAME=\"test_seq\" VALUE=\"$ts\">\n");
	echo("<INPUT TYPE=\"HIDDEN\" NAME=\"num_blanks\" VALUE=\"" . @$_POST["next_num_blanks"] . "\">\n");
	echo("<INPUT TYPE=\"HIDDEN\" NAME=\"num_choices\" VALUE=\"" . @$_POST["next_num_choices"] . "\">\n");
	echo("<INPUT TYPE=\"HIDDEN\" NAME=\"to_do\" VALUE=\"show_question_form\">\n");
	display_question_type_menu();
	echo("</FORM>\n");
	echo("\t<P ALIGN=\"CENTER\"><BUTTON onClick=\"location.href='menu_admin.php?cat=test&scr=modtest&ts=$ts&do=mod'\" NAME=\"cancel\">Cancel</BUTTON>\n");
}
//DISPLAY FORM FOR A NEW QUESTION TO BE ENTERED
else if(@$_POST["to_do"] == "show_question_form"){
	$nnc = $_POST["next_num_choices"];
	$nnb = $_POST["next_num_blanks"];
	$qn = $_POST["question_num"];
	$nqts = $_POST["next_question_type_seq"];
	$ts = $_POST["test_seq"];
	$qts = "SELECT * FROM question_type_def WHERE type_seq = $nqts";
	$qtn = mssql_query($qts) or die("Error in Query: $question_type_string. mssql said " . mssql_error() . '.');  //select the username 
	$qtn = mssql_fetch_array($qtn);
	$qtn = $qtn["type_name"];
	
	echo("<FORM NAME=\"form1\" method=\"POST\" action=\"question_modify.php\">\n");
	display_question_form($nqts, $qn, $nnc, $nnb);
	echo("<INPUT TYPE=\"hidden\" NAME=\"ts\" VALUE=\"$ts\">\n");
	echo("<INPUT TYPE=\"hidden\" NAME=\"qn\" VALUE=\"$qn\">\n");
	echo("<INPUT TYPE=\"hidden\" NAME=\"type_seq\" VALUE=\"$nqts\">\n");
	echo("<INPUT TYPE=\"hidden\" NAME=\"type_name\" VALUE=\"$qtn\">\n");
	echo("<INPUT TYPE=\"HIDDEN\" NAME=\"num_blanks\" VALUE=\"" . @$_POST["next_num_blanks"] . "\">\n");
	echo("<INPUT TYPE=\"HIDDEN\" NAME=\"num_choices\" VALUE=\"" . @$_POST["next_num_choices"] . "\">\n");
	echo("<INPUT TYPE=\"hidden\" NAME=\"to_do\" VALUE=\"insert_question\">\n");
	echo("\t<P ALIGN=\"CENTER\"><BUTTON onClick=\"submit();\" NAME=\"cancel\">Cancel</BUTTON>\n");
	echo("&nbsp&nbsp<INPUT TYPE=\"SUBMIT\" NAME=\"Submit\" VALUE=\"Submit\"></P>\n");
	echo("</FORM>\n");
}

//PROCESS THE NEW QUESTION ENTERED
else if(@$_POST["to_do"] == "insert_question"){
	$ts = $_POST["ts"];
	$qn = $_POST["qn"];
	//UPDATE ALL THE QUESTION NUMBERS BEYOND THE NUMBER OF THIS QUESTION.
	if(!empty($_POST["question_A"])){
		$renumber = "UPDATE question_def SET question_num = question_num + 1 " .
				"WHERE test_seq = $ts AND question_num >= $qn";
		$response = mssql_query($renumber) or die("error in $renumber<BR>");
	}
	insert_question_into_db($_POST);
	Header("Location: ./menu_admin.php?cat=test&scr=modtest&ts=$ts&do=mod");
	
}
//A QUESTION HAS BEEN MODIFIED AND NEEDS PROCESSED.
else if(!empty($_POST["mod_entered"])){
	$what_to_mod = $_POST["type_name"];
	$fxn = "process_" . $what_to_mod . "_mod";
	call_user_func($fxn, $_POST);
}
else{ 
	//USER HAS SELECTED A QUESTION TO MODIFY.
	//DISPLAY THE QUESTION TO BE MODIFIED.
	$q_num = $_GET["q_num"];
	$q_num = $_POST["question_num"];
	$q_seq = $_POST["question_seq"];
	$test_seq = $_POST["test_seq"];
	$type_name = $_POST["type_name"];
	$num_blanks = @$_POST["num_blanks"];
	$num_choices = @$_POST["num_choices"];
	$type_seq = $_POST["type_seq"];
	//SUBMIT THIS FORM TO SELF SO IT CAN BE PROCESSED AND THE USER FORWARDED TO THE TEST
	echo("\t<FORM NAME=\"question_mod_form\" method=\"POST\" action=\"question_modify.php?scr=modquest&cat=test\">\n");
	$fxn = "display_" . $type_name . "_to_mod";
	call_user_func($fxn, $test_seq, @$_POST["question_seq"], @$num_blanks, @$num_choices);
	echo("\t<DIV ALIGN=\"CENTER\">\n\t\t<INPUT TYPE=\"SUBMIT\" VALUE=\"Submit Changes\">\n");
	echo("\t\t&nbsp&nbsp&nbsp&nbsp\n\t\t<INPUT TYPE=\"BUTTON\" ONCLICK=\"location.href='./menu_admin.php?cat=test&scr=modtest&ts=$test_seq&qs=$q_seq&do=mod'\" VALUE=\"Cancel\">\n");

	if(strcmp($type_name, "title") != 0){
		echo("\t\t&nbsp&nbsp&nbsp&nbsp\n\t\t<INPUT TYPE=\"BUTTON\" ONCLICK=\"verifyDelete();\" VALUE=\"Delete Question\">\n\t</DIV>\n");
	}
	echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"question_seq\" VALUE=\"$q_seq\">\n");
	echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"question_num\" VALUE=\"$q_num\">\n");
	echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"test_seq\" VALUE=\"$test_seq\">\n");
	echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"mod_entered\" VALUE=\"true\">\n");
	echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"type_seq\" VALUE=\"$type_seq\">\n");
	echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"type_name\" VALUE=\"$type_name\">\n");
	echo("\t</FORM>\n");
	echo("</BODY>\n</HTML>");
}

function insert_question_into_db($_POST){
	require_once("../inc_files/some_useful_functions.inc");
	$type_seq = $_POST["type_seq"];
	//INSERT INFORMATION INTO VARIABLES.
	$description = @$_POST["description"];
	$num_blanks = $_POST["num_blanks"];
	$num_choices = $_POST["num_choices"];
	$question_num = $_POST["qn"];
	$test_seq = $_POST["ts"];
	$t_seq = "SELECT type_seq FROM question_type_def WHERE type_name = \"matching\" or type_name = \"word_list\"";
	$t_seq = mssql_query($t_seq);
	$mat = mssql_fetch_array($t_seq);
	$matching_seq = $mat["type_seq"];
	$word = mssql_fetch_array($t_seq);
	$word_list_seq = $word["type_seq"];
	if(($_POST["type_seq"] == $matching_seq) || ($_POST["type_seq"] == $word_list_seq)){
		$subject = @$_POST["subject"];
	}
	$q = 0;
	$a = 0;
	$c = 0;
	foreach ($_POST as $key => $value){ 
		switch($key){
			case(!strncmp($key, "question_", 9)):
				$question[$q++] = ltrim($value);	
				break;
			case(!strncmp($key, "answer", 6)):
				$answer[$a++] = ltrim($value);
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
	$last_question = ltrim($last_question["question"]);
	if(sizeof($last_question) > 0) $last_question = @$last_question["question"];
	else $last_question = 0;
	$s = $q-1;
	if(strcmp($question[$s], @$last_question) == 0) return 0;
	
	//INSERT INFORMATION INTO DATABASE
	/*
	* All question types have 1 question and n answers except matching.
	* Matching will have n questions and n answers.
	* If num_questions == 1, all answers will have the same question_seq.
	* If num_questions > 1, each answer will have a different question_seq.
	*/
	if(isset($subject)){
		//Insert the question into the question table
		$insert = "INSERT INTO question_def " .
				"(test_seq, type_seq, question_num, question) " .
				"VALUES " . 
				"('$test_seq', '$type_seq', '$question_num', \"$subject\")";
		
		if(!(@mssql_query($insert)))
			echo("error in: <br>$insert<br>");
	}
	if(sizeof($question) == 1 ){
		//Insert the question into the question table
		$insert = "INSERT INTO question_def " .
				"(test_seq, type_seq, question_num, question) " .
				"VALUES " . 
				"('$test_seq', '$type_seq', '$question_num', \"" . $question[0] . "\")";
		
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
						"('$test_seq', '$type_seq', '$question_num', \"$value\")";
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
						"('$question_seq', '$test_seq', \"$value\")";
				if(!(@mssql_query($insert)))
					echo("error in: <br>$insert<br>");	
			}
		}
	}
	// Else it is a matching question with multiple questions
	else{
		foreach($question as $key => $value){
			//Insert the question into the question table
			$insert = "INSERT INTO question_def " .
					"(test_seq, type_seq, question_num, question) " .
					"VALUES " . 
					"('$test_seq', '$type_seq', '$question_num', \"$value\")";
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
					"('$question_seq', '$test_seq', \"$value\")";
			if(!(@mssql_query($insert)))
				echo("error in: <br>$insert<br>");	
		}
		$choice_seq = "SELECT type_seq FROM question_type_def WHERE type_name = \"choice\"";
		$temp = mssql_query($choice_seq);
		$temp = mssql_fetch_array($temp);
		$type_seq = $temp["type_seq"];
		foreach($choice as $key=>$value){
			$insert = "INSERT INTO question_def " .
					"(test_seq, type_seq, question_num, question) " .
					"VALUES " . 
					"('$test_seq', '$type_seq', '$question_num', \"$value\")";
			if(!(@mssql_query($insert)))
				echo("error in: <br>$insert<br>");	
		}
	}
}
?>