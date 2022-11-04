package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// import java.util.List;

public class AdminController { 
    private List <List<String>> adminList; 
    
    public boolean main () {
        // dummy data 
        ArrayList<String> user = new ArrayList<> ();  
        
        // dummy data 
        user.add("kristian"); 
        user.add("kristian123"); 
        this.adminList = new ArrayList<> (); 
        this.adminList.add(user);  
        String username = "", password = ""; 
        Scanner sc = new Scanner(System.in); 
        do { 
            System.out.println("|=========|Admin Login|=========|");
            System.out.println("Enter your username: ");
            username = sc.nextLine().trim(); 
            System.out.println("Enter your password: ");
            password = sc.nextLine().trim();
            if(this.login(username, password)){
                break; 
            }

        } while (true); 
        System.out.println("Successfully logged in!"); 
        System.out.println("Welcome " + username);
        // sc.close(); but hv to comment since it makes error 
        return true; 
    } 

    public boolean administratorAction() { 
        Scanner sc = new Scanner(System.in); 
        System.out.println("|=========================================|");
        System.out.println("|=========|MOBLIMA Administrator|=========|");
        System.out.println("|=========================================|\n" + 
                    "1. Create/Update/Remove movie listing\n"+
                    "2. Create/Update/Remove movie session\n"+
                    "3. Configure system settings\n" +
                    "4. Search/List movies\n" +
                    "5. View move details\n" + 
                    "6. Create another admin account" + 
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
                return false;  
            default:
                System.out.println("Invalid action, try again!");
                break;
        }
        return true; 

    }
    

    public boolean login (String username, String password){  
        for(int i = 0; i<this.adminList.size(); i++){ 
            if (username.equals(this.adminList.get(i).get(0)) && password.equals(this.adminList.get(i).get(1))){
                return true; 
            }

        } 
        System.out.println("Wrong password");
        return false; 
    }
 
    public void createAdminAccount(){ 




    }

    public boolean isAdminExist() { 

        // do checking


        return false; 

        
    }
    
}
