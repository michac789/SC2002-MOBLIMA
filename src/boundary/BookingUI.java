package boundary;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import controller.AppController;
import controller.BookingController;
import controller.CinemaController;
import controller.SeatController;
import controller.ShowtimeController;
import model.Booking;
import model.Cinema;
import model.Cineplex;
import model.Settings;
import model.Showtime;

public class BookingUI {
    public static void main(int movieGoerId){
        UtilUI.printBlue("### Booking System ###");

        while (true) {
            // prompt user for movie id
            MovieUI.displayAllShowingMovies();
            int movieId = MovieUI.promptValidMovieId(false, false, false);
            if (movieId == -1) { break;}

            while (true) {
                // show all cineplex that show that movie
                ArrayList<Integer> validCineplexIds = AppController.cc.displayCineplexesByMovieId(movieId);
                
                // prompt user for cineplex id
                int cineplexId = UtilUI.promptInt(validCineplexIds, "Enter Cineplex ID: ");
                if (cineplexId == -1) { break;}

                while (true) {
                    // show all cinemas and showtime for that movie
                    Cineplex cineplex = AppController.cc.getCineplexById(cineplexId);
                    CinemaController cc = cineplex.getController();
                    ArrayList<Integer> validCinemaCodes = cc.displayCinemaAndShowtimeByMovieId(movieId);

                    // prompt for cinema code
                    int cinemaCode = UtilUI.promptInt(validCinemaCodes, "Enter Cinema Code: ");
                    if (cinemaCode == -1) { break;}

                    while (true) {
                        Cinema cinema = cc.getCinemaByCode(cinemaCode);
                        ShowtimeController shc = cinema.getController();
                        
                        // prompt for showtime
                        ArrayList<Integer> validShowtimes = shc.displaShowtimeByMovieId(movieId);
                        int showtimeId = UtilUI.promptInt(validShowtimes, "Enter Showtime ID: ");
                        if (showtimeId == -1) { break;}

                        while (true) {
                            Showtime showtime = shc.getShowtimeById(showtimeId);
                            SeatController sec = showtime.getController();

                            // display seating arrangement
                            sec.displaySeats();

                            // prompt starting seat code

                            UtilUI.printPurple("You will need to enter starting seat code and number of seats n\n" +
                                "You will book n seats from the starting seat code to (n-1) seats on the right of starting seat code."
                            );
                            String seatCode = UtilUI.getStr("Enter starting seat code: (enter -1 to cancel) ");
                            if (seatCode.equals("-1")) { break;}

                            // prompt number of seats to the right of starting seat code to be booked
                            if (sec.seatExists(seatCode)) {
                                int numSeats = UtilUI.getInt("Enter number of seats: ");
                                if (numSeats == -1) { break;}

                                // validate if all the seats are valid (is a seat, still empty)
                                if (sec.validateSeats(seatCode, numSeats)) {
                                    while (true) {

                                        // ask for promo code or checkout immediately
                                        boolean disc = false;
                                        System.out.println("(1) Enter Promo Code");
                                        System.out.println("(2) Checkout & Payment");
                                        System.out.println("(3) Exit");
                                        ArrayList<Integer> validOptions = new ArrayList<Integer>();
                                        for (int i = 1; i <= 3; i++) { validOptions.add(i);}
                                        int option = UtilUI.promptInt(validOptions, "Enter Action: ");
                                        if (option == -1 || option == 3) { break;}

                                        if (option == 1) {
                                            while (true) {
                                                int promoCode = UtilUI.getInt("Enter promo code: (enter -1 to cancel) ");
                                                if (promoCode == -1) { break;}
                                                if (promoCode == Settings.promoCode) {
                                                    disc = true;
                                                    UtilUI.printGreen("Promo Code Applied!");
                                                    break;
                                                } else {
                                                    UtilUI.printRed("Invalid Promo Code!");
                                                }
                                            }
                                        }

                                        // display all booking information
                                        DateFormat dtFormat = new SimpleDateFormat("dd/MM/yy,HH:mm");
                                        float price = BookingController.calculatePrice(cinema, showtime, numSeats, movieGoerId, disc);
                                        displayBookingInformation(
                                            AppController.mgc.getMovieGoerById(movieGoerId).getUsername(),
                                            AppController.mc.getMovieById(showtime.getMovieId()).getTitle(),
                                            cineplex.getLocation(),
                                            cinemaCode,
                                            dtFormat.format(showtime.getDate()),
                                            generateSeatCodeString(seatCode, numSeats),
                                            price
                                        );

                                        // ask for booking confirmation
                                        boolean confirm = UtilUI.getBool("Confirm booking? ");
                                        if (!confirm) { break;}

                                        // create booking model and add to users booking
                                        Booking newBooking = new Booking(
                                            movieGoerId,
                                            AppController.mc.getMovieById(showtime.getMovieId()).getTitle(),
                                            cineplex.getLocation(), cinemaCode,
                                            dtFormat.format(showtime.getDate()),
                                            generateSeatCodeString(seatCode, numSeats),
                                            price
                                        );
                                        AppController.mgc.getMovieGoerById(movieGoerId).getController()
                                            .getBookings().add(newBooking);

                                        // increment sales in movie model
                                        AppController.mc.getMovieById(movieId).incrementSales(numSeats);

                                        // mark seats as occupied
                                        sec.bookSeats(seatCode, numSeats);

                                        // show success, prompt to make another booking or return
                                        UtilUI.printGreen("Booking successful, please check your booking history!");
                                        boolean anotherBooking = UtilUI.getBool("Create another booking? ");
                                        if (!anotherBooking) { return;}
                                        else { break;}
                                    }
                                } else {
                                    UtilUI.printRed("Some seats does not exist or already taken!");
                                }
                            } else {
                                UtilUI.printRed("Invalid seat code, please try again!");
                            }
                        }
                    }
                }
            }
        }
    }

    private static String generateSeatCodeString(String startCode, int seatCount) {
        String rowLetter = startCode.substring(0, 1);
        String columnNumber = startCode.substring(1);
        String returnVal = "";
        for (int i = 0; i < seatCount; i++) {
            int newColumnNumber = Integer.parseInt(columnNumber) + i;
            String seatCode = rowLetter + newColumnNumber;
            if (i == 0) { returnVal = seatCode;}
            else {
                returnVal = returnVal + "/" + seatCode;
            }
        }
        return returnVal;
    }

    private static void displayBookingInformation(String username, String title, String location,
            int cinemaCode, String dt, String seats, float price) {
        UtilUI.printPurple("MovieGoer Username: " + username);
        UtilUI.printPurple("Movie Title: " + title);
        UtilUI.printPurple("Cineplex Location: " + location);
        UtilUI.printPurple("Cinema Code: " + cinemaCode);
        UtilUI.printPurple("Date & Time: " + dt);
        UtilUI.printPurple("Seats Booked: " + seats);
        UtilUI.printPurple("Total Price: " + String.format("$%.2f", price));
    }

    public static void history(int movieGoerId) {
        UtilUI.printBlue("### Booking History ###");
        ArrayList<Booking> bookings = AppController.mgc
            .getMovieGoerById(movieGoerId).getController().getBookings();
        if (bookings.size() == 0) {
            UtilUI.printPurple("You haven't made any bookings yet.\n");
        } else {
            UtilUI.printPurple("Total " + bookings.size() + " bookings:");
        }
        for (int i = 0; i < bookings.size(); i++) {
            System.out.println(bookings.get(i));
        }
    }
}