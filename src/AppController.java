public class AppController {
    public static DAO dao = new DAO();

    public static MovieController mc;
    public AppController() {
    }

    public void initControllers() {
        mc = new MovieController();
        mc.getMovies();
    }

    public void mcDisplayAllMovies() {
        mc.displayAllMovies();
    }

    public int mcSearchMovie(String title) {
        return mc.searchMovie(title);
    }

    public void mcRankMovieBySales(int num) {
        mc.rankMovieBySales(num);
    }

    public void mcRankMovieByRating() {
        mc.rankMovieByRating();
    }
}
