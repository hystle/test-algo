package ch1.sec3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ResizingArrayQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int tail;
    private int head;

    public ResizingArrayQueue() {
        a = (Item[]) new Object[1];
        tail = 0;
        head = 0;
    }

    public void enqueue(Item item) {
        if (tail == a.length) {
            resize(2 * a.length);
        }
        a[tail++] = item;
    }

    public Item dequeue() {
        Item item = a[head];
        a[head++] = null;    // important line to avoid memory leak
        if (tail - head <= a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    private void resize(int newSize) {
        Item[] tmp = (Item[]) new Object[newSize];
        for (int p = head; p < tail; p++) {
            tmp[p - head] = a[p];
        }
        tail = tail - head;
        head = 0;
        a = tmp;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public int size() {
        return tail - head;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private int p = head;

            @Override
            public boolean hasNext() {
                return p < tail;
            }

            @Override
            public Item next() {
                return a[p++];
            }
        };
    }

    public static void main(String[] args) {
        ResizingArrayQueue<String> queue = new ResizingArrayQueue<>();
        StdOut.println(queue.isEmpty());
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");

        for (String s : queue) {
            StdOut.print(s + " ");
        }

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        StdOut.println(queue.size());
        for (String s : queue) {
            StdOut.print(s + " ");
        }
        queue.dequeue();
        queue.dequeue();
        StdOut.println(queue.isEmpty());
    }
}
