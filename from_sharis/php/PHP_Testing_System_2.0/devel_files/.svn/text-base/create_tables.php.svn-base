<?
require("../inc_files/variables.inc");
require("../inc_files/sql_connect.inc");
$title = "Creating tables";
require("../inc_files/html_header.inc");
require("./create_course_tables.php");
createTables();
fillTables();
echo("<P ALIGN=CENTER><INPUT TYPE=\"BUTTON\" VALUE=\"display_all_tables\" onClick=\"window.location='display_all_tables.php'\"><p>\n");
require("../inc_files/html_footer.inc");
function createTables(){
	create_emp_def();
	create_emp_sec_lvl_def();
	create_question_def();
	create_question_solution_dtl();
	create_question_student_answer_dtl();
	create_question_type_def();
	create_test_category_def();
	create_test_header_def();
	create_test_taken_dtl();
	create_sec_lvl_def();
}
function fillTables(){
	fill_emp_def();
	fill_question_type_def();
	fill_emp_category_def();
	fill_sec_lvl_def();
	fill_test_header_def();
	fill_question_def();
}

/*
* CREATE QUESTION TABLES
* question_choice_dtl
* question_def
* question_type_def
* question_solution_dtl
* question_student_answer_def
*/
function create_question_def(){
	$dropIt = "DROP TABLE question_def";
	@mssql_query($dropIt);
	$createQuestions = "CREATE TABLE question_def ( " .
 				"question_seq INTEGER IDENTITY (1,1), " .
 				"test_seq INTEGER, " .
 				"type_seq INT, " .
 				"question_num INT, " .
				"question TEXT NULL)";
	if(!@mssql_query($createQuestions)){
		echo("<p>Error creating question_def table:</p><h3>$createQuestions</h3>");
	}
	else echo("question_def created<br>");
}
function create_question_type_def(){
	$dropIt = "DROP TABLE question_type_def";
	@mssql_query($dropIt);
	$createIt = "CREATE TABLE question_type_def ( " .
			"type_seq INTEGER IDENTITY (1,1), " .
			"type_name VARCHAR (20) null)";
	if(!@mssql_query($createIt)) echo("<P>Error creating question_type_def</P>");
	else echo("question_type_def created<br>");
}

function create_question_solution_dtl(){
	$dropIt = "DROP TABLE question_solution_dtl";
	@@mssql_query($dropIt);
 	$createSolution = "CREATE TABLE question_solution_dtl ( " .
 				"solution_seq INTEGER IDENTITY (1,1), " .
 				"question_seq INT, " .
 				"test_seq INT, " .
 				"solution TEXT)";
 	if(!@mssql_query($createSolution)) echo("<P>Error creating question_solution_dtl</P>");
	else echo("question_solution_dtl created<br>");
}
function create_question_student_answer_dtl(){
	$dropIt = "DROP TABLE question_student_answer_dtl";
	$dropIt = "DROP TABLE student_answer_dtl";
	@@mssql_query($dropIt);
 	$createAns = "CREATE TABLE student_answer_dtl ( " .
				"answer_seq INTEGER IDENTITY (1,1)," .
				"take_seq INT, " .
				"question_seq INT, " .
				"answer TEXT, " .
				"correct BIT default NULL)";
 	if(!@@mssql_query($createAns))echo("<P>Error creating student_answer_dtl table</P>");
	else echo("student_answer_dtl created<br>");
}
/*
* CREATE EMPLOYEE TABLES
* emp_def
* emp_sec_lvl_def
*/

function create_emp_def(){
	$drop = "DROP TABLE emp_def";
	@@mssql_query($drop);
 	$createTeachers = "CREATE TABLE emp_def ( " .
			"emp_seq INTEGER IDENTITY (1,1)," .
			"emp_num INTEGER NOT NULL, " .
			"ssn INTEGER NOT NULL, " .
			"first_name VARCHAR(25), " .
			"last_name VARCHAR(25), " .
			"email VARCHAR(30), " . 
			"sec_lvl_seq INT, " . 
			"active BIT default 1)";
 	if (!@mssql_query($createTeachers) ) echo("<P>Error creating emp_def table</P>");
	else echo("emp_def created<br>");
}

