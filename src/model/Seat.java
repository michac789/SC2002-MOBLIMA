package model;

public class Seat {
    private String code;
    private boolean isBooked;
    private boolean isSeat;

    public Seat(String code, boolean isBooked, boolean isSeat) {
        this.code = code;
        this.isBooked = isBooked;
        this.isSeat = isSeat;
    }

    public void setCode(String code) { this.code = code;}
    public void bookSeat() { this.isBooked = true;}
    public void unbookSeat() { this.isBooked = false;}

    public String getSeatCode() { return this.code;}
    public boolean getIsBooked() { return this.isBooked;}
    public boolean getIsSeat() { return this.isSeat;}
}