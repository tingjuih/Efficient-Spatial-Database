import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * QuadLeaf object implement QuadNode interface to represent the leaf node of
 * PRQuadtree.
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-10-16
 */
public class QuadLeaf implements QuadNode {
    private List<Point> points;

    /**
     * The constructor of QuadLeaf. All children are initially set as QuadNull.
     */
    public QuadLeaf() {
        points = new ArrayList<>();
    }


    /**
     * Indicate if current node if a leaf.
     * 
     * @return
     *         the boolean to indicate if current node if a leaf.
     */
    public boolean isLeaf() {
        return true;
    }


    /**
     * Insert the point into current QuadNode. The leaf node will check if there
     * is duplication in the point list. Insert the point if there is no
     * duplication. If there are over 4 points with different coordinates, the
     * leaf will be decomposed.
     * 
     * @param point
     *            the Point wished to be inserted
     * @param x
     *            the x-coordinate of the current QuadNode
     * @param y
     *            the y-coordinate of the current QuadNode
     * @param dimension
     *            the dimension of the current QuadNode
     * @return a QuadPackage carrying current QuadNode and inserted Point
     */
    public QuadPackage insert(Point point, int x, int y, int dimension) {
        for (Point candidate : points) {
            if (point.equals(candidate) && point.getName().equals(candidate
                .getName())) {
                return QuadPackage.get(this, null);
            }
        }
        points.add(point);

        if (points.size() <= 3 || (points.size() == 4 && isEven()) || (points
            .size() > 4 && points.get(0).equals(point))) {
            return QuadPackage.get(this, point);
        }
        // decompose
        QuadNode internal = new QuadInternalNode();
        for (Point element : points) {
            internal.insert(element, x, y, dimension);
        }
        return QuadPackage.get(internal, point);
    }


    /**
     * Check if the coordinates in points are the same
     * 
     * @return a boolean to indicate if the coordinates in points are the same
     */
    private boolean isEven() {
        Point reference = points.get(0);
        boolean isSamePoint = true;
        for (Point candidate : points) {
            isSamePoint &= candidate.equals(reference);
        }
        return isSamePoint;
    }


    /**
     * Remove the point into current QuadNode. The leaf node will check if there
     * is a point with the same name and coordinates. Remove the point if there
     * is such a point in the current node.
     * 
     * @param point
     *            the Point wished to be removed
     * @param x
     *            the x-coordinate of the current QuadNode
     * @param y
     *            the y-coordinate of the current QuadNode
     * @param dimension
     *            the dimension of the current QuadNode
     * @return a QuadPackage carrying current QuadNode and removed Point
     */
    public QuadPackage remove(Point point, int x, int y, int dimension) {
        QuadPackage quadPackage = QuadPackage.get(this, null);
        if (points.size() > 3 && !point.equals(points.get(0))) {
            return quadPackage;
        }

        String targetName = point.getName();
        for (int i = 0; i < points.size(); i++) {
            Point current = points.get(i);
            if (point.equals(current) && (targetName == null || current
                .getName().equals(targetName))) {
                quadPackage.setPoint(points.remove(i));
                break;
            }
        }

        if (points.size() == 0) {
            quadPackage.setNode(QuadNull.get());
        }
        return quadPackage;
    }


    /**
     * Get the total quantity of points stored in the current node.
     * 
     * @return the total quantity of points stored in the current node
     */
    public int getQuantity() {
        return points.size();
    }


    /**
     * Get all points stored in the current node
     * 
     * @return a List of points stored in the current node
     */
    public List<Point> getPoints() {
        return points;
    }


    /**
     * Print the points contained in the specific region.
     * 
     * @param regionX
     *            the x-coordinate of the target region
     * @param regionY
     *            the y-coordinate of the target region
     * @param regionW
     *            the width of the target region
     * @param regionH
     *            the height of the target region
     * @param x
     *            the x-coordinate of the current QuadNode
     * @param y
     *            the y-coordinate of the current QuadNode
     * @param dimension
     *            the dimension of the current QuadNode
     * @return the number of visited nodes
     */
    public int regionsearch(
        int regionX,
        int regionY,
        int regionW,
        int regionH,
        int x,
        int y,
        int dimension) {
        Rectangle region = new Rectangle(regionX, regionY, regionW, regionH);
        for (Point point : points) {
            if (!region.contains(point)) {
                continue;
            }
            System.out.print("Point Found: ");
            System.out.println(point);
        }
        return 1;
    }


    /**
     * search the points with specific name. The leaf node will all points to
     * find the points with the target name
     * 
     * @param name
     *            the target name
     * @return a List of Point with the specific name
     */
    public List<Point> search(String name) {
        List<Point> result = new ArrayList<>();
        for (Point point : points) {
            if (point.getName().equals(name)) {
                result.add(point);
            }
        }
        return result;
    }


    /**
     * print the duplicated coordinates.
     */
    public void duplicates() {
        Set<Point> seen = new HashSet<>();
        for (Point point : points) {
            if (!seen.add(point)) {
                System.out.print('(');
                System.out.print((int)point.getX());
                System.out.print(", ");
                System.out.print((int)point.getY());
                System.out.println(')');
                break;
            }
        }
    }


    /**
     * Print the current node, points in a specific format.
     * 
     * @param header
     *            the indentation
     * @param x
     *            the x-coordinate of the current QuadNode
     * @param y
     *            the y-coordinate of the current QuadNode
     * @param dimension
     *            the dimension of the current QuadNode
     * @return the total number of nodes in the subtree
     */
    public int dump(String header, int x, int y, int dimension) {
        System.out.print(header);
        System.out.print("Node at ");
        System.out.print(x);
        System.out.print(", ");
        System.out.print(y);
        System.out.print(", ");
        System.out.print(dimension);
        System.out.print(":\n");
        for (Point point : points) {
            System.out.print(header);
            System.out.println(point);
        }
        return 1;
    }
}
