package ch4.mst;

import ch4.mst.datatype.EdgeWeighted;
import ch4.mst.datatype.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This implementation keeps eligible crossing edges on an index priority queue
 * better performance than lazy version
 */
public class PrimMSTEager {

    // following 2 combined is equivalent to "mst" in the lazy version
    private EdgeWeighted[] edgeTo;    // shortest edge from tree vertex
    private double[] distTo;          // distTo[w] = edgeTo[w].weight()

    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    PrimMSTEager(EdgeWeightedGraph G) {
        edgeTo = new EdgeWeighted[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(G.V());

        // initialize pq with vertex 0 and weight 0
        distTo[0] = 0.0;
        pq.insert(0, 0.0);

        // add closest vertex to the tree
        while (!pq.isEmpty()) {
            visit(G, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (EdgeWeighted e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) {
                continue;
            }

            // if the weight is lighter than the lightest known
            if (e.weight() < distTo[w]) {
                // update the previous lightest in edgeTo[] + disTo[]
                edgeTo[w] = e;
                distTo[w] = e.weight();

                // update the priority queue
                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    public Iterable<EdgeWeighted> edges() {
        return new Iterable<EdgeWeighted>() {
            @Override
            public Iterator<EdgeWeighted> iterator() {
                return new Iterator<EdgeWeighted>() {
                    int i = 1;

                    @Override
                    public boolean hasNext() {
                        return i != edgeTo.length;
                    }

                    @Override
                    public EdgeWeighted next() {
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }
                        int v = edgeTo[i].either();
                        int w = edgeTo[i].other(v);
                        // note i++ to increment the index after use current i
                        return new EdgeWeighted(v, w, distTo[i++]);
                    }
                };
            }
        };
    }

    public double weight() {
        double weight = 0;
        for (double w : distTo) {
            weight += w;
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

        PrimMSTEager mst = new PrimMSTEager(G);
        for (EdgeWeighted e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}
