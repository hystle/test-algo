package ch1.sec3;

import java.util.Iterator;

public class ResizingArrayDeque<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1];
    private int head = 0;
    private int tail = 0;

    public boolean isEmpty() {
        return head == tail;
    }

    public int size() {
        return tail - head;
    }

    /**
     * Climax of the exercise is here
     *
     * @param newLen
     */
    private void resize(int newLen) {
        Item[] tmp = (Item[]) new Object[newLen];

        int newStart = (newLen - size()) / 2;
        for (int p = 0; p < size(); p++) {
            tmp[newStart + p] = a[head + p];
        }

        // update tail first as size() will change
        tail = newStart + size();
        head = newStart;

        a = tmp;
    }

    public void pushLeft(Item item) {
        if (head == 0) {
            resize(a.length * 2);
        }
        a[--head] = item;
    }

    public void pushRight(Item item) {
        if (tail == a.length) {
            resize(a.length * 2);
        }
        a[tail++] = item;
    }

    public Item popLeft() {
        Item item = a[head++];
        if (!isEmpty() && size() < a.length / 4) {
            resize(a.length / 2);
        }
        return item;

    }

    public Item popRight() {
        Item item = a[--tail];
        a[tail] = null;

        if (!isEmpty() && size() < a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            int curr = head;

            @Override
            public boolean hasNext() {
                return curr < tail;
            }

            @Override
            public Item next() {
                return a[curr++];
            }
        };
    }

    public static void main(String[] args) {
        ResizingArrayDeque<Integer> deque = new ResizingArrayDeque<>();
        deque.pushRight(1);
        deque.pushRight(2);
        deque.pushRight(3);
        deque.pushRight(4);
        deque.pushRight(5);
        deque.popRight();
        deque.popRight();
        deque.popRight();
        deque.popRight();
        deque.popRight();
        deque.pushLeft(1);
        deque.pushLeft(2);
        deque.pushLeft(3);
        deque.pushLeft(4);
        deque.pushLeft(5);

//        deque.pushRight(1);
//        deque.pushRight(2);
//        deque.pushRight(3);
//        deque.pushLeft(1);
//        deque.pushLeft(2);
//        deque.pushLeft(3);
//        deque.pushRight(4);
//        deque.pushRight(5);
//        deque.pushRight(6);
//        deque.pushLeft(4);
//        deque.popRight();
//        deque.popRight();
//        deque.popRight();
//        deque.popRight();
//        deque.popLeft();
//        deque.popLeft();
//        deque.popRight();
//        deque.popRight();
//        deque.popLeft();
//        deque.popLeft();

        for (Integer i : deque) {
            System.out.println(i);
        }
    }
}
