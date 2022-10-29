package User;

public abstract class User { 
    private String username;  
    public User (String username){
        this.username = username; 
    }
    public String getUsername () { 
        return this.username; 
    }; 
    public abstract boolean hasAdminAccess (); 
}
