package boundary;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UtilUI {
    private static Scanner sc = new Scanner(System.in);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BLUE = "\u001B[34m";

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
        System.out.println("");
        System.out.println(ANSI_BLUE + "             MOvie Booking and LIsting Management Application");
        System.out.println(ANSI_RESET + "");
    }

    public static int getInt(String msg) {
        int number;
        while (true) {
            System.out.print(msg);
            try {
                number = sc.nextInt();
                sc.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input must be an integer!");
                sc.nextLine();
            }
        }
        return number;
    }

    public static float getPositiveFloat(String msg) {
        float number;
        while (true) {
            System.out.print(msg);
            try {
                number = sc.nextFloat();
                sc.nextLine();
                if (number < 0) {
                    System.out.println("Number cannot be negative!");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be a number!");
                sc.nextLine();
            }
        }
        return number;
    }

    public static String getStr(String msg) {
        System.out.print(msg);
        String str = sc.nextLine().trim();
        return str;
    }
}