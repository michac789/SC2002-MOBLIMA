import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        int choice;
        Scanner sc = new Scanner(System.in);

        // sample movies
        Movie m1 = new Movie(1, "movie1");
        Movie m2 = new Movie(2, "movie2");
        m1.printMovieInfo();
        m2.printMovieInfo();

        // sample cineplexes
        Cineplex cx1 = new Cineplex(1, "location1");
        Cineplex cx2 = new Cineplex(2, "location2");
        cx1.printCineplexInfo();
        cx2.printCineplexInfo();

        // add sample cinemas to cineplex
        cx1.addCinema(1, 10, 16);
        cx1.addCinema(2, 10, 16);
        cx1.addCinema(3, 8, 12);
        cx2.addCinema(1, 12, 20);
        cx2.addCinema(2, 12, 20);
        cx1.printCinemasList();
        cx2.printCinemasList();

        // get cinemas and add showtimes
        Cinema c1 = cx1.getCinema(1);
        c1.addShowtime("14:00", 1);
        c1.addShowtime("17:00", 1);
        Cinema c2 = cx1.getCinema(2);
        c2.addShowtime("15:00", 1);
        c2.addShowtime("18:00", 1);
        Cinema c3 = cx1.getCinema(3);
        c3.addShowtime("12:00", 3);
        c3.addShowtime("15:00", 2);
        c3.addShowtime("18:00", 2);

        do {
            System.out.println("(1) List All Movies");
            // display a list of all movies with their id,
            // prompt the user for a movie id or exit,
            // display all cineplex & cinema & date & showtime & type of cinema/seats that shows that movie with that id,
            // prompt the user to click on a cineplex or exit,
            // clicking a cineplex will display the cinemas and showtime,
            // prompt for a cinema and showtime or exit,
            // show the available seats
            // allow user to book tickets or exit

            System.out.println("(2) Search Movies");
            // search movie by title, then do the same thing as (1)

            System.out.println("(3) Top 5 Movies");
            // top 5 ratings by:
            // 1) ticket sales
            // 2) ratings

            //System.out.println("(4) List Cineplexes");
            // ...

            System.out.println("(5) Booking History");
            // see booking history
            // rate booked tickets

            System.out.println("(6) Admin Login");
            // login as admin to admin module
            
            System.out.println("(7) Exit Application");

            choice = sc.nextInt();
        }
        while (choice != 7);
        sc.close();
    }
}
