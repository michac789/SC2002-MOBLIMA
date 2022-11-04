/*
    'App' class

    Master file to be executed to launch the program
*/

import java.util.Date;
import java.util.Scanner;

import cinema.CinemasController;
import cineplex.Cineplex;
import cineplex.CineplexesController;
import movie.MoviesController;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to MOBLIMA !!!\n");
        Date todaysDate = new Date();
        System.out.println(todaysDate);

        // load models: movies, cineplexes, cinemas, TODO - showtimes, seats
        MoviesController moviesController = new MoviesController();
        CineplexesController cineplexesController = new CineplexesController();
        CinemasController cinemasController = new CinemasController();

        moviesController.save();
        cineplexesController.save();
        cinemasController.save();
        
        
        System.exit(1);


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
            
            System.out.println("(7) Exit Application");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                moviesController.displayAllMovies();
                    bookingFlow(sc);
                    break;
                
                case 2:
                    System.out.println("Type movie title");
                    String searchQuery = sc.nextLine();
                    moviesController.searchMovie(searchQuery); // TODO
                    bookingFlow(sc);
                    break;
                
                case 3:
                    moviesController.rankMovieBySales(5); // TODO
                    bookingFlow(sc);
                    break;
                
                case 4:
                    moviesController.rankMovieByRating(5); // TODO
                    bookingFlow(sc);
                    break;
                
                case 5:
                    historyFlow(); // TODO
                    break;

                case 6:
                    adminFlow(); // TODO
            }
        }
        while (choice != 7);
        sc.close();
    }

    private static void bookingFlow(Scanner sc) {
        // prompt the user for a movie id or exit,
        // display all cineplex & cinema & date & showtime & type of cinema/seats that shows that movie with that id,
        // prompt the user to click on a cineplex or exit,
        // clicking a cineplex will display the cinemas and showtime,
        // prompt for a cinema and showtime or exit,
        // show the available seats
        // allow user to book tickets or exit

        System.out.println("Enter Movie ID: ");
        System.out.println("Enter (-1) to go back");

        int movieId = sc.nextInt();
        if (movieId == -1) { return;}

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

    private static void adminFlow() {
        // TODO
        // maybe can create seperate class for admin
    }
}
