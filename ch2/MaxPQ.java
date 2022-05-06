package ch2;

/**
 * Algorithm 2.6
 * P318
 */
public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;

    // track priority size; start from 1
    private int n = 0;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    // enqueue and heap sort
    public void insert(Key v) {
        pq[++n] = v;  // append the new item to the tail of array
        swim(n);      // swim the new item to proper position
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {  // compare the item with its parent (at k/2)
            exchange(k / 2, k);
            k = k / 2;                     // keep going further up to parent's parent
        }
    }

    // dequeue and heap sort
    public Key delMax() {
        Key max = pq[1];   // heap-sorted max is at head of the array
        exchange(1, n--);  // exchange the item on tail to head
        pq[n + 1] = null;  // release memory
        sink(1);           // sink the new head to proper position
        return max;
    }

    private void sink(int k) {
        while (2 * k < n) {
            // get k's child items
            int j = 2 * k;

            // get the greater one of 2 child items
            // if j < j+1, then use j+1; if j >= j+1, then use j
            if (j < n && less(j, j + 1)) {
                j++;
            }

            // compare a[k] with a[j] (the greater child item)
            if (!less(k, j)) {
                break;
            }

            // exchange with the child if child is greater
            exchange(k, j);

            // keep going further down
            k = j;
        }
    }

    /*
    other common functions
     */
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exchange(int i, int j) {
        Key tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }
}
