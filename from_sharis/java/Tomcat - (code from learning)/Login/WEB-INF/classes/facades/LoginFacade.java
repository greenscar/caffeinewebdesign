package facades;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import db.DBUtil;
import aCalendar.Cal;
import daos.UserDAO;
import vos.UserVO;
/*
 * loginFacade acts as a facade for the login process.
 * It creates a user Value Object userVO relating to the user table in the DB
 * Page 270
 */
public class LoginFacade {
    UserVO userVO;
    UserDAO userDAO;
    Cal cal;
    HttpSession localSession;
    /** Creates a new instance of loginFacade */
    public LoginFacade() {
    }
    
    /*
     * processLogin handles the bulk of the login process.
     * The login name is received as a parameter then passed as a parameter 
     *      to userDAO.loadDAO 
     * userDAO.loadDAO will use the login ID passed to load the DAO with the
     *      user table values for that login & password.
     */
    public void processLogin(ServletRequest request, HttpSession session)
        throws Exception{
            System.err.println("-=-=-=-=-=-=-=-=START LoginFacade.processLogin()=-=-=-=-=-=-=-=-=-=-");
        try{
            //Get the request URI
            //String uri = request.getServletPath();
            //System.err.println("uri = " + uri);
            /*
             * Check the session to see if the user exists. If not, 
             *      process login via the DB
             */
            
            localSession = session;
            // Process login
            String id = request.getParameter("login");
            userDAO = new UserDAO();
            userDAO.createPreparedStatements();
            userDAO.loadDAO(id);
            
            userVO = userDAO.getVO();            
            String pwd = userVO.getPwd().trim();
            System.err.println(pwd);
            String pwdEntered = request.getParameter("password").trim();
            System.err.println(pwdEntered);
            // If the password doesn't match
            if(!(pwd.equals(pwdEntered))){
                System.err.println("Incorrect password.");
                throw new Exception("Login error.");
            }
            else{ // A Valid Login
                System.err.println("Correct password!!!");
                // Store session info about user
                session.setAttribute("userVO", userVO);
                session.setAttribute("firstName", userVO.getFirstName());
                session.setAttribute("lastName", userVO.getLastName());
                session.setAttribute("storeNum", userVO.getStoreNum());
                session.setAttribute("lastLogIn", userVO.getLastLogin());
                session.setAttribute("secLvl", userVO.getSecLvl());
                session.setAttribute("dateCreated", userVO.getDateCreated());
                session.setAttribute("loginID", userVO.getLoginID());
                
                userVO.setLastLogin(cal.getCurrentDate());
                userDAO.setVO(userVO);
                userDAO.updateDAO();
                
            }
        }catch(Exception e){
            System.err.println("Exception loginFacade.processLogin(): " + e);
        }
    }
    public String getLastName(){
        String lastName = userVO.getLastName();
        System.err.println("getLastName() lastName = " + lastName);
        return lastName;
    }
    public String getLastLogin(){
        String lastLogin = userVO.getLastLogin();
        System.err.println("getLastLogin() lastLogin = " + lastLogin);
        return lastLogin;
    }
}
