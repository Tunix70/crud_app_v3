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
    private final String SQLUpdate = "UPDATE post SET content = ?,updated = ?, post_status = ?  WHERE id = ?";
    private final String SQLdeleteById = "DELETE FROM post WHERE id = ?";
    private final String SQLread = "SELECT * FROM post";
    private final String SQLadd = "INSERT INTO post (id, content, created, updated, post_status)" +
            " VALUES (?, ?, ?, ?, ?)";
    private Long date = new Date().getTime();

    @Override
    public List<Post> getAll() {
        List<Post> listpost = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLread)) {
            ResultSet resultSet = preparedStatement.executeQuery();
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
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLadd)) {
            preparedStatement.setLong(1, generateId());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setTimestamp(3, getTimeStamp(date));
            preparedStatement.setTimestamp(4, getTimeStamp(date));
            preparedStatement.setString(5, String.valueOf(post.getPostStatus()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public Post update(Post post) {
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLUpdate)) {
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setTimestamp(2, getTimeStamp(date));
            preparedStatement.setString(3, String.valueOf(post.getPostStatus()));
            preparedStatement.setLong(4, post.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void deleteById(Long id) {
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLdeleteById)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
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
    public Long generateId(){
        if(!getAll().isEmpty()){
            return getAll().stream()
                    .skip(getAll().size()-1)
                    .findFirst().get().getId()+1;
        }else
            return 1l;
    }
}
