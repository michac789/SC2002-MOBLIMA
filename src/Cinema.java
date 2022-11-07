public class Cinema {
    static int numCinema = 0;

    // information of a cinema
    private int cinemaId;
    private int fkCineplex;
    private int cinemaCode;
    private int height;
    private int width;
    // private List<Showtime> showtimes = new ArrayList<Showtime>();

    public enum showClassOptions {
        // TODO - change these placeholders
        CLASS1, CLASS2, CLASS3
    }
    private showClassOptions cinemaClass;

    // constructor
    public Cinema(int fkCineplex, int code, int height, int width, showClassOptions c) {
        numCinema++;
        this.cinemaId = numCinema;
        this.fkCineplex = fkCineplex;
        this.cinemaCode = code;
        this.height = height;
        this.width = width;
        this.cinemaClass = c;
    }

    public int getCinemaId() { return this.cinemaId;}
    public int getfkCineplex() { return this.fkCineplex;}
    public int getCinemaCode() { return this.cinemaCode;}
    public int getHeight() { return this.height;}
    public int getWidth() { return this.width;}
    public showClassOptions getCinemaClass() { return this.cinemaClass;}

    // add new showtime to a cinema
    // get all showtimes
    // TODO

    // edit a showtime
    // TODO

    // remove a showtime
    // TODO
    
    // display cinema basic information
    public void printCinemaInfo() {
        System.out.println("Cinema Code: " + this.cinemaCode);
        System.out.println("Height: " + this.height);
        System.out.println("Width: " + this.width);
        System.out.println("Class: " + this.cinemaClass);
        System.out.println("");
    }
}
