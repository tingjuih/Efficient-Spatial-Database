/**
 * This is a customized Rectangle object inheriting java.awt.Rectangle and 
 * overriding the toString method to print the rectangle in the specific format.
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-09-18
 *
 */
@SuppressWarnings("serial")
public class Rectangle extends java.awt.Rectangle {
    /**
     * The constructor for this class initializes a Rectangle and its parameters
     * 
     * @param x
     *            x-coordinate of the rectangle to be removed
     * @param y
     *            x-coordinate of the rectangle to be removed
     * @param w
     *            width of the rectangle to be removed
     * @param h
     *            height of the rectangle to be removed
     */
    public Rectangle(int x, int y, int w, int h) {
        super(x, y, w, h);
    }
    
    /**
     * The overridden toString method to print the rectangle in the specific 
     * format.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append((int)getX());
        result.append(", ");
        result.append((int)getY());
        result.append(", ");
        result.append((int)getWidth());
        result.append(", ");
        result.append((int)getHeight());
        return result.toString();
    }
}
