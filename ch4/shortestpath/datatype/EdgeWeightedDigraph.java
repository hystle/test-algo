package ch4.shortestpath.datatype;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private Bag<EdgeDirected>[] adj;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public EdgeWeightedDigraph(In in) {
        this.V = in.readInt();
        adj = new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            EdgeDirected e = new EdgeDirected(v, w, weight);
            addEdge(e);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(EdgeDirected e) {
        adj[e.from()].add(e);
        E++;
    }

    public Iterable<EdgeDirected> adj(int v) {
        return adj[v];
    }

    public Iterable<EdgeDirected> edges() {
        Bag<EdgeDirected> bag = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (EdgeDirected e : adj[v]) {
                bag.add(e);
            }
        }
        return bag;
    }
}
