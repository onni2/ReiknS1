import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import java.util.Arrays;

public class Fast {
    private final Queue<Queue<Point>> segments = new Queue<>();

    public Fast(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("Array is null");

        Arrays.sort(points); // Sort the points array

        for (int p = 0; p < points.length; p++) {
            Point[] pointsCopy = Arrays.copyOf(points, points.length);

            // Sort pointsCopy based on slope order
            Arrays.sort(pointsCopy, points[p].slopeOrder());

            for (int q = 1, r = 1; r < pointsCopy.length; r++) {
                // Check if the adjacent points are collinear
                while (r < pointsCopy.length && Double.compare(points[p].slopeTo(pointsCopy[q]),
                        points[p].slopeTo(pointsCopy[r])) == 0) {
                    r++;
                }

                // If found more than or equal 3 collinear points, check if p is their smallest
                // point
                if (r - q >= 3 && points[p].compareTo(pointsCopy[q]) < 0) {
                    Queue<Point> segment = new Queue<>();
                    segment.enqueue(points[p]);
                    for (int i = q; i < r; i++) {
                        segment.enqueue(pointsCopy[i]);
                    }
                    segments.enqueue(segment);
                }
                q = r;
            }
        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public Iterable<Queue<Point>> segments() {
        return segments;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Fast <input-file>");
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

        Fast fast = new Fast(points);

        Out out = new Out();
        out.println(fast.numberOfSegments());
        for (Queue<Point> segment : fast.segments()) {
            while (!segment.isEmpty()) {
                Point point = segment.dequeue();
                out.print(point);
                if (!segment.isEmpty()) {
                    out.print(" -> ");
                }
            }
            out.println();
        }
    }
}