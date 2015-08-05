<?php
function display_matching_form($question_num, $next_num_choices, $next_num_blanks){
	include("../inc_files/variables.inc");
	echo("\t<TABLE WIDTH=\"$table_width\" border=\"0\">\n");
	echo("\t\t<TR>\n\t\t\t");
	echo("</TR>\n\t\t");
	echo("<TR>\n\t\t\t");
	if($question_num == 0) $question_num = 1;
	echo("<TD WIDTH=\"3\" VALIGN=\"top\">" . $question_num . ")</TD>\n\t\t\t");
	echo("<TD COLSPAN=\"2\">\n\t\t\t\t");
	echo("<INPUT TYPE=\"TEXT\" SIZE=\"90\" NAME=\"subject\">");
	echo("</TD>\n\t\t");	
	echo("</TR>");
	for($q = 1; $q <= $next_num_blanks || $q <= $next_num_choices; $q++){
		$letter = $q-1;
		echo("\n\t\t<TR>\n\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\">\n\t\t\t\t");
		if($next_num_blanks >= $q)
			echo("<INPUT TYPE=\"TEXT\" SIZE=\"1\" NAME=\"answer_" . $alphabet[$letter] . "\">");
		echo("\n\t\t\t</TD>\n\t\t\t<TD VALIGN=\"TOP\" HALIGN=\"LEFT\">");
		if($next_num_blanks >= $q)
			echo("\n\t\t\t\t<INPUT TYPE=\"TEXT\" NAME=\"question_" . $alphabet[$letter] . "\" SIZE=\"50%\">\n\t\t\t");
		echo("</TD>\n\t\t\t");
		echo("<TD ALIGN=\"RIGHT\">\n\t\t\t\t");
		if($next_num_choices >= $q){
			echo($alphabet[$letter] . ") ");
			echo("<INPUT TYPE=\"TEXT\" NAME=\"choice_" . $alphabet[$letter] . "\">");
		}
		echo("\n\t\t\t</TD>\n\t\t</TR>\n");
		
	}		
	echo("\t</TABLE>\n\t\t<BR>\n");
}
function display_matching_for_taking($q_num, $this_question, $all_questions){
    include("variables.inc");
	$question[0] = 0;
	$choice[0] = 0;
	$question_seq[0] = 0;
	$qn = 0;
	$an = 0;
	$question_num = 0;
	$test_seq = $this_question["test_seq"];
	$description = $this_question["question"];
	//GET ALL QUESTIONS FROM THE DATABASE
	$num_rows = "SELECT * FROM question_def, question_type_def WHERE " .
				"question_type_def.type_name = \"matching\" AND " .
				"question_type_def.type_seq = question_def.type_seq AND " .
				"question_def.test_seq = $test_seq AND " .
				"question_def.question_num = \"" . $this_question["question_num"] . "\" " .
				"ORDER BY question_def.question_seq;";
	$temp = mssql_query($num_rows);
	$num_rows = (mssql_num_rows($temp));
	$question_seq[0] = $this_question["question_seq"];
	$answers = "SELECT solution FROM question_solution_dtl WHERE question_seq = " . $question_seq[$qn];
	$answers = mssql_query($answers);
	$answers = mssql_fetch_array($answers);
	$solution[0] = $answers["solution"];
	$question[0] = $this_question["question"];
	$question_num = $q_num;
	for($qn = 1; $qn < $num_rows; $qn++){
		$this_question = mssql_fetch_array($all_questions);
		$question_seq[$qn] = $this_question["question_seq"];
		$question[$qn] = $this_question["question"];
		
		$answers = "SELECT solution FROM question_solution_dtl WHERE question_seq = " . $this_question["question_seq"];
		$answers = mssql_query($answers);
		$answers = mssql_fetch_array($answers);
		
		//$solution[$qn] = $answers["solution"];
	}
	//GET ALL CHOICES FROM THE DATABASE
	$choices = "SELECT * FROM question_def, question_type_def " .
			   "WHERE " . 
			   "question_def.type_seq = question_type_def.type_seq " .
			   "AND question_type_def.type_name = \"choice\" " . 
			   "AND question_def.test_seq = $test_seq " . 
			   "AND question_def.question_num = " . $this_question["question_num"]  .
			   "ORDER BY question_def.question_seq;";
	$choices = mssql_query($choices);
	while($a_choice = mssql_fetch_array($choices)){
		$choice[$an++] = $a_choice["question"];
	}
				
	//DISPLAY ALL QUESTIONS AND CHOCIES IN TABLE FORM.
	echo("\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\">\n");
	echo("\t\t<TR>\n\t\t\t");
	echo("<TD WIDTH=\"3\" VALIGN=\"top\" HALIGN=\"LEFT\">$question_num)</TD>\n\t\t\t");
	echo("<TD colspan=\"3\" HALIGN=\"LEFT\">" . stripslashes($description) . "</TD>\n");
	echo("\t\t</TR>\n"); 
	for($x=1, $y=0; ($x < sizeof($question) || $y < sizeof($choice)); $x++, $y++){
	//echo("<TR><TD>" . $k . "</TD><TD>" . stripslashes($question[$k]) . "</TD></TR>\n");
		echo("\t\t<TR>\n\t\t\t");
		echo("<TD WIDTH=\"3%\" VALIGN=\"top\">&nbsp</TD>\n\t\t\t");
		echo("<TD WIDTH=\"4%\" ALIGN=\"LEFT\">\n\t\t\t\t");
		if(isset($question_seq[$x]))
			echo("<INPUT TYPE = \"TEXT\" NAME=\"" . $question_seq[$x] . "\" SIZE=\"2\">\n\t\t\t");
		else
			echo("&nbsp");
		echo("</TD>\n\t\t\t");
		echo("<TD WIDTH=\"48%\" ALIGN=\"LEFT\">\n\t\t\t\t" . stripslashes(@$question[$x]) . "\n\t\t\t</TD>\n\t\t\t");
		echo("<TD WIDTH=\"45%\">\n\t\t\t\t");
		if(isset($choice[$y]))
			echo($alphabet[$y] . ") " . @$choice[$y]);
		else
			echo("&nbsp");
		echo("\n\t\t\t</TD>\n\t\t</TR>\n\t\t\t");

	}		
	echo("\t</TABLE>\n\t<BR>\n");
}

