package boundary;
import java.util.ArrayList;
import java.util.Scanner;
import controller.AppController;
import controller.CinemaController;
import controller.ShowtimeController;
import model.Showtime;

public class ShowtimeUI {
    private static Scanner sc = new Scanner(System.in);
    
    public static void admin() {
        while (true) {
            System.out.println("|=========|Showtime Admin Panel|=========|");
            System.out.print(
                    "1. Display All Showtimes\n" +
                            "2. Create new Showtime\n" +
                            "3. Edit Showtime\n" +
                            "4. Edit Movie\n" +
                            "5. Exit\n");
            System.out.print("Select action: ");
            int choice = sc.nextInt();
            // cleaner
            sc.nextLine();
            // switch (choice) {
            //     case 1:
            //         displayAllMovies();
            //         break;
            //     case 2:
            //         displayDetailMovie();
            //         break;
            //     case 3:
            //         createMovie();
            //         break;
            //     case 4:
            //         editMovie();
            //         break;
            //     case 5:
            //         return;
            //     default:
            //         System.out.println("Invalid action, try again!");
            //         break;
        System.out.println("TODOOOO");
        // TODO

        // while (true) {
        //     int cineplexId = CineplexUI.promptValidCineplexId();
        //     if (cineplexId == -1) { break;}

        //     CinemaController cc = AppController.cc.getCineplexById(cineplexId).getController();
        //     while (true) {
        //         int cinemaId = CinemaUI.promptValidCinemaId(cc);
        //         if (cinemaId == -1) { break;}

        //         ShowtimeController shc = cc.getCinemaByCode(cinemaId).getController();
        //         while (true) {
        //             // TODO - create showtime

        //             break;

        //         }
        //     }
        // }
    }
    
    public static void displayShowtimes(ShowtimeController shc) {
        ArrayList<Showtime> showtimes = shc.getAllShowtimes();
        for (int i = 0; i < showtimes.size(); i++) {
            Showtime s = showtimes.get(i);
            System.out.println(s);
            // TODO - print available seats
        }
    }

    public static int promptValidShowtimeId(ShowtimeController shc) {
        // display showtime
        int showtimeId;
        while (true) {
            System.out.println("Enter showtime id: (enter -1 to exit)");
            showtimeId = sc.nextInt();
            if (showtimeId == -1) { return -1;}
            if (1 <= showtimeId && showtimeId <= shc.getShowtimeCount()) {
                break;
            }
            System.out.println("Invalid Showtime ID");
        }
        return showtimeId;
    }
}