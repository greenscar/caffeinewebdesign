
<table cellspacing="2" cellpadding="2" border="0" align="center">
  <tr> 
	<td valign="middle">{INBOX_IMG}</td>
	<td valign="middle"><span class="cattitle">{INBOX} &nbsp;</span></td>
	<td valign="middle">{SENTBOX_IMG}</td>
	<td valign="middle"><span class="cattitle">{SENTBOX} &nbsp;</span></td>
	<td valign="middle">{OUTBOX_IMG}</td>
	<td valign="middle"><span class="cattitle">{OUTBOX} &nbsp;</span></td>
	<td valign="middle">{SAVEBOX_IMG}</td>
	<td valign="middle"><span class="cattitle">{SAVEBOX}</span></td>
  </tr>
</table>

<br />

<form method="post" action="{S_PRIVMSGS_ACTION}">
{S_HIDDEN_FIELDS}
<table width="100%" cellspacing="2" cellpadding="2" border="0">
  <tr>
	  <td valign="middle">{REPLY_PM_IMG}</td>
	  <td width="100%"><span class="nav">&nbsp;<a href="{U_INDEX}" class="nav">{L_INDEX}</a></span></td>
  </tr>
</table>

{TPL_HDR1}<span class="cattitle">{POST_SUBJECT}</span>{TPL_HDR2}<table border="0" cellpadding="0" cellspacing="1" width="100%" class="forumline">
<tr>
	<td class="th" align="right" valign="middle"><table border="0" cellspacing="0" cellpadding="2">
	<tr height="26">
		<td align="right" valign="middle" nowrap="nowrap" height="26">{QUOTE_PM_IMG} {EDIT_PM_IMG}</td>
	</tr>
	</table></td>
</tr>
<tr>
	<td class="row1" align="left" valign="top" width="100%"><table border="0" cellspacing="0" cellpadding="0" width="100%"><!-- main table start -->
	<tr>
		<td width="150" align="left" valign="top" rowspan="2"><table border="0" cellspacing="0" cellpadding="0" width="100%"><!-- left row table start -->
		<tr>
			<td width="100%" align="left" valign="top" background="{T_TEMPLATE_PATH}/images/post_bg.gif"><table border="0" cellspacing="0" cellpadding="4">
			<tr>
				<td align="left" valign="top" nowrap="nowrap"><span class="genmed">{L_FROM}:</span></td>
				<td align="left" valign="top" nowrap="nowrap"><span class="genmed">{MESSAGE_FROM}</span></td>
			</tr>
			<tr>
				<td align="left" valign="top" nowrap="nowrap"><span class="genmed">{L_TO}:</span></td>
				<td align="left" valign="top" nowrap="nowrap"><span class="genmed">{MESSAGE_TO}</span></td>
			</tr>
			<tr>
				<td align="left" valign="top" nowrap="nowrap"><span class="genmed">{L_POSTED}:</span></td>
				<td align="left" valign="top" nowrap="nowrap"><span class="genmed">{POST_DATE}</span></td>
			</tr>
			<tr>
				<td align="left" valign="top" nowrap="nowrap"><span class="genmed">{L_SUBJECT}:</span></td>
				<td align="left" valign="top"><span class="genmed">{POST_SUBJECT}</span></td>
			</tr>
			</table><br /><br /></td>
			<td width="15" background="{T_TEMPLATE_PATH}/images/post_right.gif"><img src="{T_TEMPLATE_PATH}/images/spacer.gif" width="15" height="1" border="0" /></td>
		</tr>
		<tr>
			<td height="15" background="{T_TEMPLATE_PATH}/images/post_bottom.gif"><img src="{T_TEMPLATE_PATH}/images/spacer.gif" width="1" height="15" border="0" /></td>
			<td width="15" height="15"><img src="{T_TEMPLATE_PATH}/images/post_corner.gif" width="15" height="15" border="0" /></td>
		</tr>
		<!-- left row table end --></table><br /><br /></td>
		<td class="row1" align="left" valign="top" width="100%"><table border="0" cellspacing="0" cellpadding="5" width="100%"><!-- right row table start -->
		<tr>
			<td width="100%"><span class="postbody">{MESSAGE}</span></td>
		</tr>
		<!-- right row table end --></table></td>
	</tr>
	<tr>
		<td class="row1" align="right" valign="bottom" nowrap="nowrap">
			<input type="submit" name="save" value="{L_SAVE_MSG}" class="liteoption" /><input type="submit" name="delete" value="{L_DELETE_MSG}" class="liteoption" />
		</td>
	</tr>
	</table></td>
</tr>
<tr>
	<td height="28" align="center" valign="bottom" class="row4"><table border="0" cellspacing="0" cellpadding="3">
	<tr>
		<td width="170"><img src="{T_TEMPLATE_PATH}/images/spacer.gif" width="170" height="1" border="0" /></td>
		<td width="100%" align="left" valign="middle" nowrap="nowrap">{PROFILE_IMG} {PM_IMG} {EMAIL_IMG} {WWW_IMG} {AIM_IMG} {YIM_IMG} {MSN_IMG} {ICQ_IMG}</td>
	</tr></table></td>
</tr>
</table>{TPL_FTR}

  <table width="100%" cellspacing="2" border="0" align="center" cellpadding="2">
	<tr> 
	  <td>{REPLY_PM_IMG}</td>
	  <td align="right" valign="top" nowrap="nowrap"><span class="gensmall">{S_TIMEZONE}</span></td>
	</tr>
</table>
</form>



<table width="100%" cellspacing="2" border="0" align="center" cellpadding="2">
  <tr> 
	<td valign="top" align="right"><span class="gensmall">{JUMPBOX}</span></td>
  </tr>
</table>
