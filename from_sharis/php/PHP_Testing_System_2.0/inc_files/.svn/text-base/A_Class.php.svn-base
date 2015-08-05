<?php
class A_Class extends A_Course{
	var $class_seq
	var $teacher;
	var $start_date;
	var $students[];
	function A_Class($name, $number, $dept_seq, $prereq[], $tests[], $teacher, $date_started){
		$this->Course($name, $number, $dept_seq, $prereq[], $tests[]);
		$this->teacher = $teacher;
		$this->start_date = $date_started;
	}
	function enroll($student){
		$install = "INSERT INTO enrollment_dtl " .
					"(class_seq, student_seq, active) VALUES(" .
					$this->class_seq . ", " . $student->emp_seq .
					. ", 1);";
		@mssql_query($install) or die("Error in $install<BR>");		
		array_push($this->students, $student);
	}
	function drop($student){
		$drop = "UPDATE enrollment_dtl SET active = 0 " .
				"WHERE class_seq = " . $this->class_seq .
				"AND student_seq = " . $student->emp_seq;
		@mssql_query($drop) or die("Error in $drop<BR>");		
	}
	function get_student_list(){
		
	}
	function 
}
?>
