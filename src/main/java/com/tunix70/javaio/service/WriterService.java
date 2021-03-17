package com.tunix70.javaio.service;

import com.tunix70.javaio.model.Writer;
import com.tunix70.javaio.repository.JDBC.JDBCWriterRepositoryImpl;
import com.tunix70.javaio.repository.WriterRepository;

import java.util.List;

public class WriterService {
    private WriterRepository writerRepository = new JDBCWriterRepositoryImpl();

    public List<Writer> getAll(){
        return writerRepository.getAll();
    }
    public Writer getById(Long id){
        return writerRepository.getById(id);
    }
    public Writer save(Writer writer){
        return writerRepository.save(writer);
    }
    public Writer update(Writer writer){
        return writerRepository.update(writer);
    }
    public void deleteById(Long id){
        writerRepository.deleteById(id);
    }
}
