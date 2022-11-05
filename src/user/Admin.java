package User;

public class Admin extends User { 
    // do we need to use ***** instead ? 
    private String password; 

    public Admin (String username, String password) { 
        super(username);  
        setType(UserType.ADMIN);
        this.password = password; 
    }

    public boolean hasAdminAccess() { 
        return true; 
    }
}
