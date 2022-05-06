package ch2;

/**
 * Algorithm 2.5
 * P299
 */
public class QuickSort3Way extends AbstractSort {

    @Override
    public void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    /*
    far more efficient than the standard QuickSort implementation for arrays
    with large numbers of duplicate keys
    */
    private void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int lt = lo;
        int i = lo + 1;
        int gt = hi;

        // pick pivot number
        Comparable v = a[lo];

        // swap item less than pivot to left & greater than pivot to right
        //
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                exchange(a, lt++, i++);  // exchange a[lt] with a[i], lt++, i++
            } else if (cmp > 0) {
                exchange(a, i, gt--);    // exchange a[i], with a[gt], gt--
            } else {
                i++;                     // magic code to do nothing on duplicate items
            }
            sort(a, lo, lt - 1);
            sort(a, gt + 1, hi);
        }
    }
}
