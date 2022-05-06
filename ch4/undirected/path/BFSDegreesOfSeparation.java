package ch4.undirected.path;

import ch4.undirected.datatype.Graph;
import ch4.undirected.datatype.SymbolGraph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * SymbolGraph + BFS path
 */
public class BFSDegreesOfSeparation {
    /**
     * routes.txt " " JFK
     * <p>
     * Type in the airport name then shortest route will be printed:
     * LAS
     * JFK
     * ORD
     * PHX
     * LAS
     *
     * @param args
     */
    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph(args[0], args[1]);
        Graph g = sg.graph();
        String source = args[2];
        if (!sg.contains(source)) {
            StdOut.println(source + " not in database");
            return;
        }
        int s = sg.indexOf(source);
        BFSPath bfs = new BFSPath(g, s);
        while (!StdIn.isEmpty()) {
            String sink = StdIn.readLine();
            if (sg.contains(sink)) {
                int t = sg.indexOf(sink);
                if (bfs.hasPathTo(t)) {
                    for (int v : bfs.pathTo(t)) {
                        StdOut.println("  " + sg.nameOf(v));
                    }
                } else {
                    StdOut.println(" not connected");
                }
            } else {
                StdOut.println("not in database");
            }
        }
    }
}
