/*
    'Seat' class

    - Stores whether the seat is booked or not
*/

public class Seat {
    private String code;
    private boolean booked;
    private static final String ANSI_BLUE = "\u001b[34m";  // blue = seat taken
    private static final String ANSI_WHITE = "\u001b[37m";  // white = seat available
    private static final String ANSI_RESET = "\u001B[0m";  // original command line colour

    public Seat(String code) {
        this.code = code;
        this.booked = false;
    }

    // mutators
    public void setCode(String code) { this.code = code;}
    public void bookSeat() { this.booked = true;}
    public void unbookSeat() {this.booked = false;}
    
    // accessors
    public String getSeatCode() { return this.code;}
    public boolean isBooked() { return this.booked;}
    public void printSeat() { 
        if (this.isBooked() == true) {
            System.out.println(ANSI_BLUE + this.getSeatCode() + ANSI_RESET);
        } else {
            System.out.println(ANSI_WHITE + this.getSeatCode() + ANSI_RESET);
        }
    }
}
