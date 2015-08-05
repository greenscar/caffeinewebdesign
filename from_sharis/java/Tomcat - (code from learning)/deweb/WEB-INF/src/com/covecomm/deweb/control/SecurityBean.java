package com.covecomm.deweb.control;

import java.sql.*;
import com.covecomm.deweb.doc.*;

public class SecurityBean {
	public final static int NO_DBPOOL = 1002;
	public final static int NO_CONN = 1001;
	public final static int NO_FILE = 404;
	public static final int INVALID_USER = 4010;
	public static final int NOT_AUTHORIZED = 401;
	public static final int AUTHORIZED = 4000;
	public static final int PENDING = 4001;
	public final static int CONFIGURATION_ERROR = 4002;
	public final static int AUTHORIZABLE=4003;
        public SecurityBean() {
                super();
        }


        /** Check the document rights.
         *
         * Gets all the ArticleGroups this document is associated with,
         * and then checks each article group for a rights record.
         */
        private int checkDocumentRights(Connection conn, UserBean user, WebDocBean webdoc) {
                Statement stmt = null;
                int rights = NOT_AUTHORIZED;

                Object[] articleGroups = webdoc.getArticleGroups();
                // there must be at least one article group assigned
                // if rights are used
                if (articleGroups.length < 1) return CONFIGURATION_ERROR;
                try {
                        stmt = conn.createStatement();

                        for (int i = 0; i < articleGroups.length; i++) {
                                ResultSet rs =
                                        stmt.executeQuery(
                                                "select RightID "
                                                        + "from Rights "
                                                        + "where ArticleGroupID = "
                                                        + articleGroups[i]
                                                        + " "
                                                        + "and NameID = "
                                                        + user.getID());
                                if (rs.next()) {
                                        rights = AUTHORIZED;
                                        break;
                                }
                        }
                } catch (SQLException e1) {
                        System.out.println("checkDocumentRights: " + e1);
                } finally {
                        try {
                                if (stmt != null) {
                                        stmt.close();
                                }
                        } catch (SQLException e2) {
                        }

                }
                return rights;
        }

        /** Find out if this is a valid user.
         *
         * This method doesn't validate the user for a particular document;
         * it just checks that the supplied userid and password match the
         * database, and if this is the case, sets the rights flag to
         * NOT_AUTHORIZED. Default is INVALID_USER.
         */
        private int checkUser(Connection conn, UserBean user, WebDocBean webdoc) {
                Statement stmt = null;
                int rights = INVALID_USER;
                //System.out.println("checkUser");
                try {
                        stmt = conn.createStatement();
                        ResultSet rs =
                                stmt.executeQuery(
                                        "select NameID,FirstName,LastName,UserID,Password"
                                                + ",ReadAllGroup,AutoAddBackIssues,AllowPreview from Names "
                                                + "where UserID = '"
                                                + user.getUsername()
                                                + "'");
                        if (rs.next()) {
                                user.setID(rs.getInt("NameID"));
                                System.out.println("set user id to " + user.getID());
                                user.setRealname(rs.getString("FirstName") + rs.getString("LastName"));
                                if (user.getPassword().equals(rs.getString("Password"))) {
                                        rights = NOT_AUTHORIZED;
                                }
                        } else System.out.println("user " + user.getUsername() + " not found");
                } catch (SQLException e1) {
                        System.out.println("checkUser: " + e1);
                } finally {
                        try {
                                if (stmt != null) {
                                        stmt.close();
                                }
                        } catch (SQLException e2) {
                        }

                }
                return rights;
        }

