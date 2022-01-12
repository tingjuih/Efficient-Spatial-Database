/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project spec.
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-09-18
 */
public class CommandProcessor {

    // the database object to manipulate the
    // commands that the command processor
    // feeds to it
    private Database data;

    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
     */
    public CommandProcessor() {
        data = new Database();
    }


    /**
     * This method identifies keywords in the line and calls methods in the
     * database as required. Each line command will be specified by one of the
     * keywords to perform the actions within the database required. These
     * actions are performed on specified objects and include insert, remove,
     * regionsearch, search, intersections, and dump. If the command in the file
     * line is not
     * one of these, an appropriate message will be written in the console. This
     * processor method is called for each line in the file. Note that the
     * methods called will themselves write to the console, this method does
     * not, only calling methods that do.
     * 
     * @param line
     *            a single line from the text file
     */
    public void processor(String line) {
        String[] commends = line.trim().split("\\s+");
        String commend = commends[0];
        if (commend.equals("dump")) {
            data.dump();
        }
        else if (commend.equals("insert")) {
            data.insert(new KVPair<>(commends[1], new Point(commends[1], Integer
                .parseInt(commends[2]), Integer.parseInt(commends[3]))));
        }
        else if (commend.equals("remove")) {
            if (commends.length == 2) {
                data.remove(commends[1]);
            }
            else {
                data.remove(Integer.parseInt(commends[1]), Integer.parseInt(
                    commends[2]));
            }
        }
        else if (commend.equals("regionsearch")) {
            data.regionsearch(Integer.parseInt(commends[1]), Integer.parseInt(
                commends[2]), Integer.parseInt(commends[3]), Integer.parseInt(
                    commends[4]));
        }
        else if (commend.equals("search")) {
            data.search(commends[1]);
        }
        else if (commend.equals("duplicates")) {
            data.duplicates();
        }
    }

}
