package com.transport.transportation.dto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestEx {

    public static void main(String[] args) throws IOException {
        String filePath = "/Users/ravi/Desktop/1Btestit.txt";
        byte[] bFile = Files.readAllBytes(Paths.get(filePath));
        System.out.println(bFile);


    }
}
