package boundary;
import java.util.ArrayList;
import controller.AppController;
import controller.MovieController;
import model.Movie;

public class MovieUI {
    private static MovieController mc = AppController.mc;

    /*
     * Called in App.java to show all movies except sorted by category,
     * except the category 'END_OF_SHOWING' should not be showed here.
     * It then prompts the user for a valid movie ID, then show a detailed
     * view of a particular movie ID.
     */
    public static void main() {
        UtilUI.printBlue("Displaying all movies...");
        ArrayList<Movie> movies = mc.getShowingMovies();
        int curr = 0;
        Movie m;
        for(int i = 0; i < movies.size(); i++){
            m = movies.get(i);
            if (curr < 3) {
                if (curr == 0) {
                    if (m.getShowStatus() == Movie.showStatusOptions.NOW_SHOWING) {
                        UtilUI.printPurple("--------Now Showing----------------------------------");
                        curr++;
                    }
                } else if (curr == 1) {
                    if (m.getShowStatus() == Movie.showStatusOptions.PREVIEW) {
                        UtilUI.printPurple("--------Preview--------------------------------------");
                        curr++;
                    }
                } else if (curr == 2) {
                    if (m.getShowStatus() == Movie.showStatusOptions.COMING_SOON) {
                        UtilUI.printPurple("--------Coming Soon----------------------------------");
                        curr++;
                    }
                }
            }
            System.out.println("Movie ID " + m.getMovieId() + ": " +
                m.getTitle());
        }
        while (true) {
            UtilUI.printBlue("Enter Movie ID to View Details");
            int movieId = promptValidMovieId(true, true, false);
            if (movieId == -1) { break;}
            displayDetailMovieInfo(movieId);
        }
    }

    /*
     * Prompts user for a searchQuery
     * Displays all movies whose title is a substring of the searchQuery (case insensitive)
     * It can search for movies with any show status (including end_of_showing)
     */
    public static void searchMovie() {
        UtilUI.printBlue("Search Feature");
        String searchQuery = UtilUI.getStr("Search movie by its title: ");
        ArrayList<Movie> movies = mc.searchMovie(searchQuery);
        displayBasicInfoMovies(movies);
        System.out.println("");
    }

    /*
     * Prompt the user for a valid movie ID, and return the movie ID selected
     * Display appropriate error message if movie ID is invalid or if access not allowed
     * It takes three boolean arguments: prev, comingSoon, end
     * If prev is true, movie with status of 'PREVIEW' can be selected
     * If comingSoon is true, movie with status of 'COMING_SOON' can be selected
     * If end is true, movie with status of 'END_OF_SHOWING' can be selected
     * All movies with status 'NOW_SHOWING' can always be selected
     */
    public static int promptValidMovieId(boolean prev, boolean comingSoon, boolean end) {
        int movieId;
        while (true) {
            movieId = UtilUI.getInt("Enter movie ID: (enter -1 to exit) ");
            if (movieId == -1) {
                System.out.println("");
                return -1;
            }
            Movie m = AppController.mc.getMovieById(movieId);
            if (movieId <= 0 || movieId > Movie.getNumMovies()) {
                UtilUI.printRed("Invalid movie ID!");
            } else if (!prev && m.getShowStatus() == Movie.showStatusOptions.PREVIEW ||
                    !comingSoon && m.getShowStatus() == Movie.showStatusOptions.COMING_SOON ||
                    !end && m.getShowStatus() == Movie.showStatusOptions.END_OF_SHOWING) {
                UtilUI.printRed("You are not allowed to access this Movie ID!");
            } else {
                break;
            }
        }
        return movieId;
    }

    /*
     * Called from AdminUI, provide admin configuration for movies as follows:
     * 1) Displaying all movies (all show status), and detailed view for each movie
     * 2) Create new movie
     * 3) Edit various information in existing movie
     */
    public static void admin() {
        while (true) {
            UtilUI.printBlue("|=========|Movie Admin Panel|=========|");
            System.out.print(
                "(1) Display All Movies\n" +
                "(2) Create Movie\n" +
                "(3) Edit Movie\n" +
                "(4) Exit\n");
            int choice = UtilUI.getInt("Select action: ");
            switch (choice) { 
                case 1:
                    displayAllMovies();
                    break;
                case 2:
                    createMovie();
                    break;
                case 3:
                    editMovie();
                    break;
                case 4:
                    return;
                default:
                    UtilUI.printRed("Invalid action, try again!");
                    break;
            }
        }
    }

    /*
     * Display all movies regardless of the show status, used by admin.
     * It then prompts the admin for a movie ID to show detailed view.
     */
    private static void displayAllMovies() {
        UtilUI.printBlue("Displaying all movies...");
        ArrayList<Movie> movies = mc.getAllMovies();
        displayBasicInfoMovies(movies);
        while (true) {
            UtilUI.printBlue("Enter Movie ID to View Details");
            int movieId = promptValidMovieId(true, true, true);
            if (movieId == -1) { break;}
            displayDetailMovieInfo(movieId);
        }
    }

    /*
     * Utility function that takes in an arraylist of movies,
     * and display basic movie information in a tidy column view
     * (id, title, show status)
     * Used for admin and search feature
     */
    private static void displayBasicInfoMovies(ArrayList<Movie> movies) {
        for(int i = 0; i < movies.size(); i++){
            Movie movie = movies.get(i);
            System.out.println(
                String.format("Movie ID %-3s: %-20s (%s)",
                movie.getMovieId(), movie.getTitle(), movie.getShowStatus())
            );
        }
    }

