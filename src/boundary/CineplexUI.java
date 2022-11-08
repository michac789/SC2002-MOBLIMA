package boundary;
import java.util.Scanner;
import controller.CineplexController;

public class CineplexUI {
    private static Scanner sc = new Scanner(System.in);
    private static CineplexController cc = new CineplexController();

    public static void admin() {
        System.out.println("TODO");
        // called from adminUI
        // ask to display all cineplex / create / update only
        // then call the respective function defined below
        // displayAllCineplexes();
        // createCineplex();
        // editCineplex();
    }

    private static void displayAllCineplexes() {
        // TODO - admin functionality
        // display all cineplexes (id and location)
    }

    private static void createCineplex() {
        // TODO - admin functionality
        // prompt for new cineplex location
        // call the function below
        // cc.createCineplex("some location");
    }

    private static void editCineplex() {
        // TODO - admin functionality
        // prompt for cineplex id
        // it should be between 1 and Cineplex.getNumCineplex()
        // if valid ask for new location
        // call the function below
        // cc.editLocation(cineplexId, newLocation);
    }
}