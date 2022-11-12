package boundary;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import controller.SettingsController;
import model.Settings;

public class SettingsUI {
    private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void admin() {
        while (true) {
            System.out.println("|=========|Settings Configuration Admin Panel|=========|");
            System.out.print(
                "1. Display Price Settings\n" +
                "2. Display Holiday Dates\n" +
                "3. Edit Price Settings\n" +
                "4. Edit Holiday Dates\n" +
                "5. Exit\n\n");
            int choice = UtilUI.getInt("Select action: ");
            switch (choice) { 
                case 1:
                    displayPriceSettings();
                    break;
                case 2:
                    displayHolidayDates();
                    break;
                case 3:
                    editPriceSettings();
                    break;
                case 4:
                    editHolidayDates();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid action, try again!");
                    break;
            }
        }
    }

    private static void displayPriceSettings() {
        System.out.println("### Displaying Price Settings ###\n" +
            String.format(
                "Silver Class Cinema Price: $%.2f\n" +
                "Gold Class Cinema Price: $%.2f\n" +
                "Platinum Class Cinema Price: $%.2f\n" +
                "3D Movie Extra Charge: $%.2f\n" +
                "Blockbuster Movie Extra Charge: $%.2f\n" +
                "Holiday Extra Charge: $%.2f\n\n",
                Settings.silverPrice, Settings.goldPrice,
                Settings.platinumPrice, Settings.charge3D,
                Settings.chargeBlockbuster, Settings.chargeHoliday
            )
        );
    }

    private static void displayHolidayDates() {
        System.out.println("### Displaying Holiday Dates ###");
        for (int i = 0; i < Settings.holidayDates.size(); i++) {
            System.out.println(dateFormat.format(Settings.holidayDates.get(i)));
        }
        System.out.println("");
    }

    private static void editPriceSettings() {
        while (true) {
            System.out.println("### Edit Price Configuration ###");
            System.out.print(
                "1. Edit Silver Class Price\n" +
                "2. Edit Gold Class Price\n" +
                "3. Edit Platinum Price\n" +
                "4. Edit 3D Movie Extra Charge\n" +
                "5. Edit Blockbuster Movie Extra Charge\n" +
                "6. Edit Holiday Extra Charge\n" +
                "7. Exit\n\n");
            int choice = UtilUI.getInt("Select action: ");
            float newPrice;
            switch (choice) { 
                case 1:
                    System.out.println(String.format(
                        "Current Class1 Price: $%.2f",
                        Settings.silverPrice
                    ));
                    newPrice = UtilUI.getPositiveFloat("Enter new price: ");
                    Settings.silverPrice = newPrice;
                    break;
                case 2:
                    System.out.println(String.format(
                        "Current Class2 Price: $%.2f",
                        Settings.goldPrice
                    ));
                    newPrice = UtilUI.getPositiveFloat("Enter new price: ");
                    Settings.goldPrice = newPrice;
                    break;
                case 3:
                    System.out.println(String.format(
                        "Current Class3 Price: $%.2f",
                        Settings.platinumPrice
                    ));
                    newPrice = UtilUI.getPositiveFloat("Enter new price: ");
                    Settings.platinumPrice = newPrice;
                    break;
                case 4:
                    System.out.println(String.format(
                        "Current 3D Movie Extra Charge: $%.2f",
                        Settings.charge3D
                    ));
                    newPrice = UtilUI.getPositiveFloat("Enter new price: ");
                    Settings.charge3D = newPrice;
                    break;
                case 5:
                    System.out.println(String.format(
                        "Current Blockbuster Movie Extra Charge: $%.2f",
                        Settings.chargeBlockbuster
                    ));
                    newPrice = UtilUI.getPositiveFloat("Enter new price: ");
                    Settings.chargeBlockbuster = newPrice;
                    break;
                case 6:
                    System.out.println(String.format(
                        "Current Holiday Extra Charge: $%.2f",
                        Settings.chargeHoliday
                    ));
                    newPrice = UtilUI.getPositiveFloat("Enter new price: ");
                    Settings.chargeHoliday = newPrice;
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid action, try again!");
                    break;
            }
            System.out.println("Changes saved\n");
        }
    }

    private static void editHolidayDates() {
        while (true) {
            System.out.println("### Edit Holiday Dates ###");
            System.out.print(
                "1. Display All Holiday Dates (with ID)\n" +
                "2. Add Holiday Date\n" +
                "3. Remove Holiday Date\n" +
                "4. Exit\n\n");
            int choice = UtilUI.getInt("Select action: ");
            switch (choice) { 
                case 1:
                    for (int i = 0; i < Settings.holidayDates.size(); i++) {
                        Date x = Settings.holidayDates.get(i);
                        System.out.println(String.format("ID %d: %s",
                            i + 1, dateFormat.format(x)));
                    }
                    System.out.println("");
                    break;
                case 2:
                    String date = UtilUI.getStr("Enter New Holiday Date (Format DD/MM/YYYY): ");
                    int status = SettingsController.addHoliday(date);
                    switch (status) {
                        case 0:
                            System.out.println("Successfully added");
                            break;
                        case 1:
                            System.out.println("Incorrect format (DD/MM/YY required)");
                            break;
                        case 2:
                            System.out.println("No duplicate dates allowed");
                            break;
                    }
                    break;
                case 3:
                    int id = UtilUI.getInt("Enter Date ID (to be deleted): ");
                    if (SettingsController.removeHoliday(id)) {
                        System.out.println("Date successfully deleted!");
                    } else {
                        System.out.println("Invalid holiday ID");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid action, try again!");
                    break;
            }
        }
    }
}