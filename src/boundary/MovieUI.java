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
            UtilUI.printBlue("|=========|Movie Admin Panel|=========|");
            System.out.print(
                "1. Display All Movies\n" +
                "2. Movie Detailed View\n" +
                "3. Create Movie\n" +
                "4. Edit Movie\n" +
                "5. Exit\n");
            int choice = UtilUI.getInt("Select action: ");
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
        String searchQuery = UtilUI.getStr("Enter movie title: ");
        int movieId = mc.searchMovie(searchQuery);
        displayDetailMovieInfo(movieId);
    }

    public static void displayAllMovies() {
        UtilUI.printBlue("Displaying all movies...");
        ArrayList<Movie> movies = mc.getShowingMovies();
        int curr = 0;
        Movie m;
        for(int i = 0; i < movies.size(); i++){
            m = movies.get(i);
            if (curr < 3) {
                if (curr == 0) {
                    if (m.getShowStatus() == Movie.showStatusOptions.NOW_SHOWING) {
                        System.out.println("--------Now Showing----------------------------------");
                        curr++;
                    }
                } else if (curr == 1) {
                    if (m.getShowStatus() == Movie.showStatusOptions.PREVIEW) {
                        System.out.println("--------Preview--------------------------------------");
                        curr++;
                    }
                } else if (curr == 2) {
                    if (m.getShowStatus() == Movie.showStatusOptions.COMING_SOON) {
                        System.out.println("--------Coming Soon----------------------------------");
                        curr++;
                    }
                }
            }
            System.out.println("Movie ID " + m.getMovieId() + ": " +
                m.getTitle());
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
            movieId = UtilUI.getInt("Enter movie ID: (enter -1 to exit) ");
            if (movieId == -1) { return -1;}
            if (movieId > 0 && movieId <= Movie.getNumMovies()) {
                break;
            }
            System.out.println("Invalid movie ID!");
        }
        return movieId;
    }

    private static void displayDetailMovieInfo(int movieId) {
        UtilUI.printBlue("Movie Detail View");
        Movie m = AppController.mc.getMovieById(movieId);
        System.out.println(m);
        m.getController().displayReviews();
    }
    
    private static void createMovie() {
        // title, duration, director, cast, status, age rating, is3D, isBlockbuster
        UtilUI.printBlue("Movie Creation");

        String title = UtilUI.getStr("Enter Movie Title: ");
        int duration = UtilUI.getInt("Enter Movie Duration (mins): ");
        String director = UtilUI.getStr("Enter Movie Director: ");
        String cast = UtilUI.getStr("Enter Movie Cast: ");
        // use comma to seperate? so need do comma parsing like in reviews

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

        boolean is3D = UtilUI.getBool("Is Movie 3D (true/false): ");
        boolean isBlockbuster = UtilUI.getBool("Is Movie Blockbuster (true/false): ");

        mc.createMovie(
            title, duration, director, cast, Movie.showStatusOptions.values()[status],
            Movie.ageRatingOptions.values()[ageRating], is3D, isBlockbuster
        );
    }

    private static void editMovie() {
        UtilUI.printBlue("Movie Editing");
        int movieId = promptValidMovieId();
        if (movieId == -1) { return;}

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
                    "9. Exit\n");
            option = UtilUI.getInt("Select action: ");
            if (option == 9) {break;}

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
                    boolean is3D = UtilUI.getBool("Enter new is3D (true/false): ");
                    mc.getMovieById(movieId).setIs3D(is3D);
                    break;
                case 8:
                    boolean isBlockbuster = UtilUI.getBool("Is Movie Blockbuster (true/false): ");
                    mc.getMovieById(movieId).setIsBlockbuster(isBlockbuster);
                    break;
                case 9:
                    break;
            }
            System.out.println("Success in editing!");
        }
    }

//    public static Movie movieSelection() {
//        int option;
//        int i = mc.displayShowingMovies();
//        while (true) {
//            option = UtilUI.getInt("Select a movie: ");
//            if (!(option < 0 || option >= i)) {
//                break;
//            }
//            System.out.println("Invalid Option.");
//        }
//        return mc.getMovieById(selectedMovieId);
//    }
}