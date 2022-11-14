package controller;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import DAO.BookingDAO;
import boundary.UtilUI;
import model.Booking;
import model.Cinema;
import model.Movie;
import model.MovieGoer;
import model.Cinema.showClassOptions;
import model.Settings;
import model.Showtime;

/**
 * Controller for movie booking functionality and store all bookings,
 * each booking controller is owned my one MovieGoer object
 * @version 1.0
 * @since 2022-11-13
 */
public class BookingController {
    /**
     * Contains an ArrayList of all available bookings for this user
     */
    private ArrayList <Booking> bookingList;
    /**
     * BookingDAO to get and save data to database
     */
    private BookingDAO bookingDao = new BookingDAO();
    /**
     * MovieGoer ID that corresponds to this booking controller
     */
    private int movieGoerId;

    /**
     * Constructor to create Booking Controller object,
     * automatically load bookings data from the database
     * @param movieGoerId ID of movieGoer object
     */
    public BookingController(int movieGoerId) {
        this.movieGoerId = movieGoerId;
        this.bookingList = this.bookingDao.load(movieGoerId);
    }

    /**
     * Save all changes regarding one user bookings to the database
     */
    public void save() {
        this.bookingDao.save(bookingList, movieGoerId);
    }

    /**
     * Get list of all bookings made by this movieGoerId user
     * @return Arraylist of all bookings for this user
     */
    public ArrayList<Booking> getBookings() {
        return this.bookingList;
    }

    /**
     * Display price calculation process and calculate price for a particular booking
     * @param c Cinema where the MovieGoer by ticket from
     * @param s Showtime that contains date of showing of the movie
     * @param seatsCount Number of seats booked in this particular showtime
     * @param movieGoerId ID of movieGoer who booked the ticket
     * @param disc Discount applied or not, has a value of true or false
     * @return Final Price for this ticket booking
     */
    public static float calculatePrice(Cinema c, Showtime s, int seatsCount, int movieGoerId, boolean disc) {
        float price = 0;

        // price based on cinema class
        showClassOptions showClass = c.getCinemaClass();
        if (showClass == showClassOptions.SILVER) {
            price += Settings.silverPrice;
        } else if (showClass == showClassOptions.GOLD) {
            price += Settings.goldPrice;
        } else {
            price += Settings.platinumPrice;
        }
        UtilUI.printCyan("Single Seat Price: $" + String.format("%.2f", price));

        // extra charge if movie is in 3D or blockbuster
        Movie m = AppController.mc.getMovieById(s.getMovieId());
        if (m.is3D()) {
            price += Settings.charge3D;
            UtilUI.printCyan("3D Extra Charge (+$" +
                String.format("$.2f", Settings.charge3D) + ")");
        }
        if (m.isBlockbuster()) {
            price += Settings.chargeBlockbuster;
            UtilUI.printCyan("Blockbuster Extra Charge (+$" +
                String.format("%.2f", Settings.chargeBlockbuster) + ")");
        }
        
        // extra charge if showtime is in holiday date
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String showtimeDateFormat = dateFormat.format(s.getDate());
        for (int i = 0; i < Settings.holidayDates.size(); i++) {
            String holidayDateFormat = dateFormat.format(Settings.holidayDates.get(i));
            if (showtimeDateFormat.equals(holidayDateFormat)) {
                price += Settings.chargeHoliday;
                UtilUI.printCyan("Holiday Date Extra Charge (+$" +
                    String.format("%.2f", Settings.chargeHoliday) + ")");
                break;
            }
        }

        // discount if movie goer is a child or senior citizen
        MovieGoer.Category mgoer = AppController.mgc.getMovieGoerById(movieGoerId).getAgeCategory();
        if (mgoer == MovieGoer.Category.CHILD && Settings.childDiscount != 0) {
            UtilUI.printCyan("Child Discount (" + Settings.childDiscount + "%) (-$" +
                    String.format("%.2f", price * ((float) Settings.childDiscount / 100)) + ")");
            price = price * (1 - (float) Settings.childDiscount / 100);
        }
        if (mgoer == MovieGoer.Category.SENIOR_CITIZEN && Settings.seniorDiscount != 0) {
            UtilUI.printCyan("Senior Citizen Discount (" + Settings.seniorDiscount + "%) (-$" +
                    String.format("%.2f", price * ((float) Settings.seniorDiscount / 100)) + ")");
            price = price * (1 - (float) Settings.seniorDiscount / 100);
        }

        // afternoon and midnight discount
        if (isAfternoonShowtime(s.getDate()) && Settings.afternoonDiscount != 0) {
            UtilUI.printCyan("Afternoon Showtime Discount (" + Settings.afternoonDiscount + "%) (-$" +
                    String.format("%.2f", price * ((float) Settings.afternoonDiscount / 100)) + ")");
            price = price * (1 - ((float) Settings.afternoonDiscount / 100));
        }
        if (isMidnightShowtime(s.getDate()) && Settings.midnightDiscount != 0) {
            UtilUI.printCyan("Midnight Showtime Discount (" + Settings.midnightDiscount + "%) (-$" +
                    String.format("%.2f", price * ((float) Settings.midnightDiscount / 100)) + ")");
            price = price * (1 - ((float) Settings.midnightDiscount / 100));
        }

        // promo code discount
        if (disc) {
            UtilUI.printCyan("Promo Code Discount (" + Settings.seniorDiscount + "%) (-$" +
                    String.format("%.2f", price * (float) Settings.promoDiscount / 100) + ")");
            price = price * (1 - (float) Settings.promoDiscount / 100);
        }

        // multiply with number of seats
        UtilUI.printPurple(String.format("Total price = $%.2f × %d = $%.2f",
            price, seatsCount, price * seatsCount
        ));
        return price * seatsCount;
    }
    
    /**
     * Indicate whether a showtime is considered afternoon showtime or not
     * @param date Date and Time object of the showtime booking
     * @return True if the showtime that starts before 8am-6pm (exclusive 6pm), otherwise false
     */
    private static boolean isAfternoonShowtime(Date date) {
        DateFormat dtFormat = new SimpleDateFormat("dd/MM/yy,HH:mm");
        try {
            Date c = dtFormat.parse("01/01/2001,18:00");
            return compareTimes(c, date);
        } catch (ParseException e) {}
        return false;
    }

    /**
     * Indicate whether a showtime is considered midnight showtime or not
     * @param d Date and Time object of the showtime booking
     * @return True if the showtime that starts at 6pm onwards, otherwise false
     */
    private static boolean isMidnightShowtime(Date d) {
        DateFormat dtFormat = new SimpleDateFormat("dd/MM/yy,HH:mm");
        try {
            Date c = dtFormat.parse("01/01/2001,18:00");
            return compareTimes(d, c);
        } catch (ParseException e) {}
        return false;
    }

    /**
     * Utility function to compare the time of two date objects,
     * ignoring the date part itself
     * @param d1 First date object
     * @param d2 Second date object
     * @return True if time of d2 is after time of d1, else false
     */
    public static boolean compareTimes(Date d1, Date d2) {
        int t1, t2;
        t1 = (int) (d1.getTime() % (24*60*60*1000L));
        t2 = (int) (d2.getTime() % (24*60*60*1000L));
        if (t1 > t2) { return true;}
        return false;
    }
}