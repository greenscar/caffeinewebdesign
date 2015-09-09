<?php
$alphabet = array(A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z);
error_reporting(E_ALL);
//$ip = gethostbyname($str);
foreach($alphabet AS $letter_1)
{
   foreach($alphabet AS $letter_2)
   {
      foreach($alphabet AS $letter_3)
      {
         $handle = fopen("./results/open_domains.csv", "a");
         foreach($alphabet AS $letter_4)
         {
            set_time_limit(30);
            $str = $letter_1 . $letter_2 . $letter_3 . $letter_4 . ".COM";
            $ip = gethostbyname($str);
            if(!is_ip($ip))
            {
               echo($ip.", ");
               $str .= ", ";
               fwrite($handle, $str);
            }
         }
         fclose($handle);
      }
   }
}
function is_ip($ip) {
   $valid = true;
   $ip = explode(".", $ip);
   foreach($ip as $block) {
       if(!is_numeric($block)) {
           $valid = false;
       }
   }
   return $valid;
}
//echo(checkdnsrr("caffeinewebdesign.com"));
//echo(shell_exec("/usr/bin/whois CAFFEINEWEBDESIGN.COM"));
//echo(shell_exec("/usr/bin/whois CAFFEINEWEBDESIGN.COM"));
?>
