package com.tunix70.javaio.repository.io;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tunix70.javaio.model.Region;
import com.tunix70.javaio.repository.RegionRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class JavaIORegionRepositoryImpl implements RegionRepository {
    private final String regionFile = "C:\\Users\\Konstantin\\IdeaProjects\\CRUDapp_new\\src\\main\\resources\\files\\regions.json";
    private final static Gson gson = new Gson();

    @Override
    public List<Region> getAll() {
        return readFile(regionFile);
    }

    @Override
    public Region getById(Long id) {
        return getAll().stream()
                .filter(p -> id.equals(p.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Region save(Region region) {
        if(region.getId() == null){
            region.setId(generateByID());
            List<Region> newRegionList = getAll();
            newRegionList.add(region);
            writeFile(newRegionList, regionFile);
        }
        else update(region);
        return region;
    }

    @Override
    public Region update(Region region) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    public static List<Region> readFile(String file) {
        ArrayList<Region> list = null;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String st = br.readLine();
            String jsonFile = "";
            while (st != null) {
                jsonFile += st;
                st = br.readLine();
            }

            Type listRegion = new TypeToken<ArrayList<Region>>() {}.getType();
            list = gson.fromJson(jsonFile, listRegion);

        }catch (IOException e){
            System.out.println("File not found exception");
        }
        return list;
    }

    public static void writeFile(List<Region> region, String file){
        try(Writer writer = new FileWriter(file)){

            //convert Object to JSON
            gson.toJson(region, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Long generateByID(){
        if(!getAll().isEmpty()){
            return getAll().stream()
                    .skip(getAll().size()-1)
                    .findFirst().get().getId()+1;
        }else
            return 1l;
    }
}
