package controller;
import java.util.ArrayList;
import java.util.Locale.Category;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import DAO.BookingDAO;
import model.Booking;
import model.Cinema;
import model.Movie;
import model.MovieGoer;
import model.Cinema.showClassOptions;
import model.Seat;
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

        // extra charge if movie is in 3D or blockbuster
        Movie m = AppController.mc.getMovieById(s.getMovieId());
        if (m.is3D()) {
            price += Settings.charge3D;
        }
        if (m.isBlockbuster()) {
            price += Settings.chargeBlockbuster;
        }
        
        // extra charge if showtime is in holiday date
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String showtimeDateFormat = dateFormat.format(s.getDate());
        for (int i = 0; i < Settings.holidayDates.size(); i++) {
            String holidayDateFormat = dateFormat.format(Settings.holidayDates.get(i));
            if (showtimeDateFormat.equals(holidayDateFormat)) {
                price += Settings.chargeHoliday;
                break;
            }
        }

        // discount if movie goer is a child or senior citizen
        MovieGoer.Category mgoer = AppController.mgc.getMovieGoerById(movieGoerId).getAgeCategory();
        if (mgoer == MovieGoer.Category.CHILD) {
            price = price * (1 - Settings.childDiscount / 100);
        }
        if (mgoer == MovieGoer.Category.SENIOR_CITIZEN) {
            price = price * (1 - Settings.seniorDiscount / 100);
        }

        // afternoon and midnight discount
        if (isAfternoonShowtime(s.getDate())) {
            price = price * (1 - Settings.afternoonDiscount / 100);
        }
        if (isMidnightShowtime(s.getDate())) {
            price = price * (1 - Settings.midnightDiscount / 100);
        }

        // promo code discount
        if (disc) {
            price = price * (1 - Settings.promoDiscount);
        }

        price = price * seatsCount;
        return price;
    }
    
    private static boolean isAfternoonShowtime(Date d) {
        return false; // TODO
    }

    private static boolean isMidnightShowtime(Date d) {
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