package com.tunix70.javaio;

import com.tunix70.javaio.model.Post;
import com.tunix70.javaio.model.PostStatus;
import com.tunix70.javaio.model.Region;
import com.tunix70.javaio.model.Writer;
import com.tunix70.javaio.repository.JDBC.JDBCPostRepositoryImpl;
import com.tunix70.javaio.repository.JDBC.JDBCRegionRepositoryImpl;
import com.tunix70.javaio.repository.JDBC.JDBCWriterRepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Runner {
    public static void main(String[] args) throws SQLException {
//        ConsoleView consoleView = new ConsoleView();
//        consoleView.startApp();
        JDBCPostRepositoryImpl po = new JDBCPostRepositoryImpl();
        JDBCRegionRepositoryImpl reg = new JDBCRegionRepositoryImpl();
        JDBCWriterRepositoryImpl jw = new JDBCWriterRepositoryImpl();

        List<Post> listPost = new ArrayList<>();
        listPost.add(po.getById(1l));
        listPost.add(po.getById(5l));



        jw.update(new Writer(5l, "Ksenya", "Razina", listPost, reg.getById(3L)));
//        System.out.println(po.generateId());
//        System.out.println(reg.generateId());
//        System.out.println(jw.generateId());

//        System.out.println(reg.getAll());
    }
}
