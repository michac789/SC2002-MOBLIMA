/*
    'Showtime' class

    - Contain the date & time, movie ID, 2D array of seat objects
    - Methods to book seat(s) in a showtime
*/

import java.util.ArrayList;
import java.util.Date;

public class Showtime {
    public static int numOfShowtime = 0;
    private int showtimeId;
    private int movieId;

    private Date date;

    // For creating new showtime
    public Showtime(int movieId, Date date) {
        this.showtimeId = ++Showtime.numOfShowtime;
        this.movieId = movieId;
        this.date = date;
    }

    // For existing showtime (From DAO)
    public Showtime(int showtimeId, int movieId, Date date) {
        this.showtimeId = showtimeId;
        this.movieId = movieId;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public int getMovieId() {
        return movieId;
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
