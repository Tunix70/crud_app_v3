package com.tunix70.javaio.repository.JDBC;

import com.tunix70.javaio.exceptions.InputCheckException;
import com.tunix70.javaio.model.Post;
import com.tunix70.javaio.model.PostStatus;
import com.tunix70.javaio.model.Region;
import com.tunix70.javaio.repository.PostRepository;
import com.tunix70.javaio.util.ConnectUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JDBCPostRepositoryImpl implements PostRepository {
    private final String SQLUpdate = "UPDATE post SET content = '%s',updated = '%s', post_status = '%s'  WHERE id = %d";
    private final String SQLdeleteById = "DELETE FROM post WHERE id = %d";
    private final String SQLread = "SELECT * FROM post";
    private final String SQLadd = "INSERT INTO post (content, created, updated, post_status) VALUES ('%s', '%s', '%s', '%s')";

    private Connection connection = ConnectUtil.getInstance().getConnection();
    private Long date = new Date().getTime();

    @Override
    public List<Post> getAll() {
        List<Post> listpost = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQLread);
            listpost = getPostFromSQL(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listpost;
    }

    @Override
    public Post getById(Long id) {
        return getAll().stream()
                .filter(post -> id.equals(post.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Post save(Post post) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(String.format(SQLadd, post.getContent(),  getTimeStamp(date),
                    getTimeStamp(date), post.getPostStatus()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public Post update(Post post) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format(SQLUpdate, post.getContent(), getTimeStamp(date),
                    post.getPostStatus(), post.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void deleteById(Long id) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format(SQLdeleteById, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Post> getPostFromSQL(ResultSet resultSet) {
        List<Post> postList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Post post = new Post();
                post.setId((long) resultSet.getInt("id"));
                post.setContent(resultSet.getString("content"));
                post.setCreated(resultSet.getTimestamp("created").getTime());
                post.setUpdated(resultSet.getTimestamp("updated").getTime());
                post.setPostStatus(PostStatus.valueOf(resultSet.getString("post_status")));
                postList.add(post);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return postList;
    }

    private Timestamp getTimeStamp(Long time){
        return new Timestamp(time);
    }
}
