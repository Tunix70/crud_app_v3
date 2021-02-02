package com.tunix70.javaio;

import com.tunix70.javaio.model.Region;
import com.tunix70.javaio.repository.io.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws IOException {
//        Util.readFile("src/main/resources/files/regions.json");
//        Region region = new Region(2l, "EU");
//        Region region1 = new Region(3l, "FR");
//
//        List<Region> regList = new ArrayList<>();
//        regList.add(region1);
//        regList.add(region);
//
//
//        Util.writeFile(regList, "src/main/resources/files/regions.json");
        Util.readFile("src/main/resources/files/regions.json");
    }
}
