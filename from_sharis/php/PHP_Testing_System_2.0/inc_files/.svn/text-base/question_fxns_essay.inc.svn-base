<?php    
/*
 * displayES takes the number of essay questions requested and displays
 * that number of essay spaces in the form.
 */
function display_essay_form($question_num){
    include("variables.inc");
    if($question_num == 0) $question_num = 1;
    echo("\t<TABLE WIDTH=\"$table_width\">\n");
    echo("\t\t<TR>\n");
    echo("\t\t\t<TD WIDTH=\"50\"><P>" . $question_num . ")</P></TD>\n");
    echo("\t\t\t<TD WIDTH=\"580\"> \n\t\t\t\t\n");
    echo("\t\t\t\t<INPUT TYPE=\"text\" NAME=\"question_A\" SIZE=\"85\">\n");
    echo("\t\t\t</TD>\n");
    echo("\t\t</TR>\n");
    echo("\t\t\t<TD WIDTH=\"50\">Solution:</TD>\n");
    echo("\t\t\t<TD WIDTH=\"580\"> \n\t\t\t\t\n");
    echo("\t\t\t\t<TEXTAREA NAME=\"answer_A\" cols=\"65\" rows=\"15\"></textarea>\n");
    echo("\t\t\t</TD>\n");
    echo("\t\t</TR>\n");
    echo("\t\t<TD COLSPAN=\"2\"></TD>\n");
    echo("\t\t</TR>\n");
    echo("\t</TABLE>\n");    
}

function display_essay_for_taking($q_num, $this_question){
    include("variables.inc");
    echo("\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\">\n");
    echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\" HALIGN=\"LEFT\">" . $q_num . ")</TD>\n\t\t\t<TD colspan=\"3\">" . stripslashes($this_question["question"]) . "</TD>\n");
    echo("\t\t</TR>\n"); 
    echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\"></TD>\n");
    echo("\t\t\t<TD COLSPAN=\"2\"VALIGN=\"top\"><TEXTAREA NAME=\"" . $this_question["question_seq"] . "\" cols=\"70\" rows=\"10\"></TEXTAREA></TD>");
    echo("\n\t\t</TR>\n");
    echo("\t</TABLE>\n\t<BR>\n");
}

function display_essay_with_solution($this_question, $all_questions, $for_mod, $q_num){
    include("variables.inc");
    $answer = "SELECT * FROM question_solution_dtl WHERE question_seq = " . $this_question["question_seq"];
    $answer = mssql_query($answer);
    $answer = mssql_fetch_array($answer);
    $solution = nl2br($answer["solution"]);
    echo("\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\" ID=\"a_question\"");
    if($for_mod){
        echo(" onMouseOver=\"this.className='red_bold'\" onMouseOut=\"this.className='black'\" ");
        echo("onclick=\"submit();\"");
    }
    echo(">\n");
    $the_question = stripslashes($this_question["question"]);
    echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\">" . $q_num . ")</TD>\n\t\t\t<TD colspan=\"3\">" . $the_question . "</TD>\n");
    echo("\t\t</TR>\n"); 
    echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\"></TD>\n");
    echo("\t\t\t <TD VALIGN=\"top\">");
    if(empty($for_mod)) echo("<B>");
    echo(stripslashes($solution));
    if(empty($for_mod)) echo("</B>");
    echo("</TD>");
       echo("\n\t\t</TR>\n");
    echo("\t</TABLE>\n\t<BR>\n");
}


