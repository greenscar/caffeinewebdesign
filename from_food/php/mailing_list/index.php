<?php
require("./inc/A_Person.inc");
include("./inc/sql_connect.inc");
session_start();
if((@$_POST["Submit"]) || (session_is_registered("mailing_list_admin"))){
	if(session_is_registered("mailing_list_admin")){
		//LOGIN IS SUCCESSFUL
		//echo("login was good.");
		$mailing_list_admin = $_SESSION['mailing_list_admin'];
		Header("Location: newsletter_create.php");
	}
  	else if((!empty($_POST["login"])) && (!empty($_POST["pwd"])) ){
		$search_string = "SELECT * FROM ml_admins WHERE login = '" . $_POST["login"] . "' AND pwd = '" . $_POST["pwd"] . "'";
		//echo("search_string = " . $search_string);
                $search_results = mssql_query($search_string) or die("Error in Query: $search_string. mssql said " . mssql_error() . '.');  //select the username 
		$search_occurances = mssql_num_rows($search_results); 
		@$num_attempts ++;
		if (($search_occurances != '0')) { 
			//LOGIN IS SUCCESSFUL
			$row = mssql_fetch_array($search_results);
			session_register("mailing_list_admin");
			$mailing_list_admin = new A_Person($row["login"]);
			Header("Location: newsletter_create.php");
		} 
 	   	else {
			//LOGIN IS NOT SUCCESSFUL
			Header("Location: index.php?incorrect=true");
                } 
	}	
	else{
                echo("login failed.");
		//LOGIN IS NOT SUCCESSFUL
		Header("Location: index.php?incorrect=true");
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
        
?>
<html>
<header><title><?echo($title);?></title></header>
<body>
	<CENTER>	
		<FORM NAME="FORM1" METHOD="post" ACTION="<?= $_SERVER['PHP_SELF']; ?>" ONSUBMIT="return validateData(this) ">
			<TABLE BORDER="4" CELLPADDING="4" ALIGN="CENTER" HEIGHT="115">
				<TR>
					<TD>				
					<TABLE BORDER="0" ALIGN="CENTER" HEIGHT="115">
						<TR>						
							<TD ALIGN="right">Employee ID:</TD>
							<TD><INPUT TYPE="text" NAME="login"></TD>
						</TR>
						<TR>
							<TD ALIGN="right">Password:</TD>
							<TD><INPUT TYPE="password" NAME="pwd"></TD>					
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
<?php
}
?>