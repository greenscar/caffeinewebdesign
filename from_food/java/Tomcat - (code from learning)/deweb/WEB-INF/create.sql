# MySQL dump 8.13
#
# Host: localhost    Database: deweb
#--------------------------------------------------------
# Server version	3.23.35-log

#
# Table structure for table 'AccessLog'
#

CREATE DATABASE deweb;

# Assign rights

GRANT ALL ON deweb.* TO dewebservlet@localhost IDENTIFIED BY 'tomcat';
FLUSH PRIVILEGES

USE deweb;

CREATE TABLE AccessLog (
  LogID int(11) NOT NULL auto_increment,
  IP varchar(16) default NULL,
  DateTime timestamp(14) NOT NULL,
  User varchar(255) default NULL,
  Request varchar(255) default NULL,
  Result varchar(6) default NULL,
  Status int(11) default NULL,
  Referer varchar(255) default NULL,
  UserAgent varchar(255) default NULL,
  Info text,
  PRIMARY KEY  (LogID)
) TYPE=MyISAM;

#
# Dumping data for table 'AccessLog'
#


#
# Table structure for table 'ArtGrpLink'
#

CREATE TABLE ArtGrpLink (
  ArtGrpLinkID int(11) NOT NULL auto_increment,
  ArticleGroupID int(11) NOT NULL default '0',
  ArticleID int(11) NOT NULL default '0',
  SortDate datetime NOT NULL default '0000-00-00 00:00:00',
  LastModified timestamp(14) NOT NULL,
  PRIMARY KEY  (ArtGrpLinkID),
  KEY IDX_ArtGrpLink_Article (ArticleID,ArticleGroupID),
  KEY IDX_ArtGrpLink_GroupID (ArticleGroupID,ArticleID)
) TYPE=MyISAM;

#
# Dumping data for table 'ArtGrpLink'
#

INSERT INTO ArtGrpLink VALUES (1,1,3,'0000-00-00 00:00:00',20010414162229);
INSERT INTO ArtGrpLink VALUES (2,1,4,'0000-00-00 00:00:00',20010414162351);
INSERT INTO ArtGrpLink VALUES (3,1,2,'0000-00-00 00:00:00',20010414162359);
INSERT INTO ArtGrpLink VALUES (4,2,7,'0000-00-00 00:00:00',20010416115206);
INSERT INTO ArtGrpLink VALUES (5,1,9,'0000-00-00 00:00:00',20010416135833);

#
# Table structure for table 'ArticleGroups'
#

CREATE TABLE ArticleGroups (
  ArticleGroupID int(11) NOT NULL auto_increment,
  ParentID int(11) NOT NULL default '0',
  Name varchar(60) NOT NULL default '',
  Type enum('P','S') default 'P',
  Subtype enum('S','O','I') default 'S',
  Title varchar(255) NOT NULL default '',
  PublicationDate datetime NOT NULL default '0000-00-00 00:00:00',
  Descending tinyint(4) default '1',
  Value tinyint(4) default '1',
  AutoAdd enum('Y','N') default 'Y',
  AutoAddRange enum('W','M','Y') default 'W',
  LastModified timestamp(14) NOT NULL,
  TimeToLive int(11) default '86400',
  Rights enum('Y','N') default 'N',
  ParentGroup enum('Y','N') default 'N',
  StartDate date default NULL,
  EndDate date default NULL,
  PRIMARY KEY  (ArticleGroupID),
  KEY IDX_ArticleGroups_Name (Name),
  KEY IDX_ArticleGroups_Date (PublicationDate)
) TYPE=MyISAM;

#
# Dumping data for table 'ArticleGroups'
#

INSERT INTO ArticleGroups VALUES (1,0,'leftsidebar','','','','2001-04-01 00:00:00',0,1,'Y','W',20010414162226,86400,'N','N',NULL,NULL);
INSERT INTO ArticleGroups VALUES (2,0,'protected','','','','2001-01-01 00:00:00',0,0,'Y','W',20010416115222,86400,'Y','N',NULL,NULL);

