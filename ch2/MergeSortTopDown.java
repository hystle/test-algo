package ch2;

/**
 * Algorithm 2.4
 * P273
 */
public class MergeSortTopDown extends AbstractMergeSort {

    @Override
    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        topDownSort(a, 0, a.length - 1);
    }

    /*
    1. recursive
    2. divide and conquer
     */
    private void topDownSort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;  // resume to where the program is hanged due to recursion
        }
        int mid = lo + (hi - lo) / 2;
        topDownSort(a, lo, mid);      // recursively get smaller piece of left half of array
        topDownSort(a, mid + 1, hi);  // recursively get smaller piece of right half of array
        merge(a, lo, mid, hi);        // actual sorting here by merging 2 sub-array into 1 with correct order
    }
}
