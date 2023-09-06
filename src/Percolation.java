import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[] parent; // Store parent links
    private int[] size;   // Store size of each component
    private boolean[] openSites; // Store open status of sites
    private int gridSize; // Number of sites in the grid
    private int openCount; // Number of open sites

    // Create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be greater than 0");
        }
        gridSize = N;
        int totalSites = N * N;
        parent = new int[totalSites + 2]; // Extra 2 for virtual top and bottom
        size = new int[totalSites + 2];
        openSites = new boolean[totalSites];
        openCount = 0;

        // Initialize parent and size arrays
        for (int i = 0; i <= totalSites + 1; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        // Connect virtual top and bottom to the first and last rows
        for (int col = 1; col <= N; col++) {
            union(encode(1, col), 0); // Connect to virtual top (0)
            union(encode(N, col), totalSites + 1); // Connect to virtual bottom (totalSites + 1)
        }
    }

    // Open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateIndices(row, col);
        int siteIndex = encode(row, col);

        if (!openSites[siteIndex]) {
            openSites[siteIndex] = true;
            openCount++;

            int[] neighbors = {encode(row - 1, col), encode(row + 1, col), encode(row, col - 1), encode(row, col + 1)};

            for (int neighbor : neighbors) {
                if (neighbor != -1 && isOpen(neighbor / gridSize, neighbor % gridSize)) {
                    union(siteIndex, neighbor);
                }
            }
        }
    }

    // Is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        return openSites[encode(row, col)];
    }

    // Is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateIndices(row, col);
        return isOpen(row, col) && connected(encode(row, col), 0);
    }

    // Number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // Does the system percolate?
    public boolean percolates() {
        return connected(0, gridSize * gridSize + 1);
    }

    // Validate row and col indices
    private void validateIndices(int row, int col) {
        if (row < 0 || row >= gridSize || col < 0 || col >= gridSize) {
            throw new IndexOutOfBoundsException("Row or column index is out of bounds");
        }
    }

    // Encode 2D coordinates to 1D union-find representation
    private int encode(int row, int col) {
        if (row < 0 || row >= gridSize || col < 0 || col >= gridSize) {
            return -1; // Return -1 for out-of-bounds sites
        }
        return row * gridSize + col + 1;
    }

    // Weighted Quick Union-Find operations
    private int root(int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]]; // Path compression
            i = parent[i];
        }
        return i;
    }

    private boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    private void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        if (rootP == rootQ) return;

        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
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