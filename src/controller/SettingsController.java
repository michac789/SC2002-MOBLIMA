package controller;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import DAO.SettingDAO;
import model.Settings;

/**
 * Controller for all settings configuration to be edited by admin
 * @version 1.0
 * @since 2022-11-13
 */
public class SettingsController {
    /**
     * SettingDAO to get and save data to database
     */
    private SettingDAO settingsDao = new SettingDAO();
    /**
     * Date formatting in appropriate format
     */
    private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Construct settings controller object, load all settings
     */
    public SettingsController() {
        settingsDao.load();
    }

    /**
     * Save all changes made to settings, called upon terminating program
     */
    public void save() {
        settingsDao.savePriceSettings(
            Settings.silverPrice,
            Settings.goldPrice,
            Settings.platinumPrice,
            Settings.charge3D,
            Settings.chargeBlockbuster,
            Settings.chargeHoliday,

            Settings.childDiscount,
            Settings.seniorDiscount,
            Settings.afternoonDiscount,
            Settings.midnightDiscount,
            Settings.promoDiscount,
            Settings.promoCode
        );
        settingsDao.saveHolidayDates(
            Settings.holidayDates
        );
    }

    /**
     * Add new holiday date to settings
     * @param input String correspond to a date in the format dd/MM/yyyy 
     * @return 0 if changes success, 1 if input is invalid, 2 if such date already exist
     */
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

    /**
     * Remove a holiday date from settings
     * @param id Date ID from the list of all holiday dates
     * @return True if removal successful, false otherwise
     */
    public static boolean removeHoliday(int id) {
        if (1 <= id && id <= Settings.holidayDates.size()) {
            Settings.holidayDates.remove(id - 1);
            return true;
        } else {
            return false;
        }
    }
}