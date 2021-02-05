package com.tunix70.javaio.repository.ioJson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tunix70.javaio.model.Post;
import com.tunix70.javaio.repository.PostRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JsonPostRepositoryImpl implements PostRepository {
    private final String postFile = "src/main/resources/files/posts.json";
    private static final Gson gson = new Gson();
    private Long date = new Date().getTime();

    @Override
    public List<Post> getAll() {
        return readFile(postFile);
    }

    @Override
    public Post getById(Long id) {
        return getAll().stream()
                .filter(p -> id.equals(p.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Post save(Post post) {
        if(post.getId() == null){
            post.setId(generateByID());
            post.setCreated(date);
            post.setUpdated(date);
            List<Post> newPostList = getAll();
            if(newPostList == null){
                newPostList = new ArrayList<>();
            }
            newPostList.add(post);
            writeFile(newPostList, postFile);
        }
        else System.out.println("Данная статья уже есть в базе");
        return post;
    }

    @Override
    public Post update(Post post) {
        List<Post> posts = getAll();
        posts.stream().peek(s -> {
            if (s.getId().equals(post.getId()))
                s.setContent(post.getContent());
                s.setUpdated(date);
        }).collect(Collectors.toList());
        writeFile(posts, postFile);
        return post;
    }


    @Override
    public void deleteById(Long id) {
        List<Post> list = getAll();
        list.removeIf(n -> n.getId().equals(id));
        writeFile(list, postFile);
    }

    public List<Post> readFile(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String st = br.readLine();
            String jsonFile = "";
            while (st != null) {
                jsonFile += st;
                st = br.readLine();
            }
            Type listPost = new TypeToken<List<Post>>() {}.getType();
            List<Post> list = gson.fromJson(jsonFile, listPost);
            return list;
        }catch (IOException e){
            System.out.println("Problem to read file: " + e);
            return null;
        }
    }

    public void writeFile(List<Post> post, String file){
        try(Writer writer = new FileWriter(file)){

            //convert Object to JSON
            gson.toJson(post, writer);
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
