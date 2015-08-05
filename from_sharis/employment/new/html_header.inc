<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Enter Job Information</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <script language = "javascript" type="text/javascript" src="form_error_check.js"></script>
<style type="text/css">
<!--
textarea.store_location {
    height: 75px; width: 300px; margin-left: auto; margin-right: auto;
}
h3.error {
            align: center;
            font-size: 20px;
            color: red;
            border-width: thick;
            border-style: double; 
            border-color: black; 
            padding: 0.5em;
            width: 50%; 
            margin-top: +25px;
            margin-bottom: +5px;
            text-align: center;
        }
textarea.other_instructions {
    height: 100px; width: 300px; margin-left: auto; margin-right: auto;
}
table.mainMenu{
    width: 200px; margin-left: auto; margin-right: auto; border: outset 3pt;
    border-style: solid; border-color: green; margin-top: +25px; margin-bottom: +33px;
}
body { 
    text-align: center; 
}
A.page {
    color:BLACK;  font-size: 12pt; font-weight: bold; text-decoration:none; border:none; padding:0
}
A.page:link{
    color:BLACK;  font-size: 12pt; font-weight: bold;text-decoration:none; border:none; padding:0
}
A.page:hover{
    color:RED;  font-size: 12pt; font-weight: bold;text-decoration:none; border:none
}

A.mainMenu {
    color:BLACK;  font-size: 14pt; font-weight: bold; text-decoration:none; border:none; padding:0
}
A.mainMenu:link{
    color:BLACK;  font-size: 14pt; font-weight: bold;text-decoration:none; border:none; padding:0
}
A.mainMenu:hover{
    color:RED;  font-size: 14pt; font-weight: bold;text-decoration:none; border:none
}
A.link {
    color:BLACK;  font-size: 12pt; font-weight: bold; text-decoration:none; border:none; padding:0
}
A.link:link{
    color:BLACK;  font-size: 12pt; font-weight: bold;text-decoration:none; border:none; padding:0
}
A.link:hover{
    color:RED;  font-size: 12pt; font-weight: bold;text-decoration:none; border:none
}
@font-face
	{font-family:Verdana;
	panose-1:2 11 6 4 3 5 4 4 2 4;
	mso-font-charset:0;
	mso-generic-font-family:swiss;
	mso-font-pitch:variable;
	mso-font-signature:647 0 0 0 159 0;}
 /* Style Definitions */
p.MsoNormal, li.MsoNormal, div.MsoNormal
	{mso-style-parent:"";
	margin:0in;
	margin-bottom:.0001pt;
	mso-pagination:widow-orphan;
	font-size:12.0pt;
	font-family:"Times New Roman";
	mso-fareast-font-family:"Times New Roman";}
p
	{font-size:12.0pt;
	font-family:"Times New Roman";
	mso-fareast-font-family:"Times New Roman";}
p.small, li.small, div.small
	{mso-style-name:small;
	font-size:7.5pt;
	font-family:"Times New Roman";
	mso-fareast-font-family:"Times New Roman";}
p.menu, li.menu, div.menu
	{mso-style-name:menu;
	font-size:9.0pt;
	font-family:"Times New Roman";
	mso-fareast-font-family:"Times New Roman";}
p.menuhd, li.menuhd, div.menuhd
	{mso-style-name:menuhd;
	font-size:9.0pt;
	font-family:"Times New Roman";
	mso-fareast-font-family:"Times New Roman";}
@page Section1
	{size:8.5in 11.0in;
	margin:1.0in 1.25in 1.0in 1.25in;
	mso-header-margin:.5in;
	mso-footer-margin:.5in;
	mso-paper-source:0;}
div.Section1
	{page:Section1;}
th {width:98.0pt;border:inset .75pt;background:#336633;padding:2.25pt 2.25pt 2.25pt 2.25pt; width=140; valign=top; text-align:center;}
th.class {width:46.65pt;border:inset .75pt;background:#336633; padding:2.25pt 2.25pt 2.25pt 2.25pt; width:140; text-align:center}
th.jobTitle {width:46.65pt;border:inset .75pt;background:#336633; padding:2.25pt 2.25pt 2.25pt 2.25pt; text-align:center;}
th.address {width:105.2pt;border:inset .75pt;background:#336633;padding:2.25pt 2.25pt 2.25pt 2.25pt; text-align:center;}
th.contact {width:92.7pt;border:inset .75pt;background:#336633;padding:2.25pt 2.25pt 2.25pt 2.25pt; text-align:center;}
th.fax {width:66.2pt;border:inset .75pt;background:#336633;padding:2.25pt 2.25pt 2.25pt 2.25pt; text-align:center;}
th.other {width:92.7pt;border:inset .75pt;background:#336633;padding:2.25pt 2.25pt 2.25pt 2.25pt; text-align:center;}
td.class {width:46.65pt;border:inset .75pt;background:white; padding:2.25pt 2.25pt 2.25pt 2.25pt; width:140; text-align:center}
td.jobTitle {width:46.65pt;border:inset .75pt;background:white; padding:2.25pt 2.25pt 2.25pt 2.25pt; text-align:center;}
td.address {width:105.2pt;border:inset .75pt;background:white;padding:2.25pt 2.25pt 2.25pt 2.25pt; text-align:center;}
td.contact {width:92.7pt;border:inset .75pt;background:white;padding:2.25pt 2.25pt 2.25pt 2.25pt; text-align:center;}
td.fax {width:66.2pt;border:inset .75pt;background:white;padding:2.25pt 2.25pt 2.25pt 2.25pt; text-align:center;}
td.other {width:92.7pt;border:inset .75pt;background:white;padding:2.25pt 2.25pt 2.25pt 2.25pt; text-align:center;}
span.normal {font-size:7.5pt;font-family:Verdana;color:#333333;text-align:center}
span.columnName {font-size:7.5pt;font-family:Verdana;color:white}

-->
</style>
</head>
<?php
    //require_once("./inc/some_useful_fxns.inc");
    //Connect to DB
    //$dbcnx = connect_to_mssql("PSNT", "sa", "sa", "WEB");
?>
<body>
