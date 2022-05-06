package ch2;

import org.junit.Test;

public class TestClient {

    @Test
    public void testSelectionSort() {
        String[] arr = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        AbstractSort s = new SelectionSort();
        s.sort(arr);
        assert AbstractSort.isSorted(arr);
        AbstractSort.show(arr);
    }

    @Test
    public void testInsertionSort() {
        String[] arr = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        AbstractSort s = new InsertionSort();
        s.sort(arr);
        assert AbstractSort.isSorted(arr);
        AbstractSort.show(arr);
    }

    @Test
    public void testShellSort() {
        String[] arr = {"S", "H", "E", "L", "L", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        AbstractSort s = new ShellSort();
        s.sort(arr);
        assert AbstractSort.isSorted(arr);
        AbstractSort.show(arr);
    }

    @Test
    public void testMergeSortTopDown() {
        AbstractMergeSort s = new MergeSortTopDown();
        String[] arr = {"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        s.sort(arr);
        assert AbstractSort.isSorted(arr);
        AbstractSort.show(arr);
    }

    @Test
    public void testMergeSortBottomUp() {
        AbstractMergeSort s = new MergeSortBottomUp();
        String[] arr = {"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        s.sort(arr);
        assert AbstractSort.isSorted(arr);
        AbstractSort.show(arr);
    }

    @Test
    public void testQuickSort() {
        AbstractSort s = new QuickSort();
//        String[] arr = {"Q", "U", "I", "C", "K", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        String[] arr = {"Q", "A", "A", "A", "E"};

        s.sort(arr);
        assert AbstractSort.isSorted(arr);
        AbstractSort.show(arr);
    }

    @Test
    public void testQuickSort3Way() {
        AbstractSort s = new QuickSort3Way();
//        String[] arr = {"Q", "U", "I", "C", "K", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        String[] arr = {"Q", "A", "A", "A", "E"};

        s.sort(arr);
        assert AbstractSort.isSorted(arr);
        AbstractSort.show(arr);
    }

    @Test
    public void testHeapSort() {
        AbstractSort s = new HeapSort();
//        String[] arr = {"Q", "U", "I", "C", "K", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        String[] arr = {"Q", "A", "A", "A", "E"};

        s.sort(arr);

        AbstractSort.show(arr);
        assert AbstractSort.isSorted(arr);
    }
}
