package controller;
import java.util.*;
import DAO.MovieGoerDAO;
import model.Movie;
import model.MovieGoer;
import model.Review;

/**
 * Controller for all MovieGoer objects
 * @version 1.0
 * @since 2022-11-13
 */
public class MovieGoerController {
    /**
     * Contain an arraylist of all movieGoer objects
     */
    private ArrayList<MovieGoer> movieGoers = new ArrayList<MovieGoer>();
    /**
     * MovieGoerDAO to get and save data to database
     */
    private MovieGoerDAO movieGoerDao = new MovieGoerDAO();

    /**
     * Load necessary information regarding all moviegoers from database
     * Called at the start of the program
     */
    public MovieGoerController() {
        movieGoers = this.movieGoerDao.load();
    }

    /**
     * Save all local changes of movie goers to the database
     * Call the 'save' method for booking controllers on all movie goers
     */
    public void save() {
        this.movieGoerDao.save(movieGoers);
    }

    /**
     * Get a movieGoer object, given its id
     * @param id ID of movieGoer to be retrieved
     * @return MovieGoer object that has that particular id
     */
    public MovieGoer getMovieGoerById(int id) {
        return this.movieGoers.get(id - 1);
    }

    /**
     * Authenticate a user, make sure password entered equals to correct password
     * @param username Username entered of a moviegoer object
     * @param password Password entered by the user
     * @return True if authentication successful, otherwise false
     */
    public int login(String username, String password){  
        for(int i = 0; i < this.movieGoers.size(); i++){ 
            if (username.equals(this.movieGoers.get(i).getUsername()) &&
                    password.equals(this.movieGoers.get(i).getPassword())) {
                return this.movieGoers.get(i).getId();
            }
        }
        return -1;
    }

    /**
     * Get MovieGoer ID given its username
     * @param username Username of movieGoer
     * @return ID of movieGoer if exist, otherwise -1
     */
    public int getMovieGoerIdByUsername(String username) {
        for (int i = 0; i < movieGoers.size(); i++){
            if (movieGoers.get(i).getUsername().equals(username)){
                return (i + 1);
            }
        }
        return -1;
    }

    /**
     * Create new movie Goer object
     * @param username
     * @param password
     * @param email
     * @param phoneNumber
     * @param age
     */
    public void createNewMovieGoer(String username, String password,
            String email, String phoneNumber, int age) {
        int newMovieGoerId = MovieGoer.movieGoerCount + 1;
        movieGoerDao.createUserBookingFile(newMovieGoerId);

        MovieGoer mg = new MovieGoer(username, password, phoneNumber, email, age);
        movieGoers.add(mg);
    }

    /**
     * Check whether a username has been used or not,
     * used to ensure that all movieGoers has a unique username
     * @param username Username of the MovieGoer
     * @return True if username is not used yet, false otherwise
     */
    public boolean isMovieGoerExist(String username) {
        for (int i = 0; i < movieGoers.size(); i++){
            if (movieGoers.get(i).getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    /**
     * Display all reviews made by a particular MovieGoer based on id
     * @param movieGoerId ID of movieGoer
     */
    public void displayAllReviewsByMovieGoerId(int movieGoerId) {
        ArrayList<Movie> movies = AppController.mc.getAllMovies();
        ArrayList<Review> tempReviews;
        for (int i = 0; i < movies.size(); i++) {
            tempReviews = movies.get(i).getController().getAllReviews();
            for (int j = 0; j < tempReviews.size(); j++) {
                boolean temp = false;
                if (tempReviews.get(j).getUserId() == movieGoerId) {
                    if (!temp) {
                        System.out.println(String.format("Movie ID %d: %s",
                            movies.get(i).getMovieId(), movies.get(i).getTitle()
                        ));
                    }
                    System.out.println(tempReviews.get(j));
                    temp = true;
                }
            }
        }
    }
}