package controller;

/**
 * AppController to store other controllers on the topmost level,
 * to initiate loading of all objects from the database,
 * initiate saving of all objects to the database when terminating the program
 * @version 1.0
 * @since 2022-11-13
 */
public class AppController {
    /**
     * MovieController that controls all movies and its reviews
     */
    public static MovieController mc;
    /**
     * CineplexController that controls all cineplexes,
     * including all cinemas, showtimes and seats inside it
     */
    public static CineplexController cc;
    /**
     * MovieGoerController that controls all MovieGoers object
     * and user reviews
     */
    public static MovieGoerController mgc;
    /**
     * SettingsController that controls admin configuration setup
     */
    public static SettingsController sc;

    /**
     * Constructor of AppController,
     * initialize all objects from the database, called upon loading
     */
    public static void init() {
        mc = new MovieController(); // load all movies  -> load all reviews corresponding to each movie
        cc = new CineplexController(); // cineplex -> cinema -> showtime -> seats
        mgc = new MovieGoerController(); // user -> bookings
        sc = new SettingsController();
    }
    
    /**
     * Save method to overwrite all changes to database,
     * called upon terminating program
     */
    public static void save() {
        mc.save();
        cc.save();
        sc.save();
        mgc.save();
    }
}