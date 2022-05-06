package ch2;

/**
 * Algorithm 2.5
 * P289
 */
public class QuickSort extends AbstractSort {

    /*
    arrange the array that when sub-array is sorted, the whole array is sorted
     */
    @Override
    public void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);  // main function call of partitioning
        sort(a, lo, j - 1);            // recursively partitioning left
        sort(a, j + 1, hi);            // recursively partitioning right
    }

    /*
    Partitioning:
    1. a[j] is at the final position
    2. all entries left of j is less than a[j]
    3. all entries right of j is greater than a[j]
     */
    private int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        // 1. pick the pivot item (shuffle the array in practical to avoid a bad pivot)
        Comparable v = a[lo];
        while (true) {
            // 2. scan from left end
            // pause when a[i] item is greater than partitioning item
            while (less(a[++i], v)) {
                // done scanning until left pointer reaches the end of array
                if (i == hi) {
                    break;
                }
            }
            // 3. scan from right end
            // pause when a[j] item is less than partitioning item
            while (less(v, a[--j])) {
                // done scanning until right pointer reaches the end of array
                if (j == lo) {
                    break;
                }
            }
            // 5. done exchange if 2 pointers met
            if (i >= j) {
                break;
            }
            // 4. exchange two misplaced items a[i] - a[j]
            exchange(a, i, j);

            // then resume 2 scans above
        }
        // 6. move the pivot item to its final position after all exchanges
        exchange(a, lo, j);
        return j;
    }
}
