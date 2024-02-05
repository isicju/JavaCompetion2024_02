package kotojava.competition;

import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<Long, Long> memo = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("user: " + fibonacci(120));
    }

    public static long fibonacci(long n) {
        if (n <= 1) {
            return n;
        } else {
            if (memo.containsKey(n)) {
                return memo.get(n);
            } else {
                long result = fibonacci(n - 1) + fibonacci(n - 2);
                memo.put(n, result);
                return result;
            }
        }
    }
}