#
# Table structure for table 'ArticleTypes'
#

CREATE TABLE ArticleTypes (
  ArticleType varchar(8) NOT NULL default '',
  Name varchar(30) NOT NULL default '',
  Keywords varchar(255) default NULL,
  LastModified timestamp(14) NOT NULL,
  PRIMARY KEY  (ArticleType),
  KEY IDX_ArticletType_Name (Name)
) TYPE=MyISAM;

#
# Dumping data for table 'ArticleTypes'
#


#
# Table structure for table 'Articles'
#

CREATE TABLE Articles (
  ArticleID int(11) NOT NULL auto_increment,
  ArticleType varchar(8) NOT NULL default '',
  JspID int(11) NOT NULL default '0',
  Title varchar(255) NOT NULL default '',
  RequireRights enum('Y','N','A','P') default 'Y',
  URI varchar(255) NOT NULL default '',
  FileName varchar(255) NOT NULL default '',
  FilePath varchar(255) NOT NULL default '',
  Summary text,
  LastModified timestamp(14) NOT NULL,
  Alias varchar(255) default NULL,
  Status enum('P','V') default 'P',
  PublicationDate datetime default NULL,
  HasForm enum('Y','N') default 'N',
  ExtraInfo varchar(255) default NULL,
  IncludePage varchar(255) default NULL,
  ForwardPage varchar(255) default NULL,
  AuthorName varchar(100) default NULL,
  cmagonlyafterthis char(1) default NULL,
  IssueID int(11) default NULL,
  IssueSeq tinyint(4) default NULL,
  ArticleBytes int(11) default NULL,
  SizeFactor double(16,4) default NULL,
  ArticleSize int(11) default NULL,
  AdjustedSize int(11) default NULL,
  CurrValue double(16,4) default NULL,
  Notes text,
  Indexed enum('Y','N') default 'N',
  PRIMARY KEY  (ArticleID),
  KEY IDX_Articles_TypeCode (ArticleType),
  KEY IDX_Articles_Title (Title)
) TYPE=MyISAM;

#
# Dumping data for table 'Articles'
#

INSERT INTO Articles VALUES (1,'CMAG',1,'About This Web Application','N','/about.html','','','A brief description of this web application\'s purpose and construction.',20010414161648,'','P','2001-04-07 00:00:00','','','','','','',0,0,NULL,NULL,NULL,NULL,NULL,'','N');
INSERT INTO Articles VALUES (2,'ADMIN',1,'CoveComm','N','http://www.covecomm.com','','','',20010414162426,'','P','2001-04-14 00:00:00','','','','','','',0,0,NULL,NULL,NULL,NULL,NULL,'','N');
INSERT INTO Articles VALUES (3,'ADMIN',2,'The Book','N','http://www.covecomm.com/java','','','',20010414162435,'','P','2001-04-12 00:00:00','','','','','','',0,0,NULL,NULL,NULL,NULL,NULL,'','N');
INSERT INTO Articles VALUES (5,'CMAG',1,'Download the book source','N','/download.html','','','The source code for JSP, Servlets, and MySQL is now available.',20010414162916,'','P','2001-04-14 00:00:00','','','','','','',0,0,NULL,NULL,NULL,NULL,NULL,'','N');
INSERT INTO Articles VALUES (6,'CMAG',1,'Authentication/Authorization Demo','N','/authentication.html','','','This page demonstrates the use of the authentication/authorization code.',20010416114849,'','P','2001-04-16 00:00:00','','','','','','',0,0,NULL,NULL,NULL,NULL,NULL,'','N');
INSERT INTO Articles VALUES (7,'CMAG',1,'Protected Page','A','/protected.html','','','This page is password protected.',20010416115227,'','V','2001-04-16 00:00:00','','','','','','',0,0,NULL,NULL,NULL,NULL,NULL,'','N');
INSERT INTO Articles VALUES (8,'ADMIN',1,'Bad Login','N','/badlogin.html','','','Message displayed when a login attempt fails.',20010416120233,'','P','2001-04-16 00:00:00','','','','','','',0,0,NULL,NULL,NULL,NULL,NULL,'','N');
INSERT INTO Articles VALUES (9,'ADMIN',2,'Home Page','N','/index.html','','','',20010416145457,'','P','2001-04-05 00:00:00','Y','com.covecomm.deweb.example.DewebSurveyBean','','','','',0,0,NULL,NULL,NULL,NULL,NULL,'','N');

