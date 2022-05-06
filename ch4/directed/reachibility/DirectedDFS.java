package ch4.directed.reachibility;

import ch4.directed.datatype.Digraph;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DirectedDFS {
    private boolean[] marked;

    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int s : sources) {
            if (!marked[s]) {
                dfs(G, s);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    /**
     * tinyDG.txt 1 2 6
     * <p>
     * Target(s) reachable from:
     * 0 1 2 3 4 5 6 8 9 10 11 12
     *
     * @param args
     */
    public static void main(String[] args) {
        Digraph G = new Digraph(new In(args[0]));
        Bag<Integer> sources = new Bag<>();
        for (int i = 1; i < args.length; i++) {
            sources.add(Integer.parseInt(args[i]));
        }
        DirectedDFS reachable = new DirectedDFS(G, sources);
        StdOut.println("Target(s) reachable from: ");
        for (int v = 0; v < G.V(); v++) {
            if (reachable.marked(v)) {
                StdOut.print(v + " ");
            }
        }
        StdOut.println();
    }
}
