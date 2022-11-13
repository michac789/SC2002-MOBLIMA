package model;
import controller.ReviewController;
import static java.lang.Double.isNaN;

/**
 * Represents the movie object
 * @version 1.0
 * @since 2022-11-13
 */
public class Movie {
    /**
     * Represents the total number of movies
     */
    private static int numMovies = 0;
    /**
     * ReviewController object related to this specific movie containing all reviews for this specific movie
     */
    private ReviewController reviewController;

    /**
     * The unique ID related to this movie
     */
    private int movieId;
    /**
     * The movie title
     */
    private String title;
    /**
     * The duration of the movie
     */
    private int durationMinutes;
    /**
     * The synopsis of the movie
     */
    private String synopsis;
    /**
     * The name of the director of the movie
     */
    private String director;
    /**
     * The main cast of this movie
     */
    private String cast;

    /**
     * The categories of movie showing status
     */
    public enum showStatusOptions {
        COMING_SOON, PREVIEW, NOW_SHOWING, END_OF_SHOWING
    }

    /**
     * The status of movie
     */
    private showStatusOptions showStatus;

    /**
     * The categories of age rating
     */
    public enum ageRatingOptions {
        G, PG, PG13, NC16, M18, R21
    };
    /**
     * The age rating related to the movie
     */
    private ageRatingOptions ageRating;
    /**
     * The indicator whether a movie is 3D movie or not
     */
    private boolean is3D;
    /**
     * The indicator whether a movie is a blockbuster movie or not
     */
    private boolean isBlockbuster;
    /**
     * The number of sales that the movie has
     */
    private int sales;

    /**
     * Constructor for creating movies to store its specific details
     * @param title The title of the movie
     * @param durationMinutes The duration of the movie
     * @param synopsis The synopsis of the movie
     * @param director The name of the director of the movie
     * @param cast The main cast of the movie
     * @param showStatus The showing status of this movie
     * @param ageRating The age rating of the movie
     * @param is3D The indicator whether movie is 3D or not
     * @param isBlockbuster The indicator whether the movie is blockbuster or not
     * @param sales The total number of sales of movie
     */
    public Movie(
            String title, int durationMinutes, String synopsis, String director, String cast,
            showStatusOptions showStatus, ageRatingOptions ageRating,
            boolean is3D, boolean isBlockbuster, int sales
        ) {
        numMovies++;
        this.movieId = numMovies;
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.showStatus = showStatus;
        this.ageRating = ageRating;
        this.is3D = is3D;
        this.isBlockbuster = isBlockbuster;
        this.sales = sales;
        this.reviewController = new ReviewController(movieId);
    }

    /**
     * Sets then movie ID
     * @param id The desired movie ID
     */
    public void setMovieId(int id) { this.movieId = id;}

    /**
     * Sets the title of the movie
     * @param title The title of the movie
     */
    public void setTitle(String title) { this.title = title;}

