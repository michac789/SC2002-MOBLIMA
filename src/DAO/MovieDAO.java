package DAO;
import java.util.ArrayList;
import java.util.LinkedList;
import model.Movie;

/**
 * Represents data access object related ot movie
 * @version 1.0
 * @since 2022-11-13
 */
public class MovieDAO extends BaseDAO {
    /**
     * File path for movie database for readability
     */
    private static String FILEPATH = "src/database/Movie/Movies.csv";

    /**
     * Saves updated movie state of the application to the database
     * @param instances The array of Movie object that represents one movie
     */
    public void save(ArrayList<Movie> instances) {
        emptyFile(FILEPATH);
        String header = "movieId,title,duration,synopsis,director,cast,showStatus,ageRating,is3D,isBlockbuster,sales";
        writeLine(FILEPATH, header);
        String writeStr = "";
        for (int i = 0; i < instances.size(); i++) {
            Movie instance = instances.get(i);
            writeStr = String.format("%d,%s,%d,%s,%s,%s,%s,%s,%s,%s,%d",
                instance.getMovieId(), instance.getTitle(),
                instance.getDurationMinutes(),
                instance.getSynopsis().replace(',','\\'),
                instance.getDirector().replace(',','\\'),
                instance.getCast().replace(',','\\'),
                instance.getShowStatus(), instance.getAgeRating(),
                (instance.is3D() ? 1 : 0), (instance.isBlockbuster() ? 1 : 0),
                instance.getSalesCount()
            );
            writeLine(FILEPATH, writeStr);
            instance.getController().save();
        }
    }

    /**
     * Loads movies and put it in array of Movie object
     * @return ArrayList of movie object fetched from database
     */
    public ArrayList<Movie> load() {
        LinkedList<String> instances = getData(FILEPATH);
        ArrayList<Movie> returnList = new ArrayList<Movie>();
        for (int i = 1; i < instances.size(); i++) {
            String[] x = instances.get(i).split(",");
            Movie newInstance = new Movie(
                x[1], Integer.parseInt(x[2]),
                x[3].replace('\\', ','),
                x[4].replace('\\', ','),
                x[5].replace('\\', ','),
                Movie.showStatusOptions.valueOf(x[6]),
                Movie.ageRatingOptions.valueOf(x[7]),
                Boolean.valueOf(x[8]), Boolean.valueOf(x[9]),
                Integer.parseInt(x[10])
            );
            returnList.add(newInstance);
        }
        return returnList;
    }

    public void createMovieReviewFile(int movieId) {
        String BASEPATH = "src/database/Movie/Review/";
        UtilDAO.createFile(BASEPATH + movieId + ".csv");
    }
}