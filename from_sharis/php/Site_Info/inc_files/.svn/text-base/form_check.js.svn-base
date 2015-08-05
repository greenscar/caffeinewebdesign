function validate_name_lookup(form)
{
    if((form.first_name.value.length == 0) || (form.last_name.value.length == 0))
    {
        alert("You must enter a first and last name.");
        return(false);
    }
}

function validate_create(form)
{
    if(!check_store_num(form, "store_number"))
        return(false);
    if(!(validate_date(form.date.value)))
    {
        alert("Please enter a valid opening date. (dd/mm/yyyy)");
        form.date.select();
        form.date.focus();
        return(false);
    }
    if(field_is_blank(form, "site_name", "Site Name"))
        return(false);
    if(field_is_blank(form, "street", "Street"))
        return(false);
    if(!field_only_chars(form, "city", "City"))
        return(false);
    if(!check_zip_code(form, "zip"))
        return(false);
    if(!check_sysco_num(form, "sysco_num", "Sysco Acct Num"))
        return(false);
    if(!check_phone_num(form, "phn_MN", "Main Phone Line"))
        return(false);
    if(!check_phone_num(form, "phn_FX", "RAS/Fax Line"))
        return(false);
    if(!check_phone_num(form, "phn_CRDT", "Credit Card Line"))
        return(false);
    if(!check_phone_num(form, "phn_PS", "POS Line"))
        return(false);
    if(!only_numbers(form, "mgr", "Manager Emp ID"))
        return(false);
    if(!only_numbers(form, "asst_mgr_1", "Asst Manager Emp ID"))
        return(false);
    if(!ensure_radio_checked(form, "smoking"))
        return(false);
    if(!ensure_radio_checked(form, "alcohol"))
        return(false);
    if(!ensure_radio_checked(form, "gaming"))
        return(false);
    return(true);
}

function check_phone_num(form, the_field, descr)
{
    var pn_reg_exp = /^[1-9][0-9]{2}-[1-9][0-9]{2}-[0-9]{4}$/;
    obj_field = eval("form." + the_field); 
    if(!pn_reg_exp.test(obj_field.value))
    {
      alert ("Please enter a " + descr + " in the format XXX-XXX-XXXX");
      obj_field.select();
      obj_field.focus();
      return (false);
    } 
    return (true);
}
function check_sysco_num(form, the_field)
{
    var zip_reg_exp = /^[0-9]{6}$/;
    obj_field = eval("form." + the_field); 
    if(!zip_reg_exp.test(obj_field.value))
    {
      alert ("Please enter a 6 digit Sysco Account number.\nBegin with 0 if needed.");
      obj_field.select();
      obj_field.focus();
      return (false);
    } 
    return (true);
}
function field_only_chars(form, the_field, descr)
{
    var string_reg_exp = /^[A-Za-z]+$/;
    obj_field = eval("form." + the_field);
    if(!string_reg_exp.test(obj_field.value))
    {
      alert ("Please enter a valid " + descr);
      obj_field.select();
      obj_field.focus();
      return (false);
    }
    return (true);
}
function field_is_blank(form, the_field, descr)
{
    var val = eval("form." + the_field);
    if(val.value.length > 0)
        return(false);
    else
        alert("Please enter the " + descr);
        eval("form." + the_field + ".select()");
        eval("form." + the_field + ".focus()");
        return(true);
}
function check_store_num(form, the_field)
{
    var zip_reg_exp = /^[0-9]{3}$/;
    obj_field = eval("form." + the_field);
    if(!zip_reg_exp.test(obj_field.value))
    {
      alert ("Please enter a valid store number");
      obj_field.select();
      obj_field.focus();
      return (false);
    }
    return (true);
}

function only_numbers(form, the_field, descr){
    var nums = /^[0-9]+$/;
    obj_field = eval("form." + the_field);
    if(!nums.test(obj_field.value))
    {
      alert ("Please enter a valid " + descr);
      obj_field.select();
      obj_field.focus();
      return (false);
    }
    return (true);
}
    
function check_zip_code(form, the_field)
{
    var zip_reg_exp = /^[0-9]{5}$/;
    obj_field = eval("form." + the_field); 
    if(!zip_reg_exp.test(obj_field.value))
    {
      alert ("Please enter a zip code in the number format XXXXX");
      obj_field.select();
      obj_field.focus();
      return (false);
    } 
    return (true);
}

function ensure_radio_checked(form, the_field)
{
    var length = eval("form." + the_field + ".length");
    for(var i=0; i < length; i++)
    {
        if(eval("form." + the_field + "[" + i + "].checked"))
        {
            return(true);
        }
    }
    alert("Please select an option on " + the_field);
    return(false);
}


function validate_date( strValue )
{
    var objRegExp = /^\d{1,2}(\-|\/|\.)\d{1,2}\1\d{4}$/
    
    //check to see if in correct format
    if(!objRegExp.test(strValue))
    {
        return(false);
    }
    else
    {
        var strSeparator = strValue.substring(2,3) //find date separator
        var arrayDate = strValue.split(strSeparator); //split date into month, day, year
        //create a lookup for months not equal to Feb.
        var arrayLookup = { '01' : 31,'03' : 31, '04' : 30,'05' : 31,'06' : 30,'07' : 31,
                            '08' : 31,'09' : 30,'10' : 31,'11' : 30,'12' : 31}
        var intDay = parseInt(arrayDate[0]);
        var intYear = parseInt(arrayDate[2]);
        var intMonth = parseInt(arrayDate[1]);
        var date = new Date();
        var thisYear = date.getYear();
        if(intMonth < 1 || intMonth > 12)
        {
            return(false);
        }
        //check if month value and day value agree
        if(arrayLookup[intMonth] != null)
        {
            if(intDay <= arrayLookup[arrayDate[1]] && intDay > 0)
            return true; //found in lookup table, good date
        }
        if(intYear < 1976)
        {
            alert("Come on now, the first store didn't open until 1978!!!");
            return(false);
        }
        if(intYear > date.getYear())
        {
            alert("You are predicting the future... I'd like to meet you.");
            return(false);
        }
        //check for February
        if( ((intYear % 4 == 0 && intDay <= 29) || (intYear % 4 != 0 && intDay <=28)) && intDay !=0)
            return true; //Feb. had valid number of days
    }
    return(false);
}

























/*************************************************************
 *************************************************************
 *************************************************************/

function check_field(form, the_field, the_field_display, obj_reg_exp){ 
    obj_field = eval("document." + form + "." + the_field); 
    if(!obj_reg_exp.test(obj_field.value))
    {
      alert ("Please enter a valid " + the_field_display + "");
      obj_field.select();
      obj_field.focus();
      return (false);
    } 
    return (true);
}
function only_chars(form, the_field, error_the_field){
    var chars = /\_d/;
    return(check_field(form, the_field, error_the_field, chars));
}