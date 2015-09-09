<?php
require_once("./vars.inc");
require_once("multiuse_fxns/php_fxns.php");
require_once("./queries.inc");
$empNum = $_POST["emp_num"];
$db = $databases[3];
$connection = connect_to_mssql($db[0], $db[1], $db[2], $db[3]);
$name = load_emp_name($empNum);
?>
<html>
<header>
<title>Enter your Information.</title>
<style type="text/css">
@import url("styles.css");
</style>
</header>
<body>
<form name="discp_notice" method="post" action="disciplinary_notice_process.php">
<table class="outer">
    <tr>
        <td class="label">Name:</td>
        <td class="setValue"><?php echo($name)?></td>
        <td class="label">Emp #:</td>
        <td class="setValue"><?php echo($empNum)?></td>
    </tr>
    <tr>
        <td class="label">Date:</td>
        <td class="setValue"><?php echo(strftime("%b %d %Y",time())); ?></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td class="label">Store:</td>
        <td>
            <select name="store_num">
        <?php
            $stores = load_store_nums();
            foreach($stores as $jobCode => $description)
            {
                echo("\t\t<option value=\"$jobCode\">$description</option>\n");    
            }
            //print_r($positions);
        ?>
            </select>
        </td>
        <td class="label">Position:</td>
        <td>
            <select name="job_position">
        <?php
            $positions = load_job_positions();
            foreach($positions as $jobCode => $description)
            {
                echo("\t\t<option value=\"$jobCode\">$description</option>\n");    
            }
            //print_r($positions);
        ?>
            </select>
        </td>
    </tr>
    <tr>
        <td colspan=4><hr class="divider"></td>
    </tr>
    <tr>
        <td class="label" colspan=4>Violation:</td>
    </tr>
    <?php
        $colNum = 1;
        $violations = load_violations();
        $x=1;
        foreach($violations as $key => $description)
        {
            if($colNum == 1)
                echo("<tr>\n\t");
            echo("<td colspan=2>\n\t\t");
            echo("<input type=\"checkbox\" value=\"$key\" name=\"violation_$x\">&nbsp$description\n\t");
            echo("</td>\n\t\t");
            $colNum++;
            if($colNum == 3)
            {
                $colNum = 1;
                echo("</tr>\n");
            }
            $x++;
        }
        if($colNum == 2)
        {
            echo("<td colspan=2>&nbsp</td>");
            echo("</tr>");
        }
    ?>
    <tr>
        <td colspan=4><hr class="divider"></td>
    </tr>
    <tr>
        <td colspan=4>
            <div class="label">
                Description of issue:
            </div>
            <div class="smallLetters">
                (violation of rules, standards, practices, unsatisfactory performance, etc.)
            </div>
        </td>
    </tr>
    <tr>
        <td colspan=4>
            <textarea name="description" class="description"></textarea>
        </td>
    </tr>
    <tr>
        <td colspan=4><hr class="divider"></td>
    </tr>
    <tr>
        <td colspan=4>
            <div class="label">
                Negative effect to business or shift because of the incident:
            </div>
        </td>
    </tr>
    <tr>
        <td colspan=4>
            <textarea name="neg_effect" class="description"></textarea>
        </td>
    </tr>
    <tr>
        <td colspan=4>
            <table class=inner>
                <tr>
                    <td colspan=2>
                        <div class="label">
                            Prior warnings on this matter:
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="checkbox" name="warningVerbal" value="true">&nbsp;Verbal on
                    </td>
                    <td>
                        <?php echo(selectDate()); ?>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="checkbox" name="warningWritten" value="true">&nbsp;Written on
                    </td>
                    <td>
                        <?php echo(selectDate()); ?>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="checkbox" name="suspensionWritten" value="true">&nbsp;Suspension on
                    </td>
                    <td>
                        <?php echo(selectDate()); ?>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan=4><hr class="divider"></td>
    </tr>
    <tr>
        <td colspan=4>
            <div class="label">
                Action Taken:
            </div>
        </td>
    </tr>
    <?php
        $colNum = 1;
        $actions = load_actions();
        foreach($actions as $key => $description)
        {
            if($colNum == 1)
                echo("<tr>\n\t");
            echo("<td colspan=2>\n\t\t");
            echo("<input type=\"radio\" name=\"action\" value=\"$key\">$description</input>\n\t");
            echo("</td>\n\t\t");
            $colNum++;
            if($colNum == 3)
            {
                $colNum = 1;
                echo("</tr>\n");
            }
        }
        if($colNum == 2)
        {
            echo("<td colspan=2>&nbsp</td>");
            echo("</tr>");
        }
    ?>
    <tr>
        <td colspan=4><hr class="divider"></td>
    </tr>
    <tr>
        <td colspan=4>
            <div class="label">
                What must employee do in the future to avoid further disciplinary action:
            </div>
            <div class="smallLetters">
                (include dates for improvement and plans for follow-up, if any)
            </div>
        </td>
    </tr>
    <tr>
        <td colspan=4>
            <textarea name="whatEmpMustDo" class="description"></textarea>
        </td>
    </tr>
    <tr>
        <td colspan=4>
            <div class="label">
                Employee Comments:
            </div>
        </td>
    </tr>
    <tr>
        <td colspan=4>
            <textarea name="empComments" class="description"></textarea>
        </td>
    </tr>
    <tr>
        <td colspan=4>
            <div class="legalNotice">
                THE ABSENCE OF ANY STATEMENT ON THE PART OF THE EMPLOYEE INDICATES HIS/HER
                AGREEMENT WITH THE REPORT AS STATED. ANY WRITTEN COMMENT MADE BY THE EMPLOYEE
                DOES NOT AUTOMATICALLY NULLIFY THE ABOVE.
            </div>
        </td>
    </tr>
    <tr>
        <td colspan=4 align="center">
            <input type=submit name="Print" value="Print">
        </td>
    </tr>
</table>
</body>
</html>