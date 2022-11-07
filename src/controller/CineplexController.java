package controller;
import java.util.*;
import DAO.CineplexDAO;
import model.Cineplex;

public class CineplexController {
    private ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
    private CineplexDAO cineplexDao = new CineplexDAO();

    public CineplexController() {
        cineplexes = this.cineplexDao.load();
    }


    public void displayCineplexesByMovieId (int movieId){
        int count = 0;
        for(int i = 0; i<cineplexes.size(); i++){
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
        for(int i = 0; i <cineplexes.size() ; i++){
            Cineplex cineplex = cineplexes.get(i);
            if(cineplex.getLocation().equals(location)){
                return cineplex.getCineplexId();
            }

        }

        return -1;
    }
    public void save() {
        this.cineplexDao.save(cineplexes);
    }
}