package com.tunix70.javaio.controller;

import com.tunix70.javaio.model.Region;
import com.tunix70.javaio.repository.RegionRepository;
import com.tunix70.javaio.repository.JDBC.JDBCRegionRepositoryImpl;
import com.tunix70.javaio.service.RegionService;

import java.util.List;

public class RegionController {
    private RegionService regionService = new RegionService();

    public List<Region> getAll(){
        return regionService.getAll();
    }
    public Region getById(Long id){
        return regionService.getById(id);
    }
    public Region save(Region region){
        return regionService.save(region);
    }
    public Region update(Region region){
        return regionService.update(region);
    }
    public void deleteById(Long id){
        regionService.deleteById(id);
    }
}


