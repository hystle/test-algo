package ch4.undirected.connected;

import ch4.undirected.datatype.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class DFSConnected {
    private boolean[] marked;
    private int[] id;
    private int count;

    public DFSConnected(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        // go over all vertices in graph
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    /**
     * tinyG.txt
     * <p>
     * 3 components in Graph
     * 0 1 2 3 4 5 6
     * 7 8
     * 9 10 11 12
     *
     * @param args
     */
    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        DFSConnected cc = new DFSConnected(G);

        int n = cc.count();
        StdOut.println(n + " components in Graph");

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
