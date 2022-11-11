package controller;
import java.util.*;
import DAO.MovieGoerDAO;
import DAO.UtilDAO;
import model.Movie;
import model.MovieGoer;
import model.Review;

public class MovieGoerController {
    private ArrayList<MovieGoer> movieGoers = new ArrayList<MovieGoer>();
    private MovieGoerDAO movieGoerDao = new MovieGoerDAO();

    public MovieGoerController() {
        movieGoers = this.movieGoerDao.load();
    }

    public void save() {
        this.movieGoerDao.save(movieGoers);
    }

    public MovieGoer getMovieGoerById(int id) {
        return this.movieGoers.get(id - 1);
    }

    public int getMovieGoerIdByUsername(String username) {
        for (int i = 0; i < movieGoers.size(); i++){
            System.out.println(movieGoers.get(i).getUsername());
            if (movieGoers.get(i).getUsername().equals(username)){
                return (i + 1);
            }
        }
        return -1;
    }

    public void createNewMovieGoer(String username, String email,
            String phoneNumber, int age) {
        String BASEPATH = "src/database/User/Booking/";
        int newMovieGoerId = MovieGoer.movieGoerCount + 1;
        UtilDAO.createFile(BASEPATH + newMovieGoerId + ".csv");
        MovieGoer mg = new MovieGoer(username, phoneNumber, email, age);
        movieGoers.add(mg);
    }

    public boolean isMovieGoerExist(String username) {
        for (int i = 0; i < movieGoers.size(); i++){
            if (movieGoers.get(i).getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

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