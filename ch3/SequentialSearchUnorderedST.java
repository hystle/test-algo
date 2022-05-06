package ch3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SequentialSearchUnorderedST<Key extends Comparable<Key>, Value> {

    private class Node {
        Key key;
        Value val;
        Node next;

        Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    // first node in the linked list
    private Node first;

    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;
            }
        }
        return null;
    }

    public int size() {
        int cnt = 0;
        for (Node x = first; x != null; x = x.next) {
            cnt++;
        }
        return cnt;
    }

    public void delete(Key key) {
        Node x = first;
        if (key.equals(x.key)) {
            first = x.next;
            return;
        }
        while (x != null && x.next != null) {
            if (key.equals(x.next.key)) {
                x.next = x.next.next;
                return;
            }
            x = x.next;
        }
    }

    public boolean contains(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Iterable<Key> keys() {
        return new Iterable<Key>() {
            @Override
            public Iterator<Key> iterator() {
                return new Iterator<Key>() {
                    Node x = first;

                    @Override
                    public boolean hasNext() {
                        return x != null;
                    }

                    @Override
                    public Key next() {
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }
                        Key tmp = x.key;
                        x = x.next;
                        return tmp;
                    }
                };
            }
        };
    }

}
