package DAO;
import java.util.ArrayList;
import java.util.LinkedList;
import model.Review;

public class ReviewDAO extends BaseDAO {
    String BASEPATH = "src/database/Movie/Review/";

    public void save(ArrayList<Review> instances, int movieId) {
        String FILEPATH = BASEPATH + movieId + ".csv";
        emptyFile(FILEPATH);
        String writeStr = "";
        for (int i = 0; i < instances.size(); i++) {
            Review instance = instances.get(i);
            writeStr = String.format(
                "%d,%d,\"%s\"",
                instance.getUserId(), instance.getRating(),
                instance.getComment().replace(",", "\\comma")
            );
            writeLine(FILEPATH, writeStr);
        }
    }

    public ArrayList<Review> load(int movieId) {
        String FILEPATH = BASEPATH + movieId + ".csv";
        LinkedList<String> instances = this.getData(FILEPATH);
        ArrayList<Review> returnList = new ArrayList<Review>();
        for (int i = 0; i < instances.size(); i++) {
            System.out.println(instances.get(i));
            String[] x = instances.get(i).split(",");
            x[2] = x[2].substring(1, x[2].length() - 1)
                .replace("\\comma", ",");
            Review newInstance = new Review(
                Integer.parseInt(x[0]), Integer.parseInt(x[1]), x[2]
            );
            returnList.add(newInstance);
        }
        return returnList;
    }
}