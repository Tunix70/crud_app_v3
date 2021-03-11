package com.tunix70.javaio.repository.JDBC;

import com.tunix70.javaio.model.Post;
import com.tunix70.javaio.model.Region;
import com.tunix70.javaio.repository.PostRepository;
import com.tunix70.javaio.util.ConnectUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JDBCPostRepositoryImpl implements PostRepository {
    private Connection connection = ConnectUtil.getInstance().getConnection();
    private Statement statement;
    private Long dataTime = new Date().getTime();

    @Override
    public List<Post> getAll() {
        return null;
    }

    @Override
    public Post getById(Long aLong) {
        return null;
    }

    @Override
    public Post save(Post post) {
        return null;
    }

    @Override
    public Post update(Post post) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    private List<Post> sqlReader(){
        String SQLread = "SELECT * FROM post";
        List<Post> postList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQLread);
            while(resultSet.next()){
                Post post = new Post();
                post.setId((long) resultSet.getInt("id"));
                post.setContent(resultSet.getString("content"));
                postList.add(post);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return postList;
    }

//    public void sqlWriter(Post post){
//        String SQLadd = "INSERT INTO post (content, created, updated) VALUES ('" + post.getContent() + "', '" + post.getCreated() + "'"")";
//        try{
//            statement = connection.createStatement();
//            statement.executeUpdate(SQLadd);
//        }catch (SQLException e){
//            e.printStackTrace();
//        }finally {
//            try {
//                statement.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//    }
}
