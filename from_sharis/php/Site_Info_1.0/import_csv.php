<?php
require_once("inc_files/php_fxns.inc");
require_once("classes/Store.inc");
$row = 1;
$handle = fopen("PHONES.csv", "r");
$pn_list = array();
$line_num = 0;
while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) {   
   
   // If the first cell is a number, it is a new store.
   if(is_numeric($data[0]))
   {
      $line_num = 1;
      if(!empty($store))
      {
         $store->set_date_active("01/01/2010");
         $store->active = 1;
         $store->set_sysco_num(000000);
         //echo($store->display_info() . "<BR><hr>");
         // INSERT DATA INTO DB
         echo($store->db_insert_store());
      }
      
   }
   else $line_num ++;
   
   /*
   echo("+++++++++++++++++++++++++++++++++++++<br>");
   echo("line_num = $line_num <BR>");
   for($x=0; $x < sizeof($data); $x++)
   {
      echo("data[$x] = " .$data[$x] . "<BR>");
   }
   echo("-----------------------------------<br>");
   */
      
   if($line_num == 1){
      /*
      * Read the first line of data for this new object.
      */
      $store = new Store($data[0],$data[1]);
      //$store->add_line($data[2], $data[3]);
   }
   else if($line_num == 2)
   {
      set_phone_company(&$store, $data[0]);
      //$store->set_phone_company_name($data[0]);
      //$store->phone_system->company->db_load_phone_co_via_name();
      $store->address->description = $data[1];
   }
   else if($line_num == 3)
   {
      $store->address->street = $data[1];
   }
   else if($line_num == 4)
   {
      $csz = $data[1];
      $comma_loc = strpos($csz, ",");
      $last_space_loc = strrpos($csz, " ");
      $store->address->city = substr($csz, 0, $comma_loc);
      $store->address->state = trim(substr($csz, ($comma_loc + 2), 3));
      $store->address->zip = substr($csz, ($last_space_loc + 1), (strlen($csz)));
   }
   add_phone_line(&$store, $data[2], $data[3]);
   //echo("num_phone_lines = " . sizeof($store->phone_system->line_list) . "<br>");
}
// TAKE CARE OF LAST STORE IN TABLE
$store->set_date_active("01/01/2010");
$store->active = 1;
$store->set_sysco_num(000000);
//echo($store->display_info() . "<BR><hr>");
echo($store->db_insert_store());

/************************************************************
 * If the phone line type doesn't exist in the db, insert it.
 * Else get the id from the db.
 * Return the id
 ************************************************************/
function add_phone_line($store, $type, $number)
{
   //echo("<h4>add_phone_line(store, $type, $number)</h4>");
      /* CREATE THE PHONE TYPE OR LOAD THE KEY FROM THE DB */
      $plt = new Phone_Line_Type();
      $plt->name = $type;
      if($plt->db_type_name_exists())
      {
         // Load the phone_type_id
         $plt->db_load_id_via_name();
         //echo("type name " . $plt->id . " exists in DB<BR>");
      }
      else
      {
         // Insert this phone type then get the id
         $plt->generate_id();
         $plt->db_insert_phone_type();
         //echo("new type name = " . $plt->id . "<BR>");
      }
      /* END CREATE THE PHONE TYPE OR LOAD THE KEY FROM THE DB */
      //echo("store->add_line($plt->id, $number);<br>");
      $store->add_line($plt->id, $number);
      //echo("store->add_line($plt->id, $number) = " . $k);
      return($plt->id);
}
function set_phone_company($store, $name)
{
   //echo("<h4>add_phone_line(store, $type, $number)</h4>");
      /* CREATE THE PHONE COMPANY OR LOAD THE KEY FROM THE DB */
      $plc = new Phone_Company("", $name);
      if($plc->db_company_name_exists())
      {
         // Load the phone_type_id
         $plc->db_load_phone_co_via_name();
         //echo("type name " . $plt->id . " exists in DB<BR>");
      }
      else
      {
         // Insert this phone type then get the id
         $plc->generate_id();
         $plc->db_insert_phone_company();
         //echo("new type name = " . $plt->id . "<BR>");
      }
      /* END CREATE THE PHONE TYPE OR LOAD THE KEY FROM THE DB */
      //echo("store->add_line($plt->id, $number);<br>");
      $store->phone_system->company = $plc;
      //echo("store->add_line($plt->id, $number) = " . $k);
}
fclose($handle);
?>