<?php
/**
* Database class
*
* @author    Sven Wagener <sven.wagener@intertribe.de>
* @copyright Sven Wagener
* @include  Funktion:_include_
*/
class database{
var $database_types="";

var $db_connect="";
var $db_close="";
var $db_select_db="";
var $db_query="";
var $db_fetch_array="";
var $db_num_rows="";

var $host;
var $database;
var $user;
var $password;
var $port;
var $database_type;

var $sql;

var $con; // variable for connection id
var $con_string; // variable for connection string
var $query; // variable for query id

var $errors; // variable for error messages
var $error_count=0; // variable for counting errors

/**
* Constructor of class - Initializes class and connects to the database
* @param string $database_type the name of the database (db2,informix,msql,mssql,mysql,oracle,postgres)
* @param string $host the host of the database
* @param string $database the name of the database
* @param string $user the name of the user for the database
* @param string $password the passord of the user for the database
* @desc Constructor of class - Initializes class and connects to the database.
*
*  You can use this shortcuts for the database type:
*
* ifx -> INFORMIX
* msql -> mSQL
* mssql -> Microsoft SQL Server
* mysql -> MySQL
* pg -> Postgres SQL
*sybase -> Sybase
*/
function database($database_type,$host,$database,$user,$password,$port=false){
$this->host=$host;
$this->database=$database;
$this->user=$user;
$this->password=$password;
$this->port=$port;

$this->database_types=array("ifx","msql","mssql","mysql","pg","sybase");

// Setting database type and connect to database
if(in_array($database_type,$this->database_types)){
$this->database_type=$database_type;

$this->db_connect=$this->database_type."_connect";
$this->db_close=$this->database_type."_close";
$this->db_select_db=$this->database_type."_select_db";
$this->db_query=$this->database_type."_query";
$this->db_fetch_array=$this->database_type."_fetch_array";
$this->db_num_rows=$this->database_type."_num_rows";


if(!$this->connect()){
return false;
}else{
return true;
}
}else{
return false;
}
}

/**
* This function connects the database
* @return boolean $is_connected Returns true if connection was successful otherwise false
* @desc This function connects to the database which is set in the constructor
*/
function connect(){
// Selecting connection function and connecting

// INFORMIX
if($this->database_type=="ifx"){
$this->con=call_user_func($this->db_connect,$this->database."@".$this->host,$this->user,$this->password);
}else if($this->database_type=="mysql"){
// With port
if(!$this->port){
$this->con=call_user_func($this->db_connect,$this->host.":".$this->port,$this->user,$this->password);
}
// Without port
else{
$this->con=call_user_func($this->db_connect,$this->host,$this->user,$this->password);
}
// mSQL
}else if($this->database_type=="msql"){
$this->con=call_user_func($this->db_connect,$this->host,$this->user,$this->password);
// MS SQL Server
}else if($this->database_type=="mssql"){
$this->con=call_user_func($this->db_connect,$this->host,$this->user,$this->password);
// Postgres SQL
}else if($this->database_type=="pg"){
// With port
if(!$this->port){
$this->con=call_user_func($this->db_connect,"host=".$this->host." port=".$this->port." dbname=".$this->database." user=".$this->user." password=".$this->password);
}
// Without port
else{
$this->con=call_user_func($this->db_connect,"host=".$this->host." dbname=".$this->database." user=".$this->user." password=".$this->password);
}
// Sybase
}else if($this->database_type=="sybase"){
$this->con=call_user_func($this->db_connect,$this->host,$this->user,$this->password);
}

if(!$this->con){
$this->errors[$this->error_count]="Wrong connection data! Can't establish connection to host.";
$this->error_count++;
return false;
}else{
if(!call_user_func($this->db_select_db,$this->database,$this->con)){
$this->errors[$this->error_count]="Wrong database data! Can't select database.";
$this->error_count++;
return false;
}else{
return true;
}
}
}

/**
* This function disconnects from the database
* @desc This function disconnects from the database
*/
function disconnect(){
if(call_user_func($this->db_close,$this->con)){
return true;
}else{
return false;
}
}

/**
* This function starts the sql query
* @param string $sql_statement the sql statement
* @desc This function disconnects from the database
*/
function query($sql_statement){
$this->sql=$sql_statement;
$this->query=call_user_func($this->db_query,$this->sql,$this->con);
}

/**
* This function returns a row of the resultset
* @return array $row the row as array or false if there is no more row
* @desc This function returns a row of the resultset
*/
function get_row(){
$row=call_user_func($this->db_fetch_array,$this->query);
return $row;
}

/**
* This function returns all tables of the database in an array
* @return array $tables all tables of the database in an array
* @desc This function returns all tables of the database in an array
*/
function get_tables(){
                $tables = "";
                $sql="SHOW TABLES";
                $this->query($sql);
                for($i=0;$data=$this->get_row();$i++){
            $tables[$i]=$data['Tables_in_'.$this->database];
                }  
                return $tables;
}

/**
* Returns all occurred errors
* @return array $errors all occurred errors as array
* @desc Returns all occurred errors
*/
function get_errors(){
return $this->errors;
}
}
?>