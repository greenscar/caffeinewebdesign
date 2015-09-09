<?php include("variables.inc"); ?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 //EN">
<HTML>
<HEAD>
    <TITLE>
        <?php echo $title  ?>
    </TITLE>
	<meta http-equiv="Content-Type" CONTENT="text/html; charset=iso-8859-1">
	<link rel="stylesheet" type="text/css" href="../inc_files/css.css">		
	<script type="text/javascript" language="Javascript">
	function change_to_red() {	
	  	var openObj = document.getElementById("a_question");
		openObj.style.color = "#FF0000";
	}
	function change_to_black() {	
	  	var openObj = document.getElementById("a_question");
		openObj.style.color = "#000000";
	}
	</script>
</HEAD>
<BODY <?php echo $background?>>
