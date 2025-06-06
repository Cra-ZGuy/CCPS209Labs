package Lab0I;

import java.util.Arrays;
import java.util.BitSet;

public class P2J9 {
    public static boolean[] sumOfTwoDistinctSquares(int n) {
        int high = (int) Math.ceil(Math.sqrt(n));
        boolean[] result = new boolean[n];
        BitSet distinctSquares = new BitSet();

        for (int a = 1; a < high; a++) {
            for (int b = a + 1; b <= high; b++) {
                distinctSquares.set(a * a + b * b);
            }
        }

        for (int i = 0; i < n; i++) {
            result[i] = distinctSquares.get(i);
        }

        return result;
    }

    public static boolean[] subtractSquare(int n) {
        boolean[] hotStates = new boolean[n + 1];

        hotStates[0] = false;

        for (int i = 1; i <= n; i++) {
            hotStates[i] = false;

            for (int j = 1; j * j <= i; j++) {
                // Checks for cold state
                if (hotStates[i - j * j]) continue;

                hotStates[i] = true;
                break;
            }
        }

        return hotStates;
    }
}
