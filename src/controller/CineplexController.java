package controller;
import java.util.ArrayList;
import DAO.CineplexDAO;
import DAO.DAO;
import model.Cineplex;

public class CineplexController {
    private ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
    private CineplexDAO cineplexDao = new CineplexDAO();

    public CineplexController() {
        cineplexes = this.cineplexDao.load();
    }
    
    public void save() {
        this.cineplexDao.save(cineplexes);
    }

    public ArrayList<Cineplex> getAllCineplexes() {
        return this.cineplexes;
    }

    public Cineplex getCineplexById(int id) {
        return this.cineplexes.get(id - 1);
    }

    public void createCineplex(String location) {
        String BASEPATH = "database/Cineplex/";
        int newCineplexId = Cineplex.getNumCineplex() + 1;
        DAO.createFolder(BASEPATH + newCineplexId);
        DAO.createFile(BASEPATH + newCineplexId + "/Cinemas.csv");
        DAO.writeFile(
            BASEPATH + "Cineplexes.csv",
            String.format("%d,%s", newCineplexId, location),
            true
        );
        Cineplex newCineplex = new Cineplex(location);
        this.cineplexes.add(newCineplex);
    }

    public void editLocation(int cineplexId, String newLocation) {
        this.cineplexes.get(cineplexId - 1).setLocation(newLocation);
    }

    public void displayCineplexesByMovieId(int movieId) {
        int count = 0;
        for(int i = 0; i < cineplexes.size(); i++){
            Cineplex cineplex = cineplexes.get(i);
            CinemaController cinemaController = new CinemaController(cineplex.getCineplexId());
            if(cinemaController.isMovieExist(movieId)){
                System.out.println("Cineplex: " + cineplex.getLocation());
                count++;
            }
        }
        if (count == 0){
            System.out.println("No cinema are currently showing this movie");
        }
    }

    public int getCineplexIdByLocation(String location){
        for (int i = 0; i <cineplexes.size() ; i++) {
            Cineplex cineplex = cineplexes.get(i);
            if (cineplex.getLocation().equals(location)) {
                return cineplex.getCineplexId();
            }

        }
        return -1;
    }
}