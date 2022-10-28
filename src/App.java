/*
    'App' class

    Master file to be executed to launch the program
*/

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to MOBLIMA !!!\n");

        // create sample movies
        MoviesController mc = new MoviesController();
        mc.addMovie(
            "movie1", 90,
            "dir1", "cast1",
            Movie.showStatusOptions.NOW_SHOWING,
            Movie.ageRatingOptions.G,
            false, false
        );
        mc.addMovie(
            "movie2", 100,
            "dir2", "cast2",
            Movie.showStatusOptions.NOW_SHOWING,
            Movie.ageRatingOptions.G,
            false, false
        );
        mc.displayAllMovies();
        ReviewsController mr = new ReviewsController(0); //Uncomment the init at Movie.java ltr
//        mr.createReview("movie2", 1);
        mr.displayReviews(1);

        System.exit(0);

        // sample cineplexes
        Cineplex cx1 = new Cineplex("location1");
        Cineplex cx2 = new Cineplex("location2");
        cx1.printCineplexInfo();
        cx2.printCineplexInfo();

        // add sample cinemas to cineplex
        cx1.addCinema(10, 16, Cinema.showClassOptions.CLASS1);
        cx1.addCinema(10, 16, Cinema.showClassOptions.CLASS1);
        cx1.addCinema(8, 12, Cinema.showClassOptions.CLASS1);
        cx2.addCinema(12, 20, Cinema.showClassOptions.CLASS2);
        cx2.addCinema(12, 20, Cinema.showClassOptions.CLASS3);
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
                    mc.displayAllMovies();
                    bookingFlow(sc);
                    break;
                
                case 2:
                    System.out.println("Type movie title");
                    String searchQuery = sc.nextLine();
                    mc.searchMovie(searchQuery); // TODO
                    bookingFlow(sc);
                    break;
                
                case 3:
                    mc.rankMovieBySales(5); // TODO
                    bookingFlow(sc);
                    break;
                
                case 4:
                    mc.rankMovieByRating(5); // TODO
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
