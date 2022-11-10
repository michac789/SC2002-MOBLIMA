package boundary;
import java.util.ArrayList;
import java.util.Scanner;
import controller.AppController;
import controller.MovieController;
import model.Movie;

public class MovieUI {
    private static Scanner sc = new Scanner(System.in);
    private static MovieController mc = AppController.mc;

    public static void admin() {
        while (true) {
            System.out.println("|=========|Movie Admin Panel|=========|");
            System.out.print(
                "1. Display All Movies\n" +
                "2. Movie Detailed View\n" +
                "3. Create Movie\n" +
                "4. Edit Movie\n" +
                "5. Exit\n");
            System.out.print("Select action: ");
            int choice = sc.nextInt();
            // cleaner
            sc.nextLine();
            switch (choice) { 
                case 1:
                    displayAllMovies();
                    break;
                case 2:
                    displayDetailMovie();
                    break;
                case 3:
                    createMovie();
                    break;
                case 4:
                    editMovie();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid action, try again!");
                    break;
            }
        }
    }

    public static void searchMovie() {
        System.out.print("Enter movie title: ");
        String searchQuery = sc.nextLine();
        int movieId = mc.searchMovie(searchQuery);
        displayDetailMovieInfo(movieId);
    }

    public static void displayAllMovies() {
        System.out.println("Displaying all movies...");
        ArrayList<Movie> movies = mc.getAllMovies();
        for(int i = 0; i<movies.size(); i++){
            Movie movie = movies.get(i);
            System.out.println("Movie ID " + movie.getMovieId() + ": " +
                movie.getTitle() + " (" + movie.getShowStatus() + ")");
        }
        System.out.println("");
    }

    public static void displayDetailMovie() {
        int movieId = promptValidMovieId();
        if (movieId == -1) { return;}
        displayDetailMovieInfo(movieId);
    }

    public static int promptValidMovieId() {
        int movieId;
        while (true) {
            System.out.print("Enter movie ID: (enter -1 to exit) ");
            movieId = sc.nextInt();
            if (movieId == -1) { return -1;}
            if (movieId > 0 && movieId <= Movie.getNumMovies()) {
                break;
            }
            System.out.println("Invalid movie ID!");
        }
        return movieId;
    }

    private static void displayDetailMovieInfo(int movieId) {
        Movie m = AppController.mc.getMovieById(movieId);
        System.out.println(m);
        m.getController().displayReviews();
    }
    
    private static void createMovie() {
        // title, duration, director, cast, status, age rating, is3D, isBlockbuster
        System.out.println("Adding new Movie: ");

        System.out.print("Enter Movie Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Movie duration(mins): ");
        int duration = sc.nextInt();

        sc.nextLine();
        System.out.print("Enter Movie Director: ");
        String director = sc.nextLine();

        // use comma to seperate? so need do comma parsing like in reviews
        System.out.print("Enter Movie Cast: ");
        String cast = sc.nextLine();

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
        boolean isBlockbuster = sc.nextBoolean();

        mc.createMovie(
            title, duration, director, cast, Movie.showStatusOptions.values()[status],
            Movie.ageRatingOptions.values()[ageRating], is3D, isBlockbuster
        );
    }

    private static void editMovie() {
        int movieId;
        while (true) {
            System.out.print("Enter a movie ID: ");
            movieId = sc.nextInt();
            if (!(movieId <= 0 || movieId >= Movie.getNumMovies())) {
                break;
            }
            System.out.println("Invalid Option.");
        }

        int option;
        while (true) {
            System.out.print(
                    "1. Edit Title\n" +
                    "2. Edit Duration\n" +
                    "3. Edit Director\n" +
                    "4. Edit Cast\n" +
                    "5. Edit Showing Status\n" +
                    "6. Edit Age Rating\n" +
                    "7. Edit is3D\n" +
                    "8. Edit is Blockbuster\n" +
                    "9. Remove Movie\n" +
                    "10. Exit\n");
            option = sc.nextInt();
            if (option == 10) {break;}
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Enter new Title: ");
                    String title = sc.nextLine();
                    mc.getMovieById(movieId).setTitle(title);
                    break;
                case 2:
                    System.out.println("Enter new duration: ");
                    int duration = sc.nextInt();
                    mc.getMovieById(movieId).setDurationMinutes(duration);
                    break;
                case 3:
                    System.out.println("Enter new Director: ");
                    String director = sc.nextLine();
                    mc.getMovieById(movieId).setDirector(director);
                    break;
                case 4:
                    System.out.println("Enter new Cast: ");
                    String cast = sc.nextLine();
                    mc.getMovieById(movieId).setCast(cast);
                    break;
                case 5:
                    int j = 0;
                    for (Movie.showStatusOptions m: Movie.showStatusOptions.values()) {
                        System.out.print(j + ": " + m + "\t");
                        j++;
                    }
                    System.out.println("Select option to change: " + "(integer from 0 to " + (j - 1) + ")");
                    int status = sc.nextInt();
                    mc.getMovieById(movieId).setShowStatus(Movie.showStatusOptions.values()[status]);
                    break;
                case 6:
                    System.out.println("Enter Movie Age Rating: ");
                    int i = 0;
                    for (Movie.ageRatingOptions m: Movie.ageRatingOptions.values()) {
                        System.out.print(i + ": " + m + "\t");
                        i++;
                    }
                    System.out.println("Select option to change: " + "(integer from 0 to " + (i - 1) + ")");
                    int ageRating = sc.nextInt();
                    mc.getMovieById(movieId).setAgeRating(Movie.ageRatingOptions.values()[ageRating]);
                    break;
                case 7:
                    System.out.print("Enter new is3D (true/false): ");
                    boolean is3D = sc.nextBoolean();
                    mc.getMovieById(movieId).setIs3D(is3D);
                    break;
                case 8:
                    System.out.print("Is Movie Blockbuster (true/false): ");
                    boolean isBlockbuster = sc.nextBoolean();
                    mc.getMovieById(movieId).setIsBlockbuster(isBlockbuster);
                    break;
                case 9:
                // TODO
                    // System.out.printf("Confirm remove \"%s\"(Yes,No): " + mc.getAllMovies().get(movieSelected).getTitle());
                    // String choice = sc.nextLine();
                    // if (choice.toLowerCase() == "yes") {
                    //     String removedMovie = mc.removeMovie(movieSelected);
                    //     System.out.printf("Removed movie:\"%s\"", removedMovie);
                    // }
                    break;
            }
            System.out.println("Success in editing!");
        }
    }
    
    // public static void displayReviews(){
    //     mc.displayReviews(movieId);
    // }

    // public static void searchMovie(){

    //     System.out.println("Search movie by name: ");
    //     String title = sc.nextLine();
    //     movieId = mc.searchMovie(title);

    // }

    public static void displayShowingMovies () {
        mc.displayShowingMovies();

    }

    public static void movieSelection(){
        int option;
        int i = mc.displayShowingMovies();
        while (true) {
            System.out.print("Select a movie: ");
            option = sc.nextInt();
            if (!(option < 0 || option >= i)) {
                break;
            }
            System.out.println("Invalid Option.");
        }
        int selectedMovieId = sc.nextInt();
        mc.getMovieById(selectedMovieId);

    }
    // public static void rankMovieByRating(){
    //     mc.rankMovieByRating(5);
    // }
    // public static void rankMovieBySales(){
    //     mc.rankMovieBySales(5);
    // }
}