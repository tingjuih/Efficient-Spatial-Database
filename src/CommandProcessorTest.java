import java.io.ByteArrayOutputStream;
import org.junit.Before;
import org.junit.Test;
/**
 * This junit test case is for CommandProcessor object.
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-10-16
 */
public class CommandProcessorTest extends student.TestCase {
    private CommandProcessor processor;
    private ByteArrayOutputStream output;
    /**
     * Initialize the CommandProcessor and and set output listener for the test.
     */
    @Before
    public void setUp() throws Exception {
        processor = new CommandProcessor();
        output = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(output));
    }

    /**
     * Test processor
     */
    @Test
    public void testProcessor() {
        output.reset();
        processor.processor("insert p_p          -1 -20");
        processor.processor("insert p        7 -8");
        processor.processor("duplicates");
        processor.processor("dump");
        processor.processor("search p_p");
        processor.processor("##");
        processor.processor("remove p_p");
        processor.processor("remove 1 -1");
        processor.processor("regionsearch   -5 -5 20 20 ");
        processor.processor("regionsearch 5    5   4   -2");
        assertEquals("Point REJECTED: (p_p, -1, -20)\r\n"
            + "Point REJECTED: (p, 7, -8)\r\n"
            + "Duplicate Points:\r\n"
            + "SkipList Dump:\r\n"
            + "level: 1 Value: null\r\n"
            + "The SkipList's Size is: 0\r\n"
            + "QuadTree Dump:\r\n"
            + "Node at 0, 0, 1024: Empty\r\n"
            + "QuadTree Size: 1 QuadTree Nodes Printed.\r\n"
            + "Point Not Found: p_p\r\n"
            + "point Not Removed: p_p\r\n"
            + "Point Rejected: (1, -1)\r\n"
            + "Points Intersecting Region: (-5, -5, 20, 20)\r\n"
            + "1 QuadTree Nodes Visited\r\n"
            + "Rectangle Rejected: (5, 5, 4, -2)\r\n", output.toString());
    }
}
