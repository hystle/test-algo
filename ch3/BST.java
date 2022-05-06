package ch3;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> implements ST<Key, Value> {

    private class Node {
        private Node left, right;
        private Key key;
        private Value val;
        private int n;  // number of nodes in subtree

        Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            this.n = n;
        }
    }

    private Node root;

    @Override
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else {
            x.val = val;
        }
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        // not hit
        if (x == null) {
            return null;
        }
        // hit
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            return get(x.right, key);
        } else if (cmp < 0) {
            return get(x.left, key);
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
        } else {
            return x.n;
        }
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
        // the min is the node that does not have left child
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
        // when the target "key" is larger than the current key,
        // the current key is a potential return value, given
        // further down there is no hit or another larger fork
        Node t = floor(x.right, key);
        if (t != null) {  // exact match in right branch -> return hit
            return t;
        } else {  // not hit in right branch -> return current x
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
        // t is not null means there is a hit in recursive left
        // otherwise, return current x
        Node t = ceiling(x.left, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    @Override
    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(key, x.left);
        } else if (cmp > 0) {
            return size(x.left) + rank(key, x.right) + 1;
        } else {
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
        // more than need on left -> continue go left
        if (t > k) {
            return select(x.left, k);
        }
        // until left is not enough -> find rest in go right
        else if (t < k) {
            return select(x.right, k - t - 1);
        }
        // match need -> done
        else {
            return x;
        }
    }

    @Override
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.n = size(x.left) + size(x.right) + 1;
        return x;
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
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        // when there is no left -> current x is the min to be removed -> x.right might still
        // have node -> return it to caller (upper level node) as its left node
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) {
            return x.left;
        }
        x.right = deleteMax(x.right);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
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
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        List<Key> l = new ArrayList<>();
        keys(root, l, lo, hi);
        return l;
    }

    private void keys(Node x, List<Key> l, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);
        if (cmpLo < 0) {
            keys(x.left, l, lo, hi);
        }
        if (cmpLo <= 0 && cmpHi >= 0) {
            l.add(x.key);
        }
        if (cmpHi > 0) {
            keys(x.right, l, lo, hi);
        }
    }
}
