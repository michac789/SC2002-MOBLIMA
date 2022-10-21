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

    // method to book the seats ??
    // public boolean bookSeats(User user, ArrayList seatsCodes) {
    //     TODO
    // }

    // method to show all the seats (2D)
    // public void showSeats() {
    //     TODO
    // }
}
