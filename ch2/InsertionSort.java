package ch2;

/**
 * Algorithm 2.2
 * P251
 */
public class InsertionSort extends AbstractSort {

    @Override
    public void sort(Comparable[] a) {
        int n = a.length;
        // start from 1, so that the a[j-1] in the inner loop below will cover the 1st item
        for (int i = 1; i < n; i++) {
            // compare a[i] with a[i-1] and swap if needed iteratively
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exchange(a, j, j - 1);
            }
        }
    }
}
