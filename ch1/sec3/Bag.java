package ch1.sec3;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {
    private Node first;

    private class Node {
        Item item;
        Node next;
    }

    public void add(Item item) {
        Node tmp = first;

        first = new Node();
        first.item = item;
        first.next = tmp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
