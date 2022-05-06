package ch5.substring;

import edu.princeton.cs.algs4.StdOut;

public class BoyerMoore {
    private int[] right;
    private String pat;

    public BoyerMoore(String pat) {
        this.pat = pat;
        int m = pat.length();
        int R = 256;

        // initialize right[]: the rightmost occurrence in the pattern of each character in alphabet
        right = new int[R];
        for (int c = 0; c < R; c++) {
            right[c] = -1;
        }
        // ex. for AACAA: after the for loop: "A" -> 4, "C" -> 2, others -> -1
        // 0=-1, 1=-1, ... 65=4, 66=-1, 67=2, 68=-1, ...
        for (int j = 0; j < m; j++) {
            right[pat.charAt(j)] = j;
        }
    }

    public int search(String txt) {
        int n = txt.length();
        int m = pat.length();
        int skip;

        for (int i = 0; i <= n - m; i += skip) {
            // does the pattern match the text at position i ?
            // if txt.charAt(i+j) == pat.charAt(j) for all j from m-1 to 0, then match found
            skip = 0;
            for (int j = m - 1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    skip = j - right[txt.charAt(i + j)];
                    if (skip < 1) {
                        skip = 1;
                    }
                    break;
                }
            }
            if (skip == 0) {
                return i;        // found
            }
        }
        return n;                // not found
    }

    /**
     * AACAA AABRAACADABRAACAADABRA
     * <p>
     * text   : AABRAACADABRAACAADABRA
     * pattern:             AACAA
     *
     * @param args
     */
    public static void main(String[] args) {
        String pat = args[0];
        String txt = args[1];
        BoyerMoore kmp = new BoyerMoore(pat);
        StdOut.println("text   : " + txt);
        int offset = kmp.search(txt);
        StdOut.print("pattern: ");
        for (int i = 0; i < offset; i++) {
            StdOut.print(" ");
        }
        StdOut.println(pat);
    }
}
