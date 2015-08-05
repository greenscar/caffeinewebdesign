<?php

/*
 * displaymultiple_choice takes the number of multiple_choice questions requested and displays
 * that number of multiple_choice spaces in the form.
 */
function display_multiple_choice_form($question_num){
	if($question_num == 0) $question_num = 1;
	include("variables.inc");
	echo("\t<TABLE WIDTH=\"$table_width\" BORDER=0>\n");
    echo("\t\t<TR>\n");
    echo("\t\t\t<TD WIDTH=\"50\">" . $question_num . ")</TD>\n");
    echo("\t\t\t<TD WIDTH=\"580\"> \n\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp\n");
    echo("\t\t\t\t<INPUT TYPE=\"text\" NAME=\"question_A\" SIZE=\"85\">\n");
    echo("\t\t\t</TD>\n");
    echo("\t\t</TR>\n");
    for($i=0; $i<4; $i++){
		echo("\t\t<TR>\n");
		if($i == 0) echo("\t\t\t<TD WIDTH=\"50\">Answer:</TD>\n");
		else echo("\t\t\t<TD WIDTH=\"50\">&nbsp;</TD>\n");
        echo("\t\t\t<TD WIDTH=\"580\">");
        echo("<INPUT TYPE=\"radio\" NAME=\"answer_A\" VALUE=\"" . $alphabet[$i] . "\">\n");
        echo("\t\t\t\t<INPUT TYPE=\"text\" NAME=\"choice_". @$alphabet[$i] . "\" SIZE=\"50\">\n");
        echo("\t\t\t</TD>\n");
        echo("\t\t</TR>\n");
    }
    echo("\t</TABLE>\n");    
}


function display_multiple_choice_for_taking($q_num, $this_question, $questions){   
    include("variables.inc");
	$question_seq = $this_question["question_seq"];
	$test_seq = $this_question["test_seq"];
    echo("\n\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\">\n");
    echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\"  HALIGN=\"LEFT\">" . $q_num . ")</TD>\n\t\t\t<TD colspan=\"2\"  HALIGN=\"LEFT\">" . stripslashes($this_question["question"]) . "</TD>\n\t\t</TR>\n");
	//GET ALL CHOICES FROM THE DATABASE
	$choices = "SELECT * FROM question_def, question_type_def " .
               "WHERE " . 
               "question_def.type_seq = question_type_def.type_seq " .
			   "AND question_type_def.type_name = \"choice\" " . 
			   "AND question_def.test_seq = $test_seq " . 
		       "AND question_def.question_num = " . $this_question["question_num"] .
			   " ORDER BY question_def.question_seq";
	$choices = mssql_query($choices);
	for($i = 0; $i < 4; $i++){
		$a_choice = mssql_fetch_array($choices);
		echo("\t\t<TR>\n");
		echo("\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\"></TD>\n\t\t\t");
    	echo("<TD VALIGN=\"top\"><INPUT TYPE=\"radio\" NAME=\"" . $question_seq . "\" VALUE=\"" .$alphabet[$i] . "\">");
		echo($alphabet[$i] . ") " . stripslashes($a_choice["question"]));
		echo("</TD>\n");
    	echo("\t\t</TR>\n");
	}
    echo("\t</TABLE>\n\t<BR>\n");
}
function display_multiple_choice_graded($this_question, $q_num, $take_seq){ 
	$question_seq = $this_question["question_seq"];
	include("variables.inc");
	$solution = "SELECT solution FROM question_solution_dtl WHERE question_seq = $question_seq";
	$solution = mssql_query($solution);
	$solution = mssql_fetch_array($solution);
	$solution = $solution["solution"];  
	
	$answer = "SELECT answer, correct FROM student_answer_dtl WHERE " .
				"take_seq = $take_seq AND " .
				"question_seq = " . $this_question["question_seq"];
	$answer = mssql_query($answer);
	$answer = mssql_fetch_array($answer);
	$emp_answer = $answer["answer"];
	$correct = $answer["correct"];
	
    //GET ALL CHOICES FROM THE DATABASE
	$choices = "SELECT * FROM question_def, question_type_def " .
               "WHERE " . 
               "question_def.type_seq = question_type_def.type_seq " .
			   "AND question_type_def.type_name = \"choice\" " . 
			   "AND question_def.test_seq =  " . $this_question["test_seq"] .
		       " AND question_def.question_num = " . $this_question["question_num"] . 
			   " ORDER BY question_def.question_seq";
	$choices = mssql_query($choices);
	echo("\n\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\">\n");
    echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"8\" VALIGN=\"top\">" . $q_num . ")</TD>\n\t\t\t<TD colspan=\"3\">" . stripslashes($this_question["question"]) . "</TD>\n\t\t</TR>");
    for($i = 0; $i < 4; $i++){
		$a_choice = mssql_fetch_array($choices);
		echo("\n\t\t<TR>\n");
		echo("\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\">");
    	if($emp_answer == $alphabet[$i]){
			echo("<IMG SRC=\"");
			if(strcmp($emp_answer, $solution) == 0) echo($GLOBALS["check_mark"]);
			else echo($GLOBALS["x_mark"]);
			echo("\">");
		}
		else if (!isset($emp_answer) && $alphabet[$i] == $solution){
			echo("<IMG SRC=\"");
			echo($GLOBALS["x_mark"]);
			echo("\">");
		}
		echo("&nbsp</TD>\n\t\t\t");
   		echo("<TD width=500>\n\t\t\t\t");
		if($emp_answer == $alphabet[$i]) echo("<B>");
		else if ($alphabet[$i] == $solution) echo("<P STYLE=\"color:red;font-weight:900\">");
   		echo($alphabet[$i] . ") " . stripslashes($a_choice["question"]));
		if($emp_answer == $alphabet[$i]) echo("</B>");
		else if ($alphabet[$i] == $solution) echo("</P>");
    	echo("\n\t\t\t</TD>\n");
    	echo("\t\t</TR>");
	}
    echo("\t</TABLE>\n\t<BR>\n");
}

