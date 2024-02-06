package com.example;

import main.java.com.example.CityData;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class Generator {
    public static void main(String[] args) throws Exception {
        int limit = args.length > 0 ? Integer.parseInt(args[0]) : 1_000_000;
        String finalDataLocation = args.length > 1 && args[1] != null && !args[1].isBlank() ? args[1] : "data.txt";
        generateRecords(limit, readCities(), finalDataLocation);
    }

    private static List<String> readCities() throws Exception {
        try (InputStream inputStream = Generator.class.getClassLoader().getResourceAsStream("cities.txt")) {
            if (inputStream == null) {
                throw new FileNotFoundException("cities.txt not found in the classpath");
            }
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            return new BufferedReader(reader).lines().collect(Collectors.toList());
        }
    }

    private static void generateRecords(int limit, List<String> cityNames, String finalPath) {
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(finalPath))) {
            for (int i = 1; i <= limit; i++) {
                String line = i + "|" + generateCityName(cityNames, i) + "|" + decimalFormat.format(generateXValue(i)) + "|" +
                        decimalFormat.format(generateYValue(i)) + "|" + ((i == limit) ? "" : "\n");
                writer.write(line);
            }
        } catch (IOException e) {
            System.err.println("can't write int data.txt: " + e.getMessage());
        }
    }

    private static String generateCityName(List<String> cityName, int index) {
        boolean isLessThanSize = cityName.size() > index;
        if (isLessThanSize) {
            return cityName.get(index);
        } else {
            int postfix = index / cityName.size();
            int position = index % cityName.size();
            return cityName.get(position) + postfix;
        }

    }

    private static double generateXValue(int i) {
        return ((i * 3 - i ^ 2) % 200 / 12d + 33 - i * 31) % 1000;
    }

    private static double generateYValue(int i) {
        return ((i * 3 - i ^ 3) % 120 / 2d + 33 - i * 731) % 2000;
    }

}
