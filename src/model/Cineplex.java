package model;
import controller.CinemaController;

public class Cineplex {
    private static int numCineplex = 0;

    private int cineplexId;
    private String location;
    private CinemaController cinemaController;

    public Cineplex(String location) {
        numCineplex++;
        this.cineplexId = numCineplex;
        this.location = location;
        this.cinemaController = new CinemaController(cineplexId);
    }

    public void setLocation(String location) { this.location = location;}

    public int getCineplexId() { return this.cineplexId;}
    public String getLocation() { return this.location;}
    public CinemaController getController() { return this.cinemaController;}
}