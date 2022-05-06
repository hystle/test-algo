package ch2;

abstract class AbstractMergeSort extends AbstractSort {

    static Comparable[] aux;

    /*
    assume the array a has 2 sorted halves.
    merge them into one single sorted array with another helper array
    */
    void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;       // item on the left half of the array
        int j = mid + 1;  // item on the right half of the array

        // copy the array to a helper array
        for (int k = 0; k <= hi; k++) {
            aux[k] = a[k];
        }

        // loop until the output array is filled
        for (int k = lo; k <= hi; k++) {
            // special case left exhausted: take from right
            if (i > mid) {
                a[k] = aux[j++];
            }
            // special case right exhausted: take from left
            else if (j > hi) {
                a[k] = aux[i++];
            }
            // general case current right is less than current left: take from right
            else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            }
            // general case current right is greater than current left: take from left
            else {
                a[k] = aux[i++];
            }
        }
    }
}
