package com.tunix70.javaio.repository.io;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tunix70.javaio.model.Region;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Util {
    private static final Gson gson = new Gson();

    public static void readFile(String file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String st = br.readLine();
        String jsonFile = "";
        while (st != null) {
            jsonFile += st;
            st = br.readLine();
        }

        Type listRegion = new TypeToken<ArrayList<Region>>(){}.getType();
        ArrayList<Region> regions = gson.fromJson(jsonFile, listRegion);

        for(Region region : regions) {
            System.out.println(region);
        }

    }

    public static void writeFile(List<Region> region, String file){
        try(Writer writer = new FileWriter(file)){

            //convert Object to JSON
            gson.toJson(region, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
