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

//        po.save(new Post(null, "text4", null, null, PostStatus.ACTIVE));
//        po.save(new Post(null, "text2", null, null, PostStatus.DELETED));
//        reg.save(new Region(null, "eu"));
//        reg.save(new Region(null, "ua"));

                List<Post> listPost = new ArrayList<>();
        listPost.add(po.getById(2l));


//        jw.save(new Writer(null, "Ivan", "Burov", listPost, reg.getAll().get(0)));
//        jw.update(new Writer(1l, "Lusya", "Ivanova", listPost, reg.getAll().get(2)));
        System.out.println(jw.getById(1l));





//        jw.update(new Writer(5l, "Ksenya", "Razina", listPost, reg.getById(3L)));

    }
}
