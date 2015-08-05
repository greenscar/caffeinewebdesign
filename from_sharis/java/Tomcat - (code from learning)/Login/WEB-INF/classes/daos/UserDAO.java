package daos;

import vos.*;
import db.*;
import java.util.*;
import java.sql.*;

/**
 * userDAO is the Data Access Object for the user.
 * This class communicates with the database for the user object.
 */
public class UserDAO {
    
    // Hold SQL Statements
    private String insertStmtStr;
    private String updateStmtStr;
    private String deleteStmtStr;
    private String selectStmtStr;
    
    // Hold prepared statements
    private PreparedStatement insertStmt;
    private PreparedStatement updateStmt;
    private PreparedStatement deleteStmt;
    private PreparedStatement selectStmt;
    
    // DB Util class
    DBUtil dbUtil = new DBUtil();
    //Internal Value Object
    UserVO userVO;
    
    public void createPreparedStatements(){
        try{
            insertStmtStr = "INSERT INTO users(login, firstName, lastName, " 
                + "storeNum, secLvl, pwd, dateCreated, lastLogin) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?)";
            insertStmt = dbUtil.createPreparedStatement(insertStmtStr);
            
            updateStmtStr = "UPDATE users set " 
                + "firstName = ?, " 
                + "lastName = ?, "
                + "storeNum = ?, "
                + "secLvl = ?, "
                + "pwd = ?, "
                + "lastLogin = ?, "
                + "WHERE login = ?";
            updateStmt = dbUtil.createPreparedStatement(updateStmtStr);
            
            deleteStmtStr = "DELETE FROM users WHERE login = ?";
            deleteStmt = dbUtil.createPreparedStatement(deleteStmtStr);
            
            selectStmtStr = "SELECT login, firstName, lastName, storeNum, "
                + "secLvl, pwd, dateCreated, lastLogin "
                + "FROM users WHERE login = ?";
            selectStmt = dbUtil.createPreparedStatement(selectStmtStr);
        }catch(Exception e){
            System.err.println("Exception in userDAO.createPreparedStatements(): "
                + e);
        }
    }
    public void updateDAO() throws SQLException{
        updateStmt.setString(1, userVO.getFirstName());
        updateStmt.setString(2, userVO.getLastName());
        updateStmt.setString(3, userVO.getStoreNum());
        updateStmt.setString(4, userVO.getSecLvl());
        updateStmt.setString(5, userVO.getPwd());
        updateStmt.setString(6, userVO.getLastLogin());
        updateStmt.setString(7, userVO.getLoginID());
        updateStmt.executeUpdate();
    }
    public void insertDAO() throws SQLException{
        insertStmt.setString(1, userVO.getLoginID());
        insertStmt.setString(2, userVO.getFirstName());
        insertStmt.setString(3, userVO.getLastName());
        insertStmt.setString(4, userVO.getStoreNum());
        insertStmt.setString(5, userVO.getSecLvl());
        insertStmt.setString(6, userVO.getPwd());
        insertStmt.setString(7, userVO.getDateCreated());
        insertStmt.setString(8, userVO.getLastLogin());
        insertStmt.executeUpdate();
    }
    public void deleteDAO() throws SQLException{
        deleteStmt.setString(1, userVO.getLoginID());
        deleteStmt.executeUpdate();
    }
    public void loadDAO(String login) throws Exception{
        try{
            selectStmt.setString(1, login);
            ResultSet rs = selectStmt.executeQuery();
            if(rs.next()){
                userVO = new UserVO();
                userVO.setLoginID(rs.getString(1));
                userVO.setFirstName(rs.getString(2));
                userVO.setLastName(rs.getString(3));
                userVO.setStoreNum(rs.getString(4));
                userVO.setSecLvl(rs.getString(5));
                userVO.setPwd(rs.getString(6));
                userVO.setDateCreated(rs.getString(7));
                userVO.setLastLogin(rs.getString(8));
            }
            else{
                System.err.println("UserDAO.loadDAO(): No records found.");
            }
        }catch(SQLException e){
            System.err.println("SQLException in UserDAO.loadDAO()" + e);
            throw new Exception("Exception caught in UserDAO.loadDAO() " + e);
        }
    }
    // setXXX
    public void setVO(UserVO vo){
        userVO = vo;
    }
    
    // getXXX
    public UserVO getVO(){
        return this.userVO;
    }
}