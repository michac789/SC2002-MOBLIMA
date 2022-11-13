package controller;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
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

public class BookingController {
    private ArrayList <Booking> bookingList;
    private BookingDAO bookingDao = new BookingDAO();
    private int movieGoerId;

    public BookingController(int movieGoerId) {
        this.movieGoerId = movieGoerId;
        this.bookingList = this.bookingDao.load(movieGoerId);
    }

    public void save() {
        this.bookingDao.save(bookingList, movieGoerId);
    }

    public ArrayList<Booking> getBookings() {
        return this.bookingList;
    }

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
        UtilUI.printCyan("Single Seat Price: " + price);

        // extra charge if movie is in 3D or blockbuster
        Movie m = AppController.mc.getMovieById(s.getMovieId());
        if (m.is3D()) {
            price += Settings.charge3D;
            UtilUI.printCyan("3D Extra Charge (+$" + Settings.charge3D + ")");
        }
        if (m.isBlockbuster()) {
            price += Settings.chargeBlockbuster;
            UtilUI.printCyan("Blockbuster Extra Charge (+$" + Settings.chargeBlockbuster + ")");
        }
        
        // extra charge if showtime is in holiday date
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String showtimeDateFormat = dateFormat.format(s.getDate());
        for (int i = 0; i < Settings.holidayDates.size(); i++) {
            String holidayDateFormat = dateFormat.format(Settings.holidayDates.get(i));
            if (showtimeDateFormat.equals(holidayDateFormat)) {
                price += Settings.chargeHoliday;
                UtilUI.printCyan("Holiday Date Extra Charge (+$" + Settings.chargeHoliday + ")");
                break;
            }
        }

        // discount if movie goer is a child or senior citizen
        MovieGoer.Category mgoer = AppController.mgc.getMovieGoerById(movieGoerId).getAgeCategory();
        if (mgoer == MovieGoer.Category.CHILD) {
            price = price * (1 - (float) Settings.childDiscount / 100);
            UtilUI.printCyan("Child Discount (" + Settings.childDiscount + "%) (-$" +
                String.format("%.2f", price * ((float) Settings.childDiscount / 100)) + ")");
        }
        if (mgoer == MovieGoer.Category.SENIOR_CITIZEN) {
            price = price * (1 - (float) Settings.seniorDiscount / 100);
            UtilUI.printCyan("Senior Citizen Discount (" + Settings.seniorDiscount + "%) (-$" +
                String.format("%.2f", price * ((float) Settings.seniorDiscount / 100)) + ")");
        }

        // afternoon and midnight discount
        if (isAfternoonShowtime(s.getDate())) {
            price = price * (1 - ((float) Settings.afternoonDiscount / 100));
            UtilUI.printCyan("Afternoon Showtime Discount (" + Settings.afternoonDiscount + "%) (-$" +
                String.format("%.2f", price * ((float) Settings.afternoonDiscount / 100)) + ")");
        }
        if (isMidnightShowtime(s.getDate())) {
            price = price * (1 - ((float) Settings.midnightDiscount / 100));
            UtilUI.printCyan("Midnight Showtime Discount (" + Settings.midnightDiscount + "%) (-$" +
                String.format("%.2f", price * ((float) Settings.midnightDiscount / 100)) + ")");
        }

        // promo code discount
        if (disc) {
            price = price * (1 - (float) Settings.promoDiscount / 100);
            UtilUI.printCyan("Promo Code Discount (" + Settings.seniorDiscount + "%) (-$" +
                String.format("%.2f", price * (float) Settings.promoDiscount / 100) + ")");
        }

        // multiply with number of seats
        UtilUI.printPurple(String.format("Total price = $%.2f × %d = $%.2f",
            price, seatsCount, price * seatsCount
        ));
        return price * seatsCount;
    }
    
    private static boolean isAfternoonShowtime(Date d) {
        // return true if the showtime that starts before 12am-6pm, otherwise false
        return false; // TODO
    }

    private static boolean isMidnightShowtime(Date d) {
        // return true if the showtime that starts at 6pm onwards, otherwise false
        return false; // TODO
    }

    public static boolean compareTimes(Date d1, Date d2) {
        int t1, t2;
        t1 = (int) (d1.getTime() % (24*60*60*1000L));
        t2 = (int) (d2.getTime() % (24*60*60*1000L));
        if (t1 > t2) { return true;}
        return false;
    }
}