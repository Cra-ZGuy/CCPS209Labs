package Lab0J;

public class P2J10 {
    public static Fraction matchingDissimilarity(boolean[] v1, boolean[] v2) {
        int matches = 0;

        for (int i = 0; i < v1.length; i++) {
            if (v1[i] != v2[i]) matches++;
        }

        return new Fraction(matches, v1.length);
    }

    public static Fraction jaccardDissimilarity(boolean[] v1, boolean[] v2) {
        int[] counts = calculateDissimilarityCounts(v1, v2);

        int n01 = counts[1];
        int n10 = counts[2];
        int n11 = counts[3];

        return new Fraction(n10 + n01, n11 + n10 + n01);
    }

    public static Fraction diceDissimilarity(boolean[] v1, boolean[] v2) {
        int[] counts = calculateDissimilarityCounts(v1, v2);

        int n01 = counts[1];
        int n10 = counts[2];
        int n11 = counts[3];

        return new Fraction(n10 + n01, 2 * n11 + n10 + n01);
    }

    public static Fraction rogersTanimonoDissimilarity(boolean[] v1, boolean[] v2) {
        int[] counts = calculateDissimilarityCounts(v1, v2);

        int n00 = counts[0];
        int n01 = counts[1];
        int n10 = counts[2];
        int n11 = counts[3];

        return new Fraction(2 * (n10 + n01), n11 + 2 * (n10 + n01) + n00);
    }

    public static Fraction russellRaoDissimilarity(boolean[] v1, boolean[] v2) {
        int[] counts = calculateDissimilarityCounts(v1, v2);

        int n00 = counts[0];
        int n01 = counts[1];
        int n10 = counts[2];

        return new Fraction(n10 + n01 + n00, v1.length);
    }

    public static Fraction sokalSneathDissimilarity(boolean[] v1, boolean[] v2) {
        int[] counts = calculateDissimilarityCounts(v1, v2);

        int n01 = counts[1];
        int n10 = counts[2];
        int n11 = counts[3];

        return new Fraction(2 * (n10 + n01), n11 + 2 * (n10 + n01));
    }

    private static int[] calculateDissimilarityCounts(boolean[] v1, boolean[] v2) {
        int[] counts = new int[4];

        for (int i = 0; i < v1.length; i++) {
            boolean a = v1[i];
            boolean b = v2[i];
            boolean equal = a == b;

            if (!a && equal) counts[0]++;
            else if (a && !equal) counts[1]++;
            else if (!a) counts[2]++;
            else counts[3]++;
        }

        return counts;
    }
}
