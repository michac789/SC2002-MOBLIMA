package DAO;
import java.util.ArrayList;
import java.util.LinkedList;
import model.Booking;

/**
 * Represents data access object related to booking information
 * @version 1.0
 * @since 2022-11-13
 *
 */
public class BookingDAO extends BaseDAO {
    /**
     * Base path of booking database for readability
     */
    private String BASEPATH = "src/database/User/Booking/";


    /**
     * Saves updated booking state of the application to the database
     * @param instances The array of Booking object that represents one booking history of specific moviegoer
     * @param movieGoerId The ID of movie goer
     */
    public void save(ArrayList<Booking> instances, int movieGoerId) {
        String FILEPATH = BASEPATH + movieGoerId + ".csv";
        emptyFile(FILEPATH);
        String header = "userId,movieTitle,cineplexLocation,cinemaCode,datetime,seatCode,price";
        writeLine(FILEPATH, header);
        String writeStr = "";
        for (int i = 0; i < instances.size(); i++) {
            Booking instance = instances.get(i);
            writeStr = String.format("%d,%s,%s,%d,%s,%s,%.2f",
                instance.getUserId(), instance.getTitle(),
                instance.getLocation(), instance.getCinemaCode(),
                instance.getDatetime().replace(",", "\\"),
                instance.getSeat(), instance.getPrice()
            );
            writeLine(FILEPATH, writeStr);
        }
    }

    /**
     * Loads booking of particular moviegoer and put it in an array of Booking object
     * @param movieGoerId The moviegoer's ID whose data which to be fetched
     * @return ArrayList of Booking object fetched from database
     */
    public ArrayList<Booking> load(int movieGoerId) {
        String FILEPATH = BASEPATH + movieGoerId + ".csv";
        LinkedList<String> instances = this.getData(FILEPATH);
        ArrayList<Booking> returnList = new ArrayList<Booking>();
        for (int i = 1; i < instances.size(); i++) {
            String[] x = instances.get(i).split(",");
            Booking newInstance = new Booking(
                Integer.parseInt(x[0]), x[1], x[2],
                Integer.parseInt(x[3]),
                x[4].replace("\\", ","),
                x[5], Float.parseFloat(x[6])
            );
            returnList.add(newInstance);
        }
        return returnList;
    }
}