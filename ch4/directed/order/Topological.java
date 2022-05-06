package ch4.directed.order;

import ch4.directed.cycle.DirectedCycle;
import ch4.directed.datatype.Digraph;
import ch4.directed.datatype.SymbolDigraph;
import ch4.shortestpath.cycle.EdgeWeightedDirectedCycle;
import ch4.shortestpath.datatype.EdgeWeightedDigraph;
import ch4.shortestpath.order.DFSWeightedOrder;
import edu.princeton.cs.algs4.StdOut;

public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph G) {
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if (!cycleFinder.hasCycle()) {
            DFSOrder dfs = new DFSOrder(G);
            order = dfs.reversePostOrder();
        }
    }

    public Topological(EdgeWeightedDigraph G) {
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
        if (!finder.hasCycle()) {
            DFSWeightedOrder dfs = new DFSWeightedOrder(G);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean hasOrder() {
        return order != null;
    }

    /**
     * jobs.txt "/"
     * <p>
     * Calculus
     * Linear Algebra
     * Introduction to CS
     * Advanced Programming
     * Algorithms
     * Theoretical CS
     * Artificial Intelligence
     * Robotics
     * Machine Learning
     * Neural Networks
     * Databases
     * Scientific Computing
     * Computational Biology
     *
     * @param args
     */
    public static void main(String[] args) {
        String filename = args[0];
        String delimiter = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
        Topological top = new Topological(sg.digraph());
        if (top.hasOrder()) {
            for (int v : top.order()) {
                StdOut.println(sg.nameOf(v));
            }
        }
    }
}
