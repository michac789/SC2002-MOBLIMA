public class Cineplex {
    static int numCineplex = 0;

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
    // public Cinema getCinema(int code) {
    //     return this.cinemas.get(code - 1);
    // }

    // create new cinema and add it to this cineplex
    // public void addCinema(int height, int width, Cinema.showClassOptions c) {
    //     numCineplex++;
    //     Cinema newCinema = new Cinema(
    //         this.cinemaCount, height, width, c
    //     );
    //     cinemas.add(newCinema);
    // }

    // print basic information
    // public void printCineplexInfo() {
    //     System.out.println("Cineplex ID: " + this.cineplexId);
    //     System.out.println("Cineplex Location: " + this.location);
    //     System.out.println("");
    // }

    // print list of all cinemas
    // public void printCinemasList() {
    //     System.out.println("Cineplex ID " + this.cineplexId);
    //     System.out.println("Showing " + this.cinemaCount + " cinemas:\n");
    //     for (int i = 0; i < this.cinemas.size(); i++) {
    //         this.cinemas.get(i).printCinemaInfo();
    //     }
    // }

    public void displayAllShowtimes(int id) {
        // TODO
    }
}