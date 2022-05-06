package ch1.unionfind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*-
 * lazy approach
 * initialize: N
 * union:      N
 * find:       N
 * DEFECT: find too expensive
 *
 * use a tree structure where id[i] is the parent of i
 * Ex. 0 1 9   6 7 8
 *        / \  |
 *       2   4 5
 *           |
 *           3
 * Array is defined as per the graph above
 * idx  0 1 2 3 4 5 6 7 8 9
 * id[] 0 1 9 4 9 6 6 7 8 9
 *
 */
public class QuickUnionUF {

    private int[] id;
    private int count;

    /*-
     * Starting with array like:
     * idx  0 1 2 3 4 5 6 7 8 9
     * id[] 0 1 2 3 4 5 6 7 8 9
     *
     * @param N
     */
    QuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    /*-
     * to merge components containing p and q, set
     * the id of p's root to the id of q's root
     * NOTE: make sure union the root but not the node itself!!
     */
    void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);
        id[pRoot] = qRoot;
        count--;
    }

    /*
     * helper function to get the root node index
     */
    private int root(int n) {
        // root node's value == index
        while (n != id[n]) {
            n = id[n];
        }
        return n;
    }

    /*-
     * check if p and q have the same root
     */
    boolean connected(int p, int q) {
        // find the index of p's root
        // find the index of q's root
        // connected if the root indices are the same
        return root(p) == root(q);
    }

    int count() {
        return count;
    }

    /**
     * client code
     */
    public static void main(String[] args) {
        // read in a pair of integers from standard input
        // if they are not yet connected, connect them and print out pair
        int N = StdIn.readInt();
        QuickUnionUF uf = new QuickUnionUF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
        StdOut.println(uf.count() + " components");
    }
}
