package boundary;
import java.util.ArrayList;
import controller.AppController;
import controller.ReviewController;
import model.Movie;
/**
 * Represents the boundary of moviegoer's platform to review movie review
 * Consisting of multiple methods which interact directly with reviewController
 * @version 1.0
 * @since 2022-11-13
 */
public class ReviewUI {
    /**
     * Displays UI for moviegoer to interact with his/her past reviews and create new review
     * @param movieGoerId The moviegoer's ID whoose logged in
     */
    public static void main(int movieGoerId) {
        while (true) {
            UtilUI.printBlue("Review Panel");
            System.out.print(
                "(1) View All My Reviews\n" +
                "(2) Create New Review\n" +
                "(3) Exit\n");
            int choice = UtilUI.getInt("Select action: ");  
            switch (choice) { 
                case 1:
                    AppController.mgc.displayAllReviewsByMovieGoerId(movieGoerId);
                    break;
                case 2:
                    newReview(movieGoerId);
                    break;
                case 3:
                    return;
                default:
                    UtilUI.printRed("Invalid action, try again!");
                    break;
            }
        }
    }

    /**
     * Display UI for moviegoer to input his/her past review
     * @param movieGoerId The moviegoer's ID whoose logged in
     */
    private static void newReview(int movieGoerId) {
        UtilUI.printBlue("|=========|Review Movie|=========|");


        int movieId = UtilUI.getInt("Enter movie ID: ");

        if (movieId == -1) { return;}
        int reviewScore;
        do {
            reviewScore = UtilUI.getInt("Enter Movie Rating (1-5): ");
            if (reviewScore < 1 || reviewScore > 5) {
                UtilUI.printRed("Invalid entry, only integer 1 to 5 are allowed!");
            } else {
                break;
            }
        } while (true);
        String comment = UtilUI.getStrSafe("Enter your comment: ");
        if (comment.equals("-1")) { return;}
        AppController.mc.getMovieById(movieId).getController()
            .createReview(movieGoerId, reviewScore, comment);
        UtilUI.printGreen("Review successfully created");
    }
}