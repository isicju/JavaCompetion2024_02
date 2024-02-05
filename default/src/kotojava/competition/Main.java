package kotojava.competition;

import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<Long, Long> memo = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("user: " + calculateFactorial(Long.parseLong(args[0])));
    }

    private static long calculateFactorial(long n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * calculateFactorial(n - 1);
        }
    }
}
