<?php
/*
 * displaytrue_false takes the number of true false questions requested and displays
 * that number of true false spaces in the form.
 */
function display_true_false_form($question_num){
   include("variables.inc");
	echo("\t<TABLE WIDTH=\"$table_width\">\n");
	echo("\t\t<TR>\n");
	if($question_num == 0) $question_num = 1;
	echo("\t\t\t<TD WIDTH=\"50\">" . $question_num . ")</TD>\n");
	echo("\t\t\t<TD WIDTH=\"580\"> \n\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp\n");
	echo("\t\t\t\t<INPUT TYPE=\"text\" NAME=\"question_A\" SIZE=\"85\">\n");
	echo("\t\t\t</TD>\n");
	echo("\t\t</TR>\n");
	echo("\t\t<TR>\n");
	echo("\t\t\t<TD WIDTH=\"50\">Answer:</TD>\n");
	echo("\t\t\t<TD WIDTH=\"580\">\n\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TRUE:\n\t\t\t\t");
	echo("<INPUT TYPE=\"radio\" NAME=\"answer_A\" VALUE=\"true\">\n\t\t\t\t");
	echo("&nbsp;&nbsp;&nbsp;FALSE:\n\t\t\t\t");
	echo("<INPUT TYPE=\"radio\" NAME=\"answer_A\" VALUE=\"false\">\n\t\t\t");
	echo("</TD>\n");
	echo("\t\t\t<TD COLSPAN=\"2\">&nbsp;</TD>\n");
	echo("\t\t</TR>\n");
	echo("\t</TABLE>\n");	
}

function display_true_false_for_taking($q_num, $row, $questions){	
   include("variables.inc");
   echo("\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"CENTER\">\n");
   echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\">" . $q_num . ")</TD>\n\t\t\t<TD colspan=\"2\">" . stripslashes($row["question"]) . "</TD>\n");
	echo("\t\t</TR>\n");
	echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\"></TD>\n\t\t\t<TD WIDTH=\"5%\">");
	echo("<VALIGN=\"TOP\"><INPUT TYPE=\"radio\" NAME=\"" . $row["question_seq"] . "\" VALUE=\"true\">TRUE");
	echo("</TD>\n\t\t\t <TD VALIGN=\"top\"><INPUT TYPE=\"radio\" NAME=\"" . $row["question_seq"] . "\" VALUE=\"false\">FALSE");
	echo("</TD>\n\t\t</TR>\n");
	echo("\t</TABLE>\n\t<BR>\n");
}

function add_true_false_to_email($this_question, $take_seq){
    include("variables.inc");
	$solution = "SELECT solution FROM question_solution_dtl WHERE question_seq = " . $this_question["question_seq"];
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
		
	$email_body = "\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\">\n";
	$email_body .= "\t\t<TR>\t\t\t";
	$email_body .= "\n\t\t\t<TD VALIGN=\"top\" HALIGN=\"LEFT\">" . $question_num . ") " . stripslashes($this_question["question"]) . "</TD>\n";
	$email_body .= "\t\t</TR>\n"; 
	$email_body .= "\t\t<TR>\n";
	$email_body .= "\t\t\t<TD VALIGN=\"top\" HALIGN=\"LEFT\">";
	if($correct) $email_body .= "&nbsp &nbsp <FONT STYLE=\"color:green;font-weight:900\">" .$emp_answer . "</FONT></TD>";
	else if(!$correct) $email_body .= "&nbsp &nbsp <FONT STYLE=\"color:red;font-weight:900\">" .$solution . "</FONT></TD>";
	$email_body .= "\n\t\t</TR>\n";
	$email_body .= "\t</TABLE>\n\t<BR>\n";
	return $email_body;
}

function display_true_false_graded($this_question, $q_num, $take_seq){
    include("variables.inc");
	$solution = "SELECT solution FROM question_solution_dtl WHERE question_seq = " . $this_question["question_seq"];
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
		
	echo("\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\">\n");
	echo("\t\t<TR>\t\t\t");
	echo("\n\t\t\t<TD WIDTH=\"5\" VALIGN=\"top\">" . $q_num . ")</TD>\n\t\t\t<TD colspan=\"3\">" . stripslashes($this_question["question"]) . "</TD>\n");
	echo("\t\t</TR>\n"); 
	echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"5\"><IMG SRC=\"");
	if($correct) echo($GLOBALS["check_mark"]);
	else echo($GLOBALS["x_mark"]);
	echo("\"></TD>\n");
	echo("\t\t\t<TD VALIGN=\"top\" HALIGN=\"LEFT\" COLSPAN=\"2\"><U>&nbsp<B>" . $emp_answer . "</B>&nbsp</U>");
	echo("</TD><TD>");
	if(!$correct) echo("&nbsp &nbsp <FONT STYLE=\"color:red;font-weight:900\">" .$solution . "</FONT>");
	echo("</TD>\n\t\t</TR>\n");
	echo("\t</TABLE>\n\t<BR>\n");
}

