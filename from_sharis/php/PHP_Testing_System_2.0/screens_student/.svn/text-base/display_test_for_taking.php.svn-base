<?php
    $user_type = "student";
/******************************************************************************************
* AUTHOR: James Sandlin
* DATE: 09/06/02
* FILE: display_test_for_taking.php
* PACKAGE: Training
* FUNCTION: This file will display a test in HTML format for taking.
*             Questions of types can be asked:
*                     Multiple Choice (4 choices)
*                     True/False
*                     Matching
*                     Fill in the blank
*                     Listing
*                     Short Answer
*                     Essay
*             The results of the test will be forwarded to process_test_taken.php
*             The form variables will be numbered based on the question_seq of the 
*                 question being asked so when the data is inserted into the 
*                 question_student_answer_dtl, the question_seq will be available
*                 by looking at the form variable names.
* REQUIRES: The value of $test_seq will be provided by the page calling this page.
* 
*******************************************************************************************/
    //test_num and test_seq are defined only for development purposes.
    $test_seq = $_POST["test_seq"];
    require_once("../inc_files/check_session.inc");
    require_once("../inc_files/sql_connect.inc");
    require_once("../inc_files/question_fxns.inc");
    //GET test_name AND test_num FROM THE DATABASE BASED ON test_seq
    $test_info = "SELECT * FROM test_header_def WHERE test_seq = $test_seq";
    $test_info = mssql_query($test_info);
    $test_info = mssql_fetch_array($test_info);
    $test_name = $test_info["test_name"];
    
    //SET THE TITLE AND DISPLAY THE HTML HEADER
    $title = "Good luck on $test_name, " . $the_user->first_name;
    require_once("../inc_files/html_header_with_fxns.inc");
    
    //START THE HTML CODE DISPLAYING THE TEST NAME & FORM
    echo("<h1 align=\"center\">" . stripslashes($test_name) . "</H1>");
    echo("<HR SIZE=\"1\" WIDTH=\"80%\" ALIGN=\"CENTER\">");
    echo("<FORM ACTION = \"process_taken_test.php\" METHOD = \"POST\">\n");
    echo("\n\t<INPUT TYPE=\"hidden\" NAME=\"test_seq\" VALUE = \"$test_seq\">");
    echo("\n\t<INPUT TYPE=\"hidden\" NAME=\"emp_seq\" VALUE = \"" . $the_user->emp_seq . "\">");
    
    //GET THE TEST FROM THE DATABASE
    $query = "SELECT * FROM question_def, question_type_def " .
                 "WHERE question_def.test_seq = $test_seq AND " .
                  "question_type_def.type_name != \"choice\" AND " .
                 "question_def.type_seq = question_type_def.type_seq " .
                 "ORDER BY question_def.question_num, question_def.question_seq;";
    $all_questions = mssql_query($query);
    $q_num = 1;
    //DISPLAY ALL QUESTIONS. THE DISPLAY FORMAT WILL BE BASED ON THE type_name
    while(@$this_question = mssql_fetch_array($all_questions)){    
        $question_seq = $this_question["question_seq"];
        $type_name = $this_question["type_name"];
		  $question_num = $this_question["question_num"];
        /*
        * IF THE type_name IS subject, THE question IS NOT A QUESTION BUT A SECTION HEADER, 
        *     SUCH AS: "True/False - 5 points each"
        */
        if($type_name == "subject"){
            echo("<h4 align=\"center\">" . stripslashes($this_question["question"]) . "</h4>\n");
        }
        
        else if ($type_name != "choice"){
            $function = "display_" . $type_name . "_for_taking";
            call_user_func($function, $q_num, $this_question, $all_questions);
            $q_num++;
        }
        
    }
    
    //DISPLAY THE FORM FOOTER AND HTML FOOTER
    echo("<INPUT TYPE=\"hidden\" name=\"start_time\" value=\"" . date("Y\-m\-d H\:i\:s", time()) . "\">\n");
    echo("<TABLE ALIGN=\"center\">");
    echo("\n\t<TR>\n\t\t<TD colspan=\"2\">\n");
    echo("\t\t\t<INPUT TYPE=\"reset\" NAME=\"Clear\" VALUE=\"Clear\">\n");
    echo("\t\t\t<INPUT TYPE=\"submit\" NAME=\"Submit\" VALUE=\"Submit\">\n");
    echo("\t\t</TD>\n\t</TR>\n</TABLE>\n");
    echo("</FORM>\n");
    echo("</BODY>\n");
    echo("</HTML>\n");
    //require_once("../inc_files/html_footer.inc");
    
?>
