 
<table width="100%" cellspacing="2" cellpadding="2" border="0" align="center">
  <tr> 
	<td align="left"><span class="nav"><a href="{U_INDEX}" class="nav">{L_INDEX}</a></span></td>
  </tr>
</table>

{TPL_HDR1}<span class="cattitle">{L_SEARCH_MATCHES}</span>{TPL_HDR2}<table width="100%" cellpadding="4" cellspacing="1" border="0" class="forumline" align="center">
  <tr> 
	<th width="4%" height="25" class="thCornerL" nowrap="nowrap">&nbsp;</th>
	<th class="thTop" nowrap="nowrap">&nbsp;{L_FORUM}&nbsp;</th>
	<th class="thTop" nowrap="nowrap">&nbsp;{L_TOPICS}&nbsp;</th>
	<th class="thTop" nowrap="nowrap">&nbsp;{L_AUTHOR}&nbsp;</th>
	<th class="thTop" nowrap="nowrap">&nbsp;{L_REPLIES}&nbsp;</th>
	<th class="thTop" nowrap="nowrap">&nbsp;{L_VIEWS}&nbsp;</th>
	<th class="thCornerR" nowrap="nowrap">&nbsp;{L_LASTPOST}&nbsp;</th>
  </tr>
  <!-- BEGIN searchresults -->
  <tr> 
	<td class="row4" align="center" valign="middle"><img src="{searchresults.TOPIC_FOLDER_IMG}" width="19" height="18" alt="{searchresults.L_TOPIC_FOLDER_ALT}" title="{searchresults.L_TOPIC_FOLDER_ALT}" /></td>
	<td class="row1" onmouseover="this.style.backgroundColor='{C_ROW1_OVER}';" onmouseout="this.style.backgroundColor='{C_ROW1}';" onclick="window.location.href='{searchresults.U_VIEW_FORUM}'"><span class="forumlink"><a href="{searchresults.U_VIEW_FORUM}" class="forumlink">{searchresults.FORUM_NAME}</a></span></td>
	<td class="row2" onmouseover="this.style.backgroundColor='{C_ROW2_OVER}';" onmouseout="this.style.backgroundColor='{C_ROW2}';" onclick="window.location.href='{searchresults.U_VIEW_TOPIC}'"><span class="topictitle">{searchresults.NEWEST_POST_IMG}{searchresults.TOPIC_TYPE}<a href="{searchresults.U_VIEW_TOPIC}" class="topictitle">{searchresults.TOPIC_TITLE}</a></span><br /><span class="gensmall">{searchresults.GOTO_PAGE}</span></td>
	<td class="row1" align="center" valign="middle"><span class="name">{searchresults.TOPIC_AUTHOR}</span></td>
	<td class="row2" align="center" valign="middle"><span class="postdetails">{searchresults.REPLIES}</span></td>
	<td class="row1" align="center" valign="middle"><span class="postdetails">{searchresults.VIEWS}</span></td>
	<td class="row2" align="center" valign="middle" nowrap="nowrap"><span class="postdetails">{searchresults.LAST_POST_TIME}<br />{searchresults.LAST_POST_AUTHOR} {searchresults.LAST_POST_IMG}</span></td>
  </tr>
  <!-- END searchresults -->
</table>{TPL_FTR}

<table width="100%" cellspacing="2" border="0" align="center" cellpadding="2">
  <tr> 
	<td align="left" valign="top"><span class="nav">{PAGE_NUMBER}</span></td>
	<td align="right" valign="top" nowrap="nowrap"><span class="nav">{PAGINATION}</span><br /><span class="gensmall">{S_TIMEZONE}</span></td>
  </tr>
</table>

<table width="100%" cellspacing="2" border="0" align="center">
  <tr> 
	<td valign="top" align="right">{JUMPBOX}</td>
  </tr>
</table>
