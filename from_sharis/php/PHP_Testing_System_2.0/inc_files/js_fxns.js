	function validateData(theForm){
		// only allow numbers to be entered
		var checkOK = "0123456789";
		var checkID = theForm.emp_num.value;
		var checkSSN = theForm.ssn.value;
		var allValid = true;
		var allNum = "";
		for (i = 0;  i < checkID.length;  i++)
		{
			ch = checkID.charAt(i);
			for (j = 0;  j < checkOK.length;  j++)
				if (ch == checkOK.charAt(j)) break;
			if (j == checkOK.length)
			{
				allValid = false;
				break;
			}
			if (ch != ",") allNum += ch;
		}
		if (!allValid)
		{
			alert("Your username should be numbers only (Your payroll ID).");
			theForm.emp_num.focus();
			return (false);
		}
		for (i = 0;  i < checkSSN.length;  i++)
		{
			ch = checkSSN.charAt(i);
			for (j = 0;  j < checkOK.length;  j++)
				if (ch == checkOK.charAt(j)) break;
			if (j == checkOK.length)
			{
				allValid = false;
				break;
			}
			if (ch != ",") allNum += ch;
		}
		if (!allValid)
		{
			alert("Your password should be numbers only.");
			theForm.ssn.focus();
			return (false);
		}
	}
function verifyDelete(){
	if(confirm("Are you sure you wish to delete this question?")){
		var q_num = document.question_mod_form.question_num.value;
		var tys = document.question_mod_form.type_seq.value;
		var tes = document.question_mod_form.test_seq.value;
		var q_seq = document.question_mod_form.question_seq.value;
		location.href="./question_delete.php?qn=" + q_num + "&typseq=" + tys + "&tesseq=" + tes + "&qs=" + q_seq;
	}
}

function check_num_blanks(){
	type_seq = document.form1.next_question_type_seq.value;
	if((type_seq == 5)){
		//pop up window asking the number of blanks
		var next_num_choices = prompt ("How many blanks do you want in this question?", "1");
		document.form1.next_num_blanks.value = next_num_choices;
	}
	else if(type_seq == 4){
		//pop up window asking the number of blanks
		var next_num_blanks = prompt ("How many questions do you want?", "1");
		document.form1.next_num_blanks.value = next_num_blanks;
		//pop up window asking the number of choices
		var next_num_choices = prompt ("How many answers do you want to choose from?", "1");
		document.form1.next_num_choices.value = next_num_choices;
	}
	else if(type_seq == 9){
		//pop up window asking the number of choices
		var next_num_blanks = prompt ("How many questions do you want?", "1");
		document.form1.next_num_blanks.value = next_num_blanks;
		//pop up window asking the number of blanks
		var next_num_choices = prompt ("How many words do you want to choose from?", "1");
		document.form1.next_num_choices.value = next_num_choices;
	}
	
	document.form1.submit();
}

function get_word_list_info(form_name){
	var orig_choices = document.all.item(form_name).num_choices.value;
	var orig_blanks = document.all.item(form_name).num_blanks.value;
	
	//pop up window asking the number of blanks
	var next_num_blanks = prompt ("How many questions do you want?", orig_blanks);
	var temp = orig_blanks - next_num_blanks;
	if(next_num_blanks < orig_blanks){
		if(!confirm("This will delete the last " + temp + " words to choose from. Do you wish to do that?"))
			return;
	}		
	//pop up window asking the number of choices
	var next_num_choices = prompt ("How many words do you want to choose from?", orig_choices);
	var temp = orig_choices - next_num_choices;
	if(orig_choices > next_num_choices){
		if(!confirm("This will delete the last " + temp + " questions. Do you wish to do that?")){
			return;
		}
	}		
	document.all.item(form_name).num_blanks.value = next_num_blanks;
	document.all.item(form_name).num_choices.value = next_num_choices;
	document.all.item(form_name).submit();
}
function get_fib_info(form_name){
	var orig_num = document.all.item(form_name).num_blanks.value;
	var next_num_choices = prompt ("How many blanks do you want in this question?", orig_num);
	var temp = orig_num - next_num_choices ;
	var ask = temp > 0;
	if(ask){
		if(!confirm("This will delete the last " + temp + " answers from your list. Do you wish to do that?"))
			return;
	}
	document.all.item(form_name).num_blanks.value = next_num_choices;
	document.all.item(form_name).submit();
}

function get_matching_info(form_name){
	var orig_choices = document.all.item(form_name).num_choices.value;
	var orig_blanks = document.all.item(form_name).num_blanks.value;
	
	//pop up window asking the number of blanks
	var next_num_blanks = prompt ("How many blanks do you want in this question?", orig_blanks);
	var temp = orig_blanks - next_num_blanks;
	if(next_num_blanks < orig_blanks){
		if(!confirm("This will delete the last " + temp + " blanks from this question. Do you wish to do that?"))
			return;
	}		
	//pop up window asking the number of choices
	var next_num_choices = prompt ("How many choices do you want in this question?", orig_choices);
	var temp = orig_choices - next_num_choices;
	if(orig_choices > next_num_choices){
		if(!confirm("This will delete the last " + temp + " choices from this question. Do you wish to do that?")){
			return;
		}
	}		
	document.all.item(form_name).num_blanks.value = next_num_blanks;
	document.all.item(form_name).num_choices.value = next_num_choices;
	document.all.item(form_name).submit();
}
