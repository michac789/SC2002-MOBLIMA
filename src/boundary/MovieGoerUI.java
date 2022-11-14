package boundary;
import controller.AppController;
/**
 * Represents the boundary of moviegoer functionalities
 * @version 1.0
 * @since 2022-11-13
 */
public class MovieGoerUI {
    /**
     * Main UI when asking to log in or register as MovieGoer
     * @return The id of movieGoer if succeed to login
     */
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
                    UtilUI.printRed("Invalid action, try again!");
                    break;
            }
        }
    }

    /**
     * Prompts the user for username and password to login
     * Automatically return after 3 unsuccessful attempts
     * @return MovieGoerId if authentication successful, else return -1
     */
    private static int movieGoerLogin() {
        int movieGoerId, attempts = 0;
        while (true) {
            UtilUI.printBlue("|=========|Movie Goer Login|=========|");
            String username = UtilUI.getStr("Enter username: ");
            String password = UtilUI.getStr("Enter password: ");
            movieGoerId = AppController.mgc.login(username, password);
            if (movieGoerId != -1) { break;}
            UtilUI.printRed("Wrong password or account does not exist!");
            if (++attempts == 3) {
                UtilUI.printRed("Automatically exit after 3 unsuccessful attempts!");
                return -1;
            }
        }
        UtilUI.printGreen("Login Successful!\n");
        return movieGoerId;
    }

    /**
     * Prompts unique username, password, email, phone number, age
     * Register the information above as a new Movie Goer User
     */
    private static void movieGoerRegister() {
        UtilUI.printBlue("MovieGoer Registration");
        String username, password;
        while (true) {
            username = UtilUI.getStr("Enter username: ");
            if (!AppController.mgc.isMovieGoerExist(username)) {
                break;
            }
            UtilUI.printRed("No duplicate username allowed!");
        }
        while (true) {
            password = UtilUI.getStr("Enter password: ");
            String confirmationPassword = UtilUI.getStr("Password again to confirm: ");
            if (password.equals(confirmationPassword)) {
                break;
            }
            UtilUI.printRed("Confirmation password and password do not match!");
        }
        String email = UtilUI.getStr("Enter Email: ");
        String phoneNumber = UtilUI.getStr("Enter Phone Number: ");
        int age = UtilUI.getInt("Enter Age: ");
        AppController.mgc.createNewMovieGoer(username, password, email, phoneNumber, age);
        UtilUI.printGreen("MovieGoer user created!\n");
    }
}