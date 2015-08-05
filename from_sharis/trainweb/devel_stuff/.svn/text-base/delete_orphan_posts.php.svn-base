<?php
mysql_connect("localhost", "mysql", "Zoolu4");
mysql_select_db("BBS");

$sel = "SELECT DISTINCT p.post_id
FROM phpbb_posts p, phpbb_posts_text t
WHERE poster_id
IN (

SELECT DISTINCT poster_id
FROM phpbb_posts, phpbb_users
WHERE NOT
EXISTS (

SELECT *
FROM phpbb_users
WHERE phpbb_posts.poster_id = phpbb_users.user_id
)
) AND p.post_id = t.post_id
ORDER BY `poster_id` ASC
";

$pid = Array();
$rs = mysql_query($sel);
while($row = mysql_fetch_row($rs))
{
        array_push($pid, $row[0]);
}
foreach($pid AS $id=>$key)
{
        $d1 = "delete FROM phpbb_posts where post_id = " . $key;
    $rs2 = mysql_query($d1) or die(mysql_error());
        $d2 = "delete FROM phpbb_posts_text where post_id = " . $key;
//    echo($d2 . "<BR>");
        $rs3 = mysql_query($d2) or die(mysql_error());
    //echo($dl2 . "<BR>");
}

//echo($sel);

?>