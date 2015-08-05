// Original Version Copyright 1998,1999 Macromedia, Inc. All rights reserved.
// Additional Netscape 6+ coding Copyright 2003 Liam Byrne - liam.byrne@online.ie

function MM_dragLayer(objName,x,hL,hT,hW,hH,toFront,dropBack,cU,cD,cL,cR,targL,targT,tol,dropJS,et,dragJS) { //v3.0
	//Copyright 1998 Macromedia, Inc. All rights reserved.
	var i,j,aLayer,retVal,curDrag=null,NS=(navigator.appName=='Netscape'), curLeft, curTop;

	oldNS=(NS && isntNewNS());

	if (!document.all && !document.layers && isntNewNS()) return false;

	retVal = true; if(!NS && event) event.returnValue = true;

	if (MM_dragLayer.arguments.length > 1) {
		curDrag = MM_findObj(objName); if (!curDrag) return false;

		if (!document.allLayers) { 
			document.allLayers = new Array();
			with (document) {
				if (NS) { 
					if (isntNewNS()) {
// Netscape 4.x
						for (i=0; i<layers.length; i++) {
							allLayers[i]=layers[i];
						}
				        	for (i=0; i<allLayers.length; i++) {
							if (allLayers[i].document && allLayers[i].document.layers) {
		        					with (allLayers[i].document) {
									for (j=0; j<layers.length; j++) {
										allLayers[allLayers.length]=layers[j];
									}
								}
							}
						}
				      } else {
// N.B. This section of the code requires the searchPrefixes to contain the default names of drag/drop layers
						searchPrefixes=new Array("Drag","DragTarget","Target");
						dragPrefix=objName.substring(0,3);
						for (sPindex=0;sPindex<searchPrefixes.length;sPindex++) {
							for (i=1;;i++) {
								tmp=MM_findObj(dragPrefix+searchPrefixes[sPindex]+i)
								if (!(tmp && tmp.style && tmp.style.position))  {
									break
								}
								allLayers[allLayers.length]=tmp
							}
						}
					}
		      	} else {
				// IE
					for (i=0;i<all.length;i++) {
						if (all[i].style&&all[i].style.position) {
							allLayers[allLayers.length]=all[i]; 
						}
					}
				}
			}
		}
		
		curDrag.MM_dragOk=true; curDrag.MM_targL=targL; curDrag.MM_targT=targT;
		curDrag.MM_tol=Math.pow(tol,2); curDrag.MM_hLeft=hL; curDrag.MM_hTop=hT;
		curDrag.MM_hWidth=hW; curDrag.MM_hHeight=hH; curDrag.MM_toFront=toFront;
		curDrag.MM_dropBack=dropBack; curDrag.MM_dropJS=dropJS;
		curDrag.MM_everyTime=et; curDrag.MM_dragJS=dragJS;
		curDrag.MM_oldZ = (oldNS)?curDrag.zIndex:curDrag.style.zIndex;
		curLeft= (oldNS)?curDrag.left:parseInt(curDrag.style.left); curDrag.MM_startL = curLeft;
		curTop = (oldNS)?curDrag.top:parseInt(curDrag.style.top); curDrag.MM_startT = curTop;
		curDrag.MM_bL=(cL<0)?null:curLeft-cL; curDrag.MM_bT=(cU<0)?null:curTop -cU;
		curDrag.MM_bR=(cR<0)?null:curLeft+cR; curDrag.MM_bB=(cD<0)?null:curTop +cD;
		curDrag.MM_LEFTRIGHT=0; curDrag.MM_UPDOWN=0; curDrag.MM_SNAPPED=false; //use in your JS!
		document.onmousedown = MM_dragLayer; document.onmouseup = MM_dragLayer;
		if (NS) {
			if (isntNewNS()) {
	    			document.captureEvents(Event.MOUSEDOWN|Event.MOUSEUP);
			} else {
				document.getElementById(objName).addEventListener("mousedown",MM_dragLayer, false);
				document.getElementById(objName).addEventListener("mouseup",MM_dragLayer, false);
			}
		}
  	} else {
		var theEvent = ((NS)?objName.type:event.type);

		if (theEvent == 'mousedown') {
			if (NS) {
				var mouseX= (oldNS)?objName.pageX:objName.clientX;
				var mouseY= (oldNS)?objName.pageY:objName.clientY;
			} else {
				var mouseX = event.clientX + document.body.scrollLeft;
				var mouseY = event.clientY + document.body.scrollTop;
			}
			var maxDragZ=null; document.MM_maxZ = 0;

			for (i=0; i<document.allLayers.length; i++) { 
				aLayer = document.allLayers[i];
				var aLayerZ = (oldNS)?aLayer.zIndex:aLayer.style.zIndex;

				if (Math.floor(aLayerZ) > document.MM_maxZ) document.MM_maxZ = Math.floor(aLayerZ);
				
				var isVisible = (((oldNS)?aLayer.visibility:aLayer.style.visibility).indexOf('hid') == -1);

				if (aLayer.MM_dragOk != null && isVisible) with (aLayer) {
					var parentL=0; var parentT=0;

					if (!(NS || oldNS)) { 
						parentLayer = aLayer.parentElement;
						while (parentLayer != null && parentLayer.style.position) {
							parentL += parentLayer.offsetLeft; parentT += parentLayer.offsetTop;
							parentLayer = parentLayer.parentElement; 
						}
					}

					var tmpX=mouseX-(((oldNS)?pageX:parseInt(style.left)+parentL)+MM_hLeft);
					var tmpY=mouseY-(((oldNS)?pageY:parseInt(style.top)+parentT)+MM_hTop);

					if (typeof (clip) == "undefined") { 
						cw=parseInt(style.width); ch=parseInt(style.height)
					} else {
						cw=parseInt(clip.width); ch=parseInt(clip.height)
					}

					var tmpW = MM_hWidth;  if (tmpW <= 0) tmpW += ((NS)?cw :offsetWidth);
					var tmpH = MM_hHeight; if (tmpH <= 0) tmpH += ((NS)?ch:offsetHeight);

					if ((0 <= tmpX && tmpX < tmpW && 0 <= tmpY && tmpY < tmpH) && (maxDragZ == null || maxDragZ <= aLayerZ)) { 
						curDrag = aLayer; maxDragZ = aLayerZ; 
					}
				}
			}


			if (curDrag) {

				document.onmousemove = MM_dragLayer; 

				if (NS) {
					if (isntNewNS()) {
			    			document.captureEvents(Event.MOUSEMOVE);
					} else {
						curDrag.addEventListener("mousemove",MM_dragLayer, false);
					}
				}

				curLeft = (oldNS)?curDrag.left:parseInt(curDrag.style.left);
				curTop = (oldNS)?curDrag.top:parseInt(curDrag.style.top);
				MM_oldX = mouseX - curLeft; MM_oldY = mouseY - curTop;
				document.MM_curDrag = curDrag;  curDrag.MM_SNAPPED=false;

				if(curDrag.MM_toFront) {
					eval('curDrag.'+((oldNS)?'':'style.')+'zIndex=document.MM_maxZ+1');
					if (!curDrag.MM_dropBack) document.MM_maxZ++; 
				}

				retVal = false; if(!NS) event.returnValue = false;
			}
		} else if (theEvent == 'mousemove') {

			if (document.MM_curDrag) with (document.MM_curDrag) {

				if (NS) {
					var mouseX= (oldNS)?objName.pageX:objName.clientX;
					var mouseY= (oldNS)?objName.pageY:objName.clientY;
				} else {
					var mouseX = event.clientX + document.body.scrollLeft;
					var mouseY = event.clientY + document.body.scrollTop;
				}

				newLeft = mouseX-MM_oldX; newTop  = mouseY-MM_oldY;
				if (MM_bL!=null) newLeft = Math.max(newLeft,MM_bL);
				if (MM_bR!=null) newLeft = Math.min(newLeft,MM_bR);
				if (MM_bT!=null) newTop  = Math.max(newTop ,MM_bT);
				if (MM_bB!=null) newTop  = Math.min(newTop ,MM_bB);
				MM_LEFTRIGHT = newLeft-MM_startL; MM_UPDOWN = newTop-MM_startT;
				if (oldNS) {
					left = newLeft; top = newTop;
				} else {
					style.left = newLeft; style.top = newTop;
				}

				if (MM_dragJS) eval(MM_dragJS);

				retVal = false; if(!NS) event.returnValue = false;
			}
		} else if (theEvent == 'mouseup') {
			document.onmousemove = null;
			if (oldNS) {
				document.releaseEvents(Event.MOUSEMOVE);
				document.captureEvents(Event.MOUSEDOWN); //for mac NS
			} 

			if (document.MM_curDrag) {
				if (NS && !(oldNS)) {
					document.MM_curDrag.removeEventListener("mousemove",MM_dragLayer, false);
				}
				with (document.MM_curDrag) {
					if (typeof MM_targL =='number' && typeof MM_targT == 'number' && (Math.pow(MM_targL-((NS)?left:style.left),2)+Math.pow(MM_targT-((NS)?top:style.top),2))<=MM_tol) {
						if (oldNS) {
							left = MM_targL; top = MM_targT;
						} else {
							style.left = MM_targL; style.top = MM_targT;
						}
						MM_SNAPPED = true; MM_LEFTRIGHT = MM_startL-MM_targL; MM_UPDOWN = MM_startT-MM_targT; 
					}
					if (MM_everyTime || MM_SNAPPED) eval(MM_dropJS);
		        		if(MM_dropBack) {
						if (NS) zIndex = MM_oldZ; else style.zIndex = MM_oldZ;
					}
				        retVal = false; if(!NS) event.returnValue = false; 
				} 
				document.MM_curDrag = null;
			}
		}
		if (NS) document.routeEvent(objName);
	} 
	return retVal;
}
