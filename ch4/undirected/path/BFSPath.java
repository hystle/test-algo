package ch4.undirected.path;

import ch4.undirected.datatype.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class BFSPath {

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;
    private int count;

    BFSPath(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    /**
     * no recursion
     */
    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<>();
        // mark origin vertex as seen
        marked[s] = true;
        count++;
        queue.enqueue(s);
        // loop handling the 1st vertex in queue
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            // enqueue its adjacent vertices if not seen
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    count++;
                    edgeTo[w] = v;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> stack = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            stack.push(x);
        }
        stack.push(s);
        return stack;
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }

    /**
     * tinyCG.txt 0
     * <p>
     * 0 to 0: 0
     * 0 to 1: 0-1
     * 0 to 2: 0-2
     * 0 to 3: 0-2-3
     * 0 to 4: 0-2-4
     * 0 to 5: 0-5
     *
     * @param args
     */
    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        BFSPath paths = new BFSPath(G, s);
        for (int v = 0; v < G.V(); v++) {
            StdOut.print(s + " to " + v + ": ");
            if (paths.hasPathTo(v)) {
                for (int x : paths.pathTo(v)) {
                    if (x == s) {
                        StdOut.print(x);
                    } else {
                        StdOut.print("-" + x);
                    }
                }
            }
            StdOut.println();
        }
    }
}
