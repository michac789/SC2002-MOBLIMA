package boundary;
import java.util.Scanner;
import controller.AppController;

public class ReviewUI {
    private static Scanner sc = new Scanner(System.in);

    public static void main(int movieGoerId) {
        while (true) {
            System.out.print(
                "(1) View All My Reviews\n" +
                "(2) Create New Review\n" +
                "(3) Exit\n");
            System.out.print("Select action: ");  
            int choice = sc.nextInt();
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
                    System.out.println("Invalid action, try again!");
                    break;
            }
        }
    }

    private static void newReview(int movieGoerId) {
        System.out.println("|=========|Review Movie|=========|");
        System.out.print("Enter a movie ID: ");
        // TODO - validate valid movie id, make sure user haven't review before
        int movieId = sc.nextInt();
        int reviewScore;
        do {
            System.out.print("Enter Movie Rating (1-5): ");
            reviewScore = sc.nextInt();
            sc.nextLine();
            if (reviewScore < 1 || reviewScore > 5) {
                System.out.println("Invalid Entry!");
            } else {
                break;
            }
        } while (true);
        String comment = UtilUI.getStrSafe("Enter your comment: ");
        AppController.mc.getMovieById(movieId).getController()
            .createReview(movieGoerId, reviewScore, comment);
        System.out.println("Review successfully created");
    }
}