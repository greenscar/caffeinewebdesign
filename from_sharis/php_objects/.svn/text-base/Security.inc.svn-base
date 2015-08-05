<?php
//require_once("http://www.e-sharis.net/includes/php_files/User.inc");
require_once("User.inc");
//require_once("http://www.e-sharis.net/includes/php_files/DB_Mgr.inc");
require_once("DB_Mgr.inc");
class Security
{
   var $db_mgr;
   
   function Security()
   {
      $this->db_mgr = new DB_Mgr("rics");
   }
   
   /*
    * Get the security level of this user from the App Access table.
    * REQUIRE: load_user has already been called.
    */
   function get_security_level($user, $path_info)
   {
      return $user->db_load_sec_lvl($this->db_mgr, $path_info);
   }
   /*
    * Load the user information based on the provided user_id.
    * This user_id was set as a cookie by cold fusion.
    */
   function load_user($user_id, $_SESSION)
   {
      $a_user = new User($user_id);
      if(empty($_SESSION["user"]))
      {
         //$this->db_mgr->connect();
         $a_user->db_load_user($this->db_mgr);
         //return $a_user->first_name;
         //$this->db_mgr->disconnect();
         $_SESSION["user"] = $a_user;
      }
      return $a_user;
   }
   function user_is_admin(&$user, $path_info)
   {
      $sec_lvl = $user->db_load_sec_lvl($this->db_mgr, $path_info);
      if(strcmp($sec_lvl, "root") == 0)
         return(true);
      else if(strcmp($sec_lvl, "admin") == 0)
         return(true);
      else
         return(false);
      return(true);
   }
   function user_is_root($sec_lvl)
   {
      if(strcmp($sec_lvl, "root") == 0)
         return(true);
      else
         return(false);
   }
}
?>