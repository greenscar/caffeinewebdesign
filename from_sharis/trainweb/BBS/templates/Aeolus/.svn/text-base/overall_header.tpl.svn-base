<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html dir="{S_CONTENT_DIRECTION}">
<head>
<meta http-equiv="Content-Type" content="text/html; charset={S_CONTENT_ENCODING}">
<meta http-equiv="Content-Style-Type" content="text/css">
<meta name="Author" content="http://www.trushkin.net" />
{META}
{NAV_LINKS}
<title>{SITENAME} :: {PAGE_TITLE}</title>
<!--
<script type="text/javascript" src="http://university.sharis.com/reference_check.js"></script>
-->
<link rel="stylesheet" href="{T_TEMPLATE_PATH}/Aeolus.css" type="text/css">
<style type="text/css">
<!--
th, td.th, td.spacerow	{ background-image: url({T_TEMPLATE_PATH}/images/bg_cat.gif); }
td.th2	{ background-image: url({T_TEMPLATE_PATH}/images/bg_cat2.gif); }
td.cat,td.catHead,td.catSides,td.catLeft,td.catRight,td.catBottom, td.row4	{ background-image: url({T_TEMPLATE_PATH}/images/bg_cat4.gif); }

/* Import the fancy styles for IE only (NS4.x doesn't use the @import function) */
@import url("{T_TEMPLATE_PATH}/formIE.css"); 
-->
</style>
<!-- BEGIN switch_enable_pm_popup -->
<script language="Javascript" type="text/javascript">
<!--
	if ( {PRIVATE_MESSAGE_NEW_FLAG} )
	{
		window.open('{U_PRIVATEMSGS_POPUP}', '_phpbbprivmsg', 'HEIGHT=225,resizable=yes,WIDTH=400');;
	}
//-->
</script>
<!-- END switch_enable_pm_popup -->
<script language="javascript" type="text/javascript">
<!--

 function changeImages()
 {
  if (document.images)
  {
   for (var i=0; i<changeImages.arguments.length; i+=2)
   {
    document[changeImages.arguments[i]].src = changeImages.arguments[i+1];
   }
  }
 }

 var PreloadFlag = false;

 function newImage(arg)
 {
  if (document.images)
  {
   rslt = new Image();
   rslt.src = arg;
   return rslt;
  }
 }

 function PreloadImages()
 {
  if (document.images)
  {
	// preload all rollover images
	<!-- BEGIN switch_user_logged_out -->
	img0 = newImage('{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_login_on.gif');
	img1 = newImage('{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_register_on.gif');
	<!-- END switch_user_logged_out -->
	<!-- BEGIN switch_user_logged_in -->
	img2 = newImage('{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_pm_on.gif');
	img3 = newImage('{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_profile_on.gif');
	img4 = newImage('{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_groups_on.gif');
	img5 = newImage('{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_logout_on.gif');
	<!-- END switch_user_logged_in -->
	img6 = newImage('{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_faq_on.gif');
	img7 = newImage('{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_search_on.gif');
	img8 = newImage('{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_users_on.gif');
	img9 = newImage('{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_index_on.gif');
	PreloadFlag = true;
  }
  return true;
 }

 function ShowImage(title, href, name)
 {
  document.write('<td align="center" valign="middle">');
  document.write('<a title="' + title + '" href="' + href + '" class="mainmenu" ');
  document.write('onmouseover="changeImages(\'btn_top_' + name + '\', \'{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_' + name + '_on.gif\'); return true;" ');
  document.write('onmouseout="changeImages(\'btn_top_' + name + '\', \'{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_' + name + '.gif\'); return true;">');
  document.write('<img name="btn_top_' + name + '" src="{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_' + name + '.gif" height="23" border="0" alt="' + title + '" /></a>');
  document.write('</td>');
  return true;
 }

//-->
</script>
</head>
<body bgcolor="#CAD5DD" text="#000000" link="#975922" vlink="#A26024" marginwidth="0" marginheight="0" leftmargin="0" topmargin="0" background="{T_TEMPLATE_PATH}/images/bg_main.gif" onload="PreloadImages();">

<a name="top"></a>


<table width="100%" cellspacing="0" cellpadding="0" border="0" bgcolor="#5F7D8E" background="{T_TEMPLATE_PATH}/images/hdr_bg.gif">
<tr>
	<td width="100%" align="center" valign="middle"><table border="0" cellspacing="0" cellpadding="5" width="80%">
	<tr>
		<td align="center" valign="middle">
			<a href="{U_INDEX}"><img src="{T_TEMPLATE_PATH}/images/logo_phpBB.gif" border="0" /></a>
		</td>
		<td><img src="{T_TEMPLATE_PATH}/images/spacer.gif" width="50" height="1" border="0" /></td>
		<td align="center" valign="middle" nowrap="nowrap">
			<span class="maintitle">{SITENAME}</span><br /><br />
			<span class="subtitle">{SITE_DESCRIPTION}</span> 
		</td>
	</tr>
	</table>
	</td>
