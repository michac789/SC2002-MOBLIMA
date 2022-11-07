package controller;
import java.util.LinkedList;
import java.util.Scanner;
import DAO.ReviewDAO;
import model.Review;

public class ReviewController {
    private int movieId;
    private int rating;

    private Scanner sc;

    private int sorted = 0;
    private LinkedList<Review> reviews;

    private static ReviewDAO reviewDao = new ReviewDAO();

    public ReviewController(int movieId) {
        this.rating = 0;
        this.movieId = movieId;
        sc = new Scanner(System.in);
        reviews = new LinkedList<Review>();
        reviewDao.getReviews(movieId, reviews);
        sortByRating();
        System.out.println("Review for movieId " + movieId + " loaded.");
    }

    public void createReview(String movieName, int userId) {
        int reviewScore = -1;
        String reviewText = "";
        //Temporary Take in moviename, will use movie id ltr? Or implementation of creating review can take in moviename instead?
        // Do we need a exit parameter???
        System.out.printf("Creating Review for %s\n", movieName);
        do {
            System.out.print("Enter Movie Rating (1-5): ");
            reviewScore = sc.nextInt();
            sc.nextLine(); // Scanner Skipping reviewText Scanner
            if (reviewScore < 1 || reviewScore > 5) {
                System.out.println("Invalid Entry!");
            } else {
                break;
            }
        } while (true);
        System.out.print("Enter Review: \n");
        reviewText = sc.nextLine(); // Do i have to read comments that have newline??, if so need replace this
        Review newReview = new Review(userId, reviewScore, reviewText);
        addReview(newReview);
        this.rating += reviewScore;

        reviewDao.saveReview(newReview, this.movieId);
    }

    // Keeps reviews linkedlist sorted at all times
    public void addReview(Review r) {
        if (reviews.size() == 0) {
            reviews.add(r);
        }else {
            for (int i=0; i < reviews.size();i++) {
                if (reviews.get(i).getRating() < r.getRating()) {
                    reviews.add(i,r);
                }
            }
        }
    }

    public void displayReviews(int displayBy) {
        if (sorted == 0) {sortByRating();}

        if (displayBy == 1) {
            // Descending
            for (int i = 0; i < reviews.size(); i++) {
                System.out.print(reviews.get(i));
            }
        } else {
            // Ascending
            for (int i = reviews.size()-1; i >= 0; i--) {
                System.out.print(reviews.get(i));
            }
        }
    }

    public void sortByRating() {
        // Sort by Descending Rating
        // Need by Alphabetical???
        Review temp;

        // Insertion Sort
        for (int i=0; i < reviews.size(); i++) {
            this.rating += reviews.get(i).getRating();
            for (int j=i; j > 0; j--) {
                if (reviews.get(j).compareTo(reviews.get(j - 1)) > 0) {
                    temp = reviews.get(j - 1);
                    reviews.set(j - 1, reviews.get(j));
                    reviews.set(j, temp);
                }else {
                    break;
                }
            }
        }

        // Quicksort?

    }

    public double getRating() {
        if (reviews.size() == 0) {return -1;} // No Reviews

        return ((this.rating/ 1.0) / reviews.size()); //Force typecast to double then divide by int;
    }
}
