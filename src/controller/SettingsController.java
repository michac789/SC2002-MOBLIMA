package controller;
import DAO.SettingDAO;
import model.Settings;

public class SettingsController {
    private SettingDAO settingsDao = new SettingDAO();

    public SettingsController() {
        settingsDao.load();
    }

    public void save() {
        settingsDao.save(
            Settings.class1Price,
            Settings.class2Price,
            Settings.class3Price,
            Settings.charge3D,
            Settings.chargeBlockbuster,
            Settings.chargeHoliday
        );
    }
}