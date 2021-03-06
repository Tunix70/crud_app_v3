package com.tunix70.javaio.service;

import com.tunix70.javaio.model.Post;
import com.tunix70.javaio.repository.JDBC.JDBCPostRepositoryImpl;
import com.tunix70.javaio.repository.PostRepository;

import java.util.List;

public class PostService {
    private PostRepository postRepository = new JDBCPostRepositoryImpl();

    public List<Post> getAll(){
        return postRepository.getAll();
    }
    public Post getById(Long id){
        return postRepository.getById(id);
    }
    public Post save(Post post){
        return postRepository.save(post);
    }
    public Post update(Post post){
        return postRepository.update(post);
    }
    public void deleteById(Long id){
        postRepository.deleteById(id);
    }
}
