package boundary;

import model.Settings;

public class SettingsUI {
    // static Scanner sc = new Scanner(System.in);

    public static void displayPriceSettings() {
        // change to 2 decimal place, add dollar sign TODO
        System.out.println("### Displaying price settings ###\n" +
            "Class1 Cinema Price: " + Settings.class1Price + "\n" +
            "Class2 Cinema Price: " + Settings.class2Price + "\n" +
            "Class3 Cinema Price: " + Settings.class3Price + "\n" +
            "3D Movie Extra Charge: " + Settings.charge3D + "\n" +
            "Blockbuster Movie Extra Charge: " + Settings.chargeBlockbuster + "\n" +
            "Holiday Extra Charge: " + Settings.chargeHoliday + "\n\n"
        );
    }
}
