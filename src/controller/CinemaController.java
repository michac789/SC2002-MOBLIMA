package controller;
import java.util.*;
import DAO.CinemaDAO;
import model.Cinema;

public class CinemaController {
    private ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
    private CinemaDAO cinemaDao = new CinemaDAO();
    private int cinemasCount = 0;
    private int cineplexId;
    
    public CinemaController(int cineplexId) {
        this.cineplexId = cineplexId;
        this.cinemasCount++;
        cinemas = this.cinemaDao.load(cineplexId);
    }

    public void save() {
        this.cinemaDao.save(cinemas, cineplexId);
    }

    public int getCinemasCount() { return this.cinemasCount;}

    public void create() {
        
    }
    public boolean isMovieExist(int movieId) {
        for(int i = 0; i<cinemas.size(); i++){
            Cinema cinema = cinemas.get(i);
            ShowtimeController showtimeController = cinema.getShowtimeController();
            if(showtimeController.isMovieExist(movieId)){
                return true;
            };
        }

        return false;
    }
//    public void edit() {
//
//    }
//
    public void displayCinemaByMovie(int movieId){
        for(int i = 0; i < cinemas.size(); i++) {
            Cinema cinema = cinemas.get(i);
            ShowtimeController showtimeController = cinema.getShowtimeController();
            if(showtimeController.isMovieExist(movieId)){
                System.out.println("Cinema " + cinema.getCinemaCode());
            }

        }

    }


}