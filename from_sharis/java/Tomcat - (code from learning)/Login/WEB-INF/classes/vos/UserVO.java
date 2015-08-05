package vos;

import daos.*;

public class UserVO {
    
    private String loginID;
    private String firstName;
    private String lastName;
    private String storeNum;
    private String secLvl;
    private String pwd;
    private String dateCreated;
    private String lastLogin;
    
    // setXXX
    public void setLoginID(String x){
        this.loginID = x;
    }
    public void setFirstName(String x){
        this.firstName = x;
    }
    public void setLastName(String x){
        this.lastName = x;
    }
    public void setStoreNum(String x){
        this.storeNum = x;
    }
    public void setSecLvl(String x){
        this.secLvl = x;
    }
    public void setPwd(String x){
        this.pwd = x;
    }
    public void setDateCreated(String x){
        this.dateCreated = x;
    }
    public void setLastLogin(String x){
        this.lastLogin = x;
    }
    
    // getXXX
    public String getFirstName (){
        return this.firstName ;
    }
    public String getLastName (){
        return this.lastName ;
    }
    public String getStoreNum (){
        return this.storeNum ;
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
