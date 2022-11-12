package model;

public class Booking {
    private int userId;
    private String title;
    private String location;
    private int cinemaCode;
    private String datetime;
    private String seat;
    private float price;

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

    public int getUserId() { return this.userId;}
    public String getTitle() { return this.title;}
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