function add_matching_to_email($this_question, $take_seq, $all_questions){
	include("variables.inc");
	//include("../inc_files/some_useful_functions.inc";
	$email_body = "\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\">\n";
	$question[0] = 0;
	$choice[0] = 0;
	$question_seq[0] = 0;
	$answer[0] = 0;
	$correct[0] = 0;
	$qn = 0;
	$an = 0;
	$question_num = 0;
	$test_seq = $this_question["test_seq"];
	$description = $this_question["question"];
	//GET ALL QUESTIONS FROM THE DATABASE
	$num_rows = "SELECT * FROM question_def, question_type_def WHERE " .
				"question_type_def.type_name = \"matching\" AND " .
				"question_type_def.type_seq = question_def.type_seq AND " .
				"question_def.test_seq = $test_seq AND " .
				"question_def.question_num = \"" . $this_question["question_num"] . "\" " .
				"ORDER BY question_def.question_seq;";
	$temp = mssql_query($num_rows);
	$num_rows = (mssql_num_rows($temp));
	$question_seq[0] = $this_question["question_seq"];
	$answers = "SELECT solution FROM question_solution_dtl WHERE question_seq = " . $question_seq[$qn];
	$answers = mssql_query($answers);
	$answers = mssql_fetch_array($answers);
	$solution[0] = $answers["solution"];
	$question[0] = $this_question["question"];
	for($qn = 1; $qn < $num_rows; $qn++){
		$this_question = mssql_fetch_array($all_questions);
		$question_num = $this_question["question_num"];
		$question_seq[$qn] = $this_question["question_seq"];
		$question[$qn] = stripslashes($this_question["question"]);
		
		$answers = "SELECT solution_seq, solution FROM question_solution_dtl WHERE question_seq = " . $question_seq[$qn];
		$answers = mssql_query($answers);
		$answers = mssql_fetch_array($answers);
		$solution[$qn] = strtoupper($answers["solution"]);
		$solution_seq[$qn] = $answers["solution_seq"];
		
		$emp_answer = "SELECT answer, correct FROM student_answer_dtl WHERE " .
				"take_seq = $take_seq AND " .
				"question_seq = " . $this_question["question_seq"];
		$emp_answer = mssql_query($emp_answer);
		$emp_answer = mssql_fetch_array($emp_answer);
		$answer[$qn] = strtoupper($emp_answer["answer"]);
		$correct[$qn] = $emp_answer["correct"];
	}
	//GET ALL CHOICES FROM THE DATABASE
	$choices = "SELECT * FROM question_def, question_type_def " .
			   "WHERE " . 
			   "question_def.type_seq = question_type_def.type_seq " .
			   "AND question_type_def.type_name = \"choice\" " . 
			   "AND question_def.test_seq = $test_seq " . 
			   "AND question_def.question_num = " . $this_question["question_num"] . 
			   "ORDER BY question_def.question_seq";
	$choices = mssql_query($choices);
	while($a_choice = mssql_fetch_array($choices)){
		$choice[$an++] = stripslashes($a_choice["question"]);
	}
	
	//DISPLAY ALL QUESTIONS AND CHOICES IN TABLE FORM.
	$email_body .="\t\t<TR>\n\t\t\t";
	$email_body .="<TD WIDTH=\"3\" VALIGN=\"top\">$question_num)</TD>\n\t\t\t";
	$email_body .="<TD colspan=\"3\">". stripslashes($description) . "</TD>\n";
	$email_body .="\t\t</TR>\n"; 
	for($x=1, $y=0; ($x < sizeof($question) || $y < sizeof($choice)); $x++, $y++){
		$email_body .="\t\t<TR>\n\t\t\t";
		$email_body .="<TD WIDTH=3% HALIGN=\"LEFT\">\n\t\t\t\t";
		if($x < sizeof($answer))
			$email_body .="<B><U>&nbsp;" . $answer[$x] . "&nbsp</U></B>\n\t\t\t";
		$email_body .="</TD>\n\t\t\t";
		$email_body .="<TD WIDTH=\"47%\" HALIGN=\"LEFT\">\n\t\t\t\t";
		if($x < sizeof($answer)) $email_body .=@$question[$x];
		if(!@$correct[$x]) $email_body .="&nbsp;&nbsp;<FONT STYLE=\"color:red;font-weight:900\">" .@$solution[$x] . "</FONT>";
		else $email_body .="&nbsp;&nbsp;<FONT STYLE=\"color:green;font-weight:900\">" .@$solution[$x] . "</FONT>";
		$email_body .="\n\t\t\t</TD>\n\t\t\t";
		$email_body .="<TD WIDTH=\"47%\" HALIGN=\"LEFT\">\n\t\t\t\t";
		if(isset($choice[$y]))
			$email_body .=$alphabet[$y] . ") " . @$choice[$y];
		$email_body .="\n\t\t\t</TD>\n\t\t</TR>\n";

	}		
	$email_body .="\t</TABLE>\n\t<BR>\n";
	return $email_body;
	//echo("<h4 align=\"center\">" . $this_question["question"] . "</h4>\n");
}


