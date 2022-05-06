package ch4.mst;

import ch4.mst.datatype.EdgeWeighted;
import ch4.mst.datatype.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class PrimMSTLazy {
    // MST vertices
    private boolean[] marked;
    // MST edges
    private Queue<EdgeWeighted> mst;
    // crossing and ineligible edges; sorts by weight
    private MinPQ<EdgeWeighted> pq;

    public PrimMSTLazy(EdgeWeightedGraph G) {
        pq = new MinPQ<>();
        marked = new boolean[G.V()];
        mst = new Queue<>();

        // populate pq with Edges adjacent to 0
        // also sort by weight from light to heavy
        visit(G, 0);

        while (!pq.isEmpty()) {
            // get and remove the lowest-weighted edge from priority queue
            EdgeWeighted e = pq.delMin();
            int v = e.either();
            int w = e.other(v);

            // skip if ineligible
            if (marked[v] && marked[w]) {
                continue;
            }

            // add edge to return mst
            mst.enqueue(e);

            // add either v or w 's adjacency to pq if 1 end is not marked
            if (!marked[v]) {
                visit(G, v);
            }
            if (!marked[w]) {
                visit(G, w);
            }
        }
    }

    /*
    add all edges adjacent to v to pq
     */
    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (EdgeWeighted e : G.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.insert(e);
            }
        }
    }

    public Iterable<EdgeWeighted> edges() {
        return mst;
    }

    public double weight() {
        double weight = 0;
        for (EdgeWeighted e : mst) {
            weight += e.weight();
        }
        return weight;
    }

    /**
     * tinyEWG.txt
     * <p>
     * 0-7 0.16000
     * 1-7 0.19000
     * 0-2 0.26000
     * 2-3 0.17000
     * 5-7 0.28000
     * 4-5 0.35000
     * 6-2 0.40000
     * 1.81000
     *
     * @param args
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);

        PrimMSTLazy mst = new PrimMSTLazy(G);
        for (EdgeWeighted e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}
