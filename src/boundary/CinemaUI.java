package boundary;
import java.util.Scanner;
import controller.AppController;
import controller.CinemaController;
import model.Cineplex;
import model.Cinema;

public class CinemaUI {
    private static Scanner sc = new Scanner(System.in);

    public static void admin() {
        int id = promptValidCineplexId();
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
            System.out.print("Select action: ");  
            int choice = sc.nextInt();
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

    public static int promptValidCineplexId() {
        int id;
        while (true) {
            System.out.println("Enter cineplex ID (or enter -1 to exit):");
            id = sc.nextInt();
            if (id == -1) { return -1;}
            if (1 <= id && id <= Cineplex.getNumCineplex()) {
                break;
            }
            System.out.println("Invalid cineplex ID");
        }
        return id;
    }

    private static void displayDetailedCinemaInfo(CinemaController cc) {
        // TODO - view seat config ?? for admin
        System.out.println("Enter cinema code: ");
        int cinemaCode = sc.nextInt();
        if (1 <= cinemaCode && cinemaCode <= cc.getCinemasCount()) {
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
        } else {
            System.out.println("Invalid Cinema Code");
            return;
        }
    }

    private static void createCinema(CinemaController cc) {
        System.out.println("### Creating New Cinema ####");
        System.out.print("Enter height: ");
        int height = sc.nextInt();
        System.out.print("Enter width: ");
        int width = sc.nextInt();
        sc.nextLine();
        String cinemaClass;
        do {
            System.out.print("Enter class (CLASS1/CLASS2/CLASS3): ");
            cinemaClass = sc.nextLine();
            if (cinemaClass.equals("CLASS1") || cinemaClass.equals("CLASS2") ||
                cinemaClass.equals("CLASS3")) { break;}
            System.out.println("Invalid cinema class");
        } while (true);
        String seatConfiguration, error = "";
        while (true) {
            System.out.println("Enter seat configuration below");
            System.out.println("Write 'X' for not a seat, 'S' for seat");
            System.out.println("The length of string should be equal to width times height");
            seatConfiguration = sc.nextLine();
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