function display_matching_graded($this_question, $q_num, $take_seq, $all_questions){
	include("variables.inc");
	//include("../inc_files/some_useful_functions.inc");
	echo("\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\">\n");
	$question[0] = 0;
	$choice[0] = 0;
	$question_seq[0] = 0;
	$answer[0] = 0;
	$correct[0] = 0;
	$qn = 0;
	$an = 0;
	$question_num = 0;
	$test_seq = $this_question["test_seq"];
	$description = $this_question["question"];
	//GET ALL QUESTIONS FROM THE DATABASE
	$num_rows = "SELECT * FROM question_def, question_type_def WHERE " .
				"question_type_def.type_name = \"matching\" AND " .
				"question_type_def.type_seq = question_def.type_seq AND " .
				"question_def.test_seq = $test_seq AND " .
				"question_def.question_num = \"" . $this_question["question_num"] . "\" " .
				"ORDER BY question_def.question_seq;";
	$temp = mssql_query($num_rows);
	$num_rows = (mssql_num_rows($temp));
	$question_seq[0] = $this_question["question_seq"];
	$answers = "SELECT solution FROM question_solution_dtl WHERE question_seq = " . $question_seq[$qn];
	$answers = mssql_query($answers);
	$answers = mssql_fetch_array($answers);
	$solution[0] = $answers["solution"];
	$question[0] = $this_question["question"];
	for($qn = 1; $qn < $num_rows; $qn++){
		$this_question = mssql_fetch_array($all_questions);
		$question_num = $this_question["question_num"];
		$question_seq[$qn] = $this_question["question_seq"];
		$question[$qn] = stripslashes($this_question["question"]);
		
		$answers = "SELECT solution_seq, solution FROM question_solution_dtl WHERE question_seq = " . $question_seq[$qn];
		$answers = mssql_query($answers);
		$answers = mssql_fetch_array($answers);
		$solution[$qn] = $answers["solution"];
		$solution_seq[$qn] = $answers["solution_seq"];
		
		$emp_answer = "SELECT answer, correct FROM student_answer_dtl WHERE " .
				"take_seq = $take_seq AND " .
				"question_seq = " . $this_question["question_seq"];
		$emp_answer = mssql_query($emp_answer);
		$emp_answer = mssql_fetch_array($emp_answer);
		$answer[$qn] = $emp_answer["answer"];
		$correct[$qn] = $emp_answer["correct"];
	}
	//GET ALL CHOICES FROM THE DATABASE
	$choices = "SELECT * FROM question_def, question_type_def " .
			   "WHERE " . 
			   "question_def.type_seq = question_type_def.type_seq " .
			   "AND question_type_def.type_name = \"choice\" " . 
			   "AND question_def.test_seq = $test_seq " . 
			   "AND question_def.question_num = " . $this_question["question_num"] . 
			   "ORDER BY question_def.question_seq";
	$choices = mssql_query($choices);
	while($a_choice = mssql_fetch_array($choices)){
		$choice[$an++] = stripslashes($a_choice["question"]);
	}
	
	//DISPLAY ALL QUESTIONS AND CHOICES IN TABLE FORM.
	echo("\t\t<TR>\n\t\t\t");
	echo("<TD WIDTH=\"3\" VALIGN=\"top\">$q_num)</TD>\n\t\t\t");
	echo("<TD colspan=\"3\">". stripslashes($description) . "</TD>\n");
	echo("\t\t</TR>\n"); 
	for($x=1, $y=0; ($x < sizeof($question) || $y < sizeof($choice)); $x++, $y++){
		echo("\t\t<TR>\n\t\t\t");
		echo("<TD WIDTH=\"3%\" VALIGN=\"TOP\" HALIGN=\"RIGHT\">\n\t\t\t\t");
		if(($x < sizeof($answer)) && isset($answer[$x])){
			echo("<IMG SRC=\"");
			if($correct[$x]) echo($GLOBALS["check_mark"]);
			else echo($GLOBALS["x_mark"]);
			echo("\">\n\t\t\t");
		}
		echo("</TD>\n\t\t\t");
		echo("<TD WIDTH=3% HALIGN=\"LEFT\">\n\t\t\t\t");
		if($x < sizeof($answer))
			echo("<B><U>&nbsp;" . $answer[$x] . "&nbsp</U></B>\n\t\t\t");
		echo("</TD>\n\t\t\t");
		echo("<TD WIDTH=\"60%\" HALIGN=\"LEFT\">\n\t\t\t\t");
		if($x < sizeof($answer))
		echo(@$question[$x]);
		if(!@$correct[$x]) echo("&nbsp;&nbsp;<FONT STYLE=\"color:red;font-weight:900\">" .strtoupper(@$solution[$x]) . "</FONT>");
		echo("\n\t\t\t</TD>\n\t\t\t");
		echo("<TD WIDTH=\"34%\" HALIGN=\"LEFT\">\n\t\t\t\t");
		if(isset($choice[$y]))
			echo($alphabet[$y] . ") " . @$choice[$y]);
		echo("\n\t\t\t</TD>\n\t\t</TR>\n");

	}		
	echo("\t</TABLE>\n\t<BR>\n");
	//echo("<h4 align=\"center\">" . $this_question["question"] . "</h4>\n";
}
function display_matching_with_solution($this_question, $all_questions, $for_mod, $q_num){
	include("variables.inc");
	//include("../inc_files/some_useful_functions.inc");
	$question[0] = 0;
	$choice[0] = 0;
	$question_seq[0] = 0;
	$qn = 0;
	$an = 0;
	$question_num = 0;
	$test_seq = $this_question["test_seq"];
	$description = $this_question["question"];
	//GET ALL QUESTIONS FROM THE DATABASE
	$num_rows = "SELECT * FROM question_def, question_type_def WHERE " .
				"question_type_def.type_name = \"matching\" AND " .
				"question_type_def.type_seq = question_def.type_seq AND " .
				"question_def.test_seq = " . $this_question["test_seq"] . " AND " .
				"question_def.question_num = \"" . $this_question["question_num"] . "\" " .
				"ORDER BY question_def.question_seq";
	$temp = mssql_query($num_rows);
	$num_rows = (mssql_num_rows($temp));
	$question_seq[0] = $this_question["question_seq"];
	$answers = "SELECT solution FROM question_solution_dtl " .
				"WHERE question_seq = " . $question_seq[$qn];
	$answers = mssql_query($answers);
	$answers = mssql_fetch_array($answers);
	$solution[0] = $answers["solution"];
	$question[0] = $this_question["question"];
	$question_num = $q_num;
	for($qn = 1; $qn < $num_rows; $qn++){
		$this_question = mssql_fetch_array($all_questions);
		//$question_num = $this_question["question_num"];
		$question_seq[$qn] = $this_question["question_seq"];
		$question[$qn] = $this_question["question"];
		//if()
		$answers = "SELECT solution FROM question_solution_dtl WHERE question_seq = " . $question_seq[$qn];
		$answers = mssql_query($answers) or die("error in: <B>$answers<B><BR>");
		$answers = mssql_fetch_array($answers);

		$solution[$qn] = $answers["solution"];
	}
	//GET ALL CHOICES FROM THE DATABASE
	$choices = "SELECT * FROM question_def, question_type_def " .
			   "WHERE " . 
			   "question_def.type_seq = question_type_def.type_seq " .
			   "AND question_type_def.type_name = \"choice\" " . 
			   "AND question_def.test_seq = $test_seq " . 
			   "AND question_def.question_num = " . $this_question["question_num"] .
			   "ORDER BY question_def.question_seq;";
	$choices = mssql_query($choices);
	while($a_choice = mssql_fetch_array($choices)){
		$choice[$an++] = stripslashes($a_choice["question"]);
	}
	//DISPLAY ALL QUESTIONS AND CHOCIES IN TABLE FORM.
	echo("\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\" ID=\"a_question\" ");
	if($for_mod){
		echo("onMouseOver=\"this.className='red_bold'\" onMouseOut=\"this.className='black'\" ");
		echo("onclick=\"get_matching_info('mod_form_" . $question_seq[0] . "');\"");
	}
	echo(">\n");
	echo("\t\t<TR>\n\t\t\t");
	echo("<TD WIDTH=\"3\" VALIGN=\"top\">$question_num)</TD>\n\t\t\t");
	echo("<TD colspan=\"3\">" . stripslashes($description) . "</TD>\n");
	echo("\t\t</TR>\n"); 
	for($x=1, $y=0; ($x < sizeof($question) || $y < sizeof($choice)); $x++, $y++){
		echo("\t\t<TR>\n\t\t\t");
		echo("<TD WIDTH=\"2%\" VALIGN=\"top\">&nbsp</TD>\n\t\t\t");
		echo("<TD WIDTH=\"5%\" HALIGN=\"LEFT\">\n\t\t\t\t");
		if($x < sizeof($solution) && isset($solution[$x])){
			if(empty($for_mod)) echo("<B>");
			echo("<U>&nbsp");
			echo(strtoupper(stripslashes($solution[$x])));
			echo(" &nbsp</U>");
			if(empty($for_mod)) echo("</B>");
		}
		//echo("<B><U>&nbsp;" . $solution[$x] . "&nbsp</U></B>");
		echo("\n\t\t\t</TD>\n\t\t\t");
		echo("<TD WIDTH=\"60%\">\n\t\t\t\t");
		if($x < sizeof($question) && !empty($question[$x]))
			echo(stripslashes($question[$x]));
		echo("\n\t\t\t</TD>\n\t\t");
		echo("<TD WIDTH=\"34%\">\n\t\t\t\t");
		if(isset($choice[$y]))
			echo($alphabet[$y] . ") " . @$choice[$y]);
		echo("\n\t\t\t</TD>\n\t\t</TR>\n\t\t\t");

	}		
	echo("\t</TABLE>\n\t<BR>\n");
}
function display_matching_to_mod($test_seq, $qs, $num_blanks, $num_choices){
	include("variables.inc");
	//include("../inc_files/some_useful_functions.inc");
	$question[0] = 0;
	$choice[0] = 0;
	$qn = 0;
	$an = 0;
	$question_num = 0;
	//GET THE QUESTION NUMBER FROM THE DATABASE
	$qn = "SELECT question_num FROM question_def WHERE " .
					"question_seq = $qs";
	$qn = mssql_query($qn);
	$qn = mssql_fetch_array($qn);
	$question_num = $qn["question_num"];
	
	//GET THE DESCRIPTION FROM question_seq
	$qd = "SELECT question_def.question, question_seq, question_type_def.type_name FROM question_def, question_type_def WHERE " .
			"question_num = $question_num AND " .
			"question_def.test_seq = $test_seq AND " . 
			"question_def.type_seq = question_type_def.type_seq " .
			"ORDER BY question_seq";
	$qd = mssql_query($qd);
	$qd = mssql_fetch_array($qd);
	$description = $qd["question"];
	$ds = $qd["question_seq"];
		
	//GET THE QUESTIONS AND ANSWERS FROM question_seq and question_solution_dtl
	$qq = "SELECT question, question_def.question_seq, solution, solution_seq, type_name " .
			"FROM question_def, question_type_def, question_solution_dtl WHERE " . 
			"question_num = $question_num AND " .
			"question_def.test_seq = $test_seq AND " . 
			"question_solution_dtl.question_seq = question_def.question_seq AND " .
			"question_def.type_seq = question_type_def.type_seq AND " .
			"question_type_def.type_name = \"matching\" " .
			"ORDER BY question_def.question_seq";
	$qq = mssql_query($qq);
	for($i = 0; $q = mssql_fetch_array($qq); $i++){
		$question[$i] = $q["question"];
		$question_seq[$i] = $q["question_seq"];
		$solution[$i] = $q["solution"];
		$solution_seq[$i] = $q["solution_seq"];
	}
	//GET THE CHOICES FROM question_seq
	$qc = "SELECT question, question_type_def.type_name FROM question_def, question_type_def WHERE " .
			"question_num = $question_num AND " .
			"question_def.test_seq = $test_seq AND " . 
			"question_def.type_seq = question_type_def.type_seq AND " .
			"question_type_def.type_name = \"choice\" " .
			"ORDER BY question_seq";
	$qc = mssql_query($qc);
	for($i = 0; $c = mssql_fetch_array($qc); $i++){
		$choice[$i] = $c["question"];	
	}
	//GET ALL CHOICES FROM THE DATABASE
	$choices = "SELECT * FROM question_def, question_type_def " .
			   "WHERE " . 
			   "question_def.type_seq = question_type_def.type_seq " .
			   "AND question_type_def.type_name = \"choice\" " . 
			   "AND question_def.test_seq = $test_seq " . 
			   "AND question_def.question_num = " . $question_num . 
			   "ORDER BY question_def.question_seq";
	$choices = mssql_query($choices);
	while($a_choice = mssql_fetch_array($choices)){
		$choice[$an] = $a_choice["question"];
		$choice_seq[$an++] = $a_choice["question_seq"];
	}		
	/*
	* AT THIS POINT, THESE ARE THE VARIABLES TO WORK WITH:
	* $question_num, $test_seq, $qs, $num_blanks, $num_choices
	* $description, $ds
	* $question[], $question_seq[], $solution[], $solution_seq[]
	* $choice[], $choice_seq[]
	*/
	
	echo("<INPUT TYPE=\"HIDDEN\" NAME=\"num_blanks\" VALUE=\"$num_blanks\">\n");
	echo("<INPUT TYPE=\"HIDDEN\" NAME=\"num_choices\" VALUE=\"$num_choices\">\n");
	echo("<INPUT TYPE=\"HIDDEN\" NAME=\"description_seq\" VALUE=\"$ds\">\n");
	echo("<INPUT TYPE=\"HIDDEN\" NAME=\"question_num\" VALUE=\"$question_num\">\n");
	//DISPLAY ALL QUESTIONS AND CHOCIES IN TABLE FORM.
	echo("\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\">\n");
	echo("\t\t<TR>\n\t\t\t");
	echo("<TD WIDTH=\"3\" VALIGN=\"top\">$question_num)</TD>\n\t\t\t");
	echo("<TD colspan=\"3\">");
	$num_cols = 80;
	$str_length = strlen($description);
	$num_lines = $str_length / $num_cols + 1;
	echo("<TEXTAREA NAME=\"description\" COLS=\"$num_cols\" ROWS=\"$num_lines\" WRAP>");
	echo(stripslashes($description));
	echo("</TEXTAREA>\n");
	echo("</TD>\n");
	echo("\t\t</TR>\n"); 
	for($x=0, $y=0; ($x < sizeof($question) || $y < sizeof($choice) || $x < $num_blanks || $y < $num_choices); $x++, $y++){
		$num_cols = 100;
		echo("\t\t<TR>\n\t\t\t");
		echo("<TD WIDTH=\"3\" HALIGN=\"LEFT\" VALIGN=\"TOP\">\n\t\t\t\t");
		if($x < sizeof($solution)){
			if($x < $num_blanks){
				echo("<INPUT TYPE=\"TEXT\" size = " . strlen($solution[$x]). " NAME=\"solution_" . $solution_seq[$x] . "\" VALUE=\"" . $solution[$x] . "\" >\n\t\t\t");
			}
			else{
				echo("<INPUT TYPE=\"hidden\" NAME=\"solution_" . $solution_seq[$x] . "\" VALUE=\"DELETE\" >\n\t\t\t");
			}
		}
		else if ($x < $num_blanks){
			echo("<INPUT TYPE=\"TEXT\" size = 2 NAME=\"new_solution_" . $x . "\">\n\t\t\t");
		}
		echo("</TD>\n\t\t\t");
		echo("<TD>\n");
		if($x < sizeof($question)){
			if($x < $num_blanks){
				$str_length = strlen($question[$x]);
				if($str_length < $num_cols) 
					$num_cols = $str_length + 1;
				$num_lines = $str_length / $num_cols + 1;
				echo("\t\t\t\t<TEXTAREA NAME=\"question_" . $question_seq[$x] . "\" COLS=\"40\" ROWS=\"$num_lines\" WRAP>");
				echo(stripslashes($question[$x]));
				echo("</TEXTAREA>\n");
			}
			else{
				echo("\t\t\t\t<INPUT TYPE=\"hidden\" NAME=\"question_" . $question_seq[$x] . "\" VALUE=\"DELETE\">\n");
			}			
   		}
		else if ($x < $num_blanks){
			echo("\t\t\t\t<INPUT TYPE=\"TEXT\" size = 60 NAME=\"new_question_" . $x . "\">\n");
		}
		echo("\t\t\t</TD>\n");
		echo("\t\t\t<TD>\n\t\t\t\t");
		if(isset($choice[$y])){
			if($y < $num_choices){
				echo($alphabet[$y] . ")\n\t\t\t</TD>\n");
				echo("\t\t\t<TD>\n");
				$str_length = strlen($choice[$y]);
				echo("\t\t\t\t<TEXTAREA NAME=\"choice_" . $choice_seq[$y] . "\" COLS=\"40\" ROWS=\"$num_lines\" WRAP>");
				echo(stripslashes($choice[$y]));
				echo("</TEXTAREA>\n");
			}
			else{
			echo("\t\t\t</TD>\n\t\t\t<TD>\n");			
				echo("\t\t\t\t<INPUT TYPE=\"hidden\" NAME=\"choice_" . $choice_seq[$y] . "\" VALUE=\"DELETE\">\n");
			}		
   		}
		else if ($x < $num_choices){
			echo($alphabet[$y] . ")\n\t\t\t</TD>\n");
			echo("\t\t\t<TD>\n");
			echo("\t\t\t\t<INPUT TYPE=\"TEXT\" size = 60 NAME=\"new_choice_" . $y . "\">\n\t\t\t");
		}
		echo("\t\t\t</TD>\n\t\t</TR>\n");

	}		
	echo("\t</TABLE>\n\t<BR>\n");
}

