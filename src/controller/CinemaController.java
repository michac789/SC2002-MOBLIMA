package controller;
import java.util.*;
import DAO.DAO;
import DAO.CinemaDAO;
import model.Cinema;
import model.Cinema.showClassOptions;

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

    public ArrayList<Cinema> getAllCinemas() {
        return this.cinemas;
    }

    public Cinema getCinemaByCode(int code) {
        return this.cinemas.get(code - 1);
    }

    public int getCinemasCount() {
        return this.cinemasCount;
    }

    public void displayAllCinemas() {
        System.out.println("Showing all cinemas in this cineplex:");
        for (int i = 0; i < this.cinemas.size(); i++) {
            System.out.println(this.cinemas.get(i));
        }
        System.out.println("");
    }

    public void createCinema(int height, int width, showClassOptions c, String s) {
        String BASEPATH = "src/database/Cineplex/" + cineplexId + "/Showtime_";
        int newCode = this.cinemas.size() + 1;
        DAO.createFile(BASEPATH + newCode + ".csv");
        Cinema cinema = new Cinema(cineplexId, newCode, height, width, c, s);
        this.cinemas.add(cinema);
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