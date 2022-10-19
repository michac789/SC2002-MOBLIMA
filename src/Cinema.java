import java.util.*;

public class Cinema {
    private int cinemaCode;
    private int height;
    private int width;

    private String cinemaClass; // TODO enum: ...
    private List<Showtime> showtimes = new ArrayList<Showtime>();;

    public Cinema(int code, int height, int width) {
        this.cinemaCode = code;
        this.height = height;
        this.width = width;
    }

    public void addShowtime(String dt, int movieId) {
        Showtime newSt = new Showtime(dt, movieId, this.height, this.width);
        this.showtimes.add(newSt);
    }

    // get showtimes
    

    public void printCinemaInfo() {
        System.out.println("Cinema Code: " + this.cinemaCode);
        System.out.println("Height: " + this.height);
        System.out.println("Width: " + this.width);
        System.out.println("");
    }
}
