public class Movie {
    private static int numMovies = 0;
    // private ReviewsController reviews;

    private int movieId;
    private String title;
    private int durationMinutes;
    private String director;
    private String cast;
    public enum showStatusOptions {
        COMING_SOON, PREVIEW, NOW_SHOWING, END_OF_SHOWING
    }
    private showStatusOptions showStatus;
    public enum ageRatingOptions {
        G, PG, PG13, NC16, M18, R21
    };
    private ageRatingOptions ageRating;
    private boolean is3D;
    private boolean isBlockbuster;

    public Movie(
            String title, int durationMinutes, String director, String cast,
            showStatusOptions showStatus, ageRatingOptions ageRating,
            boolean is3D, boolean isBlockbuster
        ) {
        numMovies++;
        this.movieId = numMovies;
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.director = director;
        this.cast = cast;
        this.showStatus = showStatus;
        this.ageRating = ageRating;
        this.is3D = is3D;
        this.isBlockbuster = isBlockbuster;
    }

    public void setMovieId(int id) { this.movieId = id;}
    public void setTitle(String title) { this.title = title;}
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes;}
    public void setDirector(String director) { this.director = director;}
    public void setCast(String cast) { this.cast = cast;}
    public void setShowStatus(showStatusOptions showStatus) { this.showStatus = showStatus;}
    public void setAgeRating(ageRatingOptions ageRating) { this.ageRating = ageRating;}
    public void setIs3D(boolean is3D) { this.is3D = is3D;}
    public void setIsBlockbuster(boolean isBlockbuster) { this.isBlockbuster = isBlockbuster;}

    public int getMovieId() { return this.movieId;}
    public String getTitle() { return this.title;}
    public String getDirector() { return director;}
    public String getCast() { return cast;}
    public int getDurationMinutes() { return durationMinutes;}
    public showStatusOptions getShowStatus() { return showStatus;}
    public ageRatingOptions getAgeRating() { return ageRating;}
    public boolean is3D() { return is3D;}
    public boolean isBlockbuster() { return isBlockbuster;}
    public String getIs3D() { return this.is3D ? "yes" : "no";}
    public String getIsBlockbuster() { return this.isBlockbuster ? "yes": "no";}
    public static int getNumMovies() { return numMovies;}

    // print basic movie information (id & title)
    public void printMovieInfo() {
        System.out.println(
            String.format("ID %d: %s", this.movieId, this.title)
        );
    }

    //Method Override
    // print complete movie information
//    public String toString() {
//        String rtn;
//        System.out.println("Movie ID: " + this.movieId);
//        System.out.println("Movie Title: " + this.title);
//        System.out.println("Duration: " + this.durationMinutes);
//        System.out.println("Director: " + this.director);
//        System.out.println("Cast: " + this.cast);
//        System.out.println("Show Status: " + this.showStatus);
//        System.out.println("Age Rating: " + this.ageRating);
//        System.out.println("3D: " + this.getIs3D());
//        System.out.println("Blockbuster: " + this.getIsBlockbuster());
//        System.out.println("");
//    }

    // public void loadReviews() {
    //     reviews = new ReviewsController(this.movieId);
    // }

    // public double getRating() {
    //     return reviews.getRating();
    // }
    // public void displayReviews() {
    //     reviews.displayReviews(1);
    // }
}
