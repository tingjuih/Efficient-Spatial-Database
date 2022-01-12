import java.io.ByteArrayOutputStream;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * This junit test case is for SkipList object. It will test insert, search,
 * remove, removeByValue, dump, and toString methods.
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-09-25
 */
public class SkipListTest extends student.TestCase {
    private SkipList<String, Point> list;
    private ByteArrayOutputStream output;

    /**
     * Initialize the skip list and output listener for the test. The skip list
     * initially contains r2 ~ r5 nodes.
     */
    @Before
    public void setUp() {
        output = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(output));
        list = new SkipList<>();
        list.insert(new KVPair<>("r5", new Point("r5", 155, 25)));
        list.insert(new KVPair<>("r2", new Point("r2", 15, 15)));
        list.insert(new KVPair<>("r4", new Point("r4", 5, 50)));
        list.insert(new KVPair<>("r3", new Point("r3", 100, 105)));
        list.insert(new KVPair<>("r6", new Point("r6", 15, 75)));
    }


    /**
     * Test the insert method with unordered insertion behaviors.
     */
    @Test
    public void testInsert() {
        list.insert(new KVPair<>("r6", new Point("r6", 215, 275)));
        list.insert(new KVPair<>("r2", new Point("r2", 545, 53)));
        list.insert(new KVPair<>("r1", new Point("r1", 105, 5)));
        assertEquals("level: 7 Value: null\r\n"
            + "level: 2 Value: (r1, 105, 5)\r\n"
            + "level: 1 Value: (r2, 545, 53)\r\n"
            + "level: 2 Value: (r2, 15, 15)\r\n"
            + "level: 2 Value: (r3, 100, 105)\r\n"
            + "level: 1 Value: (r4, 5, 50)\r\n"
            + "level: 1 Value: (r5, 155, 25)\r\n"
            + "level: 1 Value: (r6, 215, 275)\r\n"
            + "level: 7 Value: (r6, 15, 75)\r\n" + "The SkipList's Size is: 8",
            list.toString());
    }


    /**
     * Test the search method with not found and multiple result cases. And test
     * the result after removal.
     */
    @Test
    public void testSearch() {
        assertTrue(list.search("r0").isEmpty());
        assertTrue(list.search("r8").isEmpty());
        List<KVPair<String, Point>> pairs = list.search("r2");
        assertEquals(1, pairs.size());
        assertEquals("r2", pairs.get(0).getKey());
        list.insert(new KVPair<>("r2", new Point("r2", 1565, 53)));
        list.insert(new KVPair<>("r3", new Point("r3", 165, 53)));
        list.insert(new KVPair<>("r4", new Point("r4", 1685, 53)));
        list.insert(new KVPair<>("r5", new Point("r5", 15, 53)));
        pairs = list.search("r4");
        assertEquals(2, pairs.size());
        for (KVPair<String, Point> pair : pairs) {
            assertEquals("r4", pair.getKey());
        }

        list.remove("r5");
        list.remove("r4");
        list.remove("r6");
        list.remove("r3");
        list.remove("r3");
        list.remove("r2");
        list.remove("r2");
        list.remove("r2");
        assertTrue(list.search("r8").isEmpty());
        list.remove("r5");
        list.remove("r4");
        assertTrue(list.search("r8").isEmpty());
    }


    /**
     * Test remove method with absent keys and exist keys.
     */
    @Test
    public void testRemove() {
        assertEquals(null, list.remove("r1"));
        assertEquals(null, list.remove("r9"));
        assertEquals("(r4, 5, 50)", list.remove("r4").toString());
        assertEquals("(r2, 15, 15)", list.remove("r2").toString());
    }


    /**
     * Test removeByValue method with absent and exist values.
     */
    @Test
    public void testRemoveByValue() {
        assertEquals(null, list.removeByValue(new Point("r2", 15, 159)));
        assertEquals(null, list.removeByValue(new Point("r3", 1005, 15)));
        assertEquals("(r2, 15, 15)", list.removeByValue(new Point("r2", 15, 15))
            .toString());
        assertEquals("(r3, 100, 105)", list.removeByValue(new Point("r3", 100,
            105)).toString());
        list.insert(new KVPair<>("r4", new Point("r4", 215, 275)));
        list.insert(new KVPair<>("r4", new Point("r4", 545, 53)));
        list.insert(new KVPair<>("r4", new Point("r4", 1005, 5)));
        list.insert(new KVPair<>("r4", new Point("r4", 1025, 5)));
        list.insert(new KVPair<>("r4", new Point("r4", 105, 52)));
        list.insert(new KVPair<>("r4", new Point("r4", 105, 55)));
        list.insert(new KVPair<>("r4", new Point("r4", 105, 75)));
        list.insert(new KVPair<>("r4", new Point("r4", 1058, 5)));
        list.insert(new KVPair<>("r4", new Point("r4", 1805, 5)));
        list.insert(new KVPair<>("r4", new Point("r4", 105, 58)));
        list.insert(new KVPair<>("r4", new Point("r4", 1435, 435)));
        list.insert(new KVPair<>("r4", new Point("r4", 185, 85)));
        list.insert(new KVPair<>("r4", new Point("r4", 125, 5)));
        assertEquals("(r4, 1058, 5)", list.removeByValue(new Point("r4", 1058,
            5)).toString());
        list.insert(new KVPair<>("r2", new Point("r2", 215, 275)));
        list.insert(new KVPair<>("r3", new Point("r3", 545, 53)));
        list.insert(new KVPair<>("r2", new Point("r2", 1005, 5)));
        list.insert(new KVPair<>("r1", new Point("r1", 1025, 5)));
        list.insert(new KVPair<>("r0", new Point("r0", 105, 52)));
        list.insert(new KVPair<>("r0", new Point("r0", 105, 55)));
        list.insert(new KVPair<>("r3", new Point("r3", 105, 75)));
        list.insert(new KVPair<>("r2", new Point("r2", 1058, 5)));
        list.insert(new KVPair<>("r1", new Point("r1", 1805, 5)));
        list.insert(new KVPair<>("r0", new Point("r0", 105, 58)));
        list.insert(new KVPair<>("r3", new Point("r3", 1435, 435)));
        list.insert(new KVPair<>("r2", new Point("r2", 1895, 85)));
        list.insert(new KVPair<>("r1", new Point("r1", 125, 5)));
        assertEquals("(r4, 185, 85)", list.removeByValue(new Point("r4", 185,
            85)).toString());
    }


    /**
     * Test remove by pair method
     */
    @Test
    public void testRemoveByKVPair() {
        KVPair<String, Point> testPair1 = new KVPair<>("r2", new Point("r2",
            1895, 86));
        KVPair<String, Point> testPair2 = new KVPair<>("r2", new Point("r2",
            1895, 85));
        KVPair<String, Point> testPair3 = new KVPair<>("r8", new Point("r8",
            1895, 85));
        assertEquals(null, list.remove(testPair3));
        list.remove("r2");
        list.remove("r3");
        list.remove("r4");
        list.remove("r5");
        list.remove("r6");
        
        assertEquals(null, list.remove(testPair1));
        list.insert(new KVPair<>("r4", new Point("r4", 215, 275)));
        list.insert(new KVPair<>("r4", new Point("r4", 545, 53)));
        list.insert(new KVPair<>("r4", new Point("r4", 1005, 5)));
        list.insert(new KVPair<>("r4", new Point("r4", 1025, 5)));
        list.insert(new KVPair<>("r4", new Point("r4", 105, 52)));
        list.insert(new KVPair<>("r4", new Point("r4", 105, 55)));
        list.insert(new KVPair<>("r4", new Point("r4", 105, 75)));
        list.insert(new KVPair<>("r4", new Point("r4", 1058, 5)));
        list.insert(new KVPair<>("r4", new Point("r4", 1805, 5)));
        list.insert(new KVPair<>("r4", new Point("r4", 105, 58)));
        list.insert(new KVPair<>("r4", new Point("r4", 1435, 435)));
        list.insert(new KVPair<>("r4", new Point("r4", 185, 85)));
        list.insert(new KVPair<>("r4", new Point("r4", 125, 5)));
        list.insert(new KVPair<>("r2", new Point("r2", 215, 275)));
        list.insert(new KVPair<>("r3", new Point("r3", 545, 53)));
        list.insert(new KVPair<>("r2", new Point("r2", 1005, 5)));
        list.insert(new KVPair<>("r1", new Point("r1", 1025, 5)));
        list.insert(new KVPair<>("r0", new Point("r0", 105, 52)));
        list.insert(new KVPair<>("r0", new Point("r0", 105, 55)));
        list.insert(new KVPair<>("r3", new Point("r3", 105, 75)));
        list.insert(new KVPair<>("r2", new Point("r2", 1058, 5)));
        list.insert(new KVPair<>("r1", new Point("r1", 1805, 5)));
        list.insert(new KVPair<>("r0", new Point("r0", 105, 58)));
        list.insert(new KVPair<>("r3", new Point("r3", 1435, 435)));
        list.insert(new KVPair<>("r2", new Point("r2", 1895, 85)));
        list.insert(new KVPair<>("r1", new Point("r1", 125, 5)));
        assertEquals(null, list.remove(testPair1));
        assertTrue(testPair2.equals(list.remove(testPair2)));
    }


    /**
     * Test dump method. The dump method should print "SkipList dump:",
     * nodes, and the size.
     */
    @Test
    public void testDump() {
        output.reset();
        list.dump();
        assertEquals("SkipList Dump:\r\n" + "level: 7 Value: null\r\n"
            + "level: 2 Value: (r2, 15, 15)\r\n"
            + "level: 2 Value: (r3, 100, 105)\r\n"
            + "level: 1 Value: (r4, 5, 50)\r\n"
            + "level: 1 Value: (r5, 155, 25)\r\n"
            + "level: 7 Value: (r6, 15, 75)\r\n"
            + "The SkipList's Size is: 5\r\n", output.toString());
        list.remove("r2");
        list.remove("r3");
        list.remove("r4");
        list.remove("r5");
        list.remove("r4");
        output.reset();
        list.dump();
        assertEquals("SkipList Dump:\r\n" + "level: 7 Value: null\r\n"
            + "level: 7 Value: (r6, 15, 75)\r\n"
            + "The SkipList's Size is: 1\r\n", output.toString());
    }


    /**
     * Test toString method. The dump method should print the nodes and followed
     * by the size.
     */
    @Test
    public void testToString() {
        assertEquals("level: 7 Value: null\r\n"
            + "level: 2 Value: (r2, 15, 15)\r\n"
            + "level: 2 Value: (r3, 100, 105)\r\n"
            + "level: 1 Value: (r4, 5, 50)\r\n"
            + "level: 1 Value: (r5, 155, 25)\r\n"
            + "level: 7 Value: (r6, 15, 75)\r\n" + "The SkipList's Size is: 5",
            list.toString());
        list.remove("r2");
        list.remove("r3");
        list.remove("r4");
        list.remove("r5");
        list.remove("r4");
        assertEquals("level: 7 Value: null\r\n"
            + "level: 7 Value: (r6, 15, 75)\r\n" + "The SkipList's Size is: 1",
            list.toString());
    }


    /**
     * Test the iterator. The output should be sorted by keys.
     */
    @Test
    public void testIterator() {
        StringBuilder elements = new StringBuilder();
        for (KVPair<String, Point> pair : list) {
            elements.append(pair);
            elements.append('\n');
        }
        assertEquals("(r2, 15, 15)\r\n" + "(r3, 100, 105)\r\n"
            + "(r4, 5, 50)\r\n" + "(r5, 155, 25)\r\n" + "(r6, 15, 75)\r\n",
            elements.toString());
    }


    /**
     * Test the size method.
     */
    @Test
    public void testSize() {
        assertEquals(5, list.size());
        list.remove("r2");
        assertEquals(4, list.size());
        list.remove("r3");
        assertEquals(3, list.size());
        list.remove("r4");
        assertEquals(2, list.size());
        list.remove("r5");
        assertEquals(1, list.size());
        list.remove("r6");
        assertEquals(0, list.size());
    }
}
