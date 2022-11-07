import java.util.*;

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

    public void edit() {
        
    }
}