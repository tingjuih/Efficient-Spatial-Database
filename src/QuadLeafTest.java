import java.io.ByteArrayOutputStream;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * This junit test case is for QuadLeaf object.
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-10-16
 */
public class QuadLeafTest extends student.TestCase {
    private QuadLeaf leaf;
    private ByteArrayOutputStream output;

    /**
     * Initialize the QuadNull and and set output listener for the test.
     */
    @Before
    public void setUp() throws Exception {
        leaf = new QuadLeaf();
        output = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(output));
        leaf.insert(new Point("p1", 12, 12), 0, 0, 1024);
    }


    /**
     * Test isLeaf method
     */
    @Test
    public void testIsLeaf() {
        assertTrue(leaf.isLeaf());
    }


    /**
     * Test insertion method
     */
    @Test
    public void testInsert() {
        output.reset();
        leaf.insert(new Point("p2", 12, 12), 0, 0, 1024);
        leaf.insert(new Point("p3", 12, 12), 0, 0, 1024);
        leaf.insert(new Point("p4", 12, 12), 0, 0, 1024);
        leaf.insert(new Point("p5", 12, 12), 0, 0, 1024);
        leaf.dump("", 0, 0, 1024);
        assertEquals("Node at 0, 0, 1024:\r\n" + "(p1, 12, 12)\r\n"
            + "(p2, 12, 12)\r\n" + "(p3, 12, 12)\r\n" + "(p4, 12, 12)\r\n"
            + "(p5, 12, 12)\r\n", output.toString());
        leaf.insert(new Point("p5", 12, 12), 0, 0, 1024);
        output.reset();
        leaf.dump("", 0, 0, 1024);
        assertEquals("Node at 0, 0, 1024:\r\n" + "(p1, 12, 12)\r\n"
            + "(p2, 12, 12)\r\n" + "(p3, 12, 12)\r\n" + "(p4, 12, 12)\r\n"
            + "(p5, 12, 12)\r\n", output.toString());
        QuadNode node = null;
        node = leaf.insert(new Point("p4", 700, 12), 0, 0, 1024).getNode();
        node = leaf.insert(new Point("p5", 700, 12), 0, 0, 1024).getNode();
        node = leaf.insert(new Point("p6", 700, 12), 0, 0, 1024).getNode();
        node = leaf.insert(new Point("p6", 900, 12), 0, 0, 1024).getNode();
        output.reset();
        node.dump("", 0, 0, 1024);
        assertEquals("Node at 0, 0, 1024: Internal\r\n"
            + "  Node at 0, 0, 512:\r\n" + "  (p1, 12, 12)\r\n"
            + "  (p2, 12, 12)\r\n" + "  (p3, 12, 12)\r\n" + "  (p4, 12, 12)\r\n"
            + "  (p5, 12, 12)\r\n" + "  Node at 512, 0, 512: Internal\r\n"
            + "    Node at 512, 0, 256:\r\n" + "    (p4, 700, 12)\r\n"
            + "    (p5, 700, 12)\r\n" + "    (p6, 700, 12)\r\n"
            + "    Node at 768, 0, 256:\r\n" + "    (p6, 900, 12)\r\n"
            + "    Node at 512, 256, 256: Empty\r\n"
            + "    Node at 768, 256, 256: Empty\r\n"
            + "  Node at 0, 512, 512: Empty\r\n"
            + "  Node at 512, 512, 512: Empty\r\n", output.toString());
    }


    /**
     * Test the remove method
     */
    @Test
    public void testRemove() {
        output.reset();
        QuadPackage pack = leaf.remove(new Point("p1", 12, 12), 0, 0, 1024);
        pack.getNode().dump("", 0, 0, 1024);
        assertEquals("Node at 0, 0, 1024: Empty\r\n", output.toString());
        QuadNode node = leaf;
        node = node.insert(new Point("p1", 12, 12), 0, 0, 1024).getNode();
        node = node.insert(new Point("p2", 12, 12), 0, 0, 1024).getNode();
        node = node.insert(new Point("p3", 12, 12), 0, 0, 1024).getNode();
        node = node.insert(new Point("p4", 12, 12), 0, 0, 1024).getNode();
        node = node.insert(new Point("p5", 12, 12), 0, 0, 1024).getNode();
        node = node.remove(new Point("p1", 12, 13), 0, 0, 1024).getNode();
        node = node.remove(new Point("p4", 12, 12), 0, 0, 1024).getNode();
        node = node.remove(new Point(null, 12, 12), 0, 0, 1024).getNode();
        output.reset();
        node.dump("", 0, 0, 1024);
        assertEquals("Node at 0, 0, 1024:\r\n" + "(p2, 12, 12)\r\n"
            + "(p3, 12, 12)\r\n" + "(p5, 12, 12)\r\n", output.toString());
        node = node.insert(new Point("p1", 112, 322), 0, 0, 1024).getNode();
        node = node.insert(new Point("p2", 123, 123), 0, 0, 1024).getNode();
        node = node.insert(new Point("p3", 212, 122), 0, 0, 1024).getNode();
        node = node.insert(new Point("p4", 122, 127), 0, 0, 1024).getNode();
        node = node.insert(new Point("p5", 152, 125), 0, 0, 1024).getNode();
        node = node.insert(new Point("p0", 0, 0), 0, 0, 1024).getNode();
        node = node.insert(new Point("p5", 0, 128), 0, 0, 1024).getNode();
        node = node.remove(new Point("p6", 152, 125), 0, 0, 1024).getNode();
        node = node.remove(new Point("p5", 0, 128), 0, 0, 1024).getNode();
        node = node.remove(new Point(null, 0, 0), 0, 0, 1024).getNode();
        output.reset();
        node.dump("", 0, 0, 1024);
        assertEquals("Node at 0, 0, 1024: Internal\r\n"
            + "  Node at 0, 0, 512: Internal\r\n"
            + "    Node at 0, 0, 256: Internal\r\n"
            + "      Node at 0, 0, 128: Internal\r\n"
            + "        Node at 0, 0, 64:\r\n" + "        (p2, 12, 12)\r\n"
            + "        (p3, 12, 12)\r\n" + "        (p5, 12, 12)\r\n"
            + "        Node at 64, 0, 64: Empty\r\n"
            + "        Node at 0, 64, 64: Empty\r\n"
            + "        Node at 64, 64, 64:\r\n" + "        (p2, 123, 123)\r\n"
            + "        (p4, 122, 127)\r\n" + "      Node at 128, 0, 128:\r\n"
            + "      (p3, 212, 122)\r\n" + "      (p5, 152, 125)\r\n"
            + "      Node at 0, 128, 128: Empty\r\n"
            + "      Node at 128, 128, 128: Empty\r\n"
            + "    Node at 256, 0, 256: Empty\r\n"
            + "    Node at 0, 256, 256:\r\n" + "    (p1, 112, 322)\r\n"
            + "    Node at 256, 256, 256: Empty\r\n"
            + "  Node at 512, 0, 512: Empty\r\n"
            + "  Node at 0, 512, 512: Empty\r\n"
            + "  Node at 512, 512, 512: Empty\r\n", output.toString());

    }


    /**
     * Test region search method
     */
    @Test
    public void testRegionsearch() {
        output.reset();
        QuadNode node = leaf;
        node = node.insert(new Point("p1", 112, 322), 0, 0, 1024).getNode();
        node = node.insert(new Point("p2", 123, 123), 0, 0, 1024).getNode();
        int n = node.regionsearch(0, 0, 110, 150, 0, 0, 1024);
        assertEquals("Point Found: (p1, 12, 12)\r\n", output.toString());
        assertEquals(1, n);
        node = node.insert(new Point("p1", 12, 12), 0, 0, 1024).getNode();
        node = node.insert(new Point("p2", 12, 12), 0, 0, 1024).getNode();
        node = node.insert(new Point("p3", 12, 12), 0, 0, 1024).getNode();
        node = node.insert(new Point("p4", 12, 12), 0, 0, 1024).getNode();
        node = node.insert(new Point("p5", 12, 12), 0, 0, 1024).getNode();
        node = node.insert(new Point("p1", 112, 322), 0, 0, 1024).getNode();
        node = node.insert(new Point("p2", 123, 123), 0, 0, 1024).getNode();
        node = node.insert(new Point("p3", 212, 122), 0, 0, 1024).getNode();
        node = node.insert(new Point("p4", 122, 127), 0, 0, 1024).getNode();
        node = node.insert(new Point("p5", 152, 125), 0, 0, 1024).getNode();
        node = node.insert(new Point("p0", 0, 0), 0, 0, 1024).getNode();
        node = node.insert(new Point("p5", 0, 128), 0, 0, 1024).getNode();
        n = node.regionsearch(0, 0, 110, 150, 0, 0, 1024);
        assertEquals("Point Found: (p1, 12, 12)\r\n"
            + "Point Found: (p0, 0, 0)\r\n" + "Point Found: (p1, 12, 12)\r\n"
            + "Point Found: (p2, 12, 12)\r\n" + "Point Found: (p3, 12, 12)\r\n"
            + "Point Found: (p4, 12, 12)\r\n" + "Point Found: (p5, 12, 12)\r\n"
            + "Point Found: (p5, 0, 128)\r\n", output.toString());
        assertEquals(21, n);
    }


    /**
     * Test search method
     */
    public void testSearch() {
        QuadNode node = leaf;
        node = node.insert(new Point("p1", 112, 322), 0, 0, 1024).getNode();
        node = node.insert(new Point("p2", 123, 123), 0, 0, 1024).getNode();
        node = node.insert(new Point("p1", 12, 12), 0, 0, 1024).getNode();
        node = node.insert(new Point("p2", 12, 12), 0, 0, 1024).getNode();
        node = node.insert(new Point("p3", 12, 12), 0, 0, 1024).getNode();
        node = node.insert(new Point("p4", 12, 12), 0, 0, 1024).getNode();
        node = node.insert(new Point("p5", 12, 12), 0, 0, 1024).getNode();
        node = node.insert(new Point("p1", 112, 322), 0, 0, 1024).getNode();
        node = node.insert(new Point("p2", 123, 123), 0, 0, 1024).getNode();
        node = node.insert(new Point("p3", 212, 122), 0, 0, 1024).getNode();
        node = node.insert(new Point("p4", 122, 127), 0, 0, 1024).getNode();
        node = node.insert(new Point("p5", 152, 125), 0, 0, 1024).getNode();
        node = node.insert(new Point("p0", 0, 0), 0, 0, 1024).getNode();
        node = node.insert(new Point("p5", 0, 128), 0, 0, 1024).getNode();
        List<Point> results = node.search("p2");
        assertEquals(2, results.size());
        for (Point point : results) {
            assertTrue(point.getName().equals("p2"));
        }
    }


    /**
     * Test duplicates method
     */
    public void testDuplicates() {
        QuadNode node = leaf;
        output.reset();
        node.duplicates();
        assertEquals("", output.toString());
        node = node.insert(new Point("p1", 112, 322), 0, 0, 1024).getNode();
        node = node.insert(new Point("p2", 123, 123), 0, 0, 1024).getNode();
        node = node.insert(new Point("p1", 12, 12), 0, 0, 1024).getNode();
        node = node.insert(new Point("p2", 12, 12), 0, 0, 1024).getNode();
        node = node.insert(new Point("p3", 12, 12), 0, 0, 1024).getNode();
        node = node.insert(new Point("p4", 12, 12), 0, 0, 1024).getNode();
        node = node.insert(new Point("p5", 12, 12), 0, 0, 1024).getNode();
        node = node.insert(new Point("p1", 112, 322), 0, 0, 1024).getNode();
        node = node.insert(new Point("p2", 123, 123), 0, 0, 1024).getNode();
        node = node.insert(new Point("p3", 212, 122), 0, 0, 1024).getNode();
        node = node.insert(new Point("p4", 122, 127), 0, 0, 1024).getNode();
        node = node.insert(new Point("p5", 152, 125), 0, 0, 1024).getNode();
        node = node.insert(new Point("p0", 0, 0), 0, 0, 1024).getNode();
        node = node.insert(new Point("p5", 0, 128), 0, 0, 1024).getNode();
        node.duplicates();
        assertEquals("(12, 12)\r\n", output.toString());
    }


    /**
     * Test dump method
     */
    public void testDump() {
        output.reset();
        QuadNode node = leaf;
        int n = node.dump("", 0, 0, 1024);
        assertEquals("Node at 0, 0, 1024:\r\n" + "(p1, 12, 12)\r\n", output
            .toString());
        assertEquals(1, n);
        node = node.remove(new Point("p1", 12, 12), 0, 0, 1024).getNode();
        output.reset();
        n = node.dump("", 0, 0, 1024);
        assertEquals("Node at 0, 0, 1024: Empty\r\n", output.toString());
        assertEquals(1, n);
    }
}
