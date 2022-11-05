package cineplex;
import java.util.*;

public class CineplexesController {
    private ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
    private CineplexesDAO cineplexDao = new CineplexesDAO();

    public CineplexesController() {
        cineplexes = this.cineplexDao.load();
    }

    public void save() {
        this.cineplexDao.save(cineplexes);
    }

    // TODO - add more functionality ???
}
