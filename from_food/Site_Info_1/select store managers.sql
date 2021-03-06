/*
Name the index PS_SI_JOB4 before running this.
Run SP_HELPINDEX to see which indexes exist.
Run it in PSPROD
*/
/* Created by: Index Tuning Wizard 	*/
/* Date: 6/2/2004 			*/
/* Time: 10:03:43 AM 			*/
/* Server Name: PSPROD 			*/
/* Database Name: PRODSUP 			*/
USE [PRODSUP] 
go

SET QUOTED_IDENTIFIER ON 
SET ARITHABORT ON 
SET CONCAT_NULL_YIELDS_NULL ON 
SET ANSI_NULLS ON 
SET ANSI_PADDING ON 
SET ANSI_WARNINGS ON 
SET NUMERIC_ROUNDABORT OFF 
go

DECLARE @bErrors as bit

BEGIN TRANSACTION
SET @bErrors = 0

CREATE NONCLUSTERED INDEX [PS_JOB4] ON [dbo].[PS_JOB] ([DEPTID] ASC, [EMPLID] ASC, [EFFDT] ASC, [EFFSEQ] ASC, [JOBCODE] ASC, [EMPL_STATUS] ASC, [EMPL_RCD] ASC )
IF( @@error <> 0 ) SET @bErrors = 1

IF( @bErrors = 0 )
  COMMIT TRANSACTION
ELSE
  ROLLBACK TRANSACTION


/* Statistics to support recommendations */

CREATE STATISTICS [hind_1001770626_1A_58A_64A] ON [dbo].[PS_PERSONAL_DATA] ([EMPLID], [LAST_NAME], [PREF_FIRST_NAME])
CREATE STATISTICS [hind_1674593154_5A_6A] ON [dbo].[PS_JOB] ([DEPTID], [JOBCODE])
CREATE STATISTICS [hind_1674593154_6A_5A_1A] ON [dbo].[PS_JOB] ([JOBCODE], [DEPTID], [EMPLID])
