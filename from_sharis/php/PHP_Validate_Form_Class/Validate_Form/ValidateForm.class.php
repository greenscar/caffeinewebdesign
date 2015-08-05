<?php
 
/**
 * Validate form input fields,options include check if field is empty,check for valid email address using both Regex and  a DNS look-up, check for valid URL using Regex and DNS look-up,check field contains a numeric value, check field contains a numeric value within a certain range, check the field contains letters only and set the minimum um length of a string a user can enter
 *
 * @author George Mclachlan <george@experimentalmonkey.co.uk>
 * @Version 1.1
 * Note: This script will only work in versions of PHP 4.1.2 and greater 
 * @access public
 * @copyright (c) George Mclachlan 2002
 */

class ValidateForm {
 			
			/**
			 * Error list 
			 *
			 * @var string
			 * @access public 
			 * @see ValidateForm()
			 */
 			 var $error;
 			
    /**
     * Checks for error's  and stores them in an array
	 *
	 * @return array
     * @access public
	 */ 			 
 			 
 			 function ValidateForm() {
 			 	
 			 	  $this->error=array();
 			 }
 			 
 			 
 			 /**
 			   * Check's how our form is processed either with POST or GET then returns the field content.
			   *
			   * @param string $field 
			   * @return string
			   * @access public
			   * @see ValidateEmpty()
			   * @see ValidateLen()
			   * @see ValidateEmail()
			   * @see ValidateIsStr()
			   * @see ValidateUrl()
			   * @see ValidateIsNum()
			   * @see ValidateRange()
 			   */
 			 
 			 function FieldValue($field) {
 			   			  		
 			  		if(!$_POST[$field]){
 			  	       
 			  			return trim($_GET[$field]);
 			  			
 			  		} else {
 			  		
 			  			return trim($_POST[$field]);
 			  		}
 			  }   
 			
 			
 			  /**
 			   * Checks if the field name has a value and returns false if empty
			   *
 			   * @param string $field Name of field you wish to check 
			   * @param string $err_msg This message if displayed if the method returns false
			   * @return boolean
			   * @access public
 			   */
 			  
 			  
 			  function ValidateEmpty ($field, $err_msg) {
 			  	
 			  		$field_value = $this->FieldValue($field);
 			  		
 			  			if(!$field_value) {
 			  				
 			  				$this->error[] = array ( "field" => $field, 
 			  										 "err_msg" =>$err_msg
 			  										 );
 			  										 
 									return false;
 			  			}					
 			  		
 			  				return true;
 				
 		  }	
 			 
 		  
 		     /**
 			   * Checks if the field returns a string a certain length
			   *
			   * @param string $field Name of field you wish to check 
			   * @param int $min_len This set's the minimum length of the field
			   * @param string $err_msg This message if displayed if the method returns false
 			   * @return boolean
			   * @access public
 			   */  

 		    function ValidateLen ($field,$min_len,$err_msg) {
 		    	
 		    	$field_value = $this->FieldValue($field);
 		    	
 		    	  if(strlen($field_value) < $min_len) { 
 		    	  	
 		    	  	$this->error[] = array ( "field" => $field, 
 			  								 "err_msg" =>$err_msg
 			  										 );
 		    	  	
 		    	  	 return false;
 		    	  
 		    	  }
 		  
 		    	  return true;
 		    	  
 		    }
 		  		  
              /**
 			   * A simple check on an email address using regex, then a MX look up of the host name
			   *
 			   * @param string $field Name of field you wish to check
			   * @param string $err_msg This message if displayed if the method returns false
			   * @return boolean
			   * @access public
			   * 
 			   */                          
                    
 		  
                    function ValidateEmail($field,$err_msg) {
                    	
                    	$field_value = $this->FieldValue($field);
                    	
                    	if( !ereg("^[0-9,a-z,A-Z]+@[0-9,a-z,A-Z\_-]+.[0-9,a-z,A-Z\_-]+.*", $field_value ) ) 	
                    	
                    	{
						$this->error[] = array ( "field" => $field, 
 			  									 "err_msg" =>$err_msg
 			  										 );
                    		return false;
							
                    	} 
                    	
                    	                     	                 	  
                    	  $email_check=split("@",$field_value);
                    	                      	 
                    	  $email_url="www.".$email_check[1];
                    	                      	 
                    	  if(!checkdnsrr($email_url, mx)) {
                    	  	
                    	  	$this->error[] = array ( "field" => $field, 
 			  									 "err_msg" =>$err_msg
 			  										 );
 			  										 
                    	  }
							
                    		return true;
                    	
                    }

                    
              /**
			   * Checks if the field returns a string 
			   *
 			   * @param string $field Name of field you wish to check 
			   * @param string $err_msg This message if displayed if the method returns false
			   * @return boolean
			   * @access public
 			   */  
                    	                   
