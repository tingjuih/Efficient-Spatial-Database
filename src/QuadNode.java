import java.util.List;

/**
 * QuadNode interface define the common methods for all type of node in
 * PRQuadtree.
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-10-16
 */
public interface QuadNode {
    /**
     * Indicate if current node if a leaf
     * 
     * @return
     *         the boolean to indicate if current node if a leaf
     */
    public boolean isLeaf();


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
    public QuadPackage insert(Point point, int x, int y, int dimension);


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
    public QuadPackage remove(Point point, int x, int y, int dimension);


    /**
     * search the points with specific name.
     * 
     * @param name
     *            the target name
     * @return a List of Point with the specific name
     */
    public List<Point> search(String name);


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
        int dimension);


    /**
     * search and print the duplicated coordinates.
     */
    public void duplicates();


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
    public int dump(String header, int x, int y, int dimension);


    /**
     * Get the total quantity of points stored in the current node
     * 
     * @return the total quantity of points stored in the current node
     */
    public int getQuantity();


    /**
     * Get all points stored in the current node
     * 
     * @return a List of points stored in the current node
     */
    public List<Point> getPoints();
}
