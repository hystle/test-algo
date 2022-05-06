package ch5.stringsort;

import java.util.Arrays;

/**
 * LSD with 1 digit
 */
public class BasicLSD {

    public static void sort(String[] a) {
        // R should be big enough to hold charAt() of any character of the input String[]
        // if the possible input range is A(65)-E(70), the R should be at least 70
        int R = 70;
        int n = a.length;
        String[] aux = new String[n];
        int[] count = new int[R + 1];

        for (int i = 0; i < n; i++) {
            count[a[i].charAt(0) + 1]++;
        }

        for (int r = 0; r < R; r++) {
            count[r + 1] += count[r];
        }

        for (int i = 0; i < n; i++) {
            aux[count[a[i].charAt(0)]++] = a[i];
        }

        for (int i = 0; i < n; i++) {
            a[i] = aux[i];
        }
    }

    public static void main(String[] args) {
        String[] s = {"C", "E", "D", "A", "B"};
        BasicLSD.sort(s);
        System.out.println(Arrays.toString(s));
    }
}
