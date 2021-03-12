package com.tunix70.javaio;

import com.tunix70.javaio.model.Post;
import com.tunix70.javaio.model.PostStatus;
import com.tunix70.javaio.model.Region;
import com.tunix70.javaio.repository.JDBC.JDBCPostRepositoryImpl;
import com.tunix70.javaio.repository.JDBC.JDBCRegionRepositoryImpl;

import java.sql.SQLException;


public class Runner {
    public static void main(String[] args) throws SQLException {
//        ConsoleView consoleView = new ConsoleView();
//        consoleView.startApp();

        JDBCPostRepositoryImpl pr = new JDBCPostRepositoryImpl();
        System.out.println(pr.getAll());

    }
}
