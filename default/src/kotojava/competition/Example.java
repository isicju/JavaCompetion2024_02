package kotojava.competition;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static java.lang.Double.parseDouble;

@AllArgsConstructor
public class Example {

    //todo put your name and solution type
    private static String participantName = "Dmitrii";
    private static ParticipantExperience experience = ParticipantExperience.AFTER_JUNIOR;
    private static SolutionType solutionType = SolutionType.REGULAR;

    enum ParticipantExperience {
        BEFORE_JUNIOR, AFTER_JUNIOR
    }

    enum SolutionType {
        REGULAR, ADVANCED
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("please specify data.txt location with all records.");
        } else {
            File file = new File(args[0]);
            if (!file.exists()) {
                System.out.println("file: " + file.getAbsoluteFile() + " not found");
            } else {
                calculateStats(file);
            }
        }
    }

    //change implementation
    private static void calculateStats(File file) throws IOException {
        List<String> cityRecords = Files.readAllLines(file.toPath());
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

        printResults(minCity, maxCity, meanDistance);
    }

    private static void printResults(String minCity, String maxCity, double meanDistance) {
        System.out.println("min|max|mean|name|group");
        System.out.println(minCity + "|" + maxCity + "|" + meanDistance + "|" + participantName + "|" + experience + "|" + solutionType);
    }



    private static double distance(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

}
