package Lab0E;

import java.math.BigInteger;
import java.util.*;

public class P2J5 {
    public static List<BigInteger> fibonacciSum(BigInteger n) {
        Deque<BigInteger> fibs = new ArrayDeque<>();
        BigInteger n2 = BigInteger.ONE;
        BigInteger n1 = BigInteger.TWO;
        fibs.add(n2);
        fibs.add(n1);

        // Precalculate fibonacci numbers
        while (fibs.getLast().compareTo(n) < 1) {
            BigInteger fib = n1.add(n2);

            fibs.add(fib);

            n2 = n1;
            n1 = fib;
        }

        Iterator<BigInteger> it = fibs.descendingIterator();
        List<BigInteger> sumList = new ArrayList<>();
        BigInteger sum = BigInteger.ZERO;

        // Greedy algorithm, add fibonacci number to sum
        // if it does not exceed target number
        while (it.hasNext() && sum.compareTo(n) < 0) {
            BigInteger fib = it.next();
            BigInteger currentSum = sum.add(fib);

            if (currentSum.compareTo(n) > 0) continue;

            sum = currentSum;
            sumList.add(fib);
        }

        return sumList;
    }

    public static BigInteger sevenZero(int n) {
        boolean requiresOnly7s = n % 2 != 0 && n % 5 != 0;

        BigInteger bigN = BigInteger.valueOf(n);

        if (requiresOnly7s) {
            BigInteger current = BigInteger.ZERO;

            for (int i = 1; /* Don't stop */; i++) {
                current = current.multiply(BigInteger.TEN).add(BigInteger.valueOf(7));

                if (!current.mod(bigN).equals(BigInteger.ZERO)) continue;

                return current;
            }
        }

        Queue<String> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer("7");

        while (!queue.isEmpty()) {
            String current = queue.poll();
            BigInteger number = new BigInteger(current);
            int remainder = number.mod(bigN).intValue();

            if (remainder == 0) return number;
            if (visited.contains(remainder)) continue;

            visited.add(remainder);

            if (current.charAt(current.length() - 1) != '0') {
                queue.offer(current + "7");
                queue.offer(current + "0");
            } else {
                queue.offer(current + "0");
            }
        }

        return BigInteger.ZERO; // Fallback
    }
}
