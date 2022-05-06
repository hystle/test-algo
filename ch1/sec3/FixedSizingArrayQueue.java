package ch1.sec3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class FixedSizingArrayQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int tail;
    private int head;
    private int capacity;

    public FixedSizingArrayQueue(int capacity) {
        this.capacity = capacity;
        a = (Item[]) new Object[capacity];
        tail = 0;
        head = 0;
    }

    public void enqueue(Item item) {
        if (!isFull()) {
            a[tail++] = item;
        }
    }

    public Item dequeue() {
        Item item = a[head];
        a[head++] = null;    // important line to avoid memory leak
        return item;
    }

    public boolean isFull() {
        return tail == capacity;
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

            private int i = head;

            @Override
            public boolean hasNext() {
                return tail > i;
            }

            @Override
            public Item next() {
                return a[i++];
            }
        };
    }

    public static void main(String[] args) {
        FixedSizingArrayQueue<String> queue = new FixedSizingArrayQueue<>(5);
//        while (!StdIn.isEmpty()) {
//            String item = StdIn.readString();
//            if (!item.equals("-")) {
//                queue.enqueue(item);
//            } else if (!queue.isEmpty()) {
//                StdOut.print(queue.dequeue() + " ");
//            }
//        }
//        StdOut.print("| " + queue.size() + " left on queue: ");
//        for (String s : queue) {
//            StdOut.print(s + " ");
//        }
//        StdOut.println();

        StdOut.println(queue.isEmpty());
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");
        StdOut.println(queue.isFull());
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        for (String s : queue) {
            StdOut.print(s + " ");
        }
        queue.dequeue();
        queue.dequeue();
        StdOut.println(queue.isEmpty());
    }
}
