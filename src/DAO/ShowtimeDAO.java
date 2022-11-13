package DAO;
import java.util.LinkedList;
import model.Showtime;
import model.Seat;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class ShowtimeDAO extends BaseDAO {
    String BASEPATH = "src/database/Cineplex/";
    String FILEPATH;

    private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private static DateFormat daoFormat = new SimpleDateFormat("dd/MM/yy,HH:mm");

    public void save(ArrayList<Showtime> instances, int cineplexId, int cinemaId) {
        FILEPATH = BASEPATH + cineplexId + "/Showtime_" + cinemaId + ".csv";
        emptyFile(FILEPATH);
        String header = "movieId,date,time,seats";
        writeLine(FILEPATH, header);
        String writeStr = "";
        for (int i = 0; i < instances.size(); i++) {
            Showtime instance = instances.get(i);
            Date sDate = instance.getDate();
            String formatDate = dateFormat.format(sDate);
            String formatTime = timeFormat.format(sDate);
            String seats = instance.getController().serializeSeats();
            writeStr = String.format("%d,%s,%s,%s",
                instance.getMovieId(), formatDate, formatTime, seats
            );
            writeLine(FILEPATH, writeStr);
        }
    }
    
    public ArrayList<Showtime> load(int cineplexId, int cinemaId, int height, int width) {
        FILEPATH = BASEPATH + cineplexId + "/Showtime_" + cinemaId + ".csv";
        LinkedList<String> instances = this.getData(FILEPATH);
        ArrayList<Showtime> returnList = new ArrayList<Showtime>();
        for (int i = 1; i < instances.size(); i++) {
            Seat[][] returnSeats = new Seat[height][width];
            String[] s = instances.get(i).split(",");
            Date date = null;
            try {
                date = daoFormat.parse(String.format("%s,%s", s[1], s[2]));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            String seats = instances.get(i).split(",")[3];
            int h = 0, w = -1;
            int x = 0, y = 65;
            for (int j = 0; j < seats.length(); j++) {
                w = w + 1;
                x = x + 1;
                if (w >= width) {
                    h = h + 1;
                    w = w % width;
                    x = 1;
                    y = y + 1;
                }
                char yChar = (char) y;
                returnSeats[h][w] = new Seat(
                    yChar + "" + x,
                    (seats.charAt(j) == 'F' ? true : false),
                    (seats.charAt(j) == 'X' ? false : true)
                );
            }
            Showtime newShowtime = new Showtime(
                Integer.parseInt(s[0]), date, height, width,
                cineplexId, cinemaId, returnSeats
            );
            returnList.add(newShowtime);
        }
        return returnList;
    }
}