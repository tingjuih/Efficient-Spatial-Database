import org.junit.Before;
import org.junit.Test;

/**
 * This junit test case is for Point object.
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-10-16
 */
public class PointTest extends student.TestCase {
    private Point pt;

    /**
     * Initialize Point object for test
     */
    @Before
    public void setUp() throws Exception {
        pt = new Point("p1", 0, 0);
    }


    /**
     * Test the getName method
     */
    @Test
    public void testGetName() {
        assertEquals("p1", pt.getName());
    }


    /**
     * Test the toString method
     */
    @Test
    public void testToString() {
        assertEquals("(p1, 0, 0)", pt.toString());
    }

}
