package boundary;
import controller.AdminController;

public class AdminUI { 
    public static AdminController adminController;

    public static void main() {
        adminController = new AdminController();
        String username, password;
        int attempt = 0;
        while (true) { 
            System.out.println("|=========|Admin Login|=========|");
            username = UtilUI.getStr("Enter your username: ");
            password = UtilUI.getStr("Enter your password: ");
            if (adminController.login(username, password)) {
                break;
            }
            UtilUI.printRed("Wrong password or admin account does not exist!");
            if (++attempt == 3) { return;}
        }
        UtilUI.printGreen("Successfully logged in!"); 
        UtilUI.printPurple("Welcome, " + username);
        administratorAction();
    } 

    public static void administratorAction() {
        while (true) {
            UtilUI.printBlue("|=========================================|");
            UtilUI.printBlue("|=========|MOBLIMA Administrator|=========|");
            UtilUI.printBlue("|=========================================|");
            System.out.println(
                        "1. View/Create/Edit Movie\n" +
                        "2. View/Create/Edit Cineplex\n" +
                        "3. View/Create/Edit Cinema\n" +
                        "4. View/Create/Edit Showtime\n" +
                        "5. Display & Configure System Settings\n" +
                        "6. Create Another Admin Account\n" +
                        "7. Log Out\n");
            int choice = UtilUI.getInt("Select action: ");
            switch (choice) { 
                case 1:
                    MovieUI.admin();
                    break;
                case 2:
                    CineplexUI.admin();
                    break;
                case 3:
                    CinemaUI.admin();
                    break;
                case 4:
                    ShowtimeUI.admin();
                    break;
                case 5:
                    SettingsUI.admin();
                    break;
                case 6: 
                    createAdminAccount();
                    break;
                case 7:
                    UtilUI.printGreen("Logged out successfully!");
                    AdminUI.adminController.save();
                    return; 
                default:
                    UtilUI.printRed("Invalid action, try again!");
                    break;
            }
        }
    }
    
    public static void createAdminAccount(){
        String username, password, confirmationPassword;
        do {
            username = UtilUI.getStr("Please enter admin username: ");
            password = UtilUI.getStr("Password: ");
            confirmationPassword = UtilUI.getStr("Password again to confirm: ");
            if (!adminController.isAdminExist(username) && password.equals(confirmationPassword)) {
                adminController.createAccount(username, password);
                break;
            }
            UtilUI.printRed("Cannot make the account. Either the admin user" +
                " already exists or password and confirmation password does not match");
        } while (true);
    }
}