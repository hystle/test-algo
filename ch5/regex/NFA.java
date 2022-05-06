package ch5.regex;

import ch4.directed.datatype.Digraph;
import ch4.directed.reachibility.DirectedDFS;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class NFA {
    private char[] re;
    private Digraph G;
    private int m;

    public NFA(String regexp) {
        Stack<Integer> ops = new Stack<>();
        re = regexp.toCharArray();
        m = re.length;
        G = new Digraph(m + 1);

        for (int i = 0; i < m; i++) {
            int lp = i;
            if (re[i] == '(' || re[i] == '|') {
                ops.push(i);
            } else if (re[i] == ')') {
                int or = ops.pop();
                if (re[or] == '|') {
                    lp = ops.pop();
                    G.addEdge(lp, or + 1);
                    G.addEdge(or, i);
                } else {
                    lp = or;
                }
            }
            if (i < m - 1 && re[i + 1] == '*') {
                G.addEdge(lp, i + 1);
                G.addEdge(i + 1, lp);
            }
            if (re[i] == '(' || re[i] == '*' || re[i] == ')') {
                G.addEdge(i, i + 1);
            }
        }
    }

    public boolean recognizes(String txt) {
        Bag<Integer> pc = new Bag<>();
        DirectedDFS dfs = new DirectedDFS(G, 0);
        for (int v = 0; v < G.V(); v++) {
            if (dfs.marked(v)) {
                pc.add(v);
            }
        }
        for (int i = 0; i < txt.length(); i++) {
            Bag<Integer> states = new Bag<>();
            for (int v : pc) {
                if (v < m) {
                    if (re[v] == txt.charAt(i) || re[v] == '.') {
                        states.add(v + 1);
                    }
                }
            }
            pc = new Bag<>();
            dfs = new DirectedDFS(G, states);
            for (int v = 0; v < G.V(); v++) {
                if (dfs.marked(v)) {
                    pc.add(v);
                }
            }
        }
        for (int v : pc) {
            if (v == m) {
                return true;
            }
        }
        return false;
    }

    /**
     * java NFA "(A*B|AC)D" < tinyL.txt
     * <p>
     * java -classpath /Users/ziczhou/coursera/algorithm/out/production/algorithm:/Users/ziczhou/coursera/algs4.jar ch5.regex.NFA "(A*B|AC)D" < /Users/ziczhou/coursera/algorithm/src/tinyL.txt
     * ABD
     * ABCCBD
     *
     * @param args
     */
    public static void main(String[] args) {
        String regexp = "(.*" + args[0] + ".*)";
        NFA nfa = new NFA(regexp);
        while (StdIn.hasNextLine()) {
            String txt = StdIn.readLine();
            if (nfa.recognizes(txt)) {
                StdOut.println(txt);
            }
        }
    }
}
