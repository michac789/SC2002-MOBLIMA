package controller;
import java.io.*;
import java.util.*;
import model.Seat;

public class SeatController {
    private int showtimeId;
    private int height;
    private int width;
    private Seat[][] seats;
    private Scanner sc = new Scanner(System.in);

    private static final String ANSI_RED = "\u001b[31m";  // red = seat taken
    private static final String ANSI_GREEN = "\u001b[32m";  // green = seat available
    private static final String ANSI_RESET = "\u001B[0m";  // original command line text colour

    public SeatController(int height, int width, int cineplexId, int cinemaId, Seat[][] seats) {
        // this.showtimeId = showtimeId;
        this.height = height;
        this.width = width;
        this.seats = seats;
    }

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

    // // create seats
    // public void createSeats(int h, int w) {
    //     seats = new Seat[h][w];
    //     boolean isSeat = true;  // true = seat, false = stairwell/aisle
    //     int aisle = w/2;   // assume aisle down the middle
    //     for (int i = 0; i < h; i++) {
    //         for (int j = 0; j < w; j++) {
    //             // assume first 4 rows have stairwell on the left 2 cols
    //             if ((i < 4 && j < 2) || j == aisle) { isSeat = false; }
    //             seats[i][j] = new Seat(Character.toString((char)(65 + i)) + (j + 1), isSeat, false);
    //             isSeat = true;
    //         }
    //     }
    //     seatDao.saveSeats(seats, this.showtimeId);
    // }

    // print seat with colour according to whether it has been booked or not
    public void printSeat(Seat seat) {
        if (seat.getIsSeat() && seat.getIsBooked()) {  // booked seat
            System.out.print(ANSI_RED + seat.getSeatCode() + ANSI_RESET);
        } else if (seat.getIsSeat() && !seat.getIsBooked()) {  // unbooked seat
            System.out.print(ANSI_GREEN + seat.getSeatCode() + ANSI_RESET);
        } else {  // stairwell or aisle
            for (int i = 0; i < seat.getSeatCode().length(); i++) { System.out.print(" "); }
        }
    }

    // get number of available seats
    public int availSeats() {
        int counter = 0;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j].getIsSeat() && !seats[i][j].getIsBooked()) { counter++; }
            }
        }
        return counter;
    }

    // display seats
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

    // book seats - TODO
    public void bookSeats() {
        char row;
        int col;
        int counter = 0;  // to exit after 3 unsuccessful attempts to get seat
        String trySeatCode;
        displaySeats(); 

        do {
            System.out.print("Enter seat row: ");
            row = Character.toUpperCase(sc.next().charAt(0));  // convert input char to upper case
            System.out.print("Enter seat column: ");
            col = sc.nextInt();
            sc.nextLine();  // consume the new line
            trySeatCode = Character.toString(row) + Integer.toString(col);  // convert to seatCode
            if (!seatExists(trySeatCode)) {  // check if the seat exists
                System.out.println("Invalid entry!");
            } else {
                if (seats[((int)row) - 65][col - 1].getIsBooked()) {  // check if the seat is available
                    System.out.println("The seat is unavailable.");
                } else {
                    seats[((int) row) - 65][col - 1].bookSeat();
                    System.out.println("Seat " + seats[((int) row) - 65][col - 1].getSeatCode() + " successfully booked!");
                    // seatDao.saveSeats(seats, this.showtimeId); TODO
                    break;
                }
            }
            counter++;
        } while (counter < 3);
        if (!(counter < 3)) { System.out.println("System exiting after 3 unsuccessful attempts..."); }
    }
    
    // check if the seatCode corresponds to an actual seat
    public boolean seatExists(String trySeatCode) {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (trySeatCode.equals(seats[i][j].getSeatCode())) {   // object exists
                    if (seats[i][j].getIsSeat()) { return true; }   // is a seat, not stairwell/aisle
                }
            }
        }
        return false;
    }
}