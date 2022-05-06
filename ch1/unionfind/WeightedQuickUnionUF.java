package ch1.unionfind;

import edu.princeton.cs.algs4.StdOut;

/*-
 * Algorithm
 * Always put smaller tree under bigger tree, so that
 * the distance from leaf node to root node will become
 * shorter eventually.
 * NOTE: the small and big here are in terms of the number
 *       of nodes, but not the height of the tree!!
 * initialize: N
 * union:      lgN
 * connected:  lgN
 *
 * Implementation
 * same as quick-union, but maintain extra array sz[i]
 * to count number of objects in the tree rooted at i.
 */
public class WeightedQuickUnionUF {
    private int[] id;    // mark parent of each element
    private int[] sz;    // size of the tree
    private int count;   // number of trees

    WeightedQuickUnionUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
        count = N;
    }

    /*-
     * to merge components containing p and q, link
     * root of smaller tree to root of larger tree;
     * then update the size array of the larger tree
     */
    void union(int p, int q) {
        // work on root of each tree only
        // leaf nodes still point to its parent
        int pr = root(p);
        int qr = root(q);

        // p, q is the same. nothing to do
        if (pr == qr) {
            return;
        }

        // weight the tree: always try to move smaller tree
        //
        // 1. p belongs to a smaller tree -> p's parent set to q
        if (sz[pr] < sz[qr]) {
            id[pr] = qr;
            sz[qr] += sz[pr];
        }
        // 2. q belongs to a smaller tree OR same size -> q's parent set to p
        else {
            id[qr] = pr;
            sz[pr] += sz[qr];
        }
        count--;
    }

    /*
     * helper function to get the root node index
     * Note the path compression improvement
     */
    private int root(int n) {
        // root node's value == index
        while (n != id[n]) {
            // extra step for sake of performance: set n's value to be same
            // as n's parent's value => move n to be one level up and then
            // search path becomes shorter
            id[n] = id[id[n]];

            // get parent node; same as regular QuickUnion find root
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

    private int count() {
        return count;
    }

    /**
     * client code
     */
    public static void main(String[] args) {
        // read in pair of integers from standard input
        // if they are not yet connected, connect them and print out pair
//        int N = StdIn.readInt();
//        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
//        while (!StdIn.isEmpty()) {
//            int p = StdIn.readInt();
//            int q = StdIn.readInt();
//            if (!uf.connected(p, q)) {
//                uf.union(p, q);
//                StdOut.println(p + " " + q);
//            }
//        }

        // for debug and understanding
        int N = 10;
        int[] a1 = {4, 3, 6, 9, 2, 8, 5, 7, 6, 1};
        int[] a2 = {3, 8, 5, 4, 1, 9, 0, 2, 1, 0};
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        for (int i = 0; i < N; i++) {
            int p = a1[i];
            int q = a2[i];
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
        StdOut.println(uf.count() + " components");
    }
}
