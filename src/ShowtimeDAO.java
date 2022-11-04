import java.io.*;
import java.util.LinkedList;

public class ShowtimeDAO {
    public void saveShowtime(Showtime s) {
        String filename = s.getMovieId() + "_Reviews.csv";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
            String line = s.savetoCSV();
            bw.write(line);
            bw.close();
        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void getShowtimes(int movieId, LinkedList<Showtime> showtimes) {
        String filename = movieId + "_Reviews.csv";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            String[] values;
            Review r;
            while ((line = br.readLine()) != null) {
                values = line.split(",");
                values[2] = values[2].substring(1, values[2].length()-1); // Remove first and last char, which are "" added for CSV purposes
                values[2] = values[2].replace("\\comma", ",");
//                r = new Showtime(Integer.parseInt(values[0]), Integer.parseInt(values[1]), values[2]);
//                showtimes.add(r);
            }
            br.close();
        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
