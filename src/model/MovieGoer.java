package model;
import controller.BookingController;

/**
 * Represents the moviegoer object
 * @version 1.0
 * @since 2022-11-13
 */
public class MovieGoer extends User {
    /**
     * The category of moviegoer based on age
     */
    public enum Category {
        ADULT,
        SENIOR_CITIZEN,
        CHILD
    }

    /**
     * The total number of moviegoer shared all over every moviegoer object (class variable)
     */
    public static int movieGoerCount = 0;
    /**
     * The unique ID related to the movigoer
     */
    private int id;
    /**
     * The email of the moviegoer
     */
    private String email;
    /**
     * The phone number of moviegoer
     */
    private String phoneNumber;
    /**
     * The age of moviegoer
     */
    private int age;
    /**
     * The booking controller related to the moviegoer containing all past booking of her/him
     */
    private BookingController bookingController;

    /**
     * Constructor for making MovieGoer
     * @param username The username of moviegoer
     * @param password The password of moviegoer
     * @param phoneNumber The phone number of moviegoer
     * @param email The email of moviegoer
     * @param age The age of moviegoer
     */
    public MovieGoer(String username, String password, String phoneNumber, String email, int age){ 
        super(username, password);
        setType(UserType.MOVIEGOER);
        this.id = ++movieGoerCount;
        this.phoneNumber = phoneNumber; 
        this.email = email;
        this.age = age;
        this.bookingController = new BookingController(id);
    }

    /**
     * Gets the ID of moviegoer
     * @return The ID of moviegoer
     */
    public int getId() { return this.id;}

    /**
     * Gets the email of moviegoer
     * @return The email of moviegoer
     */
    public String getEmail() { return this.email;}

    /**
     * Gets the phone number of moviegoer
     * @return The phone number of moviegoer
     */
    public String getPhoneNumber() { return this.phoneNumber;}

    /**
     * Gets the age of moviegoer
     * @return The age of moviegoer
     */
    public int getAge() { return this.age;}

    /**
     * Gets the category of moviegoer
     * @return The variable of type enum Category of moviegoer's age category
     */
    public Category getAgeCategory() {
        return (this.age <= 19 ? Category.CHILD :
            (this.age <= 59 ? Category.ADULT : Category.SENIOR_CITIZEN)
        );
    }

    /**
     * Gets the BookingController object related to moviegoer
     * @return BookingController object consisting of previously made booking
     */
    public BookingController getController() { return this.bookingController;}
}