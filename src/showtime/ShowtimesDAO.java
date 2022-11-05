package showtime;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class ShowtimesDAO {
    String FILEPATH = "database/showtimes.csv";

    public void save(ArrayList<Showtime> showtimes) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(FILEPATH, false));
            bw.write("");
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < showtimes.size(); i++) {
            try {
                bw = new BufferedWriter(new FileWriter(FILEPATH, true));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Showtime showtime = showtimes.get(i);
            String writeStr = String.format("%d,%d,%d,%d",
                showtime.getShowtimeId(), showtime.getMovieId(),
                showtime.getCinemaId(), showtime.getDate(),
                showtime.getHeight(), showtime.getWidth(),
                showtime.getSeats()
            );
            try {
                bw.write(writeStr);
                bw.newLine();
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public LinkedList<String> getData() {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(FILEPATH));
            String line;
            LinkedList<String> rtnList = new LinkedList<String>();
            while ((line = br.readLine()) != null) {
                rtnList.add(line);
            }
            br.close();
            return rtnList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Showtime> load() {
        LinkedList<String> instances = this.getData();
        ArrayList<Showtime> returnList = new ArrayList<Showtime>();
        for (int i = 0; i < instances.size(); i++) {
            System.out.println(instances.get(i));
            String singleData = instances.get(i);
            String[] x = singleData.split(",");
            Showtime new_instance = new Showtime(
                Integer.parseInt(x[1]),
                Integer.parseInt(x[2]),
                x[3]
            );
            returnList.add(new_instance);
        }
        return returnList;
    }
}




// package showtime;
// import java.io.*;
// import java.text.DateFormat;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;
// import java.util.Date;
// import java.util.LinkedList;

// import AppController;

// public class ShowtimesDAO {
//     private static DateFormat dateFormat = new SimpleDateFormat("dd/mm/yy");
//     private static DateFormat timeFormat = new SimpleDateFormat("hh:mm");

//     private static DateFormat daoFormat = new SimpleDateFormat("dd/mm/yy,hh:mm");

//     public void saveShowtime(int movieId, Showtime s) {
//         String filename = s.getMovieId() + "_Showtimes.csv";

//         Date sDate = s.getDate();
//         String formatDate = dateFormat.format(sDate);
//         String formatTime = timeFormat.format(sDate);

//         // CSV Data format
//         // showtimeId, movieId, cinemaId, dd/mm/yy, hh:mm
//         String writeStr = String.format("%d,%d,%d,%s,%s", s.getShowtimeId() + "," + s.getMovieId());
//     }

//     public void getShowtimes(int movieId, LinkedList<Showtime> showtimes) {
//         String filename = movieId + "_Showtimes.csv";

//         LinkedList<String> showtimeStr = AppController.dao.readText(filename);
//         String[] values;
//         Showtime s;
//         for (int i=0; i < showtimeStr.size(); i++) {
//             values = showtimeStr.get(i).split(",");
//             Date d = null;
//             try {
//                 d = daoFormat.parse(String.format("%s,%s",values[3], values[4]));
//             } catch (ParseException e) {
//                 throw new RuntimeException(e);
//             }
//             s = new Showtime(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]), d);
//             showtimes.add(s);
//             AppController.showtimeIdMax++; //Add Count to track showtimeId
//         }
//     }
// }
