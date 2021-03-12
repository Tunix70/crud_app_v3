package com.tunix70.javaio.repository.JDBC;

import com.tunix70.javaio.model.Region;
import com.tunix70.javaio.repository.RegionRepository;
import com.tunix70.javaio.util.ConnectUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCRegionRepositoryImpl implements RegionRepository {
    private final String SQLUpdate = "UPDATE region SET name = '%s' WHERE id = %d";
    private final String SQLdeleteById = "DELETE FROM region WHERE id = %d";
    private final String SQLread = "SELECT * FROM region";
    private final String SQLadd = "INSERT INTO region (id, name) VALUES ('%d','%s')";

    private Connection connection = ConnectUtil.getInstance().getConnection();

    @Override
    public List<Region> getAll() {
        List<Region> listRegion= new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQLread);
            listRegion = getRegionFromSQL(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listRegion;
    }

    @Override
    public Region getById(Long id) {
        return getAll().stream()
                .filter(region -> id.equals(region.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Region save(Region region) {
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(String.format(SQLadd, generateId(), region.getName()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return region;
    }

    @Override
    public Region update(Region region) {
        try (Statement statement = connection.createStatement()){
            statement.execute(String.format(SQLUpdate, region.getName(), region.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return region;
    }

    @Override
    public void deleteById(Long id) {
        try (Statement statement = connection.createStatement()){
            statement.execute(String.format(SQLdeleteById, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Region> getRegionFromSQL(ResultSet resultSet) {
        List<Region> regionList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Region region = new Region();
                region.setId((long) resultSet.getInt("id"));
                region.setName(resultSet.getString("name"));
                regionList.add(region);
            }
        }catch (SQLException throwables) {
        throwables.printStackTrace();
    }
        return regionList;
    }

    public Long generateId(){
            if(!getAll().isEmpty()){
                return getAll().stream()
                        .skip(getAll().size()-1)
                        .findFirst().get().getId()+1;
            }else
                return 1L;
        }
}
