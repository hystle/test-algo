package ch1.sec1;

/**
 * Array must be sorted before binary search
 */
public class BinarySearch {

    /**
     * non-recursive
     */
    public static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 1.1.22
     * recursive and print the depth by indents
     */
    public static int indexOfRecursive(int[] a, int key, int lo, int hi, int depth) {
        String format;
        if (depth == 0) {
            format = "%s %s%n";
            System.out.printf(format, lo, hi);
        } else {
            format = "%" + depth + "s%s %s%n";
            System.out.printf(format, "", lo, hi);
        }

        if (lo > hi) {
            return -1;
        }
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) {
            depth++;
            return indexOfRecursive(a, key, lo, mid - 1, depth);
        } else if (key > a[mid]) {
            depth++;
            return indexOfRecursive(a, key, mid + 1, hi, depth);
        } else {
            return mid;
        }
    }

    /**
     * moving the lo pointer to the right if possible
     * eventually lo points to an element that is less than or equal to the key
     *
     * @param a   a sorted array without duplicate keys
     * @param key int value
     * @return number of elements that are smaller than the key value
     */
    public static int rank(int[] a, int key) {
        // Add error checking if key < a[0]
        if (key < a[0])
            return -1;

        int lo = 0;
        int hi = a.length;
        int mid;

        while (hi - lo > 1) {
            mid = lo + (hi - lo) / 2;

            // note here we have "<="
            if (a[mid] <= key)
                lo = mid;
            else
                hi = mid;
        }

        // lo pointer is the rank (given it isn't set by mid+1 but mid)
        return lo;
    }

    /**
     * given a is an array with duplicate keys
     *
     * @param a
     * @param key
     * @return number of elements that are equal to the key value
     */
    public static int count(int[] a, int key) {
        int size = a.length;
        int left = getLeft(a, key, 0, size);
        int right = getRight(a, key, 0, size);

        // if the key is not in array
        if (a[left] != key && a[right] != key) {
            return 0;
        }
        return right - left + 1;
    }

    public static int getRight(int[] a, int key, int lo, int hi) {
        int mid;
        while (hi - lo > 1) {
            mid = lo + (hi - lo) / 2;
            if (a[mid] <= key) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public static int getLeft(int[] a, int key, int lo, int hi) {
        int mid;
        while (hi - lo > 1) {
            mid = lo + (hi - lo) / 2;
            if (a[mid] >= key) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return hi;
    }

    /**
     * given a sorted array gets rotated at certain point, find the min key
     * Ex. 6, 7, 8, 9, 1, 2, 3 -> min is 1
     *
     * @param a
     * @return
     */
    public static int findMinInRotatedArray(int a[]) {
        int lo = 0;
        int hi = a.length - 1;
        int mid;

        if (a[lo] <= a[hi]) {
            return lo;
        }

        while (lo <= hi) {
            if (lo == hi) {
                return lo;
            }
            mid = lo + (hi - lo) / 2;

            if (a[mid] < a[hi]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }

    /**
     * identify integers in tinyT.txt that is not in tinyW.txt
     * <p>
     * java BinarySearch tinyW.txt < tinyT.txt
     * <p>
     * cd /Users/ziczhou/coursera/algorithm/out/production/algorithm
     * java -classpath /Users/ziczhou/coursera/algorithm/out/production/algorithm:/Users/ziczhou/coursera/algs4.jar ch1.sec1.BinarySearch tinyW.txt < /Users/ziczhou/coursera/algorithm/src/tinyT.txt
     * 50
     * 99
     * 13
     *
     * @param args
     */
    public static void main(String[] args) {
//        In in = new In(args[0]);
//        int[] whiteList = in.readAllInts();
//        Arrays.sort(whiteList);
//
//        while (!StdIn.isEmpty()) {
//            int key = StdIn.readInt();
//            System.out.print("search: " + key + " in: " + Arrays.toString(whiteList) + "\n");
//            // print those not in allowed list
////            if (indexOf(whiteList, key) == -1) {
//            if (indexOfRecursive(whiteList, key, 0, whiteList.length - 1, 0) == -1) {
//                StdOut.println("miss found: " + key);
//            }
//            System.out.println("----");
//        }
//        System.out.println("====");
//        int key = 40;
//        System.out.println(Arrays.toString(whiteList) + " " + key);
//        int[] a = {1, 2, 2, 2, 3, 5, 10};
//        System.out.println(count(a, 5));
        int[] a = {6, 7, 8, 9, 10, 1, 2, 3, 4};
        System.out.println(findMinInRotatedArray(a));
    }
}
