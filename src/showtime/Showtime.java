import java.util.ArrayList;
import java.util.Date;

public class Showtime {
    static int numShowtimes = 0;

    private int showtimeId;
    private int fkMovieId;
    private int fkCinemaId;

    private String date;

    private int height;
    private int width;
    private String seats;

    // private Date date; # TODO hard to serialize date

    // For creating new showtime
    public Showtime(int movieId, int cinemaId, String date, int height, int width, String seats) { // TODO - change to date
        numShowtimes++;
        this.showtimeId = numShowtimes;
        this.fkMovieId = movieId;
        this.fkCinemaId = cinemaId;
        this.date = date;
        this.height = height;
        this.width = width;
        this.seats = seats;
    }

    // For existing showtime (From DAO)
    // public Showtime(int showtimeId, int movieId, int cinemaId, Date date) {
    //     this.showtimeId = showtimeId;
    //     this.movieId = movieId;
    //     this.cinemaId = cinemaId;
    //     this.date = date;
    // }

    public int getShowtimeId() { return this.showtimeId;}
    public int getMovieId() { return this.fkMovieId;}
    public int getCinemaId() { return this.fkCinemaId;}
    public String getDate() { return this.date;}
    public int getHeight() { return this.height;}
    public int getWidth() { return this.width;}
    public String getSeats() { return this.seats;}
    

    // public void setDate(Date date) {
    //     this.date = date;
    // }

    // public void setMovieId(int movieId) {
    //     this.movieId = movieId;
    // }

    // public void setCinemaId(int cinemaId) {
    //     this.cinemaId = cinemaId;
    // }

    // public void setShowtimeId(int showtimeId) {
    //     this.showtimeId = showtimeId;
    // }

    //Method Override
    // public int compareTo(Object o) {
    //     Showtime s = (Showtime) o;
    //     if (this.date.before(s.getDate())) {
    //         return -1; // Try to prevent duplicate timeslots when booking!
    //     }else {
    //         return 1;
    //     }
    // }
}
