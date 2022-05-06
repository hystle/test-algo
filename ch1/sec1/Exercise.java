package ch1.sec1;

import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import java.util.Arrays;

public class Exercise {

    /**
     * 1.1.9
     */
    @Test
    public void testIntegerToBinary() {
        int n = 5;
        String s = "";
        for (int k = n; k > 0; k /= 2) {
            s = (k % 2) + s;
        }
        System.out.print(s);
    }

    /**
     * 1.1.13
     * transposition of a matrix
     */
    @Test
    public void testTranspose() {

        int[][] a = {{1, 2, 3}, {4, 5, 6}};  // quick way of initialize multi-dimensional array
        int row = a.length;  // row #
        int col = a[0].length;  // column #

        // original matrix
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("====");

        // new transposed matrix will be [col * row] instead of [row * col]
        // so the 2 for-loop order is swapped for correct final matrix size
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                // new entry is read from old matrix but index-swapped location
                System.out.print(a[j][i] + " ");
            }
            System.out.print("\n");
        }
    }

    /**
     * 1.1.14
     * returns the largest int not larger than the base-2 log of n
     */
    @Test
    public void testLg() {
        for (int i = 1; i < 35; i++) {
            System.out.printf("%2s: %s%n", i, lg(i));
        }
    }

    private int lg(int n) {
        if (n == 0) {
            throw new ArithmeticException();
        }
        if (n == 1) {
            return 0;
        }
        int c = 0;
        while (true) {
            n /= 2;
            if (n == 0) {
                break;
            }
            c++;
        }
        return c;
    }

    /**
     * 1.1.15
     * input: int[] a, int m
     * output: int[m] b, whose int[i] == # of appearance of i in b
     */
    @Test
    public void testNumberCount() {
        int[] a = {1, 2, 2, 3, 4};
        int m = 3;
        System.out.println(Arrays.toString(histogram(a, m)));
    }

    private int[] histogram(int[] a, int m) {
        int[] r = new int[m];
        for (int i = 0; i < a.length; i++) {
            if (i < m) {
                r[a[i]]++;
            } else {
                break;
            }
        }
        return r;
    }

    /**
     * 1.1.18 (1)
     * implement multiplication
     */
    @Test
    public void testMultiplication() {
        System.out.println(multiple(3, 11));
    }

    private int multiple(int a, int b) {
        if (b == 0) {
            return 0;
        }
        if (b % 2 == 0) {
            return multiple(a + a, b / 2);
        }
        return multiple(a + a, b / 2) + a;
    }

    /**
     * 1.1.18 (2)
     * implement production
     */
    @Test
    public void testPower() {
        System.out.println(power(3, 11));
    }

    private int power(int a, int b) {
        if (b == 0) {
            return 1;
        }
        if (b % 2 == 0) {
            return power(a * a, b / 2);
        }
        return power(a * a, b / 2) * a;
    }

    /**
     * 1.1.19
     * fibonacci study
     */
    @Test
    public void testFibonacci() {
        int l = 90;
        long[] a = new long[l];
        for (int n = 0; n < l; n++) {
//            StdOut.println(n + " " + fib1(n));
            StdOut.println(n + " " + fib2(n, a));
        }
    }

    /*
    (1) basic fibonacci: cannot compute to n=90
     */
    private long fib1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib1(n - 1) + fib1(n - 2);
    }

    /*
    improved fibonacci: instantly compute to n=90
    added an array to store calculated value
     */
    private long fib2(int n, long[] arr) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        // read value from array if known
        if (arr[n] != 0) {
            return arr[n];
        }
        // write known value to array
        arr[n - 1] = fib2(n - 1, arr);
        arr[n - 2] = fib2(n - 2, arr);

        return arr[n - 1] + arr[n - 2];
    }

    /**
     * 1.1.20
     * calculate ln(n!) by recursion
     */
    @Test
    public void testLnExponential() {
        StdOut.println(lnExp(4));
    }

    private double lnExp(int n) {
        if (n == 1) {
            return 0;
        }
        return lnExp(n - 1) + Math.log(n);
    }

    /**
     * 1.1.21
     */
    @Test
    public void testPrintf() {
        String name = "abc";
        int a = 2000;
        int b = 7;
        System.out.printf("%s %d %d %.3f", name, a, b, (double) a / b);

    }

    /**
     * 1.1.24
     */
    @Test
    public void testEuclidAlgorithm() {
        System.out.print(greatestCommonDivisor(11111111, 1234567));
    }

    private int greatestCommonDivisor(int p, int q) {
        if (q == 0) {
            return p;
        }
        int r = p % q;
        return greatestCommonDivisor(q, r);
    }

    /**
     * 1.1.27
     */
    @Test
    public void testBinomialDistribution() {
        int n = 6;
        int k = 4;
        double p = 0.25;

        // (6, 4, 0.25)
        // 6! / [4! * (6-2)!] * 0.25^4 * (1-0.25)^(6-4) = 032958984375
        System.out.println(binomial1(n, k, p));

        // for big number set like (50, 25, 0.25) binomial1 won't work
        double[][] arr = new double[n + 1][k + 1];
        System.out.println(binomial2(n, k, p, arr));
    }

    /**
     * @param n number of independent experiments
     * @param k number of successes
     * @param p probability of success on a single trial
     * @return binomial probability
     */
    private double binomial1(int n, int k, double p) {
        if ((n == 0) && (k == 0)) {
            return 1.0;
        }
        if ((n < 0) || (k < 0)) {
            return 0.0;
        }
        return (1 - p) * binomial1(n - 1, k, p) + p * binomial1(n - 1, k - 1, p);
    }

    private double binomial2(int n, int k, double p, double[][] arr) {
        if ((n == 0) && (k == 0)) {
            return 1.0;
        }
        if ((n < 0) || (k < 0)) {
            return 0.0;
        }

        // Return with stored value if known
        if (arr[n][k] != 0) {
            return arr[n][k];
        }

        double v1 = binomial2(n - 1, k, p, arr);
        double v2 = binomial2(n - 1, k - 1, p, arr);

        // Store known value (from 1 layer down)
        // Note:
        // 1. store the value only not the product (p*v) since the factor could be "p" or "1-p"
        // 2. array cannot have negative index. since if negative the return is 0 so just ignore those
        if ((n - 1) >= 0 && (k - 1) >= 0) {
            arr[n - 1][k] = v1;
            arr[n - 1][k - 1] = v2;
        }

        // calculate the product here
        return (1 - p) * v1 + p * v2;
    }

    /**
     * 1.1.28
     */
    @Test
    public void testRemoveDuplicatesFromSortedArray() {
        int[] a = {1, 2, 2, 2, 3, 5, 10};
        int i = 0, j = 1;
        while (j <= a.length - 1) {
            if (a[i] != a[j]) {
                i++;
                a[i] = a[j];
            }
            j++;
        }
        System.out.print(Arrays.toString(Arrays.copyOfRange(a, 0, i + 1)));
    }

    /**
     * 1.1.30
     * calculate the n-by-n matrix: if i and j are relatively prime then return 1, else 0
     * assume the matrix indices start from 1 not 0
     */
    @Test
    public void testRelativelyPrime() {
        int n = 4;
        int[][] a = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (greatestCommonDivisor(i + 1, j + 1) > 1) {
                    a[i][j] = 0;
                } else {
                    a[i][j] = 1;
                }
                System.out.print(a[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    /**
     * 1.1.35
     */
    @Test
    public void testDiceSimulation() {
        int SIDES = 6;
        int[] freq = new int[2 * SIDES + 1];
        for (int i = 1; i <= SIDES; i++) {
            for (int j = 1; j <= SIDES; j++) {
                freq[i + j]++;
            }
        }

        double[] prob = new double[2 * SIDES + 1];
        for (int k = 2; k <= 2 * SIDES; k++) {
            prob[k] = freq[k] / 36.0;
        }
        System.out.println(Arrays.toString(prob));
    }

}
