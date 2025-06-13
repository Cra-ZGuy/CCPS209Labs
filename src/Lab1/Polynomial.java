package Lab1;

import java.util.ArrayList;

public class Polynomial {
    private final ArrayList<Integer> coefficients = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int size = this.coefficients.size();

        for (int i = size - 1; i >= 0; i--) {
            int coefficient = this.coefficients.get(i);

            if (coefficient != 0) {
                sb.append(Math.abs(this.coefficients.get(i)));
            }

            if (i > 0) {
                if (i == 1) {
                    sb.append("x");
                } else {
                    sb.append("x^");
                    sb.append(i);
                }

                int nextCoefficient = this.coefficients.get(i - 1);

                if (nextCoefficient >= 0) sb.append(" + ");
                else sb.append(" - ");
            }
        }

        return sb.toString();
    }

    public Polynomial(int[] coefficients) {
        int zeroCount = 0;

        for (int coefficient : coefficients) {
            if (coefficient == 0) {
                zeroCount++;
            } else {
                zeroCount = 0;
            }

            this.coefficients.add(coefficient);
        }

        for (int i = zeroCount; i > 0; i--) {
            this.coefficients.removeLast();
        }
    }

    public int getDegree() {
        return Math.max(this.coefficients.size() - 1, 0);
    }

    public int getCoefficient(int k) {
        if (k < 0 || k >= this.coefficients.size()) {
            return 0;
        }

        return this.coefficients.get(k);
    }

    public long evaluate(int x) {
        long result = 0;

        for (int i = 0; i < this.coefficients.size(); i++) {
            result += (long) (this.coefficients.get(i) * Math.pow(x, i));
        }

        return result;
    }
}
