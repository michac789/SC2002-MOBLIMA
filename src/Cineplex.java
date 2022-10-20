import java.util.*;

public class Cineplex {
    private int cineplexId;
    private String location;
    private List<Cinema> cinemas = new ArrayList<Cinema>();

    public Cineplex(int id, String location) {
        this.cineplexId = id;
        this.location = location;
    }

    // mutators
    public void setCineplexId(int id) { this.cineplexId = id;}
    public void setLocation(String location) { this.location = location;}
    // todo for cinemas

    // accessors
    public int getCineplexId() { return this.cineplexId;}
    public String getLocation() { return this.location;}
    public Cinema getCinema(int code) {
        return this.cinemas.get(code - 1);
    }

    // print basic information
    public void printCineplexInfo() {
        System.out.println("Cineplex ID: " + this.cineplexId);
        System.out.println("Cineplex Location: " + this.location);
        System.out.println("");
    }

    // print list of all cinemas
    public void printCinemasList() {
        for (int i = 0; i < this.cinemas.size(); i++) {
            this.cinemas.get(i).printCinemaInfo();
        }
    }

    // create new cinema and add it to this cineplex
    public void addCinema(int code, int height, int width) {
        Cinema newCinema = new Cinema(code, height, width);
        cinemas.add(newCinema);
    }
}