#
# Table structure for table 'AuthorArticle'
#

CREATE TABLE AuthorArticle (
  AuthorArticleID int(11) NOT NULL auto_increment,
  ArticleID int(11) NOT NULL default '0',
  AuthorID int(11) NOT NULL default '0',
  AuthorPayType enum('R','S','I') default 'R',
  PayFlag enum('Y','N') default 'Y',
  RoyaltyRate float(10,2) default NULL,
  CurrValue float(10,2) default NULL,
  PubDate date default NULL,
  LastModified timestamp(14) NOT NULL,
  PRIMARY KEY  (AuthorArticleID),
  KEY IDX_AuthorArticle_Author (AuthorID,ArticleID),
  KEY IDX_ArticleAuthor_Article (ArticleID,AuthorID)
) TYPE=MyISAM;

#
# Dumping data for table 'AuthorArticle'
#


#
# Table structure for table 'CommentGroups'
#

CREATE TABLE CommentGroups (
  CommentGroupID int(11) NOT NULL auto_increment,
  Name varchar(255) NOT NULL default '',
  Notes text,
  LastModified timestamp(14) NOT NULL,
  PRIMARY KEY  (CommentGroupID),
  KEY IDX_CommentGroups_Name (Name)
) TYPE=MyISAM;

#
# Dumping data for table 'CommentGroups'
#


#
# Table structure for table 'Comments'
#

CREATE TABLE Comments (
  CommentID int(11) NOT NULL auto_increment,
  CommentGroupID int(11) NOT NULL default '0',
  email varchar(255) default NULL,
  Source varchar(255) default NULL,
  Comments text,
  LastModified timestamp(14) NOT NULL,
  PRIMARY KEY  (CommentID),
  KEY IDX_Comments_CommentGroup (CommentGroupID)
) TYPE=MyISAM;

#
# Dumping data for table 'Comments'
#


#
# Table structure for table 'DetailLog'
#

CREATE TABLE DetailLog (
  IP varchar(20) default NULL,
  User varchar(30) default NULL,
  DateTime timestamp(14) NOT NULL,
  Request varchar(255) default NULL,
  Result int(11) default NULL,
  Status int(11) default NULL,
  FileSize int(11) default NULL
) TYPE=MyISAM;

#
# Dumping data for table 'DetailLog'
#


#
# Table structure for table 'Filters'
#

CREATE TABLE Filters (
  ID int(11) NOT NULL auto_increment,
  Type varchar(8) NOT NULL default '',
  Text varchar(200) NOT NULL default '',
  PRIMARY KEY  (ID),
  UNIQUE KEY IDX_Filter_TypeText (Type,Text)
) TYPE=MyISAM;

#
# Dumping data for table 'Filters'
#


#
# Table structure for table 'Forms'
#

CREATE TABLE Forms (
  ID int(11) NOT NULL auto_increment,
  JspID int(11) NOT NULL default '0',
  Name varchar(30) NOT NULL default '',
  Description varchar(255) default NULL,
  URI varchar(255) NOT NULL default '',
  FormBeanClass varchar(255) default NULL,
  IncludePage varchar(255) default NULL,
  PRIMARY KEY  (ID),
  UNIQUE KEY IDX_Forms_URI (URI),
  UNIQUE KEY IDX_Forms_Name (Name)
) TYPE=MyISAM;

