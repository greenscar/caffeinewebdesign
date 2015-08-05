var activeSub=0;
var SubNum=0;

//function reDo(){ window.location.reload() }
//window.onresize = reDo;

    //Define global variables

	    var timerID = null;
		var timerOn = false;
		var timecount = 500;
		var what = null;
		var newbrowser = true;
		var check = false;

    	function init(){
    	//  alert ("Running Init");
          if (document.layers) {
                      //  alert ("Running Netscape 4");
                        layerRef="document.layers";
                        styleSwitch="";
                        visibleVar="show";
			screenSize = window.innerWidth;
			what ="ns4";


          }else if(document.all){
                      //  alert ("Running IE");
                        layerRef="document.all";
                        styleSwitch=".style";
                        visibleVar="visible";
			screenSize = document.body.clientWidth + 18;
			what ="ie";

		  }else if(document.getElementById){
                      //  alert ("Running Netscape 6");
                        layerRef="document.getElementByID";
                        styleSwitch=".style";
                        visibleVar="visible";
			what="moz";
		  
		  }else{
		  	//alert("Older than 4.0 browser.");
			what="none";
			newbrowser = false;
		  }
		  
 
		window.status='Accelrys Inc -- Software for Pharmaceutical, Chemical, and Materials Research';
		check = true;
  	 	}

	// Turns the layers on and off
        function showLayer(layerName){
        	if(check){
        		if (what =="none"){
        			return;
        			}
	        	else if (what == "moz"){
        			document.getElementById(layerName).style.visibility="visible";
        			}
        		else{
                  eval(layerRef+'["'+layerName+'"]'+styleSwitch+'.visibility="visible"');
                  }
		 }
        	else {// alert ("Please wait for the page to finish loading.");
        		return;}
		}

        function hideLayer(layerName){
        	if(check){
        		if (what =="none"){
        			return;
        			}
        		else if (what == "moz"){
        			document.getElementById(layerName).style.visibility="hidden";
        			}
        		else{
                  eval(layerRef+'["'+layerName+'"]'+styleSwitch+'.visibility="hidden"');
				}
        
        	}
        	else {// alert ("Please wait for the page to finish loading.");
        		return;}
        }


		function hideAll(){
				hideLayer('aboutmenu');
				hideLayer('sciencemenu');
				hideLayer('indusmenu');
				hideLayer('prodmenu');
				hideLayer('custmenu');
				}


		function startTime() {
	        if (timerOn == false) {
                timerID=setTimeout( "hideAll()" , timecount);
                timerOn = true;

	        }

		}


		function stopTime() {
	        if (timerOn) {
    	        clearTimeout(timerID);
                timerID = null;
                timerOn = false;
	        }
		}

		function onLoad(){
			init();
			
			}
	
