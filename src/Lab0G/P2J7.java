package Lab0G;

import java.math.BigInteger;
import java.util.*;

public class P2J7 {
    public static <T> List<T> josephus(List<T> men, int k) {
        List<T> result = new ArrayList<>();
        LinkedList<T> circle = new LinkedList<>(men); // avoid modifying the original

        int index = 0;

        while (!circle.isEmpty()) {
            index = (index + k - 1) % circle.size();
            result.add(circle.remove(index));
        }

        return result;
    }

    public static int[] huntingtonHill(int[] population, int seats) {
        int stateCount = population.length;
        Fraction[] priorities = new Fraction[stateCount];

        class PriorityComparator implements Comparator<Integer> {
            public int compare(Integer n1, Integer n2) {
                // Prevent errors on queues size of 1
                if (priorities[n1] == null || priorities[n2] == null) return 0;

                return priorities[n2].compareTo(priorities[n1]);
            }
        }

        PriorityQueue<Integer> states = new PriorityQueue<>(stateCount, new PriorityComparator());
        BigInteger[] populationSquared = new BigInteger[stateCount];
        int[] stateSeats = new int[stateCount];

        for (int i = 0; i < stateCount; i++) {
            int statePopulation = population[i];

            BigInteger numerator = BigInteger.valueOf(statePopulation);
            numerator = numerator.multiply(numerator);

            populationSquared[i] = numerator;

            BigInteger denominator = BigInteger.valueOf(1);
            denominator = denominator.multiply(denominator.add(BigInteger.ONE));

            priorities[i] = new Fraction(numerator, denominator);
            stateSeats[i] = 1;
            states.offer(i);
        }

        seats -= stateCount;

        while (seats > 0) {
            Integer nextState = states.poll();

            if (nextState == null) break;

            int stateSeatCount = ++stateSeats[nextState];

            BigInteger denominator = BigInteger.valueOf(stateSeatCount);
            denominator = denominator.multiply(denominator.add(BigInteger.ONE));

            priorities[nextState] = new Fraction(populationSquared[nextState], denominator);

            states.offer(nextState);
            seats--;
        }

        return stateSeats;
    }
}
