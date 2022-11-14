package controller;
import model.Seat;

/**
 * Controller for Seat objects,
 * each corresponds to a unique movie
 * @version 1.0
 * @since 2022-11-13
 */
public class SeatController {
    /**
     * The height of seating layout
     */
    private int height;
    /**
     * The width of seating layout
     */
    private int width;
    /**
     * Array 2D of seats object belong to aprticular cinema
     */
    private Seat[][] seats;
    /**
     * The color code for red
     */
    private static final String ANSI_RED = "\u001b[31m";  // red = seat taken
    /**
     * The color code for green
     */
    private static final String ANSI_GREEN = "\u001b[32m";  // green = seat available
    /**
     * The color code for color resetting
     */
    private static final String ANSI_RESET = "\u001B[0m";  // original command line text colour

    /**
     * SeatController constructor to create with customized properties
     * @param height The height of seating layout
     * @param width The width of seating layout
     * @param cineplexId The cineplex ID
     * @param cinemaId The cinema ID
     * @param seats The array 2D of seats
     */
    public SeatController(int height, int width, int cineplexId, int cinemaId, Seat[][] seats) {
        this.height = height;
        this.width = width;
        this.seats = seats;
    }

    /**
     * Serualizes seats into String format
     * @return String representation of Seating layout
     */
    public String serializeSeats() {
        String s = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                s = s + (!this.seats[i][j].getIsSeat() ? "X" :
                    (this.seats[i][j].getIsBooked() ? "F" : "E"));
            }
        }
        return s;
    }


    /**
     // print seat with colour according to whether it has been booked or not
     * @param seat The seat object that wants to be printed
     */
    public void printSeat(Seat seat) {
        if (seat.getIsSeat() && seat.getIsBooked()) {  // booked seat
            System.out.print(ANSI_RED + seat.getSeatCode() + ANSI_RESET);
        } else if (seat.getIsSeat() && !seat.getIsBooked()) {  // unbooked seat
            System.out.print(ANSI_GREEN + seat.getSeatCode() + ANSI_RESET);
        } else {  // stairwell or aisle
            for (int i = 0; i < seat.getSeatCode().length(); i++) { System.out.print(" "); }
        }
    }

    /**
     * Gets the number of available seats
     * @return The number of available seats
     */
    public int availSeats() {
        int counter = 0;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j].getIsSeat() && !seats[i][j].getIsBooked()) { counter++; }
            }
        }
        return counter;
    }

    /**
     * Display seats in this SeatController
     */
    public void displaySeats() {
        if (availSeats() == 0) {   // showtime fully booked
            System.out.println("No available seats left.");
        }
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                printSeat(seats[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("Legend: Green seats are available. Red seats are not available.");
    }

    /**
     * Validate the seats range
     * @param startCode The code of starting seat
     * @param seatCount The number of seats
     * @return The indicator whether seats selected valid or not
     */
    public boolean validateSeats(String startCode, int seatCount) {
        String rowLetter = startCode.substring(0, 1);
        String columnNumber = startCode.substring(1);
        for (int i = 0; i < seatCount; i++) {
            int newColumnNumber = Integer.parseInt(columnNumber) + i;
            String seatCode = rowLetter + newColumnNumber;
            if (!seatExists(seatCode)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Book seats by indicating starting seat and the number of seats
     * @param startCode The code of starting seat
     * @param seatCount The number of seats
     */
    public void bookSeats(String startCode, int seatCount) {
        char row = startCode.charAt(0);
        int col = Integer.parseInt(startCode.substring(1));
        for (int i = 0; i < seatCount; i++) {
            this.seats[((int)row) - 65][col - 1 + i].bookSeat();
        }
    }

    /**
    // checks if the seatCode corresponds to an actual seat and not booked yet
    * @param trySeatCode Seat code that is tested
    * @return The indicator whether the seat is an actual seat and unoccupied
    */
    public boolean seatExists(String trySeatCode) {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (trySeatCode.equals(seats[i][j].getSeatCode())) {   // object exists
                    if (seats[i][j].getIsSeat() && !seats[i][j].getIsBooked()) {
                        return true;
                    }   // is a seat, not stairwell/aisle, is not booked yet
                }
            }
        }
        return false;
    }
}