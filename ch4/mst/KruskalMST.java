package ch4.mst;

import ch4.mst.datatype.EdgeWeighted;
import ch4.mst.datatype.EdgeWeightedGraph;
import edu.princeton.cs.algs4.*;

public class KruskalMST {

    private Queue<EdgeWeighted> mst;

    public KruskalMST(ch4.mst.datatype.EdgeWeightedGraph G) {
        mst = new Queue<>();
        MinPQ<EdgeWeighted> pq = new MinPQ<>();
        for (EdgeWeighted e : G.edges()) {
            pq.insert(e);
        }

        UF uf = new UF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            EdgeWeighted e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (uf.connected(v, w)) {
                continue;
            }
            uf.union(v, w);
            mst.enqueue(e);
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
        ch4.mst.datatype.EdgeWeightedGraph G = new EdgeWeightedGraph(in);

        KruskalMST mst = new KruskalMST(G);
        for (EdgeWeighted e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}
