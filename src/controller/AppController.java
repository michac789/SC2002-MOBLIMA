package controller;

public class AppController {
    public static MovieController mc;
    public static CineplexController cc;
    public static SettingsController sc;

    // initialize all objects from the database, called upon loading
    public static void init() {
        mc = new MovieController();
        cc = new CineplexController();
        sc = new SettingsController();
    }

    public void initControllers() {
        // mc.getMovies();
    }

    // overwrite all changes to database, called upon terminating program
    public static void save() {
        mc.save();
        cc.save();
        sc.save();
    }
}