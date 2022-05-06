package ch1.sec3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {

    private Item[] a = (Item[]) new Object[1];
    private int n = 0;

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void push(Item item) {
        if (n == a.length) {
            resize(2 * a.length);
        }
        a[n++] = item;
    }

    public Item pop() {
        Item item = a[--n];
        a[n] = null;    // important line to avoid memory leak
        if (n > 0 && n == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    private void resize(int newLen) {
        Item[] tmp = (Item[]) new Object[newLen];
        for (int i = 0; i < n; i++) {
            tmp[i] = a[i];
        }
        a = tmp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {

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
     * java -classpath /Users/ziczhou/coursera/algorithm/out/production/algorithm:/Users/ziczhou/coursera/algs4.jar ch1.sec3.ResizingArrayStack < tobe.txt
     * to be not that or be | 2 left on stack: is to
     *
     * @param args
     */
    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<>();
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
