package DAO;
import java.util.ArrayList;
import java.util.LinkedList;
import model.Movie;

public class MovieDAO extends BaseDAO {
    private static String FILEPATH = "src/database/Movie/Movies.csv";

    public void save(ArrayList<Movie> instances) {
        emptyFile(FILEPATH);
        String header = "movieId,title,duration,director,cast,showStatus,ageRating,is3D,isBlockbuster,sales";
        writeLine(FILEPATH, header);
        String writeStr = "";
        for (int i = 0; i < instances.size(); i++) {
            Movie instance = instances.get(i);
            writeStr = String.format("%d,%s,%d,%s,%s,%s,%s,%s,%s,%d",
                instance.getMovieId(), instance.getTitle(),
                instance.getDurationMinutes(), instance.getDirector(),
                instance.getCast(), instance.getShowStatus(), instance.getAgeRating(),
                (instance.is3D() ? 1 : 0), (instance.isBlockbuster() ? 1 : 0),
                instance.getSalesCount()
            );
            writeLine(FILEPATH, writeStr);
            instance.getController().save();
        }
    }

    public ArrayList<Movie> load() {
        LinkedList<String> instances = getData(FILEPATH);
        ArrayList<Movie> returnList = new ArrayList<Movie>();
        for (int i = 1; i < instances.size(); i++) {
            System.out.println(instances.get(i));
            String[] x = instances.get(i).split(",");
            Movie newInstance = new Movie(
                x[1], Integer.parseInt(x[2]), x[3], x[4],
                Movie.showStatusOptions.valueOf(x[5]),
                Movie.ageRatingOptions.valueOf(x[6]),
                Boolean.valueOf(x[7]), Boolean.valueOf(x[8]),
                Integer.parseInt(x[9])
            );
            returnList.add(newInstance);
        }
        return returnList;
    }
}