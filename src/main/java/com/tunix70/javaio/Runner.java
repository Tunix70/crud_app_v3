package com.tunix70.javaio;

import com.tunix70.javaio.util.ConnectUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Runner {
    public static void main(String[] args) throws SQLException {
//        ConsoleView consoleView = new ConsoleView();
//        consoleView.startApp();

        Connection connection = ConnectUtil.getInstance().getConnection();
        Statement statement = connection.createStatement();
        String SQL = "SELECT * FROM region";
//        statement.execute(SQL);
        ResultSet resultSet = statement.executeQuery(SQL);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");

            System.out.println("id: " + id);
            System.out.println("Name: " + name);
            System.out.println("\n===================\n");
        }


    }
}