function add_essay_to_email($this_question, $take_seq){
    include("variables.inc");
    $answer = "SELECT answer, correct FROM student_answer_dtl WHERE " .
                "take_seq = $take_seq AND " .
                "question_seq = " . $this_question["question_seq"];
    $answer = mssql_query($answer);
    $answer = mssql_fetch_array($answer);
    $emp_answer = $answer["answer"];
    $correct = $answer["correct"];
    $question_num = $this_question["question_num"];
    $email_body = "\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\">\n";
    $email_body .= "\t\t<TR>\n\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\">" . $question_num . ")</TD>\n\t\t\t<TD colspan=\"3\">" . stripslashes($this_question["question"]) . "</TD>\n";
    $email_body .= "\t\t</TR>\n"; 
    $email_body .= "\t\t<TR>\n\t\t\t<TD WIDTH=\"3\">&nbsp</TD>\n";
    //$solution = stripslashes($solution["solution"]);
    //if(empty($solution)) $solution = "No solution was entered.";
    $email_body .= "\t\t\t <TD VALIGN=\"top\"><B>" . stripslashes(ltrim($emp_answer)) . "</B></TD>";
    $email_body .= "\n\t\t</TR>\n";
    $email_body .= "\t</TABLE>\n\t<BR>\n";
    return $email_body;
}
function display_essay_graded($this_question, $q_num, $take_seq){
    include("variables.inc");
    $answer = "SELECT answer, correct FROM student_answer_dtl WHERE " .
                "take_seq = $take_seq AND " .
                "question_seq = " . $this_question["question_seq"];
    $answer = mssql_query($answer);
    $answer = mssql_fetch_array($answer);
    $emp_answer = $answer["answer"];
    $correct = $answer["correct"];
    echo("\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\">\n");
    echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\">" . $q_num . ")</TD>\n\t\t\t<TD colspan=\"3\">" . stripslashes($this_question["question"]) . "</TD>\n");
    echo("\t\t</TR>\n"); 
    echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\">&nbsp</TD>\n");
    //$solution = stripslashes($solution["solution"]);
    //if(empty($solution)) $solution = "No solution was entered.";
    echo("\t\t\t <TD VALIGN=\"top\"><B>" . stripslashes(ltrim($emp_answer)) . "</B></TD>");
    echo("\n\t\t</TR>\n");
    echo("\t</TABLE>\n\t<BR>\n");
}
function display_essay_to_mod($test_seq, $question_seq){
    include("variables.inc");
    
    $question = "SELECT question, question_num, solution FROM " . 
               "question_def, question_solution_dtl " .
               "WHERE question_def.question_seq = $question_seq " .
               "AND question_solution_dtl.question_seq = $question_seq";
    $question = mssql_query($question);
    $question = mssql_fetch_array($question);
    $solution = $question["solution"];
    $question_phrase = stripslashes($question["question"]);    
    echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"tm\" VALUE=\"essay\">\n");
    echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"ts\" VALUE=\"$test_seq\">\n");
    echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"qs\" VALUE=\"$question_seq\">\n");
    
    echo("\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\">\n");
    echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\" VALIGN=\"top\">" . $question["question_num"] . ")</TD>\n\t\t\t");
     echo("<TD colspan=\"3\">");
    $str_length = 100;
    if($str_length < $num_cols) 
        $num_cols = $str_length + 1;
    $num_lines = $str_length / $num_cols + 1;
    echo("<TEXTAREA NAME=\"question\" COLS=\"$num_cols\" ROWS=\"$num_lines\" WRAP>");
    echo($question_phrase);
    echo("</TEXTAREA>\n");
    echo("</TD>\n\t\t"); 
      echo("</TR>\n"); 
    echo("\t\t<TR>\n\t\t\t<TD WIDTH=\"3\"></TD>\n");
    echo("\t\t\t <TD VALIGN=\"top\"><TEXTAREA NAME=\"answer\" cols=\"80\" rows=\"10\">$solution</TEXTAREA></TD>");
    echo("\n\t\t</TR>\n");
    echo("\t</TABLE>\n\t<BR>\n");
}

function process_essay_mod($_POST){
    $qs = $_POST["qs"];
    $ts = $_POST["ts"];
    $question = htmlspecialchars($_POST["question"]);
    $answer = htmlspecialchars($_POST["answer"]);
    if((empty($answer)) || strlen($answer) == 0) $answer="No answer entered";
    $update_q = "UPDATE question_def SET question = \"" . $question  .
                "\" WHERE question_seq = $qs";
    $update_a = "UPDATE question_solution_dtl SET solution = \"" . $answer  .
                "\" WHERE question_seq = $qs";
    if(!mssql_query($update_q) || !mssql_query($update_a)) echo("ERROR in: $update_q or $update_a<BR>");
    else Header("Location: ./menu_admin.php?cat=test&scr=modtest&ts=$ts&do=mod");
}

?>