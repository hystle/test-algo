package ch5.trie;

import edu.princeton.cs.algs4.Queue;

public class TrieSymbolTable<Value> {
    private final static int R = 256;  // radix
    private Node root = new Node();    // root of trie

    private static class Node {
        private Object val;  // value to be held
        private Node[] next = new Node[R];  // array of length R as a "Node" in trie
    }

    /**
     * get a key-value pair from trie
     *
     * @param key
     * @return
     */
    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        // return when no more sub-trie (search key length is longer than trie length)
        if (x == null) {
            return null;
        }
        // return when reach the length of search key
        // could be miss or hit: depend on whether x.val is null
        if (d == key.length()) {
            return x;
        }
        // d is the index for iterating the search key
        char c = key.charAt(d);
        // go next digit of the search key
        return get(x.next[c], key, d + 1);
    }

    /**
     * add a key-value pair to trie
     *
     * @param key
     * @param val
     */
    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        // when search key length is longer, create a new "node"
        // not return yet
        if (x == null) {
            x = new Node();
        }
        // reach the length of the search key
        // set value then return
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        // set current node's next to return when child node are done
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    /**
     * number of keys in the trie
     *
     * @return
     */
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        int count = 0;
        if (x.val != null) {
            count++;
        }
        for (char c = 0; c < R; c++) {
            count += size(x.next[c]);
        }
        return count;
    }

    /**
     * collect all keys
     *
     * @return
     */
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new Queue<>();
        collect(get(root, prefix, 0), prefix, queue);
        return queue;
    }

    private void collect(Node x, String prefix, Queue<String> queue) {
        if (x == null) {
            return;
        }
        // enqueue when the val is not null!
        // so "b" won't enqueue but "by" will
        if (x.val != null) {
            queue.enqueue(prefix);
        }
        for (char c = 0; c < R; c++) {
            collect(x.next[c], prefix + c, queue);
        }
    }

    /**
     * wildcard match: all the keys that match s
     * dot(.) could be any character
     *
     * @param pattern
     * @return
     */
    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> queue = new Queue<>();
        collect(root, "", pattern, queue);
        return queue;
    }

    private void collect(Node x, String prefix, String pattern, Queue<String> queue) {
        int d = prefix.length();
        if (x == null) {
            return;
        }
        if (d == pattern.length() && x.val != null) {
            queue.enqueue(prefix);
        }
        if (d == pattern.length()) {
            return;
        }

        char next = pattern.charAt(d);
        for (char c = 0; c < R; c++) {
            if (next == '.' || next == c) {
                collect(x.next[c], prefix + c, pattern, queue);
            }
        }
    }

    /**
     * longest prefix with substring
     *
     * @param s
     * @return
     */
    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private int search(Node x, String s, int d, int length) {
        if (x == null) {
            return length;
        }
        // update the return length when we see a key
        // i.e., when s is "shel" and we run till "she"
        if (x.val != null) {
            length = d;
        }
        // return when index d equals the length of search key
        if (d == s.length()) {
            return length;
        }
        char c = s.charAt(d);
        return search(x.next[c], s, d + 1, length);
    }

    /**
     * delete
     *
     * @param key
     */
    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {  //
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }
        if (x.val != null) {
            return x;
        }

        for (char c = 0; c < R; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        TrieSymbolTable<Integer> trie = new TrieSymbolTable<>();
        trie.put("she", 0);
        trie.put("sells", 1);
        trie.put("sea", 2);
        trie.put("shells", 3);
        trie.put("by", 4);
        trie.put("the", 5);
        trie.put("sea", 6);
        trie.put("shore", 7);

        System.out.println("Get: " + trie.get("the").toString());

        System.out.println("Size: " + trie.size());

        System.out.print("Keys: ");
        for (String s : trie.keys()) {
            System.out.print(s + " ");
        }
        System.out.println();

        System.out.print("Wildcard match: ");
        for (String s : trie.keysThatMatch("..e")) {
            System.out.print(s + " ");
        }
        System.out.println();

        System.out.println("Longest prefix: " + trie.longestPrefixOf("shel"));

        trie.delete("she");
        System.out.print("After delete: ");
        System.out.print(trie.get("she"));
    }
}
