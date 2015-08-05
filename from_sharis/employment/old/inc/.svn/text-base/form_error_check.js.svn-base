
function checkField(theForm, theField, theFieldDisplay, objRegex)
{ 
    objField = eval("document." + theForm + "." + theField); 
    if(!objRegex.test(objField.value))
    {
      alert ("Please enter a valid " + theFieldDisplay + "");
      objField.select();
      objField.focus();
      return (false);
    } 
    return (true);
}
function onlyNumbers(theForm, theField, errorFieldName)
{
    var nums = /^[0-9]+$/;
    return(checkField(theForm, theField, errorFieldName, nums));
}
    
function onlyChars(theForm, theField, errorFieldName)
{
    var chars = /\D/;
    return(checkField(theForm, theField, errorFieldName, chars));
}
function checkEmail(theForm, theField)
{
    
    var email = /(^[a-z]([a-z_\.]*)@([a-z_\.]*)([.][a-z]{3})$)|(^[a-z]([a-z_\.]*)@([a-z_\.]*)(\.[a-z]{3})(\.[a-z]{2})*$)/i;
    //var matchArray=theField.value.match(email); 
    //alert(theField.value);
    if((!email.test(theField.value)))
    {
        alert("The Email Address Is Invalid");
        theField.select();
        theField.focus();
        return(false);
    }
    return(true);
} 
function checkPhoneNumber(theForm, theField)
{
    // Must be XXX-XXX-XXXX
    var num = /^[0-9]{3}-[0-9]{3}-[0-9]{4}$/;
    if(!num.test(theField.value))
    {
        alert("Your phone number must be in format XXX-XXX-XXXX");
        theField.select();
        theField.focus();
        return(false);
    }
    return(true);
}
function checkForm(form){
    //displayAllFormVars(form);
    if(form.contact_name.value == "" && form.contact.value == "dm")
    {
        alert("If you wish to use DM as the contact, you must give his/her name.");
        return(false);
    }
    if((form.email_address.value != "") && (!form.email.checked)){
        alert("You have entered an email address but have not checked the box beside it.");
        return(false);
    }
    if((form.call_number.value != "") && (!form.call.checked))
    {
        alert("You have entered a phone number but have not checked the box beside it.");
        return(false);
    }
    if((form.other_instructions.value != "") && (!form.other.checked))
    {
        alert("You have entered other instructions but have not checked the box beside it.");
        return(false);
    }
    if(form.email.checked)
    {
        if(form.email_address.value == "")
        {
            alert("If you wish to have Email as an instruction, you must include an address.");
            return(false);
        }
        else
        {
            if(!checkEmail(form, form.email_address))
            {
                return(false);
            }
        }
    }
    
    if(form.call.checked)
    {
        if(form.call_number.value == "")
        {
            alert("The applicant cannot place a call without a phone number.");
            return(false);
        }
        else
        {
            if(!checkPhoneNumber(form, form.call_number))
            {
                return(false);
            }
        }
    }
    if(!(form.job_title.value == "ma" || form.job_title.value == "am"))
    {
        if(!(form.online.checked && form.in_person.checked))
        {
            if(!confirm("Are you sure you want a floor employee\nto not be both online and in person?"))
            {
                return(false);
            }
        }
    }
    if(form.other.checked && form.other_instructions.value == "")
    {
        alert("You must enter other instructions to use other as an option.");
        return(false);
    }
    if(confirm("Are you sure you want to submit this job"))
    {
        return(true);
    }
    else
    {
        return(false);
    }
}

function displayAllFormVars(form){
    var vars = "";
    for(elementNum=0; elementNum < form.length; elementNum++){
        vars += form.elements[elementNum].name + " = " + form.elements[elementNum].value + "\n";
    }
    alert(vars);
}

function lookUpStore()
{
	var nums = /^[0-9]+$/;
	var store_num = document.jobForm.store_number.value;
	if(nums.test(store_num))
	{
		var loc = "storeLookup.php?store_num=" + store_num;
		location.href(loc);
	}
	else
	{
		alert("Please enter the number of a store to look up.");
	}
	return(false);
	
}

