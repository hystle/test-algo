package ch4.mst.datatype;

public class EdgeWeighted implements Comparable<EdgeWeighted> {

    private final int v;
    private final int w;
    private final double weight;

    public EdgeWeighted(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    // return either end of the edge
    public int either() {
        return v;
    }

    // return the other end (vertex) of the edge
    public int other(int vertex) {
        if (vertex == v) {
            return w;
        } else if (vertex == w) {
            return v;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int compareTo(EdgeWeighted that) {
        if (this.weight() < that.weight()) {
            return -1;
        } else if (this.weight() > that.weight()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }
}
