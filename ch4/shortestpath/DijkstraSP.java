package ch4.shortestpath;

import ch4.shortestpath.datatype.EdgeDirected;
import ch4.shortestpath.datatype.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DijkstraSP {
    private EdgeDirected[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        // initialization
        edgeTo = new EdgeDirected[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<>(G.V());
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        // start by adding 0 to pq
        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            relax(G, pq.delMin());
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (EdgeDirected e : G.adj(v)) {
            // the vertex w on the other end
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
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
     * tinyEWD.txt 0
     * <p>
     * 0 to 0 (0.00):
     * 0 to 1 (1.05): 0->4 0.38  4->5 0.35  5->1 0.32
     * 0 to 2 (0.26): 0->2 0.26
     * 0 to 3 (0.99): 0->2 0.26  2->7 0.34  7->3 0.39
     * 0 to 4 (0.38): 0->4 0.38
     * 0 to 5 (0.73): 0->4 0.38  4->5 0.35
     * 0 to 6 (1.51): 0->2 0.26  2->7 0.34  7->3 0.39  3->6 0.52
     * 0 to 7 (0.60): 0->2 0.26  2->7 0.34
     *
     * @param args
     */
    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DijkstraSP sp = new DijkstraSP(G, s);

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
