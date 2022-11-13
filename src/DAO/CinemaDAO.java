package DAO;
import java.util.ArrayList;
import java.util.LinkedList;
import model.Cinema;

/**
 * Represents data access object related to cinemas
 * @version 1.0
 * @since 2022-11-13
 */
public class CinemaDAO extends BaseDAO {
    /**
     * Base path of cinema database for readability
     */
    String BASEPATH = "src/database/Cineplex/";
    /**
     * File path for particular cinemas database in one cineplex
     */
    String FILEPATH;

    /**
     * Saves updated cinema state of the application to the database
     * @param instances The array of Cinema object that represents one cinema of specific cineplex
     * @param cineplexId The ID of cineplex
     */
    public void save(ArrayList<Cinema> instances, int cineplexId) {
        FILEPATH = BASEPATH + cineplexId + "/Cinemas.csv";
        emptyFile(FILEPATH);
        String header = "cineplexId,cinemaCode,height,width,cinemaClass,seatConfiguration";
        writeLine(FILEPATH, header);
        String writeStr = "";
        for (int i = 0; i < instances.size(); i++) {
            Cinema instance = instances.get(i);
            writeStr = String.format("%d,%d,%d,%s,%s,%s",
                instance.getCineplexId(), instance.getCinemaCode(),
                instance.getHeight(), instance.getWidth(),
                instance.getCinemaClass(), instance.getSeatConfiguration()
            );
            writeLine(FILEPATH, writeStr);
            instance.getController().save();
        }
    }

    /**
     * Loads cinema of particular cineplex and put it in array of Cinema object
     * @param cineplexId The ID of cineplex
     * @return ArrayList of Cinema object fetched from database
     */
    public ArrayList<Cinema> load(int cineplexId) {
        FILEPATH = BASEPATH + cineplexId + "/Cinemas.csv";
        LinkedList<String> instances = getData(FILEPATH);
        ArrayList<Cinema> returnList = new ArrayList<Cinema>();
        for (int i = 1; i < instances.size(); i++) {
            String[] x = instances.get(i).split(",");
            Cinema newInstance = new Cinema(
                Integer.parseInt(x[0]),
                Integer.parseInt(x[1]),
                Integer.parseInt(x[2]),
                Integer.parseInt(x[3]),
                Cinema.showClassOptions.valueOf(x[4]),
                x[5]
            );
            returnList.add(newInstance);
        }
        return returnList;
    }
}