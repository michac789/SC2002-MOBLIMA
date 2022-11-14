package DAO;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import model.Settings;

/**
 * Represents data access object related to settings configuration of price and holiday dates
 * @version 1.0
 * @since 2022-11-13
 */
public class SettingDAO extends BaseDAO {
    /**
     * File path for price configuration
     */
    private static String FILEPATH1 = "src/database/Settings/Price.csv";
    /**
     * File path for holiday configuration
     */
    private static String FILEPATH2 = "src/database/Settings/Holiday.csv";
    /**
     * DateFormat object to assist date formatting
     */
    private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Save updated state of price setting of the application to the database
     * @param silverPrice The base cost of silver class price
     * @param goldPrice The base cost of gold class price
     * @param platinumPrice The base cost of platinum class price
     * @param _3DPrice The additional cost for 3D movies
     * @param blockBusterPrice The additional cost for BlockBuster price
     * @param holidayPrice The additional cost for holiday price
     * @param childDiscount The amount of money discounted for children
     * @param seniorDiscount The amount of money discounted for seniors
     * @param afternoonDiscount The amount of money discounted for afternoon movies
     * @param midnightDiscount The amount of money discounted for midnight movies
     * @param promoDiscount The amount of money discounted from promo
     * @param promoCode The promo code
     */
    public void savePriceSettings(double silverPrice, double goldPrice, double platinumPrice, double _3DPrice,
          double blockBusterPrice, double holidayPrice, int childDiscount, int seniorDiscount, int afternoonDiscount
        , int midnightDiscount, int promoDiscount, int promoCode) {
        emptyFile(FILEPATH1);
        String header = "silverPrice,goldPrice,platinumPrice,charge3D,chargeBlockbuster,chargeHoliday" +
            "childDiscount,seniorDiscount,afternoonDiscount,midnightDiscount,promoDiscount,promoCode";
        writeLine(FILEPATH1, header);
        String writeStr = String.format("%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%d,%d,%d,%d,%d,%d",
                silverPrice, goldPrice, platinumPrice, _3DPrice, blockBusterPrice, holidayPrice, childDiscount,
                seniorDiscount, afternoonDiscount, midnightDiscount, promoDiscount, promoCode
        );
        writeLine(FILEPATH1, writeStr);
    }

    /**
     * Saves updated holiday dates state of the application to the database
     * @param dates The array of Date that represents holiday date
     */
    public void saveHolidayDates(ArrayList<Date> dates) {
        emptyFile(FILEPATH2);
        for (int i = 0; i < dates.size(); i++) {
            writeLine(FILEPATH2, dateFormat.format(dates.get(i)));
        }
    }

    /**
     * Loads dates of holiday and put it in array of date object
     */
    public void load() {
        LinkedList<String> instances = this.getData(FILEPATH1);
        String[] x = instances.get(1).split(",");
        Settings.silverPrice = Float.parseFloat(x[0]);
        Settings.goldPrice = Float.parseFloat(x[1]);
        Settings.platinumPrice = Float.parseFloat(x[2]);
        Settings.charge3D = Float.parseFloat(x[3]);
        Settings.chargeBlockbuster = Float.parseFloat(x[4]);
        Settings.chargeHoliday = Float.parseFloat(x[5]);
        
        Settings.childDiscount = Integer.parseInt(x[6]);
        Settings.seniorDiscount = Integer.parseInt(x[7]);
        Settings.afternoonDiscount = Integer.parseInt(x[8]);
        Settings.midnightDiscount = Integer.parseInt(x[9]);
        Settings.promoDiscount = Integer.parseInt(x[10]);
        Settings.promoCode = Integer.parseInt(x[11]);

        LinkedList<String> dates = this.getData(FILEPATH2);
        ArrayList<Date> holidayDates = new ArrayList<Date>();
        for (int i = 0; i < dates.size(); i++) {
            try {
                holidayDates.add(dateFormat.parse(dates.get(i)));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        Settings.holidayDates = holidayDates;
    }
}