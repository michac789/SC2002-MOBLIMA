import java.util.Scanner;
import boundary.AdminUI;
import boundary.BookingUI;
import boundary.MovieGoerUI;
import boundary.MovieUI;
import boundary.ReviewUI;
import boundary.WelcomeUI;
import controller.AppController;

public class App {
    public static void main(String[] args) throws Exception {
        WelcomeUI.printWelcomeMessage();
        AppController.init();

        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("(1) List All Movies");
            // display all movies: id, title, showing status

            System.out.println("(2) Movie Detail View");
            // prompt user for movie id
            // display complete movie info + rating + review

            System.out.println("(3) Search Movie");
            // prompt user for string x
            // display all movies that has 'x' as a substring

            System.out.println("(4) Top 5 Movies based on Ticket Sales");

            System.out.println("(5) Top 5 Movies based on Ratings");

            System.out.println("(6) Book Ticket");
            // ask user to log in
            // prompt user for movie id
            // show all cineplex that show that movie
            // prompt user for cineplex id
            // show all cinemas and showtime for that movie
            // prompt for cinema code, than showtime
            // show all seats, able to book one or more seats
            // checkout option, price calculation, create booking

            System.out.println("(7) Booking History");
            // ask user to log in
            // print booking history of that user

            System.out.println("(8) Review Movie");
            // ask user to log in
            // view all reviews of this user
            // create new review

            System.out.println("(9) Admin Login");
            // perform various admin functionalities

            System.out.println("(10) Exit Application");

            choice = sc.nextInt();
            sc.nextLine();
            int movieGoerId;

            switch (choice) {
                case 1:
                    MovieUI.displayAllMovies();
                    break;
                
                case 2:
                    MovieUI.displayDetailMovie();
                    break;
                
                case 3:
                    MovieUI.searchMovie();
                    break;
                
                case 4:
                    AppController.mc.rankMovieBySales(5);
                    break;
                
                case 5:
                    AppController.mc.rankMovieByRating(5);
                    break;

                case 6:
                    movieGoerId = MovieGoerUI.main();
                    if (movieGoerId == -1) { break;}
                    BookingUI.main(movieGoerId); // TODO
                    break;

                case 7:
                    movieGoerId = MovieGoerUI.main();
                    if (movieGoerId == -1) { break;}
                    BookingUI.history(movieGoerId); // TODO
                    break;

                case 8:
                    movieGoerId = MovieGoerUI.main();
                    if (movieGoerId == -1) { break;}
                    ReviewUI.main(movieGoerId);
                    break;

                case 9:
                    AdminUI.main();
                    break;
            }
        }
        while (choice != 10);
        sc.close();
        AppController.save();
    }
}