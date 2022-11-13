package controller;
import java.util.*;
import java.util.ArrayList;
import DAO.MovieDAO;
import model.Movie;

public class MovieController {
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private MovieDAO movieDAO = new MovieDAO();

    /*
     * Load necessary information regarding movies from database
     * Trigger the loading of all reviews on each movie
     * Called at the start of the program
     */
    public MovieController() {
        this.movies = this.movieDAO.load();
    }

    /*
     * Save all local changes regarding movies to the database
     * Call the 'save' method for review controllers on all movies
     * Called when terminating the program
     */
    public void save() {
        this.movieDAO.save(movies);
    }

    /*
     * Return an arraylist of all movies
     * Used in admin functionality
     */
    public ArrayList<Movie> getAllMovies() {
        return movies;
    }

    /*
     * Return an arraylist of all movies,
     * except movies that has status of 'END_OF_SHOWING'
     * Used in movie goer functionality
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

    /*
     * Return a movie object given its id
     */
    public Movie getMovieById(int id) {
        for (Movie m: this.movies) {
            if (m.getMovieId() == id) {
                return m;
            }
        }
        return null;
    }

    public Movie getMovieByTitle(String title) {
        for (Movie m: this.movies) {
            if (m.getTitle().equals(title)) {
                return m;
            }
        }
        return null;
    }
    
    /*
     * Create new movie given information of the movie
     * Perform necessary action on database
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

    /*
     * Get List of movies sorted by Sales
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

    /*
     * Display the first 'num' movie that has the highest rating,
     * given that there are at least 2 reviews
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

    /*
     * Returns an arraylist of movies where 'str' is a substring in its title
     * Upper case, lower case, blank space are ignored
     * Used for search feature
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