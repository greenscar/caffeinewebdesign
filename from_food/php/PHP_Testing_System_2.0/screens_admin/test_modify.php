<?php
$user_type = "admin";
require_once("../inc_files/check_session.inc");
require_once("../inc_files/display_fxns.inc");
require_once("../inc_files/some_useful_functions.inc");
if(isset($_GET["ts"])) $_POST["ts"] = $_GET["ts"];
if(isset($_GET["cop"])) $_POST["do"] = $_GET["cop"];

if(isset($_POST["ts"]) && (@$_POST["do"] == "cop")){
    /*
    * The user has selected an exam to modify and verified 
    *     this is the exam they wish to modify.
    * The exam is going to be displayed for modification purposes
    *     for the first time. 
    * 1) Check to see if the test has been taken by anyone. 
    *      If it has, goto step 2.
    *    If it has not, display the original for modifications.
    * 2) Make a duplicate of the exam in the database.
    * 3) Mark the original exam not active
    * 4) Display the copy for modifications
    */
    $old_test_seq = $_POST["ts"];
    $has_been = "SELECT * FROM test_taken_dtl WHERE test_seq = $old_test_seq";    
    $temp = mssql_query($has_been);
    $num_rows = (mssql_num_rows($temp));
    if($num_rows > 0){
        $new_test_seq = copy_test_in_db($old_test_seq);
        mark_orig_not_active($old_test_seq);
        display_test_to_modify($new_test_seq, $the_user);
    }
    else{
        display_test_to_modify($old_test_seq, $the_user);
    }
    
}
else if (isset($_GET["ts"]) && ($_GET["do"] == "mod")){
    /*
    * At least 1 portion of this test has been modified.
    * Display the copy for modifications.
    */
    display_test_to_modify($_GET["ts"], $the_user);
}
else if(isset($_GET["ts"]) && ($_GET["do"] == "ver")){
    /*
    * The user has just selected an exam to modify. 
    * Display the exam and make sure they wish to modify it.
    */
    verify_test_selection($_GET);
}
else{
    /*
    * The user has just come to the page.
    * Display a list of tests for the user to look through  
    *     and select which they wish to modify.
    */
    display_list_of_tests($the_user);
}

/*************************************************************************************
* FUNCTIONS
*************************************************************************************/

