package model;

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
    
    // Returns a String to save into csv
    public String savetoCSV() {
        String rtn = String.format("%d,%d,\"%s\"\n", this.userId, this.rating, this.comment);
        return rtn;
    }

    // Method Override
    public int compareTo(Object o) {
        Review r = (Review) o;
        if (this.rating ==  r.getRating()) {
//            return (r.userId - this.userId);
            return -1; // Btr to return this so dont need do any swaps
        } else {
            return (this.rating - r.getRating());
        }
    }

    public String toString() {
        String divider = "-------------------------\n";
        String rtn = String.format("User ID: %d\n" +
                                    "Rating: %d\n" +
                                    "Comment:\n%s",
                                    this.userId, this.rating, this.comment);
        rtn = rtn + divider;
        return rtn;
    }
}
