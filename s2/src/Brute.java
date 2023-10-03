import edu.princeton.cs.algs4.In;

public class Brute {
    private final Point[][] lineSegments; // Array to store line segments
    private int numberOfSegments; // Variable to keep track of the number of segments

    public Brute(Point[] points) {
        int n = points.length;
        lineSegments = new Point[n][4]; // Each segment has 4 points
        numberOfSegments = 0;

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int m = k + 1; m < n; m++) {
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) &&
                                points[i].slopeTo(points[j]) == points[i].slopeTo(points[m])) {
                            Point[] segment = { points[i], points[j], points[k], points[m] };
                            sort(segment); // Sort the points within the segment
                            lineSegments[numberOfSegments++] = segment;
                        }
                    }
                }
            }
        }
    }

    // Custom sorting function for points within a segment
    private void sort(Point[] segment) {
        int n = segment.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (segment[i].compareTo(segment[j]) > 0) {
                    Point temp = segment[i];
                    segment[i] = segment[j];
                    segment[j] = temp;
                }
            }
        }
    }

    public int numberOfSegments() {
        return numberOfSegments;
    }

    public Point[][] segments() {
        return lineSegments;
    }

    public static void main(String[] args) {
        // Check if the correct number of command-line arguments is provided
        if (args.length != 1) {
            System.out.println("Usage: java Brute <input-file>");
            return;
        }

        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];

        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        Brute brute = new Brute(points);

        // Print the segments
        for (int i = 0; i < brute.numberOfSegments(); i++) {
            Point[] segment = brute.segments()[i];
            for (int j = 0; j < segment.length; j++) {
                System.out.print(segment[j] + ", ");
            }
            System.out.println();
        }
    }
}
