package DAO;
import java.util.ArrayList;
import java.util.LinkedList;
import model.Review;

/**
 * Represeents data access object related to review
 * @version 1.0
 * @since 2022-11-13
 */
public class ReviewDAO extends BaseDAO {
    /**
     * Base path of review database for readability
     */
    String BASEPATH = "src/database/Movie/Review/";

    /**
     * Save update review state of the application to the database
     * @param instances The array of Review object that represents one review of specific movie
     * @param movieId The ID of the movie
     */
    public void save(ArrayList<Review> instances, int movieId) {
        String FILEPATH = BASEPATH + movieId + ".csv";
        emptyFile(FILEPATH);
        String header = "movieGoerId,rating,comment";
        writeLine(FILEPATH, header);
        String writeStr = "";
        for (int i = 0; i < instances.size(); i++) {
            Review instance = instances.get(i);
            writeStr = String.format(
                "%d,%d,\"%s\"",
                instance.getUserId(), instance.getRating(),
                instance.getComment().replace(",", "\\comma")
            );
            writeLine(FILEPATH, writeStr);
        }
    }

    /**
     * Loads reviews of particular movie anb put it on array of Review object
     * @param movieId The ID of the movie
     * @return ArrayList of Review object fetched from database
     */
    public ArrayList<Review> load(int movieId) {
        String FILEPATH = BASEPATH + movieId + ".csv";
        LinkedList<String> instances = this.getData(FILEPATH);
        ArrayList<Review> returnList = new ArrayList<Review>();
        for (int i = 1; i < instances.size(); i++) {
            String[] x = instances.get(i).split(",");
            x[2] = x[2].substring(1, x[2].length() - 1)
                .replace("\\comma", ",");
            Review newInstance = new Review(
                Integer.parseInt(x[0]), Integer.parseInt(x[1]), x[2]
            );
            returnList.add(newInstance);
        }
        return returnList;
    }
}