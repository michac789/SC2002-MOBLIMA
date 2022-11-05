package Boundaries;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.AdminController;
import DAO.AdminDAO;

public class AdminUI { 
    AdminController adminController; 
    public void main () {
        adminController = new AdminController(); 
        // dummy data 
        ArrayList<String> user = new ArrayList<> ();  
        
        user.add("kristian"); 
        user.add("kristian123"); 
        adminController.adminList = new ArrayList<> (); 
        adminController.adminList.add(user);  
        String username = "", password = ""; 
        Scanner sc = new Scanner(System.in); 
        while(true) { 
            System.out.println("|=========|Admin Login|=========|");
            System.out.println("Enter your username: ");
            username = sc.nextLine().trim(); 
            System.out.println("Enter your password: ");
            password = sc.nextLine().trim();
            if(adminController.login(username, password)){
                break; 
            }
        }
        System.out.println("Successfully logged in!"); 
        System.out.println("Welcome " + username);
        this.administratorAction();
        // sc.close(); but hv to comment since it makes error 
    } 

    public void administratorAction() { 
        Scanner sc = new Scanner(System.in); 
        
        while (true){
            System.out.println("|=========================================|");
            System.out.println("|=========|MOBLIMA Administrator|=========|");
            System.out.println("|=========================================|\n" + 
                        "1. Create/Update/Remove movie listing\n"+
                        "2. Create/Update/Remove movie session\n"+
                        "3. Configure system settings\n" +
                        "4. Search/List movies\n" +
                        "5. View move details\n" + 
                        "6. Create another admin account\n" +
                        "7. Log out\n");
            System.out.print("Select action: ");  
            int choice = sc.nextInt();  
            switch(choice){ 
                case 1: 
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6: 
                    this.createAdminAccount(); 
                    break; 
                case 7:
                    System.out.println("Logged out successfully!");  
                    return; 
                default:
                    System.out.println("Invalid action, try again!");
                    break;
            }
        }   
    }

    public void createAdminAccount(){
        Scanner sc = new Scanner(System.in);
        String username = "", password = "", confirmationPassword = "";
        do {
            System.out.println("Please enter admin username: ");
            username = sc.nextLine().trim();
            System.out.println("Password: ");
            password = sc.nextLine().trim();
            System.out.println("Password again to confirm: ");
            confirmationPassword = sc.nextLine().trim();


            if (!adminController.isAdminExist(username) && password.equals(confirmationPassword)){
                AdminDAO adminDAO = new AdminDAO();
                adminDAO.createAdmin(username, password);
                break;
            }
            System.out.println("Cannot make the account. Either the admin user" +
                " already exists or password and confirmation password does not match");

        } while(true);

    }
}
