package boundary;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import controller.SettingsController;
import model.Settings;

/**
 * Represents the boundary of settings configuration for admin
 * @version 1.0
 * @since 2022-11-13
 */
public class SettingsUI {
    /**
     * Date format object to format date
     */
    private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Main function that is called when logged in as admin
     * Allows to interact with Settings controller directly
     */
    public static void admin() {
        while (true) {
            UtilUI.printBlue("|=========|Settings Configuration Admin Panel|=========|");
            System.out.print(
                "(1) Display Price Settings\n" +
                "(2) Display Holiday Dates\n" +
                "(3) Edit Price Settings\n" +
                "(4) Edit Holiday Dates\n" +
                "(5) Exit\n\n");
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
                    UtilUI.printRed("Invalid action, try again!");
                    break;
            }
        }
    }

    /**
     * Display the current price settings configuration
     */
    private static void displayPriceSettings() {
        UtilUI.printBlue("### Displaying Price Settings ###\n" +
            String.format(
                "Silver Class Cinema Price     : $%.2f\n" +
                "Gold Class Cinema Price       : $%.2f\n" +
                "Platinum Class Cinema Price   : $%.2f\n" +
                "3D Movie Extra Charge         : $%.2f\n" +
                "Blockbuster Movie Extra Charge: $%.2f\n" +
                "Holiday Extra Charge          : $%.2f\n" +
                "Child Discount                : %d%%\n" +
                "Senior Citizen Discount       : %d%%\n" +
                "Afternoon Showtime Discount   : %d%%\n" +
                "Midnight Showtime Discount    : %d%%\n" +
                "Promo Code Discount           : %d%%\n" +
                "Promo Code                    : %d\n\n",
                Settings.silverPrice, Settings.goldPrice,
                Settings.platinumPrice, Settings.charge3D,
                Settings.chargeBlockbuster, Settings.chargeHoliday,
                Settings.childDiscount, Settings.seniorDiscount,
                Settings.afternoonDiscount, Settings.midnightDiscount,
                Settings.promoDiscount, Settings.promoCode
            )
        );
    }

    /**
     * Display current holiday dates from database
     */
    private static void displayHolidayDates() {
        UtilUI.printBlue("### Displaying Holiday Dates ###");
        for (int i = 0; i < Settings.holidayDates.size(); i++) {
            System.out.println(dateFormat.format(Settings.holidayDates.get(i)));
        }
        System.out.println("");
    }

    /**
     * Display UI to choose action to edit price configuration
     */
    private static void editPriceSettings() {
        while (true) {
            UtilUI.printBlue("### Edit Price Configuration ###");
            System.out.print(
                "(1) Edit Silver Class Price\n" +
                "(2) Edit Gold Class Price\n" +
                "(3) Edit Platinum Price\n" +
                "(4) Edit 3D Movie Extra Charge\n" +
                "(5) Edit Blockbuster Movie Extra Charge\n" +
                "(6) Edit Holiday Extra Charge\n" +
                "(7) Edit Child Discount\n" +
                "(8) Edit Senior Citizen Discount\n" +
                "(9) Edit Afternoon Showtime Discount\n" +
                "(10) Edit Midnight Showtime Discount\n" +
                "(11) Edit Promo Code Discount\n" +
                "(12) Edit Promo Code\n" +
                "(13) Exit\n\n");
            int choice = UtilUI.getInt("Select action: ");
            float newPrice;
            int newDiscount;
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
                    System.out.println(String.format(
                        "Current Child Discount: %d%%",
                        Settings.childDiscount
                    ));
                    newDiscount = UtilUI.getDiscount();
                    Settings.childDiscount = newDiscount;
                    break;
                case 8:
                    System.out.println(String.format(
                        "Current Senior Citizen Discount: %d%%",
                        Settings.seniorDiscount
                    ));
                    newDiscount = UtilUI.getDiscount();
                    Settings.seniorDiscount = newDiscount;
                    break;
                case 9:
                    System.out.println(String.format(
                        "Current Afternoon Showtime Discount: %d%%",
                        Settings.afternoonDiscount
                    ));
                    newDiscount = UtilUI.getDiscount();
                    Settings.afternoonDiscount = newDiscount;
                    break;
                case 10:
                    System.out.println(String.format(
                        "Current Midnight Showtime Discount: %d%%",
                        Settings.midnightDiscount
                    ));
                    newDiscount = UtilUI.getDiscount();
                    Settings.midnightDiscount = newDiscount;
                    break;
                case 11:
                    System.out.println(String.format(
                        "Current Promo Code Discount: %d%%",
                        Settings.promoDiscount
                    ));
                    newDiscount = UtilUI.getDiscount();
                    Settings.promoDiscount = newDiscount;
                    break;
                case 12:
                    System.out.println(String.format(
                        "Current Promo Code: %d",
                        Settings.promoCode
                    ));
                    newDiscount = UtilUI.getInt("Enter new code: ");
                    Settings.promoCode = newDiscount;
                    break;
                case 13:
                    return;
                default:
                    UtilUI.printRed("Invalid action, try again!");
                    break;
            }
            System.out.println("Changes saved\n");
        }
    }

    /**
     * Display UI to choose action to configure holiday dates
     */
    private static void editHolidayDates() {
        while (true) {
            UtilUI.printBlue("### Edit Holiday Dates ###");
            System.out.print(
                "(1) Display All Holiday Dates (with ID)\n" +
                "(2) Add Holiday Date\n" +
                "(3) Remove Holiday Date\n" +
                "(4) Exit\n\n");
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
                            UtilUI.printGreen("Successfully added");
                            break;
                        case 1:
                            UtilUI.printRed("Incorrect format (DD/MM/YY required)");
                            break;
                        case 2:
                        UtilUI.printRed("No duplicate dates allowed");
                            break;
                    }
                    break;
                case 3:
                    int id = UtilUI.getInt("Enter Date ID (to be deleted): ");
                    if (SettingsController.removeHoliday(id)) {
                        UtilUI.printGreen("Date successfully deleted!");
                    } else {
                        UtilUI.printRed("Invalid holiday ID");
                    }
                    break;
                case 4:
                    return;
                default:
                    UtilUI.printRed("Invalid action, try again!");
                    break;
            }
        }
    }
}