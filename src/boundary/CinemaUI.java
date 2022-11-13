package boundary;
import controller.AppController;
import controller.CinemaController;
import model.Cinema;
/**
 * Represents the boundary of cinema viewing & editing
 * Consisting of multiple methods which interact directly with cinemaController
 * @version 1.0
 * @since 2022-11-13
 */
public class CinemaUI {
    /**
     * Main function that is called when admin wants to look for cinema detailed view and create new cinema
     * Admin must be authenticated first before proceed further here
     */
    public static void admin() {
        CineplexUI.displayAllCineplexes();
        int id = CineplexUI.promptValidCineplexId();
        if (id == -1) { return;}
        CinemaController cc = AppController.cc.getCineplexById(id).getController();
        while (true) {
            UtilUI.printBlue("\n|=========|Cinema Admin Panel|=========|");
            System.out.println(AppController.cc.getCineplexById(id));
            System.out.print(
                "(1) Cinema Detailed View\n" +
                "(2) Create New Cinema\n" +
                "(3) Exit\n");
            int choice = UtilUI.getInt("Select action: ");
            switch (choice) { 
                case 1:
                    displayDetailedCinemaInfo(cc);
                    break;
                case 2:
                    createCinema(cc);
                    break;
                case 3:
                    return;
                default:
                    UtilUI.printRed("Invalid action, try again!");
                    break;
            }
        }
    }

    /**
     * Prompts the admin to enter  cinema ID,
     * Reprompt if the cinema ID is not valid
     * @param cc Cinema controller that contains all cinemas of particular cineplex
     * @return The valid cinema code
     */
    public static int promptValidCinemaId(CinemaController cc) {
        int cinemaCode;
        while (true) {
            cc.displayAllCinemas();
            cinemaCode = UtilUI.getInt("Enter cinema code: (enter -1 to exit) ");
            if (cinemaCode == -1) { return -1;}
            if (1 <= cinemaCode && cinemaCode <= cc.getCinemasCount()) {
                break;
            }
            UtilUI.printRed("Invalid Cinema Code");
        }
        return cinemaCode;
    }

    /**
     * Displaying the detailed information about cinemas of particular cineplexes
     * @param cc Cinema controller that contains all cinemas of particular cineplexes
     */
    private static void displayDetailedCinemaInfo(CinemaController cc) {
        int cinemaCode = promptValidCinemaId(cc);
        if (cinemaCode == -1) { return;}
        Cinema c = cc.getCinemaByCode(cinemaCode);
        System.out.println(String.format(
            "Cineplex ID   : %d\n" +
            "Cinema Code   : %d\n" +
            "Cinema Height : %d\n" +
            "Cinema Width  : %d\n" +
            "Cinema Class  : %s",
            c.getCineplexId(), c.getCinemaCode(),
            c.getHeight(), c.getWidth(), c.getCinemaClass()
        ));
        Cinema.printCinemaLayout(c.getHeight(), c.getWidth(), c.getSeatConfiguration());
    }

    /**
     * Creates new cinema with the ability to customize cinema's property such as its height, width, class, and detailed
     * configuration of its seating.
     * @param cc Cinema controller that contains all cinemas of particular cineplexes
     */
    private static void createCinema(CinemaController cc) {
        UtilUI.printBlue("### Creating New Cinema ####");
        int height = UtilUI.getInt("Enter height: (enter -1 to cancel) ");
        if (height == -1) { return;}
        int width = UtilUI.getInt("Enter width: ");
        if (width == -1) { return;}
        String cinemaClass;
        do {
            cinemaClass = UtilUI.getStr("Enter class (SILVER/GOLD/PLATINUM): ");
            if (cinemaClass.equals("SILVER") || cinemaClass.equals("GOLD") ||
                cinemaClass.equals("PLATINUM")) { break;}
            UtilUI.printRed("Invalid cinema class");
        } while (true);
        String seatConfiguration, error = "";
        while (true) {
            System.out.println("Enter seat configuration below");
            System.out.println("Write 'X' for not a seat, 'S' for seat");
            System.out.println("The length of string should be equal to width times height");
            UtilUI.printPurple("For example, if you have a cinema with height 3, width 4, and the configuration 'XSXSSSXSXXSX'");
            UtilUI.printPurple("It means you will have a cinema configuration like this:");
            Cinema.printCinemaLayout(3, 4, "XSXSSSXSSSXS");
            
            seatConfiguration = UtilUI.getStr("Enter configuration: ");
            if (seatConfiguration.length() != height * width) {
                error = "Invalid length";
            }
            for (int i = 0; i < seatConfiguration.length(); i++) {
                if (seatConfiguration.charAt(i) != 'S' &&
                        seatConfiguration.charAt(i) != 'X') {
                    error = "Invalid character";
                }
            }
            if (error.length() == 0) { break;}
            UtilUI.printRed(error);
        }
        Cinema.printCinemaLayout(height, width, seatConfiguration);
        cc.createCinema(height, width,
            Cinema.showClassOptions.valueOf(cinemaClass),
            seatConfiguration);
        UtilUI.printGreen("Cinema Layout Successfully Created!");
    }
}