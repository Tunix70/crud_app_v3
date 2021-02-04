package com.tunix70.javaio.repository.ioJson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tunix70.javaio.model.User;
import com.tunix70.javaio.repository.UserRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonUserRepositoryImpl implements UserRepository {
    private final String userFile = "files/users.json";
    private static final Gson gson = new Gson();

    @Override
    public List<User> getAll() {
        return readFile(userFile);
    }

    @Override
    public User getById(Long id) {
        return getAll().stream()
                .filter(p -> id.equals(p.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User save(User user) {
        if(user.getId() == null){
            user.setId(generateByID());
            List<User> newUserList = getAll();
            if(newUserList == null){
                newUserList = new ArrayList<>();
            }
            newUserList.add(user);
            writeFile(newUserList, userFile);
        }
        else System.out.println("Данный пользователь уже есть в базе");
        return user;
    }

    @Override
    public User update(User user) {
        List<User> posts = getAll();
        posts.stream().peek(s -> {
            if (s.getId().equals(user.getId()))
                s.setFirstName(user.getFirstName());
                s.setLastName(user.getLastName());
                s.setPost(user.getPost());
                s.setRegion(user.getRegion());
                s.setRole(user.getRole());
        }).collect(Collectors.toList());
        writeFile(posts, userFile);
        return user;
    }

    @Override
    public void deleteById(Long id) {
        List<User> list = getAll();
        list.removeIf(n -> n.getId().equals(id));
        writeFile(list, userFile);
    }

    public List<User> readFile(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String st = br.readLine();
            String jsonFile = "";
            while (st != null) {
                jsonFile += st;
                st = br.readLine();
            }
            Type listUser = new TypeToken<List<User>>() {}.getType();
            List<User> list = gson.fromJson(jsonFile, listUser);
            return list;
        }catch (IOException e){
            System.out.println("Файл не читается" + e);
            return null;
        }
    }

    public void writeFile(List<User> user, String file){
        try(Writer writer = new FileWriter(file)){

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

