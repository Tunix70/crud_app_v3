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
    private final String SQLUpdateWriter = "UPDATE writer SET first_name = '%s',last_name = '%s'  WHERE id = %d";
    private final String SQLdeleteById = "DELETE FROM writer WHERE id = %d";
    private final String SQLread = "SELECT * FROM writer";

    private final String SQLaddWriter = "INSERT INTO writer (id, first_name, last_name) VALUES ('%d', '%s', '%s')";
    private final String SQLaddRegionWriter = "UPDATE region SET writer_id = '%d' WHERE id = %d";
    private final String SQLaddPostWriter = "UPDATE post SET writer_id = '%d' WHERE id = %d";
//для удаления старых значений при внесении новых при обновлении Writer
    private final String SQLDeleteOldPostWriter = "UPDATE post SET writer_id = NULL WHERE writer_id = %d";
    private final String SQLDeleteOldRegionWriter = "UPDATE region SET writer_id = NULL WHERE writer_id = %d";
//для вытаскивания Post и Region из других таблиц
    private final String SQLgetPost = "SELECT id, content, created, updated, post_status FROM post WHERE writer_id = '%d'";
    private final String SQLgetRegion = "SELECT id, name FROM region WHERE writer_id = '%d'";

    @Override
    public List<Writer> getAll() {
        List<Writer> listwriter = new ArrayList<>();
        try (Statement statement = ConnectUtil.getStatement()) {
            ResultSet resultSet = statement.executeQuery(SQLread);
            listwriter = getWriterFromSQL(resultSet);
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
        try (Statement statement = ConnectUtil.getStatement()) {
            String SQL1 = (String.format
                    (SQLaddWriter, newId, writer.getFirstName(),  writer.getLastName()));
            statement.addBatch(SQL1);
            String SQL2 = (String.format(SQLaddRegionWriter, newId, writer.getRegion().getId()));
            statement.addBatch(SQL2);
            for(Long writerPostId : getIdPosts(writer)){
                String SQL = (String.format(SQLaddPostWriter, newId, writerPostId));
                statement.addBatch(SQL);
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        try (Statement statement = ConnectUtil.getStatement()) {
            deleteDuplicatePostRegion(writer.getId());
            String SQL1 = (String.format(
                    SQLUpdateWriter, writer.getFirstName(),  writer.getLastName(), writer.getId()));
            statement.addBatch(SQL1);
            String SQL2 = (
                    String.format(SQLaddRegionWriter, writer.getId(), writer.getRegion().getId()));
            statement.addBatch(SQL2);
            for(Long writerPostId : getIdPosts(writer)){
                String SQL = (String.format(SQLaddPostWriter, writer.getId(), writerPostId));
                statement.addBatch(SQL);
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return writer;
    }

    @Override
    public void deleteById(Long id) {
        try (Statement statement = ConnectUtil.getStatement()) {
            statement.execute(String.format(SQLdeleteById, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Writer> getWriterFromSQL(ResultSet resultSet) {
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

    public Long generateId(){
        if(!getAll().isEmpty()){
            return getAll().stream()
                    .skip(getAll().size()-1)
                    .findFirst().get().getId()+1;
        }else
            return 1l;
    }

    public List<Long> getIdPosts(Writer writer){
        List<Long> writerPostId = new ArrayList<>();
        for(Post writerPost : writer.getPost()){
            writerPostId.add(writerPost.getId());
        }
        return writerPostId;
    }

    public void deleteDuplicatePostRegion(Long writerId) {
        try (Statement statement = ConnectUtil.getStatement()) {
            String SQL = (String.format(SQLDeleteOldRegionWriter, writerId));
            statement.addBatch(SQL);
            String SQL1 = (String.format(SQLDeleteOldPostWriter, writerId));
            statement.addBatch(SQL1);
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Post> getWritersPostList(Long writerId){
        JDBCPostRepositoryImpl postRepo = new JDBCPostRepositoryImpl();
        List<Post> listPost = new ArrayList<>();
        try (Statement statement = ConnectUtil.getStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format(SQLgetPost, writerId));
            listPost = postRepo.getPostFromSQL(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listPost;

    }


    public Region getWritersRegion(Long writerId) {
        JDBCRegionRepositoryImpl regionRepo = new JDBCRegionRepositoryImpl();
        Region region = new Region();
        try (Statement statement = ConnectUtil.getStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format(SQLgetRegion, writerId));
            List<Region> regionList = regionRepo.getRegionFromSQL(resultSet);
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
