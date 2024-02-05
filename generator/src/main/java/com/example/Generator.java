package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Generator {
    public static void main(String[] args) {
//        if ("generate".equals(args[0])) {
            generate();
//        }
    }

    private static void generate() {
        try (InputStream inputStream = com.example.Generator.class.getClassLoader().getResourceAsStream("cities.txt")) {
            if (inputStream != null) {
                // Use a Scanner to read the content of the file
                Scanner scanner = new Scanner(inputStream);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                }
            } else {
                System.err.println("Resource not found");
            }
        } catch (IOException e) {
            System.err.println("Resource not found:" + e.getMessage());
        }
    }
}
