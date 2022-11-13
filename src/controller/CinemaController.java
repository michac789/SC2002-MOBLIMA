package controller;
import java.util.*;
import DAO.UtilDAO;
import DAO.CinemaDAO;
import model.Cinema;
import model.Cinema.showClassOptions;
import model.Showtime;

public class CinemaController {
    private ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
    private CinemaDAO cinemaDao = new CinemaDAO();
    private int cineplexId;
    
    public CinemaController(int cineplexId) {
        this.cineplexId = cineplexId;
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
        return this.cinemas.size();
    }

    public void displayAllCinemas() {
        System.out.println("Showing all cinemas in this cineplex:");
        for (int i = 0; i < this.cinemas.size(); i++) {
            System.out.println(this.cinemas.get(i));
        }
        System.out.println("");
    }

    public void createCinema(int height, int width, showClassOptions c, String s) {
        int newCode = this.cinemas.size() + 1;
        cinemaDao.createCinemaShowtimeFile(this.cineplexId, newCode);

        Cinema cinema = new Cinema(cineplexId, newCode, height, width, c, s);
        this.cinemas.add(cinema);
    }

    public boolean isMovieExist(int movieId) {
        for (int i = 0; i<cinemas.size(); i++) {
            ShowtimeController showtimeController = cinemas.get(i).getController();
            if (showtimeController.isMovieExist(movieId)) {
                return true;
            };
        }
        return false;
    }
    
    public ArrayList<Integer> displayCinemaAndShowtimeByMovieId(int movieId){
        ArrayList<Integer> validIds = new ArrayList<Integer>();
        for (int i = 0; i < cinemas.size(); i++) {
            ArrayList<Showtime> showtimes = cinemas.get(i).getController().getAllShowtimes();
            boolean temp = true;
            for (int j = 0; j < showtimes.size(); j++) {
                if (showtimes.get(j).getMovieId() == movieId) {
                    if (temp) {
                        System.out.println(cinemas.get(i));
                        validIds.add(i + 1);
                        temp = false;
                    }
                    System.out.println(showtimes.get(j));
                }
            }
        }
        return validIds;
    }
}