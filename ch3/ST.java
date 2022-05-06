package ch3;

/*
symbol table
 */
public interface ST<Key extends Comparable<Key>, Value> {

    void put(Key key, Value val);

    Value get(Key key);

    int size();

    Key min();

    Key max();

    Iterable<Key> keys(Key lo, Key hi);

    /*
    largest key less than or equal to "key"
    */
    Key floor(Key key);

    /*
    smallest key greater than or equal to "key"
     */
    Key ceiling(Key key);

    /*
    number of keys less than "key"
     */
    int rank(Key key);

    /*
    key of the rank "k"
     */
    Key select(int k);

    void delete(Key key);

    boolean contains(Key key);

    boolean isEmpty();

    void deleteMin();

    void deleteMax();

    int size(Key lo, Key hi);

    Iterable<Key> keys();
}
