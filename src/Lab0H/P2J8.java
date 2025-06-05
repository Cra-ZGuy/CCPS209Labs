package Lab0H;

import java.math.BigInteger;

public class P2J8 {
    public static void hittingIntegerPowers(int a, int b, int t, int[] out) {
        if (a == b) {
            out[0] = 1;
            out[1] = 1;
            return;
        }

        BigInteger bigA = BigInteger.valueOf(a);
        BigInteger bigB = BigInteger.valueOf(b);
        BigInteger bigT = BigInteger.valueOf(t);
        BigInteger powA = bigA;
        BigInteger powB = bigB;

        int pa = 1;
        int pb = 1;

        while (true) {
            BigInteger diff = powA.subtract(powB).abs();
            BigInteger min = (powA.compareTo(powB) < 0) ? powA : powB;

            if (diff.multiply(bigT).compareTo(min) <= 0) {
                out[0] = pa;
                out[1] = pb;
                return;
            }

            if (powA.compareTo(powB) < 0) {
                powA = powA.multiply(bigA);
                ++pa;
            } else {
                powB = powB.multiply(bigB);
                ++pb;
            }
        }
    }

    public static BigInteger nearestPolygonalNumber(BigInteger n, int s) {
        if (n.compareTo(BigInteger.ONE) <= 0) return BigInteger.ONE;

        BigInteger two = BigInteger.valueOf(2);
        BigInteger s2 = BigInteger.valueOf(s - 2);
        BigInteger s4 = BigInteger.valueOf(s - 4);

        java.util.function.Function<BigInteger, BigInteger> P = i -> i.multiply(i).multiply(s2).subtract(i.multiply(s4)).divide(two);

        BigInteger low = BigInteger.ONE;
        BigInteger high = BigInteger.ONE;
        BigInteger highVal = P.apply(high);

        while (highVal.compareTo(n) < 0) {
            low = high;
            high = high.multiply(BigInteger.TEN);
            highVal = P.apply(high);
        }

        while (high.subtract(low).compareTo(BigInteger.ONE) > 0) {
            BigInteger mid = low.add(high).shiftRight(1);
            BigInteger midVal = P.apply(mid);

            if (midVal.compareTo(n) < 0) {
                low = mid;
            } else {
                high = mid;
                highVal = midVal;
            }
        }

        BigInteger lowVal = P.apply(low);

        return n.subtract(lowVal).abs().compareTo(highVal.subtract(n).abs()) <= 0 ? lowVal : highVal;
    }
}
