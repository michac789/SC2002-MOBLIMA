import java.util.Date;

public class Showtime {
    private int movieId;
    private Date date;
    // seat controller ???

    public Showtime(int movieId, Date date) {
        this.movieId = movieId;
        this.date = date;
    }
    
    public int getMovieId() { return this.movieId;}
    public Date getDate() { return this.date;}


    // public int getShowtimeId() {
    //     return showtimeId;
    // }

    public void setMovieId(int movieId) { this.movieId = movieId;}
    public void setDate(Date date) { this.date = date;}


    //Method Override
    public int compareTo(Object o) {
        Showtime s = (Showtime) o;
        if (this.date.before(s.getDate())) {
            return -1; // Try to prevent duplicate timeslots when booking!
        } else {
            return 1;
        }
    }
}