function display_test_to_modify($test_seq, $the_user){
    //test_num and test_seq are defined only for development purposes.
    include("../inc_files/question_fxns.inc");

    //GET test_name AND test_num FROM THE DATABASE BASED ON test_seq
    $test_info = "SELECT * FROM test_header_def WHERE test_seq = $test_seq";
    $test_info = mssql_query($test_info);
    $test_info = mssql_fetch_array($test_info);
    $test_name = stripslashes($test_info["test_name"]);
    
    //SET THE TITLE AND DISPLAY THE HTML HEADER
    //$title = "$test_name";
    //include("../inc_files/html_header_with_fxns.inc");
    echo("\n<FORM name=\"form1\" method=\"POST\" action=\"menu_admin.php?scr=modquest&cat=test&num=$q_num\">\n");
    echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"test_seq\" VALUE=\"$test_seq\">\n");
    echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"type_name\" VALUE=\"title\">\n");
    echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"question_num\" VALUE=\"0\">\n");
    echo("\t<DIV ALIGN=\"CENTER\">\n" .
         "\t\t<A HREF=# CLASS=\"title\" ALIGN=\"CENTER\" onClick=\"submit();\">\n" .
         "\t\t\t" .stripslashes($test_name) . "\n" .
         "\t\t</A>\n" .
         "\t</DIV>\n");
    echo("\t<HR SIZE=\"1\" WIDTH=\"620\" ALIGN=\"CENTER\">\n");
    echo("\t<P ALIGN=\"CENTER\"><BUTTON onClick=\"location.href='menu_admin.php?scr=modquest&cat=test&ts=$test_seq&qn=0'\" NAME=\"insert_here\">Insert a question or header here</BUTTON></P>\n");
    echo("</FORM>\n");
    
    //GET THE TEST FROM THE DATABASE
    $query = "SELECT * FROM question_def, question_type_def " .
             "WHERE question_def.test_seq = $test_seq AND " . 
             "question_type_def.type_name != \"choice\" AND " .
             "question_def.type_seq = question_type_def.type_seq " .
             "ORDER BY question_def.question_num, question_def.question_seq;"; 
	 $all_questions = mssql_query($query);
    $q_num =1;
    //DISPLAY ALL QUESTIONS. THE DISPLAY FORMAT WILL BE BASED ON THE type_name
    while(@$question = mssql_fetch_array($all_questions)){ 
	     //view_array($question);
        $question_seq = $question["question_seq"];
        $question_num = $question["question_num"];
        $type_seq = $question["type_seq"];
        $type_name = $question["type_name"];
        $the_question = stripslashes($question["question"]);
        $function = "display_" . $type_name . "_with_solution";
        echo("<FORM name=\"mod_form_$question_seq\" method=\"POST\" action=\"menu_admin.php?scr=modquest&cat=test\">\n");
        echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"test_seq\" VALUE=\"$test_seq\">\n");
        echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"type_seq\" VALUE=\"$type_seq\">\n");
        echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"type_name\" VALUE=\"$type_name\">\n");
        echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"question_seq\" VALUE=\"$question_seq\">\n");
        echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"question_num\" VALUE=\"$question_num\">\n");
        if($type_name == "subject"){
            //echo("\t<DIV ALIGN=\"CENTER\"><A CLASS=\"subject\" ALIGN=\"CENTER\" HREF=\"menu_admin.php?scr=modquest&cat=test?ts=$test_seq&tm=subject&qs=$question_seq\">" . stripslashes($the_question) . "</A></DIV>\n");
            echo("\t<DIV ALIGN=\"CENTER\"><A HREF=# CLASS=\"subject\" ALIGN=\"CENTER\" onClick=\"submit();\">" . stripslashes($the_question) . "</A></DIV>\n");
        }
        else{
            $for_mod = true;
            if($type_name == "fill_in_blank"){
                $num = "SELECT solution_seq FROM question_solution_dtl " .
                    "WHERE question_seq = $question_seq";
                $num_blanks = mssql_num_rows(mssql_query($num));
                echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"num_blanks\" VALUE=\"$num_blanks\">\n");
            }
            else if(($type_name == "matching") || ($type_name == "word_list")){
                $num_questions = "SELECT question_seq, type_name FROM " .
                                "question_def, question_type_def WHERE " .
                                "question_num = $question_num AND " .
                                "question_type_def.type_name = \"$type_name\" AND " .
                                "question_def.type_seq = question_type_def.type_seq AND " . 
                                "question_def.test_seq = $test_seq";
                //echo($num_questions. "<BR>");
                $num_questions = mssql_num_rows(mssql_query($num_questions));
                
                $num_choices = "SELECT question_seq, question_type_def.type_name FROM " .
                                "question_def, question_type_def  WHERE " .
                                "question_num = $question_num AND " .
                                "question_type_def.type_name = \"choice\" AND " .
                                "question_def.type_seq = question_type_def.type_seq AND " . 
                                "question_def.test_seq = $test_seq";
                //echo($num_choices. "<BR>");
                $num_choices = mssql_num_rows(mssql_query($num_choices));
                $num_questions = $num_questions - 1;
                echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"num_blanks\" VALUE=\"$num_questions\">\n");
                echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"num_choices\" VALUE=\"$num_choices\">\n");
            }
            call_user_func($function, $question, $all_questions, $for_mod, $q_num);
            $q_num++;
        }
        $il = $question_num+1;
        echo("\t<P ALIGN=\"CENTER\"><BUTTON onClick=\"location.href='menu_admin.php?scr=modquest&cat=test&ts=$test_seq&qn=$il'\" NAME=\"insert_here\">Insert a question or header here</BUTTON></P>\n");
        echo("</FORM>\n");
    }
    //include("../inc_files/html_footer.inc");
}
function copy_test_in_db($test_seq){
    /******************************
    * COPY THE HEADER
    ******************************/
    //GET THE HEADER FROM THE DB
    $h_fetch = "SELECT * FROM test_header_def WHERE test_seq = $test_seq";
    $header = mssql_query($h_fetch);
    $header = mssql_fetch_row($header);
    //PLACE THE HEADER INTO THE DB
    $right_now = date("Y\-m\-d H\:i\:s", time());
    $insert = "INSERT INTO test_header_def VALUES (" .
                "\"" . $header[1] . "\", " . 
                "\"" . $header[2] . "\", " .
                "$header[3] , 1, \"$right_now\", " . $header[6] . ")";
    if(!(@mssql_query($insert))){
        echo("Error in $insert<BR>");
    }    
    //GET new_test_seq FROM THE test_header_def
    $test_num = "SELECT MAX(test_seq) AS test_seq FROM test_header_def";
    $the_num = mssql_query($test_num);
    $row = mssql_fetch_array($the_num);
    $new_test_seq = $row["test_seq"];
    /******************************
    * END COPY THE HEADER
    ******************************/
    
    /*********************************
    * COPY THE QUESTIONS AND ANSWERS *
    **********************************/
    $q_fetch = "SELECT * FROM question_def WHERE test_seq = $test_seq ORDER BY question_seq";
    $question = mssql_query($q_fetch);
    while($a_question = mssql_fetch_row($question)){
        //FETCH THE question_seq OF THE ORIGINAL LOCATION
        $orig_question_seq = $a_question[0];
        //INSERT THE QUESTION INTO THE DB.
        $insert = "INSERT INTO question_def " .
                "(test_seq, type_seq, question_num, question) " .
                "VALUES " . 
                "('$new_test_seq', '$a_question[2]', '$a_question[3]', \"$a_question[4]\")";
        if(!(@mssql_query($insert)))
            echo("error in: <br>$insert<br>");    
        //FETCH THE question_seq OF THE NEW LOCATION
        $q_seq_search = "SELECT question_seq FROM question_def " .
                    "WHERE test_seq = $new_test_seq " .
                    "AND type_seq = $a_question[2] " .
                    "AND question_num = $a_question[3] " .
                    "AND question LIKE \"$a_question[4]\"";
        $q_seq = mssql_query($q_seq_search) or die("ERROR IN LINE 91: $q_seq_search<BR>");
        $q_seq = mssql_fetch_row($q_seq);
        $q_seq = $q_seq[0];
        
        //FETCH THE solution OF THE QUESTION IF THERE IS ONE.
        $solution_q = "SELECT solution FROM question_solution_dtl WHERE question_seq = $orig_question_seq";
        $solution = mssql_query($solution_q) or die("ERROR IN LINE 98: $solution_q<br>");
        while($a_solution = mssql_fetch_row($solution)){
            $a_solution = $a_solution[0];
            if(!empty($a_solution)){
                $insert = "INSERT INTO question_solution_dtl " .
                        "(question_seq, test_seq, solution) " .
                        "VALUES " .
                        "('$q_seq', '$new_test_seq', \"$a_solution\")";
                if(!(@mssql_query($insert)))
                    echo("error in: <br>$insert<br>");    
            }
        }        
    }
    /*************************************
    * END COPY THE QUESTIONS AND ANSWERS *
    *************************************/
    return $new_test_seq;
}

