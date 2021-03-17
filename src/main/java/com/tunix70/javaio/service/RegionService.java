package com.tunix70.javaio.service;

import com.tunix70.javaio.model.Region;
import com.tunix70.javaio.repository.JDBC.JDBCRegionRepositoryImpl;
import com.tunix70.javaio.repository.RegionRepository;

import java.util.List;

public class RegionService {
    private RegionRepository regionRepository = new JDBCRegionRepositoryImpl();

    public List<Region> getAll(){
        return regionRepository.getAll();
    }
    public Region getById(Long id){
        return regionRepository.getById(id);
    }
    public Region save(Region region){
        return regionRepository.save(region);
    }
    public Region update(Region region){
        return regionRepository.update(region);
    }
    public void deleteById(Long id){
        regionRepository.deleteById(id);
    }
}
