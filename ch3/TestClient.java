package ch3;

import org.junit.Test;

public class TestClient {

    @Test
    public void testSequentialSearchUnorderedST() {
        SequentialSearchUnorderedST<Integer, String> ss = new SequentialSearchUnorderedST<>();
        ss.put(1, "A");
//        ss.put(2, "B");
//        ss.put(3, "C");
//        ss.put(4, "D");

        System.out.println(ss.size());

        System.out.println(ss.contains(4));

        ss.delete(1);

        System.out.println(ss.contains(1));

        System.out.println(ss.isEmpty());

        for (Integer k : ss.keys()) {
            System.out.println(ss.get(k));
        }
    }

    @Test
    public void testBinarySearchOrderedST() {
        BinarySearchOrderedST<String, Integer> bs = new BinarySearchOrderedST<>(15);
        bs.put("S", 0);
        bs.put("E", 1);
        bs.put("A", 2);
        bs.put("R", 3);
        bs.put("C", 4);
        bs.put("H", 5);
        bs.put("E", 6);
        bs.put("X", 7);
        bs.put("A", 8);
        bs.put("M", 9);
        bs.put("P", 10);
        bs.put("L", 11);
        bs.put("E", 12);

        System.out.println(bs.rank("S"));
        System.out.println(bs.ceiling("P"));
        System.out.println(bs.floor("P"));

        for (String k : bs.keys()) {
            System.out.print(k + ":" + bs.get(k) + " ");
        }
    }

    @Test
    public void testBST() {
        BST<String, Integer> bst = new BST<>();
        bst.put("S", 0);
        bst.put("E", 1);
        bst.put("A", 2);
        bst.put("R", 3);
        bst.put("C", 4);
        bst.put("H", 5);
        bst.put("E", 6);
        bst.put("X", 7);
        bst.put("A", 8);
        bst.put("M", 9);
        bst.put("P", 10);
        bst.put("L", 11);
        bst.put("E", 12);

        System.out.println(bst.get("R"));

        System.out.println(bst.floor("G"));

        System.out.println(bst.select(3));

        for (String k : bst.keys("F", "T")) {
            System.out.print(k + ":" + bst.get(k) + " ");
        }
        System.out.println();

        System.out.println(bst.contains("H"));

        for (String k : bst.keys()) {
            System.out.print(k + ":" + bst.get(k) + " ");
        }

        System.out.println();

        bst.deleteMax();

        for (String k : bst.keys()) {
            System.out.print(k + ":" + bst.get(k) + " ");
        }
        System.out.println();
    }

    @Test
    public void testRedBlackBST() {
        RedBlackBST<String, Integer> rbbst = new RedBlackBST<>();
        rbbst.put("S", 0);
        rbbst.put("E", 1);
        rbbst.put("A", 2);
        rbbst.put("R", 3);
        rbbst.put("C", 4);
        rbbst.put("H", 5);
        rbbst.put("E", 6);
        rbbst.put("X", 7);
        rbbst.put("A", 8);
        rbbst.put("M", 9);
        rbbst.put("P", 10);
        rbbst.put("L", 11);
        rbbst.put("E", 12);

        for (String k : rbbst.keys()) {
            System.out.print(k + " ");
        }
    }
}
