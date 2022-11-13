package boundary;
import java.util.ArrayList;
import controller.AppController;
import controller.ReviewController;

public class ReviewUI {
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

    private static void newReview(int movieGoerId) {
        UtilUI.printBlue("|=========|Review Movie|=========|");

        ArrayList<Integer> validIds = ReviewController.bookedMovies(movieGoerId);
        if (validIds == null) {
            UtilUI.printRed("You have not booked any movies yet, you can only review movies you have booked!");
            return;
        }
        int movieId;
        while (true) {
            movieId = UtilUI.promptInt(validIds, "Enter a movie ID: (enter -1 to exit) ");
            if (movieId == -1) { return;}
            if (ReviewController.hasReviewed(movieId, movieGoerId)) {
                UtilUI.printRed("You have reviewed this movie before! You cannot review more than once!");
            } else {
                break;
            }
        }

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