</tr>
<tr>
	<td width="100%" height="23" align="left" valign="bottom"><script language="JavaScript" type="text/javascript">
			<!-- 
				document.write('<table border="0" cellspacing="0" cellpadding="0" height="23"><tr>');
				document.write('<td width="23"><img src="{T_TEMPLATE_PATH}/images/hdr_left.gif" width="23" height="23" border="0" /></td>');
			<!-- BEGIN switch_user_logged_out -->
				ShowImage("{L_LOGIN_LOGOUT}", "{U_LOGIN_LOGOUT}", "login");
				ShowImage("{L_REGISTER}", "{U_REGISTER}", "register");
			<!-- END switch_user_logged_out -->
			<!-- BEGIN switch_user_logged_in -->
				ShowImage("{L_PROFILE}", "{U_PROFILE}", "profile");
				ShowImage("{PRIVATE_MESSAGE_INFO}", "{U_PRIVATEMSGS}", "pm");
			<!-- END switch_user_logged_in -->
				ShowImage("{L_FAQ}", "{U_FAQ}", "faq");
				ShowImage("{L_MEMBERLIST}", "{U_MEMBERLIST}", "users");
				ShowImage("{L_SEARCH}", "{U_SEARCH}", "search");
			<!-- BEGIN switch_user_logged_in -->
				ShowImage("{L_USERGROUPS}", "{U_GROUP_CP}", "groups");
				ShowImage("{L_LOGIN_LOGOUT}", "{U_LOGIN_LOGOUT}", "logout");
			<!-- END switch_user_logged_in -->
				ShowImage("{L_INDEX}", "{U_INDEX}", "index");
				document.write('<td align="center" valign="middle" width="23"><img src="{T_TEMPLATE_PATH}/images/hdr_right.gif" width="23" height="23" border="0" /></td></tr></table>');
			//-->
			</script><noscript><table border="0" cellspacing="0" cellpadding="0" height="23">
	<tr>
		<td width="23"><img src="{T_TEMPLATE_PATH}/images/hdr_left.gif" width="23" height="23" border="0" /></td>
		<!-- BEGIN switch_user_logged_out -->
		<td><a href="{U_LOGIN_LOGOUT}"><img src="{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_login.gif" height="23" border="0" alt="{L_LOGIN_LOGOUT}" /></a></td>
		<td><a href="{U_REGISTER}"><img src="{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_register.gif" height="23" border="0" alt="{L_REGISTER}" /></a></td>
		<!-- END switch_user_logged_out -->
		<!-- BEGIN switch_user_logged_in -->
		<td><a href="{U_PROFILE}"><img src="{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_profile.gif" height="23" border="0" alt="{L_PROFILE}" /></a></td>
		<td><a href="{U_PRIVATEMSGS}"><img src="{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_pm.gif" height="23" border="0" alt="{PRIVATE_MESSAGE_INFO}" /></a></td>
		<!-- END switch_user_logged_in -->
		<td><a href="{U_FAQ}"><img src="{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_faq.gif" height="23" border="0" alt="{L_FAQ}" /></a></td>
		<td><a href="{U_MEMBERLIST}"><img src="{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_users.gif" height="23" border="0" alt="{L_MEMBERLIST}" /></a></td>
		<td><a href="{U_SEARCH}"><img src="{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_search.gif" height="23" border="0" alt="{L_SEARCH}" /></a></td>
		<!-- BEGIN switch_user_logged_in -->
		<td><a href="{U_GROUP_CP}"><img src="{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_groups.gif" height="23" border="0" alt="{L_USERGROUPS}" /></a></td>
		<td><a href="{U_LOGIN_LOGOUT}"><img src="{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_logout.gif" height="23" border="0" alt="{L_LOGIN_LOGOUT}" /></a></td>
		<!-- END switch_user_logged_in -->
		<td><a href="{U_INDEX}"><img src="{T_TEMPLATE_PATH}/images/lang_{LANG}/btn_index.gif" height="23" border="0" alt="{L_INDEX}" /></a></td>
		<td width="23"><img src="{T_TEMPLATE_PATH}/images/hdr_right.gif" width="23" height="23" border="0" /></td>
	</tr>
	</table></noscript></td>
</tr>
</table><table border="0" cellspacing="0" cellpadding="0" width="100%"><tr><td background="{T_TEMPLATE_PATH}/images/hdr_border.gif"><img src="{T_TEMPLATE_PATH}/images/spacer.gif" width="1" height="10" border="0" /></td></tr></table>

<br />

<table border="0" cellspacing="0" cellpadding="10" width="100%">
<tr>
	<td align="center" valign="top">
