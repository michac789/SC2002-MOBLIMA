import java.util.LinkedList;
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
        String writeStr = "";
        for (int i = 0; i < instances.size(); i++) {
            Showtime instance = instances.get(i);
            Date sDate = instance.getDate();
            String formatDate = dateFormat.format(sDate);
            String formatTime = timeFormat.format(sDate);
            writeStr = String.format("%d,%s,%s",
                instance.getMovieId(), formatDate, formatTime
            );
            writeLine(FILEPATH, writeStr);
        }
    }
    
    public ArrayList<Showtime> load(int cineplexId, int cinemaId, int height, int width) {
        FILEPATH = BASEPATH + cineplexId + "/Showtime_" + cinemaId + ".csv";
        LinkedList<String> instances = this.getData(FILEPATH);
        ArrayList<Showtime> returnList = new ArrayList<Showtime>();
        for (int i = 0; i < instances.size(); i++) {
            System.out.println(instances.get(i));
            String[] x = instances.get(i).split(",");
            Date date = null;
            try {
                date = daoFormat.parse(String.format("%s,%s", x[1], x[2]));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            Showtime new_instance = new Showtime(
                Integer.parseInt(x[0]), date, height, width,
                cineplexId, cinemaId
            );
            returnList.add(new_instance);
        }
        return returnList;
    }
}