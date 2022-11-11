package boundary;
import java.util.ArrayList;
import controller.AppController;
import controller.CinemaController;
import controller.SeatController;
import controller.ShowtimeController;
import model.Booking;
import model.Cineplex;
import model.Showtime;

public class BookingUI {
    public static void main(int movieGoerId){
        UtilUI.printBlue("### Booking System ###");

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
                    Cineplex cineplex = AppController.cc.getCineplexById(cineplexId);
                    CinemaController cc = cineplex.getController();
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
                            Showtime showtime = shc.getShowtimeById(showtimeId);
                            SeatController sec = showtime.getController();
                            sec.displaySeats();

                            // prompt starting seat code
                            while (true) {
                                String seatCode = UtilUI.getStr("Enter starting seat code: (enter 'exit' to cancel)");
                                if (seatCode.toLowerCase().equals("exit")) { break;}

                                // prompt number of seats to the right of starting seat code to be booked
                                if (sec.seatExists(seatCode)) {
                                    int numSeats = UtilUI.getInt("Enter number of seats: ");

                                    // validate that seat is valid
                                    for (int i = 0; i < numSeats; i++) {
                                        // if (!sec.seatExists(seatCode + ))
                                        // check if the seats are valid, if not valid then display error and break
                                        // TODO
                                    }

                                    // display all booking information and confirm booking
                                    while (true) {
                                        // TODO - display all booking information
                                        // TODO - display pricing
                                        boolean confirm = UtilUI.getBool("Confirm booking? (true/false) ");
                                        if (!confirm) { break;}

                                        // create booking model, increment sales in movie model
                                        System.out.println("TODOOOO CONFIRM BOOKING !!!!!");
                                        
                                        new Booking(
                                            movieGoerId,
                                            AppController.mc.getMovieById(showtime.getMovieId()).getTitle(),
                                            cineplex.getLocation(), cinemaCode,
                                            "DATE TODO", "SEAT TODO"
                                        );
                                        AppController.mc.getMovieById(movieId).incrementSales(numSeats);
                                    }
                                } else {
                                    UtilUI.printRed("Invalid seat code!");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void history(int movieGoerId) {
        UtilUI.printBlue("### Booking History ###");
        ArrayList<Booking> bookings = AppController.mgc
            .getMovieGoerById(movieGoerId).getController().getBookings();
        if (bookings.size() == 0) {
            UtilUI.printPurple("You haven't made any bookings yet.");
        } else {
            UtilUI.printPurple("Total " + bookings.size() + " bookings:");
        }
        for (int i = 0; i < bookings.size(); i++) {
            System.out.println(bookings.get(i));
        }
    }
}