import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class ShowtimeController {
    private LinkedList<Showtime> showtimes;

    private int movieId;

    public ShowtimeController(int movieId) {
        this.movieId = movieId;
        showtimes = new LinkedList<Showtime>();
    }

    public void createShowtime() {
        String movieTitle = "Batman"
        Scanner sc = new Scanner(System.in);
        System.out.println("Creating new showtime for " + movieTitle);
        System.out.println("Enter movie showtime(hhmm):");
        int timeslot = sc.nextInt();

        Showtime s = new Showtime();

    }

    public void getShowtime() {


    }


    //1) << 12 Nov  | 13 Nov | 2) 14 Nov>>
    // Sort Showtimes and cleanup invalid showtimes(those that are before today's date)
    public void sortShowtimes(){
        Date todaysDate = new Date();
        Showtime temp;

        // Ensure first showtime is not invalid
        while (true) {
            if (todaysDate.before(showtimes.get(0).getDatetime())) {
                showtimes.remove(0);
            }else {
                break;
            }
        }

        for (int i=1; i < showtimes.size(); i++) {
            for (int j=i; j > 0; j--) {
                if (showtimes.get(j).compareTo(showtimes.get(j - 1)) > 0) {
                    temp = showtimes.get(j - 1);
                    showtimes.set(j - 1, showtimes.get(j));
                    showtimes.set(j, temp);
                }else {
                    break;
                }
            }
        }
    }
}
