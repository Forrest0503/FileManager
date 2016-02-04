package com.Model;

import java.sql.*;

/**
 * Created by Jason on 15/12/23.
 */
public class SQLProcessor {

    private static Connection conn = null;
    private static Statement stmt = null;

    public static void initSQL() {
        try {
            String DB_URL = "jdbc:mysql://localhost/FileManager?" +
                    "user=root&password=c192837465&" +
                    "useUnicode=true&characterEncoding=UTF8&useSSL=false";
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("数据库初始化成功!");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet exeQuery(String sql) throws SQLException {
        return stmt.executeQuery(sql);
    }

    public static void exeUpdate(String sql) throws SQLException {
        stmt.executeUpdate(sql);
    }

    public static void disconnectFromDB() {
        try{
            conn.close();
            stmt.close(); //statement关闭会导致Resultset关闭,因此不必关闭rs
        }catch ( Exception sqlException ){
            sqlException.printStackTrace();
        }
    }

}
