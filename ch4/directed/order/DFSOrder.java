package ch4.directed.order;

import ch4.directed.datatype.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class DFSOrder {
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;
    private boolean[] marked;

    public DFSOrder(Digraph G) {
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        // pre-dfs
        pre.enqueue(v);

        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        // post + reversePost
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> preOrder() {
        return pre;
    }

    public Iterable<Integer> postOrder() {
        return post;
    }

    /**
     * topological sorting
     */
    public Iterable<Integer> reversePostOrder() {
        return reversePost;
    }
}
