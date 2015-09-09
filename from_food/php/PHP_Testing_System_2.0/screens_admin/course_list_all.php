<?php
	$tests = "SELECT * FROM course_def, test_category_def " .
			"WHERE test_category_def.category_seq = course_def.category_seq ORDER BY course_def.course_number";
	$test_list = mssql_query($tests) or die("Error in $tests<BR>create_course line 39<BR>");
?>
	<TABLE ALIGN="CENTER" border="0">
		<TR>
			<TD ALIGN="CENTER">
				<H2 ALIGN = "CENTER"><U>Here is your course list</U></H2>
			</TD>
		</TR>
		<TR>
			<TD ALIGN="CENTER">
				<TABLE ALIGN="CENTER" BORDER="5" CELLPADDING="4">
					<TR>
						<TH>#</TH>
						<TH>NAME</TH>
						<TH>CATEGORY</TH>
						<TH>DESCRIPTION</TH>
						<TH>PREREQS</TH>
					</TR>
<?php
	while($a_course = mssql_fetch_array($test_list)){
		$ts = $a_course["test_seq"];
?>
					<TR>
						<TD>
							<?php echo(stripslashes($a_course["course_number"])); ?>
						</TD>
						<TD>
							<?php echo(stripslashes($a_course["course_name"]));?>
						</TD>
						<TD>
							<?php echo(stripslashes($a_course["category_name"]));?>
						</TD>
						<TD>
							<?php echo(stripslashes($a_course["description"]));?>
						</TD>
						<TD>
<?php
		//Assign all prerequisits to a string
		$count = 0;
		$prereq = "";
		for($i = 1; $i < 6; $i++){
			$temp = "prereq_seq_$i";
			if(!empty($a_course[$temp])){
				$select = "SELECT course_number FROM course_def WHERE course_seq = " . $a_course[$temp];
				$prereqseq = mssql_query($select);
				$prereqseq = mssql_fetch_array($prereqseq);
				$prereqseq = $prereqseq[0];
				if($count == 0){
					$prereq = $prereqseq;
					$count++;
				} 
				else $prereq .=  ", " . $prereqseq;
			}
		}
		//Display the prerequisit string
		if(empty($prereq)) $prereq = "&nbsp";
?>	
						<?php echo($prereq); ?>
					</TD>
				</TR>
<?php
	}
?>
			</TABLE>
		</TD>
		</TR>
	</TABLE>