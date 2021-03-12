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
    private final String SQLUpdate = "UPDATE post SET content = '%s',updated = '%s', postStatusID = '%d'  WHERE id = %d";
    private final String SQLdeleteById = "DELETE FROM post WHERE id LIKE %d";
    private final String SQLread = "SELECT * FROM post";
    private final String SQLadd = "INSERT INTO post (content, created, updated, postStatusID) VALUES ('%s', '%s', '%s', '%d')";

    private Connection connection = ConnectUtil.getInstance().getConnection();

    @Override
    public List<Post> getAll() {
        return sqlReader();
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
            statement.executeUpdate(String.format(SQLadd, post.getContent(),  getTimeStamp(post.getCreated()),
                    getTimeStamp(post.getUpdated()), getIntegerPostStatus(post.getPostStatus())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public Post update(Post post) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format(SQLUpdate, post.getContent(), getTimeStamp(post.getUpdated()),
                    getIntegerPostStatus(post.getPostStatus()), post.getId()));
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

    public List<Post> sqlReader() {
        List<Post> listpost = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQLread);
            listpost = getPostFromSQL(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listpost;
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
                post.setPostStatus(getPostStatus(resultSet.getInt("postStatusID")));
                postList.add(post);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return postList;
    }

    private PostStatus getPostStatus(Integer postStatusId) {
        PostStatus postStatus = null;
        if (postStatusId.equals(1) || postStatusId.equals("ACTIVE")) {
            postStatus = PostStatus.ACTIVE;
        }
        if (postStatusId.equals(2) || postStatusId.equals("DELETED"))
            postStatus = PostStatus.DELETED;
        return postStatus;
    }

    private Integer getIntegerPostStatus(PostStatus postStatus) {
        Integer postStatusId = null;
        if (postStatus == PostStatus.ACTIVE) {
            postStatusId = 1;
        }
        if (postStatus == PostStatus.DELETED) {
            postStatusId = 2;
        }
        return postStatusId;
    }

    private Timestamp getTimeStamp(Long time){
        return new Timestamp(time);
    }
}
