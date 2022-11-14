package model;
import controller.AppController;

public class Review {
    /**
     * The user ID whose review belong to
     */
    private int userId;
    /**
     * The rating of movie reviewed
     */
    private int rating;
    /**
     * The comment of the movie reviewed
     */
    private String comment;

    /**
     * The constructor for review
     * @param userId The user ID of reviewer
     * @param rating The rating of the movie reviewed
     * @param comment The comment of the movie reviewed
     */
    public Review(int userId, int rating, String comment) {
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }

    /**
     * Sets the rating of the movie reviewed
     * @param rating The rating of the movie reviewed
     */
    public void setRating(int rating) { this.rating = rating;}

    /**
     * Sets the comment of the movie reviewed
     * @param comment The commment of the movie reviewed
     */
    public void setComment(String comment) { this.comment = comment;}

    /**
     * Gets the user ID of reviewer
     * @return The user ID of reviewer
     */
    public int getUserId() { return this.userId;}

    /**
     * Get the rating of the movie reviewed
     * @return The rating of the movie from the review
     */
    public int getRating() { return this.rating;}

    /**
     * Get the comment of the movie reviewed
     * @return The comment of the movie reviewed
     */
    public String getComment() { return this.comment;}

    /**
     * Formats the review to display relevant information about the review
     * @return String representation of the review object
     */
    public String toString() {
        return String.format(
            "Username  : %s\n" +
            "Rating    : %d\n" +
            "Comment   :\n%s\n" +
            "-----------------------------------------------------\n",
            AppController.mgc.getMovieGoerById(userId).getUsername(),
            this.rating, this.comment
        );
    }
}