package DAO;
import java.util.ArrayList;
import java.util.LinkedList;
import model.Cineplex;

/**
 * Represents data access object related to cineplexes
 * @version 1.0
 * @since 2022-11-13
 */
public class CineplexDAO extends BaseDAO {
    /**
     * File path for particular cineplex database for readability
     */
    String FILEPATH = "src/database/Cineplex/Cineplexes.csv";

    /**
     * Saves updated cineplex state of application to the database
     * @param instances The array of Cineplex object that represents one cineplex
     */
    public void save(ArrayList<Cineplex> instances) {
        emptyFile(FILEPATH);
        String header = "cineplexId,location";
        writeLine(FILEPATH, header);
        String writeStr = "";
        for (int i = 0; i < instances.size(); i++) {
            Cineplex instance = instances.get(i);
            writeStr = String.format("%d,%s",
                instance.getCineplexId(), instance.getLocation()
            );
            writeLine(FILEPATH, writeStr);
            instance.getController().save();
        }
    }

    /**
     * Loads cineplexes and put it in array of Cineplex object
     * @return ArrayList of cineplex object fetched from database
     */
    public ArrayList<Cineplex> load() {
        LinkedList<String> instances = getData(FILEPATH);
        ArrayList<Cineplex> returnList = new ArrayList<Cineplex>();
        for (int i = 1; i < instances.size(); i++) {
            String[] x = instances.get(i).split(",");
            Cineplex newInstance = new Cineplex(x[1]);
            returnList.add(newInstance);
        }
        return returnList;
    }
}