function add_multiple_choice_to_email($this_question, $take_seq){
	$question_seq = $this_question["question_seq"];
	include("variables.inc");
	$solution = "SELECT solution FROM question_solution_dtl WHERE question_seq = $question_seq";
	$solution = mssql_query($solution);
	$solution = mssql_fetch_array($solution);
	$solution = $solution["solution"];  
	$question_num = $this_question["question_num"];
	
	$answer = "SELECT answer, correct FROM student_answer_dtl WHERE " .
				"take_seq = $take_seq AND " .
				"question_seq = " . $this_question["question_seq"];
	$answer = mssql_query($answer);
	$answer = mssql_fetch_array($answer);
	$emp_answer = $answer["answer"];
	$correct = $answer["correct"];
	
    //GET ALL CHOICES FROM THE DATABASE
	$choices = "SELECT * FROM question_def, question_type_def " .
               "WHERE " . 
               "question_def.type_seq = question_type_def.type_seq " .
			   "AND question_type_def.type_name = \"choice\" " . 
			   "AND question_def.test_seq =  " . $this_question["test_seq"] .
		       " AND question_def.question_num = " . $question_num . 
			   " ORDER BY question_def.question_seq";
	$choices = mssql_query($choices);
	$email_body ="\n\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\">\n";
    $email_body .="\t\t<TR>\n\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\">" . $question_num . ")</TD>\n\t\t\t<TD colspan=\"2\">" . stripslashes($this_question["question"]) . "</TD>\n\t\t</TR>";
    for($i = 0; $i < 4; $i++){
		$a_choice = mssql_fetch_array($choices);
		$email_body .="\n\t\t<TR>\n";
		$email_body .="<TD>&nbsp</TD>\n";
   		$email_body .="<TD>\n\t\t\t\t";
		if($emp_answer == $alphabet[$i]) $email_body .="<P STYLE=\"color:green;font-weight:900\">";
		else if ($alphabet[$i] == $solution) $email_body .="<P STYLE=\"color:red;font-weight:900\">";
   		$email_body .=$alphabet[$i] . ") " . stripslashes($a_choice["question"]);
		if($emp_answer == $alphabet[$i]) $email_body .="</P>";
		else if ($alphabet[$i] == $solution) $email_body .="</P>";
    	$email_body .="\n\t\t\t</TD>\n";
    	$email_body .="\t\t</TR>";
	}
    $email_body .="\t</TABLE>\n\t<BR>\n";
	return $email_body;
}

