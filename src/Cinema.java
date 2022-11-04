/*
    'Cinema' class

    - Stores information of a cinema: code, height, width, class
    - Contain a list of different showtimes
*/

import java.util.*;

public class Cinema {
    // information of a cinema
    private int cinemaCode;
    private int height;
    private int width;
    private List<Showtime> showtimes = new ArrayList<Showtime>();

    public enum showClassOptions {
        // TODO - change these placeholders
        CLASS1, CLASS2, CLASS3
    }
    private showClassOptions cinemaClass;

    // constructor
    public Cinema(int code, int height, int width, showClassOptions c) {
        this.cinemaCode = code;
        this.height = height;
        this.width = width;
        this.cinemaClass = c;
    }

    // mutators & accessors
    // I do not set this yet, bcoz in the pdf just need to
    // create/update/remove cinema showtimes and movies to be shown
    // Not sure if we can allow cinema height/width/class to be changed
    // because it will affect all showtimes class and seats inside it

    // add new showtime to a cinema
    // Would like to move this out of Cinema to ShowtimeController

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
