<?php
	/*
	* Each menu screen will check the session of the user and the security level
	* 	of the session Person instance. If the security level is not what this
	* 	menu screen is made for, the user will be forwarded to the login_screen
	* 	
	*/
	$user_type = "admin";
	require_once("../inc_files/check_session.inc");
	session_start();

?>
<html>
<head>
<TITLE>
<?
if (isset($_POST["title"])) echo $_POST["title"];
else echo ("Welcome to the test administrator.");
?>
</TITLE>
<SCRIPT SRC="../inc_files/js_fxns.js" LANGUAGE="JAVASCRIPT"></SCRIPT>
<STYLE TYPE="text/css">
<!--
body, td, form {<?php echo $css_general["body_td"] ;?>}
a:link {<?php echo $css_general["a_link"] ;?>}
a:visited {<?php echo $css_general["a_visited"] ;?>}
a:hover {<?php echo $css_general["a_hover"] ;?>}
a:active {<?php echo $css_general["a_active"] ;?>}
a.title {<?php echo $css_general["a_title"] ;?>}
a.title:link {<?php echo $css_general["a_title_link"] ;?>}
a.title:hover {<?php echo $css_general["a_title_hover"] ;?>}
button{<?php echo $css_general["button"] ;?>}
input, select{<?php echo $css_general["form_input_select"] ;?>}
.btn_main{<?php echo $css_general["form_button"] ;?>}
.inpt_main{<?php echo $css_general["form_field"] ;?>}
h2{<?php echo $css_general["h2"] ;?>}
H2.red{<?php echo $css_general["h2R"] ;?>}
h3{<?php echo $css_general["h3"] ;?>}
h4{<?php echo $css_general["h4"] ;?>}
.red_bold{<?php echo $css_general["red_bold"] ;?>}
.black{<?php echo $css_general["black"] ;?>}
.subject{<?php echo $css_general["subject"] ;?>}
.subject:link{<?php echo $css_general["subject_link"] ;?>}
.subject:hover{<?php echo $css_general["subject_hover"] ;?>}
-->
</STYLE>
</head>
<BODY  <?php echo $html_body_tag ; ?> style="direction: <?php echo $config_text_flow ; ?>">
<?
require_once("../inc_files/display_fxns.inc");
//echo("GET<BR>");
//view_array($_GET);
//echo("POST<BR>");
//view_array($_POST);
?>
<DIV ALIGN="CENTER">
<table width=750 height=600 border=1 cellspacing=3 cellpadding=3>
    <TR>
	<td height=20 COLSPAN=2>
	<TABLE width=750 border=0 cellspacing=0 cellpadding=0>
		<TR>
			<TD WIDTH=100>
				<img src="../extra_mile.gif">
			</TD>
			<TD VALIGN="BOTTOM">
				<div style="font-size: 28pt; font-weight: bold; padding: 10px;position:relative;bottom:10;">
					E-Training
				</div>
				<table width=650 border=0 cellspacing=0 cellpadding=0 align=LEFT>
        			<tr ALIGN="CENTER">
						<TD>
<?php
	if(true){
?>
          					<a class=topMenu href="./menu_admin.php?cat=test">
								Test Admin
							</a>
							<b>::</b>
		  					<a class=topMenu href="./menu_admin.php?cat=user">
								User Admin
							</a>
							<b>::</b>
		  					<a class=topMenu href="./menu_admin.php?cat=course">
								Course Admin
							</a>
							<b>::</b>
		  					<a class=topMenu href="./menu_admin.php?cat=class">
								Class Admin
							</a>
							<b>::</b>
						  	<a class=topMenu href="./menu_admin.php?cat=teacher">
								Teacher Functions
							</a>
							<b>::</b>
		  					<a class=topMenu href="../logout.php">
								Log Out
							</a>
<?
	}
?>
						</TD>
					</TR>
      			</table>
			</TD>
    	</TR>
	</TABLE>
	</TD>
  </TR>
  <TR>
  	<TD WIDTH=110 VALIGN=TOP>
			<? include("./menu_admin_sub.inc"); ?>
	</TD>
	<TD WIDTH=640 valign=top>
		<TABLE WIDTH=640>
			<TR>
				<TD>
					<?php
					session_start();
					switch(@$_GET["scr"]){
						//START USER SCREENS
						case "modusr":
							include("./user_modify.php");
							break;
						case "lausr":
							include("./user_list_all.php");
							break;
						case "addusr":
							include("./user_add.php");
							break;
						case "drpusr":
							include("./user_delete.php");
							break;
						case "react":
							include("./user_reactivate.php");
							break;
						//END USER SCREENS
						//START TEST SCREENS
						case "disptest":
							include("./test_with_solutions_display.php");
							break;
						case "deltest":
							include("./test_delete.php");
							break;
						case "cretest":
							include("./test_create.php");
							break;
						case "modtest":
							include("./test_modify.php");
							break;
						case "modquest":
							include("./question_modify.php");
							break;
						case "delquest":
							include("./question_delete.php");
							break;
						case "disptakelist":
							include("./test_taken_display.php");
							break;
						//END TEST SCREENS
						//START TEACHER SCREENS
						case "disptake":
							include("./test_of_student_display.php");
							break;
						//END TEACHER SCREENS
						//START STUDENT SCREENS
						case "enrollstu":
							include("./student_enroll.php");
							break;
						case "dropstu":
							include("./student_drop.php");
							break;
						//END STUDENT SCREENS
						//START COURSE SCREENS
						case "createcou":
							include("./course_create.php");
							break;
						case "delcou":
							include("./course_delete.php");
							break;
						case "modcou":
							include("./course_modify.php");
							break;
						case "lacou":
							include("./course_list_all.php");
							break;
						default:
							//echo "<H3>" . $_GET["scr"] . "</H3>";
							include("./intro.inc");
							break;
					}
					?>
				</TD>
			</TR>
		</TABLE>
	</TD>
	</TD>
  </TR>
</TABLE>
</div>
</BODY>
</HTML>