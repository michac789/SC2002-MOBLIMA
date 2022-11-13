package model;
/**
 * Represents the seat object
 * @version 1.0
 * @since 2022-11-13
 */
public class Seat {
    /**
     * The code of the seat
     */
    private String code;
    /**
     * The indicator whether seat is booked or not
     */
    private boolean isBooked;
    /**
     * The indicator whether it is a seat or a non seat object such as aisle and stair
     */
    private boolean isSeat;

    /**
     * Constructor for creating seat for specific cinema
     * @param code The code of seats
     * @param isBooked The indicator whether it is booked or not
     * @param isSeat The indicator whether it is a seat or not
     */
    public Seat(String code, boolean isBooked, boolean isSeat) {
        this.code = code;
        this.isBooked = isBooked;
        this.isSeat = isSeat;
    }

    /**
     * Sets the seat code
     * @param code The seat code
     */
    public void setCode(String code) { this.code = code;}

    /**
     * Books the seat by setting the indicator of booked to be true
     */
    public void bookSeat() { this.isBooked = true;}

    /**
     * Unbooks the seat
     */
    public void unbookSeat() { this.isBooked = false;}
    /**
     * Gets the seat code
     * @return The seat code
     */
    public String getSeatCode() { return this.code;}
    /**
     * Gets the status of the seat being booked or not
     * @return The indicator value whether it is booked or not
     */
    public boolean getIsBooked() { return this.isBooked;}

    /**
     * Gets the indicator whether it is a seat or not
     * @return The indicator value whether it is a seat or not
     */
    public boolean getIsSeat() { return this.isSeat;}
}