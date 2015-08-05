<?php
/*
* displayrue_falseB takes the number of fill_in_blank questions requested and displays
* that number of fill_in_blank spaces in the form.
*/
function display_fill_in_blank_form($question_num, $next_num_choices, $next_num_blanks){
	include("../inc_files/variables.inc");
	if($question_num == 0) $question_num = 1;
	echo("\t<TABLE WIDTH=\"$table_width\">\n");
    echo("\t\t<TR>\n");
    echo("\t\t\t<TD WIDTH=\"50\">" . $question_num . ")</TD>\n");
    echo("\t\t\t<TD WIDTH=\"580\"> \n\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp\n");
    echo("\t\t\t\t<INPUT TYPE=\"text\" NAME=\"question_A\" SIZE=\"85\">\n");
    echo("\t\t\t</TD>\n");
    echo("\t\t</TR>\n");
	for($i=0; $i<$next_num_blanks; $i++){
		if($i==0)
			echo("\t\t\t<TD WIDTH=\"50\">Answer:</TD>\n");
		else
			echo("\t\t\t<TD WIDTH=\"50\"></TD>\n");
	    echo("\t\t\t<TD WIDTH=\"580\"> \n\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp\n");
	    echo("\t\t\t\t<INPUT TYPE=\"text\" NAME=\"answer_" . $alphabet[$i] . "\" SIZE=\"20\">\n");
	    echo("\t\t\t</TD>\n");
    	echo("\t\t</TR>\n");
	}
    echo("\t\t<TD COLSPAN=\"2\">&nbsp;</TD>\n");
    echo("\t\t</TR>\n");
	echo("\t</TABLE>\n");	
}

function display_fill_in_blank_for_taking($q_num, $this_question, $questions){
    include("variables.inc");
	$question_seq = $this_question["question_seq"];
	echo("\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\">\n");
    echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\" HALIGN=\"LEFT\">" . $q_num . ")</TD>\n\t\t\t<TD colspan=\"3\">" . stripslashes($this_question["question"]) . "</TD>\n");
	echo("\t\t</TR>"); 
	//CHECK THE question_solution_dtl FOR THE NUMBER OF SOLUTIONS TO THIS QUESTION
    $num = "SELECT solution FROM question_solution_dtl WHERE question_seq = $question_seq";
	$num = mssql_query($num);
	$num = mssql_num_rows($num); 
    for($x = 0; $x < $num; $x++){
		echo("\n\t\t<TR>\n\t\t\t<TD WIDTH=\"3\"></TD>");
		echo("\n\t\t\t<TD VALIGN=\"top\">\n\t\t\t\t<INPUT TYPE=\"text\" NAME=\"" . $this_question["question_seq"] . "_" . $alphabet[$x] . "\" SIZE=\"20\">\n\t\t\t</TD>\n\t\t");
	    echo("</TR>");
  	}
	echo("\n\t</TABLE>\n\t<BR>\n");
}

function display_fill_in_blank_with_solution($this_question, $all_questions, $for_mod, $q_num){
	include("variables.inc");
	$answers = "SELECT * FROM question_solution_dtl WHERE question_seq = " . $this_question["question_seq"];
	$answers = mssql_query($answers);
	$question_phrase = stripslashes($this_question["question"]);
	$question_seq = $this_question["question_seq"];
	$test_seq = $this_question["test_seq"];
	$question_num = $this_question["question_num"];
	echo("\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\" ID=\"a_question\"");
	if($for_mod){
		echo("onMouseOver=\"this.className='red_bold'\" onMouseOut=\"this.className='black'\"");
		echo("onclick=\"get_fib_info('mod_form_$question_seq');\"");
	}
	echo(">\n");
	echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\">" . $q_num . ")</TD>\n\t\t\t");
	echo("<TD colspan=\"3\">");
	echo($question_phrase);
	//echo("<INPUT TYPE=\"TEXT\" size = " . strlen($question_phrase). " NAME=\"question_" . $question_seq . "\"VALUE=\"" . stripslashes($question_phrase) . "\"></TD>\n");
    echo("</TD>\n\t\t</TR>\n"); 
    while($solution = mssql_fetch_array($answers)){
		$solution = stripslashes($solution["solution"]);
		echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\"></TD>\n");
		echo("\t\t\t <TD VALIGN=\"top\">");
		if(empty($for_mod)) echo("<B>");
		echo("<U>&nbsp ");
		echo($solution);
		echo(" &nbsp</U>");
		if(empty($for_mod)) echo("</B>");
		echo("</TD>");
    	//echo("\t\t\t\t<INPUT TYPE=\"text\" NAME=\"solution_" . $solution["solution_seq"] . "\" size = " . (strlen($solution["solution"]) + 2). "  VALUE=\"" . $solution["solution"] . "\">\n");
    	echo("\n\t\t</TR>\n");
	}
	echo("\t</TABLE>\n\t<BR>\n");
}

