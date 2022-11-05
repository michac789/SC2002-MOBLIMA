/*
    'Seat' class

    - Stores whether the seat is booked or not
*/

public class Seat {
    private String code;
    private boolean booked;

    public Seat(String code) {
        this.code = code;
        this.booked = false;
    }

    public Seat(String code, boolean booked) {
        this.code = code;
        this.booked = booked;
    }

    // mutators
    public void setCode(String code) { this.code = code;}
    public void bookSeat() { this.booked = true;}
    public void unbookSeat() {this.booked = false;}

    // accessors
    public String getSeatCode() { return this.code;}
    public boolean isBooked() { return this.booked;}
}
