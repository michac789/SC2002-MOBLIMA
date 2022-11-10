package boundary;
import java.util.ArrayList;
import java.util.Scanner;
import controller.AppController;
import controller.CinemaController;
import controller.SeatController;
import controller.ShowtimeController;
import model.Booking;

public class BookingUI {
    static Scanner sc = new Scanner(System.in);

    public static void main(int movieGoerId){
        System.out.println("### BOOKING UI ###");

        while (true) {
            // prompt user for movie id
            int movieId = MovieUI.promptValidMovieId();
            if (movieId == -1) { break;}

            while (true) {
                // show all cineplex that show that movie
                AppController.cc.displayCineplexesByMovieId(movieId);
                
                // prompt user for cineplex id
                int cineplexId = CineplexUI.promptValidCineplexId();
                if (cineplexId == -1) { break;}

                while (true) {
                    // show all cinemas and showtime for that movie
                    CinemaController cc = AppController.cc.getCineplexById(cineplexId).getController();
                    cc.displayCinemaAndShowtimeByMovieId(movieId);

                    // prompt for cinema code
                    int cinemaCode = CinemaUI.promptValidCinemaId(cc);
                    if (cinemaCode == -1) { break;}

                    while (true) {
                        // prompt for showtime
                        ShowtimeController shc = cc.getCinemaByCode(cinemaCode).getController();
                        ShowtimeUI.displayShowtimes(shc);
                        int showtimeId = ShowtimeUI.promptValidShowtimeId(shc);
                        if (showtimeId == -1) { break;}

                        while (true) {
                            // display seating arrangement
                            SeatController sec = shc.getShowtimeById(showtimeId).getController();
                            sec.displaySeats();

                            // prompt for seating position
                            // TODO

                            // confirm booking and payment option
                            // TODO

                            // create booking model, increment sales in movie model
                            // TODO
                        }
                    }
                }
            }
        }
    }

    public static void history(int movieGoerId) {
        System.out.println("### Booking History ###");
        ArrayList<Booking> bookings = AppController.mgc
            .getMovieGoerById(movieGoerId).getController().getBookings();
        if (bookings.size() == 0) {
            System.out.println("You haven't made any bookings yet.");
        } else {
            System.out.println("Total " + bookings.size() + " bookings:");
        }
        for (int i = 0; i < bookings.size(); i++) {
            System.out.println(bookings.get(i));
        }
    }
}