function process_matching_mod($_POST){
	//view_array($_POST);
	$num_blanks = $_POST["num_blanks"];
	$num_choices = $_POST["num_choices"];
	$test_seq = $_POST["test_seq"];
	$description = $_POST["description"];
	$description_seq = $_POST["description_seq"];
	$question_num = $_POST["question_num"];
	$sn = 0;
	$qn = 0;
	$cn = 0;
	$ncc = 0;
	$nqc = 0;
	$nsc = 0;
	$s = 0;
	//view_array($_POST);
	foreach ($_POST as $key => $value){ 
		switch($key){
			case(!strncmp($key, "quest", 5) && is_numeric(substr($key, 9, 1))):
				$a_question = htmlspecialchars(ltrim($value));	
				$temp = ltrim($key);
				$ul = strrpos($temp, "_");
				$q_seq = substr($temp, ($ul + 1));
				$qs[$qn] = $q_seq;
				$question[$qn++] = $a_question;
				break;
			case(!strncmp($key, "choice", 6)):
				$a_choice = htmlspecialchars(ltrim($value));	
				$temp = ltrim($key);
				$ul = strrpos($temp, "_");
				$c_seq = substr($temp, ($ul + 1));
				$cs[$cn] = $c_seq;
				$choice[$cn++] = $a_choice;
				break;
			case(!strncmp($key, "solut", 5)):
				$temp = ltrim($key);
				$ul = strrpos($temp, "_");
				$ss[$s] = substr($temp, ($ul + 1));
				$solution[$s++] = htmlspecialchars(ltrim($value));
				break;
			case(!strncmp($key, "new_c", 5)):
				$a_choice = htmlspecialchars(ltrim($value));
				$new_choices[$ncc] = $a_choice;
				$ncc++;
				break;
			case(!strncmp($key, "new_q", 5)):
				$a_question = htmlspecialchars(ltrim($value));	
				$new_questions[$nqc] = $a_question;
				$nqc++;
				break;
			case(!strncmp($key, "new_s", 5)):
				$a_solution = htmlspecialchars(ltrim($value));	
				$new_solutions[$nsc] = $a_solution;
				$nsc++;
				break;				
		}
	}
	$update_d = "UPDATE question_def SET question = \"$description\" " .
				   "WHERE question_seq = $description_seq";
	if(!mssql_query($update_d))  echo("ERROR in: " . $update_d . "<BR>");
	for($i = 0; $i < sizeof($question); $i++){
		if($question[$i] == "DELETE"){
			$update_q = "DELETE from question_def WHERE question_seq = " . $qs[$i];
		}
		else{
			$update_q = "UPDATE question_def SET question = \"" . $question[$i] . "\" WHERE question_seq = " . $qs[$i];
		}
		//echo($update_q . "<BR>");
		if(!mssql_query($update_q))  echo("ERROR in: " . $update_q . "<BR>");
	}
	for($i = 0; $i < sizeof($solution); $i++){
		if($solution[$i] == "DELETE"){
			$update_s = "DELETE FROM question_solution_dtl WHERE solution_seq = " . $ss[$i];
		}
		else{
			$update_s = "UPDATE question_solution_dtl SET solution = \"" . 
						$solution[$i] . "\" WHERE solution_seq = " . $ss[$i];
		}
		//echo($update_s . "<BR>");
		if(!mssql_query($update_s))  echo("ERROR in: " . $update_s . "<BR>");
	}
	for($i = 0; $i < sizeof($choice); $i++)		{
		if($choice[$i] == "DELETE"){
			$update_c = "DELETE FROM question_def WHERE question_seq = " . $cs[$i];
		}
		else{
			$update_c = "UPDATE question_def SET question = \"" . 
					$choice[$i] . "\" WHERE question_seq = " . $cs[$i];
		}
		//echo($update_c . "<BR>");
		if(!mssql_query($update_c))  echo("ERROR in: " . $update_c . "<BR>");
	}
	$type_seq = "SELECT type_seq FROM question_type_def WHERE " .
				"type_name = 'matching' OR " .
				"type_name = 'choice' ";
	$type_seq = mssql_query($type_seq);
	$type = mssql_fetch_row($type_seq);
	$ts_question = $type[0];
	$type = mssql_fetch_row($type_seq);
	$ts_choice = $type[0];
	
	for($i = 0; $i < sizeof(@$new_questions); $i++){
		$insert_q	= "INSERT INTO question_def "	.
					"(test_seq, type_seq, question_num, question) VALUES " .
					"('$test_seq', '$ts_question', '$question_num', \"". addslashes($new_questions[$i]) . "\");";	
		//echo($insert_q . "<BR>");
		if(!mssql_query($insert_q))  echo("ERROR in: " . $insert_q . "<BR>");
		$question_seq = "SELECT MAX(question_seq) AS question_seq FROM question_def " .
						"WHERE test_seq = $test_seq AND type_seq = $ts_question " . 
						"AND question_num = $question_num";
		//echo($question_seq . "<BR>");
		$qs = mssql_query($question_seq);
		$qs = mssql_fetch_row($qs);
		$question_seq = $qs[0];
		$insert_s = "INSERT INTO question_solution_dtl " .
						"(question_seq, test_seq, solution) " .
						"VALUES " .
						"('$question_seq', '$test_seq', \"". strtoupper($new_solutions[$i]) . "\")";
		//echo($insert_s . "<BR>");
		if(!mssql_query($insert_s))  echo("ERROR in: " . $insert_s . "<BR>");
	}
	for($i = 0; $i < sizeof(@$new_choices); $i++)		{
		$insert_c	= "INSERT INTO question_def "	.
				"(test_seq, type_seq, question_num, question) VALUES " .
				"('$test_seq', '$ts_choice', '$question_num', \"". addslashes($new_choices[$i]) . "\");";	
		//echo($insert_c . "<BR>");
		if(!mssql_query($insert_c))  echo("ERROR in: " . $insert_c . "<BR>");
	}	
	Header("Location: ./menu_admin.php?cat=test&scr=modtest&ts=$test_seq&do=mod");	
	
}
?>