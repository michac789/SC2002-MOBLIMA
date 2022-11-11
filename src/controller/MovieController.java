package controller;
import java.util.*;
import DAO.MovieDAO;
import DAO.UtilDAO;
import model.Movie;

public class MovieController {
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private MovieDAO movieDAO = new MovieDAO();
    private Scanner sc;

    private int[] movieIdList; //??

    public MovieController() {
        this.movies = this.movieDAO.load();
        this.sc = new Scanner(System.in);
    }

    public void save() {
        this.movieDAO.save(movies);
    }

    public ArrayList<Movie> getAllMovies() {
        return movies;
    }

    public Movie getMovieById(int id) {
        for (Movie m: this.movies) {
            if (m.getMovieId() == id) {
                return m;
            }
        }
        return null;
    }
    
    public void createMovie(
            String title, int durationMinutes, String director, String cast,
            Movie.showStatusOptions showStatus, Movie.ageRatingOptions ageRating,
            boolean is3D, boolean isBlockbuster) {
        String BASEPATH = "src/database/Movie/Review/";
        int newMovieId = movies.size() + 1;
        UtilDAO.createFile(BASEPATH + newMovieId + ".csv");
        Movie m = new Movie(title, durationMinutes, director, cast,
            showStatus, ageRating, is3D, isBlockbuster, 0);
        movies.add(m);
    }

    public int displayShowingMovies() {
        int i = 0;
        int[] movieIdList = new int[movies.size()];
        for (int j = 0; j < movies.size(); j++) {
            if (movies.get(j).getShowStatus() == Movie.showStatusOptions.NOW_SHOWING) {
                System.out.printf("%d: %s\n", i, movies.get(j).getTitle());
                movieIdList[i] = j;
                i++;
            }
        }
        this.movieIdList = movieIdList;

        return i;
    }

    public void rankMovieBySales(int num) {
        ArrayList<Movie> sortedMovies = getAllMovies();
        Collections.sort(sortedMovies, new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return - m1.getSalesCount() + m2.getSalesCount();
            }
        });
        for (int i = 0; i < num; i++) {
            Movie movie = sortedMovies.get(i);
            System.out.println(String.format(
                "Movie ID %d: %s (Total sales: %d)",
                movie.getMovieId(), movie.getTitle(), movie.getSalesCount()
            ));
        }
        System.out.println("");
    }

    public void rankMovieByRating(int num) {
        ArrayList<Movie> sortedMovies = getAllMovies();
        Collections.sort(sortedMovies, new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                double delta = m2.getRating() - m1.getRating();
                if (delta > 0.000001) { return 1;} // m2 bigger
                else if (delta < 0.000001) { return -1;} // m1 bigger
                else { return 0;} //equal
            }
        });

        for (int i = 0; i < num; i++) {
            Movie movie = sortedMovies.get(i);
            System.out.println(String.format(
                "Movie ID %d: %s (Average Rating: %.1f, by %d users)",
                movie.getMovieId(), movie.getTitle(),
                movie.getRating(), movie.getController().getNumReviews()
            ));
        }
        System.out.println("");
    }

    public int searchMovie(String title) {
        int i = 0;
        int[] movieIdList = new int[movies.size()];
        for (int j=0; j < movies.size();j++) {
            if (movies.get(j).getTitle().contains(title)) {
                System.out.printf("%d: %s\n", i, movies.get(j).getTitle());
                movieIdList[i] = j;
                i++;
            }
        }
        if (i == 0) {
            System.out.println("No movies with \"" + title + "\" found.");
            return -1;
        }
        int option;
        while (true) {
            System.out.print("Select a movie: ");
            option = sc.nextInt();
            if (!(option < 0 || option >= i)) {
                break;
            }
            System.out.println("Invalid Option.");
        }

        // Navigate to movie
        return movieIdList[option]; // Return Movie Id
    }

    public void displayReviews(int movieId) {
        for (Movie m: this.movies) {
            if (m.getMovieId() == movieId) {
                m.getController().displayReviews();
                break;
            }
        }
    }
}