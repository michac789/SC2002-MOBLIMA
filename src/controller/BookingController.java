package controller;
import model.Booking;
import java.util.ArrayList;
import DAO.BookingDAO;
import model.Cinema;
import model.Seat;
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

    public int calculatePrice(Cinema c, Showtime s, ArrayList<Seat> seats) {
        // get cinema class price

        // add price if it is 3D

        // add price if it is blockbuster

        // get showtime date, add price if it is holiday

        // multiply by amount of seats
        
        return 1;
    }
    
    // TODO
}