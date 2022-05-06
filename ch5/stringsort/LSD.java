package ch5.stringsort;

import java.util.Arrays;

public class LSD {
    /**
     * Sort the String w times from the back
     *
     * @param a Fixed-length String array to be sorted
     * @param w Number of characters in each String
     */
    public static void sort(String[] a, int w) {
        int n = a.length;
        int R = 256;    // assume extended_ASCII
        String[] aux = new String[n];

        for (int d = w - 1; d >= 0; d--) {

            int[] count = new int[R + 1];

            // compute frequency counts
            for (int i = 0; i < n; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            // transform counts to indices
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }
            // distribute a[i] to aux[]
            for (int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            // copy back from aux[] to a[]
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }

    /**
     * run without argument
     * <p>
     * [1ICK750, 2CI0720, 2IYE230, 4PGC938]
     *
     * @param args
     */
    public static void main(String[] args) {
        String[] s = {"4PGC938", "2IYE230", "2CI0720", "1ICK750"};
        LSD.sort(s, 7);
        System.out.println(Arrays.toString(s));
    }
}
