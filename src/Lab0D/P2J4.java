package Lab0D;

import java.math.BigInteger;
import java.util.*;

public class P2J4 {
    public static List<Integer> runningMedianOfThree(List<Integer> items) {
        int size = items.size();

        if (size < 3) return new ArrayList<>(items);

        List<Integer> medianList = new ArrayList<>();

        medianList.add(items.get(0));
        medianList.add(items.get(1));

        for (int i = 2; i < size; i++) {
            int first = items.get(i - 2);
            int middle = items.get(i - 1);
            int last = items.get(i);

            if (first > middle) {
                int temp = first;
                first = middle;
                middle = temp;
            }

            if (first > last) {
                int temp = first;
                first = last;
                last = temp;
            }

            if (middle > last) {
                int temp = middle;
                middle = last;
                last = temp;
            }

            medianList.add(middle);
        }

        return medianList;
    }

    public static int firstMissingPositive(List<Integer> items) {
        Set<Integer> nums = new HashSet<>(items);

        int size = items.size() + 1;

        for (int i = 1; i < size; i++) {
            if (!nums.contains(i)) return i;
        }

        return size;
    }

    public static void sortByElementFrequency(List<Integer> items) {
        Map<Integer, Integer> numMap = new HashMap<>();

        for (int item : items) {
            Integer num = numMap.get(item);

            if (num == null) {
                numMap.put(item, 1);
                continue;
            }

            numMap.put(item, num + 1);
        }

        class FrequencyComparator implements Comparator<Integer> {
            public int compare(Integer n1, Integer n2) {
                int compare = Integer.compare(numMap.get(n2), numMap.get(n1));

                return compare == 0 ? Integer.compare(n1, n2) : compare;
            }
        }

        Collections.sort(items, new FrequencyComparator());
    }

    public static List<Integer> factorFactorial(int n) {
        if (n < 2) return new ArrayList<>();

        class Prime {
            public static boolean isPrime(int number) {
                if (number <= 1) return false;
                if (number == 2) return true;
                if (number % 2 == 0) return false;

                for (int i = 3; i <= Math.sqrt(number); i += 2) {
                    if (number % i == 0) return false;
                }

                return true;
            }

            public static List<Integer> getPrimesUpTo(int n) {
                List<Integer> primes = new ArrayList<>();
                for (int i = 2; i <= n; i++) {
                    if (isPrime(i)) primes.add(i);
                }
                return primes;
            }
        }

        List<Integer> primes = Prime.getPrimesUpTo(n);
        List<Integer> factors = new ArrayList<>();

        for (int prime : primes) {
            int power = 0;
            int p = prime;

            while (p <= n) {
                power += n / p;

                if (p > n / prime) break; // Prevent overflow

                p *= prime;
            }

            for (int i = 0; i < power; i++) {
                factors.add(prime);
            }
        }

        return factors;
    }
}
