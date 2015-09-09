
	/*******************************************************************
	 *	linkOrganizer.js Paul Baggethun, Pittsburgh PA, 2002
	 *******************************************************************/

	var linkTarget="_blank"; // placeholder for the anchor target values
	var oVisibleCategory = new Object(); // holds the currently visible link category object (a div block)

	function toggleCategory(sCategory) {
		/* Turns display off for current link category and turns it on
		 * for the new selected category. Then updates the current 
		 * category object placehoder. */
		if(oVisibleCategory.style) {oVisibleCategory.style.display='none';}
		if (sCategory.length>0) {
			document.getElementById(sCategory).style.display='block';
			oVisibleCategory=document.getElementById(sCategory);
		}
	}

	function init() {
		/* This onload function makes sure the page is properly displayed
		 * when the user returns by using the browser forward or back buttons */
		var dropMenu = document.getElementById("categories");
		toggleCategory(dropMenu.options[dropMenu.selectedIndex].value);
		var targetOptns = document.getElementsByName("linktarget");
		var i;
		for (i=0;i<targetOptns.length;i++) {
			if (targetOptns[i].checked) linkTarget=targetOptns[i].value;
		}
	}
