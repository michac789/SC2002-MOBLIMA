package boundary;
import java.util.ArrayList;
import controller.AppController;
import model.Cineplex;

public class CineplexUI {
    /*
     * Called from Admin UI, provide cineplex configuration for admin as follows:
     * - Displaying all current cineplexes
     * - Create new cineplex
     * - Edit location of existing cineplex
     */
    public static void admin() {
        while (true) {
            UtilUI.printBlue("\n|=========|Cineplex Admin Panel|=========|");
            System.out.print(
                "(1) Display All Cineplexes\n" +
                "(2) Create New Cineplex\n" +
                "(3) Change Existing Cineplex Location Name\n" +
                "(4) Exit\n");
            int choice = UtilUI.getInt("Select action: ");
            switch (choice) { 
                case 1:
                    displayAllCineplexes();
                    break;
                case 2:
                    createCineplex();
                    break;
                case 3:
                    editCineplex();
                    break;
                case 4:
                    return;
                default:
                    UtilUI.printRed("Invalid action, try again!");
                    break;
            }
        }
    }

    /*
     * Display all cineplexes (ID & location)
     */
    public static void displayAllCineplexes() {
        ArrayList<Cineplex> cineplexes = AppController.cc.getAllCineplexes();
        UtilUI.printBlue(String.format("Displaying all " +
            Cineplex.getNumCineplex() + " cineplexes:"));
        for (int i = 0; i < cineplexes.size(); i++) {
            System.out.println(cineplexes.get(i));
        }
    }

    /*
     * Create new cineplex
     */
    private static void createCineplex() {
        UtilUI.printBlue("Create Cineplex");
        String location = UtilUI.getStr("Enter new cineplex location: (enter -1 to cancel) ");
        if (location.equals("-1")) { return;}
        AppController.cc.createCineplex(location);
        UtilUI.printGreen("Cineplex successfully created!");
    }

    /*
     * Prompt admin of a valid cineplex ID
     * Edit the location of that cineplex ID and save it
     */
    private static void editCineplex() {
        UtilUI.printBlue("Edit Cineplex Location Name");
        int cineplexId = promptValidCineplexId();
        if (cineplexId == -1) { return;}
        System.out.println(AppController.cc.getCineplexById(cineplexId));
        String newLocation = UtilUI.getStr("Enter new location name: (enter -1 to cancel) ");
        if (newLocation.equals("-1")) { return;}
        AppController.cc.editLocation(cineplexId, newLocation);
        UtilUI.printGreen("Cineplex editing successful!");
    }

    /*
     * Utility funtion to prompt user for valid cineplex ID
     * Return the CineplexId
     */
    public static int promptValidCineplexId() {
        int id;
        while (true) {
            id = UtilUI.getInt("Enter cineplex ID: (enter -1 to exit) ");
            if (id == -1) {
                System.out.println("");
                return -1;
            }
            if (id > 0 && id <= Cineplex.getNumCineplex()) {
                break;
            }
            UtilUI.printRed("Invalid ID");
        }
        return id;
    }
}