<HTML>
<head>
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<title><%=request.getParameter("title")%></title>
<style type="text/css">
    <!--
    @import url(css_templates/dropdown_menu.css);
    @import url(css_templates/general.css);
    -->
</style>
<script language="JavaScript" src="js_fxns.js">
</script>
<%if(new Boolean(request.getParameter("dispMenu")).booleanValue()){%>
<script language="JavaScript1.2" src='js_menu_fxns.js' type="text/javascript"></script>
</head>
<script language="Javascript1.2">
   subMenus = new Array("subMenu1", "subMenu2", "subMenu3");
</script>
<body onload="init();">
<div name="entireMenu" id="entireMenu" class="mmMenu" style="top:10px; height: 20px;">
   <div name="menu1" id ="menu1"  class="mmCategory" style="left:100;" onMouseOver="showLayer('subMenu1');">
      <a href="javascript:void(0)">
            Category Admin
      </a>
   </div>
   <div name="subMenu1" id="subMenu1" class="mmSubMenu" style="left:100;top:-119;width:152;height:135;" onMouseOver="showLayer('subMenu1')">
      <div class="mmCell"><a href="SubCategoryAdmin?do=disp">Display Categorization</a></div>
      <div class="mmCell"><a href="CategoryAdmin?do=create">Category Create</a></div>
      <div class="mmCell"><a href="CategoryAdmin?do=mod">Category Modify</a></div>
      <div class="mmCell"><a href="SubCategoryAdmin?do=create">SubCategory Create</a></div>
      <div class="mmCell"><a href="SubCategoryAdmin?do=mod">SubCategory Modify</a></div>
   </div>
   <div name="menu2" id ="menu2" class="mmCategory" style="left:250;" onMouseOver="showLayer('subMenu2');">
      <a href="javascript:void(0)">
            Item Admin
      </a>
   </div>
   <div name="subMenu2" id="subMenu2" class="mmSubMenu" style="left:250;top:-92;width:152;height: 108;" onMouseOver="showLayer('subMenu2');">
      <div class="mmCell"><a href="ItemAdmin?do=disp">Display Items</a></div>
      <div class="mmCell"><a href="#">Item Create</a></div>
      <div class="mmCell"><a href="ItemAdmin?do=mod">Item Modify</a></div>
      <div class="mmCell"><a href="#">Item Delete</a></div>
   </div>
   
   <div name="menu3" id ="menu3" class="mmCategory" style="left:400;" onMouseOver="showLayer('subMenu3');">
      <a href="javascript:void(0)">
            Order Admin
      </a>
   </div>
   <div name="subMenu3" id="subMenu3" class="mmSubMenu" style="left:400;top:-12;width:152;height: 27;" onMouseOver="showLayer('subMenu3');">
      <div class="mmCell"><a href="Order">Order Admin</a></div>
   </div>
   <!--
      THESE DIVS ARE NOT SEEN BY THE USER. THEIR ONLY USE IS TO HIDE THE SUBMENU
   -->
   <div id="hideAll" name="hideAll" class="mmHideMenu" style="position:absolute;top:-15;z-index:4;" onMouseOver="hideLayers();">
      <a href="javascript:void(0)" >
         <img src="999999.gif" width="634" height="35" border="0">
      </a>
   </div>
   <div id="transparent" name="transparent" class="mmHideMenu" style="top:20; width: 634; height: 200; visibility=hidden;"  onMouseOver="hideLayers();">
      <a  href="javascript:void(0)" >
         <img src="transparent.gif" width="634" height="200" border="0">
      </a>
   </div>
   <!--
      END THESE DIVS ARE NOT SEEN BY THE USER. THEIR ONLY USE IS TO HIDE THE SUBMENU
   -->
</div>
<%}else{%><body><%}%>
