// TODO - still messy, please fix

public class Review {
    private int userId;
    private int rating; // range limit??
    private String comment;

    public Review(int userId, int rating, String comment) {
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }

    public void printReview() {
        System.out.println("User ID: " + this.userId);
        System.out.println("Rating: " + this.rating);
        System.out.println("Comment: " + this.comment);
        System.out.println("text");
    }

    public void setRating(int rating) { this.rating = rating;}
    public void setComment(String comment) { this.comment = comment;}

    public int getRating() { return this.rating;}
    public String getComment() { return this.comment;}
}
