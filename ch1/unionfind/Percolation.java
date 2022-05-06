package ch1.unionfind;

/*-
 * Guidelines:
 * - Must leverage WeightedQuickUnionUF
 * - Each of the methods (except the constructor) must use a constant number of union–find operations.
 */
public class Percolation {
    private int gridSize;
    private int[] openSites; // 0 is blocked; 1 is opened
    private WeightedQuickUnionUF uf;

    /*-
     * creates n-by-n grid, with all sites initially blocked
     */
    public Percolation(int n) {
        gridSize = n;
        openSites = new int[n * n];
        uf = new WeightedQuickUnionUF(n * n);
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= n; i++) {
                int idx = xyTo1D(j, i);
                openSites[idx] = 0;
            }
        }
    }

    /*-
     * convert from 2D(row, col) to 1D union-find object index
     * leverage the grid size
     */
    private int xyTo1D(int row, int col) {
        return gridSize * (row - 1) + (col - 1);
    }

    /*-
     * validate the invalid indices
     */
    private void validates(int row, int col) throws IllegalArgumentException {
        if (row < 1 || col < 1 || row > gridSize || col > gridSize) {
            throw new IllegalArgumentException();
        }
    }

    /*-
     * opens the site (row, col) if it is not open already
     */
    public void open(int row, int col) throws IllegalArgumentException {
        // 1. validate the indices of the site it receives;
        // throw exception if invalid
        validates(row, col);

        // 2. mark the site as open
        int idx = xyTo1D(row, col);
        openSites[idx] = 1;

        // 3. link the site with its open neighbors
        // up
        linkOpenNeighborSite(row - 1, col, idx);
        // down
        linkOpenNeighborSite(row + 1, col, idx);
        // left
        linkOpenNeighborSite(row, col - 1, idx);
        // right
        linkOpenNeighborSite(row, col + 1, idx);
    }

    private void linkOpenNeighborSite(int row, int col, int currSite) {
        try {
            validates(row, col);
            if (isOpen(row, col)) {
                uf.union(xyTo1D(row, col), currSite);
            }
        } catch (IllegalArgumentException e) {
            // do nothing
        }
    }

    /*-
     * is the site (row, col) open?
     */
    public boolean isOpen(int row, int col) throws IllegalArgumentException {
        return openSites[xyTo1D(row, col)] == 1;
    }

    /*-
     * is the site (row, col) full?
     */
    public boolean isFull(int row, int col) throws IllegalArgumentException {
        return openSites[xyTo1D(row, col)] == 0;
    }

    /*-
     * returns the number of open sites
     */
    public int numberOfOpenSites() {
        int sum = 0;
        for (int n : openSites) {
            sum += n;
        }
        return sum;
    }

    /*-
     * does the system percolates?
     */
    public boolean percolates() {
        for (int i = 1; i <= gridSize; i++) {
            if (isFull(1, i)) {
                continue;
            }
            for (int j = 1; j <= gridSize; j++) {
                if (isFull(gridSize, j)) {
                    continue;
                }
                if (uf.connected(xyTo1D(1, i), xyTo1D(gridSize, j))) {
                    return true;
                }
            }
        }
        return false;
    }

    /*-
     * test client
     */
    public static void main(String[] args) {
        // initialize
        int N = 5;
        Percolation p = new Percolation(N);
        // open sites
        p.open(1, 1);
        p.open(1, 2);
        p.open(1, 3);
        p.open(1, 4);
        p.open(2, 4);
        p.open(3, 4);
        p.open(3, 3);
        p.open(3, 2);
        p.open(3, 1);
        p.open(4, 1);
        p.open(5, 1);
        // print grid & outcome
        for (int j = 1; j <= N; j++) {
            for (int i = 1; i <= N; i++) {
                System.out.print(p.openSites[p.xyTo1D(j, i)] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("====");
        System.out.println(p.percolates());
    }
}
