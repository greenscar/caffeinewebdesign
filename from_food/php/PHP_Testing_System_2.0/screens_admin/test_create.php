<?php
$user_type = "admin";
//require_once("../inc_files/sql_connect.inc");
require_once("../inc_files/check_session.inc");
require_once("../inc_files/question_fxns.inc");
require_once("../inc_files/display_fxns.inc");
require_once("../inc_files/insert_fxns.inc");
require_once("../inc_files/some_useful_functions.inc");
/*
* If the session is registered, the user has at least entered the header info.
* num_loads will tell how many times this page has been loaded.
* If num_loads == null, the person is here for the time; Get test header info
* If num_loads == 1, the header info has been entered. 
* 	1) Put the header info into the DB
* 	2) Display the header info
* 	3) Display the question type menu
* If num_loads == 2, the person has selected the question type of the first question.
* 	1) Display the header info
* 	2) Display all previously entered questions
* 	3) Display the form for this question to be entered.
* 	4) Display question type menu
* If num_loads > 2, the person has entered at least 1 question.
* 	1) Put the question into the database.
* 	2) Display the header info
*	3) Display all previously entered questions
* 	4) Display the form for this question to be entered.
* 	5) Display question type menu
*/
session_start();

if(session_is_registered("test") && !empty($_GET["nl"])){
	$test = $_SESSION["test"];
	$num_loads =  @$test['num_loads'];
	//echo("num_loads = " .$test['num_loads'] ."<BR>");
	echo("<FORM NAME=\"form1\" method=\"POST\" action=\"" . $_SERVER['PHP_SELF'] . "?cat=test&scr=cretest&nl=" . $num_loads . "#qtm\">\n");
	if($test['num_loads'] == 1){
		//Get header info from html form
		$name = $_POST['test_name'];
		$creator = $_POST['created_by'];
		$category = $_POST['category_name'];
		//Enter the test header info into the session
		$_SESSION['test']['test_name'] = $name;
		$_SESSION['test']['created_by'] = $creator;
		$_SESSION['test']['category_name'] = $category;
		$_SESSION['test']['display_after'] = $_POST["display_after"];
		//Put the header information into the database
		put_test_header_into_db($_SESSION['test']);
		display_header_info($_SESSION['test']);
	}
	else{	//num_loads >= 2
		//Place variable values into HTML form
		$type_seq = @$_POST["next_question_type_seq"];
		echo("<INPUT TYPE=\"HIDDEN\" NAME=\"q_type_seq\" VALUE=\"$type_seq\">");
		echo("<INPUT TYPE=\"HIDDEN\" NAME=\"num_blanks\" VALUE=\"" . @$_POST["next_num_blanks"] . "\">");
		echo("<INPUT TYPE=\"HIDDEN\" NAME=\"num_choices\" VALUE=\"" . @$_POST["next_num_choices"] . "\">");
		//Set variables to the POST data values
		$next_q_type_seq = $_POST["next_question_type_seq"];
		$next_num_choices = @$_POST["next_num_choices"];
		$next_num_blanks = @$_POST["next_num_blanks"];
		
		$test_seq = $_SESSION['test']['test_seq'];
				
		//If num_loads > 2, a question needs to be placed into the database.
		if($test['num_loads'] > 1)	{
			$not_subject_or_choice = true;
			//If this question type != subject && != choice, increment question_num
			$qtype = "SELECT type_seq FROM question_type_def WHERE type_name = \"subject\" " .
					"OR type_name = \"choice\"";
			$qtype = mssql_query($qtype);
			while($q = mssql_fetch_row($qtype)){
				if(strcmp($q[0], $type_seq) == 0){
					$not_subject_or_choice = false;
				}
			}
			if($not_subject_or_choice){
				$_SESSION['test']['question_num'] = $_SESSION['test']['question_num'] + 1;
			}
			if($test['num_loads'] > 2){
				//1) Insert question into database
				insert_question_into_db($_POST, $test);
			}
			if($_POST['next_question_type_seq'] == "End"){
				echo("</FORM>\n");
				echo("<H2 align=\"center\">Your test</H2>\n");
				display_w_solutions($test_seq, false);
				exit();
			}
		}
		//2) Display the header info
		//display_header_info($test);
		//3) Display all previously entered questions
		display_w_solutions($test_seq, false);
		//4) Display the form for this question to be entered.
		$q_num = $test["question_num"];
		display_question_form($next_q_type_seq, $q_num, $next_num_choices, $next_num_blanks);
	}
	//5) Display question type menu
	display_question_type_menu();
	echo("</FORM>\n");
	$_SESSION['test']['num_loads'] = @$test["num_loads"] + 1;
}
/*
* If the session is not registered, this is the person's first visit.
* We need to get the test header info from the person and create the session.
*/
else{
	$_SESSION['test']['question_num'] = 1;
	$_SESSION['test']['num_loads'] = 1;
	session_register("test");
	get_header_info($the_user, $_SESSION['test']['num_loads']);
}

