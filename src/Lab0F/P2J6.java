package Lab0F;

import java.util.*;

public class P2J6 {
    public static List<Integer> sumOfDistinctCubes(int n) {
        if (n == 0) return new ArrayList<>();

        int startingBase = (int) Math.cbrt(n);
        List<Integer> result = sumOfDistinctCubes(n, startingBase, new ArrayList<>());

        return result != null ? result : new ArrayList<>();
    }

    private static List<Integer> sumOfDistinctCubes(int target, int base, List<Integer> currentList) {
        if (target == 0) return new ArrayList<>(currentList);
        if (base <= 0 || target < 0) return null;

        long sumOfCubes = ((long) base * base * (base + 1) * (base + 1)) / 4;

        if (sumOfCubes < target) return null;

        int baseCubed = base * base * base;

        if (baseCubed <= target) {
            currentList.add(base);
            List<Integer> withBase = sumOfDistinctCubes(target - baseCubed, base - 1, currentList);

            if (withBase != null) return withBase;

            currentList.removeLast();
        }

        return sumOfDistinctCubes(target, base - 1, currentList);
    }

    public static List<String> forbiddenSubstrings(String alphabet, int n, List<String> tabu) {
        List<String> result = new ArrayList<>();

        forbiddenSubstrings(alphabet, n, tabu, "", result);

        return result;
    }

    private static void forbiddenSubstrings(String alphabet, int n, List<String> tabu, String soFar, List<String> result) {
        for (String tabuWord : tabu) {
            if (soFar.endsWith(tabuWord)) return;
        }

        if (soFar.length() == n) {
            result.add(soFar);
            return;
        }

        for (char letter : alphabet.toCharArray()) {
            forbiddenSubstrings(alphabet, n, tabu, soFar + letter, result);
        }
    }
}
