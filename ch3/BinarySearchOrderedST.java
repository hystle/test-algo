package ch3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
keeps the keys and values in parallel arrays
 */
public class BinarySearchOrderedST<Key extends Comparable<Key>, Value> implements ST<Key, Value> {
    private Key[] keys;
    private Value[] vals;
    private int n;

    BinarySearchOrderedST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    @Override
    public void put(Key key, Value val) {
        int i = rank(key);
        // update?
        if (i < n && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        // move all elements between [i, n] one position (from n to i)
        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        // insert new key&val
        keys[i] = key;
        vals[i] = val;
        // increment size
        n++;
    }

    @Override
    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < n && keys[i].equals(key)) {
            return vals[i];
        }
        return null;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public Key min() {
        return keys[0];
    }

    @Override
    public Key max() {
        return keys[n - 1];
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return new Iterable<Key>() {
            @Override
            public Iterator<Key> iterator() {
                return new Iterator<Key>() {
                    int i = rank(lo);
                    int j = rank(hi);

                    @Override
                    public boolean hasNext() {
                        return i <= j;
                    }

                    @Override
                    public Key next() {
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }
                        return keys[i++];
                    }
                };
            }
        };
    }

    @Override
    public Key floor(Key key) {
        int i = rank(key);
        // hit
        if (i < n && keys[i].equals(key)) {
            return keys[i];
        }
        // not hit
        if (i == 0) {
            return keys[0];
        } else {
            return keys[i - 1];
        }
    }

    @Override
    public Key ceiling(Key key) {
        // because of the "return lo" in rank, it guarantees
        // either returns the match key index or the smallest one above the key
        int i = rank(key);
        return keys[i];
    }

    @Override
    public int rank(Key key) {
        int lo = 0;
        int hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp > 0) {
                lo = mid + 1;
            } else if (cmp < 0) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    @Override
    public Key select(int k) {
        return keys[k];
    }

    @Override
    public void delete(Key key) {

    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public void deleteMin() {
        delete(min());
    }

    @Override
    public void deleteMax() {
        delete(max());
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
}
