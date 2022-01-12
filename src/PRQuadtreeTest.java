import java.io.ByteArrayOutputStream;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * This junit test case is for PRQuadtree object.
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-10-16
 */
public class PRQuadtreeTest extends student.TestCase {
    private PRQuadtree tree;
    private ByteArrayOutputStream output;

    /**
     * Initialize the QuadInternalNode and and set output listener for the test.
     */
    @Before
    public void setUp() throws Exception {
        tree = new PRQuadtree(0, 0, 1024);
        output = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(output));
    }


    /**
     * Test insertion
     */
    @Test
    public void testInsert() {
        assertTrue(tree.insert(new Point("p1", 0, 12)));
        assertFalse(tree.insert(new Point("p1", 0, 12)));
        assertTrue(tree.insert(new Point("p2", 0, 12)));
        assertTrue(tree.insert(new Point("p3", 0, 12)));
        assertTrue(tree.insert(new Point("p4", 0, 12)));
        assertFalse(tree.insert(new Point("p4", 0, -12)));
        assertFalse(tree.insert(new Point("p4", 0, 1024)));
    }


    /**
     * Test removal
     */
    @Test
    public void testRemove() {
        assertEquals(null, tree.remove(new Point("p1", 0, 12)));
        tree.insert(new Point("p1", 0, 12));
        tree.insert(new Point("p2", 0, 12));
        tree.insert(new Point("p3", 0, 12));
        tree.insert(new Point("p4", 0, 12));
        tree.insert(new Point("p5", 0, 12));
        tree.insert(new Point("p5", 10, 192));
        tree.insert(new Point("p19", 320, 122));
        tree.insert(new Point("p1", 532, 22));
        tree.insert(new Point("p18", 920, 122));
        assertEquals(new Point("p1", 0, 12), tree.remove(new Point("p1", 0,
            12)));
        assertEquals(null, tree.remove(new Point("p1", 0, 12)));
        assertEquals(new Point("p2", 0, 12), tree.remove(new Point(null, 0,
            12)));
        assertEquals(null, tree.remove(new Point("p1", 0, -12)));
    }


    /**
     * Test search method
     */
    @Test
    public void testSearch() {
        tree.insert(new Point("p1", 0, 12));
        tree.insert(new Point("p2", 0, 12));
        tree.insert(new Point("p3", 0, 12));
        tree.insert(new Point("p4", 0, 12));
        tree.insert(new Point("p5", 0, 12));
        tree.insert(new Point("p5", 10, 192));
        tree.insert(new Point("p19", 320, 122));
        tree.insert(new Point("p1", 532, 22));
        tree.insert(new Point("p18", 920, 122));
        List<Point> results = tree.search("p5");
        assertEquals(2, results.size());
        for (Point point : results) {
            assertEquals("p5", point.getName());
        }
    }


    /**
     * Test regionsearch method
     */
    @Test
    public void testRegionsearch() {
        output.reset();
        tree.insert(new Point("p1", 0, 12));
        tree.insert(new Point("p2", 0, 12));
        tree.insert(new Point("p3", 0, 12));
        tree.insert(new Point("p4", 0, 12));
        tree.insert(new Point("p5", 0, 12));
        tree.insert(new Point("p5", 10, 192));
        tree.insert(new Point("p19", 320, 122));
        tree.insert(new Point("p1", 532, 22));
        tree.insert(new Point("p18", 920, 122));
        tree.regionsearch(32, 20, 940, 480);
        assertEquals("Point Found: (p19, 320, 122)\r\n"
            + "Point Found: (p1, 532, 22)\r\n"
            + "Point Found: (p18, 920, 122)\r\n"
            + "11 QuadTree Nodes Visited\r\n", output.toString());
        output.reset();
        tree.regionsearch(-2000, -2000, 940, 480);
        assertEquals("0 QuadTree Nodes Visited\r\n", output.toString());
    }


    /**
     * Test duplicates method
     */
    @Test
    public void testDuplicates() {
        output.reset();
        tree.insert(new Point("p1", 0, 12));
        tree.insert(new Point("p2", 0, 12));
        tree.insert(new Point("p3", 0, 12));
        tree.insert(new Point("p4", 0, 12));
        tree.insert(new Point("p5", 0, 12));
        tree.insert(new Point("p5", 10, 192));
        tree.insert(new Point("p19", 320, 122));
        tree.insert(new Point("p1", 532, 22));
        tree.insert(new Point("p18", 920, 122));
        tree.duplicates();
        assertEquals("Duplicate Points:\r\n" + "(0, 12)\r\n", output
            .toString());
    }


    /**
     * Test dump method
     */
    public void testDump() {
        output.reset();
        tree.insert(new Point("p1", 0, 12));
        tree.insert(new Point("p2", 0, 12));
        tree.insert(new Point("p3", 0, 12));
        tree.insert(new Point("p4", 0, 12));
        tree.insert(new Point("p5", 0, 12));
        tree.insert(new Point("p5", 10, 192));
        tree.insert(new Point("p19", 320, 122));
        tree.insert(new Point("p1", 532, 22));
        tree.insert(new Point("p18", 920, 122));
        tree.dump();
        assertEquals("QuadTree Dump:\r\n" + "Node at 0, 0, 1024: Internal\r\n"
            + "  Node at 0, 0, 512: Internal\r\n"
            + "    Node at 0, 0, 256: Internal\r\n"
            + "      Node at 0, 0, 128:\r\n" + "      (p1, 0, 12)\r\n"
            + "      (p2, 0, 12)\r\n" + "      (p3, 0, 12)\r\n"
            + "      (p4, 0, 12)\r\n" + "      (p5, 0, 12)\r\n"
            + "      Node at 128, 0, 128: Empty\r\n"
            + "      Node at 0, 128, 128:\r\n" + "      (p5, 10, 192)\r\n"
            + "      Node at 128, 128, 128: Empty\r\n"
            + "    Node at 256, 0, 256:\r\n" + "    (p19, 320, 122)\r\n"
            + "    Node at 0, 256, 256: Empty\r\n"
            + "    Node at 256, 256, 256: Empty\r\n"
            + "  Node at 512, 0, 512:\r\n" + "  (p1, 532, 22)\r\n"
            + "  (p18, 920, 122)\r\n" + "  Node at 0, 512, 512: Empty\r\n"
            + "  Node at 512, 512, 512: Empty\r\n"
            + "QuadTree Size: 13 QuadTree Nodes Printed.\r\n", output
                .toString());
    }


    /**
     * Test size method
     */
    public void testSize() {
        assertEquals(0, tree.size());
        tree.insert(new Point("p1", 0, 12));
        tree.insert(new Point("p2", 0, 12));
        tree.insert(new Point("p3", 0, 12));
        tree.insert(new Point("p4", 0, 12));
        tree.insert(new Point("p5", 0, 12));
        tree.insert(new Point("p5", 10, 192));
        tree.insert(new Point("p19", 320, 122));
        tree.insert(new Point("p1", 532, 22));
        tree.insert(new Point("p18", 920, 122));
        tree.remove(new Point("p", 0, 12));
        tree.remove(new Point("p18", 920, 122));
        assertEquals(8, tree.size());
    }

}
