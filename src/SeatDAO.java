import java.io.*;
import java.util.LinkedList;
import java.util.ArrayList;

public class SeatDAO extends BaseDAO {
    String BASEPATH = "src/database/Cineplex/";
    String FILEPATH;

    // public void save(ArrayList<BaseModel> instances) {
    //     String FILEPATH = BASEPATH;
    //     emptyFile(FILEPATH);
    //     String writeStr = "";
    //     for (int i = 0; i < instances.size(); i++) {
    //         BaseModel instance = instances.get(i);
    //         writeStr = String.format(
    //             "todo", instance
    //         );
    //         writeLine(FILEPATH, writeStr);
    //     }
    // }

    public ArrayList<Seat> load(int height, int width) {
        LinkedList<String> instances = this.getData(FILEPATH);
        ArrayList<Seat> returnList = new ArrayList<Seat>();
        for (int i = 0; i < instances.size(); i++) {
            System.out.println(instances.get(i));
            String seats = instances.get(i).split(",")[3];
            for (int j = 0; i < height; i++) {
                for (int k = 0; j < width; j++) {
                    // TODO
                    // System.out.println(seats.getChars(1));
                    
                }
            }
            // Seat new_instance = Seat(

            // );
            returnList.add(new_instance);
        }
        // return returnList;
    }
}

// public class SeatDAO {
    
//     public void saveSeats(Seat[][] seats, int showtimeId) {
//         String filename = showtimeId + "_Seats.csv";
//         int rows = seats.length;
//         int cols = seats[0].length;
//         String[] formatSeats = new String[rows * cols];
//         int counter = 0;
//         AppController.dao.clearFile(filename);
//         AppController.dao.openFile(filename, true);

//         for (int i = 0; i < rows; i++) {
//             for (int j = 0; j < cols; j++) {
//                 formatSeats[counter] = seats[i][j].getSeatCode() + "," + String.valueOf(seats[i][j].getIsSeat()) + "," + String.valueOf(seats[i][j].isBooked());
//                 counter++;
//             }
//         }
//         AppController.dao.writeText(formatSeats);
//         AppController.dao.closeFile();
//     }

//     public void getSeats(int showtimeId, Seat[][] seats) {
//         String filename = showtimeId + "_Seats.csv";
//         LinkedList<String> seatsStr = AppController.dao.readText(filename);
//         String[] values;
//         char row;
//         int col;
//         Seat s;

//         for (int i = 0; i < seatsStr.size(); i++) {
//             values = seatsStr.get(i).split(",");
//             row = values[0].charAt(0);  // get the row
//             col = Integer.parseInt(String.valueOf(values[0].charAt(1)));  // get the col of the seat
//             s = new Seat(values[0], Boolean.parseBoolean(values[1]), Boolean.parseBoolean(values[2]));
//             seats[(int)row - 65][col - 1] = s;
//         }
//     }

// }