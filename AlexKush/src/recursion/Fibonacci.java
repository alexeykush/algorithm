package recursion;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
    private static final Map<Integer, Integer> memo = new HashMap<>();

    private static boolean has(int number) {
        return memo.containsKey(number);
    }

    private static int get(int pos) {
        return memo.get(pos);
    }

    private static void put(int pos, int value) {
        memo.put(pos, value);
    }

    public static int fib1(int n) {
        if (n <= 2) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    public static int fib(int n) {
        int nth;
        if (has(n)) {
            nth = get(n);
        } else if (n <= 2) {
            nth = 1;
        } else {
            nth = fib(n - 1) + fib(n - 2);
        }
        put(n, nth);
        return nth;
    }

    public static void main(String[] args) {
        int number = 100;
        int fib10th = fib(number);
        System.out.println(fib10th);
        System.out.println(memo);
    }
}