function create_emp_sec_lvl_def(){
 	$dropIt = "DROP TABLE emp_sec_lvl_def";
	@@mssql_query($dropIt);
	$createSecLvl = "CREATE TABLE emp_sec_lvl_def ( " .
					"sec_lvl_seq INTEGER IDENTITY (1,1), " .
					"sec_lvl VARCHAR (30), " .
					"sec_lvl_explanation VARCHAR (100))";
	if(!@mssql_query($createSecLvl))echo("<P>Error creating emp_sec_lvl_def table</P>");
	else echo("emp_sec_lvl_def created<br>");
}	

/*
* CREATE TEST TABLES
* test_category_def
* test_header_def
* test_taken_dtl
*/
function create_test_category_def(){
	$dropIt = "DROP TABLE test_category_def";
	@mssql_query($dropIt);
	$createDef= "CREATE TABLE test_category_def (" .
			    "category_seq INTEGER IDENTITY (1,1), " .
				"category_name VARCHAR(15))";
	if(!@mssql_query($createDef)) echo("<P>Error creating test_category_def</P>");
	else echo("test_category_def created<br>");
}
function create_test_header_def(){
	$dropIt = "DROP TABLE test_header_def";
	@mssql_query($dropIt);
	$createHeaders = "CREATE TABLE test_header_def ( " .
			"test_seq INTEGER IDENTITY (1,1), " .
			"test_name VARCHAR(40) NOT NULL, " .
 			"created_by VARCHAR(30), " .
			"category_seq INTEGER, " .
			"active BIT default 1, " .
 			"creation_date DATETIME, " .
			"display_after BIT default 1)";
	if(!@mssql_query($createHeaders)) echo("<P>Error creating test_header_def</P>");
	else echo("test_header_def created<br>");
}
function create_test_taken_dtl(){
 	$drop = "DROP TABLE test_taken_dtl";
 	@@mssql_query($drop);
 	$createTaken = "CREATE TABLE test_taken_dtl ( " .
				"take_seq INTEGER IDENTITY (1,1)," .
				"test_seq INT, " .
				"emp_seq INT, " .
				"date_taken DATETIME, " .
				"duration_secs INT)";
 	if(!@mssql_query($createTaken))echo("<P>Error creating test_taken_dtl table</P>");
	else echo("test_taken_dtl created<br>");
}

