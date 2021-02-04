package com.tunix70.javaio.controller;

import com.tunix70.javaio.model.User;
import com.tunix70.javaio.repository.UserRepository;
import com.tunix70.javaio.repository.ioJson.JsonUserRepositoryImpl;

import java.util.List;

public class UserController {
    private UserRepository userRepository = new JsonUserRepositoryImpl();

    public List<User> getAll(){
        return userRepository.getAll();
    }
    public User getById(Long id){
        return userRepository.getById(id);
    }
    public User save(User user){
        return userRepository.save(user);
    }
    public User update(User user){
        return userRepository.update(user);
    }
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
}
