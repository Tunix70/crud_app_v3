package com.tunix70.javaio.repository.sqlRepo;

import com.tunix70.javaio.model.Region;
import com.tunix70.javaio.repository.RegionRepository;
import com.tunix70.javaio.util.ConnectUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RegionSQL implements RegionRepository {
    private Connection connection = ConnectUtil.getInstance().getConnection();
    private Statement statement;

    @Override
    public List<Region> getAll() {
        return sqlReader();
    }

    @Override
    public Region getById(Long id) {
        return getAll().stream()
                .filter(p -> id.equals(p.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Region save(Region region) {
        sqlWriter(region);
        return region;
    }

    @Override
    public Region update(Region region) {
        String SQLUpdate = "UPDATE region SET name = '" + region.getName() + "' WHERE id = " + region.getId();
        try{
            statement = connection.createStatement();
            statement.execute(SQLUpdate);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return region;
    }

    @Override
    public void deleteById(Long id) {
        String SQLdeleteById = "DELETE FROM region WHERE id LIKE " + id;
        try{
            statement = connection.createStatement();
            statement.execute(SQLdeleteById);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private List<Region> sqlReader(){
        String SQLread = "SELECT * FROM region";
        List<Region> regionList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQLread);
            while(resultSet.next()){
                Region region = new Region();
                region.setId((long) resultSet.getInt("id"));
                region.setName(resultSet.getString("name"));
                regionList.add(region);
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
        return regionList;
    }

    public void sqlWriter(Region region){
        String SQLadd = "INSERT INTO region (name) VALUES ('" + region.getName() + "')";
        try{
            statement = connection.createStatement();
            statement.executeUpdate(SQLadd);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
