import java.util.LinkedList;

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

    public Seat[][] load(int height, int width, int cineplexId, int cinemaId) {
        FILEPATH = BASEPATH + cineplexId + "/Showtime_" + cinemaId + ".csv";
        LinkedList<String> instances = this.getData(FILEPATH);
        Seat[][] returnSeats = new Seat[height][width];
        for (int i = 0; i < instances.size(); i++) {
            System.out.println(instances.get(i));
            String seats = instances.get(i).split(",")[3];
            int h = 0, w = 0;
            for (int j = 0; j < seats.length(); j++) {
                h = (h + 1) % height;
                w = (w + 1) % width;
                returnSeats[h][w] = new Seat(
                    "someSeatCodeTODO",
                    (seats.charAt(i) == 'F' ? true : false),
                    (seats.charAt(i) == 'X' ? false : true)
                );
            }
        }
        return returnSeats;
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