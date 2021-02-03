package com.tunix70.javaio;

import com.tunix70.javaio.controller.RegionController;
import com.tunix70.javaio.model.Post;
import com.tunix70.javaio.model.Region;
import com.tunix70.javaio.repository.io.JavaIORegionRepositoryImpl;
import com.tunix70.javaio.repository.io.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws IOException {
        RegionController rc = new RegionController();
        rc.deleteById(5l);

    }
}
