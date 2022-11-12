package model;
import controller.AppController;

public class Review {
    private int userId;
    private int rating;
    private String comment;

    public Review(int userId, int rating, String comment) {
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }

    public void setRating(int rating) { this.rating = rating;}
    public void setComment(String comment) { this.comment = comment;}

    public int getUserId() { return this.userId;}
    public int getRating() { return this.rating;}
    public String getComment() { return this.comment;}

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