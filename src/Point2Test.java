import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.Test;
/**
 * This junit test case is for Point2 object.
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-10-16
 */
public class Point2Test extends student.TestCase {

    private Point2 point2;
    private String answer;
    private ByteArrayOutputStream output;

    /**
     * Initialize Point2, read the answer from mytestOutput.txt, and set
     * output listener for the test.
     */
    public void setUp() {
        point2 = new Point2();
        output = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(output));
        File file = null;
        try {
            Scanner scanner = null;
            file = new File("./Data/P2test2Output.txt");
            scanner = new Scanner(file);
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
                sb.append("\n");
            }
            answer = sb.toString();
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Invalid file");
            e.printStackTrace();
        }
    }


    /**
     * Load commends from the file and execute the commands. After finishing
     * the commands, the program will compare the output with "mytestOutput.txt"
     */
    @SuppressWarnings("static-access")
    @Test
    public void testMain() {
        point2.main(new String[] { "./Data/P2test2.txt" });
        assertEquals(answer, output.toString().replaceAll("\r", ""));
    }


    /**
     * Test exception if the directory is invalid.
     */
    public void testException() {
        try {
            Point2.main(new String[] { System.getProperty("user.dir")
                + "/Data/mytest.txjt" });
        }
        catch (Exception e) {
            fail("Trigger Fail");
        }
    }
}