    /*
     * Display detailed movie information and its reviews
     */
    private static void displayDetailMovieInfo(int movieId) {
        UtilUI.printBlue("Movie Detail View");
        Movie m = AppController.mc.getMovieById(movieId);
        System.out.println(m);
        m.getController().displayReviews();
    }
    
    /*
     * Admin function to create new movie
     */
    private static void createMovie() {
        UtilUI.printBlue("Movie Creation");
        String title = UtilUI.getStr("Enter Movie Title: ");
        int duration = UtilUI.getInt("Enter Movie Duration (mins): ");
        String synopsis = UtilUI.getStrSafe("Enter Movie Synopsis: ");
        String director = UtilUI.getStrSafe("Enter Movie Director: ");
        String cast;
        while (true) {
            cast = UtilUI.getStrSafe("Enter new cast: (at least 2 needed, seperate by comma)\n");
            if (cast.contains(",")) { break;}
            UtilUI.printRed("At least 2 casts needed, seperate your cast using comma!");
        }

        System.out.println("Select Movie Status: ");
        int i = 0;
        for (Movie.showStatusOptions m: Movie.showStatusOptions.values()) {
            System.out.print(i + ":" + m + "\t");
            i++;
        }
        int status = UtilUI.getInt("\n");

        System.out.println("Enter Movie Age Rating: ");
        i = 0;
        for (Movie.ageRatingOptions m: Movie.ageRatingOptions.values()) {
            System.out.print(i + ":" + m + "\t");
            i++;
        }
        int ageRating = UtilUI.getInt("\n");

        boolean is3D = UtilUI.getBool("Is Movie 3D: ");
        boolean isBlockbuster = UtilUI.getBool("Is Movie Blockbuster: ");
        mc.createMovie(
            title, duration, synopsis, director, cast, Movie.showStatusOptions.values()[status],
            Movie.ageRatingOptions.values()[ageRating], is3D, isBlockbuster
        );
    }

    /*
     * Admin function to edit existing movie
     */
    private static void editMovie() {
        UtilUI.printBlue("Movie Editing");
        int movieId = promptValidMovieId(true, true, true);
        if (movieId == -1) { return;}

        int option;
        while (true) {
            System.out.print(
                    "(1) Edit Title\n" +
                    "(2) Edit Duration\n" +
                    "(3) Edit Synopsis\n" +
                    "(4) Edit Director\n" +
                    "(5) Edit Cast\n" +
                    "(6) Edit Showing Status\n" +
                    "(7) Edit Age Rating\n" +
                    "(8) Edit is3D\n" +
                    "(9) Edit is Blockbuster\n" +
                    "(10) Exit\n");
            option = UtilUI.getInt("Select action: ");
            if (option == 10) { break;}

            switch (option) {
                case 1:
                    String title = UtilUI.getStr("Enter new Title: ");
                    mc.getMovieById(movieId).setTitle(title);
                    break;
                case 2:
                    int duration = UtilUI.getInt("Enter new duration: ");
                    mc.getMovieById(movieId).setDurationMinutes(duration);
                    break;
                case 3:
                    String synopsis = UtilUI.getStr("Enter new synopsis: ");
                    mc.getMovieById(movieId).setSynopsis(synopsis);
                    break;
                case 4:
                    String director = UtilUI.getStrSafe("Enter new director: ");
                    mc.getMovieById(movieId).setDirector(director);
                    break;
                case 5:
                    String cast;
                    while (true) {
                        cast = UtilUI.getStrSafe("Enter new cast: (at least 2 needed, seperate by comma)\n");
                        if (cast.contains(",")) { break;}
                        UtilUI.printRed("At least 2 casts needed, seperate your cast using comma!");
                    }
                    mc.getMovieById(movieId).setCast(cast);
                    break;
                case 6:
                    int j = 0;
                    for (Movie.showStatusOptions m: Movie.showStatusOptions.values()) {
                        System.out.print(j + ": " + m + "\t");
                        j++;
                    }
                    System.out.println("Select option to change: " + "(integer from 0 to " + (j - 1) + ")");
                    int status = UtilUI.getInt("");
                    mc.getMovieById(movieId).setShowStatus(Movie.showStatusOptions.values()[status]);
                    break;
                case 7:
                    System.out.println("Enter Movie Age Rating: ");
                    int i = 0;
                    for (Movie.ageRatingOptions m: Movie.ageRatingOptions.values()) {
                        System.out.print(i + ": " + m + "\t");
                        i++;
                    }
                    System.out.println("Select option to change: " + "(integer from 0 to " + (i - 1) + ")");
                    int ageRating = UtilUI.getInt("");
                    mc.getMovieById(movieId).setAgeRating(Movie.ageRatingOptions.values()[ageRating]);
                    break;
                case 8:
                    boolean is3D = UtilUI.getBool("Is Movie 3D? ");
                    mc.getMovieById(movieId).setIs3D(is3D);
                    break;
                case 9:
                    boolean isBlockbuster = UtilUI.getBool("Is Movie Blockbuster? ");
                    mc.getMovieById(movieId).setIsBlockbuster(isBlockbuster);
                    break;
                case 10:
                    break;
            }
            UtilUI.printGreen("Movie editing successful!");
        }
    }
}