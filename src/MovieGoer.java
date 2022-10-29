/*
    'MovieGoer' class

    - Inherits from User class
    - Additionally contains name, mobile, reviews, bookings
*/

import java.util.*;

public class MovieGoer extends User {
    private String name;
    private String mobile;
    protected enum movieGoerType { ADULT, SENIOR, CHILD };
    protected movieGoerType mgt;
    private List<Review> reviews = new ArrayList<Review>();

    public MovieGoer(String name, String mobile, String email, userType ut, movieGoerType mgt) {
        super(email, ut);
        this.name = name;
        this.mobile = mobile;
        this.mgt = mgt;
    }

    // accessors
    public String getName() { return name; }
    public String getMobile() { return mobile; }
    public void displayReviews() {
        System.out.println("Showing all reviews:");
        for (int i = 0; i < this.reviews.size(); i++) {
            this.reviews.get(i).printReview();
        }
        System.out.println("");
    }

    // mutators
    public void setName(String name) { this.name = name; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public void addReview(Review review) { reviews.add(review); }
}