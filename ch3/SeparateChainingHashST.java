package ch3;

public class SeparateChainingHashST<Key extends Comparable<Key>, Value> {
    // size of the table
    private int m;
    // array of the table
    private SequentialSearchUnorderedST<Key, Value>[] st;

    public SeparateChainingHashST(int m) {
        this.m = m;
        st = new SequentialSearchUnorderedST[m];
        // initialize a symbol table for each index
        for (int i = 0; i < m; i++) {
            st[i] = new SequentialSearchUnorderedST<>();
        }
    }

    private int hash(Key key) {
        // mask off sign bit -> modular by the size of
        // table -> return [0, m] as index of the array
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        st[hash(key)].put(key, value);
    }

    public Iterable<Key> keys() {
        // TODO
        return null;
    }
}
