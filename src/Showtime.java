/*
    'Showtime' class

    - Contain the date & time, movie ID, 2D array of seat objects
    - Methods to book seat(s) in a showtime
*/

import java.util.ArrayList;
import java.util.Date;

public class Showtime {
    private int showtimeId;
    private int movieId;
    private int cinemaId;

    private Date date;

    // For creating new showtime
    public Showtime(int movieId, int cinemaId, Date date) {
        this.showtimeId = ++AppController.showtimeIdMax;
        this.movieId = movieId;
        this.cinemaId = cinemaId;
        this.date = date;
    }

    // For existing showtime (From DAO)
    public Showtime(int showtimeId, int movieId, int cinemaId, Date date) {
        this.showtimeId = showtimeId;
        this.movieId = movieId;
        this.cinemaId = cinemaId;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    //Method Override
    public int compareTo(Object o) {
        Showtime s = (Showtime) o;
        if (this.date.before(s.getDate())) {
            return -1; // Try to prevent duplicate timeslots when booking!
        }else {
            return 1;
        }
    }

}
