package com.tunix70.javaio.repository.JDBC;

import com.tunix70.javaio.model.Writer;
import com.tunix70.javaio.repository.WriterRepository;
import com.tunix70.javaio.util.ConnectUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCWriterRepositoryImpl implements WriterRepository {
    private final String SQLUpdateWriter = "UPDATE writer SET firstName = '%s',lastName = '%s'  WHERE id = %d";
    private final String SQLdeleteById = "DELETE FROM writer WHERE id = %d";
    private final String SQLread = "SELECT * FROM writer";

    private final String SQLaddWriter = "INSERT INTO writer (id, firstName, lastName) VALUES ('%d', '%s', '%s')";
    private final String SQLaddRegionWriter = "INSERT INTO region (writer_id) VALUES ('%d')";
    private final String SQLaddPostWriter = "INSERT INTO post (writer_id) VALUES ('%d')";

    private Connection connection = ConnectUtil.getInstance().getConnection();

    @Override
    public List<Writer> getAll() {
        List<Writer> listwriter = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
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
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(String.format
                    (SQLaddWriter, newId, writer.getFirstName(),  writer.getLastName()));
            statement.executeUpdate(String.format(SQLaddRegionWriter, newId));
            statement.executeUpdate(String.format(SQLaddPostWriter, newId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
//        try (Statement statement = connection.createStatement()) {
//            statement.execute(String.format(SQLUpdate, writer.getContent(), getTimeStamp(writer.getUpdated()),
//                    getIntegerwriterStatus(writer.getwriterStatus()), writer.getId()));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return writer;
    }

    @Override
    public void deleteById(Long id) {
        try (Statement statement = connection.createStatement()) {
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
                writer.setFirstName(resultSet.getString("firstName"));
                writer.setLastName(resultSet.getString("lastName"));
//                writer.setPost();
//                writer.setRegion();
                writerList.add(writer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return writerList;
    }

    public Long generateId(){
        if(getAll() != null){
            return getAll().stream()
                    .skip(getAll().size()-1)
                    .findFirst().get().getId()+1;
        }else
            return 1l;
    }
}
