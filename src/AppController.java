public class AppController {
    public static DAO dao = new DAO();

    public static MovieController mc = new MovieController();
    public AppController() {
        initControllers();
    }

    public void initControllers() {
        mc.getMovies();
    }
}
