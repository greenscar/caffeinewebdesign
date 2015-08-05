<?php
class Settings
{
   //var $server = "PSTEST, 2433";
   var $server = array("WEB" => "PSTEST", "sites" => "DW, 1433", "PSTEST" => "PSTEST, 2433");
   var $db = array("WEB" => "WEB", "sites" => "SITEINFO", "PSTEST" => "PSTEST");
   var $id = "sa";
   var $pwd = "sa";
   
   //var $tables = array("site" => "SI_SITE", "phone_line" =>"SI_PHONE_LINE", "phone_company"=>"SI_PHONE_CO", "phone_type"=>"SI_PHONE_TYPE", "amenity_type"=>"SI_AMENITY_TYPE", "amenity_list"=>"SI_AMENITY_LIST");
   
   function Settings()
   {
   }
}
?>