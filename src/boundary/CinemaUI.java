package boundary;
import controller.AppController;
import controller.CinemaController;
import model.Cinema;

public class CinemaUI {
    public static void admin() {
        int id = CineplexUI.promptValidCineplexId();
        if (id == -1) { return;}
        CinemaController cc = AppController.cc.getCineplexById(id).getController();
        while (true) {
            System.out.println("|=========|Cinema Admin Panel|=========|");
            System.out.println(AppController.cc.getCineplexById(id));
            System.out.print(
                "1. Display All Cinemas\n" +
                "2. Cinema Detailed View\n" +
                "3. Create New Cinema\n" +
                "4. Exit\n");
            int choice = UtilUI.getInt("Select action: ");
            switch (choice) { 
                case 1:
                    cc.displayAllCinemas();
                    break;
                case 2:
                    displayDetailedCinemaInfo(cc);
                    break;
                case 3:
                    createCinema(cc);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid action, try again!");
                    break;
            }
        }
    }

    public static int promptValidCinemaId(CinemaController cc) {
        int cinemaCode;
        while (true) {
            cinemaCode = UtilUI.getInt("Enter cinema code: (enter -1 to exit) ");
            if (cinemaCode == -1) { return -1;}
            if (1 <= cinemaCode && cinemaCode <= cc.getCinemasCount()) {
                break;
            }
            System.out.println("Invalid Cinema Code");
        }
        return cinemaCode;
    }

    private static void displayDetailedCinemaInfo(CinemaController cc) {
        int cinemaCode = promptValidCinemaId(cc);
        if (cinemaCode == -1) { return;}
        Cinema c = cc.getCinemaByCode(cinemaCode);
        System.out.println(String.format(
            "Cineplex ID: %d\n" +
            "Cinema Code: %d\n" +
            "Cinema Height: %d\n" +
            "Cinema Width: %d\n" +
            "Cinema Class: %d\n",
            c.getCineplexId(), c.getCinemaCode(),
            c.getHeight(), c.getWidth(), c.getCinemaClass()
        ));
        Cinema.printCinemaLayout(c.getHeight(), c.getWidth(), c.getSeatConfiguration());
    }

    private static void createCinema(CinemaController cc) {
        System.out.println("### Creating New Cinema ####");
        int height = UtilUI.getInt("Enter height: ");
        int width = UtilUI.getInt("Enter width: ");
        String cinemaClass;
        do {
            cinemaClass = UtilUI.getStr("Enter class (SILVER/GOLD/PLATINUM): ");
            if (cinemaClass.equals("SILVER") || cinemaClass.equals("GOLD") ||
                cinemaClass.equals("PLATINUM")) { break;}
            System.out.println("Invalid cinema class");
        } while (true);
        String seatConfiguration, error = "";
        while (true) {
            System.out.println("Enter seat configuration below");
            System.out.println("Write 'X' for not a seat, 'S' for seat");
            System.out.println("The length of string should be equal to width times height");
            seatConfiguration = UtilUI.getStr("");
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
            System.out.println(error);
        }
        cc.createCinema(height, width,
            Cinema.showClassOptions.valueOf(cinemaClass),
            seatConfiguration);
        Cinema.printCinemaLayout(height, width, seatConfiguration);
        System.out.println("Cinema Layout Successfully Created!");
    }
}