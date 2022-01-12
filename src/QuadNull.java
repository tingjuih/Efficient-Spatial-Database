import java.util.*;

/**
 * QuadNull object implement QuadNode interface to represent the null node of
 * PRQuadtree. QuadNull also implement the fly weight pattern for saving memory
 * space.
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-10-16
 */
public class QuadNull implements QuadNode {
    private static QuadNull flyweight;

    /**
     * The constructor of QuadNull.
     */
    private QuadNull() {
    }


    /**
     * The getter method of QuadNull. QuadNull will be created if and only if
     * flyweight instance points to null.
     * 
     * @return
     *         the QuadNull referenced by flyweight
     */
    public static QuadNull get() {
        if (flyweight == null) {
            flyweight = new QuadNull();
        }
        return flyweight;
    }


    /**
     * Indicate if current node if a leaf
     * 
     * @return
     *         the boolean to indicate if current node if a leaf
     */
    public boolean isLeaf() {
        return false;
    }


    /**
     * Insert the point into current QuadNode
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
        QuadNode node = new QuadLeaf();
        QuadPackage quadPackage = node.insert(point, x, y, dimension);
        quadPackage.setNode(node);
        return quadPackage;
    }


    /**
     * Remove the point into current QuadNode
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
        return QuadPackage.get(this, null);
    }


    /**
     * Get the total quantity of points stored in the current node
     * 
     * @return the total quantity of points stored in the current node
     */
    public int getQuantity() {
        return 0;
    }


    /**
     * Get all points stored in the current node
     * 
     * @return a List of points stored in the current node
     */
    public List<Point> getPoints() {
        return new ArrayList<>();
    }


    /**
     * Search and print the points contained in the specific region.
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
        return 1;
    }


    /**
     * search and print the duplicated coordinates.
     */
    public void duplicates() {
        //no points to be checked
    }


    /**
     * search the points with specific name.
     * 
     * @param name
     *            the target name
     * @return a List of Point with the specific name
     */
    public List<Point> search(String name) {
        return new ArrayList<>();
    }


    /**
     * Print the current node and saved points in a specific format.
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
        System.out.println(": Empty");
        return 1;
    }
}
