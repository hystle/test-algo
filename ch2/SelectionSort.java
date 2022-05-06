package ch2;

/**
 * Algorithm 2.1
 * P249
 */
public class SelectionSort extends AbstractSort {
    @Override
    public void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            // assume the current i is the minimum in each iteration
            int min = i;
            // go through the array [i+1, end]
            for (int j = i + 1; j < n; j++) {
                // update the position of the minimum
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            // after the loop, put minimum to i -> meaning the ith element is in place
            exchange(a, i, min);
        }
    }
}
