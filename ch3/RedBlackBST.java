package ch3;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class RedBlackBST<Key extends Comparable<Key>, Value> implements ST<Key, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        Key key;
        Value val;
        Node left;
        Node right;
        int n;
        boolean color;

        Node(Key key, Value val, int n, boolean color) {
            this.key = key;
            this.val = val;
            this.n = n;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        // the other node involved
        Node x = h.right;
        // links
        h.right = x.left;
        x.left = h;
        // colors
        x.color = h.color;
        h.color = RED;
        // size
        x.n = h.n;
        h.n = size(h.left) + size(h.right) + 1;
        // done
        return x;
    }

    private Node rotateRight(Node h) {
        // the other node involved
        Node x = h.left;
        // links
        h.left = x.right;
        x.right = h;
        // colors
        x.color = h.color;
        h.color = RED;
        // size
        x.n = h.n;
        h.n = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h) {
        // flip middle
        h.color = RED;
        // flip both sides
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    @Override
    public void put(Key key, Value val) {
        root = put(root, key, val);
        // root should always be BLACK
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) {
            // new node should always have a RED link pointing from parent to this node
            return new Node(key, val, 1, RED);
        }
        // recursively going down - same as BST
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, val);
        } else if (cmp > 0) {
            h.right = put(h.right, key, val);
        } else {
            h.val = val;
        }
        // after recursion: going up - handle color transformation
        // 1. right is RED and left is BLACK -> rotate left
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        // 2. left and left.left is RED -> rotate right
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        // 3. both left and right is RED -> flip colors
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        // count size - same as BST
        h.n = size(h.left) + size(h.right) + 1;
        return h;
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.n;
    }

    @Override
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node x = min(root);
        return x.key;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    @Override
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node x = max(root);
        return x.key;
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }


    @Override
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) {
            throw new NoSuchElementException();
        }
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    @Override
    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) {
            throw new NoSuchElementException();
        }
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp > 0) {
            return ceiling(x.right, key);
        }
        Node t = ceiling(x.left, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    /*
    number of keys SMALLER THAN key
     */
    @Override
    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(x.left, key);
        } else if (cmp > 0) {
            return size(x.left) + rank(x.right, key) + 1;
        } else {
            // does not contain node itself
            return size(x.left);
        }
    }

    @Override
    public Key select(int k) {
        if (k < 0 || k > size()) {
            throw new IllegalArgumentException();
        }
        Node x = select(root, k);
        return x.key;
    }

    private Node select(Node x, int k) {
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        if (t < k) {
            return select(x.right, t - k - 1);
        } else if (t > k) {
            return select(x.left, k);
        } else {
            return x;
        }
    }

    @Override
    public void delete(Key key) {
        // TODO
    }

    @Override
    public boolean contains(Key key) {
        return contains(root, key);
    }

    private boolean contains(Node x, Key key) {
        if (x == null) {
            return false;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return contains(x.left, key);
        } else if (cmp > 0) {
            return contains(x.right, key);
        } else {
            return true;
        }
    }

    @Override
    public boolean isEmpty() {
        return root.n == 0;
    }

    @Override
    public void deleteMin() {
        // TODO
    }

    @Override
    public void deleteMax() {
        // TODO
    }

    @Override
    public int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0) {
            return 0;
        } else if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        List<Key> l = new ArrayList<>();
        keys(root, l, lo, hi);
        return l;
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    private void keys(Node x, List<Key> l, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);

        // NOTE: no return and all "if" not "else if"
        // so all 3 if clauses all will be executed
        // 1. lo < x.key -> still possible on left
        if (cmpLo < 0) {
            keys(x.left, l, lo, hi);
        }
        // 2. in range: add to return list
        // this is done after left OR before right
        if (cmpLo <= 0 && cmpHi >= 0) {
            l.add(x.key);
        }
        // 3. x.key < hi -> still possible on right
        if (cmpHi > 0) {
            keys(x.right, l, lo, hi);
        }
    }
}
