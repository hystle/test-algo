package ch2;

/**
 * Algorithm 2.4
 * P278
 */
public class MergeSortBottomUp extends AbstractMergeSort {

    @Override
    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        bottomUpSort(a);
    }

    /*
    1. non-recursive
    2. multiple passes over the whole array, doing len-by-len merge() operations
     */
    private void bottomUpSort(Comparable[] a) {
        int n = a.length;
        // sub-array length of each pass is power of 2 == 2, 4, 8 ...
        for (int len = 1; len < n; len = len + len) {
            // loop through the list with the len defined above
            for (int lo = 0; lo < n - len; lo += len + len) {
                // put sub-array into right order
                merge(a, lo, lo + len - 1, Math.min(lo + len + len - 1, n - 1));
            }
        }
    }
}
