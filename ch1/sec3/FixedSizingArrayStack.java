package ch1.sec3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class FixedSizingArrayStack<Item> implements Iterable<Item> {
    private Item[] a;
    private int n;
    private int capacity;

    public FixedSizingArrayStack(int capacity) {
        this.capacity = capacity;
        a = (Item[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean isFull() {
        return n == capacity;
    }

    public int size() {
        return n;
    }

    public void push(Item item) {
        if (!isFull()) {
            // in the underlying array, the items are still store in order
            a[n++] = item;
        }
    }

    public Item pop() {
        // pop from the end of array
        return a[--n];
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {

        // need a local variable so actual count n does not change
        private int i = n - 1;

        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        @Override
        public Item next() {
            return a[i--];
        }
    }

    /**
     * java -classpath /Users/ziczhou/coursera/algorithm/out/production/algorithm:/Users/ziczhou/coursera/algs4.jar ch1.sec3.FixedSizingArrayStack < tobe.txt
     * to be not that or be | 2 left on stack: is to
     *
     * @param args
     */
    public static void main(String[] args) {
        FixedSizingArrayStack<String> stack = new FixedSizingArrayStack<>(100);
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
