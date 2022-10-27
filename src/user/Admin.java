package User;

public class Admin extends User { 
    // do we need to use ***** instead ? 
    private String password; 

    public Admin (String username, String password) { 
        super(username); 
        this.password = password; 
    }


    
    public boolean login (String password){ 
        if ((this.password).compareTo(password) == 1){ 
            return true; 
        }


        return false; 
    }


    public boolean hasAdminAccess() { 
        return true; 
    }
}
