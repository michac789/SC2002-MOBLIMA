package controller;
import model.Seat;

public class SeatController {
    private int height;
    private int width;
    private Seat[][] seats;

    private static final String ANSI_RED = "\u001b[31m";  // red = seat taken
    private static final String ANSI_GREEN = "\u001b[32m";  // green = seat available
    private static final String ANSI_RESET = "\u001B[0m";  // original command line text colour

    public SeatController(int height, int width, int cineplexId, int cinemaId, Seat[][] seats) {
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

    public void bookSeats(String startCode, int seatCount) {
        char row = startCode.charAt(0);
        int col = Integer.parseInt(startCode.substring(1));
        for (int i = 0; i < seatCount; i++) {
            this.seats[((int)row) - 65][col - 1 + i].bookSeat();
        }
    }

    // check if the seatCode corresponds to an actual seat and not booked yet
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