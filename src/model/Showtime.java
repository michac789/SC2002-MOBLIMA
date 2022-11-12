package model;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import controller.AppController;
import controller.SeatController;

public class Showtime {
    private int movieId;
    private Date date;
    private SeatController seatController;

    private static DateFormat formatDateToString= new SimpleDateFormat("dd MMM YYYY, EEE. HH:mm");

    public Showtime(int movieId, Date date, int height, int width, int cineplexId, int cinemaId, Seat[][] seats) {
        this.movieId = movieId;
        this.date = date;
        this.seatController = new SeatController(height, width, cineplexId, cinemaId, seats);
    }

    public Showtime(int movieId, Date date, int height, int width, int cineplexId, int cinemaId, String seatConfiguration) {
        this.movieId = movieId;
        this.date = date;
        Seat[][] seats = new Seat[height][width];
        parseSeatConfiguration(seats, seatConfiguration, height, width);
        this.seatController = new SeatController(height, width, cineplexId, cinemaId, seats);
    }

    public void parseSeatConfiguration(Seat[][] seats, String seatConfiguration, int height, int width) {
        int k = 0; //To Loop Through the seats
        for (int i=0; i < height; i++) {
            for (int j=0; j < width; j++) {
                char code = (char) (i + 65);
                seats[i][j] = new Seat(code + "" + (j+1),
                                (seatConfiguration.charAt(k) == 'F' ? true : false),
                                (seatConfiguration.charAt(k) == 'X' ? false : true));
                k++;
            }
        }
    }

    public int getMovieId() { return this.movieId;}
    public Date getDate() { return this.date;}
    public SeatController getController() { return this.seatController;}

    public void setMovieId(int movieId) { this.movieId = movieId;}
    public void setDate(Date date) { this.date = date;}

    //Method Override
    public int compareTo(Object o) {
        Showtime s = (Showtime) o;
        // No Duplicate dates so no need check equals
        if (this.date.before(s.getDate())) {
            return -1;
        } else {
            return 1;
        }
    }

    public String toString() {
        String formatDate = formatDateToString.format(this.date);
        return formatDate + " | Showing Movie: " + AppController.mc.getMovieById(this.movieId).getTitle();
    }
}