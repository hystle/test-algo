package ch1.sec2;

import org.junit.Test;

public class Exercise {
    /**
     * 1.2.7
     * abcde
     * -> cde -> de c -> e d c
     * -> ab  -> b a            -> edcba
     */
    @Test
    public void testReverseString() {
        // edcba
        System.out.println(reverse("abcde"));
    }

    private String reverse(String s) {
        int n = s.length();
        if (n <= 1) {
            return s;
        }
        String a = s.substring(0, n / 2);
        String b = s.substring(n / 2, n);
        return reverse(b) + reverse(a);
    }
}
