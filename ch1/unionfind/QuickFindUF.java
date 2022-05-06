package ch1.unionfind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * eager approach
 * initialize N
 * find       1
 * union      N
 * DEFECT: union too expensive
 */
public class QuickFindUF {

    private int[] id;
    private int count;

    /**
     * Starting with array like:
     * idx  0 1 2 3 4 5 6 7 8 9
     * id[] 0 1 2 3 4 5 6 7 8 9
     */
    QuickFindUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    /**
     * change all entries whose id equals id[p] to id[q]
     * idx      0 1 2 3 4 5 6 7 8 9
     * id[] old 0 1 1 8 8 0 0 1 8 8
     * id[] new 1 1 1 8 8 1 1 1 8 8
     */
    void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {
            // a potential bug is using id[p] instead of pid here
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
        count--;
    }

    /*-
     * check if p and q (the indices) have the same value in id array
     * Ex. {0,5,6}, {1,2,7}, {3,4,8,9}
     * idx  0 1 2 3 4 5 6 7 8 9
     * id[] 0 1 1 8 8 0 0 1 8 8
     */
    boolean connected(int p, int q) {
        return id[p] == id[q];
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
        QuickFindUF uf = new QuickFindUF(N);
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
