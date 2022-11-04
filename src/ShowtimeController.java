public class ShowtimeController {
    int movieId;

    LinkedList<Showtime> showtimes;
    public ShowtimeController() {
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
}
