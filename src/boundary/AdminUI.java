package boundary;
import java.util.Scanner;
import controller.AdminController;

public class AdminUI { 
    public static AdminController adminController;
    static Scanner sc = new Scanner(System.in);

    public static void main() {
        adminController = new AdminController();
        String username, password;
        while (true) { 
            System.out.println("|=========|Admin Login|=========|");
            System.out.println("Enter your username: ");
            username = sc.nextLine().trim(); 
            System.out.println("Enter your password: ");
            password = sc.nextLine().trim();
            if (adminController.login(username, password)) {
                break;
            }
            System.out.println("Wrong password or username does not exist");
        }
        System.out.println("Successfully logged in!"); 
        System.out.println("Welcome, " + username);
        administratorAction();
    } 

    public static void administratorAction() {
        while (true) {
            System.out.println("|=========================================|");
            System.out.println("|=========|MOBLIMA Administrator|=========|");
            System.out.println("|=========================================|\n" +
                        "1. View/Create/Edit Movie\n" +
                        "2. View/Create/Edit Cineplex\n" +
                        "3. View/Create/Edit Cinema\n" + // IN PROGRESS
                        "4. View/Create/Edit Showtime\n" + // IN PROGRESS
                        "5. Display & Configure System Settings\n" +
                        "6. Create Another Admin Account\n" +
                        "7. Log Out\n");
            System.out.print("Select action: ");  
            int choice = sc.nextInt();  
            switch (choice) { 
                case 1:
                    MovieUI.admin();
                    break;
                case 2:
                    CineplexUI.admin();
                    break;
                case 3:
                    // TODO
                    break;
                case 4:
                    // TODO
                    break;
                case 5:
                    SettingsUI.admin();
                    break;
                case 6: 
                    createAdminAccount();
                    break;
                case 7:
                    System.out.println("Logged out successfully!");
                    AdminUI.adminController.save();
                    return; 
                default:
                    System.out.println("Invalid action, try again!");
                    break;
            }
        }
    }
    
    public static void createAdminAccount(){
        String username, password, confirmationPassword;
        sc.nextLine();
        do {
            System.out.println("Please enter admin username: ");
            username = sc.nextLine().trim();
            System.out.println("Password: ");
            password = sc.nextLine().trim();
            System.out.println("Password again to confirm: ");
            confirmationPassword = sc.nextLine().trim();
            if (!adminController.isAdminExist(username) && password.equals(confirmationPassword)) {
                adminController.createAccount(username, password);
                break;
            }
            System.out.println("Cannot make the account. Either the admin user" +
                " already exists or password and confirmation password does not match");
        } while(true);
    }
}