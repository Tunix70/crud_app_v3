package com.tunix70.javaio.controller;

import com.tunix70.javaio.model.Writer;
import com.tunix70.javaio.repository.JDBC.JDBCWriterRepositoryImpl;
import com.tunix70.javaio.repository.WriterRepository;
import com.tunix70.javaio.service.RegionService;
import com.tunix70.javaio.service.WriterService;


import java.util.List;

public class WriterController {
    private WriterService writerService = new WriterService();

    public List<Writer> getAll(){
        return writerService.getAll();
    }
    public Writer getById(Long id){
        return writerService.getById(id);
    }
    public Writer save(Writer writer){
        return writerService.save(writer);
    }
    public Writer update(Writer writer){
        return writerService.update(writer);
    }
    public void deleteById(Long id){
        writerService.deleteById(id);
    }
}
