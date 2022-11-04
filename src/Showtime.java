/*
    'Showtime' class

    - Contain the date & time, movie ID, 2D array of seat objects
    - Methods to book seat(s) in a showtime
*/

import java.util.ArrayList;
import java.util.Date;

public class Showtime {
    private Date date;

    private int timeslot;

    private String datetime; // TODO - change to datetime
    private int movieId;

    private Seat[][] seats;
    
    // 2d array for seats
    // A1 A2 A3 A4
    // B1 B2 B3 B4
    // C1 C2 C3 C4

    public Showtime(String datetime, int movieId, int h, int w) {
//        this.datetime = datetime;
        this.movieId = movieId;
        this.seats = new Seat[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                this.seats[i][j] = new Seat(
                    Character.toString((char)(65 + i)) + (j + 1)
                );
            }
        }
    }

    public Showtime(Date datetime, int movieId, int h, int w) {
        this.datetime = datetime;
        this.movieId = movieId;
        this.seats = new Seat[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                this.seats[i][j] = new Seat(
                        Character.toString((char)(65 + i)) + (j + 1)
                );
            }
        }
    }

    // TODO - accessors & mutators
    // Convert to a readable datetime string for ShowtimeController to print
    public Date getDatetime() {
        return this.datetime;
    }

    public int getMovieId() {
        return movieId;
    }

    public Seat[][] getSeats() {
        return seats;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setSeats(Seat[][] seats) {
        this.seats = seats;
    }

    // method to book the seats ??
    // public boolean bookSeats(User user, ArrayList seatsCodes) {
    //     TODO
    // }

    // method to show all the seats (2D)
    // public void showSeats() {
    //     TODO
    // }

    //Method Override
    public int compareTo(Object o) {
        Showtime s = (Showtime) o;
        if (this.datetime.before(s.getDatetime())) {
            return -1; // Try to prevent duplicate timeslots when booking!
        }else {
            return 1;
        }
    }

    public String savetoCSV() {
        return "";
    }
}
