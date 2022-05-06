package ch2;

/**
 * Algorithm 2.3
 * P259
 * Enhanced version of insertion sort by allowing exchange of elements that are far apart
 */
public class ShellSort extends AbstractSort {

    @Override
    public void sort(Comparable[] a) {
        int n = a.length;
        int h = 1;

        // calculate the "gap" based on array length ~= 1/3
        while (h < n / 3) {
            h = 3 * h + 1;  // 1, 4, 13, 40, 121, 364, 1093 ...
        }

        // multiple h-sort till h>=1
        // Example: an array with 16 elements
        // 13-sort: sort 0-13 -> 1-14 -> 2-15 .. till end of array. so all subsets with gap 13 is sorted
        // 4-sort: sort 0-4 -> 1-5 -> 2-6, 3-7, 0-4-8, 1-5-9, 2-6-10.. till end of array. so all subsets with gap 4 is sorted
        // 1-sort: sort 0-1 -> 1-2 -> 2-3... still end of array. so all subsets with gap 1 is sorted (same as insertion sort)
        while (h >= 1) {
            // almost identity code to insertion sort!!!
            //
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exchange(a, j, j - h);
                }
            }
            // move on to next gap value
            h = h / 3;
        }
    }
}
