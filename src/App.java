import boundary.AdminUI;
import boundary.BookingUI;
import boundary.MovieGoerUI;
import boundary.MovieUI;
import boundary.ReviewUI;
import boundary.UtilUI;
import controller.AppController;

public class App {
    public static void main(String[] args) throws Exception {
        UtilUI.printWelcomeMessage();
        AppController.init();

        int choice, movieGoerId;
        do {
            System.out.println(">>>>> MAIN MENU <<<<<");
            System.out.println("(1) List All Movies");
            System.out.println("(2) Movie Detail View");
            System.out.println("(3) Search Movie");
            System.out.println("(4) Top 5 Movies based on Ticket Sales");
            System.out.println("(5) Top 5 Movies based on Ratings");
            System.out.println("(6) Book Ticket");
            System.out.println("(7) Booking History");
            System.out.println("(8) Review Movie");
            System.out.println("(9) Admin Login");
            System.out.println("(10) Exit Application");

            choice = UtilUI.getInt("Select action: ");

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
        AppController.save();
    }
}