package ch4.shortestpath;


import ch4.directed.order.Topological;
import ch4.shortestpath.datatype.EdgeDirected;
import ch4.shortestpath.datatype.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * faster and simpler than DijkstraSP
 * handles negative edge weights
 * solve longest path as well
 */
public class AcyclicSP {
    private EdgeDirected[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph G, int s) {
        edgeTo = new EdgeDirected[G.V()];
        distTo = new double[G.V()];

        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        Topological top = new Topological(G);
        for (int v : top.order()) {
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (EdgeDirected e : G.adj(v)) {
            // the vertex w on the other end
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }

    /*
    distance from s to v, infinity is no path
     */
    public double distTo(int v) {
        return distTo[v];
    }

    /*
    has path from s to v?
     */
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /*
    path from s to v, null if no path
     */
    public Iterable<EdgeDirected> pathTo(int v) {
        if (!hasPathTo((v))) {
            return null;
        }
        Stack<EdgeDirected> path = new Stack<>();
        for (EdgeDirected e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    /**
     * tinyEWDAG.txt 5
     * <p>
     * 5 to 0 (0.73): 5->4  0.35  4->0  0.38
     * 5 to 1 (0.32): 5->1  0.32
     * 5 to 2 (0.62): 5->7  0.28  7->2  0.34
     * 5 to 3 (0.61): 5->1  0.32  1->3  0.29
     * 5 to 4 (0.35): 5->4  0.35
     * 5 to 5 (0.00):
     * 5 to 6 (1.13): 5->1  0.32  1->3  0.29  3->6  0.52
     * 5 to 7 (0.28): 5->7  0.28
     *
     * @param args
     */
    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        AcyclicSP sp = new AcyclicSP(G, s);

        for (int t = 0; t < G.V(); t++) {
            StdOut.print(s + " to " + t);
            StdOut.printf(" (%4.2f): ", sp.distTo(t));
            if (sp.hasPathTo(t)) {
                for (EdgeDirected e : sp.pathTo(t)) {
                    StdOut.print(e + "  ");
                }
            }
            StdOut.println();
        }
    }

}
