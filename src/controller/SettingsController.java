package controller;
import java.util.Scanner;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import DAO.SettingDAO;
import model.Settings;

public class SettingsController {
    private SettingDAO settingsDao = new SettingDAO();
    private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public SettingsController() {
        settingsDao.load();
    }

    public void save() {
        settingsDao.savePriceSettings(
            Settings.class1Price,
            Settings.class2Price,
            Settings.class3Price,
            Settings.charge3D,
            Settings.chargeBlockbuster,
            Settings.chargeHoliday
        );
        settingsDao.saveHolidayDates(
            Settings.holidayDates
        );
    }

    public static int addHoliday(String input) {
        Date date;
        try {
            date = dateFormat.parse(input);
        } catch (ParseException e) {
            return 1;
        }
        for (int i = 0; i < Settings.holidayDates.size(); i++) {
            if (date.equals(Settings.holidayDates.get(i))) {
                return 2;
            }
        }
        Settings.holidayDates.add(date);
        return 0;
    }

    public static boolean removeHoliday(int id) {
        if (1 <= id && id <= Settings.holidayDates.size()) {
            Settings.holidayDates.remove(id - 1);
            return true;
        } else {
            return false;
        }
    }
}