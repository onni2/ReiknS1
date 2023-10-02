import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import edu.princeton.cs.algs4.In;

public class Brute {
    private final List<List<Point>> lineSegments;

    public Brute(Point[] points) {
        lineSegments = new ArrayList<>();

        int n = points.length;
        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int m = k + 1; m < n; m++) {
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) &&
                                points[i].slopeTo(points[j]) == points[i].slopeTo(points[m])) {
                            List<Point> segment = new ArrayList<>();
                            segment.add(points[i]);
                            segment.add(points[j]);
                            segment.add(points[k]);
                            segment.add(points[m]);
                            Collections.sort(segment); // Sort the points within the segment
                            lineSegments.add(segment);
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public Iterable<List<Point>> segments() {
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
    
        // Store segments in a list to sort them
        List<List<Point>> segmentsList = new ArrayList<>();
        for (List<Point> segment : brute.segments()) {
            // Sort the points within the segment
            Collections.sort(segment);
            segmentsList.add(segment);
        }
    
        // Sort the segments
        segmentsList.sort((seg1, seg2) -> {
            for (int i = 0; i < seg1.size(); i++) {
                int compare = seg1.get(i).compareTo(seg2.get(i));
                if (compare != 0) {
                    return compare;
                }
            }
            return 0;
        });
    
        // Print the sorted segments
        for (List<Point> segment : segmentsList) {
            int size = segment.size();
            for (int i = 0; i < size - 1; i++) {
                System.out.print(segment.get(i) + ", ");
            }
            System.out.println(segment.get(size - 1));
        }
    }
    
}
