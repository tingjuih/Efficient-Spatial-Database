import org.junit.Before;
import org.junit.Test;

/**
 * This junit test case is for QuadPackage object. It will test all method in
 * QuadPackage
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-10-16
 */
public class QuadPackageTest extends student.TestCase {
    private QuadPackage quadpack;

    /**
     * Initialize the QuadPackage for testing
     */
    @Before
    public void setUp() throws Exception {
        quadpack = QuadPackage.get(null, null);
    }


    /**
     * Test the static getter
     */
    @Test
    public void testGet() {
        assertEquals(quadpack, QuadPackage.get(null, null));
    }


    /**
     * Test setters and getters
     */
    @Test
    public void testSetterAndGetterNode() {
        QuadNode node = QuadNull.get();
        quadpack.setNode(node);
        assertEquals(node, quadpack.getNode());
        Point point = new Point("", 1, 1);
        quadpack.setPoint(point);
        assertEquals(point, quadpack.getPoint());
    }
}
