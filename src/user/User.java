package user;
public abstract class User { 
    private String username; 
    protected enum UserType { MOVIEGOER, ADMIN }; 
    private UserType userType; 

    public User (String username){
        this.username = username; 
    }

    public String getUsername () { return this.username; } 


    public boolean hasAdminAccess () { return this.userType == UserType.ADMIN ? true : false;} 
    protected void setType(UserType userType) { this.userType = userType; }

}
