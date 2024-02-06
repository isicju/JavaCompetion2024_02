package com.example;

import main.java.com.example.CityData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class Generator {
    public static void main(String[] args) throws Exception {
        generateRecords(1_000_000, readCities());
    }

    private static List<String> readCities() throws Exception {
        URL url = Generator.class.getClassLoader().getResource("cities.txt");
        return Files.lines(Paths.get(url.toURI()), StandardCharsets.UTF_8).collect(Collectors.toList());
    }

    private static void generateRecords(int limit, List<String> cityNames) {
        String fileName = "data.txt";
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
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
