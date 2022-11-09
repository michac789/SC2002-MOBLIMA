package boundary;
import java.util.Scanner;
import model.Settings;

public class SettingsUI {
    private static Scanner sc = new Scanner(System.in);

    public static void admin() {
        while (true) {
            System.out.println("|=========|Settings Configuration Admin Panel|=========|");
            System.out.print(
                "1. Display Price Settings\n" +
                "2. Display Holiday Dates\n" +
                "3. Edit Price Settings\n" +
                "4. Edit Holiday Settings\n" +
                "5. Exit");
            System.out.print("Select action: ");  
            int choice = sc.nextInt();
            switch (choice) { 
                case 1:
                    displayPriceSettings();
                    break;
                case 2:
                    // TODO
                    break;
                case 3:
                    editPriceSettings();
                    break;
                case 4:
                    // TODO
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
        System.out.println("### Displaying price settings ###\n" +
            String.format(
                "Class1 Cinema Price: $%.2f\n" +
                "Class2 Cinema Price: $%.2f\n" +
                "Class3 Cinema Price: $%.2f\n" +
                "3D Movie Extra Charge: $%.2f\n" +
                "Blockbuster Movie Extra Charge: $%.2f\n" +
                "Holiday Extra Charge: $%.2f\n\n",
                Settings.class1Price, Settings.class2Price,
                Settings.class3Price, Settings.charge3D,
                Settings.chargeBlockbuster, Settings.chargeHoliday
            )
        );
    }

    private static void editPriceSettings() {
        System.out.println("Settings");
        while (true) {
            System.out.println("Edit Price Configuration:");
            System.out.print(
                "1. Edit Class1 Price\n" +
                "2. Edit Class2 Price\n" +
                "3. Edit Class3 Price\n" +
                "4. Edit 3D Movie Extra Charge\n" +
                "5. Edit Blockbuster Movie Extra Charge\n" +
                "6. Edit Holiday Extra Charge\n" +
                "7. Exit");
            System.out.print("Select action: ");  
            int choice = sc.nextInt();
            int newPrice;
            switch (choice) { 
                case 1:
                    System.out.println(String.format(
                        "Current Class1 Price: $%.2f",
                        Settings.class1Price
                    ));
                    System.out.println("Enter new price: ");
                    newPrice = sc.nextInt();
                    Settings.class1Price = newPrice;
                    break;
                case 2:
                    System.out.println(String.format(
                        "Current Class2 Price: $%.2f",
                        Settings.class2Price
                    ));
                    System.out.println("Enter new price: ");
                    newPrice = sc.nextInt();
                    Settings.class2Price = newPrice;
                    break;
                case 3:
                    System.out.println(String.format(
                        "Current Class3 Price: $%.2f",
                        Settings.class3Price
                    ));
                    System.out.println("Enter new price: ");
                    newPrice = sc.nextInt();
                    Settings.class3Price = newPrice;
                    break;
                case 4:
                    System.out.println(String.format(
                        "Current 3D Movie Extra Charge: $%.2f",
                        Settings.charge3D
                    ));
                    System.out.println("Enter new price: ");
                    newPrice = sc.nextInt();
                    Settings.charge3D = newPrice;
                    break;
                case 5:
                    System.out.println(String.format(
                        "Current Blockbuster Movie Extra Charge: $%.2f",
                        Settings.chargeBlockbuster
                    ));
                    System.out.println("Enter new price: ");
                    newPrice = sc.nextInt();
                    Settings.chargeBlockbuster = newPrice;
                    break;
                case 6:
                    System.out.println(String.format(
                        "Current Holiday Extra Charge: $%.2f",
                        Settings.chargeHoliday
                    ));
                    System.out.println("Enter new price: ");
                    newPrice = sc.nextInt();
                    Settings.chargeHoliday = newPrice;
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid action, try again!");
                    break;
            }
        }
    }
}