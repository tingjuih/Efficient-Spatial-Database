/**
 * QuadPackage object is used to communicate between QuadNodes.
 * It carries the previous processed node and a inserted/removed point.
 * If the point is null, it means no action is done.
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-10-16
 */
public class QuadPackage {
    private static QuadPackage quadpack;
    private QuadNode node;
    private Point point;

    /**
     * The private constructor of the object. To avoid garbage collection
     * between each recursion, only through get method can create this object.
     */
    private QuadPackage() {
    }


    /**
     * Get the node carried in the package
     * 
     * @return
     *         the QuadNode carried in the package
     */
    public QuadNode getNode() {
        return node;
    }


    /**
     * Set the node carried in the package
     * 
     * @param node
     *            the QuadNode object wished to be set
     */
    public void setNode(QuadNode node) {
        this.node = node;
    }


    /**
     * Get the point carried in the package. It may return null if no point is
     * set before the execute this method.
     * 
     * @return
     *         the point carried in the package
     */
    public Point getPoint() {
        return point;
    }


    /**
     * Set the point carried in the package
     * 
     * @param point
     *            the Point object wished to be set
     */
    public void setPoint(Point point) {
        this.point = point;
    }


    /**
     * Get the QuadPackage. To save memory and prevent from garbage collection,
     * there is only QuadPackage. As the application do not need QuadPackage
     * anymore, the QuadPackage object is stored as a static instance.
     * 
     * @param node
     *            the initial QuadNode wished to be set
     * @param point
     *            the initial Point wished to be set
     * @return
     *         the QuadPackage with initialized values
     */
    public static QuadPackage get(QuadNode node, Point point) {
        if (quadpack == null) {
            quadpack = new QuadPackage();
        }
        quadpack.setNode(node);
        quadpack.setPoint(point);
        return quadpack;
    }
}