function display_multiple_choice_with_solution($this_question, $all_questions, $for_mod, $q_num){ 
	include("variables.inc");
	$question_seq = $this_question["question_seq"];
	$solution = "SELECT solution FROM question_solution_dtl WHERE question_seq = $question_seq";
	$solution = mssql_query($solution);
	$solution = mssql_fetch_array($solution);
	$solution = $solution["solution"];  
    $test_seq = $this_question["test_seq"];
	//GET ALL CHOICES FROM THE DATABASE
	$choices = "SELECT * FROM question_def, question_type_def " .
               "WHERE " . 
               "question_def.type_seq = question_type_def.type_seq " .
			   "AND question_type_def.type_name = \"choice\" " . 
		       "AND question_def.question_num = " . $this_question["question_num"] .
			   "AND question_def.test_seq = $test_seq " .
			   "ORDER BY question_def.question_seq";
	$choices = mssql_query($choices);
	echo("\n\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\" ");
    if($for_mod){
		echo(" onMouseOver=\"this.className='red_bold'\" onMouseOut=\"this.className='black'\" ");
		echo("onclick=\"submit();\"");
	}
	echo(">\n");
    echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\">" . $q_num . ")</TD>\n\t\t\t<TD colspan=\"3\" WIDTH=\"600\">" . stripslashes($this_question["question"]) . "</TD>\n\t\t</TR>\n\t\t");
    for($i = 0; $i < 4; $i++){
		$a_choice = mssql_fetch_array($choices);
		//@$a_choice = mssql_fetch_array($all_questions);
		echo("<TR>\n");
    	echo("\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\"></TD>\n\t\t\t");
    	echo("<TD VALIGN=\"top\" WIDTH=\"500\">");	
		if(strcmp($solution, $alphabet[$i]) == 0){ // && empty($for_mod)
			echo("<B>");
			echo($alphabet[$i] . ") " . stripslashes($a_choice["question"]));
			echo("</B>");
		}		
		else{
			echo($alphabet[$i] . ") " . stripslashes($a_choice["question"]));
		}
    	echo("</TD>\n");
    	echo("\t\t</TR>\n\t\t");
	}
    echo("\t</TABLE>\n\t<BR>\n");
}

