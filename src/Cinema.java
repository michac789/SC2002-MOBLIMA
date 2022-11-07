public class Cinema {
    // private List<Showtime> showtimes = new ArrayList<Showtime>();

    private int cinemaCode;
    private int height;
    private int width;
    public enum showClassOptions {
        CLASS1, CLASS2, CLASS3 // TODO
    }
    private showClassOptions cinemaClass;

    // constructor
    public Cinema(int code, int height, int width, showClassOptions c) {
        this.cinemaCode = code;
        this.height = height;
        this.width = width;
        this.cinemaClass = c;
    }

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
