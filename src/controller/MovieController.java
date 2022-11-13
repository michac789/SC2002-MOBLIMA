package controller;
import java.util.*;
import java.util.ArrayList;
import DAO.MovieDAO;
import model.Movie;

/**
 * Controller for all Movie objects
 * @version 1.0
 * @since 2022-11-13
 */
public class MovieController {
    /**
     * Contain an arraylist of all movies
     */
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    /**
     * MovieDAO to get and save data to database
     */
    private MovieDAO movieDAO = new MovieDAO();

    /**
     * Load necessary information regarding movies from database
     * Trigger the loading of all reviews on each movie
     * Called at the start of the program
     */
    public MovieController() {
        this.movies = this.movieDAO.load();
    }

    /**
     * Save all local changes regarding movies to the database
     * Call the 'save' method for review controllers on all movies
     * Called when terminating the program
     */
    public void save() {
        this.movieDAO.save(movies);
    }

    /**
     * Used in admin functionality to get all movies
     * @return Arraylist of all movies
     */
    public ArrayList<Movie> getAllMovies() {
        return movies;
    }

    /**
     * Return an arraylist of all movies,
     * except movies that has status of 'END_OF_SHOWING',
     * used in movie goer functionality
     * @return Arraylis of movies
     */
    public ArrayList<Movie> getShowingMovies() {
        int nowShowingIndex = 0;
        int previewIndex = 0;
        ArrayList<Movie> showingMovies = new ArrayList<Movie>();
        Movie m;
        for (int i=0; i < movies.size(); i++) {
            m = movies.get(i);
            if (m.getShowStatus() == Movie.showStatusOptions.NOW_SHOWING) {
                showingMovies.add(nowShowingIndex, m);
                nowShowingIndex++;
                previewIndex++;
            } else if (m.getShowStatus() == Movie.showStatusOptions.PREVIEW) {
                showingMovies.add(previewIndex, m);
                previewIndex++;
            } else if (m.getShowStatus() == Movie.showStatusOptions.COMING_SOON) {
                showingMovies.add(m); // Add to last index
            }
        }
        return showingMovies;
    }

    /**
     * Get a movie object by its ID
     * @param id MovieID to be retrieved
     * @return Movie object that has that id, or null if not found
     */
    public Movie getMovieById(int id) {
        for (Movie m: this.movies) {
            if (m.getMovieId() == id) {
                return m;
            }
        }
        return null;
    }

    /**
     * Get a movie given its title, assuming all movie titles are unique
     * @param title Title of movie to be retrieved
     * @return Movie object that has that title, or null if not found
     */
    public Movie getMovieByTitle(String title) {
        for (Movie m: this.movies) {
            if (m.getTitle().equals(title)) {
                return m;
            }
        }
        return null;
    }
    
    /**
     * Create new movie given information of the movie,
     * perform necessary action on database
     * @param title Title of the new movie
     * @param durationMinutes Duration (in minutes) of the new movie
     * @param synopsis Synopsis of the new movie
     * @param director Director of the new movie
     * @param cast Cast of the new movie, should be at least 2 casts, seperated by comma
     * @param showStatus Show status of the movie based on showStatusOptions enum
     * @param ageRating Age rating of the movie based on ageRatingOptions enum
     * @param is3D Boolean value whether the movie is shown in 3D or not
     * @param isBlockbuster Boolean value whether the movie is a blockbuster or not
     */
    public void createMovie(
            String title, int durationMinutes, String synopsis, String director, String cast,
            Movie.showStatusOptions showStatus, Movie.ageRatingOptions ageRating,
            boolean is3D, boolean isBlockbuster) {
        int newMovieId = movies.size() + 1;
        movieDAO.createMovieReviewFile(newMovieId);

        Movie m = new Movie(title, durationMinutes, synopsis, director, cast,
            showStatus, ageRating, is3D, isBlockbuster, 0);
        movies.add(m);
    }

    /**
     * Get List of movies sorted by Sales,
     * do not modify the original movies array
     * @return Arraylist deep copy containing movies sorted by sale
     */
    public ArrayList<Movie> getMoviesBySales() {
        @SuppressWarnings("unchecked")
        ArrayList<Movie> sortedMovies = (ArrayList<Movie>) getAllMovies().clone();
        Collections.sort(sortedMovies, new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return - m1.getSalesCount() + m2.getSalesCount();
            }
        });
        return sortedMovies;
    }

    /**
     * Get List of movies sorted by Average Rating,
     * do not modify the original movies array
     * @return Arraylist deep copy containing movies sorted by average rating
     */
    public ArrayList<Movie> getMoviesByRating() {
        @SuppressWarnings("unchecked")
        ArrayList<Movie> sortedMovies = (ArrayList<Movie>) getAllMovies().clone();
        Collections.sort(sortedMovies, new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                double delta = m2.getRating() - m1.getRating();
                if (delta > 0.000001) { return 1;} // m2 bigger
                else if (delta < 0.000001) { return -1;} // m1 bigger
                else { return 0;} //equal
            }
        });
        return sortedMovies;
    }

    /**
     * Returns an arraylist of movies where 'str' is a substring in its title,
     * Upper case, lower case, blank space are ignored
     * @param str Query string for search input
     * @return Arraylist of movies corresponding to search results
     */
    public ArrayList<Movie> searchMovie(String str) {
        ArrayList<Movie> searchResults = new ArrayList<Movie>();
        for (int i = 0; i < this.movies.size(); i++) {
            if (this.movies.get(i).getTitle().toLowerCase()
                    .contains(str.toLowerCase().trim())) {
                searchResults.add(this.movies.get(i));
            }
        }
        return searchResults;
    }
}