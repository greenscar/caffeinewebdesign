<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Please Enter The First and Last Name</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link rel="stylesheet" type="text/css" href="./inc_files/template.css"/>
    <script language = "javascript" type="text/javascript" src="./inc_files/form_check.js"></script>
</head>

<body>
<form name="ldp" method="post" action="employee_lookup_process.php" onsubmit="return validate_name_lookup(this);">

<div class="center">
	<div class="grey_box_70">
		<div class="row">
                    <span class="white_center">
                        <h2>Employee number look-up</h2>
                    </span>
                </div>
		<div class="spacer"></div>
                <div class="row">
			<span class="left">
                            <span class="left">
				&nbsp;&nbsp;First Name:
                            </span>
                            <span class="right">
				<input type="text" class="name" name="first_name">&nbsp;&nbsp;
                            </span>
			</span>
			<span class="left">
                            <span class="left">
				&nbsp;&nbsp;Last Name:
                            </span>
                            <span class="right">
				<input type="text" class="name" name="last_name">&nbsp;&nbsp;
                            </span>
			</span>
		</div>
		<div class="spacer">&nbsp;</div>
		<div class="row_center">
			<input class="grey" type="submit" name="submit" value="Look Up">
		</div>
		<div class="spacer">&nbsp;</div>
	</div>
</div>
</form>
</body>
</html>