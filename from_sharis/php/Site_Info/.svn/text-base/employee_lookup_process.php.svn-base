<?php
require_once("./classes/Admin.inc");
$ad = new Admin();

// LOAD THE LIST OF EMPLOYEES WITH THIS NAME
echo $ad->db_search_via_first_and_last_name(stripslashes($_POST["last_name"]), stripslashes($_POST["first_name"]));
echo("size = " . $ad->emp_list_size());
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
                        <h2>Here are the employees named <?php echo($_POST["first_name"] . " " . $_POST["last_name"]); ?></h2>
                    </span>
                    <span class="white_center">
                        <table border="1">
                            <tr>
                                <th>Emp ID</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                            </tr>
                            <?php
                            for($i=0; $i < $ad->emp_list_size() ; $i++)
                            {
                                $emp = $ad->get_an_emp_from_list($i);
                                ?>
                            <tr>
                                <th><?php echo($emp->payroll_id); ?></th>
                                <th><?php echo($emp->first_name); ?></th>
                                <th><?php echo($emp->last_name); ?></th>
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
</body>
</html>