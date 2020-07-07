/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class Percolation {

    private int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        // WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        assert(false);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        assert(false);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        assert(false);
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        assert(false);
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        assert(false);
        return -1;
    }

    // does the system percolate?
    public boolean percolates() {
        assert(false);
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Number 2");
    }
}
