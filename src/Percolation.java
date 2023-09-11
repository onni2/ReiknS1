import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    public boolean isOpen = false;
    public boolean isFull = false;
    private Random random = new Random();

    // Save a random subset of the grid to a text file
    public void saveRandomToFile(String filename, int numCoordinatesToSave) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            writer.println(count); // Write the grid size first
            for (int x = 0; x<count;x++){
                for (int y = 0; y < count; y++){
                    int ran = random.nextInt(2) + 1;
                    if(ran == 1){
                        writer.println(x + " " + y); // Write coordinates and value
                        System.out.println(x + " " + y);
                    }
                }
            }
            
            

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Create an N-by-N grid, with all sites blocked.
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be greater than 0");
        }
        // structure = new QuickFindUF(N);
        grid = new boolean[N][N];
        count = N;
        sink = N * N + 1;
        uf = new WeightedQuickUnionUF(N * N + 2);
        
        int numCoordinatesToSave = 10; // Replace with the number of random coordinates you want to save
        saveRandomToFile("txt.txt", numCoordinatesToSave);
    }

    // Open site (i, j) if it is not open already.
    public void open(int i, int j) {
        if (i < 0 || i >= count || j < 0 || j >= count) {
            throw new IndexOutOfBoundsException("Invalid arguments for open()");
        }
        if (!isOpen(i, j)) {
            grid[i][j] = true;
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


    // Is site (i, j) open?
    public boolean isOpen(int i, int j) {
        return grid[i][j]; 
        
        
    }

    // Is site (i, j) full?
    public boolean isFull(int i, int j) {
        return grid[i][j] && uf.connected(encode(i, j), source);  
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
        return uf.connected(source, sink);
    }

    // An integer ID (1...N) for site (i, j).
    private int encode(int i, int j) {
        int en = count * (i) + j;
        return en;
    }

}