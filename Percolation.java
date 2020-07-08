/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int n;
    private final WeightedQuickUnionUF ufWithVirtualTopBottom;
    private final WeightedQuickUnionUF ufWithVirtualTop;
    private final int virtualTopSite;
    private final int virtualBottomSite;
    private boolean[] sites;
    private int numberOfOpenSites = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        int sitesCount = n * n;
        this.ufWithVirtualTopBottom = new WeightedQuickUnionUF(sitesCount + 2); // 2 virtual sites
        this.ufWithVirtualTop = new WeightedQuickUnionUF(sitesCount + 1); // 1 virtual sites
        this.virtualTopSite = sitesCount;
        this.virtualBottomSite = sitesCount + 1;
        this.sites = new boolean[sitesCount];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }

        int siteIndex = toSiteIndex(row, col);
        sites[siteIndex] = true;
        numberOfOpenSites++;

        if (isTopRow(row)) {
            ufWithVirtualTopBottom.union(siteIndex, virtualTopSite);
            ufWithVirtualTop.union(siteIndex, virtualTopSite);
        }
        if (isBottomRow(row)) {
            ufWithVirtualTopBottom.union(siteIndex, virtualBottomSite);
        }

        connectWithNeighbourIfNeeded(siteIndex, row - 1, col);
        connectWithNeighbourIfNeeded(siteIndex, row + 1, col);
        connectWithNeighbourIfNeeded(siteIndex, row, col - 1);
        connectWithNeighbourIfNeeded(siteIndex, row, col + 1);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int siteIndex = toSiteIndex(row, col);
        return sites[siteIndex];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int siteIndex = toSiteIndex(row, col);
        return ufWithVirtualTop.find(virtualTopSite) == ufWithVirtualTop.find(siteIndex);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return ufWithVirtualTopBottom.find(virtualTopSite) == ufWithVirtualTopBottom.find(virtualBottomSite);
    }

    private int toSiteIndex(int row, int col) {
        if (isValidSite(row, col)) {
            return (row - 1) * this.n + (col - 1);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean isTopRow(int row) {
        return row == 1;
    }

    private boolean isBottomRow(int row) {
        return row == this.n;
    }

    private boolean isValidSite(int row, int col) {
        return 1 <= row && row <= n && 1 <= col && col <= n;
    }

    private void connectWithNeighbourIfNeeded(int siteIndex, int row, int col) {
        if (!isValidSite(row, col)) {
            return;
        }

        if (!isOpen(row, col)) {
            return;
        }

        int neighbourSiteIndex = toSiteIndex(row, col);
        ufWithVirtualTopBottom.union(siteIndex, neighbourSiteIndex);
        ufWithVirtualTop.union(siteIndex, neighbourSiteIndex);
    }

    public static void main(String[] args) {
        System.out.println("Number 2");
    }
}
