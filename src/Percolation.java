import java.util.Random;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// Models an N-by-N percolation system.
public class Percolation {
    private int source = 0;
    private boolean[][] grid;
    private int count, sink, open;
    private WeightedQuickUnionUF uf;

    // Create an N-by-N grid, with all sites blocked.
    public Percolation(int N) {
        // structure = new QuickFindUF(N);
        grid = new boolean[N][N];
        count = N;
        sink = N * N + 1;
        uf = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                grid[i][j] = false;
            }
        }
        

    }

    // Open site (i, j) if it is not open already.
    public void open(int i, int j) {
        grid[i][j] = true;

        
    }


    // Is site (i, j) open?
    public boolean isOpen(int i, int j) {
        if (grid[i][j] == true) { return grid[i][j] = true; }
        else { return grid[i][j] = false; }
        
        
    }

    // Is site (i, j) full?
    public boolean isFull(int i, int j) {
        if (grid[i][j] = true) {

            return true;

        }else { return false; }
    }

    // Number of open sites.
    public int numberOfOpenSites() {
        open = 0;
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                if (grid[i][j]) {
                    open++;
                }
            }
        }
        System.out.println("number of open sites: " + open);
        return open;
    }

    // Does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                if (i == 0) {
                uf.union(encode(i, j), source);
                }
                if (i == count - 1) {
                    uf.union(encode(i, j), sink);
                }
                if (j > 0 && isOpen(i, j - 1)) {
                    uf.union(encode(i, j), encode(i, j - 1));
                }
                if (i > 0 && isOpen(i - 1, j)) {
                    uf.union(encode(i, j), encode(i - 1, j));
                }
                if (i < count - 1 && isOpen(i + 1, j)) {
                    uf.union(encode(i, j), encode(i + 1, j));
                }
                
                if (j < count - 1 && isOpen(i, j + 1)) {
                    uf.union(encode(i, j), encode(i, j + 1));
                }
            }
        }
        return uf.connected(source, sink);
    }

    // An integer ID (1...N) for site (i, j).
    private int encode(int i, int j) {
        int en = count * (i) + j;
        return en;
    }

    
    public static void main(String[] args) {
        
    }
}