package ch2;

/**
 * Abstract class of all sorting algorithms
 * See P342 for sorting summary
 */
public abstract class AbstractSort {

    /*
    to be completed by child classes
     */
    public abstract void sort(Comparable[] a);

    /*
    true: v < w
    false: v >= w
     */
    static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /*
    swap a[i] with a[j] in a[]
     */
    static void exchange(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /*
    print a[]
     */
    static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /*
    check if a[] has been sorted
     */
    static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }
}
