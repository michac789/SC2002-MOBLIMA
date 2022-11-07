import java.util.ArrayList;
import java.util.Scanner;

public class MovieUI {
    private static Scanner sc = new Scanner(System.in);
    private static MovieController mc = new MovieController();

    public static void searchMovies () {
        System.out.print("Enter movie title:");
        String searchQuery = sc.nextLine();
        int movieId = mc.searchMovie(searchQuery);
    }

    public static void displayAllMovies () {
        System.out.println("Displaying all movies...");
        // TODO
        ArrayList<Movie> movies = mc.getAllMovies();
        for(int i = 0; i<movies.size(); i++){
            Movie movie = movies.get(i);
            System.out.println("ID: " + movie.getMovieId() + " Title: " + movie.getTitle());
        }
    }







}
