package com.tunix70.javaio;

import com.tunix70.javaio.controller.PostController;

import com.tunix70.javaio.model.Post;


import java.io.IOException;


public class Runner {
    public static void main(String[] args) throws IOException {
            PostController pc = new PostController();
            pc.save(new Post(null, "text", null, null));

    }
}
