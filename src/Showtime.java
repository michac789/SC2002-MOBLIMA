/*
    'Showtime' class

    - Contain the date & time, movie ID, 2D array of seat objects
    - Methods to book seat(s) in a showtime
*/

import java.util.ArrayList;

public class Showtime {
    private String datetime; // TODO - change to datetime
    private int movieId;
    private Seat[][] seats;
    
    // 2d array for seats
    // A1 A2 A3 A4
    // B1 B2 B3 B4
    // C1 C2 C3 C4

    public Showtime(String datetime, int movieId, int h, int w) {
        this.datetime = datetime;
        this.movieId = movieId;
        this.seats = new Seat[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                this.seats[i][j] = new Seat(
                    Character.toString((char)(65 + i)) + (j + 1)
                );
            }
        }
    }

    // TODO - accessors & mutators

    // method to book the seats 
    // Elizabeth: i think App.java should call showSeats()
    // then App.java should get and validate the user input for seats
    // then bookSeats() should book the chosen seat (done) and append it to moviegoer (not done)
    // i changed the bookSeats() parameters from (User user, ArrayList seatCodes) and return from boolean to void
    public void bookSeats(MovieGoer mg, String s) {  
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j].getSeatCode() == s) {
                    seats[i][j].bookSeat();
                }
            }
        }
    }

    // method to show all the seats (2D)
    public void showSeats() {
        int aisle = seats[0].length/2;
        for (int i = 0; i < seats.length; i++) { 
            if (i < 4) { System.out.print("    "); }  // main stairway
            for (int j = 0; j < seats[0].length; j++) {
                if (j == aisle) { System.out.print("  "); }  // aisle
                seats[i][j].printSeat();
                System.out.println(" ");
            }
            System.out.println();
        }
    }
}
