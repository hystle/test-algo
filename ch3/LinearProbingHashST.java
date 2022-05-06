package ch3;

public class LinearProbingHashST<Key extends Comparable<Key>, Value> {
    // number of key-value pairs in the table
    private int n;
    // initial size of the table
    private int m = 16;
    // parallel arrays of keys and values
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST() {
        keys = (Key[]) new Object[m];
        vals = (Value[]) new Object[m];
    }

    public LinearProbingHashST(int m) {
        this.m = m;
        keys = (Key[]) new Object[m];
        vals = (Value[]) new Object[m];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int capacity) {
        // initialize a new object
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<>(capacity);
        // copy all entries in old to new one
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }
        // re-assign the variables
        keys = t.keys;
        vals = t.vals;
        m = t.m;
    }

    public void put(Key key, Value val) {
        // resize 2 times bigger when half of the spots taken
        if (n >= m / 2) {
            resize(2 * m);
        }
        // start from the hashed index; stay in loop if the spot
        // not null; increase i by 1 (modular by m to prevent overflow)
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            // update
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
            // new key-value pair
            keys[i] = key;
            vals[i] = val;
            // increment taken spot count
            n++;
        }
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }
}
