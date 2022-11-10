package model;
import controller.ReviewController;

public class Movie {
    private static int numMovies = 0;
    private ReviewController reviewController;

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
        this.reviewController = new ReviewController(movieId);
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
    public ReviewController getController() { return this.reviewController;}

    public double getRating() {
        return this.reviewController.getAverageRating();
    }
    
    public String toString() {
        return "Movie ID: " + this.movieId + "\n" +
            "Movie Title: " + this.title + "\n" +
            "Duration: " + this.durationMinutes + "\n" +
            "Director: " + this.director + "\n" +
            "Cast: " + this.cast + "\n" +
            "Show Status: " + this.showStatus + "\n" +
            "Age Rating: " + this.ageRating + "\n" +
            "3D: " + this.getIs3D() + "\n" +
            "Blockbuster: " + this.getIsBlockbuster() + "\n" +
            "Rating: " + this.reviewController.getAverageRating() +
            " (by " + this.reviewController.getNumReviews() +
            " users)\n";
    }
}