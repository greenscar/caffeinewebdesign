<?php
require("./inc_files/A_Person.php");
include("./inc_files/sql_connect.inc");
session_start();
if(session_is_registered("test")){
	session_unset();
	session_destroy();
}
if((@$_POST["Submit"]) || (session_is_registered("the_user"))){
	if(session_is_registered("the_user")){
		//LOGIN IS SUCCESSFUL
		//RECALL THE SCHOOL FROM THE SESSION
		$the_user = $_SESSION['the_user'];
		Header("Location: ./$goto");
	}
  	else if((!empty($_POST["emp_num"])) && (!empty($_POST["pwd"])) ){
		$search_string = "SELECT * FROM emp_def WHERE emp_num = '" . $_POST["emp_num"] . "' AND pwd = '" . $_POST["pwd"] . "'";
		$search_results = mssql_query($search_string) or die("Error in Query: $search_string. mssql said " . mssql_error() . '.');  //select the username 
		$search_occurances = mssql_num_rows($search_results); 
		@$num_attempts ++;
		if (($search_occurances != '0')) { 
			//LOGIN IS SUCCESSFUL
			$row = mssql_fetch_array($search_results);
			session_register("the_user");
			$the_user = new A_Person($row["emp_num"]);
			$sec_lvl = $the_user->sec_lvl;
			$goto = "screens_" . $sec_lvl . "/menu_" . $sec_lvl . ".php";
			Header("Location: ./$goto");
		} 
 	   	else {
			//LOGIN IS NOT SUCCESSFUL
			Header("Location: ./index.php?incorrect=true");
    	} 
	}	
	else{
		//LOGIN IS NOT SUCCESSFUL
		Header("Location: ./index.php?incorrect=true");
	}
}
else{
	//THE USER IS COMING FOR THE FIRST TIME OR THEIR LOGIN WAS UNSUCCESSFUL
	if(@$_GET["incorrect"]){
		$title = "Login Incorrect. Please try again.";
		echo("<H2 align=\"center\">Your login ID or password was incorrect. Please try again. </H2>");
	}
	else{
		$title="Please log in.";
		echo("<H2 align=\"center\">Welcome. Please Log In.</H2>");
	}
	include("./inc_files/html_header.inc");
?>
	<CENTER>	
		<FORM NAME="FORM1" METHOD="post" ACTION="<?= $_SERVER['PHP_SELF']; ?>" ONSUBMIT="return validateData(this) ">
			<TABLE BORDER="4" CELLPADDING="4" ALIGN="CENTER" HEIGHT="115">
				<TR>
					<TD>				
					<TABLE BORDER="0" ALIGN="CENTER" HEIGHT="115">
						<TR>						
							<TD ALIGN="right">Employee ID:</TD>
							<TD><INPUT TYPE="text" NAME="emp_num"></TD>
						</TR>
						<TR>
							<TD ALIGN="right">Password:</TD>
							<TD><INPUT TYPE="password" NAME="ssn"></TD>					
						</TR>					
						<TR>
							<TD ALIGN = "CENTER" colspan="2">
								<INPUT TYPE="submit" NAME="Submit" VALUE="Submit">
								<INPUT TYPE="reset" NAME="Reset" VALUE="Reset">
							</TD>					
						</TR>				
					</TABLE>
					</TD>
				</TR>		
			</TABLE>	
		</FORM>	
	</CENTER>
	</BODY>
</HTML>