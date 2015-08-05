<?php
class Settings
{
   /*
   * $server will not be used in an ODBC connection because the OS already
   *     knows what the server is based on the $db.
   */
   /*
   var $server = array(
            "PS" => "psnt, 2433",
            "PSTEST" => "PSTEST, 2433", 
            "rics_SHARIS" => "dblite, 2433",
            "WEB" => "dblite, 2433",
            "webdev" => "PSTEST, 2433"
            );
   */
   var $db = array(
            "PRODSUP" => "PRODSUP",
            "PS" => "PS_DEV",
            "rics_SHARIS" => "rics_SHARIS_DEV",
            "WEB" => "WEB_DEV",
            );
   var $id = array(
            "PRODSUP" => "sa",
            "PS" => "sa",
            "rics_SHARIS" => "sa",
            "WEB" => "sa",
            );
   var $pwd = array(
            "PRODSUP" => "sa",
            "PS" => "sa",
            "rics_SHARIS" => "sa",
            "WEB" => "sa",
            );
}
?>