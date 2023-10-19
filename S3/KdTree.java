
/*************************************************************************
 *************************************************************************/

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
    private static class Node {
        private Point2D point;
        private RectHV rect;
        private Node left;
        private Node right;

        public Node(Point2D p, RectHV r) {
            point = p;
            rect = r;
            left = null;
            right = null;
        }
    }

    private Node root;

    public KdTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        return 1 + size(node.left) + size(node.right);
    }

    public void insert(Point2D p) {
        root = insert(root, p, true, new RectHV(0, 0, 1, 1));
    }

    private Node insert(Node node, Point2D p, boolean useX, RectHV rect) {
        if (node == null)
            return new Node(p, rect);

        if (node.point.equals(p))
            return node; // Duplicate point

        int cmp = useX ? Double.compare(p.x(), node.point.x()) : Double.compare(p.y(), node.point.y());
        boolean goLeft = cmp < 0;

        if (goLeft) {
            if (useX)
                rect = new RectHV(rect.xmin(), rect.ymin(), node.point.x(), rect.ymax());
            else
                rect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.point.y());
            node.left = insert(node.left, p, !useX, rect);
        } else {
            if (useX)
                rect = new RectHV(node.point.x(), rect.ymin(), rect.xmax(), rect.ymax());
            else
                rect = new RectHV(rect.xmin(), node.point.y(), rect.xmax(), rect.ymax());
            node.right = insert(node.right, p, !useX, rect);
        }

        return node;
    }

    public boolean contains(Point2D p) {
        return contains(root, p, true);
    }

    private boolean contains(Node node, Point2D p, boolean useX) {
        if (node == null)
            return false;
        if (node.point.equals(p))
            return true;

        int cmp = useX ? Double.compare(p.x(), node.point.x()) : Double.compare(p.y(), node.point.y());
        boolean goLeft = cmp < 0;

        if (goLeft)
            return contains(node.left, p, !useX);
        else
            return contains(node.right, p, !useX);
    }

    public void draw() {
        draw(root, true);
    }

    private void draw(Node node, boolean useX) {
        if (node == null)
            return;

        // Draw point
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        node.point.draw();

        // Draw splitting line
        StdDraw.setPenRadius();
        if (useX) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.point.x(), node.rect.ymin(), node.point.x(), node.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.point.y());
        }

        draw(node.left, !useX);
        draw(node.right, !useX);
    }

    public Iterable<Point2D> range(RectHV rect) {
        Stack<Point2D> rangePoints = new Stack<>();
        range(root, rect, rangePoints);
        return rangePoints;
    }

    private void range(Node node, RectHV rect, Stack<Point2D> rangePoints) {
        if (node == null)
            return;
        if (rect.intersects(node.rect)) {
            if (rect.contains(node.point))
                rangePoints.push(node.point);
            range(node.left, rect, rangePoints);
            range(node.right, rect, rangePoints);
        }
    }

    public Point2D nearest(Point2D p) {
        if (isEmpty())
            return null;
        return nearest(root, p, root.point, true);
    }

    private Point2D nearest(Node node, Point2D p, Point2D nearestPoint, boolean useX) {
        if (node == null)
            return nearestPoint;

        double nearestDistance = nearestPoint.distanceSquaredTo(p);
        if (node.rect.distanceSquaredTo(p) < nearestDistance) {
            double nodeDistance = node.point.distanceSquaredTo(p);
            if (nodeDistance < nearestDistance)
                nearestPoint = node.point;

            int cmp = useX ? Double.compare(p.x(), node.point.x()) : Double.compare(p.y(), node.point.y());
            boolean goLeft = cmp < 0;

            if (goLeft) {
                nearestPoint = nearest(node.left, p, nearestPoint, !useX);
                nearestPoint = nearest(node.right, p, nearestPoint, !useX);
            } else {
                nearestPoint = nearest(node.right, p, nearestPoint, !useX);
                nearestPoint = nearest(node.left, p, nearestPoint, !useX);
            }
        }

        return nearestPoint;
    }
}