package com.tunix70.javaio.repository.JDBC;

import com.tunix70.javaio.model.Region;
import com.tunix70.javaio.repository.RegionRepository;
import com.tunix70.javaio.util.ConnectUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCRegionRepositoryImpl implements RegionRepository {
    private final String SQLUpdate = "UPDATE region SET name = ? WHERE id = ?";
    private final String SQLdeleteById = "DELETE FROM region WHERE id = ?";
    private final String SQLread = "SELECT * FROM region";
    private final String SQLadd = "INSERT INTO region (id, name) VALUES (?,?)";

    @Override
    public List<Region> getAll() {
        List<Region> listRegion= new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLread)){
            ResultSet resultSet = preparedStatement.executeQuery();
            listRegion = getRegion(resultSet);
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
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLadd)){
            preparedStatement.setLong(1, generateId());
            preparedStatement.setString(2, region.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return region;
    }

    @Override
    public Region update(Region region) {
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLUpdate)){
            preparedStatement.setString(1, region.getName());
            preparedStatement.setLong(2, region.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return region;
    }

    @Override
    public void deleteById(Long id) {
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLdeleteById)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Region> getRegion(ResultSet resultSet) {
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