function create_sec_lvl_def(){
 	$drop = "DROP TABLE sec_lvl_def";
 	@@mssql_query($drop);
 	$createSecLvl = "CREATE TABLE sec_lvl_def ( " .
				"sec_lvl_seq INTEGER IDENTITY (1,1)," .
				"sec_lvl VARCHAR(20), " .
				"sec_lvl_explanation VARCHAR(100))";
 	if(!@mssql_query($createSecLvl))echo("<P>Error creating sec_lvl_def table</P>");
	else echo("sec_lvl_def created<br>");
}
/*
function create_course_dtl(){
 	$dropIt = "DROP TABLE course_dtl";
	@@mssql_query($dropIt);
	$createCourse = "CREATE TABLE course_dtl ( " .
					"course_seq INTEGER IDENTITY (1,1), " .
					"course_name VARCHAR (30), " .
					"subject VARCHAR (30), " .
					"level INT, " . 
					"teacher_seq INT)";
	if(!@mssql_query($createCourse))echo("<P>Error creating course_dtl table</P>");
	else echo("course_dtl created<br>");
}	
*/	
/*
function fill_emp_def(){
 	echo("<h1>fill_emp_def</h1>");
	$names = array("Bob", "Dick", "Frank", "Joe", "Bubba", "Jr.", "Pappa", "Charlie", "Eloise", "Ouida", "Boudreaux", "Thibideaux");
	$email = "sandlinjames@yahoo.com";
	$sec_lvl = array(3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 2);
	for($i = 1; $i < 11; $i++){
		$lastName = (count($names) - $i);
		$insert = "INSERT INTO emp_def " .
				"(emp_num, ssn, first_name, last_name, email, sec_lvl_seq, active) " .
				"VALUES ('$i', '" . (1000 + $i) . "', '" . $names[$i] . "','" . 
				$names[$lastName] . "', '" . $email . "','"  . $sec_lvl[$i] . "', 1);";
		//echo($insert . "<BR>");
		if (!(@mssql_query($insert))){
					echo("unable to insert into emp_def<br>");
		}
		else echo("fill_emp_def $i of 10 completed.<br> ");
	}
}
*/
function fill_emp_def(){
 	echo("<h1>fill_emp_def</h1>");
	$names = array("Bob", "Dick", "Frank", "Joe", "Bubba", "Jr.", "Pappa", "Charlie", "Eloise", "Ouida", "Boudreaux", "Thibideaux");
	$email = "jsandlin@sharis.com";
	$insert = "INSERT INTO emp_def " .
				"(emp_num, ssn, first_name, last_name, email, sec_lvl_seq, active) " .
				"VALUES ('6669', '6669', 'James'," . 
				"'Sandlin', '" . $email . "','3', 1);";
	if (!(mssql_query($insert))){
		echo("unable to insert into emp_def<br>");
	}
	else echo("fill_emp_def $i of 10 completed.<br> ");
}
function fill_question_type_def(){
	echo("<h1>fill_question_type_def</h1>");
	$test_types = array("subject", "multiple_choice", "true_false", "matching", "fill_in_blank", "short_answer", "essay", "choice", "word_bank");
	for($i = 0; $i < 8; $i++){
		$insert = "INSERT INTO question_type_def " .
				"(type_name) VALUES " .
				"('" . $test_types[$i] . "');";
		if(!(@mssql_query($insert)))
			echo("unable to insert into question_type_def<br>");
		else 
			echo("fill_question_type_def " .($i+1)." of 5 completed.<br> ");
	}
}

