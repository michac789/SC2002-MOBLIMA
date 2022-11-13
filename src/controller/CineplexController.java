package controller;
import java.util.ArrayList;
import DAO.CineplexDAO;
import model.Cineplex;

/**
 * Controller for all Cineplex objects
 * @version 1.0
 * @since 2022-11-13
 */
public class CineplexController {
    /**
     * Contains an arraylist of all cineplexes
     */
    private ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
    /**
     * CineplexDAO to get and save data to database
     */
    private CineplexDAO cineplexDao = new CineplexDAO();

    /**
     * Construct to create this object, load all cineplexes,
     * trigger the loading of cinemas, showtimes and seats objects inside
     */
    public CineplexController() {
        cineplexes = this.cineplexDao.load();
    }
    
    /**
     * Save method to save all cineplex changes,
     * call the save method of all cinemas controller objects
     */
    public void save() {
        this.cineplexDao.save(cineplexes);
    }

    /**
     * Get a list of all cineplexes objects
     * @return Arraylist of all cineplexes
     */
    public ArrayList<Cineplex> getAllCineplexes() {
        return this.cineplexes;
    }

    /**
     * Get a cineplex object by its ID
     * @param id CineplexID to be retrieved
     * @return Cineplex object that has that CineplexID
     */
    public Cineplex getCineplexById(int id) {
        return this.cineplexes.get(id - 1);
    }

    /**
     * Create new cineplex object and append to cineplexes list,
     * make necessary folder and file creation in the database
     * @param location Location of newly created cineplex
     */
    public void createCineplex(String location) {
        int newCineplexId = Cineplex.getNumCineplex() + 1;
        cineplexDao.createCineplexFolder(newCineplexId, location);

        Cineplex newCineplex = new Cineplex(location);
        this.cineplexes.add(newCineplex);
    }

    /**
     * Edit the location of a particular cineplex
     * @param cineplexId CineplexID to be edited
     * @param newLocation New location to be updated to Cineplex object
     */
    public void editLocation(int cineplexId, String newLocation) {
        this.cineplexes.get(cineplexId - 1).setLocation(newLocation);
    }

    /**
     * Display all cineplexes that show a particular movie
     * @param movieId MovieID to be watched
     * @return ArrayList of all CineplexID that displays a particular movieID
     */
    public ArrayList<Integer> displayCineplexesByMovieId(int movieId) {
        ArrayList<Integer> validIds = new ArrayList<Integer>();
        int count = 0;
        for (int i = 0; i < cineplexes.size(); i++) {
            Cineplex cineplex = cineplexes.get(i);
            CinemaController cinemaController = cineplexes.get(i).getController();
            if (cinemaController.isMovieExist(movieId)) {
                validIds.add(i + 1);
                System.out.println(cineplex);
                count++;
            }
        }
        if (count == 0){
            System.out.println("No cinema are currently showing this movie");
        }
        return validIds;
    }
}