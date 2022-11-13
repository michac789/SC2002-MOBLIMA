package DAO;
import java.util.ArrayList;
import java.util.LinkedList;
import model.MovieGoer;

/**
 * Represents data access object related to moviegoer account
 * @version 1.0
 * @since 2022-11-13
 *
 */
public class MovieGoerDAO extends BaseDAO {
    /**
     * Filepath for movigoer database for readability
     */
    String FILEPATH = "src/database/User/MovieGoers.csv";

    /**
     * Saves updated moviegoer state of the application to the database
     * @param instances The array of MovieGoer object that represents moviegoer account
     */
    public void save(ArrayList<MovieGoer> instances) {
        emptyFile(FILEPATH);
        String header = "id,username,password,email,phoneNumber,age";
        writeLine(FILEPATH, header);
        String writeStr = "";
        for (int i = 0; i < instances.size(); i++) {
            MovieGoer instance = instances.get(i);
            writeStr = String.format(
                "%d,%s,%s,%s,%s,%d",
                instance.getId(), instance.getUsername(), instance.getPassword(),
                instance.getEmail(), instance.getPhoneNumber(), instance.getAge()
            );
            writeLine(FILEPATH, writeStr);
            instance.getController().save();
        }
    }

    /**
     * Loads moviegoer account and put it on array of MovieGoer object
     * @return ArrayList of MovieGoer object fetched from database
     */
    public ArrayList<MovieGoer> load() {
        LinkedList<String> instances = this.getData(FILEPATH);
        ArrayList<MovieGoer> returnList = new ArrayList<MovieGoer>();
        for (int i = 1; i < instances.size(); i++) {
            String[] x = instances.get(i).split(",");
            MovieGoer newInstance = new MovieGoer(
                x[1], x[2], x[3], x[4], Integer.parseInt(x[5])
            );
            returnList.add(newInstance);
        }
        return returnList;
    }

    public void createUserBookingFile(int movieGoerId) {
        String BASEPATH = "src/database/User/Booking/";
        UtilDAO.createFile(BASEPATH + movieGoerId + ".csv");
    }
}