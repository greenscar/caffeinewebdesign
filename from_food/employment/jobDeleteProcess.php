<?php
    //include("../includes/php_files/ensure_admin.inc");
   require_once("http://www.e-sharis.net/includes/php_files/DB_Mgr.inc");
   $dbmgr = new DB_Mgr("web");
   $delete = "UPDATE emp_job_postings SET active = 0 WHERE time_posted = " . $_GET["jobNum"];    
   $dbmgr->query($delete);
   $dbmgr->disconnect();
   header("Location: ./index.php");
?>