    /**
     * Sets the duration of the movie
     * @param durationMinutes The duration of the movie in minutes
     */
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes;}

    /**
     * Sets the synopsis of this movie
     * @param synopsis The synopsis of movie
     */
    public void setSynopsis(String synopsis) { this.synopsis = synopsis;}

    /**
     * Sets the name of director of movie
     * @param director The name of the director
     */
    public void setDirector(String director) { this.director = director;}

    /**
     * Sets the main cast of the movie
     * @param cast The main cast of the movie
     */
    public void setCast(String cast) { this.cast = cast;}

    /**
     * Sets the showing status of the movie
     * @param showStatus The showing status of movie of type showStatusOptions
     */
    public void setShowStatus(showStatusOptions showStatus) { this.showStatus = showStatus;}

    /**
     * Sets the age rating of the movie
     * @param ageRating The age rating of the movie of type ageRatingOptions
     */
    public void setAgeRating(ageRatingOptions ageRating) { this.ageRating = ageRating;}

    /**
     * Sets the indicator of the movie whether it is 3D or not
     * @param is3D The indicator of the movie being 3D or not
     */
    public void setIs3D(boolean is3D) { this.is3D = is3D;}

    /**
     * Sets the indicator of the movie whether it is a blockbuster movie or not
     * @param isBlockbuster The indicator of the movie being blockbuster type or not
     */
    public void setIsBlockbuster(boolean isBlockbuster) { this.isBlockbuster = isBlockbuster;}

    /**
     * Increments the number of sales in the event of moviegoer purchases tickets to specific movie
     * @param n The increment number of sales
     */
    public void incrementSales(int n) { this.sales = this.sales + n;}

    /**
     * Gets the movie ID
     * @return The movie ID
     */
    public int getMovieId() { return this.movieId;}

    /**
     * Gets the title of the movie
     * @return The title of the movie
     */
    public String getTitle() { return this.title;}

    /**
     * Gets the name of the director
     * @return The number of director
     */
    public String getDirector() { return this.director;}

    /**
     * Gets the main cast of this movie
     * @return The main cast of this movie
     */
    public String getCast() { return this.cast;}

    /**
     * Gets the duration of the movie
     * @return The duration of the movie in minutes
     */
    public int getDurationMinutes() { return this.durationMinutes;}

    /**
     * Gets the synopsis of the movie
     * @return The synopsis of the movie
     */
    public String getSynopsis() { return this.synopsis;}

    /**
     * Gets the showing status of the movie
     * @return The value of type enum showStatusOptions representing the showing status
     */
    public showStatusOptions getShowStatus() { return this.showStatus;}

    /**
     * Gets the age rating of the movie
     * @return The value of type enum ageRatingOptions representing the age rating of the movie
     */
    public ageRatingOptions getAgeRating() { return this.ageRating;}

    /**
     * Gets the indicator of 3D movie
     * @return The indicator of movie being 3D or not
     */
    public boolean is3D() { return this.is3D;}

    /**
     * Gets the indicator whether a movie is a type of blockbuster or not
     * @return The indicator of movie being blockbuster one or not
     */
    public boolean isBlockbuster() { return this.isBlockbuster;}

    /**
     * Gets 3D information with the abstraction of string "yes" or "no"
     * @return The string representing 3D or not
     */
    public String getIs3D() { return this.is3D ? "yes" : "no";}
    /**
     * Gets blockbuster information with the abstraction of string "yes" or "no"
     * @return The string representing blockbuster one or not
     */
    public String getIsBlockbuster() { return this.isBlockbuster ? "yes": "no";}

    /**
     * Get the number of sales of this movie
     * @return the number of sales of this movie
     */
    public int getSalesCount() { return this.sales;}

    /**
     * Gets the total number of movies
     * @return The total number of movies
     */
    public static int getNumMovies() { return numMovies;}

    /**
     * Gets the ReviewController object associated with the movie
     * @return The ReviewController object containing its review associated with this movie
     */
    public ReviewController getController() { return this.reviewController;}

    /**
     * Gets the average rating of the movie
     * @return The average rating of the movie
     */
    public double getRating() {
        return this.reviewController.getAverageRating();
    }

    /**
     * Formats the movie information containing movieID, title, durationMinutes, director, cast, showStatus, ageRating, and type of movies with its review
     * @return String representation of movie meta information
     */
    public String toString() {
        return "-----------------------------------------------------\n" +
            "Movie ID      : " + this.movieId + "\n" +
            "Movie Title   : " + this.title + "\n" +
            "Duration      : " + this.durationMinutes + "\n" +
            "Synopsis      : \n" + this.synopsis + "\n" +
            "Director      : " + this.director + "\n" +
            "Cast          : " + this.cast + "\n" +
            "Show Status   : " + this.showStatus + "\n" +
            "Age Rating    : " + this.ageRating + "\n" +
            "3D            : " + this.getIs3D() + "\n" +
            "Blockbuster   : " + this.getIsBlockbuster() + "\n" +
            "Average Rating: " + (isNaN(this.reviewController.getAverageRating()) ? "NA\n" :
                String.format("%.1f (by %d users)\n",
                this.reviewController.getAverageRating(), this.reviewController.getNumReviews())) +
            "-----------------------------------------------------";
    }
}