function display_fill_in_blank_to_mod($test_seq, $question_seq, $num_blanks){
	include("variables.inc");
	
	$question = "SELECT question, question_num FROM question_def WHERE question_seq = $question_seq";
	$question = mssql_query($question);
	$question = mssql_fetch_array($question);
	$this_question = $question["question"];
	$this_question = stripslashes($this_question);
	
	$answers = "SELECT * FROM question_solution_dtl WHERE question_seq = $question_seq";
	$answers = mssql_query($answers);
	
	$str_length = strlen($this_question);
	$num_cols = 100;
	if($str_length < 100) $num_lines = 2;
	else $num_lines = $str_length / $num_cols + 1;
	
	echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"num_blanks\" VALUE=\"$num_blanks\">\n");
	echo("\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\">\n");
	echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\">" . $question["question_num"] . ")</TD>\n");
	echo("\t\t\t<TD colspan=\"3\">\n");
	echo("\t\t\t\t<TEXTAREA NAME=\"question_" . $question_seq . "\" COLS=\"$num_cols\" ROWS=\"$num_lines\" WRAP>");
	echo($this_question);
	echo("</TEXTAREA>\n");
	echo("\t\t\t</TD>\n\t\t</TR>\n"); 
	
	for($i = 0; $i < $num_blanks; $i++){
		$solution = mssql_fetch_array($answers);
		if(empty($solution)){
			$solution["solution"] = "";
			$solution["solution_seq"] = "new_" . $i;
		}
		echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\"></TD>\n");
		echo("\t\t\t<TD VALIGN=\"top\">\n");
		$str_length = strlen($solution["solution"]);
		if($str_length < 100) $num_lines = 1;
		else $num_lines = $str_length / $num_cols + 1;
		echo("\t\t\t\t<TEXTAREA NAME=\"solution_" . $solution["solution_seq"] . "\" COLS=\"$num_cols\" ROWS=\"$num_lines\" WRAP>");
		echo(stripslashes($solution["solution"]));
		echo("</TEXTAREA>\n");
		echo("\t\t\t</TD>\n");
    	echo("\t\t</TR>\n");
	}
	while($solution = mssql_fetch_array($answers)){
		echo("<INPUT TYPE=\"HIDDEN\" NAME=\"solution_" . $solution["solution_seq"] . "\" VALUE=\"DELETE\">\n");
	}
	echo("\t</TABLE>\n\t<BR>\n");
}
function process_fill_in_blank_mod($_POST){
    include("variables.inc");
	$num_blanks = $_POST["num_blanks"];
	$test_seq = $_POST["test_seq"];
	$sn = 0;
	$s = 0;
	foreach ($_POST as $key => $value){ 
		switch($key){
			case(strcmp($key, "question_seq") == 0):
				$qs = $value;
				break;
			case(strcmp($key, "question_num") == 0):
				break;
			case(!strncmp($key, "question", 8)):
				$question = htmlspecialchars(ltrim($value));	
				$temp = ltrim($key);
				$ul = strrpos($temp, "_");
				break;
			case(!strncmp($key, "solution_new", 10)):
				$solution_new[$sn++] = htmlspecialchars(ltrim($value));
				break;
			case(!strncmp($key, "solution", 8)):
				$temp = ltrim($key);
				$ul = strrpos($temp, "_");
				$ss[$s] = substr($temp, ($ul + 1));
				$solution[$s++] = htmlspecialchars(ltrim($value));
				break;
		}
	}
	$update_q = "UPDATE question_def SET question = \"$question\" " .
	               "WHERE question_seq = $qs";
	for($i = 0; $i < sizeof(@$solution); $i++){
		if($solution[$i] == "DELETE"){
			$update_a[$i] = "DELETE from question_solution_dtl " .
							"WHERE solution_seq = " . $ss[$i];
		}
		else{
			$update_a[$i] = "UPDATE question_solution_dtl SET solution = \"" . 
							$solution[$i] . "\" WHERE solution_seq = " . $ss[$i];
		}
	}
	if(!empty($solution_new)){
		for($i = 0; $i < sizeof(@$solution_new); $i++){
			$insert[$i] = "INSERT INTO question_solution_dtl " .
							"(question_seq, test_seq, solution) " .
							"VALUES " .
							"('$qs', '$test_seq', \"" . $solution_new[$i] . "\")";
		}
	}
	
	if(!mssql_query($update_q)) echo("ERROR in: $update_q<BR>");
	for($i=0; $i<sizeof(@$update_a); $i++)
		if(!mssql_query($update_a[$i]))  echo("ERROR in: " . $update_a[$i] . "<BR>");
	
	if(!empty($insert))
		for($i=0; $i<sizeof($insert); $i++)
			if(!mssql_query($insert[$i]))  echo("ERROR in: " . $insert[$i] . "<BR>");
	
	Header("Location: ./menu_admin.php?cat=test&scr=modtest&ts=$test_seq&do=mod");
	
}

