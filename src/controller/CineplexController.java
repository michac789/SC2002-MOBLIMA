package controller;
import java.io.File;
import java.util.ArrayList;
import DAO.CineplexDAO;
import model.Cineplex;

public class CineplexController {
    private ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
    private CineplexDAO cineplexDao = new CineplexDAO();

    public CineplexController() {
        cineplexes = this.cineplexDao.load();
    }

    public void create(String location) {
        System.out.println("CREATEEE");
        int id = Cineplex.getNumCineplex() + 1;
        new Cineplex(location);
        String PATH = "src/database/Cineplex/" + id;
        new File(PATH).mkdirs();
        new File(PATH + id + "/Cinemas.java");
    }

    public void displayCineplexesByMovieId (int movieId){
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

    public void save() {
        this.cineplexDao.save(cineplexes);
    }
}