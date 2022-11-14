package model;
import java.util.ArrayList;
import java.util.Date;

public class Settings {
    /**
     * The base price of the silver class
     */
    public static float silverPrice;
    /**
     * The base price of the gold class
     */
    public static float goldPrice;
    /**
     * The base price of the platinum class
     */
    public static float platinumPrice;
    /**
     * The additional cost of 3D movies
     */
    public static float charge3D;
    /**
     * The additional cost of blockbuster movies
     */
    public static float chargeBlockbuster;
    /**
     * The additional cost of movies that show at holiday
     */
    public static float chargeHoliday;
    /**
     * The list of holiday dates
     */
    public static ArrayList<Date> holidayDates;
    /**
     * The amount of discounted price of children's tickets
     */
    public static int childDiscount;
    /**
     * The amount of discounted price of senior's tickets
     */
    public static int seniorDiscount;
    /**
     * The amount of discounted price for afternoon movies
     */
    public static int afternoonDiscount;
    /**
     * The amount of discounted price for midnight movies
     */
    public static int midnightDiscount;
    /**
     * The amount of discounted price for promo discount
     */
    public static int promoDiscount;
    /**
     * Specific promo code that can be redeemed
     */
    public static int promoCode;
}