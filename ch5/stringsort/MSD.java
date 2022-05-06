package ch5.stringsort;

import java.util.Arrays;

/**
 * TODO: infinity loop
 * To sort an array a[] of strings, sort them on their 1st character
 * using the LSD and the recursively sort the sub-arrays corresponding
 * the each first-character value
 */
public class MSD {
    private static int R = 256;
    private static final int CUTOFF = 15;
    private static String[] aux;

    public static void sort(String[] a) {
        int n = a.length;
        aux = new String[n];
        sort(a, 0, n - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo + CUTOFF) {
            sort(a, lo, hi, d);
            return;
        }
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d) + 2]++;
        }
        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }
        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];
        }

        for (int r = 0; r < R; r++) {
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }

    private static int charAt(String s, int d) {
        if (d < s.length()) {
            return s.charAt(d);
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        String[] s = {"4PGC938", "2IYE230", "2CI0720", "1ICK750"};
        MSD.sort(s);
        System.out.println(Arrays.toString(s));
    }
}
