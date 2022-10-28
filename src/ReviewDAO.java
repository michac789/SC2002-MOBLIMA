import java.io.*;
import java.util.LinkedList;

public class ReviewDAO {
    public void saveReview(Review r, int movieId) {
        String filename = movieId + "_Reviews.csv";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
            String line = r.savetoCSV();
            bw.write(line);
            bw.close();
        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void getReviews(int movieId, LinkedList<Review> reviews) {
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
                r = new Review(Integer.parseInt(values[0]), Integer.parseInt(values[1]), values[2]);
                reviews.add(r);
            }
            br.close();
        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
