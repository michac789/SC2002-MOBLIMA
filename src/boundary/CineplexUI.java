package boundary;
import java.util.ArrayList;
import controller.AppController;
import model.Cineplex;

public class CineplexUI {
    public static void admin() {
        while (true) {
            System.out.println("|=========|Cineplex Admin Panel|=========|");
            System.out.print(
                "1. Display All Cineplexes\n" +
                "2. Create New Cineplex\n" +
                "3. Edit Existing Cineplex\n" +
                "4. Exit\n");
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
                    System.out.println("Invalid action, try again!");
                    break;
            }
        }
    }

    private static void displayAllCineplexes() {
        ArrayList<Cineplex> cineplexes = AppController.cc.getAllCineplexes();
        System.out.println(String.format("Displaying all " +
            Cineplex.getNumCineplex() + " cineplexes:"));
        for (int i = 0; i < cineplexes.size(); i++) {
            System.out.println(cineplexes.get(i));
        }
        System.out.println("");
    }

    private static void createCineplex() {
        String location = UtilUI.getStr("Enter cineplex location: ");
        AppController.cc.createCineplex(location);
        System.out.println("Cineplex successfully created!\n");
    }

    private static void editCineplex() {
        int cineplexId = promptValidCineplexId();
        if (cineplexId == -1) { return;}
        System.out.println(AppController.cc.getCineplexById(cineplexId));
        String newLocation = UtilUI.getStr("Enter new cineplex location: ");
        AppController.cc.editLocation(cineplexId, newLocation);
    }

    public static int promptValidCineplexId() {
        int id;
        while (true) {
            id = UtilUI.getInt("Enter cineplex ID: (enter -1 to exit)");
            if (id == -1) { return -1;}
            if (id > 0 && id <= Cineplex.getNumCineplex()) {
                break;
            }
            System.out.println("Invalid ID");
        }
        return id;
    }
}