package ch1.sec3;

import java.util.Iterator;

/**
 * 1.3.29
 *
 * @param <Item>
 */
public class CircularLinkedListQueue<Item extends Comparable> implements Iterable<Item> {
    private Node last;
    private int n;

    private class Node {
        Node next;
        Item item;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    /*
    enqueue after last
     */
    public void enqueue(Item item) {
        Node newNode = new Node();
        newNode.item = item;

        if (isEmpty()) {
            newNode.next = newNode;
        } else {
            newNode.next = last.next;
            last.next = newNode;
        }
        last = newNode;
        n++;
    }

    /*
    dequeue last's next which is first
     */
    public Item dequeue() {
        if (isEmpty()) {
            return null;
        }
        Node toRemove = last.next;
        if (n == 1) {
            last = null;
        } else {
            last.next = toRemove.next;
        }
        n--;
        return toRemove.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item> {

        Node current = last;
        int cnt = 0;

        @Override
        public boolean hasNext() {
            return cnt < n;
        }

        @Override
        public Item next() {
            Item item = current.next.item;
            current = current.next;
            cnt++;
            return item;
        }
    }

    public static void main(String[] args) {
        CircularLinkedListQueue<String> queue = new CircularLinkedListQueue<>();
        queue.enqueue("0");
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.enqueue("6");
//        queue.dequeue();
//        queue.dequeue();
//        for (String s : queue) {
//            StdOut.print(s + " ");
//        }
//        StdOut.println();

    }
}
