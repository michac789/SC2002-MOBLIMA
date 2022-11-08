package DAO;
import java.util.LinkedList;
import model.Settings;

public class SettingDAO extends BaseDAO {
    String FILEPATH = "src/database/Settings/Price.csv";

    public void save(double a, double b, double c, double d, double e, double f) {
        System.out.println("TEST");
        emptyFile(FILEPATH);
        String header = "class1Price,class2Price,class3Price,charge3D,chargeBlockbuster,chargeHoliday";
        writeLine(FILEPATH, header);
        String writeStr = String.format("%d,%d,%d,%d,%d,%d",
            a, b, c, d, e, f
        );
        writeLine(FILEPATH, writeStr);
    }
    
    public void load() {
        LinkedList<String> instances = this.getData(FILEPATH);
        String[] x = instances.get(1).split(",");
        Settings.class1Price = Double.parseDouble(x[0]);
        Settings.class2Price = Double.parseDouble(x[1]);
        Settings.class3Price = Double.parseDouble(x[2]);
        Settings.charge3D = Double.parseDouble(x[3]);
        Settings.chargeBlockbuster = Double.parseDouble(x[4]);
        Settings.chargeHoliday = Double.parseDouble(x[5]);
    }
}