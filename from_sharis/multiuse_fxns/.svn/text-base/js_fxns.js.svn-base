
function displayAllFormVars(form)
{
    var vars = "";
    for(elementNum=0; elementNum < form.length; elementNum++){
        vars += form.elements[elementNum].name + " = " + form.elements[elementNum].value + "\n";
    }
    alert(vars);
}

function ensure_radio_checked(fieldName, form)
{
    var length = eval("form." + fieldName + ".length");
    for(var i=0; i < length; i++)
    {
        if(eval("form." + fieldName + "[" + i + "].checked"))
        {
            return(true);
        }
    }
    alert("Please select an option on " + fieldName);
    return(false);
}

/**************************** FIELD CHECKING ***************************************/
function checkField(theForm, theField, theFieldDisplay, objRegex){ 
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
function onlyNumbers(theForm, theField, errorFieldName){
    var nums = /^[0-9]+$/;
    return(checkField(theForm, theField, errorFieldName, nums));
}
    
function onlyChars(theForm, theField, errorFieldName){
    var chars = /\D/;
    return(checkField(theForm, theField, errorFieldName, chars));
}
function checkEmail(theForm, theField){
    
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
function checkPhoneNumber(theForm, theField){
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



/************************** END FIELD CHECKING *************************************/

/******************************* COOKIE FXNS ***************************************/
function getCookie(NameOfCookie){
    if (document.cookie.length > 0) {              
    begin = document.cookie.indexOf(NameOfCookie+"=");       
    if (begin != -1) {           
      begin += NameOfCookie.length+1;       
      end = document.cookie.indexOf(";", begin);
      if (end == -1) end = document.cookie.length;
        return unescape(document.cookie.substring(begin, end));
    } 
  }
  return null;
}

function setCookie(NameOfCookie, value, expiredays) {
	var ExpireDate = new Date();
	ExpireDate.setTime(ExpireDate.getTime() + (expiredays * 24 * 3600 * 1000));
  	document.cookie = NameOfCookie + "=" + escape(value) + 
  		((expiredays == null) ? "" : "; expires=" + ExpireDate.toGMTString());
}

function delCookie (NameOfCookie) {
  if (getCookie(NameOfCookie)) {
    document.cookie = NameOfCookie + "=" +
    "; expires=Thu, 01-Jan-70 00:00:01 GMT";
  }
}
function cookieExists(NameOfCookie){
	if(getCookie(NameOfCookie)){
		return true;
	}
	return false;
}
/***************************** END COOKIE FXNS *************************************/
