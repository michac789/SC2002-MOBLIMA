import java.io.*;
import java.util.*;

public class SeatDAO {
    
    public void saveSeats(Seat[][] seats, int showtimeId) {
        String filename = showtimeId + "_Seats.csv";
        int rows = seats.length;
        int cols = seats[0].length;
        String[] formatSeats = new String[rows * cols];
        int counter = 0;
        AppController.dao.openFile(filename, true);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                formatSeats[counter] = seats[i][j].getSeatCode() + "," + String.valueOf(seats[i][j].isBooked());
                counter++;
            }
        }
        AppController.dao.writeText(formatSeats);
        AppController.dao.closeFile();
    }

    public void getSeats(int showtimeId, Seat[][] seats) {
        String filename = showtimeId + "_Seats.csv";
        LinkedList<String> seatsStr = AppController.dao.readText(filename);
        String[] values;
        char row;
        int col;
        Seat s;

        for (int i = 0; i < seatsStr.size(); i++) {
            values = seatsStr.get(i).split(",");
            row = values[0].charAt(0);  // get the row of the seat
            col = Integer.parseInt(String.valueOf(values[0].charAt(1)));  // get the col of the seat as an int
            s = new Seat(values[0], Boolean.parseBoolean(values[1]));
            seats[(int)row - 65][col - 1] = s;
        }
    }

}
