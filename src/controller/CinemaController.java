package controller;
import java.util.*;
import DAO.CinemaDAO;
import model.Cinema;
import model.Cinema.showClassOptions;
import model.Showtime;

/**
 * Controller for all cinemas and its showtimes and seats,
 * contained inside of a cineplex object
 * @version 1.0
 * @since 2022-11-13
 */
public class CinemaController {
    /**
     * Contains an arraylist of all cinemas objects in a particular cineplex
     */
    private ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
    /**
     * CinemaDAO to get and save data to database
     */
    private CinemaDAO cinemaDao = new CinemaDAO();
    /**
     * CineplexID of this particular cinemaController object
     */
    private int cineplexId;
    
    /**
     * Constructor to create Cinema Controller
     * Automatically load all cinemas from database,
     * trigger the loading of all showtimes and seats inside of all cinemas
     * @param cineplexId ID of cineplex associated with this controller
     */
    public CinemaController(int cineplexId) {
        this.cineplexId = cineplexId;
        cinemas = this.cinemaDao.load(cineplexId);
    }

    /**
     * Save method called to save all cinemas object changes,
     * trigger the save method of showtimeController of all cinemas
     */
    public void save() {
        this.cinemaDao.save(cinemas, cineplexId);
    }

    /**
     * Get all cinemas of this particular cineplex that has this controller
     * @return ArrayList of all cinemas objects
     */
    public ArrayList<Cinema> getAllCinemas() {
        return this.cinemas;
    }

    /**
     * Get cinema object by its code
     * @param code Cinema code (1, 2, 3, ...)
     * @return Cinema object that has the corresponding code
     */
    public Cinema getCinemaByCode(int code) {
        return this.cinemas.get(code - 1);
    }

    /**
     * Get number of cinemas associated in this controller
     * @return Number of cinemas
     */
    public int getCinemasCount() {
        return this.cinemas.size();
    }

    /**
     * Display all cinemas information contain in this controller
     */
    public void displayAllCinemas() {
        System.out.println("Showing all cinemas in this cineplex:");
        for (int i = 0; i < this.cinemas.size(); i++) {
            System.out.println(this.cinemas.get(i));
        }
        System.out.println("");
    }

    /**
     * Pass information for a particular cinema,
     * create new cinema object and add to current cinemas list,
     * make necessary file creation to the database
     * @param height Height of seat array of this cinema
     * @param width Width of seat array of this cinema
     * @param c Cineplex ID corresponding to this cinema
     * @param s Seat configuration string, should have a length of
     * (height x width) and only contains 'S' and 'X'
     */
    public void createCinema(int height, int width, showClassOptions c, String s) {
        int newCode = this.cinemas.size() + 1;
        cinemaDao.createCinemaShowtimeFile(this.cineplexId, newCode);

        Cinema cinema = new Cinema(cineplexId, newCode, height, width, c, s);
        this.cinemas.add(cinema);
    }

    /**
     * Check whether a particular movieId exists or not
     * @param movieId ID of a movie
     * @return True if a movie object with this ID exist, otherwise false
     */
    public boolean isMovieExist(int movieId) {
        for (int i = 0; i<cinemas.size(); i++) {
            ShowtimeController showtimeController = cinemas.get(i).getController();
            if (showtimeController.isMovieExist(movieId)) {
                return true;
            };
        }
        return false;
    }
    
    /**
     * Display all cinemas and its corresponding showtime that shows
     * a particular movie
     * @param movieId The ID of a movie which the user wants to watch
     * @return ArrayList of all valid CinemaID (that shows this movieId)
     */
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