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
    private final String SQLUpdate = "UPDATE post SET name = '%s',  WHERE id = %d";
    private final String SQLdeleteById = "DELETE FROM post WHERE id LIKE %d";
    private final String SQLread = "SELECT * FROM post";
    private final String SQLadd = "INSERT INTO post (name) VALUES ('%s')";

    private Connection connection = ConnectUtil.getInstance().getConnection();

    @Override
    public List<Region> getAll() {
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
        sqlWriter(post);
        return post;
    }

    @Override
    public Post update(Post post) {
        try (Statement statement = connection.createStatement()){
            statement.execute(String.format(SQLUpdate, post.getName(), post.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void deleteById(Long id) {
        try (Statement statement = connection.createStatement()){
            statement.execute(String.format(SQLdeleteById, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Post> sqlReader() {
        List<Post> listpost= new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQLread);
            listpost = getPostFromSQL(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listpost;
    }

    public void sqlWriter(Post post) {
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(String.format(SQLadd, post.getContent()));
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
                post.setContent(resultSet.getString("name"));
                post.setCreated(resultSet.getTimestamp("created"));
                post.setCreated(resultSet.getTimestamp("updated"));
                post.setPostStatus(resultSet.getString("postStatusID"));
                postList.add(post);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return postList;
    }
}
