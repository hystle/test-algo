package ch4.undirected.datatype;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * graph with symbolic vertex names
 */
public class SymbolGraph {
    // map names to indices
    private ST<String, Integer> st;
    // map indices to names
    private String[] keys;
    // maintains a regular Graph
    private Graph G;

    public SymbolGraph(String stream, String sp) {
        st = new ST<>();
        // first pass of input txt: build the index
        // by reading strings to associate each distinct string with an index
        In in = new In(stream);
        while (!in.isEmpty()) {
            String[] a = in.readLine().split(sp);
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i])) {
                    st.put(a[i], st.size());
                }
            }
        }
        // inverted index to get the string keys is an array
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }
        // second pass: build the graph
        // by connecting the first vertex on each line to all the others
        G = new Graph(st.size());
        in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                G.addEdge(v, st.get(a[i]));
            }
        }
    }

    public boolean contains(String key) {
        return st.contains(key);
    }

    public int indexOf(String key) {
        return st.get(key);
    }

    public String nameOf(int v) {
        return keys[v];
    }

    public Graph graph() {
        return G;
    }

    /*
    Type in the airport name then available routes will be printed:
    JFK
      ORD
      ATL
      MCO
     */
    public static void main(String[] args) {
        String filename = args[0];
        String delimiter = args[1];
        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph G = sg.graph();
        StdOut.println("Type in the airport name (available routes will be printed):");
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            for (int v : G.adj(sg.indexOf(source))) {
                StdOut.println("  " + sg.nameOf(v));
            }
        }
    }
}
