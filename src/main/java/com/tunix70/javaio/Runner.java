package com.tunix70.javaio;

import com.tunix70.javaio.repository.io.Util;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException {
        Util.readFile("src/main/resources/files/regions.json");
    }
}
