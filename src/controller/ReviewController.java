package controller;
import java.util.ArrayList;
import DAO.ReviewDAO;
import model.Booking;
import model.Movie;
import model.Review;

/**
 * Controller for Review objects,
 * each corresponds to a unique movie
 * @version 1.0
 * @since 2022-11-13
 */
public class ReviewController {
    /**
     * Contain an arraylist of all reviews
     */
    private ArrayList<Review> reviews;
    /**
     * ReviewDAO to get and save data to database
     */
    private ReviewDAO reviewDao = new ReviewDAO();
    /**
     * Unique movie ID whose Movie object corresponds to this controller
     */
    private int movieId;

    /**
     * Construct Review Controller object, initiate movie ID,
     * load necessary reviews corresponding to a movieID
     * @param movieId ID of the Movie object
     */
    public ReviewController(int movieId) {
        this.movieId = movieId;
        this.reviews = this.reviewDao.load(movieId);
    }

    /**
     * Save all local changes regarding reviews of a particular movie to the database
     */
    public void save() {
        this.reviewDao.save(this.reviews, this.movieId);
    }
    
    /**
     * Get all reviews from a particular Movie Goer
     * @return ArrayList of all reviews corresponding to a MovieGoer
     */
    public ArrayList<Review> getAllReviews() {
        return this.reviews;
    }

    /**
     * 
     * @param movieGoerId
     * @param rating
     * @param comment
     */
    public void createReview(int movieGoerId, int rating, String comment) {
        Review newReview = new Review(movieGoerId, rating, comment);
        reviews.add(newReview);
    }

    /**
     * Get number of reviews
     * @return Number of reviews made in this movie
     */
    public int getNumReviews() {
        return this.reviews.size();
    }

    /**
     * Get the average rating of this movie
     * @return Double value containing average rating of a particular movieID
     */
    public double getAverageRating() {
        double rating = 0;
        for (int i = 0; i < this.reviews.size(); i++) {
            rating += this.reviews.get(i).getRating();
        }
        return (rating / getNumReviews());
    }

    /**
     * Displays all reviews associated to a Movie based on movieId
     */
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

    /**
     * Checks if a user has reviewed a particular movie or not
     * @param movieId ID of the Movie
     * @param movieGoerId ID of the MovieGoer
     * @return True if a user has ever reviewed this movie before, otherwise false
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

    /**
     * Get all booked movies by a particular MovieGoer
    * @param movieGoerId ID of MovieGoer
    * @return Arraylist of all booked movies
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