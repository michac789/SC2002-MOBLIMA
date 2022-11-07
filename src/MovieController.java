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

    public void createMovie() {
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
        movies.add(m);
    }

    public void editMovies() {
        int movieSelected;
        while (true) {
            System.out.print("Enter a movie ID: ");
            movieSelected = sc.nextInt();
            if (!(movieSelected <= 0 || movieSelected >= Movie.getNumMovies())) {
                break;
            }
            System.out.println("Invalid Option.");
        }
        movieSelected -= 1;

        int option;
        while (true) {
            System.out.print("1. Edit Title\n" +
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

            String strSc;
            int intSc;
            boolean booleanSc;
            switch (option) {
                case 1:
                    System.out.println("Enter new Title: ");
                    strSc = sc.nextLine();
                    this.movies.get(movieSelected).setTitle(strSc);
                    break;
                case 2:
                    System.out.println("Enter new duration: ");
                    intSc = sc.nextInt();
                    this.movies.get(movieSelected).setDurationMinutes(intSc);
                    break;
                case 3:
                    System.out.println("Enter new Director: ");
                    strSc = sc.nextLine();
                    this.movies.get(movieSelected).setDirector(strSc);
                    break;
                case 4:
                    System.out.println("Enter new Cast: ");
                    strSc = sc.nextLine();
                    this.movies.get(movieSelected).setCast(strSc);
                    break;
                case 5:
                    int j=0;
                    for (Movie.showStatusOptions m: Movie.showStatusOptions.values()) {
                        System.out.print(j + ":" + m + "\t");
                        j++;
                    }
                    System.out.println();
                    int status = sc.nextInt();
                    this.movies.get(movieSelected).setShowStatus(Movie.showStatusOptions.values()[status]);
                    break;
                case 6:
                    System.out.println("Enter Movie Age Rating: ");
                    int i=0;
                    for (Movie.ageRatingOptions m: Movie.ageRatingOptions.values()) {
                        System.out.print(i + ":" + m + "\t");
                        i++;
                    }
                    System.out.println();
                    int ageRating = sc.nextInt();
                    this.movies.get(movieSelected).setAgeRating(Movie.ageRatingOptions.values()[ageRating]);
                    break;
                case 7:
                    System.out.print("Enter new is3D(true,false): ");
                    booleanSc = sc.nextBoolean();
                    this.movies.get(movieSelected).setIs3D(booleanSc);
                    break;
                case 8:
                    System.out.print("Is Movie Blockbuster(true,false): ");
                    booleanSc = sc.nextBoolean();
                    this.movies.get(movieSelected).setIsBlockbuster(booleanSc);
                    break;
                case 9:
                    System.out.printf("Confirm remove \"%s\"(Yes,No): ", this.movies.get(movieSelected).getTitle());
                    strSc = sc.nextLine();
                    if (strSc.toLowerCase() == "yes") {
                        movies.remove(movieSelected);
                        System.out.printf("Removed movie:\"%s\"", this.movies.get(movieSelected).getTitle());
                        break;
                    }
            }
        }
        // saveMovies();
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


