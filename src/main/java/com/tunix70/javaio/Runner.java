package com.tunix70.javaio;

import com.tunix70.javaio.model.Region;
import com.tunix70.javaio.repository.sqlRepo.RegionSQL;
import com.tunix70.javaio.util.ConnectUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Runner {
    public static void main(String[] args) throws SQLException {
//        ConsoleView consoleView = new ConsoleView();
//        consoleView.startApp();

        RegionSQL rs = new RegionSQL();
        rs.update(new Region(3l, "eu"));

    }
}
