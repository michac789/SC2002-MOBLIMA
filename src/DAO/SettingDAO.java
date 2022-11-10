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

    public void savePriceSettings(double a, double b, double c, double d, double e, double f) {
        emptyFile(FILEPATH1);
        String header = "silverPrice,goldPrice,platinumPrice,charge3D,chargeBlockbuster,chargeHoliday";
        writeLine(FILEPATH1, header);
        String writeStr = String.format("%.2f,%.2f,%.2f,%.2f,%.2f,%.2f",
            a, b, c, d, e, f
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
        Settings.silverPrice = Double.parseDouble(x[0]);
        Settings.goldPrice = Double.parseDouble(x[1]);
        Settings.platinumPrice = Double.parseDouble(x[2]);
        Settings.charge3D = Double.parseDouble(x[3]);
        Settings.chargeBlockbuster = Double.parseDouble(x[4]);
        Settings.chargeHoliday = Double.parseDouble(x[5]);

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