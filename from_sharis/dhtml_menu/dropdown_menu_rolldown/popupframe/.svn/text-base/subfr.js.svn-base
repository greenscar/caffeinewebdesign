var scrWin = parent, page = new Object(), isNS4 = scrWin.isNS4,
 nsWinW = window.innerWidth, nsWinH = window.innerHeight,
 nsPX = window.pageXOffset, nsPY = window.pageYOffset;
for (var f in scrWin.page) page[f] = scrWin.page[f];
page.win = window;



function isPar(mObj, mN)
{
 var par = mObj.menu[mN][0].par;
 return (par.substring(par.lastIndexOf('.')+1) == window.name);
}



var pMenu = scrWin.pMenu;
if (pMenu)
{

function createMenus(mode)
{
 for (var mN in pMenu.menu) if (isPar(pMenu, mN)) pMenu.update(mode, mN);

 if (!scrWin.isIE || window.opera)
  setInterval('if (nsPX!=pageXOffset || nsPY!=pageYOffset) ' +
   '{ nsPX=pageXOffset; nsPY=pageYOffset; window.onscroll() }', 50);
}

function deleteMenus()
{
 for (var mN in pMenu.menu) if (isPar(pMenu, mN)) pMenu.menu[mN][0].lyr = null
}

window.onload = function()
{
 if (isNS4) createMenus(false);
 window.onunload = deleteMenus;
}
if (!isNS4) createMenus(true);


window.onresize = function()
{
 if (isNS4 && (nsWinW!=innerWidth || nsWinH!=innerHeight)) history.go(0);
 pMenu.position();
}

window.onscroll = function()
{
 pMenu.position();
}

if (isNS4)
{
 document.captureEvents(Event.CLICK);
 document.onclick = function(evt)
 {
  with (pMenu) if (overI) click(overM, overI);

  return document.routeEvent(evt);
 }
}


}
