package com.tunix70.javaio.repository.io;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import com.tunix70.javaio.model.Post;
import com.tunix70.javaio.model.Region;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Util {
/*    private static final Gson gson = new Gson();*/

//    public static List<Object> readFile(String file) {
//        ArrayList<Object> list = null;
//        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//            String st = br.readLine();
//            String jsonFile = "";
//            while (st != null) {
//                jsonFile += st;
//                st = br.readLine();
//            }
//
//            Type listObject = new TypeToken<ArrayList<Object>>() {}.getType();
//            list = gson.fromJson(jsonFile, listObject);
//
//        }catch (IOException e){
//            System.out.println("File not found exception");
//        }
//        return list;
//    }


//    public static void writeFile(List<Object> object, String file){
//        try(Writer writer = new FileWriter(file)){
//
//            //convert Object to JSON
//            gson.toJson(object, writer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
