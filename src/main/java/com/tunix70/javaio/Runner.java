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
//        po.save(new Post(null, "text6", null, null, PostStatus.DELETED));
//        reg.save(new Region(null, "eu"));
//        reg.save(new Region(null, "ua"));

                List<Post> listPost = new ArrayList<>();
        listPost.add(po.getById(3l));

            jw.deleteDuplicatePostRegion(2l);
//        jw.save(new Writer(null, "Ivan", "Burov", listPost, reg.getAll().get(0)));
//        jw.save(new Writer(null, "Vasya", "Ivanov", listPost, reg.getAll().get(1)));
        jw.save(new Writer(null, "Kotya", "Opps", listPost, reg.getAll().get(1)));
//        System.out.println(jw.getWritersPostList(1l));
//        System.out.println(jw.getWritersRegion(1l));
//          System.out.println(jw.getAll());
        jw.deleteDuplicatePostRegion(1l);





//        jw.update(new Writer(5l, "Ksenya", "Razina", listPost, reg.getById(3L)));

    }
}
