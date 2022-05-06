package ch4.undirected.cycle;

import ch4.undirected.datatype.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DFSCycle {

    private boolean[] marked;
    private boolean hasCycle;

    DFSCycle(Graph G) {
        marked = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, -1, s);
            }
        }
    }

    private void dfs(Graph G, int u, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, v, w);
            } else if (w != u) {
                hasCycle = true;
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    /**
     * tinyG.txt
     * <p>
     * true
     *
     * @param args
     */
    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        StdOut.println(new DFSCycle(G).hasCycle());
    }
}
