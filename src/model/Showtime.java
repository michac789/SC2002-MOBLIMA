package model;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import controller.AppController;
import controller.SeatController;

/**
 * Represents the showtime object
 * @version 1.0
 * @since 2022-11-13
 */
public class Showtime {
    /**
     * The ID of the movie
     */
    private int movieId;
    /**
     * The date of the showtime
     */
    private Date date;
    /**
     * The SeatController object which contains seating information related to this specific showtime
     */
    private SeatController seatController;
    /**
     * Dafe formater to format date and time to string
     */
    private static DateFormat formatDateToString= new SimpleDateFormat("dd MMM YYYY, EEE. HH:mm");

    /**
     * Constructor for Showtime object which is able to store relevant informations about showtime
     * @param movieId The ID of the movie
     * @param date The date of movie aired
     * @param height The height of seating layout
     * @param width The width of seating layout
     * @param cineplexId The ID of cineplex
     * @param cinemaId The ID of the cinema in specific cineplex
     * @param seats The seats in the form of 2D Array of Seat objects
     */
    public Showtime(int movieId, Date date, int height, int width, int cineplexId, int cinemaId, Seat[][] seats) {
        this.movieId = movieId;
        this.date = date;
        this.seatController = new SeatController(height, width, cineplexId, cinemaId, seats);
    }

    /**
     * Constructor for Showtime object which is able to store relevant informations about showtime
     * @param movieId The ID of the movie
     * @param date The date of movie aired
     * @param height The height of seating layout
     * @param width The width of seating layout
     * @param cineplexId The ID of cineplex
     * @param cinemaId The ID of the cinema in specific cineplex
     * @param seatConfiguration The seat configuration in the form of String
     */
    public Showtime(int movieId, Date date, int height, int width, int cineplexId, int cinemaId, String seatConfiguration) {
        this.movieId = movieId;
        this.date = date;
        Seat[][] seats = new Seat[height][width];
        parseSeatConfiguration(seats, seatConfiguration, height, width);
        this.seatController = new SeatController(height, width, cineplexId, cinemaId, seats);
    }

    /**
     * Parses the format of seat configuration from String to 2D array of Seat objects
     * @param seats 2D array of Seat objects
     * @param seatConfiguration Seating configurations in the form of String
     * @param height The height of seating layout
     * @param width The width of seating layout
     */
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

    /**
     * Gets the movie ID
     * @return
     */
    public int getMovieId() { return this.movieId;}

    /**
     * Gets the showtime date
     * @return Date object representing the date of the movie
     */
    public Date getDate() { return this.date;}

    /**
     * Gets the SeatController object
     * @return The SeatController object related to this Showtime
     */
    public SeatController getController() { return this.seatController;}

    /**
     * Sets the movie ID of this showtime
     * @param movieId The movie ID of this showtime
     */
    public void setMovieId(int movieId) { this.movieId = movieId;}

    /**
     * Sets the date of this showtime
     * @param date The date of the showtime
     */
    public void setDate(Date date) { this.date = date;}

    /**
     * Compares Showtime date with another Showtime, returns 1 if it comes before the called object
     * @param o Showtime object to be compared
     * @return the integer 1 or -1, positive means that the object called is after the object passed.
     */
    public int compareTo(Object o) {
        Showtime s = (Showtime) o;
        // No Duplicate dates so no need check equals
        if (this.date.before(s.getDate())) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Formats the information of showtime
     * @return String representation of showtime
     */
    public String toString() {
        String formatDate = formatDateToString.format(this.date);
        return formatDate + " | Showing Movie: " + AppController.mc.getMovieById(this.movieId).getTitle();
    }
}