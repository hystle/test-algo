package ch1.sec3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class LinkedListStack<Item> implements Iterable<Item> {
    private Node first;
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

    public void push(Item item) {
        // store the old first to tmp
        Node tmp = first;

        // create a new node + assign new node's next to old first
        first = new Node();
        first.item = item;
        first.next = tmp;

        n++;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public Item peek() {
        if (first != null) {
            return first.item;
        } else {
            return null;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    public class LinkedListIterator implements Iterator<Item> {

        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item ret = current.item;
            current = current.next;
            return ret;
        }
    }

    /**
     * java -classpath /Users/ziczhou/coursera/algorithm/out/production/algorithm:/Users/ziczhou/coursera/algs4.jar ch1.sec3.LinkedListStack < tobe.txt
     * to be not that or be | 2 left on stack: is to
     *
     * @param args
     */
    public static void main(String[] args) {
        LinkedListStack<String> stack = new LinkedListStack<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                stack.push(item);
            } else if (!stack.isEmpty()) {
                StdOut.print(stack.pop() + " ");
            }
        }
        StdOut.print("| " + stack.size() + " left on stack: ");
        for (String s : stack) {
            StdOut.print(s + " ");
        }
        StdOut.println();
    }
}