function display_true_false_with_solution($question, $all_questions, $for_mod, $q_num){
    include("variables.inc");
	$question_seq = $question["question_seq"];
	$test_seq = $question["test_seq"];
	$answers = "SELECT solution FROM question_solution_dtl WHERE question_seq = $question_seq";
	$answers = mssql_query($answers);
	$answers = mssql_fetch_array($answers);
	$solution = $answers["solution"];
	echo("\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\" ID=\"a_question\"");
	if($for_mod){
		echo(" onMouseOver=\"this.className='red_bold'\" onMouseOut=\"this.className='black'\" ");
		echo("onclick=\"submit();\"");
	}
	echo(">\n");
	echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\">");
	echo($q_num . ")");
	echo("</TD>\n\t\t\t<TD colspan=\"3\">");
	echo(stripslashes($question["question"]));
	echo("</TD>\n");
	echo("\t\t</TR>\n"); 
	echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\"></TD>\n");
	echo("\t\t\t <TD VALIGN=\"top\">");
	if(empty($for_mod)) echo("<B>");
	echo("<U>&nbsp ");
	echo($solution);
	echo(" &nbsp</U>");
	if(empty($for_mod)) echo("</B>");
	echo("</TD>");
	echo("\n\t\t</TR>\n");
	echo("\t</TABLE>\n\t");
	echo("<BR>\n");
}

function display_true_false_to_mod($test_seq, $question_seq){
	include("variables.inc");
	$question = "SELECT question, question_def.question_seq, question_num, solution FROM " . 
			   "question_def, question_solution_dtl " .
			   "WHERE question_def.question_seq = $question_seq " .
			   "AND question_solution_dtl.question_seq = $question_seq";
	$question = mssql_query($question);
	$question = mssql_fetch_array($question);
	$solution = $question["solution"];
	$question_seq = $question["question_seq"];
	$question_phrase = ereg_replace ("(<br />|<br/>)","", $question["question"]) ;
	echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"tm\" VALUE=\"true_false\">\n");
	echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"ts\" VALUE=\"$test_seq\">\n");
	echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"qs\" VALUE=\"$question_seq\">\n");
	echo("\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\">\n");
	echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\">" . $question["question_num"] . ")</TD>\n\t\t\t");
	echo("<TD colspan=\"3\">");
	$num_cols = 70;
	$str_length = 100;
	if($str_length < $num_cols) 
		$num_cols = $str_length + 1;
	$num_lines = $str_length / $num_cols + 1;
	echo("<TEXTAREA NAME=\"question\" COLS=\"$num_cols\" ROWS=\"$num_lines\" WRAP=\"VIRTUAL\">");
	echo(stripslashes($question_phrase));
	echo("</TEXTAREA>\n");
	echo("\t\t</TR>\n"); 
	echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\"></TD>\n");
	echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\"></TD>\n\t\t\t<TD WIDTH=\"5%\">");
	echo("<VALIGN=\"TOP\">");
	echo("<INPUT TYPE=\"radio\" NAME=\"answer\" VALUE=\"true\" ");
	if($solution == "TRUE") echo("CHECKED ");
	echo(">TRUE");
	echo("</TD>\n\t\t\t <TD VALIGN=\"top\">");
	echo("<INPUT TYPE=\"radio\" NAME=\"answer\" VALUE=\"false\"");
	if($solution == "FALSE") echo("CHECKED ");
	echo(">FALSE");	
	echo("</TD>\n\t\t</TR>\n");
	echo("\t</TABLE>\n\t<BR>\n");
}

function process_true_false_mod($_POST){
 	$qs = $_POST["qs"];
	$ts = $_POST["ts"];
	$question = nl2br($_POST["question"]);
	$answer = nl2br($_POST["answer"]);
	$update_q = "UPDATE question_def SET question = \"$question\" " .
			  "WHERE question_seq = $qs";
	$update_a = "UPDATE question_solution_dtl SET solution = \"" . strtoupper($answer) .
				"\" WHERE question_seq = $qs";
	if(!mssql_query($update_q) || !mssql_query($update_a)) die("ERROR in: $update_q or $update_a<BR>");
	else Header("Location: ./menu_admin.php?cat=test&scr=modtest&ts=$ts&do=mod");	
}
?>