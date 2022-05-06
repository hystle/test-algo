package ch2;

/**
 * Algorithm 2.7
 * P324
 */
public class HeapSort extends AbstractSort {
    @Override
    public void sort(Comparable[] a) {
        int n = a.length;

        // 1. construct the heap structure. NOTE: where n/2 is the 1st parent node in
        // the heap (i.e., all nodes other than the bottom line of leaf nodes)
        for (int k = n / 2; k >= 1; k--) {
            sink(a, k, n);    // Example: in array of 15 items, sink() item at 7, 6, 5, .. , 1
        }

        // 2. now the largest item is at a[1], exchange it with the a[n], then repair [1, n-1] by sink()
        // next iteration get the new largest item at a[1], exchange it with a[n-1], then repair [1, n-2]
        // so the array is gradually sorted from the tail to head
        while (n > 1) {
            exch(a, 1, n--);
            sink(a, 1, n);
        }
    }

    private void sink(Comparable[] a, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(a, j, j + 1)) {
                j++;
            }
            if (!less(a, k, j)) {
                break;
            }
            exch(a, k, j);
            k = j;
        }
    }

    /*
    same as less() in abstract but starting index is 1
     */
    private boolean less(Comparable[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    /*
    same as exchange() in abstract but starting index is 1
     */
    private void exch(Comparable[] pq, int i, int j) {
        Comparable swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }
}
