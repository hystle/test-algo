package ch4.directed.transitiveclosure;

import ch4.directed.datatype.Digraph;
import ch4.directed.reachibility.DirectedDFS;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * not ideal in practice: constructor uses space V^2 and time V*(E+V)
 * no ideal solution existing yet
 */
public class TransitiveClosure {
    private DirectedDFS[] all;

    TransitiveClosure(Digraph G) {
        // each row is type of DirectedDFS
        all = new DirectedDFS[G.V()];
        // construct each row with: can "v" connected to other vertices in "marked"
        for (int v = 0; v < G.V(); v++) {
            all[v] = new DirectedDFS(G, v);
        }
    }

    boolean reachable(int v, int w) {
        return all[v].marked(w);
    }

    /**
     * tinyDG.txt
     * <p>
     * true
     *
     * @param args
     */
    public static void main(String[] args) {
        Digraph G = new Digraph(new In(args[0]));
        TransitiveClosure tc = new TransitiveClosure(G);
        StdOut.println(tc.reachable(0, 3));
    }
}
