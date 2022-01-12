import org.junit.Before;
import org.junit.Test;

/**
 * This junit test case is for Rectangle object. It will test the overridden
 * toString() method.
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-09-24
 */
public class RectangleTest extends student.TestCase {
    private Rectangle rect;

    /**
     * Initialize Rectangle for tests.
     */
    @Before
    public void setUp() {
        rect = new Rectangle(0, 0, 1024, 1024);
    }


    /**
     * Test the toString() method. The toString() method should return a string
     * consist of x-axis, y-axis, width, height separated by commas without
     * parenthesis.
     */
    @Test
    public void testRectangle() {
        assertEquals("0, 0, 1024, 1024", rect.toString());
    }
}
