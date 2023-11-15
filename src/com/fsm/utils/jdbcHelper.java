/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fsm.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class jdbcHelper {
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl = "jdbc:sqlserver://localhost;database=QLShopQuanAo;encrypt=false";
    private static String user ="sa";
    private static String pass ="123";
    
    static{
        try {
            Class.forName(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static PreparedStatement getStmt(String sql,Object... args) throws SQLException{
        Connection connection = DriverManager.getConnection(dburl,user,pass);
        PreparedStatement pstmt = null;
        if(sql.trim().startsWith("{")){
            pstmt = connection.prepareCall(sql);
        }else{
            pstmt = connection.prepareStatement(sql);
        }
        for(int i =0; i< args.length; i++){
            pstmt.setObject(i+1, args[i]);
        }
        return pstmt;
    }   
    
    public static int Update(String sql, Object...args){
        try {
            PreparedStatement stmt = getStmt(sql,args);
            try {
                return stmt.executeUpdate();
            } finally {
                stmt.getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static ResultSet query(String sql,Object...args){
        try {
            PreparedStatement stmt = getStmt(sql,args);
            return stmt.executeQuery();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Object value(String sql,Object...args){
        try {
            ResultSet rs = query(sql,args);
            if(rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
