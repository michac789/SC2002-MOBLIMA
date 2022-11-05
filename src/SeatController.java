import java.io.*;
import java.util.*;

public class SeatController {
    private int showtimeId; 
    private Seat[][] seats;
    private Scanner sc = new Scanner(System.in);
    private static SeatDAO seatDao = new SeatDAO();
    private static final String ANSI_BLUE = "\u001b[34m";  // blue = seat taken
    private static final String ANSI_WHITE = "\u001b[37m";  // white = seat available
    private static final String ANSI_RESET = "\u001B[0m";  // original command line colour

    public SeatController(int showtimeId) {
        this.showtimeId = showtimeId;
        //seatDAO.getSeats(showtimeId, seats);  IS THIS NECESSARY?
    }

    // create seats
    public void createSeats(int h, int w) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                seats[i][j] = new Seat(Character.toString((char)(65 + i)) + (j + 1));
            }
        }
        seatDao.saveSeats(seats, this.showtimeId);
    }

    // print seat with colour according to whether it has been booked or not
    public void printSeat(Seat seat) {
        if (seat.isBooked() == true) {
            System.out.print(ANSI_BLUE + seat.getSeatCode() + ANSI_RESET);
        } else {
            System.out.print(ANSI_WHITE + seat.getSeatCode() + ANSI_RESET);
        }
    }

    // display seats
    public void displaySeats() {
        System.out.println("Seats Layout");
        int aisle = seats[0].length/2;
        for (int i = 0; i < seats.length; i++) { 
            if (i < 4) { System.out.print("    "); }  // main stairway
            for (int j = 0; j < seats[0].length; j++) {
                if (j == aisle) { System.out.print("  "); }  // aisle
                printSeat(seats[i][j]);
                System.out.println(" ");
            }
            System.out.println();
        }
    }

    // book seats
    public void bookSeats() {
        char row;
        int col;
        String trySeatCode;
        displaySeats(); 
        // DO WE NEED AN EXIT?
        do {
            System.out.print("Enter seat row: ");
            row = Character.toUpperCase(sc.next().charAt(0));  // convert input char to upper case
            System.out.print("Enter seat column: ");
            col = sc.nextInt();
            sc.nextLine();  // consume the new line
            trySeatCode = Character.toString(row) + Integer.toString(col);  // convert to seatCode
            if (seatExists(trySeatCode) == false) {  // check if the seat exists
                System.out.println("Invalid entry!");
            } else {
                if (seats[((int) row) - 65][col - 1].isBooked() == true) {  // check if the seat is available
                    System.out.println("The seat is unavailable.");
                } else {
                    break;
                }
            }
        } while (true);
        seats[((int) row) - 65][col - 1].bookSeat();
        seatDao.saveSeats(seats, this.showtimeId);
    }
    
    // check if the seatCode corresponds to an actual seat
    public boolean seatExists(String trySeatCode) {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (trySeatCode == seats[i][j].getSeatCode()) { 
                    return true;
                }
            }
        }
        return false;
    }

}
