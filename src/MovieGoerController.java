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
    public boolean isMoviegoerExist(String username) {
        for(int i = 0; i<movieGoers.size(); i++){
            if (movieGoers.get(i).getUsername() == username){
                return true;
            }
        }

        return false;
    }

}