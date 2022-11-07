import java.util.*;

public class CinemaController {
    private ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
    private CinemaDAO cinemasDao = new CinemaDAO();
    private int cineplexId;
    
    public CinemaController(int cineplexId) {
        this.cineplexId = cineplexId;
        cinemas = this.cinemasDao.load(cineplexId);
    }

    public void save() {
        this.cinemasDao.save(cinemas, cineplexId);
    }
}