function mark_orig_not_active($test_seq){
    $update = "UPDATE test_header_def SET active = 0 WHERE test_seq = $test_seq";
    if(!mssql_query($update)) die("Error in $update<BR> in display_test_to_modify.php line 19<BR>");
}

function verify_test_selection($_GET){
    //$title = "Are you sure this is the test you wish to modify?";
    $test_seq = $_GET["ts"];
    //include("../inc_files/html_header_no_fxns.inc");
    echo("<H2 class=\"red\">Are you sure you want to modify this test?</H2>\n");
    require_once("../inc_files/display_fxns.inc");
    display_w_solutions($test_seq, false);
    echo("\t<FORM name=\"form1\" method=\"POST\" action=\"" . $_SERVER['PHP_SELF'] . "?cat=test&scr=modtest\">\n");
    echo("\t\t\t\t<P ALIGN=\"CENTER\">\n");
    echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"ts\" VALUE=\"$test_seq\">\n");
    echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"do\" VALUE=\"cop\">\n");
    echo("\t<INPUT TYPE=\"HIDDEN\" NAME=\"title\" VALUE=\"Click on any question or header you wish to change.\">\n");
    echo("\t\t\t\t<INPUT TYPE=\"BUTTON\" VALUE=\"YES\" onClick=\"submit();\">\n");
    echo("\t\t\t\t&nbsp&nbsp&nbsp\n");
    echo("\t\t\t\t<INPUT TYPE=\"BUTTON\" VALUE=\"NO\" onClick=\"location.href='./menu_admin.php?cat=test&scr=modtest';\">\n");
    echo("\t\t\t\t</P>\n");
    echo("\t</FORM>\n");
    echo("</BODY></HTML>");
}


