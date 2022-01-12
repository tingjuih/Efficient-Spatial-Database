import java.io.ByteArrayOutputStream;
import java.util.*;
import org.junit.Before;
import org.junit.Test;

/**
 * This junit test case is for QuadNull object. It will test all method in
 * QuadNull
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-10-16
 */
public class QuadNullTest extends student.TestCase {
    private QuadNull emptyNode;
    private ByteArrayOutputStream output;

    /**
     * Initialize the QuadNull and and set output listener for the test.
     */
    @Before
    public void setUp() throws Exception {
        emptyNode = QuadNull.get();
        output = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(output));
    }


    /**
     * Test the static flyweight function
     */
    @Test
    public void testFlyweight() {
        assertEquals(emptyNode, QuadNull.get());
    }


    /**
     * Test isLeaf method
     */
    @Test
    public void testIsLeaf() {
        assertFalse(emptyNode.isLeaf());
    }


    /**
     * Test insertion method
     */
    @Test
    public void testInsert() {
        output.reset();
        Point point1 = new Point("p1", 12, 12);
        QuadPackage quadPackage = emptyNode.insert(point1, 0, 0, 1024);
        quadPackage.getNode().dump("", 0, 0, 1024);
        assertEquals("Node at 0, 0, 1024:\r\n" + "(p1, 12, 12)\r\n", output
            .toString());
    }


    /**
     * Rest removal method
     */
    @Test
    public void testRemove() {
        Point point1 = new Point("p1", 12, 12);
        QuadPackage quadPackage = emptyNode.remove(point1, 0, 0, 1024);
        assertEquals(emptyNode, quadPackage.getNode());
    }


    /**
     * Test getQuantity method
     */
    @Test
    public void testGetQuantity() {
        assertEquals(0, emptyNode.getQuantity());
    }


    /**
     * Test getPoints method
     */
    @Test
    public void testGetPoints() {
        assertTrue(emptyNode.getPoints().isEmpty());
    }


    /**
     * Test regionsearch method
     */
    @Test
    public void testRegionsearch() {
        output.reset();
        assertEquals(1, emptyNode.regionsearch(50, 50, 100, 100, 0, 0, 1024));
        assertEquals("", output.toString());
    }


    /**
     * Test duplicates method
     */
    @Test
    public void testDuplicates() {
        output.reset();
        emptyNode.duplicates();
        assertEquals("", output.toString());
    }


    /**
     * Test search method
     */
    public void testSearch() {
        List<Point> result = emptyNode.search("");
        assertTrue(result.isEmpty());
    }


    /**
     * Test dump method
     */
    public void testDump() {
        output.reset();
        emptyNode.dump("", 0, 0, 1024);
        assertEquals("Node at 0, 0, 1024: Empty\r\n", output.toString());
    }
}
