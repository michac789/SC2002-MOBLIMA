package model;
/**
 * Represents the booking object
 * @version 1.0
 * @since 2022-11-13
 */
public class Booking {
    /**
     * The user ID related to the booking object
     */
    private int userId;
    /**
     * The title of the movie related to the booking object
     */
    private String title;
    /**
     * The location of cineplex related to the booking object
     */
    private String location;
    /**
     * The cinema code related to the booking object
     */
    private int cinemaCode;
    /**
     * The date and time related to the booking object
     */
    private String datetime;
    /**
     * The seating related to the booking object
     */
    private String seat;
    /**
     * The cost of moviegoer's purchase in this specific booking
     */
    private float price;

    /**
     * Constructor for creating booking object with the ability to store meta information regarding booking
     * @param userId The user ID
     * @param title The title of the movie
     * @param location The location of cineplex
     * @param cinemaCode The cinema code
     * @param datetime The date and time of booking
     * @param seat The seating related to user's booking
     * @param price The cost of moviegoer's purchase in this specific booking
     */
    public Booking(int userId, String title, String location, int cinemaCode,
            String datetime, String seat, float price) {
        this.userId = userId;
        this.title = title;
        this.location = location;
        this.cinemaCode = cinemaCode;
        this.datetime = datetime;
        this.seat = seat;
        this.price = price;
    }

    /**
     * Getter for user ID related to booking object
     * @return The user ID
     */
    public int getUserId() { return this.userId;}

    /**
     * Getter for movie title related to booking object
     * @return The title of the movie
     */
    public String getTitle() { return this.title;}

    /**
     * Getter for location of the booking
     * @return
     */
    public String getLocation() { return this.location;}
    public int getCinemaCode() { return this.cinemaCode;}
    public String getDatetime() { return this.datetime;}
    public String getSeat() { return this.seat;}
    public float getPrice() { return this.price;}

    public String toString() {
        return
            "Movie Title           : " + this.title + "\n" +
            "Cineplex Location     : " + this.location + "\n" +
            "Cinema Code           : " + this.cinemaCode + "\n" +
            "Showtime Date & Time  : " + this.datetime + "\n" +
            "Seat Code             : " + this.seat + "\n" +
            "Price                 : " + String.format("%.2f", this.price)+
            "\n-------------------------\n";
    }
}