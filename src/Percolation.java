import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.awt.*;    
import javax.swing.*;  
public class Percolation {
    public Percolation(int N) // create N-by-N grid, with all sites initially blocked
    {
        JFrame frameObj;
        frameObj = new JFrame();    
        

        for (int i = 0; i < N*N; i++){
            JButton btn1 = new JButton("0"); 
            frameObj.add(btn1);  
        }
        
         
        
            
        // adding buttons to the frame  
        // since, we are using the parameterless constructor, therfore;   
        // the number of columns is equal to the number of buttons we   
        // are adding to the frame. The row count remains one.  
          
        
        // setting the grid layout using the parameterless constructor    
        frameObj.setLayout(new GridLayout(N, N));    
        
        
        frameObj.setSize(300, 300);    
        frameObj.setVisible(true);    
    }
    public void open(int row, int col) // open the site (row, col) if it is not open already
    {

    }
    public boolean isOpen(int row, int col) // is the site (row, col) open?
    {
        return false;
    }
    public boolean isFull(int row, int col) // is the site (row, col) full?
    {
        return false;
    }
    public int numberOfOpenSites() // number of open sites
    {
        return 0;
    }
    public boolean percolates() // does the system percolate?
    {
        return false;
    }
    public static void main(String[] args) // unit testing (required)
    {
        new Percolation(5);
    }
}































/* import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n; // Grid size
    private boolean[][] grid; // Grid to track site openness
    private WeightedQuickUnionUF uf; // Union-Find data structure
    private int topVirtualSite; // Virtual top site
    private int bottomVirtualSite; // Virtual bottom site
    private int openSites; // Number of open sites

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Grid size N must be greater than 0");
        }
        n = N;
        grid = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2); // +2 for virtual top and bottom sites
        topVirtualSite = N * N; // Virtual top site index
        bottomVirtualSite = N * N + 1; // Virtual bottom site index
        openSites = 0;
    }

    public void open(int row, int col) {
        validateIndices(row, col);

        if (!isOpen(row, col)) {
            grid[row][col] = true;
            openSites++;

            int index = row * n + col; // Convert 2D coordinates to 1D index

            if (row == 0) {
                uf.union(index, topVirtualSite);
            }
            if (row == n - 1) {
                uf.union(index, bottomVirtualSite);
            }

            // Connect with adjacent open sites
            int[] dx = { -1, 1, 0, 0 };
            int[] dy = { 0, 0, -1, 1 };
            for (int i = 0; i < 4; i++) {
                int newRow = row + dx[i];
                int newCol = col + dy[i];
                if (isValid(newRow, newCol) && isOpen(newRow, newCol)) {
                    int newIndex = newRow * n + newCol;
                    uf.union(index, newIndex);
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        validateIndices(row, col);
        int index = row * n + col; // Convert 2D coordinates to 1D index
        return uf.connected(index, topVirtualSite);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return uf.connected(topVirtualSite, bottomVirtualSite);
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }

    private void validateIndices(int row, int col) {
        if (!isValid(row, col)) {
            throw new IndexOutOfBoundsException("Row or column index out of bounds");
        }
    }

    public static void main(String[] args) {
        int gridSize = 5; // Change this value to test different grid sizes
        Percolation percolation = new Percolation(gridSize);
    
        // Open some sites for testing
        percolation.open(0, 0);
        percolation.open(1, 0);
        percolation.open(1, 1);
        percolation.open(2, 2);
        percolation.open(2, 3);
        percolation.open(3, 3);
    
        System.out.println("Is site (0, 0) open? " + percolation.isOpen(0, 0)); // Should be true
        System.out.println("Is site (3, 3) full? " + percolation.isFull(3, 3)); // Should be false
        System.out.println("Number of open sites: " + percolation.numberOfOpenSites()); // Should be 6
        System.out.println("Does the system percolate? " + percolation.percolates()); // Depends on your input
    
        // Test out-of-bounds exception handling (uncomment these lines to test)
        // percolation.open(-1, 0); // Should throw IndexOutOfBoundsException
        // percolation.isOpen(0, gridSize); // Should throw IndexOutOfBoundsException
    }
    
}
 */