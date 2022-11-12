package controller;
import model.Booking;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import DAO.BookingDAO;
import model.Cinema;
import model.Movie;
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

    public static float calculatePrice(Cinema c, Showtime s, int seatsCount) {
        float price = 0;
        showClassOptions showClass = c.getCinemaClass();
        if (showClass == showClassOptions.SILVER) {
            price += Settings.silverPrice;
        } else if (showClass == showClassOptions.GOLD) {
            price += Settings.goldPrice;
        } else {
            price += Settings.platinumPrice;
        }
        Movie m = AppController.mc.getMovieById(s.getMovieId());
        if (m.is3D()) {
            price += Settings.charge3D;
        }
        if (m.isBlockbuster()) {
            price += Settings.chargeBlockbuster;
        }
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String showtimeDateFormat = dateFormat.format(s.getDate());
        for (int i = 0; i < Settings.holidayDates.size(); i++) {
            String holidayDateFormat = dateFormat.format(Settings.holidayDates.get(i));
            if (showtimeDateFormat.equals(holidayDateFormat)) {
                price += Settings.chargeHoliday;
                break;
            }
        }
        price = price * seatsCount;
        return price;
    }
    
    // TODO
}