import java.io.ByteArrayOutputStream;
import org.junit.Before;
import org.junit.Test;
/**
 * This junit test case is for Database object.
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-10-16
 */
public class DatabaseTest extends student.TestCase {
    private Database db;
    private ByteArrayOutputStream output;

    /**
     * Initialize the QuadInternalNode and and set output listener for the test.
     */
    @Before
    public void setUp() throws Exception {
        db = new Database();
        output = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(output));
        db.insert(new KVPair<>("p1", new Point("p1", 0, 12)));
        db.insert(new KVPair<>("p1", new Point("p2", 721, 12)));
        db.insert(new KVPair<>("p1", new Point("p3", 432, 712)));
        db.insert(new KVPair<>("p1", new Point("p4", 940, 812)));
    }


    /**
     * Test insert method
     */
    @Test
    public void testInsert() {
        output.reset();
        db.insert(new KVPair<>("p1", new Point("p1", 0, -12)));
        db.insert(new KVPair<>("p1", new Point("p1", 0, 12)));
        db.insert(new KVPair<>("p1", new Point("p1", 0, 12)));
        assertEquals("Point REJECTED: (p1, 0, -12)\r\n"
            + "Point REJECTED: (p1, 0, 12)\r\n"
            + "Point REJECTED: (p1, 0, 12)\r\n", output.toString());
    }


    /**
     * Test remove by name method
     */
    @Test
    public void testRemoveByName() {
        output.reset();
        db.remove("p0");
        db.remove("p1");
        assertEquals("point Not Removed: p0\r\n"
            + "Point (p4, 940, 812) Removed\r\n", output.toString());
    }


    /**
     * Test remove by values method
     */
    @Test
    public void testRemove() {
        output.reset();
        db.remove(0, -11);
        db.remove(0, 11);
        db.remove(0, 12);
        assertEquals("Point Rejected: (0, -11)\r\n"
            + "point Not Found: (0, 11)\r\n"
            + "Point (p1, 0, 12) Removed\r\n", output.toString());
    }


    /**
     * Test remove by values method
     */
    @Test
    public void testRegionsearch() {
        output.reset();
        db.regionsearch(0, 0, -10, 10);
        db.regionsearch(0, 0, 10, -10);
        db.regionsearch(660, 0, 300, 300);
        assertEquals("Rectangle Rejected: (0, 0, -10, 10)\r\n"
            + "Rectangle Rejected: (0, 0, 10, -10)\r\n"
            + "Points Intersecting Region: (660, 0, 300, 300)\r\n"
            + "Point Found: (p2, 721, 12)\r\n" + "2 QuadTree Nodes Visited\r\n",
            output.toString());
    }


    /**
     * Test remove by values method
     */
    @Test
    public void testDuplicates() {
        output.reset();
        db.duplicates();
        db.insert(new KVPair<>("p1", new Point("p2", 0, 12)));
        db.duplicates();
        assertEquals("Duplicate Points:\r\n" + "Point Inserted: (p2, 0, 12)\r\n"
            + "Duplicate Points:\r\n" + "(0, 12)\r\n", output.toString());
    }


    /**
     * Test search method
     */
    @Test
    public void testSearch() {
        output.reset();
        db.search("p");
        db.search("p1");
        assertEquals("Point Not Found: p\r\n"
            + "Point Found (p4, 940, 812)\r\n"
            + "Point Found (p3, 432, 712)\r\n"
            + "Point Found (p2, 721, 12)\r\n"
            + "Point Found (p1, 0, 12)\r\n",
            output.toString());
    }


    /**
     * Test dump method
     */
    @Test
    public void testDump() {
        output.reset();
        db.dump();
        assertEquals("SkipList Dump:\r\n" + "level: 2 Value: null\r\n"
            + "level: 2 Value: (p4, 940, 812)\r\n"
            + "level: 1 Value: (p3, 432, 712)\r\n"
            + "level: 2 Value: (p2, 721, 12)\r\n"
            + "level: 1 Value: (p1, 0, 12)\r\n"
            + "The SkipList's Size is: 4\r\n" + "QuadTree Dump:\r\n"
            + "Node at 0, 0, 1024: Internal\r\n" + "  Node at 0, 0, 512:\r\n"
            + "  (p1, 0, 12)\r\n" + "  Node at 512, 0, 512:\r\n"
            + "  (p2, 721, 12)\r\n" + "  Node at 0, 512, 512:\r\n"
            + "  (p3, 432, 712)\r\n" + "  Node at 512, 512, 512:\r\n"
            + "  (p4, 940, 812)\r\n"
            + "QuadTree Size: 5 QuadTree Nodes Printed.\r\n", output
                .toString());
    }
}
