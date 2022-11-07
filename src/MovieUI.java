import java.util.ArrayList;
import java.util.Scanner;

public class MovieUI {
    private static Scanner sc = new Scanner(System.in);
    private static MovieController mc = new MovieController();
    private static int movieId;
    public static void searchMovies() {
        System.out.print("Enter movie title:");
        String searchQuery = sc.nextLine();
        int movieId = mc.searchMovie(searchQuery);
    }

    public static void displayAllMovies() {
        System.out.println("Displaying all movies...");
        ArrayList<Movie> movies = mc.getAllMovies();
        for(int i = 0; i<movies.size(); i++){
            Movie movie = movies.get(i);
            System.out.println("ID: " + movie.getMovieId() + " Title: " + movie.getTitle());
        }
    }
    public static void createMovie() {
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
        boolean blockbuster = sc.nextBoolean();

        Movie m = new Movie(title, duration, director, cast, Movie.showStatusOptions.values()[status], Movie.ageRatingOptions.values()[ageRating],is3D, blockbuster);
        mc.createMovie(m);
    }

    public void editMovies() {
        int movieSelected;
        while (true) {
            System.out.print("Enter a movie ID: ");
            movieSelected = sc.nextInt();
            if (!(movieSelected <= 0 || movieSelected >= mc.getNumMovies())) {
                break;
            }
            System.out.println("Invalid Option.");
        }
        movieSelected -= 1;

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
                    mc.editTitle(title, movieSelected);
                    break;
                case 2:
                    System.out.println("Enter new duration: ");
                    int duration = sc.nextInt();
                    mc.editDuration(duration, movieSelected);
                    break;
                case 3:
                    System.out.println("Enter new Director: ");
                    String director = sc.nextLine();
                    mc.editDirector(director, movieSelected);
                    break;
                case 4:
                    System.out.println("Enter new Cast: ");
                    String cast = sc.nextLine();
                    mc.editCast(cast, movieSelected);
                    break;
                case 5:
                    int j=0;
                    for (Movie.showStatusOptions m: Movie.showStatusOptions.values()) {
                        System.out.print(j + ":" + m + "\t");
                        j++;
                    }
                    System.out.println("Select option to change: " + "(integer from 0 to " + (j-1) + ")");
                    int status = sc.nextInt();
                    mc.editShowingStatus(status, movieSelected);
                    break;
                case 6:
                    System.out.println("Enter Movie Age Rating: ");
                    int i=0;
                    for (Movie.ageRatingOptions m: Movie.ageRatingOptions.values()) {
                        System.out.print(i + ":" + m + "\t");
                        i++;
                    }
                    System.out.println("Select option to change: " + "(integer from 0 to " + (i-1) + ")");
                    int ageRating = sc.nextInt();
                    mc.editAgeRating(ageRating, movieSelected);
                    break;
                case 7:
                    System.out.print("Enter new is3D (true/false): ");
                    boolean is3D = sc.nextBoolean();
                    mc.editIs3D(is3D, movieSelected);

                    break;
                case 8:
                    System.out.print("Is Movie Blockbuster (true/false): ");
                    boolean isBlockbuster = sc.nextBoolean();
                    mc.editBlockBuster(isBlockbuster, movieSelected);

                    break;
                case 9:
                    System.out.printf("Confirm remove \"%s\"(Yes,No): " + mc.getAllMovies().get(movieSelected).getTitle());
                    String choice = sc.nextLine();
                    if (choice.toLowerCase() == "yes") {
                        String removedMovie = mc.removeMovie(movieSelected);
                        System.out.printf("Removed movie:\"%s\"", removedMovie);
                    }
                    break;
            }
            System.out.println("Success in editing!");
        }
    }
    public static void displayReviews(){
        mc.displayReviews(movieId);
    }

    public static void searchMovie(){

        System.out.println("Search movie by name: ");
        String title = sc.nextLine();
        movieId = mc.searchMovie(title);

    }

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
        int selectedMovie = sc.nextInt();
        mc.movieSelection(selectedMovie);

    }
    public static void rankMovieByRating(){
        mc.rankMovieByRating();
    }
    public static void rankMovieBySales(){
        mc.rankMovieBySales(5);
    }

}