package com.tunix70.javaio.repository.io;

import com.google.gson.Gson;
import com.tunix70.javaio.model.Region;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Util {
    public static void readFile(String file) throws IOException {
        try(Reader reader = new FileReader(file)) {
            Gson gson = new Gson();

            //convert JSON to Region
            Region region = gson.fromJson(reader, Region.class);
            System.out.println(region.toString());
        }
    }

    public static void writeFile(String file){

    }
}