function display_fill_in_blank_graded($this_question, $q_num, $take_seq, $all_questions){
	include("variables.inc");
	$answers = "SELECT * FROM question_solution_dtl WHERE question_seq = " . $this_question["question_seq"];
	$answers = mssql_query($answers);
	$question_phrase = stripslashes($this_question["question"]);
	$question_seq = $this_question["question_seq"];
	$answer = "SELECT answer, correct FROM student_answer_dtl WHERE " .
				"take_seq = $take_seq AND " .
				"question_seq = " . $this_question["question_seq"];
	$answer_query = mssql_query($answer);
	echo("\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\">\n");
	echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\">" . $q_num . ")</TD>\n\t\t\t");
	echo("<TD colspan=\"3\" WIDTH=\"500\">");
	echo($question_phrase);
    echo("</TD>\n\t\t</TR>\n"); 
    while(($solution = mssql_fetch_array($answers)) && ($answer = mssql_fetch_array($answer_query))){
		$emp_answer = $answer["answer"];
		$correct = $answer["correct"];
		$solution = $solution["solution"];
		$solution = stripslashes($solution);
		$emp_answer = stripslashes($emp_answer);
		echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\" VALIGN=\"TOP\">&nbsp</TD>\n");
		echo("\t\t\t<TD VALIGN=\"top\"200>\n");
		echo("\t\t\t\t<U><B>&nbsp;" . $emp_answer . "&nbsp</B></U>");
		echo("</TD>\n");
		echo("\t\t\t<TD VALIGN=\"TOP\" WIDTH=\"200\">\n");
		echo("\t\t\t\t<P STYLE=\"color:red;font-weight:900\">$solution</P>\n");
		echo("\t\t\t</TD>\n");
		//echo("\t\t\t\t<INPUT TYPE=\"text\" NAME=\"solution_" . $solution["solution_seq"] . "\" size = " . (strlen($solution["solution"]) + 2). "  VALUE=\"" . $solution["solution"] . "\">\n");
    	echo("\n\t\t</TR>\n");
	}
	echo("\t</TABLE>\n\t<BR>\n");
}


function add_fill_in_blank_to_email($this_question, $take_seq, $all_questions){
	include("variables.inc");
	$answers = "SELECT * FROM question_solution_dtl WHERE question_seq = " . $this_question["question_seq"];
	$answers = mssql_query($answers);
	$question_phrase = stripslashes($this_question["question"]);
	$question_seq = $this_question["question_seq"];
	$question_num = $this_question["question_num"];
	$answer = "SELECT answer, correct FROM student_answer_dtl WHERE " .
				"take_seq = $take_seq AND " .
				"question_seq = " . $this_question["question_seq"];
	$answer_query = mssql_query($answer);
	$email_body = "\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\">\n";
	$email_body .= "\t\t<TR>\n\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\">" . $question_num . ")</TD>\n\t\t\t";
	$email_body .= "<TD colspan=\"2\">$question_phrase</TD>\n\t\t</TR>\n"; 
    while(($solution = mssql_fetch_array($answers)) && ($answer = mssql_fetch_array($answer_query))){
		$emp_answer = $answer["answer"];
		$correct = $answer["correct"];
		$solution = $solution["solution"];
		$solution = stripslashes($solution);
		$emp_answer = stripslashes($emp_answer);
		$email_body .= "\t\t<TR>\n\t\t\t<TD WIDTH=\"3\" VALIGN=\"TOP\">&nbsp</TD>\n";
		$email_body .= "\t\t\t<TD VALIGN=\"top\">\n";
		$email_body .= "\t\t\t\t<U><B>&nbsp;" . $emp_answer . "&nbsp</B></U>";
		$email_body .= "</TD>\n";
		$email_body .= "\t\t\t<TD VALIGN=\"TOP\" WIDTH=\"60%\">\n";
		$email_body .= "\t\t\t\t<P STYLE=\"color:red;font-weight:900\">$solution</P>\n";
		$email_body .= "\t\t\t</TD>\n";
		//$email_body .= "\t\t\t\t<INPUT TYPE=\"text\" NAME=\"solution_" . $solution["solution_seq"] . "\" size = " . (strlen($solution["solution"]) + 2). "  VALUE=\"" . $solution["solution"] . "\">\n";
    	$email_body .= "\n\t\t</TR>\n";
	}
	$email_body .= "\t</TABLE>\n\t<BR>\n";
	return $email_body;
}

?>