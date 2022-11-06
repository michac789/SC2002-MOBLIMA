public class AppController {
    public static DAO dao = new DAO();

    public static MovieController mc;
    public AppController() {
    }

    public void initControllers() {
        mc = new MovieController();
        mc.getMovies();
    }
}
