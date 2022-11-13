package model;
import controller.CinemaController;
/**
 * Represents the cineplex object
 * @version 1.0
 * @since 2022-11-13
 */
public class Cineplex {
    /**
     * Represents the class value of the number of cineplex
     */
    private static int numCineplex = 0;
    /**
     * The unique ID of cineplex
     */
    private int cineplexId;
    /**
     * The location of cineplex
     */
    private String location;
    /**
     * The cinemaController object related to this cineplex, containing all cinemas in a specific cineplex
     */
    private CinemaController cinemaController;

    /**
     * Constructor for creating cineplex object in a specific location
     * @param location The location of cineplex
     */
    public Cineplex(String location) {
        numCineplex++;
        this.cineplexId = numCineplex;
        this.location = location;
        this.cinemaController = new CinemaController(cineplexId);
    }

    /**
     * Sets the location of cineplex
     * @param location The location of cineplex
     */
    public void setLocation(String location) { this.location = location;}

    /**
     * Gets the number of cineplexes in total
     * @return The number of cineplexes
     */
    public static int getNumCineplex() { return numCineplex;}

    /**
     * Gets the specific ID of cineplex
     * @return The cineplex ID
     */
    public int getCineplexId() { return this.cineplexId;}

    /**
     * Gets the location of cineplex
     * @return The location of cineplex
     */
    public String getLocation() { return this.location;}

    /**
     * Gets the CinemaController related to this cineplex containing all cinemas in this particular cineplex
     * @return The CinemaController object related to this cineplex
     */
    public CinemaController getController() { return this.cinemaController;}

    /**
     * Formats the printing of cineplex object consisting of its ID, location, controller related to it, and the number of cinemas
     * @return The String representation of cineplex meta information
     */
    public String toString() {
        return String.format(
            "Cineplex ID %-2d: %-20s (%d cinemas)",
            this.cineplexId, this.location,
            this.getController().getCinemasCount()
        );
    }
}