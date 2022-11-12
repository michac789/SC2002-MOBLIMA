package boundary;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UtilUI {
    private static Scanner sc = new Scanner(System.in);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    /*
     * Display 'MOBLIMA' welcome message in the beginning of running the application
     */
    public static void printWelcomeMessage() {
        System.out.println(ANSI_CYAN);
        System.out.println(" __       __   ______   _______   __       ______  __       __   ______   ");
        System.out.println(" /  \\     /  | /      \\ /       \\ /  |     /      |/  \\     /  | /      \\  ");
        System.out.println(" $$  \\   /$$ |/$$$$$$  |$$$$$$$  |$$ |     $$$$$$/ $$  \\   /$$ |/$$$$$$  | ");
        System.out.println(" $$$  \\ /$$$ |$$ |  $$ |$$ |__$$ |$$ |       $$ |  $$$  \\ /$$$ |$$ |__$$ | ");
        System.out.println(" $$$$  /$$$$ |$$ |  $$ |$$    $$< $$ |       $$ |  $$$$  /$$$$ |$$    $$ | ");
        System.out.println(" $$ $$ $$/$$ |$$ |  $$ |$$$$$$$  |$$ |       $$ |  $$ $$ $$/$$ |$$$$$$$$ | ");
        System.out.println(" $$ |$$$/ $$ |$$ \\__$$ |$$ |__$$ |$$ |_____ _$$ |_ $$ |$$$/ $$ |$$ |  $$ | ");
        System.out.println(" $$ | $/  $$ |$$    $$/ $$    $$/ $$       / $$   |$$ | $/  $$ |$$ |  $$ | ");
        System.out.println(" $$/      $$/  $$$$$$/  $$$$$$$/  $$$$$$$$/$$$$$$/ $$/      $$/ $$/   $$/  ");
        System.out.println(ANSI_BLUE + "\n             MOvie Booking and LIsting Management Application\n");
    }

    /*
     * Display thank you message at the end when terminating the application
     */
    public static void printGoodbyeMessage() {
        System.out.println(ANSI_CYAN);
        System.out.println(" .-----..-.                .-.     .-..-.           ");
        System.out.println(" `-. .-': :                : :.-.  : :: :           ");
        System.out.println("   : :  : `-.  .--.  ,-.,-.: `'.'  `.  .'.--. .-..-.");
        System.out.println("   : :  : .. :' .; ; : ,. :: . `.   .' ;' .; :: :; :");
        System.out.println("   :_;  :_;:_;`.__,_;:_;:_;:_;:_;  :_,' `.__.'`.__.'");
        System.out.println(ANSI_BLUE + "\n                See You Next Time!\n");
    }

    /*
     * Utility function to get a valid integer from the user and return it
     * - Display error message if input is not an integer, prevent crashing
     * - Automatically call sc.nextLine to prevent error in getting strings
     * - The parameter 'msg' will be shown to the user during prompting
     */
    public static int getInt(String msg) {
        int number;
        while (true) {
            System.out.print(msg);
            try {
                number = sc.nextInt();
                sc.nextLine();
                break;
            } catch (InputMismatchException e) {
                printRed("Input must be an integer!");
                sc.nextLine();
            }
        }
        return number;
    }

    public static int getDiscount() {
        int number;
        while (true) {
            System.out.print("Enter new discount: ");
            try {
                number = sc.nextInt();
                sc.nextLine();
                if (number <= 0 || number > 100) {
                    printRed("Discount should only be from 1 to 100 percent!");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                printRed("Input must be an integer!");
                sc.nextLine();
            }
        }
        return number;
    }

    /*
     * Utility function to get a valid positive float, used mainly in edit pricings
     * - Display error message and prevent crashing if input is not a float,
     * - Ensure float entered is not negative and not more than 2 decimal place
     * - Automatically call sc.nextLine to prevent error in getting strings
     * - The parameter 'msg' will be shown to the user during prompting
     */
    public static float getPositiveFloat(String msg) {
        float number;
        while (true) {
            System.out.print(msg);
            try {
                number = sc.nextFloat();
                sc.nextLine();
                if (number < 0) {
                    printRed("Number cannot be negative!");
                } else if (String.valueOf(number).split("\\.")[1].length() > 2) {
                    printRed("Maximum of 2 decimal place allowed!");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                printRed("Input must be a number!");
                sc.nextLine();
            }
        }
        return number;
    }

    /*
     * Utility function to get a string and return it
     * - Automatically trim blank spaces on left and right
     * - Not allowing any comma to be entered, as comma can cause database parsing issues
     * - The parameter 'msg' will be shown to the user during prompting
     */
    public static String getStr(String msg) {
        String str;
        while (true) {
            System.out.print(msg);
            str = sc.nextLine().trim();
            if (!str.contains(",")) {
                break;
            }
            printRed("Comma is not allowed as it might cause parsing issues!");
        }
        return str;
    }

    public static String getStrSafe(String msg) {
        System.out.print(msg);
        String str = sc.nextLine().trim();
        return str;
    }

    /*
     * Utility function to get a boolean value (true/false) and return it
     * - Ask the user to enter 'yes' or 'no', reprompt otherwise
     * - Return true if user enter 'yes', return false if user enter 'no'
     * - The parameter 'msg' will be shown to the user during prompting
     */
    public static boolean getBool(String msg) {
        String str;
        while (true) {
            System.out.print(msg + "(yes/no): ");
            str = sc.nextLine().trim();
            if (str.toLowerCase().equals("yes")) {
                return true;
            } else if (str.toLowerCase().equals("no")) {
                return false;
            }
            UtilUI.printRed("Enter only 'yes' or 'no'!");
        }
    }

    /*
     * Print message in red color, mainly to display error messages
     */
    public static void printRed(String msg) {
        System.out.println(ANSI_RED + msg + ANSI_RESET);
    }

    /*
     * Print message in green color, mainly to display success messages
     */
    public static void printGreen(String msg) {
        System.out.println(ANSI_GREEN + msg + ANSI_RESET);
    }

    /*
     * Print message in blue color, mainly as a heading on various sections
     */
    public static void printBlue(String msg) {
        System.out.println(ANSI_BLUE + msg + ANSI_RESET);
    }

    /*
     * Print message in purple color, to display other important information
     */
    public static void printPurple(String msg) {
        System.out.println(ANSI_PURPLE + msg + ANSI_RESET);
    }
}