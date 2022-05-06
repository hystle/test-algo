package ch4.undirected.datatype;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Graph {
    private final int V;         // number of vertices
    private int E;               // number of edges
    private Bag<Integer>[] adj;  // adjacency lists

    /*
    create a V-vertex graph with no edges
     */
    public Graph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];  // create array of lists
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();           // initialize all lists
        }
    }

    /*
    create a graph from input stream
     */
    public Graph(In in) {
        this(in.readInt());        // read V and construct this graph
        int E = in.readInt();      // read E
        for (int i = 0; i < E; i++) {
            int v = in.readInt();  // read 1 vertex
            int w = in.readInt();  // read another vertex
            addEdge(v, w);         // add edge connecting them
        }
    }

    /*
    number of vertices
     */
    public int V() {
        return V;
    }

    /*
    number of edges
     */
    public int E() {
        return E;
    }

    /*
    add edge v-w to this graph
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);        // add w to v's list
        adj[w].add(v);        // add v to w's list
        E++;
    }

    /*
    vertices adjacent to vertex v
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /*
    String representation
     */
    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < V; v++) {
            s += v + ": ";
            for (int w : this.adj(v)) {
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }

    /*
    degree of v: # of vertices adjacent to it
     */
    public static int degree(Graph G, int v) {
        int degree = 0;
        for (int w : G.adj(v)) {
            degree++;
        }
        return degree;
    }

    /*
    maximum of degree
     */
    public static int maxDegree(Graph G) {
        int max = 0;
        for (int v = 0; v < G.V(); v++) {
            if (degree(G, v) > max) {
                max = degree(G, v);
            }
        }
        return max;
    }

    /*
    average degree
     */
    public static double averageDegree(Graph G) {
        return 2.0 * G.E() / G.V();
    }

    /*
    count self-loops
     */
    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (v == w) {
                    count++;
                }
            }
        }
        // each edge counted twice
        return count / 2;
    }

    /*
    To run this:
    1. from IDE
    Right click -> Recompile
    Right click -> Run with argument
    Input tinyG.txt
    2. from CLI
    java -classpath /Users/ziczhou/coursera/algorithm/out/production/algorithm:/Users/ziczhou/coursera/algs4.jar ch4.mst.datatype.Graph tinyG.txt
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph g = new Graph(in);
        StdOut.print(g);
    }
}
