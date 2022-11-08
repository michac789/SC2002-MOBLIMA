package DAO;
import java.util.LinkedList;
import model.Seat;

public class SeatDAO extends BaseDAO {
    String BASEPATH = "database/Cineplex/";
    String FILEPATH;

    public Seat[][] load(int height, int width, int cineplexId, int cinemaId) {
        FILEPATH = BASEPATH + cineplexId + "/Showtime_" + cinemaId + ".csv";
        LinkedList<String> instances = this.getData(FILEPATH);
        Seat[][] returnSeats = new Seat[height][width];
        for (int i = 0; i < instances.size(); i++) {
            System.out.println(instances.get(i));
            String seats = instances.get(i).split(",")[3];
            int h = 0, w = -1;
            for (int j = 0; j < seats.length(); j++) {
                w = w + 1;
                if (w >= width) {
                    h = h + 1;
                    w = w % width;
                }
                returnSeats[h][w] = new Seat(
                    "someSeatCodeTODO",
                    (seats.charAt(j) == 'F' ? true : false),
                    (seats.charAt(j) == 'X' ? false : true)
                );
            }
        }
        return returnSeats;
    }
}