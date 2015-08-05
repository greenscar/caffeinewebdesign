<?php
function display_word_list_form($question_num, $num_choices, $num_questions){
    include("../inc_files/variables.inc");
    if($question_num == 0) $question_num = 1;
    echo("\t<table WIDTH=\"$table_width\" border=\"0\">\n");
    echo("\t\t<TR><TD>\n");
    echo("\t\t<TABLE WIDTH=\"$table_width\" BORDER=\"1\">\n");
    //DISPLAY THE BOXES FOR THE CHOICES.
    for($nc=0;$nc<$num_choices;){
        echo("\t\t<TR>\n");
        for($x=0;$x<3; $x++, $nc++){
            if($nc < $num_choices)
                echo("\t\t\t<TD WIDTH = 187>$alphabet[$nc])<INPUT TYPE=\"TEXT\" SIZE=\"20\" NAME=\"choice_".$alphabet[$nc]."\"></TD>\n");
            else
                echo("\t\t\t<TD WIDTH = 187>&nbsp</TD>\n");
        }
        echo("\t\t</TR>\n");
    }
    echo("\t\t</TABLE>\n");
    echo("\t\t</TD></TR>\n");
    //DISPLAY THE QUESTION BLANK
    echo("\t\t<TR>\n");
    echo("\t\t\t<TD COLSPAN=\"3\">$question_num) <INPUT TYPE=\"TEXT\" SIZE=\"80\" NAME=\"subject\"></TD>\n");
    echo("\t\t</TR>\n");
    //DISPLAY THE BOXES FOR THE QUESTIONS
    echo("\t\t<TR><TD>\n");
    echo("\t\t<TABLE WIDTH=\"$table_width\" BORDER=\"1\">\n");
    echo("\t\t<TR>\n");
    for($nb=0;$nb<$num_questions;){
        echo("\t\t<TR>\n");
        for($x=0;$x<1; $x++, $nb++){
            if($nb < $num_questions){
                echo("\t\t\t<TD>\n");
                echo("\t\t\t\t<INPUT TYPE=\"TEXT\" SIZE=\"2\" NAME=\"answer_".$nb."\">\n");
                echo("\t\t\t\t<INPUT TYPE=\"TEXT\" SIZE=\"60\" NAME=\"question_".$nb."\">\n");
                echo("\t\t\t</TD>\n");
            }
            else
                echo("\t\t\t<TD>&nbsp</TD>\n");
        }
        echo("\t\t</TR>\n");
    }
    echo("\t\t</TR>\n");
    echo("\t\t</TABLE>\n");
    echo("\t\t</TD></TR>\n");
    echo("\t</TABLE>\n\t<BR>\n");
}
function display_word_list_for_taking($q_num, $this_question, $all_questions){
    include("variables.inc");
	 $question[0] = 0;
    $choice[0] = 0;
    $question_seq[0] = 0;
    $qn = 0;
    $an = 0;
    $question_num = 0;
    $test_seq = $this_question["test_seq"];
    $description = $this_question["question"];
    //GET THE NUMBER OF QUESTIONS IN THIS QUESTION
    $num_rows = "SELECT * FROM question_def, question_type_def WHERE " .
                "question_type_def.type_name = \"word_list\" AND " .
                "question_type_def.type_seq = question_def.type_seq AND " .
                "question_def.test_seq = $test_seq AND " .
                "question_def.question_num = \"" . $this_question["question_num"] . "\" " .
                "ORDER BY question_def.question_seq;";
    $temp = mssql_query($num_rows);
    $num_questions = (mssql_num_rows($temp));
	 $question_seq[0] = $this_question["question_seq"];
    $answers = "SELECT solution FROM question_solution_dtl WHERE question_seq = " . $question_seq[$qn];
    $answers = mssql_query($answers);
    $answers = mssql_fetch_array($answers);
    $solution[0] = $answers["solution"];
    $question[0] = $this_question["question"];
    $question_num = $q_num;
    for($qn = 1; $qn < $num_questions; $qn++){
        $this_question = mssql_fetch_array($all_questions);
        $question_seq[$qn] = $this_question["question_seq"];
        $question[$qn] = $this_question["question"];
        
        $answers = "SELECT solution FROM question_solution_dtl WHERE question_seq = " . $this_question["question_seq"];
        $answers = mssql_query($answers);
        $answers = mssql_fetch_array($answers);
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
    $num_choices = mssql_num_rows($choices);
    while($a_choice = mssql_fetch_array($choices)){
        $choice[$an++] = $a_choice["question"];
    }
	 //DISPLAY THE QUESTION
    echo("\t<table width=\"$table_width\" ALIGN=\"CENTER\" border=\"0\">\n");
    echo("\t\t<TR><TD>\n");
    echo("\t\t<TABLE WIDTH=\"" . (($table_width - 10)). "\" BORDER=\"1\">\n");
    //DISPLAY THE BOXES FOR THE CHOICES.
    for($nc=0;$nc<$num_choices;){
        echo("\t\t<TR>\n");
        for($x=0;$x<3; $x++, $nc++){
            if($nc < $num_choices)
                echo("\t\t\t<TD WIDTH = " . (($table_width - 10) / 3). ">$alphabet[$nc])$choice[$nc]</TD>\n");
            else
                echo("\t\t\t<TD WIDTH = " . (($table_width - 10) / 3). ">&nbsp</TD>\n");
        }
        echo("\t\t</TR>\n");
    }
    echo("\t\t</TABLE>\n");
    echo("\t\t</TD></TR>\n");
    //DISPLAY THE QUESTION BLANK
    echo("\t\t<TR>\n");
    echo("\t\t\t<TD COLSPAN=\"3\">$question_num)" . stripslashes($description) . "</TD>\n");
    echo("\t\t</TR>\n");
    //DISPLAY THE BOXES FOR THE QUESTIONS
    echo("\t\t<TR><TD>\n");
    echo("\t\t<TABLE WIDTH=\"" . (($table_width - 10)). "\" BORDER=\"0\">\n");
    echo("\t\t<TR>\n");
	 for($nb=1;$nb<$num_questions;$nb++){
        echo("\t\t<TR>\n");
        echo("\t\t\t<TD COLSPAN=\"3\">\n");
        echo("\t\t\t\t<INPUT TYPE=\"TEXT\" SIZE=\"2\" NAME=\"".$question_seq[$nb]."\">\n");
        echo("\t\t\t\t" . @$question[$nb] . "\n");
        echo("\t\t\t</TD>\n");
		  echo("\t\t</TR>\n");
    }    
    echo("\t\t</TR>\n");
    echo("\t\t</TABLE>\n");
    echo("\t\t</TD></TR>\n");
    echo("\t</TABLE>\n\t<BR>\n");
}

function add_word_list_to_email($this_question, $take_seq, $all_questions){
    //view_array($question);
	 include("variables.inc");
    //include("../inc_files/some_useful_functions.inc";
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
                "question_type_def.type_name = \"word_list\" AND " .
                "question_type_def.type_seq = question_def.type_seq AND " .
                "question_def.test_seq = $test_seq AND " .
                "question_def.question_num = \"" . $this_question["question_num"] . "\" " .
                "ORDER BY question_def.question_seq;";
    $temp = mssql_query($num_rows);
    $num_questions = mssql_num_rows($temp);
    $question_seq[0] = $this_question["question_seq"];
    $answers = "SELECT solution FROM question_solution_dtl WHERE question_seq = " . $question_seq[$qn];
    $answers = mssql_query($answers);
    $answers = mssql_fetch_array($answers);
    $solution[0] = $answers["solution"];
    $question[0] = $this_question["question"];
    for($qn = 1; $qn < $num_questions; $qn++){
        $this_question = mssql_fetch_array($all_questions);
        $question_num = $this_question["question_num"];
        $question_seq[$qn] = $this_question["question_seq"];
        $question[$qn] = $this_question["question"];
        
        $answers = "SELECT solution_seq, solution FROM question_solution_dtl WHERE question_seq = " . $question_seq[$qn];
        $answers = mssql_query($answers);
        $answers = mssql_fetch_array($answers);
        $solution[$qn] = $answers["solution"];
        $solution_seq[$qn] = $answers["solution_seq"];
        
        $emp_answer_q = "SELECT answer, correct FROM student_answer_dtl WHERE " .
                "take_seq = $take_seq AND " .
                "question_seq = " . $this_question["question_seq"];
        $emp_answer_q = mssql_query($emp_answer_q);
        $emp_answer[$qn] = mssql_fetch_array($emp_answer_q);
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
	 $num_choices = mssql_num_rows($choices);
    
    while($a_choice = mssql_fetch_array($choices)){
        $choice[$an++] = $a_choice["question"];
    }
    //START THE DISPLAY
    $email_body = "\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\" ID=\"a_question\">\n";
    $email_body .= "\t\t<TR><TD>\n";
    //DISPLAY THE CHOICES
    for($nc=0;$nc<$num_choices;){
        $email_body .= "\t\t<TR>\n";
        for($x=0;$x<3; $x++, $nc++){
            if($nc < $num_choices)
                $email_body .= "\t\t\t<TD WIDTH = 187>&nbsp$alphabet[$nc])$choice[$nc]</TD>\n";
            else
                $email_body .= "\t\t\t<TD WIDTH = 187>&nbsp</TD>\n";
        }
        $email_body .= "\t\t</TR>\n";
    }
    $email_body .= "\t\t</TD></TR>\n";
    //DISPLAY THE DESCRIPTION
    $email_body .= "\t\t<TR>\n";
    $email_body .= "\t\t\t<TD COLSPAN=\"3\">$question_num)" . stripslashes($description) . "</TD>\n";
    $email_body .= "\t\t</TR>\n";
    //DISPLAY THE QUESTIONS
    $email_body .= "\t\t<TR><TD COLSPAN=\"3\">\n";
	 $email_body .= "\t\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\">\n";
    for($nb=1;$nb<$num_questions;$nb++){
        $email_body .= "\t\t<TR>\n";
        $email_body .= "\t\t\t<TD COLSPAN=\"3\">\n";
        $email_body .= "\t\t\t\t<U>&nbsp";
        $email_body .= $emp_answer[$nb]["answer"];
		  $email_body .= " &nbsp</U>";
        $email_body .= "\t\t\t\t" . @$question[$nb] . "\n";
		  if(!@$emp_answer[$nb]["correct"]) $email_body .="&nbsp;&nbsp;<FONT STYLE=\"color:red;font-weight:900\">";
		  else $email_body .="&nbsp;&nbsp;<FONT STYLE=\"color:green;font-weight:900\">";
		  $email_body .= $solution[$nb]."</FONT>";
        $email_body .= "\t\t\t</TD>\n";
		  $email_body .= "\t\t</TR>\n";
    }    
    $email_body .= "\t\t</TABLE>\n";
    $email_body .= "\t\t</TD></TR>\n";
    $email_body .= "\t</TABLE>\n\t<BR>\n";	 
    return $email_body;
}


function display_word_list_graded($this_question, $q_num, $take_seq, $all_questions){
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
                "question_type_def.type_name = \"word_list\" AND " .
                "question_type_def.type_seq = question_def.type_seq AND " .
                "question_def.test_seq = $test_seq AND " .
                "question_def.question_num = \"" . $this_question["question_num"] . "\" " .
                "ORDER BY question_def.question_seq;";
    $temp = mssql_query($num_rows);
    $num_questions = (mssql_num_rows($temp));
    $question_seq[0] = $this_question["question_seq"];
    $answers = "SELECT solution FROM question_solution_dtl WHERE question_seq = " . $question_seq[$qn];
    $answers = mssql_query($answers);
    $answers = mssql_fetch_array($answers);
    $solution[0] = $answers["solution"];
    $question[0] = $this_question["question"];
    for($qn = 1; $qn < $num_questions; $qn++){
        $this_question = mssql_fetch_array($all_questions);
        $question_num = $this_question["question_num"];
        $question_seq[$qn] = $this_question["question_seq"];
        $question[$qn] = $this_question["question"];
        
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
	 $num_choices = mssql_num_rows($choices);
    while($a_choice = mssql_fetch_array($choices)){
        $choice[$an++] = $a_choice["question"];
    }
    //START THE DISPLAY
    echo("\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\" ID=\"a_question\">\n");
    echo("\t\t<TR><TD>\n");
    //DISPLAY THE CHOICES
    for($nc=0;$nc<$num_choices;){
        echo("\t\t<TR>\n");
        for($x=0;$x<3; $x++, $nc++){
            if($nc < $num_choices)
                echo("\t\t\t<TD WIDTH = 187>&nbsp$alphabet[$nc])$choice[$nc]</TD>\n");
            else
                echo("\t\t\t<TD WIDTH = 187>&nbsp</TD>\n");
        }
        echo("\t\t</TR>\n");
    }
    echo("\t\t</TD></TR>\n");
    //DISPLAY THE DESCRIPTION
    echo("\t\t<TR>\n");
    echo("\t\t\t<TD COLSPAN=\"3\">$question_num)" . stripslashes($description) . "</TD>\n");
    echo("\t\t</TR>\n");
    //DISPLAY THE QUESTIONS
    echo("\t\t<TR><TD>\n");
    //echo("\t\t<TABLE WIDTH=\"$table_width\" BORDER=\"1\">\n");
    for($nb=1;$nb<$num_questions;$nb++){
        echo("\t\t<TR>\n");
        echo("\t\t\t<TD COLSPAN=\"3\">\n");
        echo("\t\t\t\t<U>&nbsp");
        echo(stripslashes($answer[$nb]));
        echo(" &nbsp</U>");
        echo("\t\t\t\t" . @$question[$nb] . "\n");
        echo("<IMG SRC=\"");
        if($correct[$nb]) echo($GLOBALS["check_mark"]);
        else echo($GLOBALS["x_mark"]);
        echo("\">\n\t\t\t");
		  echo("\t\t\t</TD>\n");
		  echo("\t\t</TR>\n");
    }    
   // echo("\t\t</TABLE>\n");
    echo("\t\t</TD></TR>\n");
    echo("\t</TABLE>\n\t<BR>\n");
	 
}

	 /*
	 
	 
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
        echo("<TD WIDTH=\"47%\" HALIGN=\"LEFT\">\n\t\t\t\t");
        if($x < sizeof($answer))
        echo(@$question[$x]);
        if(!@$correct[$x]) echo("&nbsp;&nbsp;<FONT STYLE=\"color:red;font-weight:900\">" .@$solution[$x] . "</FONT>");
        echo("\n\t\t\t</TD>\n\t\t\t");
        echo("<TD WIDTH=\"47%\" HALIGN=\"LEFT\">\n\t\t\t\t");
        if(isset($choice[$y]))
            echo($alphabet[$y] . ") " . @$choice[$y]);
        echo("\n\t\t\t</TD>\n\t\t</TR>\n");

    }        
    echo("\t</TABLE>\n\t<BR>\n");
    //echo("<h4 align=\"center\">" . $this_question["question"] . "</h4>\n";
    */
function display_word_list_with_solution($this_question, $all_questions, $for_mod, $q_num){
	 include("variables.inc");
    $question[0] = 0;
    $choice[0] = 0;
    $question_seq[0] = 0;
    $qn = 0;
    $an = 0;
    $question_num = 0;
    $test_seq = $this_question["test_seq"];
    $description = $this_question["question"];
    $question_seq[0] = $this_question["question_seq"];
	 $question[0] = $this_question["question"];
    //echo("this_question<BR>");
	 //view_array($this_question);
	 //GET number of QUESTIONS FROM THE DATABASE
    $num_rows = "SELECT * FROM question_def, question_type_def WHERE " .
                "question_type_def.type_name = \"word_list\" AND " .
                "question_type_def.type_seq = question_def.type_seq AND " .
                "question_def.test_seq = " . $this_question["test_seq"] . " AND " .
                "question_def.question_num = \"" . $this_question["question_num"] . "\" " .
                "ORDER BY question_def.question_seq";
    $temp = mssql_query($num_rows);
    $num_questions = (mssql_num_rows($temp));
    //GET THE CHOICES AND ANSWERS FROM THE DATABASE
    $answers = "SELECT solution FROM question_solution_dtl " .
                "WHERE question_seq = " . $question_seq[$qn];
    $answers = mssql_query($answers);
    $answers = mssql_fetch_array($answers);
	 $solution[0] = $answers["solution"];
    for($qn = 1; $qn < $num_questions; $qn++){
        $this_question = mssql_fetch_array($all_questions);
		  $question_num = $this_question["question_num"];
        $question_seq[$qn] = $this_question["question_seq"];
        $question[$qn] = stripslashes($this_question["question"]);
        $ans = "SELECT solution FROM question_solution_dtl WHERE question_seq = " . $question_seq[$qn];
        $answers = mssql_query($ans) or die("error in: <B>$ans<B><BR>");
        $answers = mssql_fetch_array($answers);
        $solution[$qn] = stripslashes($answers["solution"]);
    }
	 echo("question");
	 view_array($question);
	 $choices = "SELECT * FROM question_def, question_type_def " .
               "WHERE " . 
               "question_def.type_seq = question_type_def.type_seq " .
               "AND question_type_def.type_name = \"choice\" " . 
               "AND question_def.test_seq = $test_seq " . 
               "AND question_def.question_num = " . $this_question["question_num"] .
               "ORDER BY question_def.question_seq;";
    $choices = mssql_query($choices);
    $num_choices = mssql_num_rows($choices);
    while($a_choice = mssql_fetch_array($choices)){
	     $choice[$an++] = stripslashes($a_choice["question"]);
    }
	 //echo("choices<BR>");
	 //view_array($choice);
	 //END GET ALL CHOICES AND ANSWERS FROM THE DATABASE
	 
	 //START THE DISPLAY
    echo("\t<TABLE WIDTH=\"$table_width\" BORDER=\"0\" ALIGN=\"center\" ID=\"a_question\" ");
    if($for_mod){
        echo("onMouseOver=\"this.className='red_bold'\" onMouseOut=\"this.className='black'\" ");
        echo("onclick=\"get_word_list_info('mod_form_" . $question_seq[0] . "');\"");
    }
    echo(">\n");
    echo("\t\t<TR><TD>\n");
    //DISPLAY THE CHOICES
    for($nc=0;$nc<$num_choices;){
        echo("\t\t<TR>\n");
        for($x=0;$x<3; $x++, $nc++){
            if($nc < $num_choices)
                echo("\t\t\t<TD WIDTH = 187>&nbsp$alphabet[$nc])$choice[$nc]</TD>\n");
            else
                echo("\t\t\t<TD WIDTH = 187>&nbsp</TD>\n");
        }
        echo("\t\t</TR>\n");
    }
    echo("\t\t</TD></TR>\n");
    //DISPLAY THE DESCRIPTION
    echo("\t\t<TR>\n");
    echo("\t\t\t<TD COLSPAN=\"3\">$question_num)" . stripslashes($description) . "</TD>\n");
    echo("\t\t</TR>\n");
    //DISPLAY THE QUESTIONS
    echo("\t\t<TR><TD>\n");
    //echo("\t\t<TABLE WIDTH=\"$table_width\" BORDER=\"1\">\n");
    for($nb=1;$nb<$num_questions;$nb++){
        echo("\t\t<TR>\n");
        echo("\t\t\t<TD COLSPAN=\"3\">\n");
        if(empty($for_mod)) echo("<B>");
        echo("\t\t\t\t<U>&nbsp");
        echo((stripslashes($solution[$nb])));
        echo(" &nbsp</U>");
        if(empty($for_mod)) echo("</B>");
        echo("\t\t\t\t" . @$question[$nb] . "\n");
        echo("\t\t\t</TD>\n");
		  echo("\t\t</TR>\n");
    }    
   // echo("\t\t</TABLE>\n");
    echo("\t\t</TD></TR>\n");
    echo("\t</TABLE>\n\t<BR>\n");
}
function display_word_list_to_mod($test_seq, $qs, $num_blanks, $num_choices){
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
    echo("\t\t\t<INPUT TYPE=\"HIDDEN\" NAME=\"num_blanks\" VALUE=\"$num_blanks\">\n");
    echo("\t\t\t<INPUT TYPE=\"HIDDEN\" NAME=\"num_choices\" VALUE=\"$num_choices\">\n");
    echo("\t\t\t<INPUT TYPE=\"HIDDEN\" NAME=\"description_seq\" VALUE=\"$ds\">\n");
    echo("\t\t\t<INPUT TYPE=\"HIDDEN\" NAME=\"question_num\" VALUE=\"$question_num\">\n");
	 echo("\t\t\t<INPUT TYPE=\"HIDDEN\" NAME=\"question_seq\" VALUE=\"$question_seq\">\n");
        
    //GET THE QUESTIONS AND ANSWERS FROM question_seq and question_solution_dtl
    $qq = "SELECT question, question_def.question_seq, solution, solution_seq, type_name " .
            "FROM question_def, question_type_def, question_solution_dtl WHERE " . 
            "question_num = $question_num AND " .
            "question_def.test_seq = $test_seq AND " . 
            "question_solution_dtl.question_seq = question_def.question_seq AND " .
            "question_def.type_seq = question_type_def.type_seq AND " .
            "question_type_def.type_name = \"word_list\" " .
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
    
	 //START THE DISPLAY
    echo("\t<TABLE WIDTH=\"$table_width\" BORDER=\"1\" ALIGN=\"center\" ID=\"a_question\" ");
    if($for_mod){
        echo("onMouseOver=\"this.className='red_bold'\" onMouseOut=\"this.className='black'\" ");
        echo("onclick=\"get_word_list_info('mod_form_" . $question_seq[0] . "');\"");
    }
    echo(">\n");
    echo("\t\t<TR><TD>\n");
    //DISPLAY THE CHOICES
    for($nc=0;$nc<$num_choices;){
        echo("\t\t<TR>\n");
        for($x=0;$x<3; $x++, $nc++){
		  		echo("\t\t\t<TD WIDTH = 187>\n");
            if($nc < sizeof($choice)){
				    $str_length = strlen($choice[$nc]);
                echo("\t\t\t\t$alphabet[$nc]) ");
					 echo("<INPUT TYPE=\"TEXT\" SIZE=\"20\" NAME=\"choice_" . $choice_seq[$nc] . "\" VALUE=\"" . stripslashes($choice[$nc]) . "\">\n");
				}
         	else if ($nc < $num_choices){
					echo("\t\t\t\t$alphabet[$nc]) ");
					echo("<INPUT TYPE=\"TEXT\" SIZE=\"20\" NAME=\"new_choice_" . $nc . "\">\n");
				}
			   else
                echo("&nbsp");
        }
		  echo("</TD>\n");
        echo("\t\t</TR>\n");
    }
    echo("\t\t</TD></TR>\n");
    //DISPLAY THE DESCRIPTION
    echo("\t\t<TR>\n");
    echo("\t\t\t<TD COLSPAN = \"3\" VALIGN=\"top\">$question_num) ");
    $str_length = strlen($description);
    $num_lines = $str_length / $num_cols + 1;
    echo("\t\t\t\t<INPUT TYPE=\"TEXT\" SIZE=\"90\" NAME=\"description\" VALUE=\"" . stripslashes($description) . "\" >\n");
    echo("\t\t\t</TD>\n");
    echo("\t\t</TR>\n"); 
    //DISPLAY THE QUESTIONS
    for($nb=0;$nb<$num_blanks;$nb++){
        echo("\t\t<TR><TD COLSPAN=\"3\">\n");
	 	  if($nb < sizeof($solution)){
            if($nb < $num_blanks){
                echo("\t\t\t<INPUT TYPE=\"TEXT\" SIZE=\"2\" NAME=\"solution_" . $solution_seq[$nb] . "\" VALUE=\"" . $solution[$nb] . "\" >\n");
            }
            else{
                echo("\t\t\t<INPUT TYPE=\"hidden\" NAME=\"solution_" . $solution_seq[$nb] . "\" VALUE=\"DELETE\" >\n");
            }
        }
        else if ($nb < $num_blanks){
            echo("\t\t\t<INPUT TYPE=\"TEXT\" size = 2 NAME=\"new_solution_" . $nb . "\">\n");
        }
		  if($nb < sizeof($question)){
            if($nb < $num_blanks){
                $str_length = strlen($question[$nb]);
                if($str_length < $num_cols) 
                    $num_cols = $str_length + 1;
                $num_lines = $str_length / $num_cols + 1;
					 echo("\t\t\t<INPUT TYPE=\"TEXT\" size = 50 NAME=\"question_" . $question_seq[$nb] . "\" VALUE=\"" . stripslashes($question[$nb]) . "\" >\n");
            }
            else{
                echo("\t\t\t\t<INPUT TYPE=\"hidden\" NAME=\"question_" . $question_seq[$nb] . "\" VALUE=\"DELETE\">\n");
            }            
           }
        else if ($nb < $num_blanks){
            echo("\t\t\t<INPUT TYPE=\"TEXT\" size = 60 NAME=\"new_question_" . $nb . "\">\n");
        }
		  
        echo("\t\t</TD></TR>\n");
    }    
    echo("\t</TABLE>\n\t<BR>\n");
}

function process_word_list_mod($_POST){
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
	 //echo("_POST");
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
	 /*
	 echo("qs<BR>");
	 view_array($qs);
	 echo("question<BR>");
	 view_array($question);
	 echo("cs<BR>");
	 view_array($cs);
	 echo("choice<BR>");
	 view_array($choice);
	 echo("ss<BR>");
	 view_array($ss);
	 echo("solution<BR>");
	 view_array($solution);
	 echo("new_choices<BR>");
	 view_array($new_choices);
	 echo("new_questions<BR>");
	 view_array($new_questions);
	 echo("new_solutions<BR>");
	 view_array($new_solutions);
    */
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
        //echo($update_q);
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
    for($i = 0; $i < sizeof($choice); $i++)        {
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
                "type_name = 'word_list' OR " .
                "type_name = 'choice' ";
	 $type_seq = mssql_query($type_seq);
    $type = mssql_fetch_array($type_seq);
	 
	 $ts_choice = $type[0];
    $type = mssql_fetch_row($type_seq);
	 $ts_question = $type[0];
    //echo("choice = $ts_choice<BR>");
	 //echo("question = $ts_question<BR>");
	 
    for($i = 0; $i < sizeof(@$new_questions); $i++){
        $insert_q    = "INSERT INTO question_def "    .
                    "(test_seq, type_seq, question_num, question) VALUES " .
                    "('$test_seq', '$ts_question', '$question_num', \"". addslashes($new_questions[$i]) . "\");";    
       // echo($insert_q . "<BR>");
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
    for($i = 0; $i < sizeof(@$new_choices); $i++)        {
        $insert_c    = "INSERT INTO question_def "    .
                "(test_seq, type_seq, question_num, question) VALUES " .
                "('$test_seq', '$ts_choice', '$question_num', \"". addslashes($new_choices[$i]) . "\");";    
       // echo($insert_c . "<BR>");
        if(!mssql_query($insert_c))  echo("ERROR in: " . $insert_c . "<BR>");
    }    
    Header("Location: ./menu_admin.php?cat=test&scr=modtest&ts=$test_seq&do=mod");    
    
}
?>