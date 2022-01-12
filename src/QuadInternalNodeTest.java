import java.io.ByteArrayOutputStream;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * This junit test case is for QuadInternalNode object.
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-10-16
 */
public class QuadInternalNodeTest extends student.TestCase {
    private QuadInternalNode internalNode;
    private ByteArrayOutputStream output;

    /**
     * Initialize the QuadInternalNode and and set output listener for the test.
     */
    @Before
    public void setUp() throws Exception {
        internalNode = new QuadInternalNode();
        output = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(output));
    }


    /**
     * Test isLeaf method
     */
    @Test
    public void testIsLeaf() {
        assertFalse(internalNode.isLeaf());
    }


    /**
     * Test insertion
     */
    @Test
    public void testInsert() {
        internalNode.insert(new Point("p1", 217, 74), 0, 0, 1024);
        internalNode.insert(new Point("p2", 721, 4), 0, 0, 1024);
        internalNode.insert(new Point("p3", 21, 990), 0, 0, 1024);
        internalNode.insert(new Point("p4", 721, 864), 0, 0, 1024);
        internalNode.insert(new Point("p5", 0, 0), 0, 0, 1024);
        internalNode.insert(new Point("p6", 512, 512), 0, 0, 1024);
        internalNode.insert(new Point("p7", 512, 0), 0, 0, 1024);
        internalNode.insert(new Point("p8", 0, 512), 0, 0, 1024);
        output.reset();
        internalNode.dump("", 0, 0, 1024);
        assertEquals("Node at 0, 0, 1024: Internal\r\n"
            + "  Node at 0, 0, 512:\r\n" + "  (p1, 217, 74)\r\n"
            + "  (p5, 0, 0)\r\n" + "  Node at 512, 0, 512:\r\n"
            + "  (p2, 721, 4)\r\n" + "  (p7, 512, 0)\r\n"
            + "  Node at 0, 512, 512:\r\n" + "  (p3, 21, 990)\r\n"
            + "  (p8, 0, 512)\r\n" + "  Node at 512, 512, 512:\r\n"
            + "  (p4, 721, 864)\r\n" + "  (p6, 512, 512)\r\n", output
                .toString());
    }


    /**
     * Test removal
     */
    @Test
    public void testRemove() {
        internalNode.insert(new Point("p1", 217, 74), 0, 0, 1024);
        internalNode.insert(new Point("p2", 721, 4), 0, 0, 1024);
        internalNode.insert(new Point("p3", 21, 990), 0, 0, 1024);
        internalNode.insert(new Point("p4", 721, 864), 0, 0, 1024);
        internalNode.insert(new Point("p5", 0, 0), 0, 0, 1024);
        internalNode.insert(new Point("p6", 512, 512), 0, 0, 1024);
        internalNode.insert(new Point("p7", 512, 0), 0, 0, 1024);
        internalNode.insert(new Point("p8", 0, 512), 0, 0, 1024);
        QuadNode node = internalNode;
        node = node.remove(new Point("p1", 217, 73), 0, 0, 1024).getNode();
        node = node.remove(new Point("p2", 721, 4), 0, 0, 1024).getNode();
        node = node.remove(new Point("p5", 0, 0), 0, 0, 1024).getNode();
        node = node.remove(new Point(null, 512, 512), 0, 0, 1024).getNode();
        node = node.remove(new Point("p7", 512, 0), 0, 0, 1024).getNode();
        node = node.remove(new Point(null, 0, 512), 0, 0, 1024).getNode();
        output.reset();
        node.dump("", 0, 0, 1024);
        assertEquals("Node at 0, 0, 1024:\r\n"
            + "(p1, 217, 74)\r\n"
            + "(p3, 21, 990)\r\n"
            + "(p4, 721, 864)\r\n", output.toString());
        node = node.insert(new Point("p61", 512, 512), 0, 0, 1024).getNode();
        node = node.insert(new Point("p62", 512, 512), 0, 0, 1024).getNode();
        node = node.insert(new Point("p63", 512, 512), 0, 0, 1024).getNode();
        node = node.insert(new Point("p64", 512, 512), 0, 0, 1024).getNode();
        node = node.insert(new Point("p65", 512, 512), 0, 0, 1024).getNode();
        node = node.insert(new Point("p66", 512, 512), 0, 0, 1024).getNode();
        node = node.insert(new Point("p63", 512, 513), 0, 0, 1024).getNode();
        node = node.insert(new Point("p64", 512, 513), 0, 0, 1024).getNode();
        node = node.remove(new Point(null, 512, 513), 0, 0, 1024).getNode();
        node = node.remove(new Point(null, 512, 513), 0, 0, 1024).getNode();
        output.reset();
        node.dump("", 0, 0, 1024);
        assertEquals("Node at 0, 0, 1024: Internal\r\n"
            + "  Node at 0, 0, 512:\r\n"
            + "  (p1, 217, 74)\r\n"
            + "  Node at 512, 0, 512: Empty\r\n"
            + "  Node at 0, 512, 512:\r\n"
            + "  (p3, 21, 990)\r\n"
            + "  Node at 512, 512, 512: Internal\r\n"
            + "    Node at 512, 512, 256:\r\n"
            + "    (p61, 512, 512)\r\n"
            + "    (p62, 512, 512)\r\n"
            + "    (p63, 512, 512)\r\n"
            + "    (p64, 512, 512)\r\n"
            + "    (p65, 512, 512)\r\n"
            + "    (p66, 512, 512)\r\n"
            + "    Node at 768, 512, 256: Empty\r\n"
            + "    Node at 512, 768, 256:\r\n"
            + "    (p4, 721, 864)\r\n"
            + "    Node at 768, 768, 256: Empty\r\n", output.toString());
    }


    /**
     * Test getQuantity method
     */
    @Test
    public void testGetQuantity() {
        assertEquals(0, internalNode.getQuantity());
    }


    /**
     * Test getPoints method
     */
    @Test
    public void testGetPoints() {
        assertTrue(internalNode.getPoints().isEmpty());
    }


    /**
     * Test search method
     */
    @Test
    public void testSearch() {
        internalNode.insert(new Point("p1", 217, 74), 0, 0, 1024);
        internalNode.insert(new Point("p2", 721, 4), 0, 0, 1024);
        internalNode.insert(new Point("p3", 21, 990), 0, 0, 1024);
        internalNode.insert(new Point("p2", 721, 864), 0, 0, 1024);
        internalNode.insert(new Point("p2", 0, 0), 0, 0, 1024);
        internalNode.insert(new Point("p3", 512, 512), 0, 0, 1024);
        internalNode.insert(new Point("p2", 512, 0), 0, 0, 1024);
        internalNode.insert(new Point("p3", 0, 512), 0, 0, 1024);
        List<Point> results = internalNode.search("p2");
        assertEquals(4, results.size());
        for (Point point : results) {
            assertTrue("p2".equals(point.getName()));
        }
    }


    /**
     * Test regionsearch method
     */
    @Test
    public void testRegionSearch() {
        internalNode.insert(new Point("p1", 217, 74), 0, 0, 1024);
        internalNode.insert(new Point("p2", 721, 4), 0, 0, 1024);
        internalNode.insert(new Point("p3", 21, 990), 0, 0, 1024);
        internalNode.insert(new Point("p4", 721, 864), 0, 0, 1024);
        internalNode.insert(new Point("p5", 0, 0), 0, 0, 1024);
        internalNode.insert(new Point("p6", 512, 512), 0, 0, 1024);
        internalNode.insert(new Point("p7", 512, 0), 0, 0, 1024);
        internalNode.insert(new Point("p8", 0, 512), 0, 0, 1024);
        internalNode.insert(new Point("p1", 817, 74), 0, 0, 1024);
        internalNode.insert(new Point("p2", 721, 4), 0, 0, 1024);
        internalNode.insert(new Point("p3", 241, 990), 0, 0, 1024);
        internalNode.insert(new Point("p2", 771, 864), 0, 0, 1024);
        internalNode.insert(new Point("p2", 980, 590), 0, 0, 1024);
        internalNode.insert(new Point("p3", 512, 512), 0, 0, 1024);
        internalNode.insert(new Point("p2", 12, 890), 0, 0, 1024);
        internalNode.insert(new Point("p3", 50, 512), 0, 0, 1024);
        output.reset();
        internalNode.regionsearch(256, 256, 512, 512, 0, 0, 1024);
        assertEquals("Point Found: (p6, 512, 512)\r\n"
            + "Point Found: (p3, 512, 512)\r\n", output.toString());
        output.reset();
        internalNode.regionsearch(589, 0, 510, 510, 0, 0, 1024);
        assertEquals("Point Found: (p2, 721, 4)\r\n"
            + "Point Found: (p1, 817, 74)\r\n", output.toString());
        output.reset();
        internalNode.regionsearch(0, 589, 510, 510, 0, 0, 1024);
        assertEquals("Point Found: (p3, 21, 990)\r\n"
            + "Point Found: (p3, 241, 990)\r\n"
            + "Point Found: (p2, 12, 890)\r\n", output.toString());
        output.reset();
        internalNode.regionsearch(589, 589, 510, 510, 0, 0, 1024);
        assertEquals("Point Found: (p2, 980, 590)\r\n"
            + "Point Found: (p4, 721, 864)\r\n"
            + "Point Found: (p2, 771, 864)\r\n", output.toString());
        output.reset();
        internalNode.regionsearch(59, 58, 510, 30, 0, 0, 1024);
        assertEquals("Point Found: (p1, 217, 74)\r\n", output.toString());
    }


    /**
     * Test duplicates method
     */
    @Test
    public void testDuplicates() {
        internalNode.insert(new Point("p1", 217, 74), 0, 0, 1024);
        internalNode.insert(new Point("p2", 721, 4), 0, 0, 1024);
        internalNode.insert(new Point("p3", 721, 4), 0, 0, 1024);
        internalNode.insert(new Point("p2", 721, 864), 0, 0, 1024);
        internalNode.insert(new Point("p2", 0, 0), 0, 0, 1024);
        internalNode.insert(new Point("p3", 512, 512), 0, 0, 1024);
        internalNode.insert(new Point("p2", 217, 74), 0, 0, 1024);
        internalNode.insert(new Point("p3", 0, 512), 0, 0, 1024);
        output.reset();
        internalNode.duplicates();
        assertEquals("(217, 74)\r\n" + "(721, 4)\r\n", output.toString());
    }


    /**
     * Test dump
     */
    @Test
    public void testDump() {
        output.reset();
        internalNode.dump("", 0, 0, 1024);
        assertEquals("Node at 0, 0, 1024: Internal\r\n"
            + "  Node at 0, 0, 512: Empty\r\n"
            + "  Node at 512, 0, 512: Empty\r\n"
            + "  Node at 0, 512, 512: Empty\r\n"
            + "  Node at 512, 512, 512: Empty\r\n", output.toString());
        internalNode.insert(new Point("p1", 217, 74), 0, 0, 1024);
        internalNode.insert(new Point("p2", 721, 4), 0, 0, 1024);
        internalNode.insert(new Point("p3", 721, 4), 0, 0, 1024);
        internalNode.insert(new Point("p2", 721, 864), 0, 0, 1024);
        internalNode.insert(new Point("p2", 0, 0), 0, 0, 1024);
        internalNode.insert(new Point("p3", 512, 512), 0, 0, 1024);
        internalNode.insert(new Point("p2", 217, 74), 0, 0, 1024);
        internalNode.insert(new Point("p3", 0, 512), 0, 0, 1024);
        output.reset();
        internalNode.dump("", 0, 0, 1024);
        assertEquals("Node at 0, 0, 1024: Internal\r\n"
            + "  Node at 0, 0, 512:\r\n" + "  (p1, 217, 74)\r\n"
            + "  (p2, 0, 0)\r\n" + "  (p2, 217, 74)\r\n"
            + "  Node at 512, 0, 512:\r\n" + "  (p2, 721, 4)\r\n"
            + "  (p3, 721, 4)\r\n" + "  Node at 0, 512, 512:\r\n"
            + "  (p3, 0, 512)\r\n" + "  Node at 512, 512, 512:\r\n"
            + "  (p2, 721, 864)\r\n" + "  (p3, 512, 512)\r\n", output
                .toString());

    }
    
    /**
     * The extreme test
     */
    @Test
    public void testEx() {
        QuadNode qn = QuadNull.get();
        qn = qn.insert(new Point("dup_1", 10, 30), 0, 0, 1024).getNode();
        assertEquals(qn.getClass(), QuadLeaf.class);

        qn = qn.insert(new Point("dup_2", 10, 30), 0, 0, 1024).getNode();
        assertEquals(qn.getClass(), QuadLeaf.class);

        qn = qn.insert(new Point("dup_3", 10, 30), 0, 0, 1024).getNode();
        assertEquals(qn.getClass(), QuadLeaf.class);

        qn = qn.insert(new Point("other", 50, 50), 0, 0, 1024).getNode();
        assertEquals(qn.getClass(), QuadInternalNode.class);

        qn = qn.insert(new Point("dup_1", 1000, 30), 0, 0, 1024).getNode();
        assertEquals(qn.getClass(), QuadInternalNode.class);

        qn = qn.insert(new Point("dup_2", 1000, 30), 0, 0, 1024).getNode();
        qn = qn.insert(new Point("dup_3", 1000, 30), 0, 0, 1024).getNode();
        qn = qn.insert(new Point("other", 500, 900), 0, 0, 1024).getNode();
        qn = qn.insert(new Point("more", 800, 200), 0, 0, 1024).getNode();

        // start remove
        qn = qn.remove(new Point(null, 10, 30), 0, 0, 1024).getNode();
        qn = qn.remove(new Point(null, 10, 30), 0, 0, 1024).getNode();
        qn = qn.remove(new Point(null, 50, 50), 0, 0, 1024).getNode();
        assertEquals(qn.getClass(), QuadInternalNode.class);

        qn = qn.remove(new Point(null, 10, 30), 0, 0, 1024).getNode();
        assertEquals(qn.getClass(), QuadInternalNode.class);

        qn = qn.remove(new Point(null, 1000, 30), 0, 0, 1024).getNode();
        assertEquals(qn.getClass(), QuadInternalNode.class);
        // test merge internal into leaf
        qn = qn.remove(new Point(null, 500, 900), 0, 0, 1024).getNode();
        assertEquals(qn.getClass(), QuadLeaf.class);

        qn = qn.remove(new Point(null, 800, 200), 0, 0, 1024).getNode();
        assertEquals(qn.getClass(), QuadLeaf.class);
        // test merge leaf into empty
        qn = qn.remove(new Point(null, 1000, 30), 0, 0, 1024).getNode();
        qn = qn.remove(new Point(null, 1000, 30), 0, 0, 1024).getNode();
        assertEquals(qn.getClass(), QuadNull.class);

        qn = qn.insert(new Point("more", 800, 800), 0, 0, 1024).getNode();
        qn = qn.remove(new Point(null, 800, 800), 0, 0, 1024).getNode();
        output.reset();
    }
}
