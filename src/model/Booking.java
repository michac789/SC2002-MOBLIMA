package model;

public class Booking {
    private int userId;
    private String title;
    private String location;
    private int cinemaCode;
    private String datetime;
    private String seat;
    private int price;

    public Booking(int userId, String title, String location, int cinemaCode, String datetime, String seat) {
        this.userId = userId;
        this.title = title;
        this.location = location;
        this.cinemaCode = cinemaCode;
        this.datetime = datetime;
        this.seat = seat;
    }

    public int getUserId() { return this.userId;}
    public String getTitle() { return this.title;}
    public String getLocation() { return this.location;}
    public int getCinemaCode() { return this.cinemaCode;}
    public String getDatetime() { return this.datetime;}
    public String getSeat() { return this.seat;}
    public int getPrice() { return this.price;}






}