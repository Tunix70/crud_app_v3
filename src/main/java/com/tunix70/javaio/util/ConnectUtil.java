package com.tunix70.javaio.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectUtil {
    public static ConnectUtil connectUtil = null;
    private String DATABASE_URL = "jdbc:mysql://localhost:3306/crud";
    private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private String USER = "root";
    private String PASSWORD = "root";
    private Connection connection;

    private ConnectUtil() {
        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        }catch (SQLException e){
            System.out.println("Ошибка SQL");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static ConnectUtil getInstance(){
        if(connectUtil ==null){
            connectUtil = new ConnectUtil();
        }
        return connectUtil;
    }

    public Connection getConnection() {
        return connection;
    }

    public static Statement getStatement(){
        Statement statement = null;
        try {
            statement = getInstance().getConnection().createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return statement;
    }
}
