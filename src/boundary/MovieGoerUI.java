package boundary;
import java.util.Scanner;
import controller.AppController;

public class MovieGoerUI {
    private static Scanner sc = new Scanner(System.in);

    public static int main() {
        while (true) {
            System.out.println("|=========|MovieGoer Login/Register UI|=========|");
            System.out.print(
                "1. Login By Username\n" +
                "2. Register New Account\n" +
                "3. Exit\n");
            System.out.print("Select action: ");  
            int choice = sc.nextInt();
            sc.nextLine();
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
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        int id = AppController.mgc.getMovieGoerIdByUsername(username);
        if (id == -1) {
            System.out.println("This username is not registered yet!");
        }
        return id;
    }

    private static void movieGoerRegister() {
        System.out.println("Creating New MovieGoer User");
        System.out.print("Enter Username: ");
        String username;
        while (true) {
            username = sc.nextLine();
            if (!AppController.mgc.isMovieGoerExist(username)) {
                break;
            }
            System.out.println("No duplicate username allowed!");
        }
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        AppController.mgc.createNewMovieGoer(username, email, phoneNumber, age);
        System.out.println("MovieGoer user created!");
    }
}