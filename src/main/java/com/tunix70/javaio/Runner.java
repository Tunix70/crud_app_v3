package com.tunix70.javaio;

import com.tunix70.javaio.controller.RegionController;
import com.tunix70.javaio.model.Post;
import com.tunix70.javaio.model.Region;
import com.tunix70.javaio.repository.io.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws IOException {
//        Util.readFile("C:\\Users\\Konstantin\\IdeaProjects\\CRUDapp_new\\src\\main\\resources\\files\\regions.json");
        Region region = new Region(2l, "EU");
        Region region1 = new Region(3l, "FR");
//        System.out.println("||===============================================================||");
        List<Region> regList = new ArrayList<>();
        regList.add(region1);
        regList.add(region);

//        Util.writeFile(Collections.singletonList((List<Region>) regList), "C:\\\\Users\\\\Konstantin\\\\IdeaProjects\\\\CRUDapp_new\\\\src\\\\main\\\\resources\\\\files\\\\regions.json");
//        Util.readFile("C:\\Users\\Konstantin\\IdeaProjects\\CRUDapp_new\\src\\main\\resources\\files\\posts.json");

//        Post post1 = new Post(1l, "one", 123l, 123l);
//        Post post2 = new Post(2l, "two", 123l, 123l);
//        List<Post> postList = new ArrayList<>();
//        postList.add(post1);
//        postList.add(post2);
//        Util.writeFile(postList, "C:\\Users\\Konstantin\\IdeaProjects\\CRUDapp_new\\src\\main\\resources\\files\\posts.json");
        RegionController rc = new RegionController();
//        System.out.println(rc.getAll());
//        System.out.println(rc.getById(1l));
        rc.save(new Region(null, "RU"));


    }
}
