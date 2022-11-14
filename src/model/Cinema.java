package model;
import controller.ShowtimeController;
/**
 * Represents the cinema object
 * @version 1.0
 * @since 2022-11-13
 */
public class Cinema {
    /**
     * The cineplex ID where this cinema belongs to
     */
    private int cineplexId;
    /**
     * The code of Cinema object
     */
    private int cinemaCode;
    /**
     * The height of cinema seating arrangement
     */
    private int height;
    /**
     * The width of cinema seating arrangement
     */
    private int width;

    /**
     * The class available for cinema
     */
    public enum showClassOptions {
        SILVER, GOLD, PLATINUM
    }

    /**
     * The seating configuration of cinema
     */
    private String seatConfiguration;
    /**
     * The class of cinema accoding to showClassOptioons enum
     */
    private showClassOptions cinemaClass;
    /**
     * ShowtimeController object which contains all showtimes of this cinema
     */
    private ShowtimeController showtimeController;
    /**
     * Represents code for color green, meaning unoccupied
     */
    private static final String ANSI_GREEN = "\u001b[32m";
    /**
     * Represents code for color red, meaning occupied
     */
    private static final String ANSI_RESET = "\u001B[0m";

    /**
     * Constructor for creating cinema object with the ability to store meta information regarding cinema
     * @param cineplexId The cineplex ID
     * @param code The code of cinema
     * @param height The height of seating arrangement in cinema
     * @param width The width of seating arrangement in cinema
     * @param c The class of specific cinema
     * @param seatConfiguration The seating configurations consisting multiple type of seats and non-seats such as aisle and stairs, arbitrarily interchangeable
     */
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

    /**
     * Gets cineplex ID where cinema belongs to
     * @return The cineplex ID
     */
    public int getCineplexId() { return this.cineplexId;}

    /**
     * Get the cinema code
     * @return The cinema code
     */
    public int getCinemaCode() { return this.cinemaCode;}

    /**
     * Gets the height of seating arrangement of cinema
     * @return The height of seating arrangement
     */
    public int getHeight() { return this.height;}

    /**
     * Gets the width of seating arrangement of cinema
     * @return The width of seating arrangement
     */
    public int getWidth() { return this.width;}

    /**
     * Gets the cinema show class
     * @return The cinema class with type enum showClassOptions
     */
    public showClassOptions getCinemaClass() { return this.cinemaClass;}

    /**
     * Gets the showtime controller related to this cinema, i.e. shows available in this specific cinema
     * @return The ShowtimeController object related to this cinema
     */
    public ShowtimeController getController() { return this.showtimeController;}

    /**
     * Gets seat configuration of this cinema
     * @return The spring representation of seating configuration of cinema
     */
    public String getSeatConfiguration() { return this.seatConfiguration;}

    /**
     * Displays seating arrangement of this cinema
     */
    public void displaySeatConfiguration() {
        printCinemaLayout(1, 1, "S");
    }

    /**
     * Displays seating arrangement with seats and non-seats element indicated, together with color coding
     * @param height The height of seating arrangement
     * @param width The width of seating arrangement
     * @param s The string representation of seating layout
     */
    public static void printCinemaLayout(int height, int width, String s) {
        System.out.println("Cinema Seat Configuration:");
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == 'S') {
                System.out.print(ANSI_GREEN + 'S' + ANSI_RESET);
            } else { 
                System.out.print(" ");
            }
            if ((i + 1) % width == 0)
                System.out.println();
        }
    }

    /**
     * Facilitates printing method by overriding toString method inherited from Object class
     * @return String representation of cinema
     */
    public String toString() {
        return "Cinema Code " + this.cinemaCode + " (Cineplex " + this.cineplexId + ")";
    }
}