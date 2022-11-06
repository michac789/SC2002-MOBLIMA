import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class ShowtimeDAO {
    private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static DateFormat timeFormat = new SimpleDateFormat("HH:mm");

    private static DateFormat daoFormat = new SimpleDateFormat("dd/MM/yy,HH:mm");

    public void saveShowtime(int cinemaId, Showtime s) {
        String filename = "cinema" + cinemaId + "_Showtimes.csv";

        Date sDate = s.getDate();
        String formatDate = dateFormat.format(sDate);
        String formatTime = timeFormat.format(sDate);

        // CSV Data format
        // showtimeId, movieId, dd/mm/yy, HH:mm
        String writeStr = String.format("%d,%d,%s,%s", s.getShowtimeId(), s.getMovieId(), formatDate, formatTime);

        AppController.dao.openFile(filename, true);
        AppController.dao.writeText(writeStr);
        AppController.dao.closeFile();
    }

    public void saveShowtime(int cinemaId, LinkedList<Showtime> showtimes) {
        String filename = "cinema" + cinemaId + "_Showtimes.csv";

        AppController.dao.clearFile(filename);
        AppController.dao.openFile(filename, true);
        Date sDate;
        String formatDate, formatTime;
        for (Showtime s: showtimes) {
            sDate = s.getDate();
            formatDate = dateFormat.format(sDate);
            formatTime = timeFormat.format(sDate);

            // showtimeId, movieId, dd/mm/yy, HH:mm
            String writeStr = String.format("%d,%d,%s,%s", s.getShowtimeId(), s.getMovieId(), formatDate, formatTime);
            AppController.dao.writeText(writeStr);
        }
        AppController.dao.closeFile();
    }
    public void getShowtimes(int cinemaId, LinkedList<Showtime> showtimes) {
        String filename = "cinema" + cinemaId + "_Showtimes.csv";

        LinkedList<String> showtimeStr = AppController.dao.readText(filename);
        if (showtimeStr == null || showtimeStr.size() == 0) {return;};
        String[] values;
        Showtime s;
        for (int i=0; i < showtimeStr.size(); i++) {
            values = showtimeStr.get(i).split(",");
            Date d = null;
            try {
                d = daoFormat.parse(String.format("%s,%s",values[2], values[3]));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            s = new Showtime(Integer.parseInt(values[0]), Integer.parseInt(values[1]), d);
            showtimes.add(s);
            Showtime.numOfShowtime++; //Add Count to track showtimeId
        }
    }
}
