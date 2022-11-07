public class AppController {
    public static DAO dao = new DAO();
    public static MovieController mc = new MovieController();
    public static CineplexController cc = new CineplexController();
    
    public AppController() {
        initControllers();
    }

    public void initControllers() {
        // mc.getMovies();
    }
}
