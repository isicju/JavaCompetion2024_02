package kotojava.competition;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        performIntensiveOperation(Integer.valueOf(args[0]));
    }

    private static void performIntensiveOperation(int iterations) {
        for (int i = 0; i < iterations; i++) {
            double result = Math.sqrt(Math.pow(Math.random(), 2) + Math.pow(Math.random(), 2));
        }
    }
}
