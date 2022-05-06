package ch4.undirected.path;

import ch4.undirected.datatype.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DFSPath {

    // search
    // an array to mark all the vertices that are connected to the target vertex s
    private boolean[] marked;
    private int count;

    // path
    private int[] edgeTo;    // last vertex of known path to this vertex
    private final int s;     // source

    /**
     * find the vertices connected to a source vertex s
     */
    DFSPath(Graph G, int s) {
        // path
        edgeTo = new int[G.V()];
        this.s = s;
        // search
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    /**
     * marks the array "marked" as the dfs proceeds
     * and stores the vertex that connects to it
     */
    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            // IMPORTANT: only visit the vertex if the vertex has not visited/marked
            // prevents the infinite loop
            if (!marked[w]) {
                edgeTo[w] = v;  // path: save the last known vertex v that connects to w
                dfs(G, w);      // search: go deeper
            }
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }

    /**
     * if hasPathTo then should have visited
     */
    public boolean hasPathTo(int v) {
        return marked(v);
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        // for print in reverse
        Stack<Integer> path = new Stack<>();
        // start from target v; follow last know connecting to v -> ... -> until meet base vertex s
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        // push s in stack as well
        path.push(s);
        return path;
    }

    /**
     * tinyCG.txt 0
     * <p>
     * 0 to 0: 0
     * 0 to 1: 0-2-1
     * 0 to 2: 0-2
     * 0 to 3: 0-2-3
     * 0 to 4: 0-2-3-4
     * 0 to 5: 0-2-3-5
     *
     * @param args
     */
    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DFSPath paths = new DFSPath(G, s);
        for (int v = 0; v < G.V(); v++) {
            StdOut.print(s + " to " + v + ": ");
            if (paths.hasPathTo(v)) {
                for (int x : paths.pathTo(v)) {
                    if (x == s) {
                        StdOut.print(x);
                    } else {
                        StdOut.print("-" + x);
                    }
                }
            }
            StdOut.println();
        }
    }
}
