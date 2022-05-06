package ch4.shortestpath;

import ch4.shortestpath.datatype.EdgeDirected;
import ch4.shortestpath.datatype.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class CriticalPathMethod {

    /**
     * TODO: NPE when executing
     *
     * @param args
     */
    public static void main(String[] args) {
        int n = StdIn.readInt();
        StdIn.readLine();
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(2 * n + 2);
        int s = 2 * n;
        int t = 2 * n + 1;
        for (int i = 0; i < n; i++) {
            String[] a = StdIn.readLine().split("\\s+");
            double duration = Double.parseDouble(a[0]);
            G.addEdge(new EdgeDirected(i, i + n, duration));
            G.addEdge(new EdgeDirected(s, i, 0.0));
            G.addEdge(new EdgeDirected(i + n, t, 0.0));
            for (int j = 1; j < a.length; j++) {
                int successor = Integer.parseInt(a[j]);
                G.addEdge(new EdgeDirected(i + n, successor, 0.0));
            }
        }

        AcyclicSP lp = new AcyclicSP(G, s);

        StdOut.println("start time: ");
        for (int i = 0; i < n; i++) {
            StdOut.printf("%4d: %5.1f\n", i, lp.distTo(i));
        }
        StdOut.printf("finish time: %5.1f\n", lp.distTo(t));
    }
}
