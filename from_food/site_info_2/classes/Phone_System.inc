<?php
require_once("Phone_Line.inc");
require_once("Phone_Company.inc");
class Phone_System
{
   // the Phone_Company the store uses.
   var $company;
   // an array of Phone_Line objects with the store's phone lines and numbers.
   var $phone_lines;
   
   // Array of Phone_Type
   var $phone_types;
   
   // Array of Phone_Company
   var $phone_companies;
   
   function Phone_System()
   {
      $this->phone_companies = array();
      $this->line_list = array();
   }
   /********************************************************************
   * Line List Functions
   ********************************************************************/
   // get_line is used to return a Phone_Line.
   // REQUIRES: The name of the phone line type.
   function get_number_via_code($code)
   {
      foreach($this->line_list AS $key=>$val)
      {
         //return("strcmp(" . $val->type->id . ", ". $code .") = " .strcmp($val->type->id, $code) . "<BR>");
         if(strcmp($val->type->id, $code) == 0)
            return $val->number;
      }
   }
   function get_number_via_name($name)
   {
      $name = trim($name);
      foreach($this->line_list AS $key=>$val)
      {
         //return("strcmp(" . $val->type->id . ", ". $code .") = " .strcmp($val->type->id, $code) . "<BR>");
         if(strcmp($val->type->name, $name) == 0)
            return $val->number;
      }
      return null;
   }
   
   function get_line($num)
   {
      return $this->line_list[$num];
   }
   
   // Add this line object to the line array.
   // REQUIRE: The site does not already have a line with that name.
   function add_line($type_id, $number)
   {
      $plt = new Phone_Line_Type($type_id);
      $line = new Phone_Line($plt, $number);
      return(array_push($this->line_list, $line));        
   }
   
   // Remove this phone line from the array.
   function remove_line($type)
   {
      $tmp = array();
      foreach($this->line_list AS $key=>$val)
      {
         if(strcmp($val->type, $type) != 0)
            $tmp[$key] = $val;
      }
      $this->line_list = $tmp;
   }
   
   function remove_all_lines()
   {
      $this->line_list = array();
   }
   
   // Does this line type exist in the array?
   function line_exists($type)
   {
      $return = false;
      foreach($this->line_list AS $key=>$val)
      {
         if(strcmp($val->type->id, $type) == 0)
            $return = true;
      }
      return $return;
   }
   
   function line_list_size()
   {
      return sizeof($this->line_list);
   }
   
   /********************************************************************
   * END Line List Functions
   ********************************************************************/
   
   
   /********************************************************************
   * Phone Company List Functions
   ********************************************************************/
   function db_load_phone_co_list($si_cnx)
   {
      $select = "SELECT * FROM SI_PHONE_CO ORDER BY name";
      $rs = odbc_exec($si_cnx, $select);
      //return odbc_num_rows($rs);
      while(odbc_fetch_row($rs))
      {
         $tmp = new Phone_Company();
         $tmp->id = trim(odbc_result($rs,1));
         $tmp->name = trim(odbc_result($rs,2));
         array_push($this->phone_companies, $tmp);
      }
      odbc_free_result($rs);
      //return sizeof($this->phone_companies);
   }
   
   function get_a_phone_co_from_list($array_loc)
   {
      return($this->phone_companies[$array_loc]);
   }
   
   function phone_co_list_size()
   {
      return sizeof($this->phone_companies);
   }
   
   function db_load_co_via_id($si_cnx, $id)
   {
      $this->company = new Phone_Company($id, "");
      $this->company->db_load_co_via_id($si_cnx);
   }
   
   function db_load_co_via_name($si_cnx, $name)
   {
      $this->company = new Phone_Company("", $name);
      $this->company->db_load_co_via_name($si_cnx);
   }
   /********************************************************************
   * END Phone Company List Functions
   ********************************************************************/
   
   /********************************************************************
   * Phone Type List Functions
   ********************************************************************/
   function db_load_phone_type_list($si_cnx)
   {
      $this->phone_types = array();
      $stmt = "SELECT * FROM SI_PHONE_TYPE "
                . "ORDER BY print_order;";
      $rs = odbc_exec($si_cnx, $stmt);
      while(odbc_fetch_row($rs))
      {
         $tmp = new Phone_Line_Type();
         $tmp->id = trim(odbc_result($rs,1));
         $tmp->name = trim(odbc_result($rs,2));
         $tmp->description = trim(odbc_result($rs,3));
         array_push($this->phone_types, $tmp);
      }
      odbc_free_result($rs);
      //return(sizeof($this->phone_types));
   }
   
   function get_a_phone_type_from_list($array_loc)
   {
      return($this->phone_types[$array_loc]);
   }
   function phone_type_list_size()
   {
      return sizeof($this->phone_types);
   }
   
   /********************************************************************
   * END Phone Type List Functions
   ********************************************************************/
   function set_company($co)
   {
      $this->company = new Phone_Company("", $co);
   }
   
   /********************************************************************
   * Load Phone Line List for store
   ********************************************************************/
   
   function db_load_lines($si_cnx, $site_num)
   {
      $stmt = "SELECT t.type_id, t.name, l.phone_num "
            . "FROM SI_PHONE_LINE l, SI_PHONE_TYPE t "
            . "WHERE l.type_id = t.type_id "
            . "AND l.dept_id = " . $site_num 
            . "ORDER BY t.print_order;";
      
      /*
        $plt = new Phone_Line_Type('x');
      $plt->name = $stmt;
      $line = new Phone_Line($plt, '2222');
      array_push($this->line_list, $line); 
      */
      $rs = odbc_exec($si_cnx, $stmt);
      while(odbc_fetch_row($rs))
      {
         $plt = new Phone_Line_Type(trim(odbc_result($rs,1)));
         $plt->name = trim(odbc_result($rs, 2));
         $line = new Phone_Line($plt, trim(odbc_result($rs,3)));
         array_push($this->line_list, $line); 
      }
      odbc_free_result($rs);
   }
   /********************************************************************
   * END Phone Type List Functions
   ********************************************************************/

}

?>