package controller;

public class AppController {
    public static MovieController mc;
    public static CineplexController cc;
    public static MovieGoerController mgc;
    public static SettingsController sc;

    // initialize all objects from the database, called upon loading
    public static void init() {
        mc = new MovieController(); // load all movies  -> load all reviews corresponding to each movie
        cc = new CineplexController(); // cineplex -> cinema -> showtime -> seats
        mgc = new MovieGoerController(); // user -> bookings
        sc = new SettingsController();
    }

    // overwrite all changes to database, called upon terminating program
    public static void save() {
        mc.save();
        cc.save();
        sc.save();
        mgc.save();
    }
}