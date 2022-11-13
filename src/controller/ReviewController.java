package controller;
import java.util.ArrayList;
import DAO.ReviewDAO;
import model.Booking;
import model.Movie;
import model.Review;

public class ReviewController {
    private ArrayList<Review> reviews;
    private ReviewDAO reviewDao = new ReviewDAO();
    private int movieId;

    public ReviewController(int movieId) {
        this.movieId = movieId;
        this.reviews = this.reviewDao.load(movieId);
    }

    public void save() {
        this.reviewDao.save(this.reviews, this.movieId);
    }
    
    public ArrayList<Review> getAllReviews() {
        return this.reviews;
    }

    public void createReview(int movieGoerId, int rating, String comment) {
        Review newReview = new Review(movieGoerId, rating, comment);
        reviews.add(newReview);
    }

    public int getNumReviews() {
        return this.reviews.size();
    }

    public double getAverageRating() {
        double rating = 0;
        for (int i = 0; i < this.reviews.size(); i++) {
            rating += this.reviews.get(i).getRating();
        }
        return (rating / getNumReviews());
    }

    public void displayReviews() {
        if (this.reviews.size() == 0) {
            System.out.println("No reviews yet.\n");
        } else {
            System.out.println("Displaying all " + this.reviews.size() + " reviews:");
            System.out.println("-----------------------------------------------------");
            for (int i = 0; i < this.reviews.size(); i++) {
                System.out.print(this.reviews.get(i));
            }
            System.out.println("");
        }
    }

    /*
     * Returns true if a user has ever reviewed this movie before, otherwise false
     */
    public static boolean hasReviewed(int movieId, int movieGoerId) {
        ArrayList<Review> reviews = AppController.mc.getMovieById(movieId).getController().getAllReviews();
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getUserId() == movieGoerId) {
                return true;
            }
        }
        return false;
    }

    /*
     * Returns an arraylist of all booked movies
     */
    public static ArrayList<Integer> bookedMovies(int movieGoerId) {
        ArrayList<Booking> bookings = AppController.mgc.getMovieGoerById(movieGoerId).getController().getBookings();
        ArrayList<Integer> validIds = new ArrayList<Integer>();
        for (int i = 0; i < bookings.size(); i++) {
            Movie m = AppController.mc.getMovieByTitle(bookings.get(i).getTitle());
            validIds.add(m.getMovieId());
            System.out.println(
                String.format("Movie ID %d: %s ", m.getMovieId(), m.getTitle()) +
                (hasReviewed(m.getMovieId(), movieGoerId) ? "(reviewed)" : "(not reviewed")
            );
        }
        return validIds;
    }
}