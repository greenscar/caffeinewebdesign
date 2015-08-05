<?php
class Scribe
{
   var $file_name;
   function Scribe()
   {
      $fn = date("Y_m_d", mktime());
      $this->file_name = "/wwwroot/logs/" . date("Y_m_d", mktime()) . "_site_info.log";
   }
   function log($msg)
   {
      // open file
      $fd = fopen($this->file_name, "a");
      
      // append date/time to message
      $str = "[" . date("Y/m/d H:i", mktime()) . "] " . $msg;
      
      // write string
      fwrite($fd, $str . "\n");
      
      // close file
      fclose($fd);
   }
   function log_end_of_fxn()
   {
      // open file
      $fd = fopen($this->file_name, "a");
      
      // append date/time to message
      $str = "[" . date("Y/m/d H:i", mktime()) . "] --------------------------------------------";
      
      // write string
      fwrite($fd, $str . "\n");
      
      // close file
      fclose($fd);
   }
      
}
?>