function display_list_of_tests($the_user){
    //$title = "Welcome, " . $the_user->first_name . ". Select the test you wish to modify.";
    //include("../inc_files/html_header_no_fxns.inc");
    echo("\t<TABLE ALIGN=\"CENTER\" border=\"0\">\n");
    echo("\t\t<TR><TD ALIGN=\"CENTER\">\n");
    echo("\t\t\t<H2 ALIGN = \"CENTER\"> Please select an exam to modify.</H2>\n");
    echo("\t\t</TD></TR>\n");
    echo("\t\t<TR><TD ALIGN=\"CENTER\">\n");
    $tests = "SELECT created_by, test_name, category_name, creation_date, test_seq " .
             "FROM test_header_def, test_category_def " .
             "WHERE test_header_def.active = 1 " .
             "AND test_header_def.category_seq = test_category_def.category_seq " .
             "ORDER BY test_seq";
    $search_results = mssql_query($tests) or die("Error in Query: $tests<BR>");  //select the username 
    echo("\t\t\t<TABLE ALIGN=\"CENTER\" BORDER=\"5\" CELLPADDING=\"4\">\n");
    echo("\t\t\t\t<TR>\n");
    echo("\t\t\t\t\t<TH>NAME</TH>\n");
    echo("\t\t\t\t\t<TH>CREATOR</TH>\n");
    echo("\t\t\t\t\t<TH>CATEGORY</TH>\n");
    echo("\t\t\t\t\t<TH>CREATION DATE</TH>\n");
    echo("\t\t\t\t</TR>\n");
    while($temp = mssql_fetch_array($search_results)){
        $ts = $temp["test_seq"];
        echo("\t\t\t\t<TR>\n");
        echo("\t\t\t\t\t<TD>\n");
        echo("\t\t\t\t\t\t<A CLASS=\"test_list\" HREF=\"" . $_SERVER['PHP_SELF'] . "?cat=test&scr=modtest&ts=$ts&do=ver\">\n");
        echo("\t\t\t\t\t\t\t" . stripslashes($temp["test_name"]) . "\n");
        echo("\t\t\t\t\t\t</A>\n");
        echo("\t\t\t\t\t</TD>\n");
        echo("\t\t\t\t\t<TD>\n");
        echo("\t\t\t\t\t\t<A CLASS=\"test_list\" HREF=\"" . $_SERVER['PHP_SELF'] . "?cat=test&scr=modtest&ts=$ts&do=ver\">\n");
        echo("\t\t\t\t\t\t\t" . stripslashes($temp["created_by"]) . "\n");
        echo("\t\t\t\t\t\t</A>\n");
        echo("\t\t\t\t\t</TD>\n");
        echo("\t\t\t\t\t<TD>\n");
        echo("\t\t\t\t\t\t<A CLASS=\"test_list\" HREF=\"" . $_SERVER['PHP_SELF'] . "?cat=test&scr=modtest&ts=$ts&do=ver\">\n");
        echo("\t\t\t\t\t\t\t" . stripslashes($temp["category_name"]) . "\n");
        echo("\t\t\t\t\t\t</A>\n");
        echo("\t\t\t\t\t</TD>\n");
        echo("\t\t\t\t\t<TD>\n");
        echo("\t\t\t\t\t\t<A CLASS=\"test_list\" HREF=\"" . $_SERVER['PHP_SELF'] . "?cat=test&scr=modtest&ts=$ts&do=ver\">\n");
        echo("\t\t\t\t\t\t\t" . stripslashes($temp["creation_date"]) . "\n");
        echo("\t\t\t\t\t\t</A>\n");
        echo("\t\t\t\t\t</TD>\n");
        echo("\t\t\t\t</TR>\n");
    }
    echo("\t\t\t</TABLE>\n");
    echo("\t\t</TD></TR>\n");
    echo("\t</TABLE>\n");
}
function insert_question_into_db($_POST, $test){
    require_once("../inc_files/some_useful_functions.inc");
    $type_seq = $_POST["q_type_seq"];
    //INSERT INFORMATION INTO VARIABLES.
    $description = @$_POST["description"];
    $num_blanks = $_POST["num_blanks"];
    $num_choices = $_POST["num_choices"];
    $question_num = $test["question_num"] - 1;    
    if($question_num == 0) $question_num = 1;
    $test_seq = $test["test_seq"];
    $t_seq = "SELECT type_seq FROM question_type_def WHERE type_name = \"matching\"";
    $t_seq = mssql_query($t_seq);
    $t_seq = mssql_fetch_array($t_seq);
    $matching_seq = $t_seq["type_seq"];
    if($_POST["q_type_seq"] == $matching_seq){
        $matching_subject = @$_POST["subject"];
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
    *     table don't enter anythign into the database.
    * This is done to ensure the user doesn't hit the refresh button and reenter the same question
    *     twice.
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
    if(isset($matching_subject)){
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