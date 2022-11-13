package DAO;
import java.util.ArrayList;
import java.util.LinkedList;
import model.Admin;

/**
 * Represents data access object related to admin account
 * @version 1.0
 * @since 2022-11-13
 *
 */
public class AdminDAO extends BaseDAO {
    /**
     * Filepath for admin database for readability
     */
    String FILEPATH = "src/database/User/Admins.csv";

    /**
     * Saves updated admin account state of the application to the database
     * @param instances The array of admin object that represents admin account
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
     * Loads admin account and put it in an array of Admin object
     * @return ArrayList of admin object fetched from database
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