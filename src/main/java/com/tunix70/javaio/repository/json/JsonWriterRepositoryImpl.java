package com.tunix70.javaio.repository.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tunix70.javaio.model.Writer;
import com.tunix70.javaio.repository.WriterRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonWriterRepositoryImpl implements WriterRepository {
    private final String userFile = "src/main/resources/files/users.json";
    private static final Gson gson = new Gson();

    @Override
    public List<Writer> getAll() {
        return readFile(userFile);
    }

    @Override
    public Writer getById(Long id) {
        return getAll().stream()
                .filter(p -> id.equals(p.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Writer save(Writer writer) {
        if(writer.getId() == null){
            writer.setId(generateByID());
            List<Writer> newWriterList = getAll();
            if(newWriterList == null){
                newWriterList = new ArrayList<>();
            }
            newWriterList.add(writer);
            writeFile(newWriterList, userFile);
        }
        else System.out.println("Данный пользователь уже есть в базе");
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        List<Writer> posts = getAll();
        posts.stream().peek(s -> {
            if (s.getId().equals(writer.getId()))
                s.setFirstName(writer.getFirstName());
                s.setLastName(writer.getLastName());
                s.setPost(writer.getPost());
                s.setRegion(writer.getRegion());
        }).collect(Collectors.toList());
        writeFile(posts, userFile);
        return writer;
    }

    @Override
    public void deleteById(Long id) {
        List<Writer> list = getAll();
        list.removeIf(n -> n.getId().equals(id));
        writeFile(list, userFile);
    }

    public List<Writer> readFile(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String st = br.readLine();
            String jsonFile = "";
            while (st != null) {
                jsonFile += st;
                st = br.readLine();
            }
            Type listUser = new TypeToken<List<Writer>>() {}.getType();
            List<Writer> list = gson.fromJson(jsonFile, listUser);
            return list;
        }catch (IOException e){
            System.out.println("Файл не читается" + e);
            return null;
        }
    }

    public void writeFile(List<Writer> user, String file){
        try(java.io.Writer writer = new FileWriter(file)){
            //convert Object to JSON
            gson.toJson(user, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Long generateByID() {
        if(getAll() != null){
            return getAll().stream()
                    .skip(getAll().size()-1)
                    .findFirst().get().getId()+1;
        }else
            return 1l;
    }
}

