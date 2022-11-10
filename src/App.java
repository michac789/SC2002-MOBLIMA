import java.util.Scanner;
import boundary.AdminUI;
// import boundary.MovieUI;
// import controller.CinemaController;
// import model.Cinema.showClassOptions;
import controller.AppController;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to MOBLIMAA !!!\n");

        AppController.init();
    
        System.out.println("TESTERRR");
        
        // System.exit(1);
        int choice;
        Scanner sc = new Scanner(System.in);

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

            System.out.println("(3) Top 5 Movies based on Ticket Sales");
            System.out.println("(4) Top 5 Movies based on Ratings");
            // top 5 ratings by:
            // 1) ticket sales
            // 2) ratings
            // then do the same thing as (1)

            // list cineplex feature -> NOT REQUIRED
            // can add later if you want
            // ...

            System.out.println("(5) Booking History");
            // see booking history
            // rate booked tickets

            System.out.println("(6) Admin Login");
            // login as admin to admin module
            System.out.println("(7) Booking a ticket");
            System.out.println("(8) Exit Application");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    // MovieUI.displayAllMovies();
                    System.out.println("TODO");
                    bookingFlow();
                    break;
                
                case 2:
                    String searchQuery = sc.nextLine();
                    int movieId = AppController.mc.searchMovie(searchQuery);
                    // Navigate to movieOptions, Booking etc.
                    break;
                
                case 3:
                    AppController.mc.rankMovieBySales(5); // TODO
                    break;
                
                case 4:
                    AppController.mc.rankMovieByRating(); // TODO
                    break;
                
                case 5:
                    historyFlow(); // TODO
                    break;

                case 6:
                    AdminUI.main();
                    break;
                case 7:
                    bookingFlow();
                    break;
            }
        }
        while (choice != 8);
        sc.close();

        // save all the changes to database (csv)
        AppController.save();
    }

    private static void bookingFlow() {
        // prompt the user for a movie id or exit,
        // display all cineplex & cinema & date & showtime & type of cinema/seats that shows that movie with that id,
        // prompt the user to click on a cineplex or exit,
        // clicking a cineplex will display the cinemas and showtime,
        // prompt for a cinema and showtime or exit,
        // show the available seats
        // allow user to book tickets or exit

        System.out.println("Enter Movie ID: ");
        System.out.println("Enter (-1) to go back");

        // int movieId = sc.nextInt();
        // if (movieId == -1) { return;}

        // TODO... continue
        // maybe consider creating CineplexController class??
        // to stores an array of cineplexes, then
        // contain methods to loop through every cineplex
        // and check stuff

        System.out.println("BLABLABLA");
    }

    private static void historyFlow() {


        // TODO
        // enter user id / name
        // show a list of booking history for that user
        // add options to add review for movies watched
    }
}
