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

        int choice, movieGoerId = -1;
        do {
            UtilUI.printBlue(">>>>> MAIN MENU <<<<<");
            if (movieGoerId == -1) {
                UtilUI.printPurple("You are currently not logged in.");
                System.out.println("(0) MovieGoer Login/Register");
            } else {
                UtilUI.printPurple("You are logged in as " +
                    AppController.mgc.getMovieGoerById(movieGoerId).getUsername());
                System.out.println("(0) Logout");
            }
            System.out.println("(1) List All Movies");
            System.out.println("(2) Movie Detail View");
            System.out.println("(3) Search Movie");
            System.out.println("(4) Top 5 Movies based on Ticket Sales");
            System.out.println("(5) Top 5 Movies based on Ratings");
            System.out.println("(6) Book Ticket (login required)");
            System.out.println("(7) Booking History (login required)");
            System.out.println("(8) Review Movie (login required)");
            System.out.println("(9) Admin Login");
            System.out.println("(10) Exit Application");
            choice = UtilUI.getInt("Select action: ");

            switch (choice) {
                case 0:
                    if (movieGoerId == -1) {
                        movieGoerId = MovieGoerUI.main();
                    } else {
                        movieGoerId = -1;
                        UtilUI.printGreen("Successfully Logged Out\n");
                    }
                    break;
                
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
                    if (movieGoerId == -1) {
                        UtilUI.printRed("Please login/register first!\n");
                        break;
                    }
                    BookingUI.main(movieGoerId);
                    break;

                case 7:
                    if (movieGoerId == -1) {
                        UtilUI.printRed("Please login/register first!\n");
                        break;
                    }
                    BookingUI.history(movieGoerId);
                    break;

                case 8:
                    if (movieGoerId == -1) {
                        UtilUI.printRed("Please login/register first!\n");
                        break;
                    }
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