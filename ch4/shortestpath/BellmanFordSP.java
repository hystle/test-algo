package ch4.shortestpath;


import edu.princeton.cs.algs4.*;

/**
 * See Arbitrage.java for another test client
 */
public class BellmanFordSP {
    // length of path to v
    private double[] distTo;
    // last edge on path to v
    private DirectedEdge[] edgeTo;
    // is this vertex on the queue
    private boolean[] onQueue;
    // vertices have being relaxed
    private Queue<Integer> queue;
    // number edge relaxations
    private int cost;
    // negative cycle in edgeTo[]
    private Iterable<DirectedEdge> cycle;

    public BellmanFordSP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQueue = new boolean[G.V()];
        queue = new Queue<>();
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        queue.enqueue(s);
        onQueue[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.dequeue();
            onQueue[v] = false;
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            // 1. distTo[w]: known distance to w from source (old)
            // 2. distTo[v]: known distance to v from source (new)
            // 3. e.weight:  distance from v to w            (new)
            // if old is worse than new, then update <=> if via current edge (v->w)
            // is better (smaller) than previously known distance, then update
            //
            // edgeTo[w]: known best parent edge (from->to) to w
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQueue[w]) {
                    queue.enqueue(w);
                    onQueue[w] = true;
                }
            }
            if (cost++ % G.V() == 0) {
                findNegativeCycle();
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
    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo((v))) {
            return null;
        }
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++) {
            if (edgeTo[v] != null) {
                spt.addEdge(edgeTo[v]);
            }
        }
        EdgeWeightedDirectedCycle cf = new EdgeWeightedDirectedCycle(spt);
        cycle = cf.cycle();

    }

    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> negativeCycle() {
        return cycle;
    }

    /**
     * tinyEWDn.txt 0
     * <p>
     * 0 to 0 (0.00):
     * 0 to 1 (0.93): 0->2  0.26  2->7  0.34  7->3  0.39  3->6  0.52  6->4 -1.25  4->5  0.35  5->1  0.32
     * 0 to 2 (0.26): 0->2  0.26
     * 0 to 3 (0.99): 0->2  0.26  2->7  0.34  7->3  0.39
     * 0 to 4 (0.26): 0->2  0.26  2->7  0.34  7->3  0.39  3->6  0.52  6->4 -1.25
     * 0 to 5 (0.61): 0->2  0.26  2->7  0.34  7->3  0.39  3->6  0.52  6->4 -1.25  4->5  0.35
     * 0 to 6 (1.51): 0->2  0.26  2->7  0.34  7->3  0.39  3->6  0.52
     * 0 to 7 (0.60): 0->2  0.26  2->7  0.34
     * <p>
     * tinyEWDnc.txt 0
     * Negative cycle
     * 4->5  0.35
     * 5->4 -0.66
     *
     * @param args
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        int s = Integer.parseInt(args[1]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);

        BellmanFordSP sp = new BellmanFordSP(G, s);

        // print negative cycle
        if (sp.hasNegativeCycle()) {
            StdOut.println("Negative cycle");
            for (DirectedEdge e : sp.negativeCycle())
                StdOut.println(e);
        }

        // print shortest paths
        else {
            for (int v = 0; v < G.V(); v++) {
                if (sp.hasPathTo(v)) {
                    StdOut.printf("%d to %d (%5.2f)  ", s, v, sp.distTo(v));
                    for (DirectedEdge e : sp.pathTo(v)) {
                        StdOut.print(e + "   ");
                    }
                    StdOut.println();
                } else {
                    StdOut.printf("%d to %d           no path\n", s, v);
                }
            }
        }

    }
}
