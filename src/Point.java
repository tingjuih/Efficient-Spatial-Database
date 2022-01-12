/**
 * This is an augmented Point object inherited from java.awt.Rectangle.
 * This object stores the name and prints the point in the specific format.
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-10-13
 *
 */
@SuppressWarnings("serial")
public class Point extends java.awt.Point {
    private String name;
    /**
     * The constructor of point with name
     * @param name
     *            the name of the point
     * @param x
     *            x-coordinate of the point
     * @param y
     *            y-coordinate of the point
     */
    public Point(String name, int x, int y) {
        super(x, y);
        this.name = name;
    }
    
    /**
     * The method to get the name of point
     * @return the name of the point
     */
    public String getName() {
        return name;
    }
    
    /**
     * The overridden toString method to print the point in the specific 
     * format.
     */
    @Override
    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append('(');
        stringbuilder.append(name);
        stringbuilder.append(", ");
        stringbuilder.append((int)getX());
        stringbuilder.append(", ");
        stringbuilder.append((int)getY());
        stringbuilder.append(')');
        return stringbuilder.toString();
    }
}
