package Lab3;

import java.util.ArrayList;
import java.util.Arrays;

public class Polynomial implements Comparable<Polynomial> {
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

    @Override public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Polynomial otherPolynomial = (Polynomial) other;

        return this.compareTo(otherPolynomial) == 0;
    }

    @Override public int hashCode() {
        return Arrays.hashCode(this.coefficients.toArray());
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

    public Polynomial add(Polynomial other) {
        int maxDegree = Math.max(this.getDegree(), other.getDegree());
        int[] sum = new int[maxDegree + 1];

        for (int i = 0; i <= maxDegree; i++) {
            sum[i] = this.getCoefficient(i) + other.getCoefficient(i);
        }

        return new Polynomial(sum);
    }

    public Polynomial multiply(Polynomial other) {
        int resultDegree = this.getDegree() + other.getDegree();
        int[] product = new int[resultDegree + 1];

        for (int i = 0; i <= this.getDegree(); i++) {
            for (int j = 0; j <= other.getDegree(); j++) {
                product[i + j] += this.getCoefficient(i) * other.getCoefficient(j);
            }
        }

        return new Polynomial(product);
    }

    public int compareTo(Polynomial other) {
        int thisDegree = this.getDegree();
        int otherDegree = other.getDegree();

        if (thisDegree > otherDegree) {
            return 1;
        } else if (otherDegree > thisDegree) {
            return -1;
        }

        for (int i = thisDegree; i >= 0; i--) {
            int thisCoefficient = this.getCoefficient(i);
            int otherCoefficient = other.getCoefficient(i);

            if (thisCoefficient > otherCoefficient) {
                return 1;
            } else if (otherCoefficient > thisCoefficient) {
                return -1;
            }
        }

        return 0;
    }
}
