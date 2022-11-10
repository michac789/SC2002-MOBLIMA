package controller;

public class AppController {
    public static MovieController mc;
    public static CineplexController cc;
    public static MovieGoerController mgc;
    // booking controller (ticket) - TODO
    public static SettingsController sc;

    // initialize all objects from the database, called upon loading
    public static void init() {
        mc = new MovieController();
        cc = new CineplexController();
        mgc = new MovieGoerController();
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