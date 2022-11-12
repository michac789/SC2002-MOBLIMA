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
            System.out.println("(2) Search Movie");
            System.out.println("(3) Top 5 Movies based on Ticket Sales");
            System.out.println("(4) Top 5 Movies based on Ratings");
            System.out.println("(5) Book Ticket (login required)");
            System.out.println("(6) Booking History (login required)");
            System.out.println("(7) Review Movie (login required)");
            System.out.println("(8) Admin Login");
            System.out.println("(9) Exit Application");
            choice = UtilUI.getInt("Select action: ");
            System.out.println("");

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
                    MovieUI.main();
                    break;
                
                case 2:
                    MovieUI.searchMovie();
                    break;
                
                case 3:
                    AppController.mc.rankMovieBySales(5);
                    break;
                
                case 4:
                    AppController.mc.rankMovieByRating(5);
                    break;
                
                case 5:
                    if (movieGoerId == -1) {
                        UtilUI.printRed("Please login/register first!\n");
                        movieGoerId = MovieGoerUI.main();
                    }
                    if (movieGoerId == -1) { break;}
                    BookingUI.main(movieGoerId);
                    break;

                case 6:
                    if (movieGoerId == -1) {
                        UtilUI.printRed("Please login/register first!\n");
                        movieGoerId = MovieGoerUI.main();
                    }
                    if (movieGoerId == -1) { break;}
                    BookingUI.history(movieGoerId);
                    break;

                case 7:
                    if (movieGoerId == -1) {
                        UtilUI.printRed("Please login/register first!\n");
                        movieGoerId = MovieGoerUI.main();
                    }
                    if (movieGoerId == -1) { break;}
                    ReviewUI.main(movieGoerId);
                    break;

                case 8:
                    AdminUI.main();
                    break;
            }
        }
        while (choice != 9);
        UtilUI.printGoodbyeMessage();
        AppController.save();
    }
}