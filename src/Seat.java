/*
    'Seat' class

    - Stores whether the seat is booked or not
*/

public class Seat {
    private String code;
    private boolean booked;
    private boolean isSeat;  // 1 = seat, 0 = stairwell or aisle space

    public Seat(String code, boolean isSeat, boolean booked) {
        this.code = code;
        this.isSeat = isSeat;
        this.booked = booked;
    }

    // mutators
    public void setCode(String code) { this.code = code;}
    public void bookSeat() { this.booked = true;}
    public void unbookSeat() {this.booked = false;}

    // accessors
    public String getSeatCode() { return this.code;}
    public boolean getIsSeat() { return this.isSeat; }
    public boolean isBooked() { return this.booked;}
}
