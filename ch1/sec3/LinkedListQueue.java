package ch1.sec3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class LinkedListQueue<Item extends Comparable> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int n;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        Node tmp = last;

        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            tmp.next = last;
        }
        n++;
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item> {

        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (Iterator<Item> it = iterator(); it.hasNext(); ) {
            Item item = it.next();
            s += item.toString();
        }
        return s;
    }

    /**
     * 1.3.19
     * provided we have the reference to first node, remove the last node
     */
    public void removeLastNodeUsingRefFirst() {
        // zero node case
        if (first == null) {
            return;
        }
        // 1 node case
        if (first.next == null) {
            first = null;
            return;
        }
        // more than 2 nodes case - move the pointer to 2nd to last
        LinkedListQueue.Node current = first;
        while (current.next != null && current.next.next != null) {
            current = current.next;
        }
        // remove the last node
        current.next = null;
    }

    /**
     * 1.3.20
     * remove the kth node in the linked-list
     * k should be indexed from 0
     */
    public void removeKthNode(int k) {
        // validate input
        if (k < 0 || k >= size()) {
            throw new RuntimeException();
        }
        // empty queue
        if (first == null) {
            return;
        }
        // remove the 1st node case
        if (k == 0) {
            first = first.next;
            return;
        }
        // remove regular node case
        int count = 0;
        Node current = first;
        while (current.next != null && current.next.next != null) {
            if (count + 1 == k) {
                current.next = current.next.next;
                return;
            }
            count++;
            current = current.next;
        }
        // remove the last node case
        if (count + 1 == k) {
            current.next = null;
        }
    }

    /**
     * 1.3.21
     * find if the given val exists in the linked list
     *
     * @param val
     * @return
     */
    public boolean findNodeByValue(String val) {
        Node current = first;
        while (current != null) {
            if (current.item.equals(val)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * 1.3.24
     * remove the node after the given node
     *
     * @param n
     */
    public void removeAfter(Node n) {
        if (n == null || n.next == null) {
            return;
        }
        n.next = n.next.next;
    }

    /**
     * 1.3.25
     * insert node b after node a
     *
     * @param a
     * @param b
     */
    public void insertAfter(Node a, Node b) {
        b.next = a.next;
        a.next = b;
    }

    /**
     * remove all nodes in linked list with item==val
     *
     * @param val
     */
    public void remove(String val) {
        if (first.item.equals(val)) {
            first = first.next;
            return;
        }
        Node prev = first;
        Node curr = first.next;
        while (curr != null) {
            if (curr.item.equals(val)) {
                // remove current
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
    }

    /**
     * 1.3.27
     * get the max key in linked list
     */
    public int max() {
        int maxVal = 0;
        Node curr = first;
        while (curr != null) {
            if (curr.item.compareTo(maxVal) > 0) {
                maxVal = (Integer) curr.item;
            }
            curr = curr.next;
        }
        return maxVal;
    }

    /**
     * 1.3.28
     * get the max key in linked list by recursion
     *
     * @return
     */
    public int maxRecDriver() {
        return maxRecursive(first, 0);
    }

    private int maxRecursive(Node curr, Integer max) {
        // solution1
        //
        // base
        if (curr.next == null) {
            return (Integer) curr.item;
        }
        // recursive
        Integer val = maxRecursive(curr.next, max);
        if (val.compareTo((Integer) curr.item) > 0) {
            max = val;
        } else {
            max = (Integer) curr.item;
        }
        return max;
//        // solution2
//        //
//        // base case
//        if (curr.next == null) {
//            if (curr.item.compareTo(max) > 0) {
//                return (Integer) curr.item;
//            } else {
//                return max;
//            }
//        }
//        // recursive case
//        if (curr.item.compareTo(max) > 0) {
//            max = (Integer) curr.item;
//        }
//        return maxRecursive(curr.next, max);
    }

    /**
     * 1.3.30(2)
     */
    public void reverseDriver() {
        reverse(first);
    }

    private Node reverse(Node first) {
        if (first == null) {
            return null;
        }
        if (first.next == null) {
            return first;
        }
        Node second = first.next;
        Node rest = reverse(second);
        second.next = first;
        first.next = null;
        return rest;
    }

    /**
     * java -classpath /Users/ziczhou/coursera/algorithm/out/production/algorithm:/Users/ziczhou/coursera/algs4.jar ch1.sec3.LinkedListQueue < tobe.txt
     * to be or not to be | 2 left on stack: that is
     *
     * @param args
     */
    public static void main(String[] args) {
        LinkedListQueue<String> queue = new LinkedListQueue<>();
//        while (!StdIn.isEmpty()) {
//            String item = StdIn.readString();
//            if (!item.equals("-")) {
//                queue.enqueue(item);
//            } else if (!queue.isEmpty()) {
//                StdOut.print(queue.dequeue() + " ");
//            }
//        }
//        StdOut.print("| " + queue.size() + " left on stack: ");
//        for (String s : queue) {
//            StdOut.print(s + " ");
//        }
//        StdOut.println();

        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");

//        queue.removeLastNodeUsingRefFirst();
//        queue.removeKthNode(4);
//        queue.remove("a");
//        queue.reverseDriver();

        for (String s : queue) {
            StdOut.print(s + " ");
        }
        StdOut.println();
//        StdOut.println(queue.findNodeByValue("e"));

        LinkedListQueue<Integer> queue2 = new LinkedListQueue<>();
        queue2.enqueue(5);
        queue2.enqueue(2);
        queue2.enqueue(7);
        queue2.enqueue(8);
        queue2.enqueue(3);
        StdOut.println(queue2.max());
        StdOut.println(queue2.maxRecDriver());
    }
}
