import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import java.util.Arrays;
import java.util.Comparator;

public class Brute {
    private final Point[] points;
    private final Point[][] lineSegments;
    private int numberOfSegments;

    public Brute(Point[] points) {
        this.points = Arrays.copyOf(points, points.length);
        this.lineSegments = new Point[points.length * points.length][4];
        this.numberOfSegments = 0;
        generateLineSegments();
    }

    private void generateLineSegments() {
        int n = points.length;

        // Sort the points based on their natural order
        Arrays.sort(points);
        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    double slope1 = points[i].slopeTo(points[j]);
                    double slope2 = points[i].slopeTo(points[k]);
                    // Check if the slopes are equal
                    if (Double.compare(slope1, slope2) == 0) {
                        for (int m = k + 1; m < n; m++) {
                            double slope3 = points[i].slopeTo(points[m]);

                            // Check if the slopes are equal
                            if (Double.compare(slope1, slope3) == 0) {
                                Point[] segment = { points[i], points[j], points[k], points[m] };
                                lineSegments[numberOfSegments++] = segment;
                            }
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return numberOfSegments;

    }

    public Iterable<Iterable<Point>> segments() {
        return new SegmentIterable();
    }

    private class SegmentIterable implements Iterable<Iterable<Point>> {
        @Override

        public java.util.Iterator<Iterable<Point>> iterator() {
            return new SegmentIterator();
        }
    }

    private class SegmentIterator implements java.util.Iterator<Iterable<Point>> {
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

    public static void main(String[] args) {

        // Check if the correct number of command-line arguments is provided
        if (args.length != 1) {
            System.out.println("Usage: java Brute <input-file>");
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

        Brute brute = new Brute(points);
        // Sort the segments based on the lexicographical order of their first points
        Arrays.sort(brute.lineSegments, 0, brute.numberOfSegments, new Comparator<Point[]>() {

            @Override
            public int compare(Point[] segment1, Point[] segment2) {
                Point firstPoint1 = segment1[0];
                Point firstPoint2 = segment2[0];
                return firstPoint1.compareTo(firstPoint2);
            }

        });

        // Use algs4 Out to print the segments in the required format
        Out out = new Out();
        for (int i = 0; i < brute.numberOfSegments; i++) {
            Point[] segment = brute.lineSegments[i];
            for (int j = 0; j < segment.length; j++) {
                out.print(segment[j]);
                if (j < segment.length - 1) {
                    out.print(", ");
                }
            }
            out.println();
        }

    }

}
