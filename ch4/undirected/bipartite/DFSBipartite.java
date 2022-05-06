package ch4.undirected.bipartite;

import ch4.undirected.datatype.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

class DFSBipartite {

    private boolean[] marked;
    private boolean[] color;
    private boolean isBipartite = true;

    public DFSBipartite(Graph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(G, w);
            } else if (color[w] == color[v]) {
                isBipartite = false;
            }
        }
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    /**
     * tinyG.txt
     * <p>
     * false
     *
     * @param args
     */
    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        StdOut.println(new DFSBipartite(G).isBipartite());
    }
}