                    function ValidateIsStr($field,$err_msg) {
                    	
                    	$field_value = $this->FieldValue($field);
                    	
                    	if(!is_string(field_value)) {
                    			$this->error[] = array ( "field" => $field, 
 			  											 "err_msg" =>$err_msg
 			  										 );
                    		return false;
                    	}
                    		return true;
                    	
                    	
                    }
                    
              /**
			   *
 			   * Checks if the field returns a Valid URL, first by using a regex to check entry then performs an MX lookup 
			   *
 			   * @param string $field Name of field you wish to check
			   * @param string $err_msg This message if displayed if the method returns false
			   * @access public
 			   */ 
			   
                    function ValidateUrl($field, $err_msg) {

                    	$field_value = $this->FieldValue($field);

                    	if(!eregi("^http://[A-Za-z0-9\%\?\_\:\~\/\.-]+[.]([0-9,a-z,A-Z]){2}([0-9,a-z,A-Z])?$",$field_value)) {
                    	 	
                    		$this->error[] = array ("field" => $field, 
 			  										"err_msg" =>$err_msg
 			  										 );
                    	 	
 			  					  return false;
                    	 }
                    	 
                    	   $url=parse_url($field_value);

                    	   if(!checkdnsrr($url[host],ANY)) {
                    	   	
                    	   	$this->error[] = array ("field" => $field, 
 			  										"err_msg" =>$err_msg
 			  										 );
                    	   	return false;
                    	   	
                    	   }
                    	 
                    	 return true;
                    	 
                    }
                    
              /**
 			   * Checks if the field returns a number
 			   * 
			   * @param string $field Name of field you wish to check
			   * @param string $err_msg This message if displayed if the method returns false
			   * @return boolean
			   * @access public
 			   */  
                    
                    function ValidateIsNum($field,$err_msg) {
                    	
                    	$field_value = $this->FieldValue($field);
                    	
                    	if(!is_numeric($field_value)) {
                    			
                    		$this->error[] = array ( "field" => $field, 
 			  										 "err_msg" =>$err_msg
 			  										 );
                    		return false;
                    	
                    	} 	                    	
                    		return true;
                    	
                    }

              /**
 			   * Checks if the field returns a number within a certain range
			   *
               * @param string $field Name of field you wish to check
			   * @param int $min Set's the minimum number you wish a user to enter
			   * @param int $max Set's the maximum number you wish a user to enter
			   * @param string $err_msg This message if displayed if the method returns false
 			   * @return boolean
			   * @access public
 			   */  

					function ValidateRange($field,$min,$max,$err_msg) {
				
						$field_value = $this->FieldValue($field);
					
					    if($field_value < $min || $field_value > $max) {
					    	
					    	$this->error[] = array ( "field" => $field, 
 			  										 "err_msg" =>$err_msg
 			  										 );
					    	 return false;
					    
					    }

					    return true;
					}
                    	
                    	
              /**
 			   * Checks if the error list contains any errors
			   *
 			   * @return array
			   * @access public
 			   */  
                    
                  function ErrorList() {
                  	
                  	 if(sizeof($this->error) > 0 ) {
                  	 	return true;
                  	 }else {
                  	 	return false;
                  	 	
                  	 }
                  }
                  
              /**
 			   * If the error list contains any errors then it will display them
 			   *
			   * @return string
			   * @access public
 			   */  
                  
             function  DisplayError() {

                  echo"<ul>";
                             	  	   
             	foreach ($this->error as $e) {
             		
 		echo "<li>".ucfirst($e['field']). ": ". $e['err_msg'] ."</li>";
 		
 		}
 		echo"</ul>";
             
      }
              
	}

?>  			
 			  			