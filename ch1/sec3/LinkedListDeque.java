package ch1.sec3;

import java.util.Iterator;

public class LinkedListDeque<Item extends Comparable> implements Iterable<Item> {

    DoubleNode head;
    DoubleNode tail;
    int size;

    private class DoubleNode {
        DoubleNode prev;
        DoubleNode next;
        Item item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void pushLeft(Item item) {
        // store the old head reference
        DoubleNode tmp = head;

        // create new node
        head = new DoubleNode();
        head.item = item;
        head.prev = null;
        head.next = tmp;

        // recreate prev link for old head towards new head
        // otherwise the old head's prev will point to null
        if (tmp != null) {
            tmp.prev = head;
        }

        // initialize tail while we are dealing with head
        if (tail == null) {
            tail = head;
        }

        // all steps done - increase size now
        size++;
    }

    public void pushRight(Item item) {
        DoubleNode tmp = tail;

        tail = new DoubleNode();
        tail.item = item;
        tail.prev = tmp;
        tail.next = null;
        if (tmp != null) {
            tmp.next = tail;
        }

        if (head == null) {
            head = tail;
        }
        size++;
    }

    public Item popLeft() {
        if (head == null) {
            throw new RuntimeException();
        }
        // get the item to return
        Item item = head.item;

        // discard the current head and move to next
        head = head.next;

        // head's prev should set to null after moving
        // note null check when this is the last node
        if (head != null) {
            head.prev = null;
        }

        // all good - decrease size now
        size--;
        return item;
    }

    public Item popRight() {
        if (tail == null) {
            throw new RuntimeException();
        }
        Item item = tail.item;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        }

        size--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            DoubleNode curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public Item next() {
                Item item = curr.item;
                curr = curr.next;
                return item;
            }
        };
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.pushLeft(2);
        deque.pushLeft(1);
        deque.pushRight(3);
        deque.pushRight(4);

        for (Integer i : deque) {
            System.out.println(i);
        }

        deque.popRight();
        deque.popLeft();
        deque.popLeft();
        deque.popRight();

        for (Integer i : deque) {
            System.out.println(i);
        }
    }
}
