package com.tunix70.javaio.repository.JDBC;

import com.tunix70.javaio.model.Post;
import com.tunix70.javaio.model.Region;
import com.tunix70.javaio.model.Writer;
import com.tunix70.javaio.repository.WriterRepository;
import com.tunix70.javaio.util.ConnectUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCWriterRepositoryImpl implements WriterRepository {
    private final String SQLUpdateWriter = "UPDATE writer SET first_name = ?',last_name = ?  WHERE id = ?";
    private final String SQLdeleteById = "DELETE FROM writer WHERE id = ?";
    private final String SQLread = "SELECT * FROM writer";

    private final String SQLaddWriter = "INSERT INTO writer (id, first_name, last_name) VALUES (?, ?, ?)";
    private final String SQLaddRegionWriter = "UPDATE region SET writer_id = ? WHERE id = ?";
    private final String SQLaddPostWriter = "UPDATE post SET writer_id = ? WHERE id = ?";

//для удаления старых значений при внесении новых при обновлении Writer
    private final String SQLDeleteOldWriter = "UPDATE ? SET writer_id = NULL WHERE writer_id = ?";

//для вытаскивания Post и Region из других таблиц
    private final String SQLgetPost = "SELECT id, content, created, updated, post_status FROM post WHERE writer_id = ?";
    private final String SQLgetRegion = "SELECT id, name FROM region WHERE writer_id = ?";

    @Override
    public List<Writer> getAll() {
        List<Writer> listwriter = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLread)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            listwriter = getWriter(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listwriter;
    }

    @Override
    public Writer getById(Long id) {
        return getAll().stream()
                .filter(writer -> id.equals(writer.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Writer save(Writer writer) {
        Long newId = generateId();
        try (PreparedStatement preparedStatementAddWriter = ConnectUtil.getPreparedStatement(SQLaddWriter);
             PreparedStatement preparedStatementAddRegWriter = ConnectUtil.getPreparedStatement(SQLaddRegionWriter);
             PreparedStatement preparedStatementAddPostWriter = ConnectUtil.getPreparedStatement(SQLaddPostWriter);) {

            preparedStatementAddWriter.setLong(1, newId);
            preparedStatementAddWriter.setString(2, writer.getFirstName());
            preparedStatementAddWriter.setString(3, writer.getLastName());
            preparedStatementAddWriter.executeUpdate();

            preparedStatementAddRegWriter.setLong(1, newId);
            preparedStatementAddRegWriter.setLong(2, writer.getRegion().getId());
            preparedStatementAddRegWriter.executeUpdate();

            ConnectUtil.getInstance().getConnection().setAutoCommit(false);
            for(Long writerPostId : getIdPosts(writer)){
                preparedStatementAddPostWriter.setLong(1, newId);
                preparedStatementAddPostWriter.setLong(2, writerPostId);
                preparedStatementAddPostWriter.executeUpdate();
            }
            ConnectUtil.getInstance().getConnection().setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        try (PreparedStatement preparedStatementUpdateWriter = ConnectUtil.getPreparedStatement(SQLUpdateWriter);
             PreparedStatement preparedStatementAddRegWriter = ConnectUtil.getPreparedStatement(SQLaddRegionWriter);
             PreparedStatement preparedStatementAddPostWriter = ConnectUtil.getPreparedStatement(SQLaddPostWriter);) {
            deleteDuplicatePostRegion(writer.getId());

            preparedStatementUpdateWriter.setString(1, writer.getFirstName());
            preparedStatementUpdateWriter.setString(2, writer.getLastName());
            preparedStatementUpdateWriter.setLong(3, writer.getId());
            preparedStatementUpdateWriter.executeUpdate();

            preparedStatementAddRegWriter.setLong(1, writer.getId());
            preparedStatementAddRegWriter.setLong(2, writer.getRegion().getId());
            preparedStatementAddRegWriter.executeUpdate();

            ConnectUtil.getInstance().getConnection().setAutoCommit(false);
            for(Long writerPostId : getIdPosts(writer)){
                preparedStatementAddPostWriter.setLong(1, writer.getId());
                preparedStatementAddPostWriter.setLong(2, writerPostId);
                preparedStatementAddPostWriter.executeUpdate();
            }
            ConnectUtil.getInstance().getConnection().setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return writer;
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

    private List<Writer> getWriter(ResultSet resultSet) {
        List<Writer> writerList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Writer writer = new Writer();
                writer.setId((long) resultSet.getInt("id"));
                writer.setFirstName(resultSet.getString("first_name"));
                writer.setLastName(resultSet.getString("last_name"));
                writer.setPost(getWritersPostList(writer.getId()));
                writer.setRegion(getWritersRegion(writer.getId()));
                writerList.add(writer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return writerList;
    }

    private Long generateId(){
        if(!getAll().isEmpty()){
            return getAll().stream()
                    .skip(getAll().size()-1)
                    .findFirst().get().getId()+1;
        }else
            return 1l;
    }

    private List<Long> getIdPosts(Writer writer){
        List<Long> writerPostId = new ArrayList<>();
        for(Post writerPost : writer.getPost()){
            writerPostId.add(writerPost.getId());
        }
        return writerPostId;
    }

    private void deleteDuplicatePostRegion(Long writerId) {
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLDeleteOldWriter)) {
            preparedStatement.setString(1, "region");
            preparedStatement.setLong(2, writerId);
            preparedStatement.addBatch();

            preparedStatement.setString(1, "post");
            preparedStatement.setLong(2, writerId);
            preparedStatement.addBatch();

            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Post> getWritersPostList(Long writerId){
        JDBCPostRepositoryImpl postRepo = new JDBCPostRepositoryImpl();
        List<Post> listPost = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLgetPost)) {
            preparedStatement.setLong(1, writerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            listPost = postRepo.getPost(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listPost;

    }


    private Region getWritersRegion(Long writerId) {
        JDBCRegionRepositoryImpl regionRepo = new JDBCRegionRepositoryImpl();
        Region region = new Region();
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLgetRegion)) {
            preparedStatement.setLong(1, writerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Region> regionList = regionRepo.getRegion(resultSet);
            if(!regionList.isEmpty()) {
                region = regionList.get(0);
            }else
                region = null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return region;
    }
}