function fill_test_header_def(){
	echo("<h1>fill_test_header_def</h1>");
	$temp = date("Y\-m\-d H\:i\:s", time());
	
	$insert = "INSERT INTO test_header_def " .
			"(test_name, created_by, creation_date, category_seq)" . 
			" VALUES " . 
			"('Breakfast Menu Knowledge Test', 'Susie Baker', '$temp', '2');";	
	if(!(@@mssql_query($insert)))
		echo("unable to insert into test_header_def<br>$insert<br>");
	else 
		echo("fill_test_header_def 1 of 2 completed.<br> ");
	/*
	$insert = "INSERT INTO test_header_def " .
			"(test_name, created_by, creation_date, category_seq)" . 
			" VALUES " . 
			"('Susie Bakers Test', 'Susie Baker', '$temp', '2');";	
	if(!(@@mssql_query($insert)))
		echo("unable to insert into test_header_def<br>$insert<br>");
	else 
		echo("fill_test_header_def 1 of 2 completed.<br> ");
	$insert = "INSERT INTO test_header_def SET " .
			"test_name = 'Bubba Joe Bob Dick Frank Dwayne', " . 
			"created_by = 'Bubba Joe Bob', " .
			"creation_date = '" . $temp . "', " . 
			"category_seq = '1';";
	$insert = "INSERT INTO test_header_def " .
			"(test_name, created_by, creation_date, category_seq)" . 
			" VALUES " . 
			"('General Manager Training', 'Bob Barker', '$temp', '1');"; 
	if(!(@@mssql_query($insert)))
		echo("unable to insert into test_header_def<br>$insert<br>");
	else echo("fill_test_header_def 2 of 2 completed. <br>");
	**/
}
function fill_emp_category_def(){
	echo("<h1>fillCategoryTable</h1>");
	$categories = array("Manager", "Cook", "DM");
	for($i = 0; $i < 3; $i++){
		$insert = "INSERT INTO test_category_def " .
				"(category_name) VALUES ('" . $categories[$i] . "')";
		if(!(@@mssql_query($insert)))
			echo("unable to insert into test_category_def<br>");
		else 
			echo("fillCategoryTable part " .($i+1)." of 3 completed.<br> ");
	}
}
function fill_question_def(){
	echo("<h1>fill_question_def</h1>");
	$question_type[0] = 0;
	for($i=1; $i<11; $i++){
		$question_type[$i] = 2;
	}
	$question_type[11] = 0;
	for($i=12; $i<19; $i++){
		$question_type[$i] = 3;
	}
	$question_type[19] = 0;
	for($i=20; $i<28; $i++){
		$question_type[$i] = 1;
	}
	$question_type[28] = 0;
	for($i=29; $i<40; $i++){
		$question_type[$i] = 5;
	}
	$question_type[40] = 0;
	for($i=41; $i<48; $i++){
		$question_type[$i] = 4;
	}

	/*******************************************************
	* TRUE FALSE
	********************************************************/
	$q[0] = "True/FALSE - 5 points each";
	$q[1] = "Skillet breakfasts receive a choice of toast, pancakes, or French toast.";
	$q[2] = "All griddle breakfasts receive 2 sausage patties and 2 strips of bacon.";
	$q[3] = "The guest may replace the steak on the New York Steak and Eggs Platter with prime rib.";
	$q[4] = "Oatmeal is served with brown sugar and raisins.";
	$q[5] = "Shari's omelettes and platters are prepared with three eggs.";
	$q[6] = "A Western Scramble is topped with guacamole.";
	$q[7] = "The only skillet breakfast that is not topped with cheese is the Meat Lover's Skillet.";
	$q[8] = "Eggs benedict is served automatically with Yukon Gold potatoes.";
	$q[9] = "If a guest adds bacon to the waffle order, they receive two slices.";
	$q[10] = "Except for the omelette, honored breakfasts are prepared with one egg.";
	$a[0] = "True/FALSE - 5 points each"; 
	$a[1] = "TRUE";
	$a[2] = "FALSE";
	$a[3] = "TRUE";
	$a[4] = "TRUE";
	$a[5] = "TRUE";
	$a[6] = "FALSE";
	$a[7] = "FALSE";
	$a[8] = "TRUE";
	$a[9] = "FALSE";
	$a[10] = "TRUE";
	
	$topic = $q[0];
	$topic    = "INSERT INTO question_def "	.
				"(test_seq, type_seq, question_num, question) VALUES " .
				"('1', '" . 1 . "', '0', \"". addslashes($topic) . "\");";		
	//echo(htmlspecialchars($topic) . "<BR>");
	if(!@mssql_query($topic)){
		echo("ERROR IN <BR>" . htmlspecialchars($question) ."<br>");
	}		
		
	for($i=1; $i<11; $i++){
		$question = $q[$i];
		$answer = $a[$i];
		$question = "INSERT INTO question_def "	.
					"(test_seq, type_seq, question_num, question) VALUES " .
					"('1', '3', '$i', \"". addslashes($question) . "\");";	
		//echo(htmlspecialchars($question) . "<BR>");
		if(!@mssql_query($question)){
			echo("ERROR IN <BR>" . htmlspecialchars($question) ."<br>");
		}		
		
		/*
		* GET THE question_seq OF THE LAST ENTERED QUESTION
		*/
		$question_seq = "SELECT MAX(question_seq) AS question_seq FROM question_def";
		$the_num = @mssql_query($question_seq);
		$row = mssql_fetch_array($the_num);
		$question_seq = $row["question_seq"];
		/*
		* INSERT THE SOLUTION TO THE QUESTION JUST ENTERED
		*/
		$solution = "INSERT INTO question_solution_dtl " .
					"(question_seq, test_seq, solution) VALUES " .
					"('$question_seq', '1', \"" . addslashes($answer) . "\")";
		echo(htmlspecialchars($solution) . "<BR>");
		if(!@mssql_query($solution)){
			echo("ERROR IN <BR>" . htmlspecialchars($solution) ."<br>");
		}				
	}
	echo("fill_question_def TRUE/FALSE completed.<br> ");
	/*******************************************************
	* MATCHING
	********************************************************/
	$q[11] = "Match the cheese to the omelette.";
	$q[12] = "California Omelette";
	$q[13] = "Denver Omelette";
	$q[14] = "BMP Omelette";
	$q[15] = "Wisconsin Omelette";
	$q[16] = "Classic Omelette";
	$q[17] = "Veggie Omelette";
	$q[18] = "Fajita Steak Omelette";
	
	$c[0] = "Cheddar";
	$c[1] = "Swiss";
	$c[2] = "Pepper Jack";
	$c[3] = "All of the above";
	$c[4] = "None";
	
	$a[11] = "Match the cheese to the omelette.";
	$a[12] = "C";
	$a[13] = "A";
	$a[14] = "C";
	$a[15] = "D";
	$a[16] = "A";
	$a[17] = "B";
	$a[18] = "C";
	/*
	$q = 7;
	$c = 5;
	$a = 7;
	*/
	$topic = $q[11];
	$topic    = "INSERT INTO question_def "	.
				"(test_seq, type_seq, question_num, question) VALUES " .
				"('1', '" . 4 . "', '11', \"". addslashes($topic) . "\");";		
	//echo(htmlspecialchars($topic) . "<BR>");
	if(!@mssql_query($topic)){
		echo("ERROR IN <BR>" . htmlspecialchars($question) ."<br>");
	}		
	
	for($i=12; $i<19; $i++){
		$question = $q[$i];
		$answer = $a[$i];
		$question = "INSERT INTO question_def "	.
					"(test_seq, type_seq, question_num, question) VALUES " .
					"('1', '" . 4 . "', '11', \"". addslashes($question) . "\");";		
		//echo(htmlspecialchars($question) . "<BR>");
		if(!@mssql_query($question)){
			echo("ERROR IN <BR>" . htmlspecialchars($question) ."<br>");
		}		
		$question_seq = "SELECT MAX(question_seq) AS question_seq FROM question_def";
		$the_num = @mssql_query($question_seq);
		//echo($question_seq . "<BR>");
		$row = mssql_fetch_array($the_num);
		$question_seq = $row["question_seq"];
		$solution = "INSERT INTO question_solution_dtl " .
					"(question_seq, test_seq, solution) VALUES " .
					"('$question_seq', '1', \"" . addslashes($answer) . "\")";
		echo(htmlspecialchars($solution) . "<BR>");
		if(!@mssql_query($solution)){
			echo("ERROR IN <BR>" . htmlspecialchars($solution) ."<br>");
		}				
	}
	
	for($i=0; $i < 5; $i++){
		$choice = $c[$i];
		$choice =  "INSERT INTO question_def "	.
					"(test_seq, type_seq, question_num, question) VALUES " .
					"('1', '" . 8 . "', '11', \"". addslashes($choice) . "\");";		
		//echo(htmlspecialchars($choice) . "<BR>");
		if(!@mssql_query($choice)){
			echo("ERROR IN <BR>" . htmlspecialchars($question) ."<br>");
		}		
	}
	
	echo("fill_question_def matching completed.<br> ");
	/*******************************************************
	* MULTIPLE CHOICE
	********************************************************/
	$q[19] = "Select the correct answer - 5 points each";
	$q[20] = "The ingredients in a Denver omelette are:";
	$c[20][1] = "Ham, peppers, onion, & cheddar cheese";
	$c[20][2] = "Bay shrimp, peppers & cheddar.";
	$c[20][3] = "Bay Shrimp, green onion, and pepper jack cheese";
	$c[20][4] = "Bay Shrimp, Swiss cheese, tomatoes, and guacamole";
	$q[21] = "The toast choices that you can offer to a guest are:";
	$c[21][1] = "White, dark rye, poppy, wheat, and bagels.";
	$c[21][2] = "White, rye, wheat, muffins, and sourdough";
	$c[21][3] = "White, wheat, sourdough, marble rye, english muffin, or biscuit.";
	$c[21][4] = "White, wheat, rye, poppy seed, sourdough, bagel, and English muffin.";
	$q[22] = "The juices offered on Shari's menu are:";
	$c[22][1] = "Orange, grapefruit, tomato, and apple";
	$c[22][2] = "Orange, grapefruit, cranberry, apple, and tomato";
	$c[22][3] = "Orange, grapefruit, V8, and cranberry";
	$c[22][4] = "Orange, grapefruit, apple, and cranberry";
	$q[23] = "What types of pancakes are served at Shari's?";
	$c[23][1] = "Blueberry, apple, buttermilk, and buckwheat";
	$c[23][2] = "Buttermilk, strawberry, blueberry, and buckwheat";
	$c[23][3] = "Blueberry, buttermilk, apple, and strawberry";
	$c[23][4] = "Blueberry, raspberry, and buttermilk";
	$q[24] = "The items available to 'Shari-ize' any breakfast are:";
	$c[24][1] = "Add meat, fruit, mushrooms, or juice";
	$c[24][2] = "Add meat, fruit, mushrooms, supreme an omelette, and cheese to browns";
	$c[24][3] = "Add pancakes, juice or milk, or super size it.";
	$c[24][4] = "Add meat, fruit, mushrooms, supreme an omelette, cheese to browns, fruit to pancakes, " .
				"sub cinnamon French toast and add milk or juice";
	$q[25] = "Which omelettes are prepared with Swiss cheese?";
	$c[25][1] = "Denver and Wisconsin Omelette";
	$c[25][2] = "Denver and Veggie Omelette";
	$c[25][3] = "Denver, Veggie, and Wisconsin";
	$c[25][4] = "Veggie and Wisconsin Omelette";	
	$q[26] = "Shari's Fajita Omelette is prepared with";
	$c[26][1] = "Chicken, peppers, onions, and pepperjack cheese";
	$c[26][2] = "Steak, salsa, onions, and cheddar cheese";
	$c[26][3] = "Chicken, peppers, onions, and cheddar cheese";
	$c[26][4] = "Steak, peppers, onions, and pepperjack cheese";
	$q[27] = "Breakfast items that are prepared with three eggs are:";
	$c[27][1] = "All skillet breakfasts, platter breakfasts, and omelettes";
	$c[27][2] = "All skillet breakfasts, platter breakfasts, and scrambles";
	$c[27][3] = "All specialty breakfasts, omelettes, and full breakfasts";
	$c[27][4] = "All platter breakfasts, omelettes, scrambles";
	$a[19] = "Select the correct answer - 5 points each";
	$a[20] = "A";
	$a[21] = "C";
	$a[22] = "B";
	$a[23] = "C";
	$a[24] = "D";
	$a[25] = "D";
	$a[26] = "D";
	$a[27] = "D";
	$topic = $q[19];
	
	$topic    = "INSERT INTO question_def "	.
				"(test_seq, type_seq, question_num, question) VALUES " .
				"('1', '" . 1 . "', '12', \"". addslashes($topic) . "\");";		
	//echo(htmlspecialchars($topic) . "<BR>");
	if(!@mssql_query($topic)){
		echo("ERROR IN <BR>" . htmlspecialchars($question) ."<br>");
	}	
	for($i=20; $i<28; $i++){
		$question = $q[$i];
		$answer = $a[$i];
		$x = $i -7;
		$question = "INSERT INTO question_def "	.
					"(test_seq, type_seq, question_num, question) VALUES " .
					"('1', '" . 2 . "', '$x', \"". addslashes($question) . "\");";		
		//echo(htmlspecialchars($question) . "<BR>");
		if(!@mssql_query($question)){
			echo("ERROR IN <BR>" . htmlspecialchars($question) ."<br>");
		}		
		$question_seq = "SELECT MAX(question_seq) AS question_seq FROM question_def";
		$the_num = @mssql_query($question_seq);
		//echo($question_seq . "<BR>");
		$row = mssql_fetch_array($the_num);
		$question_seq = $row["question_seq"];
		$solution = "INSERT INTO question_solution_dtl " .
					"(question_seq, test_seq, solution) VALUES " .
					"('$question_seq', '1', \"" . addslashes($answer) . "\")";
		echo(htmlspecialchars($solution) . "<BR>");
		if(!@mssql_query($solution)){
			echo("ERROR IN <BR>" . htmlspecialchars($solution) ."<br>");
		}	
		for($counter=1; $counter<5; $counter++){
			$choice = "INSERT INTO question_def "	.
					"(test_seq, type_seq, question_num, question) VALUES " .
					"('1', '8', '$x', \"". addslashes($choice = $c[$i][$counter]) . "\");";		
			//echo(htmlspecialchars($choice) . "<BR>");
			if(!@mssql_query($choice)){
				echo("ERROR IN <BR>" . htmlspecialchars($question) ."<br>");
			}		
			
		
		}
	}
	echo("fill_question_def multiple choice completed.<br> ");
	/*******************************************************
	* LISTING
	********************************************************/
	$q[28] = "List the correct answers:";
	$q[29] = "The three fruit topping for griddled items are:";
	$q[30] = "The Big Start, Barn-Buster and Hearty Country Platter are served automatically with three meats. They are:";
	$q[31] = "When ordering toast, the guest has a choice of:";
	$a[28] = "List the correct answers:";
	$a[29] = "strawberries";
	$a[30] = "apples";
	$a[31] = "blueberries";
	$a[32] = "ham";
	$a[33] = "bacon";
	$a[34] = "sausage";
	$a[35] = "white";
	$a[36] = "whole wheat";
	$a[37] = "sourdough";
	$a[38] = "marble rye";
	$topic = $q[28];
	
	$topic    = "INSERT INTO question_def "	.
				"(test_seq, type_seq, question_num, question) VALUES " .
				"('1', '" . 1 . "', '21', \"". addslashes($topic) . "\");";		
	//echo(htmlspecialchars($topic) . "<BR>");
	if(!@mssql_query($topic)){
		echo("ERROR IN <BR>" . htmlspecialchars($question) ."<br>");
	}		
	
	for($i=29; $i<32; $i++){
		$x = $i - 7;
		$question = $q[$i];
		//echo(htmlspecialchars($question) . "<BR>");
		$question = "INSERT INTO question_def "	.
					"(test_seq, type_seq, question_num, question) VALUES " .
					"('1', '5', '$x', \"". addslashes($question) . "\");";		
		//echo(htmlspecialchars($question) . "<BR>");
		if(!@mssql_query($question)){
			echo("ERROR IN <BR>" . htmlspecialchars($question) ."<br>");
		}	
		$question_seq = "SELECT MAX(question_seq) AS question_seq FROM question_def";
		$the_num = @mssql_query($question_seq);
		//echo($question_seq . "<BR>");
		$row = mssql_fetch_array($the_num);
		$question_seq = $row["question_seq"];
		//INSERT THE ANSWERS
		if($i == 28){
			$solution = "INSERT INTO question_solution_dtl " .
						"(question_seq, test_seq, solution) VALUES " .
						"('$question_seq', '1', \"" . addslashes($a[$i]) . "\")";
			//echo(htmlspecialchars($solution) . "<BR>");
			if(!@mssql_query($solution)){
				echo("ERROR IN <BR>" . htmlspecialchars($solution) ."<br>");
			}
		}
		else if($i == 29){
			for($k = 29; $k < 32; $k++){
				$solution = "INSERT INTO question_solution_dtl " .
							"(question_seq, test_seq, solution) VALUES " .
							"('$question_seq', '1', \"" . addslashes($a[$k]) . "\")";
				//echo(htmlspecialchars($solution) . "<BR>");
				if(!@mssql_query($solution)){
					echo("ERROR IN <BR>" . htmlspecialchars($solution) ."<br>");
				}
			}
		}
		else if($i == 30){
			for($k = 32; $k < 35; $k++){
				$solution = "INSERT INTO question_solution_dtl " .
							"(question_seq, test_seq, solution) VALUES " .
							"('$question_seq', '1', \"" . addslashes($a[$k]) . "\")";
				//echo(htmlspecialchars($solution) . "<BR>");
				if(!@mssql_query($solution)){
					echo("ERROR IN <BR>" . htmlspecialchars($solution) ."<br>");
				}
			}
		}
		else if($i == 31){
			for($k = 35; $k < 39; $k++){
				$solution = "INSERT INTO question_solution_dtl " .
							"(question_seq, test_seq, solution) VALUES " .
							"('$question_seq', '1', \"" . addslashes($a[$k]) . "\")";
				//echo(htmlspecialchars($solution) . "<BR>");
				if(!@mssql_query($solution)){
					echo("ERROR IN <BR>" . htmlspecialchars($solution) ."<br>");
				}
			}
		}
	}		
	echo("fill_question_def listing completed.<br> ");
	/*******************************************************
	* FILL IN THE BLANK
	********************************************************/
	$q[32] = "Fill in the blank:";
	$q[33] = "Shari's Sunrise Melt is served with ____________________.";
	$q[34] = "All griddle breakfasts are served with __________ eggs.";
	$q[35] = "An Honored Country Fried Steak Breakfast is topped with _____________ gravy.";
	$q[36] = "An Honored Bacon and Egg breakfast is served with __ slices of bacon.";
	$q[37] = "When ordered with pancakes, full breakfasts receive __ pancakes.";
	$q[38] = "Shari's ________ omelette receives black olives as one of it's ingredients.";
	$q[39] = "Shari's Country Scramble is served with __ eggs";
	
	
	$a[39] = "Fill in the blank:";
	$a[40] = "hash browns";
	$a[41] = "2";
	$a[42] = "country";
	$a[43] = "2";
	$a[44] = "3";
	$a[45] = "BMP";
	$a[46] = "3";
	
	$topic = $q[32];
	$topic    = "INSERT INTO question_def "	.
				"(test_seq, type_seq, question_num, question) VALUES " .
				"('1', '" . 1 . "', '25', \"". addslashes($topic) . "\");";		
	//echo(htmlspecialchars($topic) . "<BR>");
	if(!@mssql_query($topic)){
		echo("ERROR IN <BR>" . htmlspecialchars($question) ."<br>");
	}		
	
	for($i=33; $i<40; $i++){
		$question = $q[$i];
		$answer = $a[($i+7)];
		$qNum = $i - 7;
		$question = "INSERT INTO question_def "	.
					"(test_seq, type_seq, question_num, question) VALUES " .
					"('1', '" . 5 . "', '$qNum', \"". addslashes($question) . "\");";		
		//echo($question . "<BR>");
		if(!@mssql_query($question)){
			echo("ERROR IN <BR>" . htmlspecialchars($question) ."<br>");
		}		
		$question_seq = "SELECT MAX(question_seq) AS question_seq FROM question_def";
		$the_num = @mssql_query($question_seq);
		//echo($question_seq . "<BR>");
		$row = mssql_fetch_array($the_num);
		$question_seq = $row["question_seq"];
		
		$solution = "INSERT INTO question_solution_dtl " .
					"(question_seq, test_seq, solution) VALUES " .
					"('$question_seq', '1', \"" . addslashes($answer) . "\")";
		//echo(htmlspecialchars($solution) . "<BR>");
		if(!@mssql_query($solution)){
			echo("ERROR IN <BR>" . htmlspecialchars($solution) ."<br>");
		}				
	}
	echo("fill_question_def fill in the blank completed.<br> ");
}
function fill_sec_lvl_def(){
	echo("<h1>fill_sec_lvl_def</h1>");
	$sec_lvl = array("student", "teacher", "admin");
	$sec_lvl_explanation = array("take test or view his results", "grade test or view all results", "do anything");
	for($i=0; $i<3; $i++){
		$insert = "INSERT INTO sec_lvl_def " .
			"(sec_lvl, sec_lvl_explanation)" .
			" VALUES " .
			"('" . $sec_lvl[$i] . "', '" . $sec_lvl_explanation[$i] . "');";
		//echo($insert."<br>");
		if(!(@mssql_query($insert)))
			echo("unable to insert into sec_lvl<br>");
		else 
			echo("fill_sec_lvl_def part " .($i+1)." of 3 completed. <br>");
	}
}

function drop_old_tables(){
	@mssql_query("DROP TABLE test_category_def");
	@mssql_query("DROP TABLE emp_def");
 	@mssql_query("DROP TABLE answer_small_dtl");
	@mssql_query("DROP TABLE answer_large_dtl");
	@mssql_query("DROP TABLE answer_large_dtl");
 	@mssql_query("DROP TABLE emp_answer_small_dtl");
 	@mssql_query("DROP TABLE emp_answer_large_dtl");
}
?>
