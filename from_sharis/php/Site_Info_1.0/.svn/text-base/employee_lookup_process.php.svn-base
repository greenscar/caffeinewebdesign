<?php
require_once("./classes/Admin.inc");
$ad = new Admin();

// LOAD THE LIST OF EMPLOYEES WITH THIS NAME
echo $ad->db_search_via_first_and_last_name(stripslashes($_POST["last_name"]), stripslashes($_POST["first_name"]));
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Your Employee's Payroll ID</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link rel="stylesheet" type="text/css" href="./inc_files/template.css"/>
</head>

<body>
<div class="center">
	<div class="grey_box_70">
		<div class="row">
                    <span class="white_center">
                        <h2>Here are the employees named</h2>
			<h2><?php echo($_POST["first_name"] . " " . $_POST["last_name"]); ?></h2>
                    </span>
                    <span class="white_center">
                        <table border="1" cellpadding="5" width="80%">
                            <tr>
                                <th>
				    <span class="white_center">
					<u>Emp ID</u>
				    </span>
                                </th>
                                <th>
				    <span class="white_center">
					<u>First Name</u>
				    </span>
                                </th>
                                <th>
				    <span class="white_center">
					<u>Last Name</u>
				    </span>
                                </th>
                            </tr>
                            <?php
                            for($i=0; $i < $ad->emp_list_size() ; $i++)
                            {
                                $emp = $ad->get_an_emp_from_list($i);
                                ?>
                            <tr>
                                <th>
				    <span class="white_center">
					<?php echo($emp->payroll_id); ?>
				    </span>
                                </th>
                                <th>
				    <span class="white_center">
					<?php echo($emp->first_name); ?>
				    </span>
                                </th>
                                <th>
				    <span class="white_center">
					<?php echo($emp->last_name); ?>
				    </span>
                                </th>
                            </tr>
                            <?php
                            }
                            ?>
                        </table>
                    </span>
                </div>
		<div class="spacer"></div>		
	</div>
</div>

<div class="spacer"></div>
<div class="spacer"></div>
<div class="spacer"></div>
<div class="spacer"></div>
<div class="center">
	<div class="grey_box_50">
	<div class="spacer"></div>
	<a class="button" onclick="window.close()">Close</a>
	<div class="spacer"></div>
	</div>
</div>
</body>
</html>