package controller;
import model.Booking;
import java.util.ArrayList;
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

    public static float calculatePrice(Cinema c, Showtime s, ArrayList<Seat> seats) {
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
        for (int i = 0; i < Settings.holidayDates.size(); i++) {
            if (s.getDate().equals(Settings.holidayDates.get(i))) {
                price += Settings.chargeHoliday;
                break;
            }
        } // TODO - might be buggy
        price = price * seats.size();
        return price;
    }
    
    // TODO
}