#
# Dumping data for table 'Forms'
#


#
# Table structure for table 'Jsps'
#

CREATE TABLE Jsps (
  JspID int(11) NOT NULL auto_increment,
  Jsp varchar(255) NOT NULL default '',
  Description varchar(255) default NULL,
  PRIMARY KEY  (JspID)
) TYPE=MyISAM;

#
# Dumping data for table 'Jsps'
#

INSERT INTO Jsps VALUES (1,'/article.jsp','');
INSERT INTO Jsps VALUES (2,'/index.jsp','');

#
# Table structure for table 'Names'
#

CREATE TABLE Names (
  NameID int(11) NOT NULL auto_increment,
  FirstName varchar(60) NOT NULL default '',
  LastName varchar(60) NOT NULL default '',
  Company varchar(60) NOT NULL default '',
  Address1 varchar(60) NOT NULL default '',
  Address2 varchar(60) NOT NULL default '',
  City varchar(60) NOT NULL default '',
  StateProv varchar(60) NOT NULL default '',
  Country varchar(60) NOT NULL default '',
  Postal varchar(15) default NULL,
  Phone varchar(60) NOT NULL default '',
  Email varchar(60) NOT NULL default '',
  UserID varchar(60) NOT NULL default '',
  Password varchar(60) NOT NULL default '',
  Active enum('Y','N') default 'N',
  LastModified timestamp(14) NOT NULL,
  Cancelled enum('Y','N') default 'N',
  BadEmail enum('Y','N') default 'N',
  AllowMail enum('Y','N') default 'N',
  AllowEmail enum('Y','N') default 'N',
  AddedtoCmag enum('Y','N') default 'N',
  ReadAllGroup enum('Y','N') default 'N',
  EdBoardGroup enum('Y','N') default 'N',
  AuthorGroup enum('Y','N') default 'N',
  Reserved1Group enum('Y','N') default 'N',
  Reserved2Group enum('Y','N') default 'N',
  AlternateID varchar(40) default NULL,
  PaymentNotes varchar(255) default NULL,
  Comments varchar(255) default NULL,
  AuthorPayments varchar(10) default NULL,
  AutoAddBackIssues enum('Y','N') default 'N',
  AllowPreview enum('Y','N') default 'N',
  LastPaymentDate date default NULL,
  CreditsLeft int(11) default NULL,
  CreditsLeftDate date default NULL,
  PrevCreditsLeft int(11) default NULL,
  AuthorBio text,
  PRIMARY KEY  (NameID),
  UNIQUE KEY IDX_Names_UserID (UserID),
  KEY IDX_Names_LastFirst (LastName,FirstName),
  KEY IDX_Names_Company (Company),
  KEY IDX_Names_Email (Email),
  KEY IDX_Names_FirstLast (FirstName,LastName),
  KEY IDX_Names_Country (Country)
) TYPE=MyISAM;

#
# Dumping data for table 'Names'
#

INSERT INTO Names VALUES (1,'Tom','Cat','','','','','','','','','','jakarta','tomcat','',20010416115122,'','','','','','','','','','','','','','','','N',NULL,0,NULL,0,'');

#
# Table structure for table 'Products'
#

CREATE TABLE Products (
  ProductCode varchar(6) NOT NULL default '',
  Description varchar(255) default NULL,
  UnitCost varchar(10) default NULL,
  LastModified timestamp(14) NOT NULL,
  PRIMARY KEY  (ProductCode),
  KEY IDX_Products_ProductCode (ProductCode)
) TYPE=MyISAM;

#
# Dumping data for table 'Products'
#


#
# Table structure for table 'PurchaseDetail'
#

