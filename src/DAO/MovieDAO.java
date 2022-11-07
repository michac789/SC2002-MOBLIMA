package DAO;
import java.util.ArrayList;
import java.util.LinkedList;
import model.Movie;

public class MovieDAO extends BaseDAO {
    String FILEPATH = "src/database/Movie/Movies.csv";

    public void save(ArrayList<Movie> instances) {
        emptyFile(FILEPATH);
        String writeStr = "";
        for (int i = 0; i < instances.size(); i++) {
            Movie instance = instances.get(i);
            writeStr = String.format("%d,%s,%d,%s,%s,%s,%s,%s,%s",
                instance.getMovieId(), instance.getTitle(),
                instance.getDurationMinutes(), instance.getDirector(),
                instance.getCast(), instance.getShowStatus(), instance.getAgeRating(),
                (instance.is3D() ? 1 : 0), (instance.isBlockbuster() ? 1 : 0)
            );
            writeLine(FILEPATH, writeStr);
        }
    }

    public ArrayList<Movie> load() {
        LinkedList<String> instances = getData(FILEPATH);
        ArrayList<Movie> returnList = new ArrayList<Movie>();
        for (int i = 0; i < instances.size(); i++) {
            System.out.println(instances.get(i));
            String[] x = instances.get(i).split(",");
            Movie new_instance = new Movie(
                x[1], Integer.parseInt(x[2]), x[3], x[4],
                Movie.showStatusOptions.valueOf(x[5]),
                Movie.ageRatingOptions.valueOf(x[6]),
                Boolean.valueOf(x[7]), Boolean.valueOf(x[8])
            );
            returnList.add(new_instance);
        }
        return returnList;
    }
}