package facades;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import aCalendar.Cal;

public class UserFacade{
    // Data Access Objects
    private UserDAO userDAO;
    // Value Objects
    private UserVO userVO;
    
    public LoginFacade() throws Exception{
        userDAO = new UserDAO();
        userVO = new UserVO();
    }       
    
    // setXXX
    public void setLoginID(String x){
        userVO.setLoginID(x);
    }
    public void setFirstName(String x){
        userVO.setFirstName(x);
    }
    public void setLastName(String x){
        userVO.setLastName(x);
    }
    public void setLocation(String x){
        userVO.setLocation(x);
    }
    public void setSecLvl(String x){
        userVO.setSecLvl(x);
    }
    public void setPwd(String x){
        userVO.setPwd(x);
    }
    public void setDateCreated(String x){
        userVO.setDateCreated (x);
    }
    public void setLastLogin(String x){
        userVO.setLastLogin (x);
    }
    
    // getXXX
    public String getFirstName (){
        return this.firstName ;
    }
    public String getLastName (){
        return this.lastName ;
    }
    public String getLocation (){
        return this.location ;
    }
    public String getSecLvl (){
        return this.secLvl ;
    }
    public String getPwd (){
        return this.pwd ;
    }
    public String getDateCreated (){
        return this.dateCreated ;
    }
    public String getLastLogin (){
        return this.lastLogin ;
    }
    public String getLoginID(){
        return this.loginID;
    }
}
