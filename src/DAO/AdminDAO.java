package DAO;
import java.util.ArrayList;
import java.util.LinkedList;
import model.Admin;

/**
 * Represents data access related to admin account
 *
 */
public class AdminDAO extends BaseDAO {
    /**
     * For readability, we declare FILEPATH
     */
    String FILEPATH = "src/database/User/Admins.csv";

    /**
     * Saves updated state of the application to the database
     * @param instances The admin object that contains admin account
     */
    public void save(ArrayList<Admin> instances) {
        emptyFile(FILEPATH);
        String header = "adminUsername,adminPassword";
        writeLine(FILEPATH, header);
        String writeStr = "";
        for (int i = 0; i < instances.size(); i++) {
            Admin instance = instances.get(i);
            writeStr = String.format("%s,%s",
                instance.getUsername(), instance.getPassword()
            );
            writeLine(FILEPATH, writeStr);
        }
    }

    /**
     * Loads admin account and put it in array of Admin object
     * @return The admin object fetched from database
     */
    public ArrayList<Admin> load() {
        LinkedList<String> instances = this.getData(FILEPATH);
        ArrayList<Admin> returnList = new ArrayList<Admin>();
        for (int i = 1; i < instances.size(); i++) {
            String[] x = instances.get(i).split(",");
            Admin newInstance = new Admin(
                x[0], x[1]
            );
            returnList.add(newInstance);
        }
        return returnList;
    }
}