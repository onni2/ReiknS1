import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import java.util.Iterator;

public class Fast2 {
    private final Point[][] lineSegments; // Array to store line segments
    private int numberOfSegments; // Variable to keep track of the number of segments

    // Constructor to find line segments
    public Fast2(Point[] points) {
        int n = points.length;
        lineSegments = new Point[n][n]; // Assuming the maximum possible line segments would not exceed 'n'
        numberOfSegments = 0;

        // Iterate through each point as the reference point
        for (int i = 0; i < n; i++) {
            Point[] sortedPoints = Arrays.copyOf(points, n);
            Arrays.sort(sortedPoints, points[i].slopeOrder());

            // Iterate through sorted points and find collinear segments
            for (int j = 1; j < n - 2; j++) {
                int k = j + 1;
                while (k < n && points[i].slopeTo(sortedPoints[j]) == points[i].slopeTo(sortedPoints[k])) {
                    k++;
                }

                // Check if 3 or more points have the same slope and it's not a subsegment
                if (k - j >= 3 && !isSubsegment(points[i], sortedPoints, j, k)) {
                    Point[] segment = new Point[k - j + 1];
                    segment[0] = points[i];
                    for (int l = j; l < k; l++) {
                        segment[l - j + 1] = sortedPoints[l];
                    }
                    Arrays.sort(segment);
                    lineSegments[numberOfSegments++] = segment;
                }
                j = k - 1;
            }
        }
    }

    // Check if the points form a subsegment of an existing segment
    private boolean isSubsegment(Point origin, Point[] sortedPoints, int start, int end) {
        for (int i = 0; i < numberOfSegments; i++) {
            Point[] segment = lineSegments[i];
            if (segment.length < 5) {
                continue; // Ignore shorter segments
            }

            boolean allPointsExist = true;
            for (int j = start; j < end; j++) {
                boolean pointExists = false;
                for (Point pt : segment) {
                    if (pt.equals(sortedPoints[j]) || pt.equals(origin)) {
                        pointExists = true;
                        break;
                    }
                }
                if (!pointExists) {
                    allPointsExist = false;
                    break;
                }
            }

            if (allPointsExist) {
                return true;
            }
        }
        return false;
    }

    // Get the total number of line segments found
    public int numberOfSegments() {
        return numberOfSegments;
    }

    // Get an iterable for line segments
    public Iterable<Iterable<Point>> segments() {
        return new SegmentIterable();
    }

    // Iterable for line segments
    private class SegmentIterable implements Iterable<Iterable<Point>> {
        @Override
        public Iterator<Iterable<Point>> iterator() {
            return new SegmentIterator();
        }
    }

    // Iterator for line segments
    private class SegmentIterator implements Iterator<Iterable<Point>> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < numberOfSegments;
        }

        @Override
        public Iterable<Point> next() {
            return Arrays.asList(lineSegments[currentIndex++]);
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Fast2 <input-file>");
            return;
        }

        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];

        // Read input points
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        Fast2 fast2 = new Fast2(points);

        // Print the segments
        for (Iterable<Point> segment : fast2.segments()) {
            Iterator<Point> iterator = segment.iterator();
            while (iterator.hasNext()) {
                Point point = iterator.next();
                System.out.print("(" + point.x + ", " + point.y + ")");
                if (iterator.hasNext()) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }
}
