package boundary;
import java.util.Scanner;
import java.util.ArrayList;
import controller.AppController;
import model.Cineplex;

public class CineplexUI {
    private static Scanner sc = new Scanner(System.in);

    public static void admin() {
        while (true) {
            System.out.println("|=========|Cineplex Admin|=========|");
            System.out.print(
                "1. Display All Cineplexes\n" +
                "2. Create New Cineplex\n" +
                "3. Edit Existing Cineplex\n" +
                "4. Exit\n");
            System.out.print("Select action: ");  
            int choice = sc.nextInt();
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
        System.out.println("Enter new cineplex location:");
        sc.nextLine();
        String location = sc.nextLine();
        AppController.cc.createCineplex(location);
        System.out.println("Cineplex successfully created!\n");
    }

    private static void editCineplex() {
        int id;
        while (true) {
            System.out.println("Enter cineplex ID:");
            id = sc.nextInt();
            if (id > 0 && id <= Cineplex.getNumCineplex()) {
                break;
            }
            System.out.println("Invalid ID");
        }
        sc.nextLine();
        System.out.println(AppController.cc.getCineplexById(id));
        System.out.println("Enter new cineplex location:");
        String newLocation = sc.nextLine();
        AppController.cc.editLocation(id, newLocation);
    }
}