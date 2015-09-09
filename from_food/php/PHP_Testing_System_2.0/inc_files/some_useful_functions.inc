<?php

function remove_uncompleted_tests(){
	$temp = "SELECT take_seq FROM test_taken_dtl";
	$take_seq = mssql_query($temp);
	while($temp = mssql_fetch_array($take_seq)){
		$num = "SELECT COUNT(*) FROM student_answer_dtl WHERE take_seq = " . $temp[0];
		$count = mssql_query($num);
		echo("There are $num entries in answer table where take_seq = " . $temp[0] . "<BR>");
	}
}

/*
* This function will convert a multidimensional array into an XML TRee
* sTRing arr2xml (array array, [sTRing TRee_name], [[int level]) 
* use the second argument if you want to specify a name for the top of the TRee - 
* 	otherwise it defaults to level0. the first call to the function is kind of a 
* 	dummy top level that wraps the array inside another array and calls the function for real. 
* dont use the third argument. thats used to keep TRack of the levels in all the recursive calls. 
*/

function arr2xml ($arr)
{
	if (func_num_args() < 3)
	{
		$wrapper = (func_num_args() < 2) ? array ($arr) : array (func_get_arg(1)=>$arr);
		$xml = arr2xml ($wrapper, '', 0);
	}
	else
	{
		$level = func_get_arg (2);
		while (list ($key, $val) = each ($arr))
		{
			if ($key === (int)$key) $key = 'level'.$level;
				$xml .= '<'.$key.'>';
			if (gettype ($val) == 'array')
			{
				$xml .= arr2xml ($val, '', $level+1);
			}
			else
			{
				$xml .= $val;
			}
			$xml .= '</'.$key.'>';
			}
	}
	return $xml;
}

?>