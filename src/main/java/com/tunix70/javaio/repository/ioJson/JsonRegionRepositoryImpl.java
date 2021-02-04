package com.tunix70.javaio.repository.ioJson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tunix70.javaio.model.Region;
import com.tunix70.javaio.repository.RegionRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class JsonRegionRepositoryImpl implements RegionRepository {
    private final String regionFile = "files/regions.json";
    private static final Gson gson = new Gson();

    @Override
    public List<Region> getAll(){
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
            if(newRegionList == null){
                newRegionList = new ArrayList<>();
            }
            newRegionList.add(region);
            writeFile(newRegionList, regionFile);
        }
        else System.out.println("Данный регион уже есть в базе");
        return region;
    }

    @Override
    public Region update(Region region) {
        List<Region> regions = getAll();
        regions.stream().peek(s -> {
                    if (s.getId().equals(region.getId()))
                        s.setName(region.getName());
        }).collect(Collectors.toList());
        writeFile(regions, regionFile);
            return region;
        }


    @Override
    public void deleteById(Long id) {
        List<Region> list = getAll();
        list.removeIf(n -> n.getId().equals(id));
        writeFile(list, regionFile);
    }

    public List<Region> readFile(String file) {
        List<Region> list = new ArrayList<>();
        String st;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            st = br.readLine();
            String jsonFile = "";
            while (st != null) {
                jsonFile += st;
                st = br.readLine();
            }
            Type listRegion = new TypeToken<List<Region>>() {}.getType();
            list = gson.fromJson(jsonFile, listRegion);

        }catch (IOException e){
            System.out.println("Файл не читается" + e);
        }
        return list;
    }

    public void writeFile(List<Region> region, String file){
        try(Writer writer = new FileWriter(file)){

            //convert Object to JSON
            gson.toJson(region, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Long generateByID() {
        if(getAll() != null){
            return getAll().stream()
                    .skip(getAll().size()-1)
                    .findFirst().get().getId()+1;
        }else
            return 1l;
    }
}
