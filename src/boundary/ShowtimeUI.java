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
        System.out.println("TODOOOO");
        // TODO

        while (true) {
            int cineplexId = CineplexUI.promptValidCineplexId();
            if (cineplexId == -1) { break;}

            CinemaController cc = AppController.cc.getCineplexById(cineplexId).getController();
            while (true) {
                int cinemaId = CinemaUI.promptValidCinemaId(cc);
                if (cinemaId == -1) { break;}

                ShowtimeController shc = cc.getCinemaByCode(cinemaId).getController();
                while (true) {
                    // TODO - create showtime

                    break;

                }
            }
        }
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