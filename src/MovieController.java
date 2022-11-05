/*
    'MoviesController' class

    - Stores a list of all movies
    - Contain utility functions to perform tasks related to
    searching, querying, etc., from a list of all movies
*/

import java.util.*;

public class MovieController {
    private LinkedList<Movie> movies = new LinkedList<Movie>();

    private Scanner sc;
    private static MovieDAO movieDAO = new MovieDAO();
    public MovieController() {
        sc = new Scanner(System.in);
    }


    // display a list of all movies (id & title only)
    public void displayAllMovies() {
        System.out.println("Showing all " + Movie.numMovies + " movies:");
        for (int i = 0; i < this.movies.size(); i++) {
            this.movies.get(i).printMovieInfo();
        }
        System.out.println("");
    }

    public void createMovie() {
        // title, duration, director, cast, status, age rating, is3D, isBlockbuster
        System.out.println("Adding new Movie: ");

        System.out.print("Enter Movie Title: ");
        String title = sc.next();

        System.out.print("Enter Movie duration(mins): ");
        int duration = sc.nextInt();

        System.out.print("Enter Movie Director: ");
        String director = sc.next();

        // use comma to seperate? so need do comma parsing like in reviews
        System.out.print("Enter Movie Cast: ");
        String cast = sc.nextLine();
        sc.nextLine();

        System.out.println("Select Movie Status: ");
        int i=0;
        for (Movie.showStatusOptions m: Movie.showStatusOptions.values()) {
            System.out.print(i + ":" + m + "\t");
            i++;
        }
        System.out.println();
        int status = sc.nextInt();

        System.out.println("Enter Movie Age Rating: ");
        i=0;
        for (Movie.ageRatingOptions m: Movie.ageRatingOptions.values()) {
            System.out.print(i + ":" + m + "\t");
            i++;
        }
        System.out.println();
        int ageRating = sc.nextInt();

        System.out.print("Is Movie 3D(true,false): ");
        boolean is3D = sc.nextBoolean();

        System.out.print("Is Movie Blockbuster(true,false): ");
        boolean blockbuster = sc.nextBoolean();

        Movie m = new Movie(title, duration, director, cast, Movie.showStatusOptions.values()[status], Movie.ageRatingOptions.values()[ageRating],is3D, blockbuster);
        movies.add(m);

        movieDAO.saveMovie(m);
    }

    public void getMovies() {
        movieDAO.getMovie(this.movies);
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
