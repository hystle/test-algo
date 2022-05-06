package ch4.shortestpath;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Arbitrage {

    /**
     * ZICZHOU-M-Q0WC:algorithm ziczhou$ java -classpath /Users/ziczhou/coursera/algorithm/out/production/algorithm:/Users/ziczhou/coursera/algs4.jar ch4.shortestpath.Arbitrage < /Users/ziczhou/coursera/algorithm/src/rates.txt
     * 1000.00000 USD =  741.00000 EUR
     * 741.00000 EUR = 1012.20600 CAD
     * 1012.20600 CAD = 1007.14497 USD
     *
     * @param args
     */
    public static void main(String[] args) {
        int V = StdIn.readInt();
        String[] name = new String[V];
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++) {
            name[v] = StdIn.readString();
            for (int w = 0; w < V; w++) {
                double rate = StdIn.readDouble();
                DirectedEdge e = new DirectedEdge(v, w, -Math.log(rate));
                G.addEdge(e);
            }
        }

        BellmanFordSP spt = new BellmanFordSP(G, 0);
        if (spt.hasNegativeCycle()) {
            double stake = 1000;
            for (DirectedEdge e : spt.negativeCycle()) {
                StdOut.printf("%10.5f %s ", stake, name[e.from()]);
                stake *= Math.exp(-e.weight());
                StdOut.printf("= %10.5f %s\n", stake, name[e.to()]);
            }
        } else {
            StdOut.println("No arbitrage opportunity");
        }
    }
}
