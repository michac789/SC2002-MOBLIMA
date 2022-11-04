package cineplex;
/*
    'Cineplex' class

    - Stores information about a cineplex
    - Contain a list of cinemas in the cineplex
*/

import java.util.*;

// import Cinema;
// import Cinema.showClassOptions;

public class Cineplex {
    // counter to keep track of total cineplex and auto assign ID
    static int numCineplex = 0;

    // information about a cineplex: id, location, cinemas list
    private int cineplexId;
    private String location;
    // private List<Cinema> cinemas = new ArrayList<Cinema>();

    // keep track of cinema count, so the id can be automatically given
    private int cinemaCount = 0;

    // constructor
    public Cineplex(String location) {
        numCineplex++;
        this.cineplexId = numCineplex;
        this.location = location;
    }

    // mutators
    public void setLocation(String location) { this.location = location;}

    // accessors
    public int getCineplexId() { return this.cineplexId;}
    public String getLocation() { return this.location;}
    
    // public Cinema getCinema(int code) {
    //     return this.cinemas.get(code - 1);
    // }

    // create new cinema and add it to this cineplex
    // public void addCinema(int height, int width, Cinema.showClassOptions c) {
    //     this.cinemaCount++;
    //     Cinema newCinema = new Cinema(this.cinemaCount, height, width, c);
    //     cinemas.add(newCinema);
    // }

    // print basic information
    public void printCineplexInfo() {
        System.out.println("Cineplex ID: " + this.cineplexId);
        System.out.println("Cineplex Location: " + this.location);
        System.out.println("");
    }

    // print list of all cinemas
    public void printCinemasList() {
        System.out.println("Cineplex ID " + this.cineplexId);
        System.out.println("Showing " + this.cinemaCount + " cinemas:\n");
        // for (int i = 0; i < this.cinemas.size(); i++) {
        //     this.cinemas.get(i).printCinemaInfo();
        // }
    }

    public void displayAllShowtimes(int id) {
        // TODO
    }
}
