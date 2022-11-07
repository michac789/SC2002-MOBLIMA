import java.util.*;
import user.MovieGoer;

public class MovieGoerController {
    private ArrayList<MovieGoer> movieGoers = new ArrayList<MovieGoer>();
    private MovieGoerDAO movieGoerDao = new MovieGoerDAO();

    public MovieGoerController() {
        movieGoers = this.movieGoerDao.load();
    }

    public void save() {
        this.movieGoerDao.save(movieGoers);
    }
}