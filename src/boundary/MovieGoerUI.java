/*
 * MovieGoerUI.java
 * 
 * This file is used for MovieGoer login/register functionality
 */
package boundary;
import controller.AppController;

public class MovieGoerUI {
    public static int main() {
        while (true) {
            UtilUI.printBlue("|=========|MovieGoer Login/Register UI|=========|");
            System.out.print(
                "(1) Login By Username\n" +
                "(2) Register New Account\n" +
                "(3) Exit\n");
            int choice = UtilUI.getInt("Select action: ");
            switch (choice) { 
                case 1:
                    int id = movieGoerLogin();
                    if (id != -1) { return id;}
                    break;
                case 2:
                    movieGoerRegister();
                    break;
                case 3:
                    return -1;
                default:
                    System.out.println("Invalid action, try again!");
                    break;
            }
        }
    }

    private static int movieGoerLogin() {
        UtilUI.printBlue("MovieGoer Login");
        String username = UtilUI.getStr("Enter username: ");
        int id = AppController.mgc.getMovieGoerIdByUsername(username);
        if (id == -1) {
            UtilUI.printRed("This username is not registered yet!");
        }
        UtilUI.printGreen("Login Successful!");
        return id;
    }

    private static void movieGoerRegister() {
        UtilUI.printBlue("MovieGoer Registration");
        String username;
        while (true) {
            username = UtilUI.getStr("Enter username: ");
            if (!AppController.mgc.isMovieGoerExist(username)) {
                break;
            }
            UtilUI.printRed("No duplicate username allowed!");
        }
        String email = UtilUI.getStr("Enter Email: ");
        String phoneNumber = UtilUI.getStr("Enter Phone Number: ");
        int age = UtilUI.getInt("Enter Age: ");
        AppController.mgc.createNewMovieGoer(username, email, phoneNumber, age);
        UtilUI.printGreen("MovieGoer user created!");
    }
}