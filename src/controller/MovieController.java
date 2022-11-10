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
            if (m.getMovieId() == m.getMovieId()) {
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
            showStatus, ageRating, is3D, isBlockbuster);
        movies.add(m);
    }

    public String removeMovie(int movieSelected) { // TODO - still buggy for ID count now
        String title = this.movies.get(movieSelected).getTitle();
        movies.remove(movieSelected);
        return title;
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
        System.out.println("TODO");
        for(Movie m: this.movies){

        }
    }

    public void rankMovieByRating(int num) {
        if (movies.size() == 0) {
            System.out.println("No Movies");
            return;
        }
        LinkedList<Movie> sortedRating = new LinkedList<Movie>();

        sortedRating.add(movies.get(0));
        for (int i=1; i < movies.size();i++) {
            for (int j=i-1; j >= 0; j--) {
                if (movies.get(i).getRating() > sortedRating.get(j).getRating()) { // TODO
                    if (j==0) {
                        sortedRating.add(j, movies.get(i));
                        break;
                    }
                    continue;
                } else {
                    sortedRating.add(j+1, movies.get(i));
                }
            }
        }

        int i=1;
        int maxListings = num;
        for (int j=0; j < maxListings; j++) {
            Movie m = sortedRating.get(j);
            if (m.getRating() != -1) {
                System.out.printf("%d: %-15s| Rating: %.1f\n", i, m.getTitle(), m.getRating());
            }else {
                System.out.printf("%d: %-15s| Rating: %s\n", i, m.getTitle(), "No Ratings Yet.");
            }
            i++;
        }
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