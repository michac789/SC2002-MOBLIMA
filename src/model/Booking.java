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
     * Gets user ID related to booking object
     * @return The user ID
     */
    public int getUserId() { return this.userId;}

    /**
     * Gets movie title related to booking object
     * @return The title of the movie
     */
    public String getTitle() { return this.title;}

    /**
     * Gets location of the booking
     * @return The location of the booking
     */
    public String getLocation() { return this.location;}

    /**
     * Gets the cinema code of the booking
     * @return The cinema code of the booking
     */
    public int getCinemaCode() { return this.cinemaCode;}

    /**
     * Gets the date and time of the booking
     * @return The string of date and time of booking
     */
    public String getDatetime() { return this.datetime;}

    /**
     * Gets the seat of the booking
     * @return The string of booked seat number
     */
    public String getSeat() { return this.seat;}

    /**
     * Gets the price of one specific booking
     * @return
     */
    public float getPrice() { return this.price;}

    /**
     * Formats the printing of booking information consisting of title, location, cinemaCode, datetime, seat, and price
     * @return The String representation of booking meta information
     */
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