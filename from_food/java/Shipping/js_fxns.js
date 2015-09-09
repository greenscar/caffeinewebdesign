var browser = "";
function init()
{   
   if(document.getElementById)
   {
      // browser implements part of W3C DOM HTML
      // Gecko, Internet Explorer 5+, Opera 5+
      layerRef="document.getElementById";
      styleSwitch=".style";
      visibleVar="visible";
      browser="moz";
   }
   else if(document.all)
   {
      // Internet Explorer 4 or Opera with IE user agent
      layerRef="document.all";
      styleSwitch=".style";
      visibleVar="visible";
      screenSize = document.body.clientWidth + 18;
      browser ="ie";
   }
   else if (document.subMenus)
   {
      // Navigator 4
      layerRef="document.subMenus";
      styleSwitch="";
      visibleVar="show";
      screenSize = window.innerWidth;
      browser ="ns4";
   }
   else
   {
      //alert("Older than 4.0 browser.");
      browser="none";
      newbrowser = false;
   }
   //alert("browser == " + browser + "\ntimeoutID = " + timeoutID);
   window.status = browser;
    //window.status= new Date();
   check = true;
}

/*
* To use this file, place the following in the header:
* 
* <script language="JavaScript" src="js_fxns.js">
* </script>
*/

function itemCostCompute(itemID)
{
    var costVal = (eval("order.cost_" + itemID)).value;
    var quantVal = (eval("order.quantity_" + itemID)).value;
    var totalField = (eval("order.sum_" + itemID));
    costVal = costVal.substring(1, costVal.length);
    if(only_numbers("order", "quantity_" + itemID, "quantity"))
    {
        var totalCost = costVal * quantVal;
        totalField.value = "$"+currencyFormat(totalCost);
    }
}

function currencyFormat(amount)
{
	var i = parseFloat(amount);
	if(isNaN(i)) { i = 0.00; }
	var minus = '';
	if(i < 0) { minus = '-'; }
	i = Math.abs(i);
	i = parseInt((i + .005) * 100);
	i = i / 100;
	s = new String(i);
	if(s.indexOf('.') < 0) { s += '.00'; }
	if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
	s = minus + s;
	return s;
}

function only_numbers(form, the_field, descr){
    var nums = /^[0-9]+$/;
    obj_field = eval(form + "." + the_field);
    if(obj_field.value == "") obj_field.value = 0;
    if(!nums.test(obj_field.value))
    {
      alert ("Please enter a valid " + descr);
      obj_field.select();
      obj_field.focus();
      return (false);
    }
    return (true);
}
function addRecipients()
{
    var rec = document.recipientList.recipients;
    var posRec = document.recipientList.possibleRecipients;
    moveValues(posRec,rec);
}
function delRecipients()
{
    var rec = document.recipientList.recipients;
    var posRec = document.recipientList.possibleRecipients;
    moveValues(rec, posRec);
}
function startOrder()
{
    if(document.recipientList.recipients.length == 0)
    {
        alert("You must have at least one recipient.");
        return(false);
    }
    selectAll(document.recipientList.recipients);
/*    
    var billToAnswered = false;
    for(var k=0; k < document.recipientList.billTo.length; k++)
    {
        if(document.recipientList.billTo[k].checked)
            billToAnswered = true;
    }
    if(billToAnswered)
        return(true);
    else
    {
        alert("Please select who you would like the bill to go to.");
        return(false);
    }
*/
}
function setFocus() 
{ 
    if (document.forms.length > 0) 
    { 
        var el, type, i = 0, j, els = document.forms[0].elements; 
        while (el = els[i++]) 
        { 
            j = 0; 
            while (type = arguments[j++])
            {
                if (el.type == type)
                {
                    el.focus(); 
                    return(true);
                }
            }
        } 
    } 
}


/*
 * Deselect everyone in the provided list box.
 */
function deselectAll(listBox)
{
    for(var q=0; q < listBox.length; q++)
    {
        listBox[q].selected = false;
    }
}

/*
 * Select everyone in the provided list box.
 */
function selectAll(listBox)
{
    for(var q=0; q < listBox.length; q++)
    {
        listBox[q].selected = true;
    }
    return(false);
}


/*
 * This function adapted from the algorithm given in:
 *			Data Abstractions & Structures Using C++, by
 *			Mark Headington and David Riley, pg. 586.
 *
 *		Quicksort is the fastest array sorting routine for
 *		unordered arrays.  Its big O is n log n.
 * 3 arguments: 
 *      vec => the array you want sorted.
 *      loBound => where to start sorting (0)
 *      hiBound => where to end sorting (vec.length - 1)
 */

function Quicksort(vec, loBound, hiBound) {
        var valuePivot, loSwap, hiSwap, tempValue;
        var tempText, textPivot;
        // Two items to sort
        if (hiBound - loBound == 1)
        {
            if (vec[loBound].value > vec[hiBound].value)
            {
                tempValue = vec[loBound].value;
                vec[loBound].value = vec[hiBound].value;
                vec[hiBound].value = tempValue;
                
                tempText = vec[loBound].text;
                vec[loBound].text = vec[hiBound].text;
                vec[hiBound].text = tempText;
                
            }
            return;
        }

        // Three or more items to sort
        valuePivot = vec[parseInt((loBound + hiBound) / 2)].value;
        textPivot = vec[parseInt((loBound + hiBound) / 2)].text;
        vec[parseInt((loBound + hiBound) / 2)].value = vec[loBound].value;
        vec[parseInt((loBound + hiBound) / 2)].text = vec[loBound].text;
        vec[loBound].value = valuePivot;
        vec[loBound].text = textPivot;
        loSwap = loBound + 1;
        hiSwap = hiBound;

        do {
            // Find the right loSwap
            while (loSwap <= hiSwap && vec[loSwap].value <= valuePivot)
                loSwap++;

            // Find the right hiSwap
            while (vec[hiSwap].value > valuePivot)
                hiSwap--;

            // Swap values if loSwap is less than hiSwap
            if (loSwap < hiSwap)
            {
                tempValue = vec[loSwap].value;
                vec[loSwap].value = vec[hiSwap].value;
                vec[hiSwap].value = tempValue;
                
                tempText = vec[loSwap].text;
                vec[loSwap].text = vec[hiSwap].text;
                vec[hiSwap].text = tempText;
            }
        } while (loSwap < hiSwap);

        vec[loBound].value = vec[hiSwap].value;
        vec[hiSwap].value = valuePivot;

        vec[loBound].text = vec[hiSwap].text;
        vec[hiSwap].text = textPivot;

        // Recursively call function...  the beauty of quicksort

        // 2 or more items in first section        
        if (loBound < hiSwap - 1)
            Quicksort(vec, loBound, hiSwap - 1);


        // 2 or more items in second section
        if (hiSwap + 1 < hiBound)
            Quicksort(vec, hiSwap + 1, hiBound);
}


/*
 * moveValues moves the items selected in source from source to destination.
 * source and destination are each select boxes.
 * 1) The item(s) are copied over to the destination.
 * 2) The item(s) are deleted from the source.
 * 3) The destination is sorted with the Quicksort algorithm.
 */
function moveValues(source, destination)
{
    var somethingMoved = false;
    for(var i=0; i < source.length; i++)
    {
        if(source[i].selected)
        {
            var k = destination.length;
            destination.options[k] = new Option(source[i].text, source[i].value, false, false);
            source[i] = null;
            somethingMoved = true;
            i--;
        }
    }
    if(somethingMoved)
        Quicksort(destination, 0, destination.length - 1);
}