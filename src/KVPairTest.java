import org.junit.Before;
import org.junit.Test;

/**
 * This junit test case is for KVPair object. It will test getKey(), getValue(),
 * compareTo(), toString() method.
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-09-24
 */
public class KVPairTest extends student.TestCase {
    private KVPair<String, Point> pair1;
    private KVPair<String, Point> pair2;
    private KVPair<String, Point> pair3;
    private KVPair<String, Point> pair4;

    /**
     * Initialize 4 KVPairs for tests.
     */
    @Before
    public void setUp() {
        pair1 = new KVPair<>("s1", new Point("s1", 0, 1024));
        pair2 = new KVPair<>("s2", new Point("s2", 0, 10));
        pair3 = new KVPair<>("s2", new Point("s2", 10, 0));
        pair4 = new KVPair<>("s4", new Point("s4", 10, 10));
    }


    /**
     * Test the getKey() method.
     */
    @Test
    public void testGetKey() {
        assertEquals("s1", pair1.getKey());
        assertEquals("s2", pair2.getKey());
        assertEquals("s2", pair3.getKey());
        assertEquals("s4", pair4.getKey());
    }


    /**
     * Test the getValue() method.
     */
    @Test
    public void testGetValue() {
        assertTrue(pair1.getValue().equals(new Point("s1", 0, 1024)));
        assertTrue(pair2.getValue().equals(new Point("s2", 0, 10)));
        assertTrue(pair3.getValue().equals(new Point("s2", 10, 0)));
        assertTrue(pair4.getValue().equals(new Point("s4", 10, 10)));
    }


    /**
     * Test the compareTo() method.
     */
    @Test
    public void testCompareTo() {
        assertTrue(pair2.compareTo(pair1) > 0);
        assertEquals(0, pair2.compareTo(pair2));
        assertEquals(0, pair2.compareTo(pair3));
        assertTrue(pair2.compareTo(pair4) < 0);
    }


    /**
     * Test the equals() method.
     */
    public void testEquals() {
        assertTrue(pair1.equals(new KVPair<>("s1", new Point("s1", 0, 1024))));
        assertFalse(pair1.equals(pair2));
        assertFalse(pair3.equals(pair2));
        assertFalse(pair3.equals(QuadNull.get()));
    }


    /**
     * Test the toString() method.
     */
    @Test
    public void testToString() {
        assertEquals("(s1, 0, 1024)", pair1.toString());
        assertEquals("(s2, 0, 10)", pair2.toString());
        assertEquals("(s2, 10, 0)", pair3.toString());
        assertEquals("(s4, 10, 10)", pair4.toString());
    }

}
