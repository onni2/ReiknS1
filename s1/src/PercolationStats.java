
//import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private double[] thresholds;
        private int T;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must be greater than 0");
        }

        this.T = T;
        thresholds = new double[T];

        Stopwatch stopwatch = new Stopwatch(); // Create a stopwatch

        for (int t = 0; t < T; t++) {
            Percolation percolation = new Percolation(N);

            while (!percolation.percolates()) {
                int i = StdRandom.uniform(N);
                int j = StdRandom.uniform(N);

                if (!percolation.isOpen(i, j)) {
                    percolation.open(i, j);
                }
            }

            thresholds[t] = (double) percolation.numberOfOpenSites() / (N * N);
        }

        //double elapsedTime = stopwatch.elapsedTime();
        //System.out.println("Total running time: " + elapsedTime + " seconds");
    }

    public double mean() {
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    public static void main(String[] args) {
        /* StdIn in;
        int N = StdIn.readInt();
        int T = StdIn.readInt();
        System.out.println("n is " + N + " and T is: " + T); */
        int N = 100;
        int T = 100;

        PercolationStats stats = new PercolationStats(N, T);

        System.out.println("mean() = " + stats.mean());
        System.out.println("stddev() = " + stats.stddev());
        
    }
} 