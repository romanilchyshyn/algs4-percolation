/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        double[] trialsData = new double[trials];
        for (int i = 0; i < trials; i++) {
            trialsData[i] = executeTrial(n);
        }

        this.mean = StdStats.mean(trialsData);
        this.stddev = StdStats.stddev(trialsData);
        this.confidenceLo = mean - (1.96 * stddev / Math.sqrt(trials));
        this.confidenceHi = mean + (1.96 * stddev / Math.sqrt(trials));
    }

    double executeTrial(int n) {
        Percolation p = new Percolation(n);
        while (!p.percolates()) {
            p.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
        }

        double nOpen = p.numberOfOpenSites();
        double nsqr = n * n;

        return nOpen / nsqr;
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHi;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats s = new PercolationStats(n, t);

        System.out.printf("mean                    = %f\n", s.mean());
        System.out.printf("stddev                  = %f\n", s.stddev());
        System.out.printf("95%% confidence interval = [%f, %f]\n", s.confidenceLo(), s.confidenceHi());
    }
}
