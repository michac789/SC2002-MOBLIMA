package boundary;
import controller.AppController;

public class MovieGoerUI {
    public static int main() {
        while (true) {
            System.out.println("|=========|MovieGoer Login/Register UI|=========|");
            System.out.print(
                "1. Login By Username\n" +
                "2. Register New Account\n" +
                "3. Exit\n");
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
        String username = UtilUI.getStr("Enter username: ");
        int id = AppController.mgc.getMovieGoerIdByUsername(username);
        if (id == -1) {
            System.out.println("This username is not registered yet!");
        }
        return id;
    }

    private static void movieGoerRegister() {
        System.out.println("Creating New MovieGoer User");
        String username;
        while (true) {
            username = UtilUI.getStr("Enter username: ");
            if (!AppController.mgc.isMovieGoerExist(username)) {
                break;
            }
            System.out.println("No duplicate username allowed!");
        }
        String email = UtilUI.getStr("Enter Email: ");
        String phoneNumber = UtilUI.getStr("Enter Phone Number: ");
        int age = UtilUI.getInt("Enter Age: ");
        AppController.mgc.createNewMovieGoer(username, email, phoneNumber, age);
        System.out.println("MovieGoer user created!");
    }
}