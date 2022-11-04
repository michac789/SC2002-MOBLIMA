package movie;
import java.util.*;

public class MoviesController {
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private MoviesDAO moviesDao = new MoviesDAO();

    public MoviesController() {
        movies = this.moviesDao.loadMovies();
    }
    
    public void save() {
        this.moviesDao.saveMovies(movies);
    }

    // add new movie to list of movies
    public void addMovie(
        String title, int durationMinutes, String director, String cast,
        Movie.showStatusOptions showStatus, Movie.ageRatingOptions ageRating,
        boolean is3D, boolean isBlockbuster
    ) {
        Movie newMovie = new Movie(
            "movie2", 100,
            "dir2", "cast2",
            Movie.showStatusOptions.NOW_SHOWING,
            Movie.ageRatingOptions.G,
            false, false
        );
        movies.add(newMovie);
    }

    // display a list of all movies (id & title only)
    public void displayAllMovies() {
        System.out.println("Showing all " + Movie.numMovies + " movies:");
        for (int i = 0; i < this.movies.size(); i++) {
            this.movies.get(i).printMovieInfo();
        }
        System.out.println("");
    }

    // TODO
    // display all movies that has 'title' as a substring
    // or can improve with other better search algorithm
    public void searchMovie(String title) {
        System.out.println("TODO");
    }

    // TODO
    // display the first 'num' movies with highest ticket sales
    public void rankMovieBySales(int num) {
        System.out.println("TODO");
    }

    // TODO
    // display the first 'num' movies with highest rating
    public void rankMovieByRating(int num) {
        System.out.println("TODO");
    }
}