function get_header_info($the_user, $num_loads){	
	//$title = $the_user->first_name . ", please enter your new test";
	//include("../inc_files/html_header_no_fxns.inc");
	$select_statement = "SELECT category_name FROM helpdesk.test_category_def";
	$search_results= mssql_query($select_statement) or die("Error in Query: $select_statement");  //select the username 
	$i = 1;
?>
	<div align="center">
  	<h2>Please enter your exam information:</h2>
  	<FORM NAME="form1" method="POST" action="<?$_SERVER['PHP_SELF']?>?cat=test&scr=cretest&nl=1">
  		<INPUT TYPE="hidden" NAME="question_num" VALUE = "0">
    	<table width="306" border="0"> 
      		<TR> 
        		<TD WIDTH="145">Exam Name:</TD>
        		<TD WIDTH="151"> 
          			<INPUT TYPE="text" NAME="test_name" SIZE="25" maxlength="30">
        		</TD>
      		</TR>
      		<TR> 
        		<TD WIDTH="145">Your Name:</TD>
        		<TD WIDTH="151"> 
		  		<INPUT TYPE="text" NAME="created_by" SIZE="25" maxlength="30" VALUE="<? echo($the_user->first_name . " " . $the_user->last_name) ?>">
        		</TD>
      		</TR>
      		<TR> 
        		<TD WIDTH="145">Exam Category:</TD>
        		<TD WIDTH="151"> 
					<select NAME="category_name">
<?
	while($_POST = mssql_fetch_array($search_results)) { 
		echo("\n\t\t\t\t<option VALUE=\"" . $_POST["category_name"] . "\">" . 
			$_POST["category_name"] . "</option>");
   	} 
?>
					</select>
        		</TD>
      		</TR>
			<TR> 
        		<TD WIDTH="145">Display After Taking:</TD>
        		<TD WIDTH="151"> 
		  			<input type="radio" name="display_after" value="1">Yes 
			  		<input type="radio" name="display_after" value="0">No
	    		</TD>
      		</TR>
			<TR>
				<TD COLSPAN="2" ALIGN="CENTER">
      				<INPUT TYPE="submit" NAME="Submit" VALUE="Submit">
      				<INPUT TYPE="reset" NAME="Reset" VALUE="Reset">
	  			</TD>
			</TR>
	 </table>
  </FORM>
  <P>&nbsp;</P>
  <P>&nbsp;</P>
</div>
<?	
}
/***********************************************************************************************
* FUNCTIONS
************************************************************************************************/
function put_test_header_into_db($session){
	//echo("put_test_header_into_db<BR>");
	//view_array($session);
	$cat_sel = "SELECT category_seq FROM test_category_def WHERE category_name = '" . $session["category_name"] . "'";
	$cat_sel_que = mssql_query($cat_sel) or die("Error in $cat_sel<BR>");
	$cat_sel = mssql_fetch_row($cat_sel_que);
	$category_seq = $cat_sel[0];
	$display_after = $
	$right_now = date("Y\-m\-d H\:i\:s", time());
	$insert = "INSERT INTO test_header_def VALUES (" .
				"\"" . ltrim($session["test_name"]) . "\", " . 
				"\"" . ltrim($session["created_by"]) . "\", " .
				"$category_seq , 1, " . $session["display_after"] . ", \"$right_now\")";
	if(!(@mssql_query($insert))){
		echo("Error in $insert<BR>");
	}	
	//GET test_seq FROM THE test_header_def
	$test_num = "SELECT MAX(test_seq) AS test_seq FROM test_header_def";
	$the_num = mssql_query($test_num);
	$row = mssql_fetch_array($the_num);
	$_SESSION['test']['test_seq'] = $row["test_seq"];	
}

?>