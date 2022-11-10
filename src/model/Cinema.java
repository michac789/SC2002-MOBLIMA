package model;
import controller.ShowtimeController;

public class Cinema {
    private int cineplexId;
    private int cinemaCode;
    private int height;
    private int width;
    public enum showClassOptions {
        SILVER, GOLD, PLATINUM
    }
    private String seatConfiguration;
    private showClassOptions cinemaClass;
    private ShowtimeController showtimeController;

    private static final String ANSI_GREEN = "\u001b[32m";
    private static final String ANSI_RESET = "\u001B[0m";

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
        for(int i = 0; i<s.length(); i++){
            if(s.charAt(i) == 'S'){
                System.out.print(ANSI_GREEN + 'S' + ANSI_RESET);
            } else { 
                System.out.print(" ");
            }
            if ((i+1)%width == 0)
                System.out.println();
        }
    }

    public String toString() {
        return "Cinema Code " + this.cinemaCode + " (Cineplex " + this.cineplexId + ")";
    }
}