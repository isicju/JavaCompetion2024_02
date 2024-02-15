package kotojava.competition.groupa;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static java.lang.Double.parseDouble;

public class DefaultSolution {

    public static void main(String[] args) throws IOException {
        String path = args[0];
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("file: " + args[0] + " not found");
        } else {
            // todo put your solution here.
            calculateStats(Files.readAllLines(file.toPath()));
        }
    }

    private static void calculateStats(List<String> cityRecords) {
        double meanDistance = 0, maxDistance = 0, minDistance = 0;
        String maxCity = "", minCity = "";

        for (String record : cityRecords) {
            String[] parts = record.split("[/|]");
            String cityName = parts[1];
            double x = parseDouble(parts[2]);
            double y = parseDouble(parts[3]);
            double distance = distance(x, y);
            meanDistance += distance;

            if (distance > maxDistance) {
                maxDistance = distance;
                maxCity = cityName;
            }
            if (distance < minDistance) {
                minDistance = distance;
                minCity = cityName;
            }
        }
        meanDistance = meanDistance / (double) cityRecords.size();

        //                                                  todo     put your name and solution type
        printResults(minCity, maxCity, meanDistance, "dmitrii", SolutionType.REGULAR);
    }

    private static void printResults(String minCity, String maxCity, double meanDistance, String participantNickName, SolutionType type) {
        System.out.println("min|max|mean|name|group");
        System.out.println(minCity + "|" + maxCity + "|" + meanDistance + "|" + participantNickName + "|" + type);
    }

    enum SolutionType {
        REGULAR, ADVANCED
    }

    private static double distance(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

}
