package Lab0B;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

public class P2J2 {
    public static String removeDuplicates(String text) {
        if (text.length() < 2) return text;

        StringBuilder sb = new StringBuilder();

        char c = text.charAt(0);

        sb.append(c);

        for (int i = 1; i < text.length(); i++) {
            c = text.charAt(i);

            if (c == text.charAt(i - 1)) continue;

            sb.append(c);
        }

        return sb.toString();
    }

    public static String uniqueCharacters(String text) {
        Set<Character> uniqueChars = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (uniqueChars.contains(c)) continue;

            uniqueChars.add(c);
            sb.append(c);
        }

        return sb.toString();
    }

    public static int countSafeSquaresRooks(int n, boolean[][] rooks) {
        boolean[] rowsWithRooks = new boolean[n];
        boolean[] colsWithRooks = new boolean[n];

        int freeRows = n;
        int freeCols = n;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean hasRook = rooks[i][j];

                // Only decrement rows only if singular rook in row.
                if (!rowsWithRooks[i] && hasRook) {
                    freeRows--;
                }

                // Only decrement cols only if singular rook in col.
                if (!colsWithRooks[j] && hasRook) {
                    freeCols--;
                }

                // Only update if rook is present.
                rowsWithRooks[i] |= hasRook;
                colsWithRooks[j] |= hasRook;
            }
        }

        return freeRows * freeCols;
    }

    public static int recaman(int n) {
        int recaman = 1;

        BitSet bitVec = new BitSet();
        bitVec.set(1); // Assign default value

        for (int i = 2; i <= n; i++) {
            int calc = recaman - i;

            if (calc <= 0 || bitVec.get(calc)) {
                calc = recaman + i;
            }

            if (calc > 0 && !bitVec.get(calc)) {
                bitVec.set(calc);
            }

            recaman = calc;
        }

        return recaman;
    }
}
