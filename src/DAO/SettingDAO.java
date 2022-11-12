package DAO;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import model.Settings;

public class SettingDAO extends BaseDAO {
    private static String FILEPATH1 = "src/database/Settings/Price.csv";
    private static String FILEPATH2 = "src/database/Settings/Holiday.csv";
    private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void savePriceSettings(double a, double b, double c, double d, double e, double f,
            int g, int h, int i, int j, int k, int l) {
        emptyFile(FILEPATH1);
        String header = "silverPrice,goldPrice,platinumPrice,charge3D,chargeBlockbuster,chargeHoliday" +
            "childDiscount,seniorDiscount,afternoonDiscount,midnightDiscount,promoDiscount,promoCode";
        writeLine(FILEPATH1, header);
        String writeStr = String.format("%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%d,%d,%d,%d,%d,%d",
            a, b, c, d, e, f, g, h, i, j, k, l
        );
        writeLine(FILEPATH1, writeStr);
    }

    public void saveHolidayDates(ArrayList<Date> dates) {
        emptyFile(FILEPATH2);
        for (int i = 0; i < dates.size(); i++) {
            writeLine(FILEPATH2, dateFormat.format(dates.get(i)));
        }
    }
    
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