package ch4.directed.scc;

import ch4.directed.datatype.Digraph;
import ch4.directed.order.DFSOrder;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * very similar to Connected in undirected graph
 */
public class KosarajuSharirSCC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSharirSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        // topological sorting on the reverse digraph of G
        DFSOrder order = new DFSOrder(G.reverse());
        for (int s : order.reversePostOrder()) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }

    /**
     * tinyDG.txt
     * <p>
     * 5 components in Digraph
     * 1
     * 0 2 3 4 5
     * 9 10 11 12
     * 6 8
     * 7
     *
     * @param args
     */
    public static void main(String[] args) {
        Digraph G = new Digraph(new In(args[0]));
        KosarajuSharirSCC cc = new KosarajuSharirSCC(G);

        int n = cc.count();
        StdOut.println(n + " components in Digraph");

        // build a queue of queue
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[n];
        for (int i = 0; i < n; i++) {
            components[i] = new Queue<>();
        }
        // column is the number of connected components
        // row is the vertices belonging to each components
        for (int v = 0; v < G.V(); v++) {
            components[cc.id(v)].enqueue(v);
        }
        // print all
        for (int i = 0; i < n; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
}
