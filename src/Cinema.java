public class Cinema {
    private int cineplexId;
    private int cinemaCode;
    private int height;
    private int width;
    public enum showClassOptions {
        CLASS1, CLASS2, CLASS3
    }
    private showClassOptions cinemaClass;
    private ShowtimeController showtimeController;

    public Cinema(int cineplexId, int code, int height, int width, showClassOptions c) {
        this.cineplexId = cineplexId;
        this.cinemaCode = code;
        this.height = height;
        this.width = width;
        this.cinemaClass = c;
        this.showtimeController = new ShowtimeController(cineplexId, cinemaCode);
    }

    public int getCineplexId() { return this.cineplexId;}
    public int getCinemaCode() { return this.cinemaCode;}
    public int getHeight() { return this.height;}
    public int getWidth() { return this.width;}
    public showClassOptions getCinemaClass() { return this.cinemaClass;}
    public ShowtimeController getController() { return this.showtimeController;}

    public void printCinemaInfo() {
        System.out.println("Cinema Code: " + this.cinemaCode);
        System.out.println("Height: " + this.height);
        System.out.println("Width: " + this.width);
        System.out.println("Class: " + this.cinemaClass);
        System.out.println("");
    }
}