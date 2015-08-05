<%@page contentType="text/html"%>
<html>
<head><title>JSP Page</title></head>
<body>
    <div align="center">
        <h2>Please enter your exam information:</h2>
        <FORM NAME="form1" method="POST" action="<?$_SERVER['PHP_SELF']?>?cat=test&scr=cretest&nl=1">
            <INPUT TYPE="hidden" NAME="question_num" VALUE = "0">
            <table width="306" border="0"> 
                <TR> 
                    <TD WIDTH="145">Exam Name:</TD>
                    <TD WIDTH="151"> 
                        <INPUT TYPE="text" NAME="test_name" SIZE="25" maxlength="30">
                    </TD>
                </TR>
                <TR> 
                    <TD WIDTH="145">Your Name:</TD>
                    <TD WIDTH="151"> 
                        <INPUT TYPE="text" NAME="created_by" SIZE="25" maxlength="30" VALUE="<? echo($the_user->first_name . " " . $the_user->last_name) ?>">
                    </TD>
                </TR>
                <TR> 
                <TD WIDTH="145">Exam Category:</TD>
                <TD WIDTH="151"> 
                <select NAME="category_name">
                <?
                while($_POST = mssql_fetch_array($search_results)) { 
                echo("\n\t\t\t\t<option VALUE=\"" . $_POST["category_name"] . "\">" . 
                $_POST["category_name"] . "</option>");
                } 
                ?>
                </select>
                </TD>
                </TR>
                <TR> 
                    <TD WIDTH="145">Display After Taking:</TD>
                    <TD WIDTH="151"> 
                        <input type="radio" name="display_after" value="1">Yes 
                        <input type="radio" name="display_after" value="0">No
                    </TD>
                </TR>
                <TR>
                    <TD COLSPAN="2" ALIGN="CENTER">
                        <INPUT TYPE="submit" NAME="Submit" VALUE="Submit">
                        <INPUT TYPE="reset" NAME="Reset" VALUE="Reset">
                    </TD>
                </TR>
            </table>
        </FORM>
        <P>&nbsp;</P>
        <P>&nbsp;</P>
    </div>
</body>
</html>
