package ch5.substring;

public class BrutalForce {
    public static int search1(String pat, String txt) {
        int m = pat.length();
        int n = txt.length();
        // i points to text
        // n - m: no need to examine if fewer characters less than pattern
        for (int i = 0; i < n - m; i++) {
            // j points to pattern
            // j only increment if the ith character matching
            int j;
            for (j = 0; j < m; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }
            // check if j == pattern length
            if (j == m) {
                return i;  // found: i not moved when j moving and checking
            }
        }
        return n;  // not found
    }

    public static int search2(String pat, String txt) {
        int j, m = pat.length();
        int i, n = txt.length();
        for (i = 0, j = 0; i < n && j < m; i++) {
            if (txt.charAt(i) == pat.charAt(j)) {
                j++;
            } else {      // 1 character not match
                i -= j;   // move back pointer i to the end of the matched character in text
                j = 0;    // move back pointer j to 0 for a new lookup
            }
        }
        // since i points to the end of the matched characters so
        // (i - length of pattern) is the location when found substring
        if (j == m) {
            return i - m;
        } else {
            return n;
        }
    }

    public static void main(String[] args) {
        String pattern = "abr";
        String text = "abacadabrac";
        System.out.println(text.substring(search1(pattern, text)));
        System.out.println(text.substring(search2(pattern, text)));
    }
}