        public int getAccess(Connection conn, UserBean user, WebDocBean webdoc) {
                //System.out.println("first rights check");
                // First check group rights, if necessary
                /*  GroupRights checking happens on documents like PDFs which
                        can cover more than one issue/ArticleGroup, and uses
                        the following sequence:

                        1. Find out if the document matches an ArticleGroup record
                                with the ParentGroup flag set to Y

                        2. If it's a match, use the start and end date of that
                                ArticleGroup record to look for non-parent ArticleGroups.

                        3. Make sure the child ArticleGroup total credits is equal to
                                or greater than the required credits

                        4. Find out how many credits the user has for that time period

                        5.If the user has enough credits, give approval

                        6.If the user doesn't have enough credits, and doesn't
                                have enough available credits that can be converted,
                                fail the access

                        7. If the user has enough available credits, find one
                                article that requires rights for each child group,
                                and validate that article. Then test one more time for
                                valid rights.

                */

                System.out.println("getAccess begins");
                int rights = checkUser(conn, user, webdoc);
                //checkUser returns AUTHORIZED if the user is has readall privileges
                if ((rights == INVALID_USER)
                        || (rights == AUTHORIZED)
                        || (rights == CONFIGURATION_ERROR))
                        return rights;

                // some pages are marked as viewable by any registered user
                if ((rights == NOT_AUTHORIZED) && (webdoc.getRequireRights().equals("A")))
                        return AUTHORIZED;

                System.out.println("** document rights check **");
                // check for document rights
                rights = checkDocumentRights(conn, user, webdoc);
                switch (rights) {
                        case AUTHORIZABLE :
                                System.out.println("returning AUTHORIZABLE");
                                break;
                        case AUTHORIZED :
                                System.out.println("returning AUTHORIZED");
                                break;
                        case CONFIGURATION_ERROR :
                                System.out.println("returning CONFIGURATION_ERROR");
                                break;
                        case INVALID_USER :
                                System.out.println("returning INVALID_USER");
                                break;
                        case NOT_AUTHORIZED :
                                System.out.println("returning NOT_AUTHORIZED");
                                break;
                        default :
                                System.out.println("returning some other code: " + rights);
                }
                return rights;

        }
        /** Create a WebDocBean for this URI
         *
         * Looks in the database for a record matching this URI,
         * and creates a matching WebDocBean instance. Also
         * locates all of the ArticleGroup records associated
         * with this article and adds this data to the bean.
         */
        public WebDocBean getWebDocBean(Connection conn, String URI) {
                //System.out.println("start of webdoc");
                WebDocBean webdoc = new WebDocBean();
                webdoc.setInDatabase(false);
                webdoc.setUri(URI);
                Statement stmt = null;
                ResultSet rs = null;
                try {
                        webdoc.init(conn,URI);
                        if (webdoc.isInDatabase()) {
                                stmt = conn.createStatement();
                                if (webdoc.getJspId() > 0) {
                                        // get the related JSP record
                                        rs = stmt.executeQuery(
                                                "select j.Jsp from Jsps j "
                                                + "where j.JspID= " + webdoc.getJspId());
                                        if (rs.next())
                                                webdoc.setJspName(rs.getString("Jsp"));
                                }
                                // get any explicit groups this article belongs to
                                rs =
                                        stmt.executeQuery(
                                                "select g.Name,g.ArticleGroupID,g.Value,"
                                                        + "g.ParentGroup,g.StartDate,g.EndDate "
                                                        + "from ArtGrpLink l,ArticleGroups g "
                                                        + "where l.ArticleID = "
                                                        + webdoc.getID()
                                                        + " and l.ArticleGroupID = g.ArticleGroupID"
                                                        + " and g.Rights = 'Y'");
                                while (rs.next()) {
                                        webdoc.addArticleGroup(rs.getInt("ArticleGroupID"));
                                        int value = rs.getInt("Value");
                                        if (
                                (value != 0)
                                                && ((webdoc.getRequiredCredits() == 0)
                                                   || (webdoc.getRequiredCredits() < value))
                                                )
                                                webdoc.setRequiredCredits(value);
                                }
                                System.out.println("RequiredCredits: " + webdoc.getRequiredCredits());
                        } else {
                                System.out.println(URI + " not in database");
                        }

                } catch (SQLException e1) {
                        System.out.println("getWebDocBean: " + e1);
                } finally {
                        try {
                                if (stmt != null) {
                                        stmt.close();
                                }
                        } catch (SQLException e2) {
                        }

                }
                return webdoc;
       }
}