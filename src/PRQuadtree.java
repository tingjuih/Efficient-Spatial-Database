import java.util.List;

/**
 * The Region-Point Quadtree which provides efficient spatial search, insertion,
 * removal operation.
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-10-16
 */
public class PRQuadtree {
    private int x;
    private int y;
    private int dimension;
    private int size;
    private QuadNode root;
    private Rectangle worldBox;

    /**
     * The constructor of PRQuadtree
     * 
     * @param x
     *            the x-coordinate of the world box
     * @param y
     *            the x-coordinate of the world box
     * @param dimension
     *            the dimension of the world box
     */
    public PRQuadtree(int x, int y, int dimension) {
        this.x = x;
        this.y = y;
        this.dimension = dimension;
        worldBox = new Rectangle(x, y, dimension, dimension);
        size = 0;
        root = QuadNull.get();
    }


    /**
     * Insert the point into quadtree
     * 
     * @param point
     *            the point to be inserted
     * @return a boolean to indicate the action is completed or rejected
     */
    public boolean insert(Point point) {
        if (!worldBox.contains(point)) {
            return false;
        }
        QuadPackage quadPackage = root.insert(point, x, y, dimension);
        root = quadPackage.getNode();
        if (quadPackage.getPoint() != null) {
            size++;
        }
        return quadPackage.getPoint() != null;
    }


    /**
     * Remove the point into quadtree
     * 
     * @param point
     *            the point to be removed
     * @return the removed point, or null if the commend is rejected
     */
    public Point remove(Point point) {
        if (!worldBox.contains(point)) {
            return null;
        }
        QuadPackage quadPackage = root.remove(point, x, y, dimension);
        root = quadPackage.getNode();
        if (quadPackage.getPoint() != null) {
            size--;
        }
        return quadPackage.getPoint();
    }


    /**
     * Search the points with the specific name
     * 
     * @param name
     *            the name to be searched
     * @return the list of points with the specific name
     */
    public List<Point> search(String name) {
        return root.search(name);
    }


    /**
     * Search and print the points contained in the specific region. The total
     * number of visited nodes is also reported at the tail.
     * 
     * @param regionX
     *            the x-coordinate of the target region
     * @param regionY
     *            the y-coordinate of the target region
     * @param regionW
     *            the width of the target region
     * @param regionH
     *            the height of the target region
     */
    public void regionsearch(
        int regionX,
        int regionY,
        int regionW,
        int regionH) {
        int subRegionX = Math.max(regionX, x);
        int subRegionY = Math.max(regionY, y);

        int subRegionW = Math.min(regionW + regionX, x + dimension)
            - subRegionX;
        int subRegionH = Math.min(regionH + regionY, y + dimension)
            - subRegionY;

        int visitedNode = 0;
        if (worldBox.intersects(new Rectangle(subRegionX, subRegionY,
            subRegionW, subRegionH))) {
            visitedNode = root.regionsearch(subRegionX, subRegionY, subRegionW,
                subRegionH, x, y, dimension);
        }
        System.out.println(visitedNode + " QuadTree Nodes Visited");
    }


    /**
     * Search and print the duplicated coordinates.
     */
    public void duplicates() {
        System.out.println("Duplicate Points:");
        root.duplicates();
    }


    /**
     * Print the all nodes and points in the quadtree in a specific format.
     */
    public void dump() {
        System.out.println("QuadTree Dump:");
        int totalNode = root.dump("", x, y, dimension);
        System.out.println("QuadTree Size: " + totalNode
            + " QuadTree Nodes Printed.");
    }


    /**
     * Get the size of the quadtree
     * 
     * @return the size of the quadtree
     */
    public int size() {
        return size;
    }
}