CREATE TABLE PurchaseDetail (
  PurchaseDetailID int(11) NOT NULL auto_increment,
  PurchaseID int(11) NOT NULL default '0',
  ProductCode varchar(6) NOT NULL default '',
  ItemCode varchar(6) default NULL,
  Quantity tinyint(4) default NULL,
  UnitCost float(10,2) default NULL,
  LastModified timestamp(14) NOT NULL,
  PRIMARY KEY  (PurchaseDetailID),
  KEY IDX_PurchaseDetail_PurchaseID (PurchaseID)
) TYPE=MyISAM;

#
# Dumping data for table 'PurchaseDetail'
#


#
# Table structure for table 'Purchases'
#

CREATE TABLE Purchases (
  PurchaseID int(11) NOT NULL auto_increment,
  NameID int(11) NOT NULL default '0',
  PurchaseDate date NOT NULL default '0000-00-00',
  EffectiveDate date default NULL,
  Total float(10,2) default NULL,
  Notes text,
  IPAddress varchar(20) default NULL,
  PaymentMethod varchar(20) default NULL,
  PrintReceipt enum('Y','N','P') default NULL,
  Credits int(11) default '0',
  Pending enum('Y','N') default 'Y',
  ConfirmDate date default NULL,
  LastModified timestamp(14) NOT NULL,
  testval varchar(20) default NULL,
  Adjust int(11) default NULL,
  ExtraCredits int(11) default NULL,
  PRIMARY KEY  (PurchaseID),
  KEY IDX_Purchases_NameID (NameID,PurchaseDate)
) TYPE=MyISAM;

#
# Dumping data for table 'Purchases'
#


#
# Table structure for table 'Releases'
#

CREATE TABLE Releases (
  ReleaseID int(11) NOT NULL auto_increment,
  Type varchar(8) default NULL,
  ReleaseDate date default NULL,
  PRIMARY KEY  (ReleaseID)
) TYPE=MyISAM;

#
# Dumping data for table 'Releases'
#


#
# Table structure for table 'Renewals'
#

CREATE TABLE Renewals (
  NameID int(11) default NULL,
  Total int(11) default NULL,
  Credits int(11) default NULL
) TYPE=MyISAM;

#
# Dumping data for table 'Renewals'
#


#
# Table structure for table 'Rights'
#

CREATE TABLE Rights (
  RightID int(11) NOT NULL auto_increment,
  NameID int(11) NOT NULL default '0',
  ArticleGroupID int(11) NOT NULL default '0',
  PurchaseID int(11) default NULL,
  Pending enum('Y','N') default 'Y',
  LastModified timestamp(14) NOT NULL,
  PRIMARY KEY  (RightID),
  KEY IDX_Rights_NameID (NameID),
  KEY IDX_Rights_ArticleGroupsID (ArticleGroupID),
  KEY IDX_NameID (NameID)
) TYPE=MyISAM;

#
# Dumping data for table 'Rights'
#

INSERT INTO Rights VALUES (2,1,2,NULL,'Y',20010416115136);

#
# Table structure for table 'SurveyData'
#

CREATE TABLE SurveyData (
  ID int(11) NOT NULL auto_increment,
  SurveyID int(11) NOT NULL default '0',
  Value varchar(20) NOT NULL default '',
  Source varchar(255) default NULL,
  DateTime timestamp(14) NOT NULL,
  PRIMARY KEY  (ID),
  KEY IDX_SurveyData_Survey (SurveyID,Value)
) TYPE=MyISAM;

#
# Dumping data for table 'SurveyData'
#

INSERT INTO SurveyData VALUES (6,1,'No','192.168.1.9',20010416144446);

#
# Table structure for table 'Surveys'
#

CREATE TABLE Surveys (
  ID int(11) NOT NULL auto_increment,
  Name varchar(30) default NULL,
  Description varchar(255) default NULL,
  PubDate date default NULL,
  LastModified timestamp(14) NOT NULL,
  PRIMARY KEY  (ID)
) TYPE=MyISAM;

#
# Dumping data for table 'Surveys'
#