function display_multiple_choice_to_mod($test_seq, $question_seq){
	include("../inc_files/variables.inc");
	$solution = "SELECT * FROM question_solution_dtl WHERE question_seq = $question_seq" ;
	$solution = mssql_query($solution);
	$solution = mssql_fetch_array($solution);
	$answer = $solution["solution"];  
	$solution_seq = $solution["solution_seq"];
	
	$question = "SELECT * FROM question_def WHERE question_seq = $question_seq";
	$question = mssql_query($question);
	$this_question = mssql_fetch_array($question);
	$question_phrase = $this_question["question"];
    //GET ALL CHOICES FROM THE DATABASE
	$choices = "SELECT * FROM question_def, question_type_def " .
               "WHERE " . 
               "question_def.type_seq = question_type_def.type_seq " .
			   "AND question_type_def.type_name = \"choice\" " . 
		       "AND question_def.question_num = " . $this_question["question_num"] .
			   "AND question_def.question_seq > $question_seq " .
			   "ORDER BY question_def.question_seq";
	$choices = mssql_query($choices);
	echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"tm\" VALUE=\"multiple_choice\">\n");
	echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"ts\" VALUE=\"$test_seq\">\n");
	echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"qs\" VALUE=\"$question_seq\">\n");
	echo("\t\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"CENTER\">\n");
	echo("\t\t\t<TR>\n\t\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\">" . $this_question["question_num"] . ")</TD>\n");
	$str_length = strlen($question_phrase);
	if(@$str_length < @$num_cols) 
		$num_cols = $str_length + 1;
	$num_lines = $str_length / @$num_cols + 1;
	if($num_cols < 100) $num_cols = 100;
	echo("\t\t\t\t<TD colspan=\"2\">\n");
	echo("\t\t\t\t<TEXTAREA NAME=\"question\" COLS=\"$num_cols\" ROWS=\"$num_lines\" WRAP>". stripslashes($question_phrase) . "</TEXTAREA>\n");
	echo("\t\t\t\t</TD>\n");
    echo("\t\t\t</TR>\n");
	for($i = 0; $i < 4; $i++){
		$a_choice = mssql_fetch_array($choices);
		include("variables.inc");
		//@$this_question = mssql_fetch_array($all_questions);
		$question_phrase = $a_choice["question"];
		echo("\t\t\t<TR>\n");
    	echo("\t\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\">&nbsp</TD>\n");
    	echo("\t\t\t\t<TD WIDTH=\"5\"VALIGN=\"top\">\n");	
		echo("\t\t\t\t\t<INPUT TYPE=\"radio\" NAME=\"answer\" VALUE=\"" . $alphabet[$i] . "\"");
		if($alphabet[$i] == $answer){
			echo(" CHECKED ");
		}
		echo(">" . $alphabet[$i] . ") \n");
		echo("\t\t\t\t</TD>\n");
		$str_length = strlen($question_phrase);
		$num_cols = 50;
		if($str_length > $num_cols) 
			$num_cols = $str_length + 1;
		$num_lines = $str_length / $num_cols + 1;
		echo("\t\t\t\t<TD>\n");
		echo("\t\t\t\t\t<TEXTAREA NAME=\"choice_" . $a_choice["question_seq"] . "\" COLS=\"$num_cols\" ROWS=\"$num_lines\" WRAP>");
		echo(stripslashes($question_phrase));
		echo("</TEXTAREA>\n");
		echo("\t\t\t\t</TD>\n");
    	echo("\t\t\t</TR>\n");
	}
    echo("\t\t</TABLE>\n");
	echo("\t<BR>\n");
}

function process_multiple_choice_mod($_POST){
	$c = 0;
	foreach ($_POST as $key => $value){ 
		switch($key){
			case(!strncmp($key, "question", 9)):
				$question = htmlspecialchars(ltrim($value));	
				break;
			case(!strncmp($key, "answer", 6)):
				$answer = htmlspecialchars(ltrim($value));
				break;
			case(!strncmp($key, "choice", 6)):
				$temp = ltrim($key);
				$ul = strrpos($temp, "_");
				$temp = substr($temp, ($ul + 1));
				$choice_seq[$c] = $temp;
				$choice[$c++] = htmlspecialchars(ltrim($value));
				break;
		}
	}
	$ts = $_POST["ts"];
	$qs = $_POST["qs"];
	$update_q = "UPDATE question_def SET question = \"$question\" " .
	               "WHERE question_seq = $qs";
	$update_a = "UPDATE question_solution_dtl SET solution = \"$answer\" " .
	              "WHERE question_seq = $qs";
	if(!mssql_query($update_q) || !mssql_query($update_a)) echo("ERROR in: $update_q or $update_a<BR>");
	for($i = 0; $i < 4; $i++){
		$ch = $choice[$i];
		$ch_seq = $choice_seq[$i];
		$update_c = "UPDATE question_def SET question = \"$ch\" " .
		            "WHERE question_seq = " . $ch_seq;
		if(!mssql_query($update_c)) echo("ERROR IN $update_c<BR>");
	}
	Header("Location: ./menu_admin.php?cat=test&scr=modtest&ts=$ts&do=mod");
}

?>