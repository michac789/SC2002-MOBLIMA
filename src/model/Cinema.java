package model;
import controller.ShowtimeController;

public class Cinema {
    private int cineplexId;
    private int cinemaCode;
    private int height;
    private int width;
    public enum showClassOptions {
        CLASS1, CLASS2, CLASS3
    }
    private String seatConfiguration;
    private showClassOptions cinemaClass;
    private ShowtimeController showtimeController;

    public Cinema(int cineplexId, int code, int height, int width,
            showClassOptions c, String seatConfiguration) {
        this.cineplexId = cineplexId;
        this.cinemaCode = code;
        this.height = height;
        this.width = width;
        this.cinemaClass = c;
        this.seatConfiguration = seatConfiguration;
        this.showtimeController = new ShowtimeController(cineplexId, cinemaCode, height, width);
    }

    public ShowtimeController getShowtimeController() {
        return showtimeController;
    }

    public int getCineplexId() { return this.cineplexId;}
    public int getCinemaCode() { return this.cinemaCode;}
    public int getHeight() { return this.height;}
    public int getWidth() { return this.width;}
    public showClassOptions getCinemaClass() { return this.cinemaClass;}
    public ShowtimeController getController() { return this.showtimeController;}
    public String getSeatConfiguration() { return this.seatConfiguration;}

    public void displaySeatConfiguration() {
        printCinemaLayout(1, 1, "S");
    }

    public static void printCinemaLayout(int height, int width, String s) {
        System.out.println("TODOOOOO");
        // TODO
    }

    public String toString() {
        return "Cinema Code " + this.cinemaCode + " (Cineplex " + this.cineplexId + ")";
    }
}