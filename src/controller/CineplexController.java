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

    public void save() {
        this.cineplexDao.save(cineplexes);
    }
}