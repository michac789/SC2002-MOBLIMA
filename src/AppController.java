import DAO.DAO;
import controller.CineplexController;
import controller.MovieController;


public class AppController {
    // public static DAO dao = new DAO();
    // public static MovieController mc = new MovieController();
    // public static CineplexController cc = new CineplexController();

    private MovieController mc;
    private CineplexController cc;
    
    public AppController() {
        mc = new MovieController();
        cc = new CineplexController();
    }

    public void initControllers() {
        // mc.getMovies();
    }
}
