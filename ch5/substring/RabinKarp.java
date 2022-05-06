package ch5.substring;

import edu.princeton.cs.algs4.StdOut;

public class RabinKarp {
    private String pat;
    private long patHash;
    private int m;
    private long q;
    private int R = 256;
    private long RM;

    public RabinKarp(String pat) {
        this.pat = pat;
        m = pat.length();
        q = 997;
        RM = 1;
        for (int i = 1; i <= m - 1; i++) {
            RM = (R * RM) % q;
        }
        patHash = hash(pat, m);
    }

    private long hash(String key, int m) {
        long h = 0;
        for (int j = 0; j < m; j++) {
            h = (R * h + key.charAt(j)) % q;
        }
        return h;
    }

    private boolean check(int i) {
        return true;
    }

    public int search(String txt) {
        int n = txt.length();
        long txtHash = hash(txt, m);
        if (patHash == txtHash && check(0)) {
            return 0;
        }
        for (int i = m; i < n; i++) {
            txtHash = (txtHash + q - RM * txt.charAt(i - m) % q) % q;
            txtHash = (txtHash * R + txt.charAt(i)) % q;
            if (patHash == txtHash) {
                if (check(i - m + 1)) {
                    return i - m + 1;
                }
            }
        }
        return n;
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
        RabinKarp kmp = new RabinKarp(pat);
        StdOut.println("text   : " + txt);
        int offset = kmp.search(txt);
        StdOut.print("pattern: ");
        for (int i = 0; i < offset; i++) {
            StdOut.print(" ");
        }
        StdOut.println(pat);
    }

}
