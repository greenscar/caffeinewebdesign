<?php
class A_Course{
	var $course_seq;
	var $name;
	var $number;
	var $department_seq;
	var $prereq[];
	var $test_sequence[];	
	function A_Course($name, $number, $dept_seq, $prereq[], $tests[]){
		$this->name = $name;
		$this->number = $number;
		$this->department_seq = $dept_seq;
		$this->prereq = $prereq;
		$this->test_sequence = $tests;	
	}
	function insert_into_db(){
		$insert = "INSERT INTO course_def VALUES (" .
				"'" . $this->name . "', " .
				"'" . $this->number . "', " .
				"'" . $this->department_seq . "', ";
		for($i=0; $i<5;$i++){
			$insert .= 	"'" . $this->prereq[$i] . "', ";
		}
		for($i=0; $i<15;$i++){
			$insert .= 	"'" . $this->test_sequence[$i] . "', ";
		}
		return mssql_query($insert) or die("Error in $insert<BR>");
	}
	/*
	function Course_class($course_seq){
		$query = "SELECT * FROM course_def WHERE course_seq = $course_seq";
		$result = mssql_query($query) or die("Error in $query");
		$line = mssql_fetch_array($result);
		$this->name = $line["name"];
		$this->seq = $line["course_seq"];
		$this->number = $line["number"];
		$this->department_seq = $line["department_seq"];
		for($i=0; $i<5;$i++){
			$temp = "prereq_" . $i;
			$this->prereq[$i] = $line[$temp];
		}
	}
	*/
	
	
}
?>