<!--
direction = new Array(10);
var absolute_speed = 4;
timeoutID = new Array(10);
startY = new Array(10);
currentY = new Array(10);
destY = new Array(10);
moving = new Array(10);
var activeMenu = null;
var activeSubMenu = null;
layerVisible = new Array(10);
//var menuHeight = 20;


function pauseMe(Amount)
{
   d = new Date() //today's date
   while (1)
   {
      mill=new Date() // Date Now
      diff = mill-d //difference in milliseconds
      if( diff > Amount )
         break;
   }
}

function hideLayers()
{
    //alert("browser = " + browser);
    if(browser == "ns4")
    {
        document.transparent.visibility="hidden";
    }
    if(browser == "ie")
    {
        //alert("ie");
        document.all.transparent.style.visibility="hidden";
        //document.getElementById("transparent").style.visibility="hidden";
    }
    if(browser == "moz")
    {
        //alert("moz");
        document.getElementById("transparent").style.visibility="hidden";
    }
    //alert("done");
   for(var i=0; i < subMenus.length; i++)
   {
      hideLayer(subMenus[i]);
   }
}
function showLayer(subMenuName)
   {
    if(browser == "ns4")
    {
        document.transparent.visibility="visible";
    }
    if(browser == "ie")
    {
        document.all.transparent.style.visibility="visible";
    }
    if(browser == "moz")
    {
        document.getElementById("transparent").style.visibility="visible";
    }
   if(!layerVisible[subMenuName])
   {
      activeSubMenu = subMenuName;
      direction[subMenuName] = 1;
      moveLayer(subMenuName);
      layerVisible[subMenuName] = true;
   }   
   for(var i=0; i < subMenus.length; i++)
   {
      if(subMenus[i] != subMenuName)
         hideLayer(subMenus[i]);
   }
}
function hideLayer(subMenuName)
{
   if(layerVisible[subMenuName])
   {
      direction[subMenuName] = -1;
      moveLayer(subMenuName);
      layerVisible[subMenuName] = false;

   }
}
function moveLayer(subMenuName)
{
    //alert("moveLayer("+subMenuName+")");
   var height = 0;
   var moveTo = 0;
   if(browser == "ns4")
   {
      destY[subMenuName] = startY[subMenuName] + (menuHeight * direction[subMenuName]);
      document.elements(subMenuName).top = destY[subMenuName];
   }
   if(browser == "ie")
   {
      height = parseInt(document.getElementById(subMenuName).style.height);
      menuLoc = parseInt(document.getElementById("entireMenu").style.top);
      var menuHeight = parseInt(document.getElementById("entireMenu").style.height);
      if(direction[subMenuName] == 1)
      {
         destY[subMenuName] =  /*menuLoc +*/  (menuHeight * direction[subMenuName]);
      }
      else
      {
         destY[subMenuName] = /*menuLoc + */(height * direction[subMenuName]);
      }
   }
   if(browser == "moz")
   {
      height = parseInt(document.getElementById(subMenuName).style.height);
      menuLoc = parseInt(document.getElementById("entireMenu").style.top);
      var menuHeight = parseInt(document.getElementById("entireMenu").style.height);
      if(direction[subMenuName] == 1)
      {
         destY[subMenuName] =  /*menuLoc +*/  (menuHeight * direction[subMenuName]);
      }
      else
      {
         destY[subMenuName] = /*menuLoc + */(height * direction[subMenuName]);
      }
   }
   if(!timeoutID[subMenuName])
   {
      moveMenu(subMenuName);
      started=1;
   }  
}
function moveMenu(subMenuName)
{
   var subMenuElement = document.getElementById(subMenuName);
   currentY[subMenuName] = parseInt(subMenuElement.style.top);
   var currentLoc = currentY[subMenuName];
   var nextLoc = parseInt(subMenuElement.style.top) + absolute_speed * direction[subMenuName];
   var destLoc = destY[subMenuName];
   
   var toAlert = "currentLoc = " +currentLoc+ "\n       nextY = " + nextLoc + "\ndestY = " + destLoc + "\ndirection = " + direction[subMenuName];
   //if((direction[subMenuName] == 1) && (currentY[subMenuName] >= destY[subMenuName]))
   if((direction[subMenuName] == 1) && (nextLoc > destY[subMenuName]))
   {
      currentY[subMenuName] = destY[subMenuName];
   }
   //else if((direction[subMenuName] == -1) && (currentY[subMenuName] <= destY[subMenuName]))
   else if((direction[subMenuName] == -1) && (nextLoc < destY[subMenuName]))
   {
      currentY[subMenuName] = destY[subMenuName];
   }
   else
   {
      currentY[subMenuName] = parseInt(subMenuElement.style.top) + absolute_speed * direction[subMenuName];
   }
   toAlert += "newCurrentY = " + currentY[subMenuName];
   //alert(toAlert);
     
   
   if(browser == "ns4")
   {
      document.getElementById(subMenuName).top=currentY[subMenuName];
   }
   if(browser == "ie")
   {
      document.getElementById(subMenuName).style.top = currentY[subMenuName];
   }
   if(browser == "moz")
   {
      document.getElementById(subMenuName).style.top = currentY[subMenuName] + "px";
   }
   if (direction[subMenuName] == 1 && currentY[subMenuName] >= destY[subMenuName])
   {
      // Clear the timeout fxn that is set to be called.
      window.clearTimeout(timeoutID);
      moving[subMenuName] = false;
      timeoutID[subMenuName]=0;
   }
   else if (direction[subMenuName] == -1 && currentY[subMenuName] <= destY[subMenuName])
   {
      // Clear the timeout fxn that is set to be called.
      window.clearTimeout(timeoutID);
      moving[subMenuName] = false;
      timeoutID[subMenuName]=0;
   }
   else
   {
      var toCall = "moveMenu('" + subMenuName + "');";
      moving[subMenuName] = true;
      timeoutID[subMenuName] = window.setTimeout(toCall,5);
   }
}
-->