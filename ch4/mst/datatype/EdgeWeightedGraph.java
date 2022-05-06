package ch4.mst.datatype;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedGraph {
    private final int V;
    private int E;
    // store adjacent Edge(weighted) instead of adjacent Vertices
    private Bag<EdgeWeighted>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public EdgeWeightedGraph(In in) {
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
            EdgeWeighted edgeWeighted = new EdgeWeighted(v, w, weight);
            addEdge(edgeWeighted);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(EdgeWeighted e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<EdgeWeighted> adj(int v) {
        return adj[v];
    }

    public Iterable<EdgeWeighted> edges() {
        Bag<EdgeWeighted> bag = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (EdgeWeighted e : adj[v]) {
                if (e.other(v) > v) {
                    bag.add(e);
                }
            }
        }
        return bag;
    }

}
