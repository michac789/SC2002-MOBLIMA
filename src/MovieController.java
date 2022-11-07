import java.util.*;

public class MovieController {
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private MovieDAO movieDAO = new MovieDAO();
    private Scanner sc;


    public ArrayList<Movie> getAllMovies () {
        return movies;
    }
    public MovieController() {
        movies = this.movieDAO.load();
        sc = new Scanner(System.in);
    }
    
    public void save() {
        this.movieDAO.save(movies);
    }

    public void createMovie(Movie m) {
        movies.add(m);
    }
    public int getNumMovies() {
        return Movie.getNumMovies();
    }
    public void editTitle(String title, int movieSelected) {
        this.movies.get(movieSelected).setTitle(title);
    }

    public void editDuration(int duration, int movieSelected) {
        this.movies.get(movieSelected).setDurationMinutes(duration);
    }

    public void editDirector(String director, int movieSelected){
        this.movies.get(movieSelected).setDirector(director);
    }

    public void editCast(String cast, int movieSelected){
        this.movies.get(movieSelected).setCast(cast);
    }

    public void editShowingStatus(int status, int movieSelected){
        this.movies.get(movieSelected).setShowStatus(Movie.showStatusOptions.values()[status]);

    }
    public void editAgeRating(int ageRating, int movieSelected){
        this.movies.get(movieSelected).setAgeRating(Movie.ageRatingOptions.values()[ageRating]);

    }


    public void editIs3D (boolean is3D, int movieSelected){
        this.movies.get(movieSelected).setIs3D(is3D);
    }

    public void editBlockBuster(boolean isBlockBuster, int movieSelected) {
        this.movies.get(movieSelected).setIsBlockbuster(isBlockBuster);
    }

    public String removeMovie(int movieSelected){
        String title = this.movies.get(movieSelected).getTitle();
        movies.remove(movieSelected);
        return title;

    }


    public Movie movieSelection() {
        System.out.println("Select Movie:");
        Movie.showStatusOptions option = Movie.showStatusOptions.NOW_SHOWING;
        int i=0;
        int[] movieList = new int[this.movies.size()];
        for (Movie m: this.movies) {
            if(m.getShowStatus() == option) {
                System.out.printf("%d: %s\n", i, m.getTitle());
                movieList[i] = m.getMovieId();
                i++;
            }
        }
        int selectedMovie = sc.nextInt();

        for (Movie m: this.movies) {
            if (m.getMovieId() == movieList[selectedMovie]) {
                return m;
            }
        }

        return null;
    }

     public int displayShowingMovies() {
         int i = 0;
         int[] id = new int[movies.size()];
         for (int j = 0; j < movies.size(); j++) {
             if (movies.get(j).getShowStatus() == Movie.showStatusOptions.NOW_SHOWING) {
                 System.out.printf("%d: %s\n", i, movies.get(j).getTitle());
                 id[i] = j;
                 i++;
             }
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

         return id[option];
     }


    public void rankMovieBySales(int num) {

    }

    public void rankMovieByRating() {
        if (movies.size() == 0) {
            System.out.println("No Movies");
            return;
        }
        LinkedList<Movie> sortedRating = new LinkedList<Movie>();

        sortedRating.add(movies.get(0));
        for (int i=1; i < movies.size();i++) {
            for (int j=i-1; j >= 0;j--) {
                if (movies.get(i).getRating() > sortedRating.get(j).getRating()) {
                    if (j==0) {
                        sortedRating.add(j, movies.get(i));
                        break;
                    }
                    continue;
                }else {
                    sortedRating.add(j+1, movies.get(i));
                }
            }
        }

        int i=1;
        int maxListings = 5;
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
         int[] id = new int[movies.size()];
         for (int j=0; j < movies.size();j++) {
             if (movies.get(j).getTitle().contains(title)) {
                 System.out.printf("%d: %s\n", i, movies.get(j).getTitle());
                 id[i] = j;
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
         return id[option]; // Return Movie Id
     }

    public void displayReviews(int movieId) {
        for (Movie m: this.movies) {
            if (m.getMovieId() == movieId) {
                m.displayReviews();
                break;
            }
        }
    }
}

//     // TODO
//     // display all movies that has 'title' as a substring
//     // or can improve with other better search algorithm
//

//     // TODO
//     // display the first 'num' movies with highest ticket sales
//     public void rankMovieBySales(int num) {
//         System.out.println("TODO");
//     }

//     // TODO
//     // display the first 'num' movies with highest rating
