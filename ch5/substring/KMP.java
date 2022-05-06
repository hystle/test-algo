package ch5.substring;

import edu.princeton.cs.algs4.StdOut;

/**
 * dfa = deterministic finite-state automation
 */
public class KMP {
    private String pat;
    private int[][] dfa;

    // build DFA from the input pattern
    public KMP(String pat) {
        this.pat = pat;
        int m = pat.length();
        int R = 256;
        dfa = new int[R][m];
        dfa[pat.charAt(0)][0] = 1;

        int x = 0;    // restart state index
        for (int j = 1; j < m; j++) {
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][x];        // for the mismatch cases: copy from previous restart state
            }
            dfa[pat.charAt(j)][j] = j + 1;    // for the match case: j+1
            x = dfa[pat.charAt(j)][x];        // update restart state
        }
    }

    public int search(String txt) {
        // simulate operation of dfa on text
        int i, j, n = txt.length();
        int m = pat.length();
        for (i = 0, j = 0; i < n && j < m; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == m) {
            return i - m;  // found: hit end of pattern
        } else {
            return n;      // not found: hit end of text
        }
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
        KMP kmp = new KMP(pat);
        StdOut.println("text   : " + txt);
        int offset = kmp.search(txt);
        StdOut.print("pattern: ");
        for (int i = 0; i < offset; i++) {
            StdOut.print(" ");
        }
        StdOut.println(pat);
    }
}
