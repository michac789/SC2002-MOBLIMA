import java.io.*;
import java.util.LinkedList;

public class ReviewDAO {
    public void saveReview(Review r, int movieId) {
        String filename = movieId + "_Reviews.csv";

        String formatComment = r.getComment();
        formatComment = formatComment.replace(",", "\\comma");

        String writeStr = String.format("%d,%d,\"%s\"", r.getUserId(), r.getRating(), formatComment);

        AppController.dao.openFile(filename, true);
        AppController.dao.writeText(writeStr);
        AppController.dao.closeFile();
    }
    public void getReviews(int movieId, LinkedList<Review> reviews) {
        String filename = movieId + "_Reviews.csv";

        LinkedList<String> reviewStr = AppController.dao.readText(filename);

        String[] values;
        if (reviewStr == null) {return;}
        Review r;
        for (int i=0; i < reviewStr.size(); i++) {
            values = reviewStr.get(i).split(",");
            values[2] = values[2].substring(1, values[2].length()-1); // Remove first and last char, which are "" added for CSV purposes
            values[2] = values[2].replace("\\comma", ",");
            r = new Review(Integer.parseInt(values[0]), Integer.parseInt(values[1]), values[2]);
            reviews.add(r);
        }
    }
}
