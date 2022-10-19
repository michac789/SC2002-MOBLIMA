public class Movie {
    private int movieId;
    private String title;
    
    private String director;
    private String cast;

    // coming soon, preview, now showing, end of showing enum??
    // allow booking -> preview, now showing
    private String showStatus; 
    private String ageRating; // enum -> G, PG, PG13, NC16, M18, R21

    private boolean is3D;
    private boolean isBlockbuster;

    private int duration; // duration in minutes

    private static int numMovies = 0;

    public Movie(int id, String title) { // TODO - initialize everything
        numMovies++;
        this.movieId = numMovies;
        this.title = title;
        // todo: initialize other variables
        // provide all var in constructor?? or make some default values?
    }

    // mutators - todo: add more
    public void setMovieId(int id) { this.movieId = id;}
    public void setTitle(String title) { this.title = title;}

    // accessors - todo: add more
    public int getMovieId() { return this.movieId;}
    public String getTitle() { return this.title;}

    // todo - add more information
    public void printMovieInfo() {
        System.out.println("Movie ID: " + this.movieId);
        System.out.println("Movie Title: " + this.title);
        System.out.println("");
    }
}
