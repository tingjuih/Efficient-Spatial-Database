import java.util.List;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 */
public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then
    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, Point> list;
    private PRQuadtree tree;
    private Rectangle worldBox;

    /**
     * The constructor for this class initializes a SkipList object with String,
     * Rectangle and its parameters, and the position and dimensions of world
     * box
     */
    public Database() {
        list = new SkipList<>();
        tree = new PRQuadtree(0, 0, 1024);
        worldBox = new Rectangle(0, 0, 1024, 1024);
    }


    /**
     * Inserts the KVPair in the SkipList if the point has valid coordinates
     * and unique name-coordinate combination.
     * 
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(KVPair<String, Point> pair) {
        if (!worldBox.contains(pair.getValue())) {
            System.out.println("Point REJECTED: " + pair);
            return;
        }
        if (!tree.insert(pair.getValue())) {
            System.out.println("Point REJECTED: " + pair);
            return;
        }
        list.insert(pair);
        System.out.println("Point Inserted: " + pair.getValue());
    }


    /**
     * Removes a point with the name "name" if available. If not an error
     * message is printed to the console.
     * 
     * @param name
     *            the name of the rectangle to be removed
     */
    public void remove(String name) {
        KVPair<String, Point> pair = list.remove(name);
        if (pair == null) {
            System.out.println("point Not Removed: " + name);
        }
        else {
            tree.remove(pair.getValue());
            System.out.println("Point " + pair + " Removed");
        }
    }


    /**
     * Removes a point with the specified coordinates if available. If not
     * an error message is printed to the console.
     * 
     * @param x
     *            x-coordinate of the point to be removed
     * @param y
     *            x-coordinate of the point to be removed
     */
    public void remove(int x, int y) {
        Point target = new Point(null, x, y);
        if (!worldBox.contains(target)) {
            StringBuilder output = new StringBuilder();
            output.append("Point Rejected: ");
            output.append('(');
            output.append(x);
            output.append(", ");
            output.append(y);
            output.append(')');
            System.out.println(output.toString());
            return;
        }
        Point point = tree.remove(target);
        if (point == null) {
            StringBuilder output = new StringBuilder();
            output.append("point Not Found: (");
            output.append(x);
            output.append(", ");
            output.append(y);
            output.append(')');
            System.out.println(output.toString());
        }
        else {
            list.remove(new KVPair<>(point.getName(), point));
            System.out.println("Point " + point + " Removed");
        }
    }


    /**
     * Displays all points inside the specified region.
     * 
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
    public void regionsearch(int x, int y, int w, int h) {
        StringBuilder output = new StringBuilder();
        if (w <= 0 || h <= 0) {
            output.append("Rectangle Rejected: (");
            output.append(getRectangle(x, y, w, h));
            output.append(')');
            System.out.println(output.toString());
        }
        else {
            output.append("Points Intersecting Region: (");
            output.append(getRectangle(x, y, w, h));
            output.append(")");
            System.out.println(output.toString());
            tree.regionsearch(x, y, w, h);
        }
    }


    /**
     * Print all duplicated coordinates
     */
    public void duplicates() {
        tree.duplicates();
    }


    /**
     * Transform a rectangle presented by coordinates and dimensions into string
     * 
     * @param x
     *            x-coordinate of the rectangle to be transformed
     * @param y
     *            x-coordinate of the rectangle to be transformed
     * @param w
     *            width of the rectangle to be transformed
     * @param h
     *            height of the rectangle to be transformed
     * @return the string with x, y, w, h format
     */

    private String getRectangle(int x, int y, int w, int h) {
        StringBuilder output = new StringBuilder();
        output.append(x);
        output.append(", ");
        output.append(y);
        output.append(", ");
        output.append(w);
        output.append(", ");
        output.append(h);
        return output.toString();
    }


    /**
     * Prints out all the points with the specified name in the SkipList.
     * 
     * @param name
     *            name of the Rectangle to be searched for
     */
    public void search(String name) {
        List<KVPair<String, Point>> results = list.search(name);
        if (results.isEmpty()) {
            System.out.println("Point Not Found: " + name);
        }
        else {
            for (KVPair<String, Point> pair : results) {
                System.out.print("Point Found ");
                System.out.println(pair.getValue());
            }
        }
    }


    /**
     * Prints out a dump of the SkipList and Quadtree, which includes
     * information about the size of the SkipList, showing all of the contents
     * of the SkipList, and all node and elements in Quadtree.
     */
    public void dump() {
        list.dump();
        tree.dump();
    }

}
