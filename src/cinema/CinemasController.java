package cinema;
import java.util.*;

public class CinemasController {
    private ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
    private CinemasDAO cinemasDao = new CinemasDAO();

    public CinemasController() {
        cinemas = this.cinemasDao.load();
    }

    public void save() {
        this.cinemasDao.save(cinemas);
    }
}
