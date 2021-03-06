package com.tunix70.javaio.controller;

import com.tunix70.javaio.model.Post;
import com.tunix70.javaio.repository.JDBC.JDBCPostRepositoryImpl;
import com.tunix70.javaio.repository.PostRepository;
import com.tunix70.javaio.service.PostService;

import java.util.List;

public class PostController {
    private PostService postService = new PostService();

    public List<Post> getAll(){
        return postService.getAll();
    }
    public Post getById(Long id){
        return postService.getById(id);
    }
    public Post save(Post post){
        return postService.save(post);
    }
    public Post update(Post post){
        return postService.update(post);
    }
    public void deleteById(Long id){
        postService.deleteById(id);
    }

}
